package model;

import java.util.ArrayList;
import java.util.List;

//Represents a list of trips where you can modify the list by adding trips and creating a review for a trip in the list
public class TripList {
    private final List<Trip> trips;

    public TripList() {
        trips = new ArrayList<>();
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void createReview(Trip trip, String review) {
        if (trips.contains(trip)) {
            trip.setReview(review);
        }
    }
}
