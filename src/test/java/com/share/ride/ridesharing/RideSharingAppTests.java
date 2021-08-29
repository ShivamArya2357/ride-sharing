package com.share.ride.ridesharing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.share.ride.ridesharing.contract.*;
import com.share.ride.ridesharing.util.ObjectMapperFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RideSharingAppTests {

    @Autowired
    private MockMvc mvc;

    /**
     * Sample test case from assignment
     *
     * @throws Exception
     */
    @Test
    public void sampleTestCase() throws Exception {

        // Add Rohan
        ServiceRequest<User> addUserRequest1 = createUserReq("Rohan", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult1 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest1.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes1 = ObjectMapperFactory.getMapper().readValue(
                addUserResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes1.getStatus().name());

        // Rohan adds one vehicle
        ServiceRequest<Vehicle> addVehicleRequest1 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Swift", generateVehicleNo()
        );
        MvcResult addVehicleResult1 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest1.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes1 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes1.getStatus().name());

        // Add Shashank
        ServiceRequest<User> addUserRequest2 = createUserReq("Shashank", "M", 29,
                generateMobileNo()
        );
        MvcResult addUserResult2 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest2.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes2 = ObjectMapperFactory.getMapper().readValue(
                addUserResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes2.getStatus().name());

        // Shashank adds first vehicle
        ServiceRequest<Vehicle> addVehicleRequest2 = createVehicleReq(addUserRes2.getResult(),
                "Shashank","Baleno", generateVehicleNo()
        );
        MvcResult addVehicleResult2 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest2.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes2 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes2.getStatus().name());

        // Add Nandini
        ServiceRequest<User> addUserRequest3 = createUserReq("Nandini", "F", 29,
                generateMobileNo()
        );
        MvcResult addUserResult3 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest3.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes3 = ObjectMapperFactory.getMapper().readValue(
                addUserResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes3.getStatus().name());

        // Add Shipra
        ServiceRequest<User> addUserRequest4 = createUserReq("Shipra", "F", 27,
                generateMobileNo()
        );
        MvcResult addUserResult4 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest4.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes4 = ObjectMapperFactory.getMapper().readValue(
                addUserResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes4.getStatus().name());

        // Shipra adds first vehicle
        ServiceRequest<Vehicle> addVehicleRequest4 = createVehicleReq(addUserRes4.getResult(),
                "Shipra","Polo", generateVehicleNo()
        );
        MvcResult addVehicleResult4 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest4.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes4 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes4.getStatus().name());

        // Shipra adds second vehicle
        ServiceRequest<Vehicle> addVehicleRequest5 = createVehicleReq(addUserRes4.getResult(),
                "Shashank","Activa", generateVehicleNo()
        );
        MvcResult addVehicleResult5 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest5.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes5 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult5.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes5.getStatus().name());

        // Add Gaurav
        ServiceRequest<User> addUserRequest5 = createUserReq("Gaurav", "M", 29,
                generateMobileNo()
        );
        MvcResult addUserResult5 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest5.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes5 = ObjectMapperFactory.getMapper().readValue(
                addUserResult5.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes5.getStatus().name());

        // Add Rahul
        ServiceRequest<User> addUserRequest6 = createUserReq("Rahul", "M", 35,
                generateMobileNo()
        );
        MvcResult addUserResult6 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest6.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes6 = ObjectMapperFactory.getMapper().readValue(
                addUserResult6.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes6.getStatus().name());

        // Rahul adds first vehicle
        ServiceRequest<Vehicle> addVehicleRequest6 = createVehicleReq(addUserRes6.getResult(),
                "Rahul","XUV", generateVehicleNo()
        );
        MvcResult addVehicleResult6 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest6.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes6 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult6.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes6.getStatus().name());

        // Rohan offers 1st ride
        ServiceRequest<Ride> offerRideRequest1 = createOfferRideReq(addUserRes1.getResult(), "Hyderabad",
                1, "Bangalore", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult1 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes1 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes1.getStatus().name());

        // Shipra offers 1st ride
        ServiceRequest<Ride> offerRideRequest2 = createOfferRideReq(addUserRes4.getResult(), "Bangalore",
                1, "Mysore", addVehicleRes5.getResult()
        );
        MvcResult offerRideResult2 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes2 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes2.getStatus().name());

        // Shipra offers 2nd ride
        ServiceRequest<Ride> offerRideRequest3 = createOfferRideReq(addUserRes4.getResult(), "Bangalore",
                2, "Mysore", addVehicleRes4.getResult()
        );
        MvcResult offerRideResult3 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes3 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes3.getStatus().name());

        // Shashank offers 1st ride
        ServiceRequest<Ride> offerRideRequest4 = createOfferRideReq(addUserRes2.getResult(), "Hyderabad",
                2, "Bangalore", addVehicleRes2.getResult()
        );
        MvcResult offerRideResult4 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest4.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes4 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes4.getStatus().name());

        // Rahul offers 1st ride
        ServiceRequest<Ride> offerRideRequest5 = createOfferRideReq(addUserRes6.getResult(), "Hyderabad",
                5, "Bangalore", addVehicleRes6.getResult()
        );
        MvcResult offerRideResult5 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest5.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes5 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult5.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes5.getStatus().name());

        // Rohan offers 2nd ride
        ServiceRequest<Ride> offerRideRequest6 = createOfferRideReq(addUserRes1.getResult(), "Bangalore",
                1, "Pune", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult6 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest6.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes6 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult6.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("FAIL", offerRideRes6.getStatus().name());

        // Nandini searches for a ride
        ServiceRequest<Ride> searchRideRequest1 = createSearchRideReq(addUserRes3.getResult(),
                "Bangalore", "Mysore", 1, "Most Vacant"
        );
        MvcResult searchRideResult1 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes1 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("SUCCESS", searchRideRes1.getStatus().name());
        assertEquals(1, searchRideRes1.getResult().getRides().size());

        // Nandini selects for a ride
        ServiceRequest<Ride> selectRideRequest1 = createSelectRideReq(searchRideRes1.getResult().getRides().get(0),
                addUserRes3.getResult()
        );
        MvcResult selectRideResult1 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes1 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes1.getStatus().name());

        // Gaurav searches for a ride
        ServiceRequest<Ride> searchRideRequest2 = createSearchRideReq(addUserRes5.getResult(),
                "Bangalore", "Mysore", 1, "Activa"
        );
        MvcResult searchRideResult2 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes2 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("SUCCESS", searchRideRes2.getStatus().name());
        assertEquals(1, searchRideRes2.getResult().getRides().size());

        // Gaurav selects for a ride
        ServiceRequest<Ride> selectRideRequest2 = createSelectRideReq(searchRideRes2.getResult().getRides().get(0),
                addUserRes5.getResult()
        );
        MvcResult selectRideResult2 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes2 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes2.getStatus().name());

        // Shashank searches for a ride
        ServiceRequest<Ride> searchRideRequest4 = createSearchRideReq(addUserRes2.getResult(),
                "Mumbai", "Bangalore", 1, "Most Vacant"
        );
        MvcResult searchRideResult4 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest4.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes4 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("FAIL", searchRideRes4.getStatus().name());

        // Rohan searches for a ride
        ServiceRequest<Ride> searchRideRequest5 = createSearchRideReq(addUserRes1.getResult(),
                "Hyderabad", "Bangalore", 1, "Baleno"
        );
        MvcResult searchRideResult5 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest5.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes5 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult5.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("SUCCESS", searchRideRes5.getStatus().name());
        assertEquals(1, searchRideRes5.getResult().getRides().size());

        // Rohan selects a ride
        ServiceRequest<Ride> selectRideRequest5 = createSelectRideReq(searchRideRes5.getResult().getRides().get(0),
                addUserRes1.getResult()
        );
        MvcResult selectRideResult5 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest5.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes5 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult5.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes5.getStatus().name());

        // Shashank searches for a ride
        ServiceRequest<Ride> searchRideRequest6 = createSearchRideReq(addUserRes2.getResult(),
                "Hyderabad", "Bangalore", 1, "Polo"
        );
        MvcResult searchRideResult6 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest6.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes6 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult6.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("SUCCESS", searchRideRes6.getStatus().name());
        assertEquals(2, searchRideRes6.getResult().getRides().size());

        // end Rohan's 1st ride
        ServiceRequest<Ride> endRideRequest1 = createEndRideReq(offerRideRes1.getResult().getId());
        MvcResult endRideResult1 = mvc.perform(post("/v1/rides/end")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(endRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> endRideRes1 = ObjectMapperFactory.getMapper().readValue(
                endRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", endRideRes1.getStatus().name());

        // end Shipra's 1st ride
        ServiceRequest<Ride> endRideRequest2 = createEndRideReq(offerRideRes2.getResult().getId());
        MvcResult endRideResult2 = mvc.perform(post("/v1/rides/end")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(endRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> endRideRes2 = ObjectMapperFactory.getMapper().readValue(
                endRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", endRideRes2.getStatus().name());

        // end Shipra's 2nd ride
        ServiceRequest<Ride> endRideRequest3 = createEndRideReq(offerRideRes3.getResult().getId());
        MvcResult endRideResult3 = mvc.perform(post("/v1/rides/end")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(endRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Ride> endRideRes3 = ObjectMapperFactory.getMapper().readValue(
                endRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", endRideRes3.getStatus().name());

        // end Shipra's 2nd ride
        ServiceRequest<Ride> endRideRequest4 = createEndRideReq(offerRideRes4.getResult().getId());
        MvcResult endRideResult4 = mvc.perform(post("/v1/rides/end")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(endRideRequest4.toString())
        ).andReturn();
        ServiceResponse<Ride> endRideRes4 = ObjectMapperFactory.getMapper().readValue(
                endRideResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", endRideRes4.getStatus().name());

        // print ride statistics
        mvc.perform(get("/v1/rides/stats")).andReturn();
    }

    /**
     * When selection strategy is most vacant
     *
     * @throws Exception
     */
    @Test
    public void tc1() throws Exception {

        // add first user
        ServiceRequest<User> addUserRequest1 = createUserReq("Rohan", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult1 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest1.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes1 = ObjectMapperFactory.getMapper().readValue(
                addUserResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes1.getStatus().name());

        // first user adds one vehicle
        ServiceRequest<Vehicle> addVehicleRequest1 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Swift", generateVehicleNo()
        );
        MvcResult addVehicleResult1 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest1.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes1 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes1.getStatus().name());

        // first user adds second vehicle
        ServiceRequest<Vehicle> addVehicleRequest2 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Activa", generateVehicleNo()
        );
        MvcResult addVehicleResult2 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest2.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes2 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes2.getStatus().name());

        // first user offers 1st ride
        ServiceRequest<Ride> offerRideRequest1 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                1, "Lucknow", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult1 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes1 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes1.getStatus().name());

        // first user offers 2nd ride
        ServiceRequest<Ride> offerRideRequest2 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                3, "Lucknow", addVehicleRes2.getResult()
        );
        MvcResult offerRideResult2 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes2 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes2.getStatus().name());

        // add second user
        ServiceRequest<User> addUserRequest2 = createUserReq("Nandini", "F", 26,
                generateMobileNo()
        );
        MvcResult addUserResult2 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest2.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes2 = ObjectMapperFactory.getMapper().readValue(
                addUserResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes2.getStatus().name());

        // second user searches for a ride
        ServiceRequest<Ride> searchRideRequest1 = createSearchRideReq(addUserRes2.getResult(),
                "Kanpur", "Lucknow", 1, "Most Vacant"
        );
        MvcResult searchRideResult1 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes1 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("SUCCESS", searchRideRes1.getStatus().name());
        assertEquals(1, searchRideRes1.getResult().getRides().size());

        // second user selects a ride
        ServiceRequest<Ride> selectRideRequest1 = createSelectRideReq(searchRideRes1.getResult().getRides().get(0),
                addUserRes2.getResult()
        );
        MvcResult selectRideResult1 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes1 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes1.getStatus().name());

        // print ride statistics
        mvc.perform(get("/v1/rides/stats")).andReturn();
    }

    @Test
    public void tc2() throws Exception {

        // add first user
        ServiceRequest<User> addUserRequest1 = createUserReq("Rohan", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult1 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest1.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes1 = ObjectMapperFactory.getMapper().readValue(
                addUserResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes1.getStatus().name());

        // first user adds one vehicle
        ServiceRequest<Vehicle> addVehicleRequest1 = createVehicleReq(addUserRes1.getResult(),
                "Rohan", "Swift", generateVehicleNo()
        );
        MvcResult addVehicleResult1 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest1.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes1 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes1.getStatus().name());

        // first user adds second vehicle
        ServiceRequest<Vehicle> addVehicleRequest2 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Activa", generateVehicleNo()
        );
        MvcResult addVehicleResult2 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest2.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes2 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes2.getStatus().name());

        // first user offers 1st ride
        ServiceRequest<Ride> offerRideRequest1 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                1, "Lucknow", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult1 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes1 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes1.getStatus().name());

        // first user offers 2nd ride
        ServiceRequest<Ride> offerRideRequest2 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                3, "Lucknow", addVehicleRes2.getResult()
        );
        MvcResult offerRideResult2 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes2 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes2.getStatus().name());

        // add second user
        ServiceRequest<User> addUserRequest2 = createUserReq("Nandini", "F", 26,
                generateMobileNo()
        );
        MvcResult addUserResult2 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest2.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes2 = ObjectMapperFactory.getMapper().readValue(
                addUserResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes2.getStatus().name());

        // second user searches for a ride
        ServiceRequest<Ride> searchRideRequest1 = createSearchRideReq(addUserRes2.getResult(),
                "Kanpur", "Lucknow", 1, "Activa"
        );
        MvcResult searchRideResult1 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes1 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("SUCCESS", searchRideRes1.getStatus().name());
        assertEquals(1, searchRideRes1.getResult().getRides().size());

        // second user selects a ride
        ServiceRequest<Ride> selectRideRequest1 = createSelectRideReq(searchRideRes1.getResult().getRides().get(0),
                addUserRes2.getResult()
        );
        MvcResult selectRideResult1 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes1 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes1.getStatus().name());

        // print ride statistics
        mvc.perform(get("/v1/rides/stats")).andReturn();
    }

    @Test
    public void tc3() throws Exception {

        // add first user
        ServiceRequest<User> addUserRequest1 = createUserReq("Rohan", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult1 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest1.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes1 = ObjectMapperFactory.getMapper().readValue(
                addUserResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes1.getStatus().name());

        // add one vehicle
        ServiceRequest<Vehicle> addVehicleRequest1 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Swift", generateVehicleNo()
        );
        MvcResult addVehicleResult1 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest1.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes1 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes1.getStatus().name());

        // add second vehicle
        ServiceRequest<Vehicle> addVehicleRequest2 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Activa", generateVehicleNo()
        );
        MvcResult addVehicleResult2 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest2.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes2 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes2.getStatus().name());

        // first user offers 1st ride
        ServiceRequest<Ride> offerRideRequest1 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                1, "Lucknow", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult1 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes1 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes1.getStatus().name());

        // first user offers 2nd ride
        ServiceRequest<Ride> offerRideRequest2 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                3, "Lucknow", addVehicleRes2.getResult()
        );
        MvcResult offerRideResult2 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes2 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes2.getStatus().name());

        // add second user
        ServiceRequest<User> addUserRequest2 = createUserReq("Nandini", "F", 26,
                generateMobileNo()
        );
        MvcResult addUserResult2 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest2.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes2 = ObjectMapperFactory.getMapper().readValue(
                addUserResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes2.getStatus().name());

        // second user searches for a ride
        ServiceRequest<Ride> searchRideRequest1 = createSearchRideReq(addUserRes2.getResult(),
                "Kanpur", "Lucknow", 1, "Maruti"
        );
        MvcResult searchRideResult1 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes1 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("SUCCESS", searchRideRes1.getStatus().name());
        assertEquals(2, searchRideRes1.getResult().getRides().size());

        // print ride statistics
        mvc.perform(get("/v1/rides/stats")).andReturn();
    }

    /**
     * When existing vehicle is offered twice
     *
     */
    @Test
    public void tc4() throws Exception {

        // add first user
        ServiceRequest<User> addUserRequest1 = createUserReq("Rohan", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult1 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest1.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes1 = ObjectMapperFactory.getMapper().readValue(
                addUserResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes1.getStatus().name());

        // add one vehicle
        ServiceRequest<Vehicle> addVehicleRequest1 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Swift", generateVehicleNo()
        );
        MvcResult addVehicleResult1 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest1.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes1 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes1.getStatus().name());

        // first user offers 1st ride
        ServiceRequest<Ride> offerRideRequest1 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                1, "Lucknow", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult1 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes1 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes1.getStatus().name());

        // first user offers 1st ride again
        ServiceRequest<Ride> offerRideRequest2 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                1, "Lucknow", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult2 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes2 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("FAIL", offerRideRes2.getStatus().name());

        // print ride statistics
        mvc.perform(get("/v1/rides/stats")).andReturn();
    }

    /**
     * When strategy is not given during search
     *
     */
    @Test
    public void tc5() throws Exception {

        // add first user
        ServiceRequest<User> addUserRequest1 = createUserReq("Rohan", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult1 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest1.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes1 = ObjectMapperFactory.getMapper().readValue(
                addUserResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes1.getStatus().name());

        // add one vehicle
        ServiceRequest<Vehicle> addVehicleRequest1 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Swift", generateVehicleNo()
        );
        MvcResult addVehicleResult1 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest1.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes1 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes1.getStatus().name());

        // add second vehicle
        ServiceRequest<Vehicle> addVehicleRequest2 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Activa", generateVehicleNo()
        );
        MvcResult addVehicleResult2 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest2.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes2 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes2.getStatus().name());

        // first user offers 1st ride
        ServiceRequest<Ride> offerRideRequest1 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                1, "Lucknow", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult1 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes1 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes1.getStatus().name());

        // first user offers 2nd ride
        ServiceRequest<Ride> offerRideRequest2 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                3, "Lucknow", addVehicleRes2.getResult()
        );
        MvcResult offerRideResult2 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes2 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes2.getStatus().name());

        // add second user
        ServiceRequest<User> addUserRequest2 = createUserReq("Nandini", "F", 26,
                generateMobileNo()
        );
        MvcResult addUserResult2 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest2.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes2 = ObjectMapperFactory.getMapper().readValue(
                addUserResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes2.getStatus().name());

        // second user searches for a ride
        ServiceRequest<Ride> searchRideRequest1 = createSearchRideReq(addUserRes2.getResult(),
                "Kanpur", "Lucknow", 1, null
        );
        MvcResult searchRideResult1 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes1 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("SUCCESS", searchRideRes1.getStatus().name());
        assertEquals(2, searchRideRes1.getResult().getRides().size());

        // second user selects 1st ride
        ServiceRequest<Ride> selectRideRequest1 = createSelectRideReq(searchRideRes1.getResult().getRides().get(0),
                addUserRes2.getResult()
        );
        MvcResult selectRideResult1 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes1 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes1.getStatus().name());

        // second user selects 2nd ride
        ServiceRequest<Ride> selectRideRequest2 = createSelectRideReq(searchRideRes1.getResult().getRides().get(1),
                addUserRes2.getResult()
        );
        MvcResult selectRideResult2 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes2 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes2.getStatus().name());

        // print ride statistics
        mvc.perform(get("/v1/rides/stats")).andReturn();
    }

    /**
     * End the running ride and offer the ride again
     *
     */
    @Test
    public void tc6() throws Exception {

        // add first user
        ServiceRequest<User> addUserRequest1 = createUserReq("Rohan", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult1 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest1.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes1 = ObjectMapperFactory.getMapper().readValue(
                addUserResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes1.getStatus().name());

        // add one vehicle
        ServiceRequest<Vehicle> addVehicleRequest1 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Swift", generateVehicleNo()
        );
        MvcResult addVehicleResult1 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest1.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes1 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes1.getStatus().name());

        // first user offers 1st ride
        ServiceRequest<Ride> offerRideRequest1 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                1, "Lucknow", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult1 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes1 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes1.getStatus().name());

        // first user offers 1st ride again
        ServiceRequest<Ride> offerRideRequest2 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                1, "Lucknow", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult2 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes2 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("FAIL", offerRideRes2.getStatus().name());

        // first user ends 1st ride
        ServiceRequest<Ride> endRideRequest1 = createEndRideReq(
                offerRideRes1.getResult().getId()
        );
        MvcResult endRideResult1 = mvc.perform(post("/v1/rides/end")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(endRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> endRideRes1 = ObjectMapperFactory.getMapper().readValue(
                endRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", endRideRes1.getStatus().name());

        // first user offers 1st ride again
        ServiceRequest<Ride> offerRideRequest3 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                1, "Lucknow", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult3 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes3 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes3.getStatus().name());

        // add second user
        ServiceRequest<User> addUserRequest2 = createUserReq("Nandini", "F", 26,
                generateMobileNo()
        );
        MvcResult addUserResult2 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest2.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes2 = ObjectMapperFactory.getMapper().readValue(
                addUserResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes2.getStatus().name());

        // second user searches for a ride
        ServiceRequest<Ride> searchRideRequest1 = createSearchRideReq(addUserRes2.getResult(),
                "Kanpur", "Lucknow", 1, null
        );
        MvcResult searchRideResult1 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes1 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("SUCCESS", searchRideRes1.getStatus().name());
        assertEquals(1, searchRideRes1.getResult().getRides().size());

        // second user selects a ride
        ServiceRequest<Ride> selectRideRequest1 = createSelectRideReq(searchRideRes1.getResult().getRides().get(0),
                addUserRes2.getResult()
        );
        MvcResult selectRideResult1 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes1 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes1.getStatus().name());

        // print ride statistics
        mvc.perform(get("/v1/rides/stats")).andReturn();
    }

    /**
     * When there is no ride with given available seats
     *
     */
    @Test
    public void tc7() throws Exception {

        // add first user
        ServiceRequest<User> addUserRequest1 = createUserReq("Rohan", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult1 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest1.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes1 = ObjectMapperFactory.getMapper().readValue(
                addUserResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes1.getStatus().name());

        // add one vehicle
        ServiceRequest<Vehicle> addVehicleRequest1 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Swift", generateVehicleNo()
        );
        MvcResult addVehicleResult1 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest1.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes1 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes1.getStatus().name());

        // add second vehicle
        ServiceRequest<Vehicle> addVehicleRequest2 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Activa", generateVehicleNo()
        );
        MvcResult addVehicleResult2 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest2.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes2 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes2.getStatus().name());

        // first user offers 1st ride
        ServiceRequest<Ride> offerRideRequest1 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                1, "Lucknow", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult1 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes1 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes1.getStatus().name());

        // first user offers 2nd ride
        ServiceRequest<Ride> offerRideRequest2 = createOfferRideReq(addUserRes1.getResult(), "Kanpur",
                3, "Lucknow", addVehicleRes2.getResult()
        );
        MvcResult offerRideResult2 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes2 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes2.getStatus().name());

        // add second user
        ServiceRequest<User> addUserRequest2 = createUserReq("Nandini", "F", 26,
                generateMobileNo()
        );
        MvcResult addUserResult2 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest2.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes2 = ObjectMapperFactory.getMapper().readValue(
                addUserResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes2.getStatus().name());

        // second user searches for a ride
        ServiceRequest<Ride> searchRideRequest1 = createSearchRideReq(addUserRes2.getResult(),
                "Kanpur", "Lucknow", 4, "Most Vacant"
        );
        MvcResult searchRideResult1 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes1 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("FAIL", searchRideRes1.getStatus().name());

        // print ride statistics
        mvc.perform(get("/v1/rides/stats")).andReturn();
    }

    /**
     * When there is no direct ride but some indirect ride is possible and search will happen without any strategy
     *
     */
    @Test
    public void tc8() throws Exception {

        // add first user
        ServiceRequest<User> addUserRequest1 = createUserReq("Rohan", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult1 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest1.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes1 = ObjectMapperFactory.getMapper().readValue(
                addUserResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes1.getStatus().name());

        // add one vehicle
        ServiceRequest<Vehicle> addVehicleRequest1 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Swift", generateVehicleNo()
        );
        MvcResult addVehicleResult1 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest1.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes1 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes1.getStatus().name());

        // add second vehicle
        ServiceRequest<Vehicle> addVehicleRequest2 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Activa", generateVehicleNo()
        );
        MvcResult addVehicleResult2 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest2.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes2 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes2.getStatus().name());

        // first user offers 1st ride
        ServiceRequest<Ride> offerRideRequest1 = createOfferRideReq(addUserRes1.getResult(), "Chandigarh",
                5, "Delhi", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult1 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes1 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes1.getStatus().name());

        // first user offers 2nd ride
        ServiceRequest<Ride> offerRideRequest2 = createOfferRideReq(addUserRes1.getResult(), "Chandigarh",
                3, "Simla", addVehicleRes2.getResult()
        );
        MvcResult offerRideResult2 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes2 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes2.getStatus().name());

        // add second user
        ServiceRequest<User> addUserRequest2 = createUserReq("Rohani", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult2 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest2.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes2 = ObjectMapperFactory.getMapper().readValue(
                addUserResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes2.getStatus().name());

        // second user adds 1st vehicle
        ServiceRequest<Vehicle> addVehicleRequest3 = createVehicleReq(addUserRes2.getResult(),
                "Rohani","Honda", generateVehicleNo()
        );
        MvcResult addVehicleResult3 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest3.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes3 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes3.getStatus().name());

        // second user adds 2nd vehicle
        ServiceRequest<Vehicle> addVehicleRequest4 = createVehicleReq(addUserRes2.getResult(),
                "Rohani","Apache", generateVehicleNo()
        );
        MvcResult addVehicleResult4 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest4.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes4 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes4.getStatus().name());

        // second user adds 3rd vehicle
        ServiceRequest<Vehicle> addVehicleRequest5 = createVehicleReq(addUserRes2.getResult(),
                "Rohani","Mercedes", generateVehicleNo()
        );
        MvcResult addVehicleResult5 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest5.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes5 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult5.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes5.getStatus().name());

        // second user offers 1st ride
        ServiceRequest<Ride> offerRideRequest3 = createOfferRideReq(addUserRes2.getResult(), "Delhi",
                5, "Lucknow", addVehicleRes3.getResult()
        );
        MvcResult offerRideResult3 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes3 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes3.getStatus().name());

        // second user offers 2nd ride
        ServiceRequest<Ride> offerRideRequest4 = createOfferRideReq(addUserRes2.getResult(), "Simla",
                2, "Lucknow", addVehicleRes4.getResult()
        );
        MvcResult offerRideResult4 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest4.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes4 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes4.getStatus().name());

        // second user offers 3rd ride
        ServiceRequest<Ride> offerRideRequest5 = createOfferRideReq(addUserRes2.getResult(), "Simla",
                3, "Dehradun", addVehicleRes5.getResult()
        );
        MvcResult offerRideResult5 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest5.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes5 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult5.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes5.getStatus().name());

        // add third user
        ServiceRequest<User> addUserRequest3 = createUserReq("Shivam", "M", 26,
                generateMobileNo()
        );
        MvcResult addUserResult3 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest3.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes3 = ObjectMapperFactory.getMapper().readValue(
                addUserResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes3.getStatus().name());

        // third user searches for a ride
        ServiceRequest<Ride> searchRideRequest3 = createSearchRideReq(addUserRes3.getResult(),
                "Chandigarh", "Lucknow", 3, null
        );
        MvcResult searchRideResult3 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes3 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("SUCCESS", searchRideRes3.getStatus().name());
        assertEquals(2, searchRideRes3.getResult().getRides().size());

        // third user selects 1st ride
        ServiceRequest<Ride> selectRideRequest3 = createSelectRideReq(searchRideRes3.getResult().getRides().get(0),
                addUserRes3.getResult()
        );
        MvcResult selectRideResult3 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes3 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes3.getStatus().name());

        // third user selects 2nd ride
        ServiceRequest<Ride> selectRideRequest4 = createSelectRideReq(searchRideRes3.getResult().getRides().get(1),
                addUserRes3.getResult()
        );
        MvcResult selectRideResult4 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest4.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes4 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes4.getStatus().name());

        // print ride statistics
        mvc.perform(get("/v1/rides/stats")).andReturn();
    }

    /**
     * When there is no direct ride but some indirect ride is possible and search will happen with Most Vacant strategy
     *
     */
    @Test
    public void tc9() throws Exception {

        // add first user
        ServiceRequest<User> addUserRequest1 = createUserReq("Rohan", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult1 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest1.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes1 = ObjectMapperFactory.getMapper().readValue(
                addUserResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes1.getStatus().name());

        // add one vehicle
        ServiceRequest<Vehicle> addVehicleRequest1 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Swift", generateVehicleNo()
        );
        MvcResult addVehicleResult1 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest1.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes1 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes1.getStatus().name());

        // add second vehicle
        ServiceRequest<Vehicle> addVehicleRequest2 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Activa", generateVehicleNo()
        );
        MvcResult addVehicleResult2 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest2.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes2 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes2.getStatus().name());

        // first user offers 1st ride
        ServiceRequest<Ride> offerRideRequest1 = createOfferRideReq(addUserRes1.getResult(), "Chandigarh",
                5, "Delhi", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult1 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes1 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes1.getStatus().name());

        // first user offers 2nd ride
        ServiceRequest<Ride> offerRideRequest2 = createOfferRideReq(addUserRes1.getResult(), "Chandigarh",
                3, "Simla", addVehicleRes2.getResult()
        );
        MvcResult offerRideResult2 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes2 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes2.getStatus().name());

        // add second user
        ServiceRequest<User> addUserRequest2 = createUserReq("Rohani", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult2 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest2.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes2 = ObjectMapperFactory.getMapper().readValue(
                addUserResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes2.getStatus().name());

        // second user adds 1st vehicle
        ServiceRequest<Vehicle> addVehicleRequest3 = createVehicleReq(addUserRes2.getResult(),
                "Rohani","Honda", generateVehicleNo()
        );
        MvcResult addVehicleResult3 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest3.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes3 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes3.getStatus().name());

        // second user adds 2nd vehicle
        ServiceRequest<Vehicle> addVehicleRequest4 = createVehicleReq(addUserRes2.getResult(),
                "Rohani","Apache", generateVehicleNo()
        );
        MvcResult addVehicleResult4 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest4.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes4 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes4.getStatus().name());

        // second user adds 3rd vehicle
        ServiceRequest<Vehicle> addVehicleRequest5 = createVehicleReq(addUserRes2.getResult(),
                "Rohani","Mercedes", generateVehicleNo()
        );
        MvcResult addVehicleResult5 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest5.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes5 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult5.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes5.getStatus().name());

        // second user offers 1st ride
        ServiceRequest<Ride> offerRideRequest3 = createOfferRideReq(addUserRes2.getResult(), "Delhi",
                5, "Lucknow", addVehicleRes3.getResult()
        );
        MvcResult offerRideResult3 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes3 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes3.getStatus().name());

        // second user offers 2nd ride
        ServiceRequest<Ride> offerRideRequest4 = createOfferRideReq(addUserRes2.getResult(), "Simla",
                2, "Lucknow", addVehicleRes4.getResult()
        );
        MvcResult offerRideResult4 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest4.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes4 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes4.getStatus().name());

        // second user offers 3rd ride
        ServiceRequest<Ride> offerRideRequest5 = createOfferRideReq(addUserRes2.getResult(), "Simla",
                3, "Dehradun", addVehicleRes5.getResult()
        );
        MvcResult offerRideResult5 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest5.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes5 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult5.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes5.getStatus().name());

        // add third user
        ServiceRequest<User> addUserRequest3 = createUserReq("Shivam", "M", 26,
                generateMobileNo()
        );
        MvcResult addUserResult3 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest3.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes3 = ObjectMapperFactory.getMapper().readValue(
                addUserResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes3.getStatus().name());

        // third user searches for a ride
        ServiceRequest<Ride> searchRideRequest3 = createSearchRideReq(addUserRes3.getResult(),
                "Chandigarh", "Lucknow", 3, "Most Vacant"
        );
        MvcResult searchRideResult3 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes3 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("SUCCESS", searchRideRes3.getStatus().name());
        assertEquals(2, searchRideRes3.getResult().getRides().size());

        // third user selects 1st ride
        ServiceRequest<Ride> selectRideRequest3 = createSelectRideReq(searchRideRes3.getResult().getRides().get(0),
                addUserRes3.getResult()
        );
        MvcResult selectRideResult3 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes3 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes3.getStatus().name());

        // third user selects 2nd ride
        ServiceRequest<Ride> selectRideRequest4 = createSelectRideReq(searchRideRes3.getResult().getRides().get(1),
                addUserRes3.getResult()
        );
        MvcResult selectRideResult4 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest4.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes4 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes4.getStatus().name());

        // print ride statistics
        mvc.perform(get("/v1/rides/stats")).andReturn();
    }

    /**
     * When there is no direct ride but some indirect ride is possible and search will happen with Preferred Vehicle strategy
     *
     */
    @Test
    public void tc10() throws Exception {

        // add first user
        ServiceRequest<User> addUserRequest1 = createUserReq("Rohan", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult1 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest1.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes1 = ObjectMapperFactory.getMapper().readValue(
                addUserResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes1.getStatus().name());

        // add one vehicle
        ServiceRequest<Vehicle> addVehicleRequest1 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Swift", generateVehicleNo()
        );
        MvcResult addVehicleResult1 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest1.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes1 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes1.getStatus().name());

        // add second vehicle
        ServiceRequest<Vehicle> addVehicleRequest2 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Activa", generateVehicleNo()
        );
        MvcResult addVehicleResult2 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest2.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes2 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes2.getStatus().name());

        // first user offers 1st ride
        ServiceRequest<Ride> offerRideRequest1 = createOfferRideReq(addUserRes1.getResult(), "Chandigarh",
                5, "Delhi", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult1 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes1 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes1.getStatus().name());

        // first user offers 2nd ride
        ServiceRequest<Ride> offerRideRequest2 = createOfferRideReq(addUserRes1.getResult(), "Chandigarh",
                3, "Simla", addVehicleRes2.getResult()
        );
        MvcResult offerRideResult2 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes2 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes2.getStatus().name());

        // add second user
        ServiceRequest<User> addUserRequest2 = createUserReq("Rohani", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult2 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest2.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes2 = ObjectMapperFactory.getMapper().readValue(
                addUserResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes2.getStatus().name());

        // second user adds 1st vehicle
        ServiceRequest<Vehicle> addVehicleRequest3 = createVehicleReq(addUserRes2.getResult(),
                "Rohani","Honda", generateVehicleNo()
        );
        MvcResult addVehicleResult3 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest3.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes3 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes3.getStatus().name());

        // second user adds 2nd vehicle
        ServiceRequest<Vehicle> addVehicleRequest4 = createVehicleReq(addUserRes2.getResult(),
                "Rohani","Apache", generateVehicleNo()
        );
        MvcResult addVehicleResult4 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest4.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes4 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes4.getStatus().name());

        // second user adds 3rd vehicle
        ServiceRequest<Vehicle> addVehicleRequest5 = createVehicleReq(addUserRes2.getResult(),
                "Rohani","Mercedes", generateVehicleNo()
        );
        MvcResult addVehicleResult5 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest5.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes5 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult5.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes5.getStatus().name());

        // second user offers 1st ride
        ServiceRequest<Ride> offerRideRequest3 = createOfferRideReq(addUserRes2.getResult(), "Delhi",
                5, "Lucknow", addVehicleRes3.getResult()
        );
        MvcResult offerRideResult3 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes3 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes3.getStatus().name());

        // second user offers 2nd ride
        ServiceRequest<Ride> offerRideRequest4 = createOfferRideReq(addUserRes2.getResult(), "Simla",
                2, "Lucknow", addVehicleRes4.getResult()
        );
        MvcResult offerRideResult4 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest4.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes4 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes4.getStatus().name());

        // second user offers 3rd ride
        ServiceRequest<Ride> offerRideRequest5 = createOfferRideReq(addUserRes2.getResult(), "Simla",
                3, "Dehradun", addVehicleRes5.getResult()
        );
        MvcResult offerRideResult5 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest5.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes5 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult5.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes5.getStatus().name());

        // add third user
        ServiceRequest<User> addUserRequest3 = createUserReq("Shivam", "M", 26,
                generateMobileNo()
        );
        MvcResult addUserResult3 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest3.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes3 = ObjectMapperFactory.getMapper().readValue(
                addUserResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes3.getStatus().name());

        // third user searches for a ride
        ServiceRequest<Ride> searchRideRequest3 = createSearchRideReq(addUserRes3.getResult(),
                "Chandigarh", "Lucknow", 3, "Honda"
        );
        MvcResult searchRideResult3 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes3 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("SUCCESS", searchRideRes3.getStatus().name());
        assertEquals(2, searchRideRes3.getResult().getRides().size());

        // third user selects 1st ride
        ServiceRequest<Ride> selectRideRequest3 = createSelectRideReq(searchRideRes3.getResult().getRides().get(0),
                addUserRes3.getResult()
        );
        MvcResult selectRideResult3 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes3 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes3.getStatus().name());

        // third user selects 2nd ride
        ServiceRequest<Ride> selectRideRequest4 = createSelectRideReq(searchRideRes3.getResult().getRides().get(1),
                addUserRes3.getResult()
        );
        MvcResult selectRideResult4 = mvc.perform(post("/v1/rides/select")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(selectRideRequest4.toString())
        ).andReturn();
        ServiceResponse<Ride> selectRideRes4 = ObjectMapperFactory.getMapper().readValue(
                selectRideResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", selectRideRes4.getStatus().name());

        // print ride statistics
        mvc.perform(get("/v1/rides/stats")).andReturn();
    }

    /**
     * When we don't have neither direct or indirect ride
     *
     * @throws Exception
     */
    @Test
    public void tc11() throws Exception {

        // add first user
        ServiceRequest<User> addUserRequest1 = createUserReq("Rohan", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult1 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest1.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes1 = ObjectMapperFactory.getMapper().readValue(
                addUserResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes1.getStatus().name());

        // add one vehicle
        ServiceRequest<Vehicle> addVehicleRequest1 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Swift", generateVehicleNo()
        );
        MvcResult addVehicleResult1 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest1.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes1 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes1.getStatus().name());

        // add second vehicle
        ServiceRequest<Vehicle> addVehicleRequest2 = createVehicleReq(addUserRes1.getResult(),
                "Rohan","Activa", generateVehicleNo()
        );
        MvcResult addVehicleResult2 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest2.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes2 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes2.getStatus().name());

        // first user offers 1st ride
        ServiceRequest<Ride> offerRideRequest1 = createOfferRideReq(addUserRes1.getResult(), "Chandigarh",
                5, "Delhi", addVehicleRes1.getResult()
        );
        MvcResult offerRideResult1 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest1.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes1 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult1.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes1.getStatus().name());

        // first user offers 2nd ride
        ServiceRequest<Ride> offerRideRequest2 = createOfferRideReq(addUserRes1.getResult(), "Chandigarh",
                3, "Simla", addVehicleRes2.getResult()
        );
        MvcResult offerRideResult2 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest2.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes2 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes2.getStatus().name());

        // add second user
        ServiceRequest<User> addUserRequest2 = createUserReq("Rohani", "M", 36,
                generateMobileNo()
        );
        MvcResult addUserResult2 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest2.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes2 = ObjectMapperFactory.getMapper().readValue(
                addUserResult2.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes2.getStatus().name());

        // second user adds 1st vehicle
        ServiceRequest<Vehicle> addVehicleRequest3 = createVehicleReq(addUserRes2.getResult(),
                "Rohani","Honda", generateVehicleNo()
        );
        MvcResult addVehicleResult3 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest3.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes3 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes3.getStatus().name());

        // second user adds 2nd vehicle
        ServiceRequest<Vehicle> addVehicleRequest4 = createVehicleReq(addUserRes2.getResult(),
                "Rohani","Apache", generateVehicleNo()
        );
        MvcResult addVehicleResult4 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest4.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes4 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes4.getStatus().name());

        // second user adds 3rd vehicle
        ServiceRequest<Vehicle> addVehicleRequest5 = createVehicleReq(addUserRes2.getResult(),
                "Rohani","Mercedes", generateVehicleNo()
        );
        MvcResult addVehicleResult5 = mvc.perform(post("/v1/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addVehicleRequest5.toString())
        ).andReturn();
        ServiceResponse<Vehicle> addVehicleRes5 = ObjectMapperFactory.getMapper().readValue(
                addVehicleResult5.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Vehicle> >() {}
        );
        assertEquals("SUCCESS", addVehicleRes5.getStatus().name());

        // second user offers 1st ride
        ServiceRequest<Ride> offerRideRequest3 = createOfferRideReq(addUserRes2.getResult(), "Delhi",
                5, "Lucknow", addVehicleRes3.getResult()
        );
        MvcResult offerRideResult3 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes3 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes3.getStatus().name());

        // second user offers 2nd ride
        ServiceRequest<Ride> offerRideRequest4 = createOfferRideReq(addUserRes2.getResult(), "Simla",
                2, "Lucknow", addVehicleRes4.getResult()
        );
        MvcResult offerRideResult4 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest4.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes4 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult4.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes4.getStatus().name());

        // second user offers 3rd ride
        ServiceRequest<Ride> offerRideRequest5 = createOfferRideReq(addUserRes2.getResult(), "Simla",
                3, "Dehradun", addVehicleRes5.getResult()
        );
        MvcResult offerRideResult5 = mvc.perform(post("/v1/rides/offer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerRideRequest5.toString())
        ).andReturn();
        ServiceResponse<Ride> offerRideRes5 = ObjectMapperFactory.getMapper().readValue(
                offerRideResult5.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Ride> >() {}
        );
        assertEquals("SUCCESS", offerRideRes5.getStatus().name());

        // add third user
        ServiceRequest<User> addUserRequest3 = createUserReq("Shivam", "M", 26,
                generateMobileNo()
        );
        MvcResult addUserResult3 = mvc.perform(post("/v1/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addUserRequest3.toString())
        ).andReturn();
        ServiceResponse<User> addUserRes3 = ObjectMapperFactory.getMapper().readValue(
                addUserResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<User> >() {}
        );
        assertEquals("SUCCESS", addUserRes3.getStatus().name());

        // third user searches for a ride
        ServiceRequest<Ride> searchRideRequest3 = createSearchRideReq(addUserRes3.getResult(),
                "Chandigarh", "Agra", 3, "Honda"
        );
        MvcResult searchRideResult3 = mvc.perform(get("/v1/rides/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchRideRequest3.toString())
        ).andReturn();
        ServiceResponse<Rides> searchRideRes3 = ObjectMapperFactory.getMapper().readValue(
                searchRideResult3.getResponse().getContentAsString(),
                new TypeReference<ServiceResponse<Rides> >() {}
        );
        assertEquals("FAIL", searchRideRes3.getStatus().name());

        // print ride statistics
        mvc.perform(get("/v1/rides/stats")).andReturn();
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
        ride.setOrigin(origin);
        ride.setDestination(dest);
        ride.setAvailableSeats(availableSeats);
        ride.setUserId(user.getId());
        ride.setSelectionStrategy(strategy);
        ServiceRequest<Ride> createRideReq = new ServiceRequest<>();
        createRideReq.setPayload(ride);
        return createRideReq;
    }

    private ServiceRequest<Ride> createOfferRideReq(User user, String origin, Integer availableSeats,
                                                    String destination, Vehicle vehicle
    ) {

        Ride ride = new Ride();
        ride.setOrigin(origin);
        ride.setDestination(destination);
        ride.setAvailableSeats(availableSeats);
        ride.setVehicleId(vehicle.getId());
        ride.setUserId(user.getId());
        ServiceRequest<Ride> createRideReq = new ServiceRequest<>();
        createRideReq.setPayload(ride);
        return createRideReq;
    }

    private ServiceRequest<Vehicle> createVehicleReq(User user, String vehicleOwner, String vehicleModel,
                                                     String vehicleNo
    ) {

        Vehicle vehicle = new Vehicle(vehicleOwner, vehicleModel, vehicleNo);
        vehicle.setUserId(user.getId());
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
        ride.setAvailableSeats(input.getAvailableSeats());
        ServiceRequest<Ride> selectRideReq = new ServiceRequest<>();
        selectRideReq.setPayload(ride);
        return selectRideReq;
    }

    private String generateMobileNo() {

        String mobileNo = "96486" + ThreadLocalRandom.current().nextInt(10000, 99999);
        return mobileNo;
    }

    private String generateVehicleNo() {

        String vehicleNo = "ST-" + ThreadLocalRandom.current().nextInt(10000, 99999);
        return vehicleNo;
    }
}
