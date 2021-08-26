package com.share.ride.ridesharing.helper;

import com.share.ride.ridesharing.enums.ErrorCode;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.model.User;

import java.util.*;

public class RideHelper {

    private static final Map<String, User> userMap = new HashMap<>();

    /**
     * This method will create userMap
     *
     * @param user
     */
    public static void createUserMap(User user) {
        userMap.put(user.getUserName().toLowerCase(), user);
    }

    /**
     * This method will return user
     *
     * @param userName
     * @return user
     */
    public static User getUserByName(String userName) {

        userName = userName.toLowerCase();
        if (userMap.containsKey(userName)) {
            return userMap.get(userName);
        } else {
            throw new RideSharingException(ErrorCode.USER_NOT_FOUND);
        }
    }

    /**
     * This method will return all the users
     *
     * @return users
     */
    public static List<User> getUsers() {

        return new ArrayList<>(userMap.values());
    }
}
