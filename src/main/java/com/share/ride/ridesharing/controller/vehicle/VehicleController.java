package com.share.ride.ridesharing.controller.vehicle;

import com.share.ride.ridesharing.enums.ApiName;
import com.share.ride.ridesharing.enums.ServiceStatus;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.model.ServiceRequest;
import com.share.ride.ridesharing.model.ServiceResponse;
import com.share.ride.ridesharing.model.Vehicle;
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
    public ServiceResponse<Vehicle> addVehicle(ServiceRequest<Vehicle> inputVehicle) {

        logger.info("Start VehicleController...addVehicle");
        try {
            apiRequestValidator.validate(inputVehicle.getPayload(), ApiName.addVehicleApi);
            Vehicle output = vehicleService.addVehicle(inputVehicle.getPayload());
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
