package com.share.ride.ridesharing.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.share.ride.ridesharing.contract.BaseModel;
import com.share.ride.ridesharing.contract.User;
import com.share.ride.ridesharing.contract.Vehicle;
import com.share.ride.ridesharing.enums.Status;

import static com.share.ride.ridesharing.enums.Status.ENDED;

@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Ride extends BaseModel {

    @JsonProperty("offeredBy")
    private String offeredBy;

    @JsonProperty("requestedBy")
    private String requestedBy;

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("intermediateStop")
    private String intermediateStop;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("availableSeats")
    private Integer availableSeats;

    @JsonProperty("vehicleModel")
    private String vehicleModel;

    @JsonProperty("vehicleNo")
    private String vehicleNo;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("vehicleId")
    private String vehicleId;

    @JsonProperty("selectionStrategy")
    private String selectionStrategy;

    public String getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(String offeredBy) {
        this.offeredBy = offeredBy;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getIntermediateStop() {
        return intermediateStop;
    }

    public void setIntermediateStop(String intermediateStop) {
        this.intermediateStop = intermediateStop;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getSelectionStrategy() {
        return selectionStrategy;
    }

    public void setSelectionStrategy(String selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }
}

