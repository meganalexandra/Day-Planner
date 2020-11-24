package persistence;

import exceptions.InvalidTimeException;
import model.Event;
import model.Day;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// sourced from https://github.com/stleary/JSON-java.git
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Day day = new Day("General Date");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDay() {
        try {
            Day day = new Day("General Date");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDay.json");
            writer.open();
            writer.write(day);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDay.json");
            day = reader.read();
            assertEquals("General Date", day.getDate());
            assertEquals(0, day.numberOfEvents());
        } catch (IOException | InvalidTimeException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDay() {
        try {
            Day day = new Day("General Date");
            day.addEvent(new Event("vote","lougheed mall", 1200, "bring ID"));
            day.addEvent(new Event("buy toilet paper","walmart", 1230, "bring reusable bag"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDay.json");
            writer.open();
            writer.write(day);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDay.json");
            day = reader.read();
            assertEquals("General Date", day.getDate());
            List<Event> listOfEvents = day.getEvents();
            assertEquals(2, day.numberOfEvents());
            checkEvent("vote","lougheed mall", 1200, "bring ID", listOfEvents.get(0));
            checkEvent("buy toilet paper","walmart", 1230, "bring reusable bag", listOfEvents.get(1));

        } catch (IOException | InvalidTimeException e) {
            fail("Exception should not have been thrown");
        }
    }
}
