package model;

import java.util.ArrayList;
import java.util.List;

//Represents a list of trips where you can modify the list by adding trips, and create reviews for a trip in the list
public class TripList {
    private final List<Trip> trips;

    //EFFECTS: constructs an array list that will hold trips
    public TripList() {
        trips = new ArrayList<>();
    }

    //REQUIRES: a trip to have information in all of the parameters
    //MODIFIES: this
    //EFFECTS: adds a trip to the list of trips
    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public List<Trip> getTrips() {
        return trips;
    }

    //REQUIRES:
    public boolean createReview(Trip trip, String review) {
        if (trips.contains(trip)) {
            trip.setReview(review);
            return true;
        } else {
            return false;
        }
    }
}
