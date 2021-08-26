package com.share.ride.ridesharing.service.ride;

import com.share.ride.ridesharing.model.Ride;

import java.util.List;

public interface RideService {

    Ride offerRide(Ride ride);

    Ride selectRide(Ride ride);

    Ride endRide(Ride ride);

    void rideStatistics();

    List<Ride> searchRide(Ride ride);
}
