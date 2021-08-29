package com.share.ride.ridesharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.share.ride.ridesharing.enums.RideStatus;

import static com.share.ride.ridesharing.enums.RideStatus.ENDED;

@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class OfferedRideEntity extends BaseModel {

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("availableSeats")
    private Integer availableSeats;

    @JsonProperty("status")
    private RideStatus rideStatus = ENDED;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("vehicleId")
    private String vehicleId;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public RideStatus getStatus() {
        return rideStatus;
    }

    public void setStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
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
}
