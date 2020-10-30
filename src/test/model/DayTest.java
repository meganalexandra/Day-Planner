package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DayTest {
    private Day dayTest;
    private Event event1;
    private Event event2;

    @BeforeEach
    void runBefore() {
        dayTest = new Day("Sept 10");
        event1 = new Event("voting", "Lougheed Mall",1200 , "bring ID and voting card");
        event2 = new Event("club interview", "zoom", 1600, "https://us02web.zoom.us/u/kbt7MwD02n");
    }


    @Test
    void testDayTest() {
        Day createDay = new Day("Sept 10");
        assertEquals("Sept 10", createDay.getDate());
        assertEquals(0, createDay.numberOfEvents());
    }

    @Test
    void testAdd() {
        // initialize dayTest with 2 entries
        dayTest.addEvent(event1);
        dayTest.addEvent(event2);

        assertEquals(event1, dayTest.getEvent(0));
        assertEquals(event2, dayTest.getEvent(1));
    }

    @Test
    void testRemove() {
        // initialize dayTest with 2 entries
        dayTest.addEvent(event1);
        dayTest.addEvent(event2);

        // remove event1
        dayTest.removeEvent(event1);

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
        dayTest.addEvent(event1);

        assertTrue(dayTest.checkDuplicate(event1));
    }

    @Test
    void testCheckDuplicateFalse() {
        // initialize dayTest with event1
        dayTest.addEvent(event1);

        assertFalse(dayTest.checkDuplicate(event2));
    }

    @Test
    void testGetListOfEvents() {
        // initialize dayTest with event1 and event2
        dayTest.addEvent(event1);
        dayTest.addEvent(event2);

        assertEquals(("voting at 1200\nclub interview at 1600\n"), dayTest.getListOfEvents());
    }
}

