package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TripListTest {
    private TripList testTripList;
    private Trip trip1;
    private Trip trip2;

    @BeforeEach
    void runBefore() {
        testTripList = new TripList();
        trip1 = new Trip(new ArrayList<>(), 0, new ArrayList<>(), new ArrayList<>());
        trip2 = new Trip(new ArrayList<>(), 0, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void testAddTrip() {
        testTripList.addTrip(trip1);
        testTripList.addTrip(trip2);
        List<Trip> trips = testTripList.getTrips();

        assertTrue(trips.contains(trip1));
        assertTrue(trips.contains(trip2));
    }

    @Test
    public void testCreateReviews() {
        testTripList.addTrip(trip1);
        testTripList.addTrip(trip2);
        testTripList.createReview(trip1,
                "Epic trip. A little cold so bring a jacket and budget to spend more than you think.");
        testTripList.createReview(trip2,
                "Not a great experience. Wouldn't come back as the whole city is full of tourists.");

        assertEquals("Epic trip. A little cold so bring a jacket and budget to spend more than you think.",
                trip1.getReview());
        assertEquals("Not a great experience. Wouldn't come back as the whole city is full of tourists.",
                trip2.getReview());

    }
}

