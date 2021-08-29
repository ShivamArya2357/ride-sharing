package com.share.ride.ridesharing.service.user;

import com.share.ride.ridesharing.contract.User;
import com.share.ride.ridesharing.enums.ErrorCode;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.helper.RideHelper;
import com.share.ride.ridesharing.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final List<UserEntity> users = new ArrayList<>();

    /**
     * This method will add input details
     *
     * @param input
     */
    public User addUser(User input) {

        logger.info("Inside UserServiceImpl...addUser()");
        String mobileNo = input.getMobileNo();
        if (!isExistingUser(mobileNo)) {
            mapToUserEntity(input);
            return input;
        } else {
            throw new RideSharingException(ErrorCode.USER_ALREADY_EXIST, mobileNo);
        }
    }

    /**
     * This method will map pojo to database entity
     *
     * @param input
     */
    private void mapToUserEntity(User input) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(String.valueOf(ThreadLocalRandom.current().nextInt(10000)));
        userEntity.setUserName(input.getUserName());
        userEntity.setGender(input.getGender());
        userEntity.setAge(input.getAge());
        userEntity.setMobileNo(input.getMobileNo());
        RideHelper.createUserMap(userEntity);
        input.setId(userEntity.getId());
        users.add(userEntity);
    }

    /**
     * This method will check If user is already registered with given mobile no
     *
     * @param mobileNo
     * @return true or false
     */
    private boolean isExistingUser(String mobileNo) {

        Optional<UserEntity> userEntityOpt = users.stream().filter(
                user -> user.getMobileNo().equals(mobileNo)
        ).findFirst();
        return userEntityOpt.isPresent();
    }
}
