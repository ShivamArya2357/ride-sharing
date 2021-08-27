package com.share.ride.ridesharing;

import com.share.ride.ridesharing.contract.*;
import com.share.ride.ridesharing.controller.ride.RideController;
import com.share.ride.ridesharing.controller.user.UserController;
import com.share.ride.ridesharing.controller.vehicle.VehicleController;
import com.share.ride.ridesharing.exception.RideSharingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RideSharingApplicationTests {

	@Autowired
	private VehicleController vehicleController;

	@Autowired
	private UserController userController;

	@Autowired
	private RideController rideController;

	@Test
	public void tc1() {

		// add user1 and vehicle and offer rides
		ServiceRequest<User> user1 = createUserReq("Rohan", "M", 36, "9648643784");
		ServiceRequest<Vehicle> vehicle1 = createVehicleReq("Rohan","Swift",
				"KA-01-12345"
		);
		ServiceRequest<Vehicle> vehicle2 = createVehicleReq("Rohan","Activa",
				"KA-01-12346"
		);
		userController.addUser(user1);
		vehicleController.addVehicle(vehicle1);
		vehicleController.addVehicle(vehicle2);
        rideController.offerRide(createOfferRideReq(user1.getPayload(), "Kanpur",
                1, "Lucknow", vehicle1.getPayload())
		);
        rideController.offerRide(createOfferRideReq(user1.getPayload(), "Kanpur",
                3, "Lucknow", vehicle2.getPayload())
		);

		// add user2 and search for ride
        ServiceRequest<User> user2 = createUserReq("Nandini", "F", 26, "9648643786");
		userController.addUser(user2);
		ServiceResponse<Rides> output = rideController.searchRide(createSearchRideReq(user2.getPayload(),
                "Kanpur", "Lucknow", 1, "Most Vacant")
		);
		assertEquals("SUCCESS", output.getStatus().name());
		assertEquals(1, output.getResult().getRides().size());

		// select ride 1
        rideController.selectRide(createSelectRideReq(output.getResult().getRides().get(0), user2.getPayload()));

		// print ride statistics
		rideController.rideStatistics();
	}

    @Test
	public void tc2() {

		// add user1 and vehicle and offer rides
		ServiceRequest<User> user1 = createUserReq("Rohan", "M", 36, "9648643784");
		ServiceRequest<Vehicle> vehicle1 = createVehicleReq("Rohan","Swift",
				"KA-01-12345"
		);
		ServiceRequest<Vehicle> vehicle2 = createVehicleReq("Rohan","Activa",
				"KA-01-12346"
		);
		userController.addUser(user1);
		vehicleController.addVehicle(vehicle1);
		vehicleController.addVehicle(vehicle2);
        rideController.offerRide(createOfferRideReq(user1.getPayload(), "Kanpur",
                1, "Lucknow", vehicle1.getPayload())
		);
        rideController.offerRide(createOfferRideReq(user1.getPayload(), "Kanpur",
                3, "Lucknow", vehicle2.getPayload())
		);

		// add user2 and search for ride
        ServiceRequest<User> user2 = createUserReq("Nandini", "F", 26, "9648643786");
		userController.addUser(user2);
		ServiceResponse<Rides> output = rideController.searchRide(createSearchRideReq(user2.getPayload(),
                "Kanpur", "Lucknow", 1, "Activa")
		);
		assertEquals("SUCCESS", output.getStatus().name());
		assertEquals(1, output.getResult().getRides().size());

        // select ride 1
        rideController.selectRide(createSelectRideReq(output.getResult().getRides().get(0), user2.getPayload()));

		// print ride statistics
		rideController.rideStatistics();
	}

	@Test
	public void tc3() {

		// add user1 and vehicle and offer rides
		ServiceRequest<User> user1 = createUserReq("Rohan", "M", 36, "9648643784");
		ServiceRequest<Vehicle> vehicle1 = createVehicleReq("Rohan","Swift",
				"KA-01-12345"
		);
		ServiceRequest<Vehicle> vehicle2 = createVehicleReq("Rohan","Activa",
				"KA-01-12346"
		);
		userController.addUser(user1);
		vehicleController.addVehicle(vehicle1);
		vehicleController.addVehicle(vehicle2);
		rideController.offerRide(createOfferRideReq(user1.getPayload(), "Kanpur", 1,
				"Lucknow", vehicle1.getPayload())
		);
		rideController.offerRide(createOfferRideReq(user1.getPayload(), "Kanpur", 3,
				"Lucknow", vehicle2.getPayload())
		);

		// add user2 and search for ride
		ServiceRequest<User> user2 = createUserReq("Nandini", "F", 26, "9648643786");
		userController.addUser(user2);
		assertThrows(RideSharingException.class, () -> rideController.searchRide(createSearchRideReq(user2.getPayload(),
				"Kanpur", "Lucknow", 1, "Maruti")
		));
		// print ride statistics
		rideController.rideStatistics();
	}

	/**
	 * When ride is already offered by some vehicle
	 *
	 */
	@Test
	public void tc4() {

		// add user and vehicle and offer rides
		ServiceRequest<User> user = createUserReq("Rohan", "M", 36, "9648643784");
		ServiceRequest<Vehicle> vehicle1 = createVehicleReq("Rohan","Swift",
				"KA-01-12345"
		);
		userController.addUser(user);
		vehicleController.addVehicle(vehicle1);
		rideController.offerRide(createOfferRideReq(user.getPayload(), "Kanpur", 1,
				"Lucknow", vehicle1.getPayload())
		);
		assertThrows(RideSharingException.class, () -> rideController.offerRide(
				createOfferRideReq(user.getPayload(), "Kanpur", 1, "Lucknow",
						vehicle1.getPayload())
		));
		// print ride statistics
		rideController.rideStatistics();
	}

	/**
	 * When strategy is not given during search
	 *
	 */
	@Test
	public void tc5() {

		// add user1 and vehicle and offer rides
		ServiceRequest<User> user1 = createUserReq("Rohan", "M", 36, "9648643784");
		ServiceRequest<Vehicle> vehicle1 = createVehicleReq("Rohan","Swift",
				"KA-01-12345"
		);
		ServiceRequest<Vehicle> vehicle2 = createVehicleReq("Rohan","Activa",
				"KA-01-12346"
		);
		userController.addUser(user1);
		vehicleController.addVehicle(vehicle1);
		vehicleController.addVehicle(vehicle2);
		rideController.offerRide(createOfferRideReq(user1.getPayload(), "Kanpur", 1,
				"Lucknow", vehicle1.getPayload())
		);
		rideController.offerRide(createOfferRideReq(user1.getPayload(), "Kanpur", 3,
				"Lucknow", vehicle2.getPayload())
		);

		// add user2 and search for ride
		ServiceRequest<User> user2 = createUserReq("Nandini", "F", 26, "9648643786");
		userController.addUser(user2);
		ServiceResponse<Rides> output = rideController.searchRide(createSearchRideReq(user2.getPayload(),
				"Kanpur", "Lucknow", 1, null)
		);
		assertEquals("SUCCESS", output.getStatus().name());
		assertEquals(2, output.getResult().getRides().size());

		// select ride 1
		rideController.selectRide(createSelectRideReq(output.getResult().getRides().get(0), user2.getPayload()));
		// select ride 2
		rideController.selectRide(createSelectRideReq(output.getResult().getRides().get(1), user2.getPayload()));

		// print ride statistics
		rideController.rideStatistics();
	}

	/**
	 * End the running ride and offer the ride again
	 *
	 */
	@Test
	public void tc6() {

		// add user1 and vehicle and offer rides
		ServiceRequest<User> user1 = createUserReq("Rohan", "M", 36, "9648643784");
		ServiceRequest<Vehicle> vehicle1 = createVehicleReq("Rohan","Swift",
				"KA-01-12345"
		);
		userController.addUser(user1);
		vehicleController.addVehicle(vehicle1);
		ServiceResponse<Ride> ride = rideController.offerRide(createOfferRideReq(user1.getPayload(), "Kanpur",
				1, "Lucknow", vehicle1.getPayload())
		);
		assertThrows(RideSharingException.class, () -> rideController.offerRide(createOfferRideReq(user1.getPayload(),
				"Kanpur", 1, "Lucknow", vehicle1.getPayload())
		));

		// end ride
		rideController.endRide(createEndRideReq(ride.getResult().getId()));

		// offer ride again with same vehicle
		rideController.offerRide(createOfferRideReq(user1.getPayload(), "Kanpur",
				1, "Lucknow", vehicle1.getPayload())
		);

		// add user2 and search for ride
		ServiceRequest<User> user2 = createUserReq("Nandini", "F", 26, "9648643786");
		userController.addUser(user2);
		ServiceResponse<Rides> output = rideController.searchRide(createSearchRideReq(user2.getPayload(),
				"Kanpur", "Lucknow", 1, null)
		);
		assertEquals("SUCCESS", output.getStatus().name());
		assertEquals(1, output.getResult().getRides().size());

		// select ride 1
		rideController.selectRide(createSelectRideReq(output.getResult().getRides().get(0), user2.getPayload()));

		// print ride statistics
		rideController.rideStatistics();
	}

	/**
	 * When there is no ride with given available seats
	 *
	 */
	@Test
	public void tc7() {

		// add user1 and vehicle and offer rides
		ServiceRequest<User> user1 = createUserReq("Rohan", "M", 36, "9648643784");
		ServiceRequest<Vehicle> vehicle1 = createVehicleReq("Rohan","Swift",
				"KA-01-12345"
		);
		ServiceRequest<Vehicle> vehicle2 = createVehicleReq("Rohan","Activa",
				"KA-01-12346"
		);
		userController.addUser(user1);
		vehicleController.addVehicle(vehicle1);
		vehicleController.addVehicle(vehicle2);
		rideController.offerRide(createOfferRideReq(user1.getPayload(), "Kanpur",
				1, "Lucknow", vehicle1.getPayload())
		);
		rideController.offerRide(createOfferRideReq(user1.getPayload(), "Kanpur",
				3, "Lucknow", vehicle2.getPayload())
		);

		// add user2 and search for ride
		ServiceRequest<User> user2 = createUserReq("Nandini", "F", 26, "9648643786");
		userController.addUser(user2);
		assertThrows(RideSharingException.class, () -> rideController.searchRide(createSearchRideReq(user2.getPayload(),
				"Kanpur", "Lucknow", 4, "Most Vacant")
		));

		// print ride statistics
		rideController.rideStatistics();
	}

	private ServiceRequest<Ride> createEndRideReq(String id) {

		Ride ride = new Ride();
		ride.setId(id);
		ServiceRequest<Ride> endRideReq = new ServiceRequest<>();
		endRideReq.setPayload(ride);
		return endRideReq;
	}

	private ServiceRequest<Ride> createSearchRideReq(User user, String origin, String dest,
													 Integer availableSeats, String strategy
	) {

		Ride ride = new Ride();
		ride.setRequestedBy(user.getUserName());
		ride.setOrigin(origin);
		ride.setDestination(dest);
		ride.setAvailableSeats(availableSeats);
		ride.setSelectionStrategy(strategy);
		ServiceRequest<Ride> createRideReq = new ServiceRequest<>();
		createRideReq.setPayload(ride);
		return createRideReq;
	}

	private ServiceRequest<Ride> createOfferRideReq(User user, String origin, Integer availableSeats,
                                                          String destination, Vehicle vehicle
	) {

		Ride ride = new Ride();
		ride.setOfferedBy(user.getUserName());
		ride.setOrigin(origin);
		ride.setDestination(destination);
		ride.setAvailableSeats(availableSeats);
		ride.setVehicleModel(vehicle.getVehicleModel());
		ride.setVehicleNo(vehicle.getVehicleNo());
        ride.setVehicleId(vehicle.getId());
		ride.setUserId(user.getId());
		ServiceRequest<Ride> createRideReq = new ServiceRequest<>();
		createRideReq.setPayload(ride);
		return createRideReq;
	}

	private ServiceRequest<Vehicle> createVehicleReq(String vehicleOwner, String vehicleModel, String vehicleNo) {

		Vehicle vehicle = new Vehicle(vehicleOwner, vehicleModel, vehicleNo);
		ServiceRequest<Vehicle> createVehicleReq = new ServiceRequest<>();
		createVehicleReq.setPayload(vehicle);
		return createVehicleReq;
	}

	private ServiceRequest<User> createUserReq(String userName, String gender, int age, String mobileNo) {

        User user = new User(userName, gender, age, mobileNo);
		ServiceRequest<User> createUserReq = new ServiceRequest<>();
		createUserReq.setPayload(user);
		return createUserReq;
	}

    private ServiceRequest<Ride> createSelectRideReq(Ride input, User user) {

        Ride ride = new Ride();
        ride.setId(input.getId());
        ride.setUserId(user.getId());
        ServiceRequest<Ride> selectRideReq = new ServiceRequest<>();
        selectRideReq.setPayload(ride);
        return selectRideReq;
    }
}
