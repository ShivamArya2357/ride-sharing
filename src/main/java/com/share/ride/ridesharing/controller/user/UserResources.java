package com.share.ride.ridesharing.controller.user;

import com.share.ride.ridesharing.model.ServiceRequest;
import com.share.ride.ridesharing.model.ServiceResponse;
import com.share.ride.ridesharing.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/v1/users")
public interface UserResources {

    @ResponseBody
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ServiceResponse<User> addUser(@RequestBody ServiceRequest<User> inputUser);
}
