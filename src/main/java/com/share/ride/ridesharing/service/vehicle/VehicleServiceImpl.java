package com.share.ride.ridesharing.service.vehicle;

import com.share.ride.ridesharing.helper.RideHelper;
import com.share.ride.ridesharing.model.User;
import com.share.ride.ridesharing.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class VehicleServiceImpl implements VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    private static final List<Vehicle> vehicles = new ArrayList<>();

    /**
     * This method will be used to add vehicle
     *
     * @param vehicle
     */
    public Vehicle addVehicle(Vehicle vehicle) {

        logger.info("Inside VehicleServiceImpl...addVehicle()");
        generateVehicleId(vehicle);
        mapVehicleToUser(vehicle);
        vehicles.add(vehicle);
        return vehicle;
    }

    /**
     * This method will generate vehicle id
     *
     * @param vehicle
     */
    private void generateVehicleId(Vehicle vehicle) {

        vehicle.setId(String.valueOf(ThreadLocalRandom.current().nextInt(10000)));
    }

    /**
     * This method will map vehicle to user
     *
     * @param vehicle
     */
    private void mapVehicleToUser(Vehicle vehicle) {

        User user = RideHelper.getUserByName(vehicle.getVehicleOwner());
        vehicle.setUserId(user.getId());
    }
}
