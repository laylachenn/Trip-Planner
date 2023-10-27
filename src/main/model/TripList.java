package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a list of trips where you can modify the list by adding trips, and create reviews for a trip in the list
public class TripList implements Writable {
    private final List<Trip> trips;

    //EFFECTS: constructs an array list that will hold trips
    public TripList() {
        trips = new ArrayList<>();
    }

    //REQUIRES: a trip to have information in all the parameters
    //MODIFIES: this
    //EFFECTS: adds a trip to the list of trips
    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public List<Trip> getTrips() {
        return trips;
    }

    //REQUIRES: if the trip list contains a trip then it will add the review to the trip information and return true,
    // if not it will return false
    public boolean createReview(Trip trip, String review) {
        if (trips.contains(trip)) {
            trip.setReview(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("trip list", tripListToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray tripListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Trip t : trips) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
