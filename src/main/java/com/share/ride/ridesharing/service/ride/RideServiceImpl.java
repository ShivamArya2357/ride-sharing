package com.share.ride.ridesharing.service.ride;

import com.share.ride.ridesharing.enums.ErrorCode;
import com.share.ride.ridesharing.enums.PreferredVehicle;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.helper.RideHelper;
import com.share.ride.ridesharing.model.Ride;
import com.share.ride.ridesharing.model.User;
import com.share.ride.ridesharing.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.share.ride.ridesharing.enums.Status.CLOSED;
import static com.share.ride.ridesharing.enums.Status.IN_PROGRESS;

@Component
public class RideServiceImpl implements RideService {

    private static final Logger logger = LoggerFactory.getLogger(RideServiceImpl.class);

    private static final String MOST_VACANT = "Most Vacant";

    private static final List<Ride> rides = new ArrayList<>();
    private static final Set<String> activesRides = new HashSet<>();

    /**
     * Using this method user can offer a ride
     *
     * @param inputRide
     */
    @Override
    public Ride offerRide(Ride inputRide) {

        logger.info("Inside RideServiceImpl...offerRide()");
        if (isActiveRide(inputRide)) {
            inputRide.setStatus(IN_PROGRESS);
            generateRideId(inputRide);
            mapOfferedRideToUser(inputRide);
            rides.add(inputRide);
            activesRides.add(inputRide.getVehicle().getVehicleNo());
            return inputRide;
        } else {
            throw new RideSharingException(ErrorCode.RIDE_IS_ALREADY_OFFERED, inputRide.getVehicle().getVehicleNo());
        }
    }

    /**
     * This method will map offeredRide to offeredUser
     *
     * @param inputRide
     */
    private void mapOfferedRideToUser(Ride inputRide) {

        User user = RideHelper.getUserByName(inputRide.getOfferedBy());
        user.setOfferedRide(user.getOfferedRide() + 1);
        user.setRideId(inputRide.getId());
    }

    /**
     * This method will generate ride id for every ride
     *
     * @param inputRide
     */
    private void generateRideId(Ride inputRide) {

        inputRide.setId(String.valueOf(ThreadLocalRandom.current().nextInt(10000)));
    }

