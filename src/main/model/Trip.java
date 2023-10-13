package model;

import java.util.ArrayList;
import java.util.List;

//Represents a trip having flights, number of days the trip is, hotels, and destinations.
public class Trip {
    private int tripLength; //amount of days that the trip will be
    private final List<String> flights; //list of the flights
    private final List<String> hotels; //list of the name(s) of the hotel(s)
    private final List<String> destinations; //list of the destination(s)

    public Trip(List<String> flights, int tripLength, List<String> hotels, List<String> destinations) {
        this.flights = flights;
        this.tripLength = tripLength;
        this.hotels = hotels;
        this.destinations = destinations;
    }

    public void setTripLength(int tripLength) {
        this.tripLength = tripLength;
    }

    public void addFlight(String flight) {
        flights.add(flight);
    }

    public void addHotel(String hotel) {
        hotels.add(hotel);
    }

    public void addDestination(String destination) {
        destinations.add(destination);
    }

    public int getTripLength() {
        return tripLength;
    }

    public List<String> getFlights() {
        return flights;
    }

    public List<String> getHotels() {
        return hotels;
    }

    public List<String> getDestinations() {
        return destinations;
    }

}