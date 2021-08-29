package com.share.ride.ridesharing.service.ride;

import com.share.ride.ridesharing.contract.Ride;
import com.share.ride.ridesharing.enums.ErrorCode;
import com.share.ride.ridesharing.enums.PreferredVehicle;
import com.share.ride.ridesharing.enums.VehicleStatus;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.helper.RideHelper;
import com.share.ride.ridesharing.entity.OfferedRideEntity;
import com.share.ride.ridesharing.entity.TakenRideEntity;
import com.share.ride.ridesharing.entity.UserEntity;
import com.share.ride.ridesharing.entity.VehicleEntity;
import com.share.ride.ridesharing.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.share.ride.ridesharing.enums.RideStatus.*;

@Component
public class RideServiceImpl implements RideService {

    private static final Logger logger = LoggerFactory.getLogger(RideServiceImpl.class);

    private static final String MOST_VACANT = "Most Vacant";

    private static final List<OfferedRideEntity> offeredRides = new ArrayList<>();
    private static final List<TakenRideEntity> takenRides = new ArrayList<>();

    /**
     * Using this method user can offer a ride
     *
     * @param input
     */
    @Override
    public Ride offerRide(Ride input) {

        logger.info("Inside RideServiceImpl...offerRide()");
        VehicleEntity vehicleEntity = RideHelper.getVehicleById(input.getVehicleId());
        if (VehicleStatus.READY.equals(vehicleEntity.getVehicleStatus())) {
            vehicleEntity.setVehicleStatus(VehicleStatus.IN_PROGRESS);
            mapToRideEntity(input);
            return input;
        } else {
            throw new RideSharingException(ErrorCode.VEHICLE_IS_NOT_FREE_FOR_RIDE);
        }
    }

    /**
     * This method will map offeredRide to offeredUser
     *
     * @param input
     */
    private void mapToRideEntity(Ride input) {

        OfferedRideEntity output = new OfferedRideEntity();
        output.setId(String.valueOf(ThreadLocalRandom.current().nextInt(10000)));
        output.setStatus(OFFERED);
        output.setOrigin(input.getOrigin());
        output.setDestination(input.getDestination());
        output.setAvailableSeats(input.getAvailableSeats());
        UserEntity userEntity = RideHelper.getUserById(input.getUserId());
        output.setUserId(userEntity.getId());
        output.setVehicleId(input.getVehicleId());
        input.setId(output.getId());
        offeredRides.add(output);
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
        Optional<OfferedRideEntity> offeredRideEntityOpt = offeredRides.stream().filter(rideEntity ->
                rideEntity.getId().equals(input.getId())
        ).findFirst();
        if (offeredRideEntityOpt.isPresent()) {
            OfferedRideEntity offeredRideEntity = offeredRideEntityOpt.get();
            if (offeredRideEntity.getUserId().equals(input.getUserId())) {
                throw new RideSharingException(ErrorCode.RIDES_PRODUCER_AND_CONSUMER_ARE_SAME);
            } else {
                createTakenRideEntity(input);
                updateOfferedRideEntity(offeredRideEntity, input);
                return input;
            }
        } else {
            throw new RideSharingException(ErrorCode.NO_RIDE_FOUND);
        }
    }

    /**
     * This method will update the status and available seats in a ride
     *
     * @param offeredRideEntity
     */
    private void updateOfferedRideEntity(OfferedRideEntity offeredRideEntity, Ride input) {

        offeredRideEntity.setAvailableSeats(offeredRideEntity.getAvailableSeats() - input.getAvailableSeats());
        offeredRideEntity.setStatus(IN_PROGRESS);
    }

