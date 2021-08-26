package com.share.ride.ridesharing.controller.ride;

import com.share.ride.ridesharing.enums.ApiName;
import com.share.ride.ridesharing.enums.ErrorCode;
import com.share.ride.ridesharing.enums.ServiceStatus;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.model.Ride;
import com.share.ride.ridesharing.model.Rides;
import com.share.ride.ridesharing.model.ServiceRequest;
import com.share.ride.ridesharing.model.ServiceResponse;
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
    public ServiceResponse<Ride> offerRide(ServiceRequest<Ride> inputRide) {

        logger.info("Start RideController...offerRide");
        try {
            apiRequestValidator.validate(inputRide.getPayload(), ApiName.offerRideApi);
            if (inputRide.getPayload().getOrigin().equals(inputRide.getPayload().getDestination())) {
                throw new RideSharingException(ErrorCode.SOURCE_AND_DESTINATION_ARE_SAME);
            } else {
                Ride outputRide = rideService.offerRide(inputRide.getPayload());
                ServiceResponse<Ride> serviceResponse = new ServiceResponse<>();
                serviceResponse.setResult(outputRide);
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
    public ServiceResponse<Ride> selectRide(ServiceRequest<Ride> inputRide) {

        logger.info("Start RideController...selectRide");
        try {
            apiRequestValidator.validate(inputRide.getPayload(), ApiName.selectRideApi);
            if (inputRide.getPayload().getOrigin().equals(inputRide.getPayload().getDestination())) {
                throw new RideSharingException(ErrorCode.SOURCE_AND_DESTINATION_ARE_SAME);
            } else {
                Ride outputRide = rideService.selectRide(inputRide.getPayload());
                ServiceResponse<Ride> serviceResponse = new ServiceResponse<>();
                serviceResponse.setResult(outputRide);
                serviceResponse.setStatus(ServiceStatus.SUCCESS);
                logger.info("End RideController...selectRide");
                return serviceResponse;
            }
        } catch (RideSharingException rex) {
            throw rex;
        } catch (Exception ex) {
            throw new RideSharingException(ex);
        }
    }

    @Override
    public ServiceResponse<Ride> endRide(ServiceRequest<Ride> inputRide) {

        logger.info("Start RideController...endRide");
        try {
            apiRequestValidator.validate(inputRide.getPayload(), ApiName.endRideApi);
            Ride outputRide = rideService.endRide(inputRide.getPayload());
            ServiceResponse<Ride> serviceResponse = new ServiceResponse<>();
            serviceResponse.setResult(outputRide);
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
    public ServiceResponse<Rides> searchRide(ServiceRequest<Ride> inputRide) {

        logger.info("Start RideController...searchRide");
        try {
            apiRequestValidator.validate(inputRide.getPayload(), ApiName.searchRideApi);
            if (inputRide.getPayload().getOrigin().equals(inputRide.getPayload().getDestination())) {
                throw new RideSharingException(ErrorCode.SOURCE_AND_DESTINATION_ARE_SAME);
            } else {
                List<Ride> rides = rideService.searchRide(inputRide.getPayload());
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
