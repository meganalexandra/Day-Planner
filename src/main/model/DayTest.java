package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class DayTest {
    private Day dayTest;
    private Event event1;
    private Event event2;

    @BeforeEach
    void runBefore() {
        dayTest = new Day();
        event1 = new Event("voting", "Lougheed Mall", 12, "bring ID and voting card");
        event2 = new Event("club interview", "zoom", 16, "https://us02web.zoom.us/u/kbt7MwD02n");
    }

    @Test
    void testAdd() {
        // initialize dayTest with 2 entries
        dayTest.add(event1);
        dayTest.add(event2);

        assertEquals(event1, dayTest.getEvent(0));
        assertEquals(event2, dayTest.getEvent(1));
    }

    @Test
    void testRemove() {
        // initialize dayTest with 2 entries
        dayTest.add(event1);
        dayTest.add(event2);

        // remove event1
        dayTest.remove(event1);

        assertEquals(1, dayTest.numberOfEvents());
        assertEquals(event2, dayTest.getEvent(0));
    }

    @Test
    void testNumberOfEventsNull() {
        assertEquals(0, dayTest.numberOfEvents());
    }

    @Test
    void testNumberOfEvents() {
        // initialize dayTest with 2 entries
        dayTest.addEvent(event1);
        dayTest.addEvent(event2);

        assertEquals(2,dayTest.numberOfEvents());
    }

    @Test
    void testCheckDuplicateTrue() {
        // initialize dayTest with event1
        dayTest.add(event1);

        assertTrue(dayTest.checkDuplicate(event1));
    }

    @Test
    void testCheckDuplicateFalse() {
        // initialize dayTest with event1
        dayTest.add(event1);

        assertFalse(dayTest.checkDuplicate(event2));
    }

    @Test
    void testAddEventSuccess() {
        // initialize dayTest with event1
        dayTest.add(event1);

        assertTrue(dayTest.addEvent(event2));
        assertEquals(event2, dayTest.getEvent(1));
    }

    @Test
    void testAddEventFailure() {
        // initialize dayTest with event1
        dayTest.add(event1);

        assertFalse(dayTest.addEvent(event1));
        assertEquals(1, dayTest.numberOfEvents());
    }
}
