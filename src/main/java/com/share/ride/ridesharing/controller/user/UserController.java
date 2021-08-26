package com.share.ride.ridesharing.controller.user;

import com.share.ride.ridesharing.enums.ApiName;
import com.share.ride.ridesharing.enums.ServiceStatus;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.model.ServiceRequest;
import com.share.ride.ridesharing.model.ServiceResponse;
import com.share.ride.ridesharing.model.User;
import com.share.ride.ridesharing.service.user.UserService;
import com.share.ride.ridesharing.validation.ApiRequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
public class UserController implements UserResources {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ApiRequestValidator apiRequestValidator;

    @Autowired
    private UserService userService;

    @Override
    public ServiceResponse<User> addUser(ServiceRequest<User> inputUser) {

        logger.info("Start UserController...addUser");
        try {
            apiRequestValidator.validate(inputUser.getPayload(), ApiName.addUserApi);
            User output = userService.addUser(inputUser.getPayload());
            ServiceResponse<User> serviceResponse = new ServiceResponse<>();
            serviceResponse.setResult(output);
            serviceResponse.setStatus(ServiceStatus.SUCCESS);
            logger.info("End UserController...addUser");
            return serviceResponse;
        } catch (RideSharingException rex) {
            throw rex;
        } catch (Exception ex) {
            throw new RideSharingException(ex);
        }
    }
}
