package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DayTest {
    private Day dayTest;
    private Event event1;
    private Event event2;
    private Event event3;

    @BeforeEach
    void runBefore() {
        dayTest = new Day("Sept 10");
        event1 = new Event("voting", "Lougheed Mall", 1200, "bring ID and voting card");
        event2 = new Event("club interview", "zoom", 1600, "https://us02web.zoom.us/u/kbt7MwD02n");
        event3 = new Event("orthodontist appointment", "Monarch Dental", 1300, "bring retainer");
    }


    @Test
    void testDayTest() {
        Day createDay = new Day("Sept 10");
        assertEquals("Sept 10", createDay.getDate());
        assertEquals(0, createDay.numberOfEvents());
    }

    @Test
    void testAddEmptyList() {
        dayTest.addEvent(event1);
        assertEquals(event1, dayTest.getEvent(0));
    }

    @Test
    void testAdd() {
        // initialize dayTest with 2 entries
        dayTest.addEvent(event1);
        dayTest.addEvent(event2);
        dayTest.addEvent(event3);
        System.out.println(dayTest);
        assertEquals(event1, dayTest.getEvent(0));
        assertEquals(event2, dayTest.getEvent(2));
        assertEquals(event3, dayTest.getEvent(3));
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

        assertEquals(2, dayTest.numberOfEvents());
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

        assertEquals(("\n1200 : voting at Lougheed Mall (reminder:bring ID and voting card)\n" +
                "\n1600 : club interview at zoom (reminder:https://us02web.zoom.us/u/kbt7MwD02n)\n"),
                dayTest.getListOfEventsDetails());
    }
}

