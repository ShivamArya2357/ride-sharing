package com.share.ride.ridesharing.controller.vehicle;

import com.share.ride.ridesharing.contract.Vehicle;
import com.share.ride.ridesharing.enums.ApiName;
import com.share.ride.ridesharing.enums.ServiceStatus;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.contract.ServiceRequest;
import com.share.ride.ridesharing.contract.ServiceResponse;
import com.share.ride.ridesharing.entity.VehicleEntity;
import com.share.ride.ridesharing.service.vehicle.VehicleService;
import com.share.ride.ridesharing.validation.ApiRequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
public class VehicleController implements VehicleResources {

    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private ApiRequestValidator apiRequestValidator;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public ServiceResponse<Vehicle> addVehicle(ServiceRequest<Vehicle> input) {

        logger.info("Start VehicleController...addVehicle");
        try {
            apiRequestValidator.validate(input.getPayload(), ApiName.addVehicleApi);
            Vehicle output = vehicleService.addVehicle(input.getPayload());
            ServiceResponse<Vehicle> serviceResponse = new ServiceResponse<>();
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
