package persistence;

import model.Event;
import model.Day;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Day day = new Day("October 31");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Day day = new Day("October 31");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDay.json");
            writer.open();
            writer.write(day);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDay.json");
            day = reader.read();
            assertEquals("October 31", day.getDate());
            assertEquals(0, day.numberOfEvents());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDay() {
        try {
            Day day = new Day("October 31");
            day.addEvent(new Event("vote","lougheed mall", 1200, "bring ID"));
            day.addEvent(new Event("buy toilet paper","walmart", 1230, "bring reusable bag"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDay.json");
            writer.open();
            writer.write(day);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDay.json");
            day = reader.read();
            assertEquals("October 31", day.getDate());
            List<Event> listOfEvents = day.getEvents();
            assertEquals(2, day.numberOfEvents());
            checkEvent("vote","lougheed mall", 1200, "bring ID", listOfEvents.get(0));
            checkEvent("buy toilet paper","walmart", 1230, "bring reusable bag", listOfEvents.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
