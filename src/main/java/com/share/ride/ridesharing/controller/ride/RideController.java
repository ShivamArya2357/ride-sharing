package com.share.ride.ridesharing.controller.ride;

import com.share.ride.ridesharing.contract.Ride;
import com.share.ride.ridesharing.enums.ApiName;
import com.share.ride.ridesharing.enums.ErrorCode;
import com.share.ride.ridesharing.enums.ServiceStatus;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.contract.Rides;
import com.share.ride.ridesharing.contract.ServiceRequest;
import com.share.ride.ridesharing.contract.ServiceResponse;
import com.share.ride.ridesharing.service.ride.RideService;
import com.share.ride.ridesharing.validation.ApiRequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Service
public class RideController implements RideResources {

    private static final Logger logger = LoggerFactory.getLogger(RideController.class);

    @Autowired
    private ApiRequestValidator apiRequestValidator;

    @Autowired
    private RideService rideService;

    @Override
    public ServiceResponse<Ride> offerRide(ServiceRequest<Ride> input) {

        logger.info("Start RideController...offerRide");
        try {
            apiRequestValidator.validate(input.getPayload(), ApiName.offerRideApi);
            if (input.getPayload().getOrigin().equals(input.getPayload().getDestination())) {
                throw new RideSharingException(ErrorCode.SOURCE_AND_DESTINATION_ARE_SAME);
            } else {
                Ride output = rideService.offerRide(input.getPayload());
                ServiceResponse<Ride> serviceResponse = new ServiceResponse<>();
                serviceResponse.setResult(output);
                serviceResponse.setStatus(ServiceStatus.SUCCESS);
                logger.info("End RideController...offerRide");
                return serviceResponse;
            }
        } catch (RideSharingException rex) {
            throw rex;
        } catch (Exception ex) {
            throw new RideSharingException(ex);
        }
    }

    @Override
    public ServiceResponse<Ride> selectRide(ServiceRequest<Ride> input) {

        logger.info("Start RideController...selectRide");
        try {
            apiRequestValidator.validate(input.getPayload(), ApiName.selectRideApi);
            Ride output = rideService.selectRide(input.getPayload());
            ServiceResponse<Ride> serviceResponse = new ServiceResponse<>();
            serviceResponse.setResult(output);
            serviceResponse.setStatus(ServiceStatus.SUCCESS);
            logger.info("End RideController...selectRide");
            return serviceResponse;
        } catch (RideSharingException rex) {
            throw rex;
        } catch (Exception ex) {
            throw new RideSharingException(ex);
        }
    }

    @Override
    public ServiceResponse<Ride> endRide(ServiceRequest<Ride> input) {

        logger.info("Start RideController...endRide");
        try {
            apiRequestValidator.validate(input.getPayload(), ApiName.endRideApi);
            Ride output = rideService.endRide(input.getPayload());
            ServiceResponse<Ride> serviceResponse = new ServiceResponse<>();
            serviceResponse.setResult(output);
            serviceResponse.setStatus(ServiceStatus.SUCCESS);
            logger.info("End RideController...endRide");
            return serviceResponse;
        } catch (RideSharingException rex) {
            throw rex;
        } catch (Exception ex) {
            throw new RideSharingException(ex);
        }
    }

    @Override
    public ServiceResponse rideStatistics() {

        logger.info("Start RideController...rideStatistics");
        try {
            rideService.rideStatistics();
            ServiceResponse serviceResponse = new ServiceResponse();
            serviceResponse.setStatus(ServiceStatus.SUCCESS);
            logger.info("End RideController...rideStatistics");
            return serviceResponse;
        } catch (RideSharingException rex) {
            throw rex;
        } catch (Exception ex) {
            throw new RideSharingException(ex);
        }
    }

    @Override
    public ServiceResponse<Rides> searchRide(ServiceRequest<Ride> input) {

        logger.info("Start RideController...searchRide");
        try {
            apiRequestValidator.validate(input.getPayload(), ApiName.searchRideApi);
            if (input.getPayload().getOrigin().equals(input.getPayload().getDestination())) {
                throw new RideSharingException(ErrorCode.SOURCE_AND_DESTINATION_ARE_SAME);
            } else {
                List<Ride> rides = rideService.searchRide(input.getPayload());
                Rides outputRides = new Rides();
                outputRides.setRides(rides);
                ServiceResponse<Rides> serviceResponse = new ServiceResponse<>();
                serviceResponse.setResult(outputRides);
                serviceResponse.setStatus(ServiceStatus.SUCCESS);
                logger.info("End RideController...searchRide");
                return serviceResponse;
            }
        } catch (RideSharingException rex) {
            throw rex;
        } catch (Exception ex) {
            throw new RideSharingException(ex);
        }
    }
}