    /**
     * This method will check If Ride has valid data or not
     *
     * @param inputRide
     * @return true or false
     */
    private boolean isActiveRide(Ride inputRide) {

        if (activesRides.contains(inputRide.getVehicle().getVehicleNo())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Using this method an user can select a ride
     *
     * @param inputRide
     * @return Ride
     */
    @Override
    public Ride selectRide(Ride inputRide) {

        logger.info("Inside RideServiceImpl...selectRide()");
        if (!rides.isEmpty()) {
            return selectRideWithStrategy(inputRide, inputRide.getSelectionStrategy());
        } else {
            throw new RideSharingException(ErrorCode.NO_RIDE_FOUND);
        }
    }

    /**
     * This method will select the appropriate ride from list of rides and will update the selected ride
     *
     * @param inputRide
     * @param strategy
     * @return Ride
     */
    private Ride selectRideWithStrategy(Ride inputRide, String strategy) {

        List<Ride> extractedRides = rides.stream().filter(
                                        ride -> IN_PROGRESS.equals(ride.getStatus())
                                        && ride.getAvailableSeats() > 0
                                        && ride.getOrigin().equals(inputRide.getOrigin())
                                        && ride.getDestination().equals(inputRide.getDestination())
                                        && ride.getAvailableSeats() >= inputRide.getAvailableSeats()
                                     ).collect(Collectors.toList());
        if (!extractedRides.isEmpty()) {
            Ride selectedRide;
            if (StringUtils.isNotEmpty(strategy.trim()) && !strategy.equalsIgnoreCase(MOST_VACANT)) {
                try {
                    PreferredVehicle.valueOf(strategy.trim().toUpperCase());
                    extractedRides = extractedRides.stream().filter(extractedRide -> extractedRide.getVehicle()
                            .getVehicleModel().equals(strategy.trim())
                    ).collect(Collectors.toList());
                    if (extractedRides.isEmpty()) {
                        throw new RideSharingException(ErrorCode.NO_RIDE_FOUND);
                    } else if (extractedRides.size() > 1) {
                        selectedRide = extractedRides.stream().max(Comparator.comparingInt(Ride::getAvailableSeats))
                                        .get();
                    } else {
                        selectedRide = extractedRides.get(0);
                    }
                } catch (Exception e) {
                    throw new RideSharingException(e);
                }
            } else {
                selectedRide = extractedRides.stream().max(Comparator.comparingInt(Ride::getAvailableSeats)).get();
            }
            mapSelectedRideToUser(inputRide);
            selectedRide.setAvailableSeats(selectedRide.getAvailableSeats() - 1);
            return selectedRide;
        } else {
            throw new RideSharingException(ErrorCode.NO_RIDE_FOUND);
        }
    }

    /**
     * This method will map selected ride to requested user
     *
     * @param inputRide
     */
    private void mapSelectedRideToUser(Ride inputRide) {

        User user = RideHelper.getUserByName(inputRide.getRequestedBy());
        user.setTakenRide(user.getTakenRide() + 1);
        user.setRideId(inputRide.getId());
    }

    /**
     * Using this method driver can end a ride
     *
     * @param inputRide
     * @return Ride
     */
    @Override
    public Ride endRide(Ride inputRide) {

        logger.info("Inside RideServiceImpl...endRide()");
        Optional<Ride> selectedRideOpt = rides.stream().filter(ride -> IN_PROGRESS.equals(ride.getStatus())
                        && ride.getVehicle().getVehicleNo().equals(inputRide.getVehicle().getVehicleNo())
        ).findFirst();
        if (selectedRideOpt.isPresent()) {
            Ride selectedRide = selectedRideOpt.get();
            selectedRide.setStatus(CLOSED);
            activesRides.remove(selectedRide.getVehicle().getVehicleNo());
            return selectedRide;
        } else {
            throw new RideSharingException(ErrorCode.NOT_A_VALID_RIDE);
        }
    }

    /**
     * This method will print details of rides taken and offered by a user
     *
     */
    @Override
    public void rideStatistics() {

        logger.info("Inside RideServiceImpl...rideStatistics()");
        List<User> users = RideHelper.getUsers();
        for (User user : users) {
            System.out.println(user.getUserName() + ": " + user.getTakenRide() + " Taken, " + user.getOfferedRide()
                    + " Offered"
            );
        }
    }

    /**
     * This method will return all the rides from source to destination
     *
     * @param inputRide
     * @return list of rides
     */
    @Override
    public List<Ride> searchRide(Ride inputRide) {

        logger.info("Inside RideServiceImpl...searchRide()");
        if (!rides.isEmpty()) {
            List<Ride> allRides = rides.stream().filter(ride -> IN_PROGRESS.equals(ride.getStatus())
                    && ride.getAvailableSeats() > 0
                    && ride.getOrigin().equals(inputRide.getOrigin())
                    && ride.getDestination().equals(inputRide.getDestination())
                    && ride.getAvailableSeats() >= inputRide.getAvailableSeats()
            ).collect(Collectors.toList());
            List<Ride> preferredRides;
            if (!allRides.isEmpty()) {
                preferredRides = searchRideWithStrategy(inputRide, allRides);
            } else {
                preferredRides = searchInterMediateRide(allRides, inputRide);
            }
            if (preferredRides != null && !preferredRides.isEmpty()) {
                return preferredRides;
            } else {
                return allRides;
            }
        } else {
            throw new RideSharingException(ErrorCode.NO_RIDE_FOUND);
        }
    }

    /**
     * This method will find indirect ride
     *
     * @param allRides
     * @param inputRide
     * @return List<Ride>
     */
    private List<Ride> searchInterMediateRide(List<Ride> allRides, Ride inputRide) {

        List<Ride> allRidesFromOrigin = allRides.stream().filter(ride -> IN_PROGRESS.equals(ride.getStatus())
                                            && ride.getAvailableSeats() > 0
                                            && ride.getOrigin().equals(inputRide.getOrigin())
                                            && !ride.getDestination().equals(inputRide.getDestination())
                                            && ride.getAvailableSeats() >= inputRide.getAvailableSeats()
        ).collect(Collectors.toList());
        List<Ride> allRidesEndInDest = allRides.stream().filter(ride -> IN_PROGRESS.equals(ride.getStatus())
                                        && ride.getAvailableSeats() > 0
                                        && !ride.getOrigin().equals(inputRide.getOrigin())
                                        && ride.getDestination().equals(inputRide.getDestination())
                                        && ride.getAvailableSeats() >= inputRide.getAvailableSeats()
        ).collect(Collectors.toList());
        List<Ride> allInterMediateRides = new ArrayList<>();
        for (Ride ridesFromOrigin : allRidesFromOrigin) {
            String destination = ridesFromOrigin.getDestination();
            for (Ride ridesEndInDest : allRidesEndInDest) {
                String origin = ridesEndInDest.getOrigin();
                if (destination.equals(origin)) {
                    allInterMediateRides.add(ridesFromOrigin);
                    allInterMediateRides.add(ridesEndInDest);
                }
            }
        }
        if (!allInterMediateRides.isEmpty()) {
            return searchRideWithStrategy(inputRide, allInterMediateRides);
        } else {
            throw new RideSharingException(ErrorCode.NO_RIDE_FOUND);
        }
    }

    /**
     * This method will select rides based on selected strategy
     *
     * @param inputRide
     * @param allRides
     * @return List<Ride>
     */
    public List<Ride> searchRideWithStrategy(Ride inputRide, List<Ride> allRides) {

        Map<Integer, List<Ride> > availableSeatsToRideMap = new HashMap<>();
        List<Ride> preferredRides = null;
        String selectionStrategy = inputRide.getSelectionStrategy();
        if (StringUtils.isNotEmpty(selectionStrategy) && MOST_VACANT.equalsIgnoreCase(selectionStrategy.trim())) {
            int maxAvailableSeats = 0;
            for (Ride ride : allRides) {
                Integer availableSeats = ride.getAvailableSeats();
                maxAvailableSeats = Math.max(maxAvailableSeats, availableSeats);
                if (availableSeatsToRideMap.containsKey(availableSeats)) {
                    List<Ride> temp = availableSeatsToRideMap.get(availableSeats);
                    temp.add(ride);
                } else {
                    List<Ride> temp = new ArrayList<>();
                    temp.add(ride);
                    availableSeatsToRideMap.put(availableSeats, temp);
                }
            }
            preferredRides = availableSeatsToRideMap.get(maxAvailableSeats);
        } else if (StringUtils.isNotEmpty(selectionStrategy)) {
            try {
                PreferredVehicle.valueOf(selectionStrategy.trim().toUpperCase());
                preferredRides = allRides.stream().filter(extractedRide -> extractedRide.getVehicle()
                        .getVehicleModel().equals(selectionStrategy.trim())
                ).collect(Collectors.toList());
            } catch (Exception e) {
                throw new RideSharingException(e);
            }
        }
        return preferredRides;
    }
}
