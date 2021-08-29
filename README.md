# ride-sharing
Ride sharing app

Entities: OfferedRideEntity, TakenRideEntity, UserEntity, VehicleEntity

OfferedRideEntity -> This entity will store the information related to offered ride 
TakenRideEntity -> This entity will store the information related to taken ride 
UserEntity -> This entity will store the information of user
VehicleEntity -> This entity will store the information of vehicle

OfferedRideEntity and TakenRideEntity are in One-to-Many relationship
UserEntity and TakenRideEntity are in One-to-Many relationship
UserEntity and VehicleEntity are in One-to-Many relationship

To test the application use the test cases. Location of test path is src/test/java/com.share.ride.ridesharing/RideSharingAppTests.
Note: Please run each test case from RideSharingAppTests separately.

