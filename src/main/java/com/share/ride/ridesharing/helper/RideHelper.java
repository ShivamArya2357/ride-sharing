package com.share.ride.ridesharing.helper;

import com.share.ride.ridesharing.entity.VehicleEntity;
import com.share.ride.ridesharing.enums.ErrorCode;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.entity.UserEntity;

import java.util.*;

public class RideHelper {

    private static final Map<String, UserEntity> userMap = new HashMap<>();
    private static final Map<String, VehicleEntity> vehicleMap = new HashMap<>();

    /**
     * This method will create userMap
     *
     * @param userEntity
     */
    public static void createUserMap(UserEntity userEntity) {
        userMap.put(userEntity.getId(), userEntity);
    }

    /**
     * This method will create vehicleMap
     *
     * @param vehicleEntity
     */
    public static void createVehicleMap(VehicleEntity vehicleEntity) {
        vehicleMap.put(vehicleEntity.getId(), vehicleEntity);
    }

    /**
     * This method will return user by id
     *
     * @param userId
     * @return user
     */
    public static UserEntity getUserById(String userId) {

        if (userMap.containsKey(userId)) {
            return userMap.get(userId);
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

        if (vehicleMap.containsKey(vehicleId)) {
            return vehicleMap.get(vehicleId);
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

        return new ArrayList<>(userMap.values());
    }

    /**
     * This method will return all the users
     *
     * @return users
     */
    public static List<VehicleEntity> getAllVehicles() {

        return new ArrayList<>(vehicleMap.values());
    }
}
