package com.share.ride.ridesharing.enums;

public enum ErrorCode {

    // 500 errors
    INTERNAL_EXCEPTION("500"),
    REQUEST_SCHEMA_PROCESSING_FAILED("500.01"),

    // HTTP errors
    REQUEST_FORBIDDEN("403"),

    // 400 errors
    NOT_A_VALID_RIDE("400.01"),
    NO_RIDE_FOUND("400.02"),
    REQUEST_VALIDATION_FAILED("400.03"),
    REQUEST_VALIDATOR_NOT_FOUND("400.04"),
    USER_NOT_FOUND("400.05"),
    SOURCE_AND_DESTINATION_ARE_SAME("400.06"),
    RIDE_IS_ALREADY_OFFERED("400.07"),
    VEHICLE_IS_ALREADY_REGISTERED("400.08"),
    USER_ALREADY_EXIST("400.09"),
    VEHICLE_NOT_FOUND("400.10")
    ;

    private String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
