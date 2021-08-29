package com.share.ride.ridesharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.share.ride.ridesharing.enums.VehicleStatus;

import static com.share.ride.ridesharing.enums.VehicleStatus.READY;

@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class VehicleEntity extends BaseModel {

    @JsonProperty("vehicleModel")
    private String vehicleModel;

    @JsonProperty("vehicleNo")
    private String vehicleNo;

    @JsonProperty("vehicleStatus")
    private VehicleStatus vehicleStatus = READY;

    @JsonProperty("userId")
    private String userId;

    public VehicleEntity() {
    }

    public VehicleEntity(String vehicleModel, String vehicleNo) {

        this.vehicleModel = vehicleModel;
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
