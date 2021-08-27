package com.share.ride.ridesharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class VehicleEntity extends BaseModel {

    @JsonProperty("vehicleOwner")
    private String vehicleOwner;

    @JsonProperty("vehicleModel")
    private String vehicleModel;

    @JsonProperty("vehicleNo")
    private String vehicleNo;

    @JsonProperty("capacity")
    private Integer capacity;

    @JsonProperty("userId")
    private String userId;

    public VehicleEntity() {
    }

    public VehicleEntity(String vehicleOwner, String vehicleModel, String vehicleNo) {

        this.vehicleOwner = vehicleOwner;
        this.vehicleModel = vehicleModel;
        this.vehicleNo = vehicleNo;
    }

    public VehicleEntity(String vehicleModel, String vehicleNo) {

        this.vehicleModel = vehicleModel;
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(String vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
