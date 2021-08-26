package com.share.ride.ridesharing;

import com.share.ride.ridesharing.controller.ride.RideController;
import com.share.ride.ridesharing.controller.user.UserController;
import com.share.ride.ridesharing.controller.vehicle.VehicleController;
import com.share.ride.ridesharing.exception.RideSharingException;
import com.share.ride.ridesharing.model.*;
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

		// add user and vehicle and offer rides
		ServiceRequest<User> user = createUserReq("Rohan", "M", 36);
		ServiceRequest<Vehicle> vehicle1 = createVehicleReq("Rohan","Swift",
				"KA-01-12345"
		);
		ServiceRequest<Vehicle> vehicle2 = createVehicleReq("Rohan","Activa",
				"KA-01-12346"
		);
		userController.addUser(user);
		vehicleController.addVehicle(vehicle1);
		vehicleController.addVehicle(vehicle2);
		rideController.offerRide(createOfferRideReq(user.getPayload(), "Kanpur", 1,
				"Lucknow", vehicle1.getPayload())
		);
		rideController.offerRide(createOfferRideReq(user.getPayload(), "Kanpur", 3,
				"Lucknow", vehicle2.getPayload())
		);

		// add user and select a ride
		user = createUserReq("Nandini", "F", 26);
		userController.addUser(user);
		ServiceResponse<Rides> output = rideController.searchRide(createSearchRideReq(user.getPayload(), "Kanpur",
				"Lucknow", 1, "Most Vacant")
		);
		assertEquals("SUCCESS", output.getStatus().name());
		assertEquals(2, output.getResult().getRides().size());
		// print ride statistics
		rideController.rideStatistics();
	}

	@Test
	public void tc2() {

		// add user and vehicle and offer rides
		ServiceRequest<User> user = createUserReq("Rohan", "M", 36);
		ServiceRequest<Vehicle> vehicle1 = createVehicleReq("Rohan","Swift",
				"KA-01-12345"
		);
		ServiceRequest<Vehicle> vehicle2 = createVehicleReq("Rohan","Activa",
				"KA-01-12346"
		);
		userController.addUser(user);
		vehicleController.addVehicle(vehicle1);
		vehicleController.addVehicle(vehicle2);
		rideController.offerRide(createOfferRideReq(user.getPayload(), "Kanpur", 1,
				"Lucknow", vehicle1.getPayload())
		);
		rideController.offerRide(createOfferRideReq(user.getPayload(), "Kanpur", 3,
				"Lucknow", vehicle2.getPayload())
		);
		// add user and select a ride
		user = createUserReq("Nandini", "F", 26);
		userController.addUser(user);
		ServiceResponse<Rides> output = rideController.searchRide(createSearchRideReq(user.getPayload(), "Kanpur",
				"Lucknow", 1, "Activa")
		);
		assertEquals("SUCCESS", output.getStatus().name());
		assertEquals(1, output.getResult().getRides().size());
		// print ride statistics
		rideController.rideStatistics();
	}

	@Test
	public void tc3() {

		// add user1 and vehicle and offer rides
		ServiceRequest<User> user1 = createUserReq("Rohan", "M", 36);
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
		// add user2 and search ride
		ServiceRequest<User> user2 = createUserReq("Nandini", "F", 26);
		userController.addUser(user2);
		assertThrows(RideSharingException.class, () -> rideController.searchRide(createSearchRideReq(user2.getPayload(),
				"Kanpur", "Lucknow", 1, "Maruti")
		));
		// print ride statistics
		rideController.rideStatistics();
	}

	@Test
	public void tc4() {

		// add user and vehicle and offer rides
		ServiceRequest<User> user = createUserReq("Rohan", "M", 36);
		ServiceRequest<Vehicle> vehicle1 = createVehicleReq("Rohan","Swift",
				"KA-01-12345"
		);
		ServiceRequest<Vehicle> vehicle2 = createVehicleReq("Rohan","Activa",
				"KA-01-12346"
		);
		userController.addUser(user);
		vehicleController.addVehicle(vehicle1);
		vehicleController.addVehicle(vehicle2);
		rideController.offerRide(createOfferRideReq(user.getPayload(), "Kanpur", 1,
				"Lucknow", vehicle1.getPayload())
		);
		assertThrows(RideSharingException.class,
				() -> rideController.offerRide(createOfferRideReq(user.getPayload(), "Kanpur",
				3, "Lucknow", vehicle2.getPayload())
		));
		// print ride statistics
		rideController.rideStatistics();
	}

	private ServiceRequest<Ride> createSearchRideReq(User user, String origin, String dest, Integer availableSeats,
													 String strategy
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

	private ServiceRequest<Ride> createOfferRideReq(User user, String origin, Integer availableSeats, String destination,
													Vehicle vehicle
	) {

		Ride ride = new Ride();
		ride.setOfferedBy(user.getUserName());
		ride.setOrigin(origin);
		ride.setDestination(destination);
		ride.setAvailableSeats(availableSeats);
		ride.setVehicle(vehicle);
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

	private ServiceRequest<User> createUserReq(String userName, String gender, int age) {

		User user = new User(userName, gender, age);
		ServiceRequest<User> createUserReq = new ServiceRequest<>();
		createUserReq.setPayload(user);
		return createUserReq;
	}
}
