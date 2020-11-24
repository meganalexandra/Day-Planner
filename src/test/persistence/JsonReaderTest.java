package persistence;

import exceptions.EmptyNameException;
import exceptions.InvalidTimeException;
import model.Event;
import model.Day;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// sourced from https://github.com/stleary/JSON-java.git
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Day day = reader.read();
            fail("IOException expected");
        } catch (IOException | InvalidTimeException | EmptyNameException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDay() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDay.json");
        try {
            Day day = reader.read();
            assertEquals("General Date", day.getDate());
            assertEquals(0, day.numberOfEvents());
        } catch (IOException | InvalidTimeException | EmptyNameException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDay() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDay.json");
        try {
            Day day = reader.read();
            assertEquals("General Date", day.getDate());
            List<Event> listOfEvents = day.getEvents();
            assertEquals(2, day.numberOfEvents());
            checkEvent("vote","lougheed mall", 1200, "bring ID", listOfEvents.get(0));
            checkEvent("buy toilet paper","walmart", 1230, "bring reusable bag", listOfEvents.get(1));
        } catch (IOException | InvalidTimeException | EmptyNameException e) {
            fail("Couldn't read from file");
        }
    }
}