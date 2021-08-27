package com.share.ride.ridesharing.service.ride;

import com.share.ride.ridesharing.contract.Ride;
import com.share.ride.ridesharing.entity.VehicleEntity;
import com.share.ride.ridesharing.enums.ErrorCode;
import com.share.ride.ridesharing.enums.PreferredVehicle;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.helper.RideHelper;
import com.share.ride.ridesharing.entity.RideEntity;
import com.share.ride.ridesharing.entity.UserEntity;
import com.share.ride.ridesharing.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.share.ride.ridesharing.enums.Status.*;

@Component
public class RideServiceImpl implements RideService {

    private static final Logger logger = LoggerFactory.getLogger(RideServiceImpl.class);

    private static final String MOST_VACANT = "Most Vacant";
    private static final String UNDERSCORE = "_";

    private static final List<RideEntity> rides = new ArrayList<>();
    private static final Set<String> existingVehicles = new HashSet<>();

    /**
     * Using this method user can offer a ride
     *
     * @param input
     */
    @Override
    public Ride offerRide(Ride input) {

        logger.info("Inside RideServiceImpl...offerRide()");
        String vehicleNo = input.getVehicleNo();
        if (!isExistingRide(vehicleNo)) {
            mapToRideEntity(input);
            existingVehicles.add(vehicleNo);
            return input;
        } else {
            throw new RideSharingException(ErrorCode.RIDE_IS_ALREADY_OFFERED, vehicleNo);
        }
    }

    /**
     * This method will map offeredRide to offeredUser
     *
     * @param input
     */
    private void mapToRideEntity(Ride input) {

        RideEntity output = new RideEntity();
        output.setId(String.valueOf(ThreadLocalRandom.current().nextInt(10000)));
        output.setStatus(OFFERED);
        output.setOfferedBy(input.getOfferedBy());
        output.setOrigin(input.getOrigin());
        output.setDestination(input.getDestination());
        output.setAvailableSeats(input.getAvailableSeats());
        UserEntity userEntity = RideHelper.getUserById(input.getUserId());
        userEntity.setOfferedRide(userEntity.getOfferedRide() + 1);
        userEntity.setRideId(output.getId());
        output.setUserId(userEntity.getId());
        output.setVehicleId(input.getVehicleId());
        input.setId(output.getId());
        rides.add(output);
    }

