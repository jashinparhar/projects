package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EventLogTest {
    private Event testEvent1;
    private Event testEvent2;
    private Event testEvent3;

    @BeforeEach
    public void loadEvents() {
        testEvent1 = new Event("Car added");
        testEvent2 = new Event("Car removed");
        testEvent3 = new Event("Car added");
        EventLog testEventLog = EventLog.getInstance();
        testEventLog.logEvent(testEvent1);
        testEventLog.logEvent(testEvent2);
        testEventLog.logEvent(testEvent3);
    }

    @Test
    public void testLogEvent() {
        List<Event> testListOfEvents = new ArrayList<Event>();

        EventLog testEventLog = EventLog.getInstance();
        for (Event next : testEventLog) {
            testListOfEvents.add(next);
        }
        assertTrue(testListOfEvents.contains(testEvent1));
        assertTrue(testListOfEvents.contains(testEvent2));
        assertTrue(testListOfEvents.contains(testEvent3));
    }

    @Test
    public void testClear() {
        EventLog testEventLog = EventLog.getInstance();
        testEventLog.clear();
        Iterator<Event> testIterator = testEventLog.iterator();
        assertTrue(testIterator.hasNext());
        assertEquals("Event log cleared.", testIterator.next().getDescription());
        assertFalse(testIterator.hasNext());
    }
}
