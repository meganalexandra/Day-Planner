package persistence;

import model.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

// sourced from https://github.com/stleary/JSON-java.git
public class JsonTest {
    protected void checkEvent(String name, String location, int time, String reminders, Event event) {
        assertEquals(name, event.getName());
        assertEquals(location, event.getLocation());
        assertEquals(time, event.getTime());
        assertEquals(reminders, event.getReminder());
    }


}
