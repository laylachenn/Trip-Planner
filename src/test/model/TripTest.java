package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//inspired by Teller application in the AccountTest class
//source asList: https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/

class TripTest {
    private Trip testTrip;

    @BeforeEach
    void runBefore() {
        String flightNames = "YVR-JFK 10/25/23 @11am, JFK-YVR 10/30/23 @2pm";
        String hotelNames = "Moxy NYC Times Square, Motto by Hilton New York City Chelsea";
        String destinationNames = "Bronxville, Yorkville";
        testTrip = new Trip(flightNames,5, hotelNames, destinationNames);
    }

    @Test
    public void testConstructor() {
        String expectedFlights = "YVR-JFK 10/25/23 @11am, JFK-YVR 10/30/23 @2pm";
        String expectedHotels = "Moxy NYC Times Square, Motto by Hilton New York City Chelsea";
        String expectedDestinations = "Bronxville, Yorkville";

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
    public void testToString() {
        String expectedString = "Trip Information:\nFlight Information: YVR-JFK 10/25/23 @11am, JFK-YVR 10/30/23 @2pm" +
                "\nTrip Length: 5\nHotels: Moxy NYC Times Square, Motto by Hilton New York City Chelsea\n" +
                "Destinations: Bronxville, Yorkville";

        assertEquals(expectedString, testTrip.toString());
    }
}