    /**
     * This method will check If Ride has valid data or not
     *
     * @param vehicleNo
     * @return true or false
     */
    private boolean isExistingRide(String vehicleNo) {

        if (existingVehicles.contains(vehicleNo)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Using this method an user can select a ride
     *
     * @param input
     * @return Ride
     */
    @Override
    public Ride selectRide(Ride input) {

        logger.info("Inside RideServiceImpl...selectRide()");
        if (!rides.isEmpty()) {
            Optional<RideEntity> rideEntityOpt = rides.stream().filter(
                    rideEntity -> !ENDED.equals(rideEntity.getStatus()) && rideEntity.getId().equals(input.getId())
            ).findFirst();
            if (rideEntityOpt.isPresent()) {
                RideEntity rideEntity = rideEntityOpt.get();
                UserEntity userEntity = RideHelper.getUserById(input.getUserId());
                userEntity.setRideId(rideEntity.getId());
                userEntity.setTakenRide(userEntity.getTakenRide() + 1);
                rideEntity.setAvailableSeats(rideEntity.getAvailableSeats() - 1);
                rideEntity.setRequestedBy(userEntity.getUserName());
                rideEntity.setStatus(IN_PROGRESS);
                return input;
            } else {
                throw new RideSharingException(ErrorCode.NO_RIDE_FOUND);
            }
        } else {
            throw new RideSharingException(ErrorCode.NO_RIDE_FOUND);
        }
    }

    /**
     * Using this method driver can end a ride
     *
     * @param input
     * @return Ride
     */
    @Override
    public Ride endRide(Ride input) {

        logger.info("Inside RideServiceImpl...endRide()");
        Optional<RideEntity> selectedRideOpt = rides.stream().filter(ride -> !ENDED.equals(ride.getStatus())
                        && ride.getId().equals(input.getId())
        ).findFirst();
        if (selectedRideOpt.isPresent()) {
            RideEntity selectedRide = selectedRideOpt.get();
            selectedRide.setStatus(ENDED);
            existingVehicles.remove(findVehicleNo(selectedRide.getVehicleId()));
            return input;
        } else {
            throw new RideSharingException(ErrorCode.NOT_A_VALID_RIDE);
        }
    }

    /**
     * This method will return vehicleNo
     *
     * @param vehicleId
     * @return vehicleNo
     */
    private String findVehicleNo(String vehicleId) {

        VehicleEntity vehicleEntity = RideHelper.getVehicleById(vehicleId);
        return vehicleEntity.getVehicleNo();
    }

    /**
     * This method will print details of rides taken and offered by a user
     *
     */
    @Override
    public void rideStatistics() {

        logger.info("Inside RideServiceImpl...rideStatistics()");
        List<UserEntity> users = RideHelper.getAllUsers();
        for (UserEntity user : users) {
            System.out.println(user.getUserName() + ": " + user.getTakenRide() + " Taken, " + user.getOfferedRide()
                    + " Offered"
            );
        }
    }

    /**
     * This method will return all the rides from source to destination
     *
     * @param input
     * @return list of rides
     */
    @Override
    public List<Ride> searchRide(Ride input) {

        logger.info("Inside RideServiceImpl...searchRide()");
        List<RideEntity> ridesFilteredWithStrategy = new ArrayList<>();
        if (!rides.isEmpty()) {
            List<RideEntity> allRides = rides.stream().filter(ride -> !ENDED.equals(ride.getStatus())
                                            && ride.getAvailableSeats() > 0
                                            && ride.getOrigin().equals(input.getOrigin())
                                            && ride.getDestination().equals(input.getDestination())
                                            && ride.getAvailableSeats() >= input.getAvailableSeats()
            ).collect(Collectors.toList());
            if (!allRides.isEmpty()) {
                ridesFilteredWithStrategy = searchRideWithStrategy(input, allRides);
            }
            if (allRides.isEmpty() || ridesFilteredWithStrategy.isEmpty()) {
                ridesFilteredWithStrategy = searchIntermediateRide(input); // find intermediate rides
            }
        }
        if (!ridesFilteredWithStrategy.isEmpty()) {
            return mapToRideModel(ridesFilteredWithStrategy);
        } else {
            throw new RideSharingException(ErrorCode.NO_RIDE_FOUND);
        }
    }

    /**
     * This method will map database entity to pojo
     *
     * @param rideEntities
     * @return List<Ride>
     */
    private List<Ride> mapToRideModel(List<RideEntity> rideEntities) {

        List<Ride> rides = new ArrayList<>();
        for (RideEntity rideEntity : rideEntities) {
            Ride ride = new Ride();
            ride.setId(rideEntity.getId());
            ride.setOfferedBy(rideEntity.getOfferedBy());
            ride.setOrigin(rideEntity.getOrigin());
            ride.setIntermediateStop(rideEntity.getIntermediateStop());
            ride.setDestination(rideEntity.getDestination());
            rides.add(ride);
        }
        return rides;
    }


    /**
     * This method will find indirect ride
     *
     * @param input
     * @return List<Ride>
     */
    private List<RideEntity> searchIntermediateRide(Ride input) {

        List<RideEntity> allRidesFromOrigin = rides.stream().filter(ride -> !ENDED.equals(ride.getStatus())
                                                && ride.getAvailableSeats() > 0
                                                && ride.getOrigin().equals(input.getOrigin())
                                                && !ride.getDestination().equals(input.getDestination())
                                                && ride.getAvailableSeats() >= input.getAvailableSeats()
        ).collect(Collectors.toList());
        List<RideEntity> allRidesEndInDest = rides.stream().filter(ride -> !ENDED.equals(ride.getStatus())
                                                && ride.getAvailableSeats() > 0
                                                && !ride.getOrigin().equals(input.getOrigin())
                                                && ride.getDestination().equals(input.getDestination())
                                                && ride.getAvailableSeats() >= input.getAvailableSeats()
        ).collect(Collectors.toList());
        List<RideEntity> allInterMediateRides = new ArrayList<>();
        Map<RideEntity, RideEntity> rideWithStopMap = new HashMap<>();
        if (!allRidesFromOrigin.isEmpty() && !allRidesEndInDest.isEmpty()) {
            for (RideEntity ridesFromOrigin : allRidesFromOrigin) {
                String destination = ridesFromOrigin.getDestination();
                for (RideEntity ridesEndInDest : allRidesEndInDest) {
                    String origin = ridesEndInDest.getOrigin();
                    if (destination.equals(origin)) {
                        allInterMediateRides.add(ridesFromOrigin);
                        allInterMediateRides.add(ridesEndInDest);
                        rideWithStopMap.put(ridesFromOrigin, ridesEndInDest);
                    }
                }
            }
        }
        if (!allInterMediateRides.isEmpty()) {
            return searchIntermediateRideWithStrategy(input, rideWithStopMap, allInterMediateRides);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * This method will filter out indirect rides with given strategy
     *
     * @param input
     * @param rideWithStopMap
     * @param allInterMediateRides
     * @return List<RideEntity>
     */
    public List<RideEntity> searchIntermediateRideWithStrategy(Ride input, Map<RideEntity, RideEntity> rideWithStopMap,
                                                               List<RideEntity> allInterMediateRides
    ) {

        List<RideEntity> ridesFilteredWithStrategy = new ArrayList<>();
        String selectionStrategy = input.getSelectionStrategy();
        Map<Integer, List<RideEntity> > availableSeatsToRideMap = new HashMap<>();
        if (StringUtils.isNotEmpty(selectionStrategy) && MOST_VACANT.equalsIgnoreCase(selectionStrategy)) {
            int maxAvailableSeats = 0;
            for (Map.Entry<RideEntity, RideEntity> entry : rideWithStopMap.entrySet()) {
                RideEntity ridesFromOrigin = entry.getKey();
                RideEntity ridesEndInDest = entry.getValue();
                int availableSeats = ridesFromOrigin.getAvailableSeats();
                maxAvailableSeats = Math.max(maxAvailableSeats, availableSeats);
                List<RideEntity> temp;
                if (availableSeatsToRideMap.containsKey(availableSeats)) {
                    temp = availableSeatsToRideMap.get(availableSeats);
                    temp.add(ridesFromOrigin);
                    temp.add(ridesEndInDest);
                } else {
                    temp = new ArrayList<>();
                    temp.add(ridesFromOrigin);
                    temp.add(ridesEndInDest);
                    availableSeatsToRideMap.put(availableSeats, temp);
                }
            }
            ridesFilteredWithStrategy = availableSeatsToRideMap.get(maxAvailableSeats);
        } else if (StringUtils.isNotEmpty(selectionStrategy)) {
            PreferredVehicle.valueOf(selectionStrategy.toUpperCase());
            try {
                for (Map.Entry<RideEntity, RideEntity> entry : rideWithStopMap.entrySet()) {
                    RideEntity ridesFromOrigin = entry.getKey();
                    RideEntity ridesEndInDest = entry.getValue();
                    VehicleEntity vehicleEntity1 = RideHelper.getVehicleById(ridesFromOrigin.getVehicleId());
                    VehicleEntity vehicleEntity2 = RideHelper.getVehicleById(ridesEndInDest.getVehicleId());
                    String vehicleModel1 = vehicleEntity1.getVehicleModel();
                    String vehicleModel2 = vehicleEntity2.getVehicleModel();
                    if (vehicleModel1.equalsIgnoreCase(selectionStrategy)
                            && vehicleModel2.equalsIgnoreCase(selectionStrategy)
                    ) {
                        ridesFilteredWithStrategy.add(ridesFromOrigin);
                        ridesFilteredWithStrategy.add(ridesEndInDest);
                    }
                }
            } catch (Exception e) {
                throw new RideSharingException(e);
            }
        } else {
            return allInterMediateRides;
        }
        return ridesFilteredWithStrategy;
    }

    /**
     * This method will select rides based on selected strategy
     *
     * @param input
     * @param allRides
     * @return List<Ride>
     */
    public List<RideEntity> searchRideWithStrategy(Ride input, List<RideEntity> allRides) {

        Map<Integer, List<RideEntity> > availableSeatsToRideMap = new HashMap<>();
        List<RideEntity> ridesFilteredWithStrategy;
        String selectionStrategy = input.getSelectionStrategy();
        if (StringUtils.isNotEmpty(selectionStrategy) && MOST_VACANT.equalsIgnoreCase(selectionStrategy)) {
            int maxAvailableSeats = 0;
            for (RideEntity ride : allRides) {
                Integer availableSeats = ride.getAvailableSeats();
                maxAvailableSeats = Math.max(maxAvailableSeats, availableSeats);
                List<RideEntity> temp;
                if (availableSeatsToRideMap.containsKey(availableSeats)) {
                    temp = availableSeatsToRideMap.get(availableSeats);
                    temp.add(ride);
                } else {
                    temp = new ArrayList<>();
                    temp.add(ride);
                    availableSeatsToRideMap.put(availableSeats, temp);
                }
            }
            ridesFilteredWithStrategy = availableSeatsToRideMap.get(maxAvailableSeats);
        } else if (StringUtils.isNotEmpty(selectionStrategy)) {
            try {
                PreferredVehicle.valueOf(selectionStrategy.toUpperCase());
                ridesFilteredWithStrategy = allRides.stream().filter(extractedRide -> {
                        VehicleEntity vehicleEntity = RideHelper.getVehicleById(extractedRide.getVehicleId());
                        return vehicleEntity.getVehicleModel().equalsIgnoreCase(selectionStrategy);
                }).collect(Collectors.toList());
            } catch (Exception e) {
                throw new RideSharingException(e);
            }
        } else {
            return allRides;
        }
        return ridesFilteredWithStrategy;
    }
}
