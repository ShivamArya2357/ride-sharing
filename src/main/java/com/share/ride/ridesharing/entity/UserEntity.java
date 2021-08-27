package com.share.ride.ridesharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserEntity extends BaseModel {

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("mobileNo")
    private String mobileNo;

    @JsonProperty("offeredRide")
    private Integer offeredRide = 0;

    @JsonProperty("takenRide")
    private Integer takenRide = 0;

    @JsonProperty("rideId")
    private String rideId;

    public UserEntity() {
    }

    public UserEntity(String userName, String gender, Integer age) {

        this.userName = userName;
        this.gender = gender;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getOfferedRide() {
        return offeredRide;
    }

    public void setOfferedRide(Integer offeredRide) {
        this.offeredRide = offeredRide;
    }

    public Integer getTakenRide() {
        return takenRide;
    }

    public void setTakenRide(Integer takenRide) {
        this.takenRide = takenRide;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }
}
