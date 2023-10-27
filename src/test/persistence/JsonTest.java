package persistence;

import model.Trip;

import static org.junit.jupiter.api.Assertions.assertEquals;

//code influenced by the JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkTrip(String flights, int tripLength, String hotels, String destinations, Trip trip) {
        assertEquals(flights, trip.getFlights());
        assertEquals(tripLength, trip.getTripLength());
        assertEquals(hotels, trip.getHotels());
        assertEquals(destinations, trip.getDestinations());
    }
}
