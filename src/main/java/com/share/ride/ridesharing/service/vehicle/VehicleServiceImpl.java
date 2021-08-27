package com.share.ride.ridesharing.service.vehicle;

import com.share.ride.ridesharing.contract.Vehicle;
import com.share.ride.ridesharing.enums.ErrorCode;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.entity.VehicleEntity;
import com.share.ride.ridesharing.helper.RideHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class VehicleServiceImpl implements VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    private static final List<VehicleEntity> vehicles = new ArrayList<>();
    private static final Set<String> existingVehicles = new HashSet<>();

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
        vehicleEntity.setVehicleOwner(input.getVehicleOwner());
        vehicleEntity.setVehicleModel(input.getVehicleModel());
        vehicleEntity.setVehicleNo(input.getVehicleNo());
        vehicleEntity.setUserId(input.getUserId());
        RideHelper.createVehicleMap(vehicleEntity);
        input.setId(vehicleEntity.getId());
        existingVehicles.add(input.getVehicleNo());
        vehicles.add(vehicleEntity);
    }

    /**
     * This method will check If a vehicle is already offered for ride
     *
     * @param vehicleNo
     * @return true or false
     */
    private boolean isExistingVehicle(String vehicleNo) {

        if (existingVehicles.contains(vehicleNo)) {
            return true;
        } else {
            return false;
        }
    }
}
