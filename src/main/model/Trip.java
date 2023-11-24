package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents a trip having flights, number of days the trip is, hotels, and destinations.
public class Trip implements Writable {
    private int tripLength; //amount of days that the trip will be
    private final String flights; //list of the flights
    private final String hotels; //list of the name(s) of the hotel(s)
    private final String destinations; //list of the destination(s)
    private String review = ""; //string of the post-travel review as an empty string, so it is never null

    //REQUIRES: all the parameters to be non-null including tripLength > 0, and to be the proper type
    //EFFECTS: constructs a trip that has a list of flights, an amount of days that the trip will be, a list of hotel(s)
    //and a list of destination(s)
    public Trip(String flights, int tripLength, String hotels, String destinations) {
        this.flights = flights;
        this.tripLength = tripLength;
        this.hotels = hotels;
        this.destinations = destinations;
    }

    public void setTripLength(int tripLength) {
        this.tripLength = tripLength;
    }

    public int getTripLength() {
        return tripLength;
    }

    public String getFlights() {
        return flights;
    }

    public String getHotels() {
        return hotels;
    }

    public String getDestinations() {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("flights", flights);
        json.put("trip length", tripLength);
        json.put("hotels", hotels);
        json.put("destinations", destinations);
        json.put("review", review);
        return json;
    }

}
