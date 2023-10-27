package persistence;

import model.Trip;
import model.TripList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//heavily influenced by JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TripList tl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTripList.json");
        try {
            TripList tl = reader.read();
            List<Trip> trips = tl.getTrips();
            assertEquals(1, trips.size());
            checkTrip("YVR-JFK 10/25/23 @11am, JFK-YVR 10/30/23 @2pm", 5,
                    "Moxy NYC Times Square, Motto by Hilton New York City Chelsea",
                    "Bronxville, Yorkville", trips.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
