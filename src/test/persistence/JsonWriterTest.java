package persistence;

import model.Trip;
import model.TripList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//code influenced by the JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            TripList tl = new TripList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            TripList tl = new TripList();
            tl.addTrip(new Trip("YVR-JFK 10/25/23 @11am, JFK-YVR 10/30/23 @2pm", 10,
                    "Moxy NYC Times Square, Motto by Hilton New York City Chelsea",
                    "Bronxville, Yorkville"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(tl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            tl = reader.read();
            List<Trip> trips = tl.getTrips();
            checkTrip("YVR-JFK 10/25/23 @11am, JFK-YVR 10/30/23 @2pm", 10,
                    "Moxy NYC Times Square, Motto by Hilton New York City Chelsea",
                    "Bronxville, Yorkville",trips.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
