package com.share.ride.ridesharing.resources.vehicle;

import com.share.ride.ridesharing.contract.ServiceRequest;
import com.share.ride.ridesharing.contract.ServiceResponse;
import com.share.ride.ridesharing.contract.Vehicle;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/v1/vehicles")
public interface VehicleResources {

    @ResponseBody
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ServiceResponse<Vehicle> addVehicle(@RequestBody ServiceRequest<Vehicle> inputVehicle);
}
