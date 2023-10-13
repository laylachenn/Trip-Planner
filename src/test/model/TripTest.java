package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void setTripLength() {
        testTrip.setTripLength(8);
        assertEquals(8,testTrip.getTripLength());
    }

    @Test
    public void testAddFlights() {
        testTrip = new Trip(new ArrayList<>(), 0, new ArrayList<>(), new ArrayList<>());
        testTrip.addFlight("YVR-JFK 10/25/23 @11am");
        testTrip.addFlight("JFK-YVR 10/30/23 @2pm");
        List<String> expectedFlights = new ArrayList<>();
        expectedFlights.add("YVR-JFK 10/25/23 @11am");
        expectedFlights.add("JFK-YVR 10/30/23 @2pm");

        assertEquals(expectedFlights, testTrip.getFlights());
    }

    @Test
    public void testAddHotels() {
        testTrip = new Trip(new ArrayList<>(), 0, new ArrayList<>(), new ArrayList<>());
        testTrip.addHotel("Moxy NYC Times Square");
        testTrip.addHotel("Motto by Hilton New York City Chelsea");
        List<String> expectedHotels = new ArrayList<>();
        expectedHotels.add("Moxy NYC Times Square");
        expectedHotels.add("Motto by Hilton New York City Chelsea");

        assertEquals(expectedHotels, testTrip.getHotels());
    }

    @Test
    public void testAddDestinations() {
        testTrip = new Trip(new ArrayList<>(), 0, new ArrayList<>(), new ArrayList<>());
        testTrip.addDestination("Bronxville");
        testTrip.addDestination("Yorkville");
        List<String> expectedDestinations = new ArrayList<>();
        expectedDestinations.add("Bronxville");
        expectedDestinations.add("Yorkville");

        assertEquals(expectedDestinations, testTrip.getDestinations());
    }

    @Test
    public void testToString() {
        String expectedString = "Trip Information:\nFlight Information: [YVR-JFK 10/25/23 @11am, JFK-YVR 10/30/23 @2pm]" +
                "\nTrip Length: 5\nHotels: [Moxy NYC Times Square, Motto by Hilton New York City Chelsea]\n" +
                "Destinations: [Bronxville, Yorkville]";

        assertEquals(expectedString, testTrip.toString());
    }
}