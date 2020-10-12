package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event testEntry;

    @BeforeEach
    void runBefore() {
        testEntry = new Event("voting", "Lougheed Mall", 12, "bring ID and voting card");
    }

    @Test
    void testConstructor () {
        assertEquals("voting", testEntry.getName());
        assertEquals("Lougheed Mall", testEntry.getLocation());
        assertEquals(12, testEntry.getTime());
        assertEquals("bring ID and voting card", testEntry.getReminders());
    }
}
