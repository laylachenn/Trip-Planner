package model;

import java.util.ArrayList;
import java.util.List;

public class TripList {
    private List<Trip> trips;

    public void TripList() {
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
        } else {
            System.out.println("The trip is not found in the list.");
        }
    }

}
