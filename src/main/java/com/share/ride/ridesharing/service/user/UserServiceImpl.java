package com.share.ride.ridesharing.service.user;

import com.share.ride.ridesharing.helper.RideHelper;
import com.share.ride.ridesharing.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final List<User> users = new ArrayList<>();

    /**
     * This method will add user details
     *
     * @param user
     */
    public User addUser(User user) {

        logger.info("Inside UserServiceImpl...addUser()");
        generateUserId(user);
        users.add(user);
        RideHelper.createUserMap(user);
        return user;
    }

    /**
     * This method will generate userId
     *
     * @param user
     */
    private void generateUserId(User user) {

        user.setId(String.valueOf(ThreadLocalRandom.current().nextInt(10000)));
    }
}
