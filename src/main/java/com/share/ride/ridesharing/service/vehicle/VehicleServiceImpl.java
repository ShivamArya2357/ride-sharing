package com.share.ride.ridesharing.service.vehicle;

import com.share.ride.ridesharing.contract.Vehicle;
import com.share.ride.ridesharing.enums.ErrorCode;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.helper.RideHelper;
import com.share.ride.ridesharing.entity.VehicleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class VehicleServiceImpl implements VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    private final List<VehicleEntity> vehicles = new ArrayList<>();

    @Autowired
    private RideHelper rideHelper;

    /**
     * This method will be used to add input
     *
     * @param input
     */
    public Vehicle addVehicle(Vehicle input) {

        logger.info("Inside VehicleServiceImpl...addVehicle()");
        String vehicleNo = input.getVehicleNo();
        if (!isExistingVehicle(vehicleNo)) {
            mapToVehicleEntity(input);
            return input;
        } else {
            throw new RideSharingException(ErrorCode.VEHICLE_IS_ALREADY_REGISTERED, vehicleNo);
        }
    }

    /**
     * This method will map pojo to database entity
     *
     * @param input
     */
    private void mapToVehicleEntity(Vehicle input) {

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setId(String.valueOf(ThreadLocalRandom.current().nextInt(10000)));
        vehicleEntity.setVehicleModel(input.getVehicleModel());
        vehicleEntity.setVehicleNo(input.getVehicleNo());
        vehicleEntity.setUserId(input.getUserId());
        rideHelper.createVehicleMap(vehicleEntity);
        input.setId(vehicleEntity.getId());
        vehicles.add(vehicleEntity);
    }

    /**
     * This method will check If a vehicle already exist
     *
     * @param vehicleNo
     * @return true or false
     */
    private boolean isExistingVehicle(String vehicleNo) {

        Optional<VehicleEntity> vehicleEntityOpt = vehicles.stream().filter(
                vehicleEntity -> vehicleEntity.getVehicleNo().equals(vehicleNo)
        ).findFirst();
        return vehicleEntityOpt.isPresent();
    }
}