    /**
     * This method will create taken ride entity
     *
     * @param input
     */
    private void createTakenRideEntity(Ride input) {

        TakenRideEntity takenRideEntity = new TakenRideEntity();
        takenRideEntity.setId(String.valueOf(ThreadLocalRandom.current().nextInt(10000)));
        takenRideEntity.setUserId(input.getUserId());
        takenRideEntity.setRideId(input.getId());
        takenRides.add(takenRideEntity);
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
        Optional<OfferedRideEntity> offeredRideOpt = offeredRides.stream().filter(
                ride -> ride.getId().equals(input.getId())
        ).findFirst();
        if (offeredRideOpt.isPresent()) {
            OfferedRideEntity offeredRideEntity = offeredRideOpt.get();
            VehicleEntity vehicleEntity = RideHelper.getVehicleById(offeredRideEntity.getVehicleId());
            vehicleEntity.setVehicleStatus(VehicleStatus.READY);
            offeredRideEntity.setStatus(ENDED);
            return input;
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
        List<UserEntity> users = RideHelper.getAllUsers();
        for (UserEntity user : users) {
            int takenRide = findRidesTakenByUser(user.getId());
            int offeredRide = findRidesOfferedByUser(user.getId());
            System.out.println(user.getUserName() + ": " + takenRide + " Taken, " + offeredRide
                    + " Offered"
            );
        }
    }

    /**
     * This method will return all the rides that were taken by a user
     *
     * @param userId
     * @return takenRides
     */
    private int findRidesTakenByUser(String userId) {

        return (int) takenRides.stream().filter(ride -> ride.getUserId().equals(userId)).count();
    }

    /**
     * This method will return all the rides that were offered by a user
     *
     * @param userId
     * @return offeredRides
     */
    private int findRidesOfferedByUser(String userId) {

        return (int) offeredRides.stream().filter(ride -> ride.getUserId().equals(userId)).count();
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
        if (!offeredRides.isEmpty()) {
            List<OfferedRideEntity> allRides = offeredRides.stream().filter(ride -> !ENDED.equals(ride.getStatus())
                                                    && ride.getAvailableSeats() > 0
                                                    && ride.getOrigin().equalsIgnoreCase(input.getOrigin())
                                                    && ride.getDestination().equalsIgnoreCase(input.getDestination())
                                                    && ride.getAvailableSeats() >= input.getAvailableSeats()
            ).collect(Collectors.toList());
            List<OfferedRideEntity> ridesFilteredWithStrategy;
            if (!allRides.isEmpty()) {
                ridesFilteredWithStrategy = searchRideWithStrategy(input, allRides);
            } else {
                ridesFilteredWithStrategy = searchIntermediateRide(input); // find intermediate rides
            }
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
    private List<Ride> mapToRideModel(List<OfferedRideEntity> rideEntities) {

        List<Ride> rides = new ArrayList<>();
        for (OfferedRideEntity rideEntity : rideEntities) {
            Ride ride = new Ride();
            ride.setId(rideEntity.getId());
            ride.setOrigin(rideEntity.getOrigin());
            ride.setDestination(rideEntity.getDestination());
            ride.setAvailableSeats(rideEntity.getAvailableSeats());
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
    private List<OfferedRideEntity> searchIntermediateRide(Ride input) {

        List<OfferedRideEntity> allRidesFromOrigin = offeredRides.stream().filter(ride -> !ENDED.equals(ride.getStatus())
                                                        && ride.getAvailableSeats() > 0
                                                        && ride.getOrigin().equalsIgnoreCase(input.getOrigin())
                                                        && !ride.getDestination().equalsIgnoreCase(input.getDestination())
                                                        && ride.getAvailableSeats() >= input.getAvailableSeats()
        ).collect(Collectors.toList());
        List<OfferedRideEntity> allRidesEndInDest = offeredRides.stream().filter(ride -> !ENDED.equals(ride.getStatus())
                                                        && ride.getAvailableSeats() > 0
                                                        && !ride.getOrigin().equalsIgnoreCase(input.getOrigin())
                                                        && ride.getDestination().equalsIgnoreCase(input.getDestination())
                                                        && ride.getAvailableSeats() >= input.getAvailableSeats()
        ).collect(Collectors.toList());
        List<OfferedRideEntity> allRidesWithIntermediateStop = new ArrayList<>();
        Map<OfferedRideEntity, OfferedRideEntity> rideWithStopMap = new HashMap<>();
        if (!allRidesFromOrigin.isEmpty() && !allRidesEndInDest.isEmpty()) {
            for (OfferedRideEntity ridesFromOrigin : allRidesFromOrigin) {
                String destination = ridesFromOrigin.getDestination();
                for (OfferedRideEntity ridesEndInDest : allRidesEndInDest) {
                    String origin = ridesEndInDest.getOrigin();
                    if (destination.equals(origin) && !ridesFromOrigin.getUserId().equals(input.getUserId())
                            && !ridesEndInDest.getUserId().equals(input.getUserId())
                    ) {
                        allRidesWithIntermediateStop.add(ridesFromOrigin);
                        allRidesWithIntermediateStop.add(ridesEndInDest);
                        rideWithStopMap.put(ridesFromOrigin, ridesEndInDest);
                    }
                }
            }
        }
        if (!allRidesWithIntermediateStop.isEmpty()) {
            return searchIntermediateRideWithStrategy(input, rideWithStopMap, allRidesWithIntermediateStop);
        } else {
            throw new RideSharingException(ErrorCode.NO_RIDE_FOUND);
        }
    }

    /**
     * This method will filter out indirect rides with given strategy
     *
     * @param input
     * @param rideWithStopMap
     * @param allRidesWithIntermediateStop
     * @return List<RideEntity>
     */
    public List<OfferedRideEntity> searchIntermediateRideWithStrategy(Ride input, Map<OfferedRideEntity, OfferedRideEntity> rideWithStopMap,
                                                               List<OfferedRideEntity> allRidesWithIntermediateStop
    ) {

        List<OfferedRideEntity> ridesFilteredWithStrategy = new ArrayList<>();
        String selectionStrategy = input.getSelectionStrategy();
        Map<Integer, List<OfferedRideEntity> > availableSeatsToRideMap = new HashMap<>();
        if (StringUtils.isNotEmpty(selectionStrategy) && MOST_VACANT.equalsIgnoreCase(selectionStrategy)) {
            int maxAvailableSeats = 0;
            for (Map.Entry<OfferedRideEntity, OfferedRideEntity> entry : rideWithStopMap.entrySet()) {
                OfferedRideEntity ridesFromOrigin = entry.getKey();
                OfferedRideEntity ridesEndInDest = entry.getValue();
                int availableSeats = ridesFromOrigin.getAvailableSeats();
                maxAvailableSeats = Math.max(maxAvailableSeats, availableSeats);
                List<OfferedRideEntity> temp;
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
            try {
                PreferredVehicle.valueOf(selectionStrategy.toUpperCase());
                for (Map.Entry<OfferedRideEntity, OfferedRideEntity> entry : rideWithStopMap.entrySet()) {
                    OfferedRideEntity ridesFromOrigin = entry.getKey();
                    OfferedRideEntity ridesEndInDest = entry.getValue();
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
                e.printStackTrace();
            }
        }
        if (!ridesFilteredWithStrategy.isEmpty()) {
            return ridesFilteredWithStrategy;
        } else {
            return allRidesWithIntermediateStop;
        }
    }

    /**
     * This method will select rides based on selected strategy
     *
     * @param input
     * @param allRides
     * @return List<Ride>
     */
    public List<OfferedRideEntity> searchRideWithStrategy(Ride input, List<OfferedRideEntity> allRides) {

        Map<Integer, List<OfferedRideEntity> > availableSeatsToRideMap = new HashMap<>();
        List<OfferedRideEntity> ridesFilteredWithStrategy = new ArrayList<>();
        String selectionStrategy = input.getSelectionStrategy();
        if (StringUtils.isNotEmpty(selectionStrategy) && MOST_VACANT.equalsIgnoreCase(selectionStrategy)) {
            int maxAvailableSeats = 0;
            for (OfferedRideEntity ride : allRides) {
                Integer availableSeats = ride.getAvailableSeats();
                maxAvailableSeats = Math.max(maxAvailableSeats, availableSeats);
                List<OfferedRideEntity> temp;
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
                e.printStackTrace();
            }
        }
        if (!ridesFilteredWithStrategy.isEmpty()) {
            return ridesFilteredWithStrategy;
        } else {
            return allRides;
        }
    }
}
