package com.share.ride.ridesharing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.share.ride.ridesharing.enums.Status;

import java.util.List;

import static com.share.ride.ridesharing.enums.Status.CLOSED;

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

    @JsonProperty("availableSeats")
    private Integer availableSeats;

    @JsonProperty("vehicle")
    private Vehicle vehicle;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("status")
    private Status status = CLOSED;

    @JsonProperty("selectionStrategy")
    private String selectionStrategy;

    public Ride() {
    }

    public Ride(String offeredBy, String origin, Integer availableSeats, Vehicle vehicle, String destination) {

        this.offeredBy = offeredBy;
        this.origin = origin;
        this.availableSeats = availableSeats;
        this.vehicle = vehicle;
        this.destination = destination;
    }

    public Ride(String offeredBy, String origin, String destination, Integer availableSeats, String selectionStrategy) {

        this.offeredBy = offeredBy;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.selectionStrategy = selectionStrategy;
    }

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

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSelectionStrategy() {
        return selectionStrategy;
    }

    public void setSelectionStrategy(String selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }
}
