package com.share.ride.ridesharing.enums;

public enum SelectionStrategy {

    MOST_VACANT("Most Vacant"),
    ACTIVA("Activa"),
    POLO("Polo"),
    XUV("XUV");

    private String description;

    SelectionStrategy(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
