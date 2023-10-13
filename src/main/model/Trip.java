package model;

import java.util.List;

//Represents a trip having flights, number of days the trip is, hotels, and destinations.
public class Trip {
    private int tripLength; //amount of days that the trip will be
    private final List<String> flights; //list of the flights
    private final List<String> hotels; //list of the name(s) of the hotel(s)
    private final List<String> destinations; //list of the destination(s)
    private String review = ""; //string of the post-travel review as an empty string so it is never nul

    //REQUIRES: all the parameters to be non-null including tripLength > 0, and to be the proper type
    //EFFECTS: constructs a trip that has a list of flights, an amount of days that the trip will be, a list of hotel(s)
    //and a list of destination(s)
    public Trip(List<String> flights, int tripLength, List<String> hotels, List<String> destinations) {
        this.flights = flights;
        this.tripLength = tripLength;
        this.hotels = hotels;
        this.destinations = destinations;
    }

    public void setTripLength(int tripLength) {
        this.tripLength = tripLength;
    }

    //MODIFIES: this
    //EFFECTS: adds a flight to the list of flights
    public void addFlight(String flight) {
        flights.add(flight);
    }

    //MODIFIES: this
    //EFFECTS: adds a hotel to the list of hotels
    public void addHotel(String hotel) {
        hotels.add(hotel);
    }

    //MODIFIES: this
    //EFFECTS: adds a destination to the list of destinations
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    //EFFECTS: returns a string representation of the trip information
    //(inspired by/sourced from Teller application in the Account class)
    @Override
    public String toString() {
        return "Trip Information:\n" + "Flight Information: " + flights + "\n"
                + "Trip Length: " + tripLength + "\n" + "Hotels: " + hotels + "\n"
                + "Destinations: " + destinations;

    }

}
