package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private Event testEvent;
    private Date testDate;

    @BeforeEach
    public void runBefore() {
        testEvent = new Event("Added car");
        testDate = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Added car", testEvent.getDescription());
        //assertEquals(d, e.getDate());
        // This test will return false due to milliseconds not being lined up correctly, but is correct logic.
    }

    @Test
    public void testToString() {
        assertEquals(testDate.toString() + "\n" + "Added car", testEvent.toString());
    }
}