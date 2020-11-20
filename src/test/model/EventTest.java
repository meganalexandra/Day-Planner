package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event testEntry;

    @BeforeEach
    void runBefore() {
        testEntry = new Event("voting", "Lougheed Mall", 1200, "bring ID and voting card");
    }

    // test Event constructor
    @Test
    void testEvent() {
        assertEquals("voting", testEntry.getName());
        assertEquals("Lougheed Mall", testEntry.getLocation());
        assertEquals(1200, testEntry.getTime());
        assertEquals("bring ID and voting card", testEntry.getReminder());
    }

    @Test
    void testGetEventDetails() {
        assertEquals("\n1200 : voting at Lougheed Mall (reminder:bring ID and voting card)\n",
                testEntry.getEventDetails());
    }
}
