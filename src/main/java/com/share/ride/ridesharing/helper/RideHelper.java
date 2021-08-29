package com.share.ride.ridesharing.helper;

import com.share.ride.ridesharing.enums.ErrorCode;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.entity.UserEntity;
import com.share.ride.ridesharing.entity.VehicleEntity;

import java.util.*;

public class RideHelper {

    private static final Map<String, UserEntity> userMapById = new HashMap<>();
    private static final Map<String, VehicleEntity> vehicleMapById = new HashMap<>();

    /**
     * This method will create userMap
     *
     * @param userEntity
     */
    public static void createUserMap(UserEntity userEntity) {
        userMapById.put(userEntity.getId(), userEntity);
    }

    /**
     * This method will create vehicleMap
     *
     * @param vehicleEntity
     */
    public static void createVehicleMap(VehicleEntity vehicleEntity) {
        vehicleMapById.put(vehicleEntity.getId(), vehicleEntity);
    }

    /**
     * This method will return user by id
     *
     * @param userId
     * @return user
     */
    public static UserEntity getUserById(String userId) {

        if (userMapById.containsKey(userId)) {
            return userMapById.get(userId);
        } else {
            throw new RideSharingException(ErrorCode.USER_NOT_FOUND);
        }
    }

    /**
     * This method will return vehicle by id
     *
     * @param vehicleId
     * @return user
     */
    public static VehicleEntity getVehicleById(String vehicleId) {

        if (vehicleMapById.containsKey(vehicleId)) {
            return vehicleMapById.get(vehicleId);
        } else {
            throw new RideSharingException(ErrorCode.VEHICLE_NOT_FOUND);
        }
    }

    /**
     * This method will return all the users
     *
     * @return users
     */
    public static List<UserEntity> getAllUsers() {

        return new ArrayList<>(userMapById.values());
    }

    /**
     * This method will return all the users
     *
     * @return users
     */
    public static List<VehicleEntity> getAllVehicles() {

        return new ArrayList<>(vehicleMapById.values());
    }
}
