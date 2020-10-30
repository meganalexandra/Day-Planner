package persistence;

import model.Event;
import model.Day;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Day day = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDay() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDay.json");
        try {
            Day day = reader.read();
            assertEquals("October 31", day.getDate());
            assertEquals(0, day.numberOfEvents());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDay() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDay.json");
        try {
            Day day = reader.read();
            assertEquals("October 31", day.getDate());
            List<Event> listOfEvents = day.getEvents();
            assertEquals(2, day.numberOfEvents());
            checkEvent("vote","lougheed mall", 1200, "bring ID", listOfEvents.get(0));
            checkEvent("buy toilet paper","walmart", 1230, "bring reusable bag", listOfEvents.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}