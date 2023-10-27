package persistence;

import model.Trip;
import model.TripList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

//code influenced by the JsonSerializationDemo

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads trip list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TripList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTripList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses trip list from JSON object and returns it
    private TripList parseTripList(JSONObject jsonObject) {
        TripList tl = new TripList();
        addTrips(tl, jsonObject);
        return tl;
    }

    // MODIFIES: this
    // EFFECTS: parses trips from JSON object and adds them to trip list
    private void addTrips(TripList tl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("trip list");
        for (Object json : jsonArray) {
            JSONObject nextTrip = (JSONObject) json;
            addTrip(tl, nextTrip);
        }
    }

    // MODIFIES: this
    // EFFECTS: parses trip from JSON object and adds it to trip list
    private void addTrip(TripList tl, JSONObject jsonObject) {
        List<String> flights = (List<String>) jsonObject.getJSONObject("flights");
        int tripLength = jsonObject.getInt("trip length");
        List<String> hotels = (List<String>) jsonObject.getJSONObject("hotels");
        List<String> destinations = (List<String>) jsonObject.getJSONObject("destinations");
        Trip trip = new Trip(flights, tripLength, hotels, destinations);
        tl.addTrip(trip);
    }
}