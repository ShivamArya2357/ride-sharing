package com.share.ride.ridesharing.controller.ride;

import com.share.ride.ridesharing.model.Ride;
import com.share.ride.ridesharing.model.Rides;
import com.share.ride.ridesharing.model.ServiceRequest;
import com.share.ride.ridesharing.model.ServiceResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/rides")
public interface RideResources {

    @ResponseBody
    @PostMapping(path = "/offer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ServiceResponse<Ride> offerRide(@RequestBody ServiceRequest<Ride> inputRide);

    @ResponseBody
    @PostMapping(path = "/select", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ServiceResponse<Ride> selectRide(@RequestBody ServiceRequest<Ride> inputRide);

    @ResponseBody
    @PostMapping(path = "/end", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ServiceResponse<Ride> endRide(@RequestBody ServiceRequest<Ride> inputRide);

    @ResponseBody
    @GetMapping(path = "/statistic")
    ServiceResponse rideStatistics();

    @ResponseBody
    @GetMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ServiceResponse<Rides> searchRide(@RequestBody ServiceRequest<Ride> inputRide);
}
