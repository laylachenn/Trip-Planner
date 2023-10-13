package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//inspired by Teller application in the AccountTest class
//source asList: https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/

class TripTest {
    private Trip testTrip;

    @BeforeEach
    void runBefore() {
        List<String> flightNames = Arrays.asList("YVR-JFK 10/25/23 @11am", "JFK-YVR 10/30/23 @2pm");
        List<String> hotelNames = Arrays.asList("Moxy NYC Times Square", "Motto by Hilton New York City Chelsea");
        List<String> destinationNames = Arrays.asList("Bronxville", "Yorkville");
        testTrip = new Trip(flightNames,5, hotelNames, destinationNames);
    }

    @Test
    public void testConstructor() {
        List<String> expectedFlights = new ArrayList<>();
        expectedFlights.add("YVR-JFK 10/25/23 @11am");
        expectedFlights.add("JFK-YVR 10/30/23 @2pm");
        List<String> expectedHotels = new ArrayList<>();
        expectedHotels.add("Moxy NYC Times Square");
        expectedHotels.add("Motto by Hilton New York City Chelsea");
        List<String> expectedDestinations = new ArrayList<>();
        expectedDestinations.add("Bronxville");
        expectedDestinations.add("Yorkville");

        assertEquals(5, testTrip.getTripLength());
        assertEquals(expectedFlights, testTrip.getFlights());
        assertEquals(expectedHotels, testTrip.getHotels());
        assertEquals(expectedDestinations, testTrip.getDestinations());
    }
}