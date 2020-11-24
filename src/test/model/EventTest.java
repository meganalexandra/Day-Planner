package model;

import exceptions.InvalidTimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    private Event event1;

    // test Event constructor
    @Test
    void testEventValidTime() {
        try {
            event1 = new Event("voting", "Lougheed Mall", 1200, "bring ID and voting card");
            // expected
        } catch (InvalidTimeException e) {
            fail("InvalidTimeException should not have been thrown");
        }
        assertEquals("voting", event1.getName());
        assertEquals("Lougheed Mall", event1.getLocation());
        assertEquals(1200, event1.getTime());
        assertEquals("bring ID and voting card", event1.getReminder());
    }

    @Test
    void testEventInvalidTime() {
        try {
            Event event2 = new Event("concert", "Rogers Arena", 2500, "bring ticket");
            fail("InvalidTimeException should have been thrown");
        } catch (InvalidTimeException e) {
            System.out.println("2500 is an invalid time");
        }
    }


    @Test
    void testGetEventDetails() {
        try {
            event1 = new Event("voting", "Lougheed Mall", 1200, "bring ID and voting card");
        } catch (InvalidTimeException e) {
            fail("InvalidTimeException should not have been thrown");
        }

        assertEquals("\n1200 : voting at Lougheed Mall (reminder:bring ID and voting card)\n",
                event1.getEventDetails());
    }
}
