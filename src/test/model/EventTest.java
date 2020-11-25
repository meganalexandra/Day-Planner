package model;

import exceptions.EmptyNameException;
import exceptions.InvalidTimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    private Event event1;

    // test Event constructor
    @Test
    void testEventWithoutOptionalFields() {
        try {
            event1 = new Event ("voting", "", 1300, "");
        } catch (InvalidTimeException e) {
            fail("InvalidTimeException should not have been thrown");
        } catch (EmptyNameException e) {
            fail("EmptyNameException should not have been thrown");
        }
        assertEquals("voting", event1.getName());
        assertEquals(1300, event1.getTime());
    }

    @Test
    void testEventValidTime() {
        try {
            event1 = new Event("voting", "Lougheed Mall", 1200, "bring ID and voting card");
            // expected
        } catch (InvalidTimeException e) {
            fail("InvalidTimeException should not have been thrown");
        } catch (EmptyNameException e) {
            fail("EmptyNameException should not have been thrown");
        }
        assertEquals("voting", event1.getName());
        assertEquals("Lougheed Mall", event1.getLocation());
        assertEquals(1200, event1.getTime());
        assertEquals("bring ID and voting card", event1.getReminder());
    }

    @Test
    void testEventInvalidTimeAboveRange() {
        try {
            Event event2 = new Event("concert", "Rogers Arena", 2500, "bring ticket");
            fail("InvalidTimeException should have been thrown");
        } catch (InvalidTimeException e) {
            System.out.println("2500 is an invalid time");
        } catch (EmptyNameException e) {
            fail("InvalidTimeException should have been thrown");
        }
    }

    @Test
    void testEventInvalidTimeBelowRange() {
        try {
            Event event3 = new Event("concert", "Rogers Arena", -500, "bring ticket");
            fail("InvalidTimeException should have been thrown");
        } catch (InvalidTimeException e) {
            System.out.println("-500 is an invalid time");
        } catch (EmptyNameException e) {
            fail("InvalidTimeException should have been thrown");
        }
    }

    @Test
    void testEventEmptyNameField() {
        try {
            Event event4 = new Event("", "dentist appointment", 1400, "bring retainer");
        } catch (InvalidTimeException e) {
            fail("EmptyNameException should have been thrown");
        } catch (EmptyNameException e) {
            // expected
        }
    }

    @Test
    void testGetEventDetails() {
        try {
            event1 = new Event("voting", "Lougheed Mall", 1200, "bring ID and voting card");
        } catch (InvalidTimeException e) {
            fail("InvalidTimeException should not have been thrown");
        } catch (EmptyNameException e) {
            fail("EmptyNameException should not have been thrown");
        }

        assertEquals("\n1200 : voting at Lougheed Mall (reminder:bring ID and voting card)\n",
                event1.getEventDetails());
    }

    @Test
    void testGetEventDetailsNoLocation() {
        try {
            event1 = new Event("voting", "", 1200, "bring ID and voting card");
        } catch (InvalidTimeException e) {
            fail("InvalidTimeException should not have been thrown");
        } catch (EmptyNameException e) {
            fail("EmptyNameException should not have been thrown");
        }

        assertEquals("\n1200 : voting (reminder:bring ID and voting card)\n",
                event1.getEventDetails());
    }

    @Test
    void testGetEventDetailsNoReminder() {
        try {
            event1 = new Event("voting", "Lougheed Mall", 1200, "");
        } catch (InvalidTimeException e) {
            fail("InvalidTimeException should not have been thrown");
        } catch (EmptyNameException e) {
            fail("EmptyNameException should not have been thrown");
        }

        assertEquals("\n1200 : voting at Lougheed Mall (reminder:N/A)\n",
                event1.getEventDetails());
    }
}
