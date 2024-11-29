// Taken from:
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/blob/main/src/test/ca/ubc/cpsc210/alarm/test/EventLogTest.java

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the EventLog class
 */
public class EventLogTest {
    private Event event1;
    private Event event2;
    private Event event3;

    @BeforeEach
    public void loadEvents() {
        event1 = new Event("Added a book to a library");
        event2 = new Event("Added a library");
        event3 = new Event("Removed a library");
        EventLog eventLog = EventLog.getInstance();
        eventLog.logEvent(event1);
        eventLog.logEvent(event2);
        eventLog.logEvent(event3);
    }

    @Test
    public void testLogEvent() {
        List<Event> eventList = new ArrayList<Event>();

        EventLog eventLog = EventLog.getInstance();
        for (Event event : eventLog) {
            eventList.add(event);
        }

        assertTrue(eventList.contains(event1));
        assertTrue(eventList.contains(event2));
        assertTrue(eventList.contains(event3));
    }

    @Test
    public void testClear() {
        EventLog eventLog = EventLog.getInstance();
        eventLog.clear();
        Iterator<Event> itr = eventLog.iterator();
        assertTrue(itr.hasNext()); // After log is cleared, the clear log event is added
        assertEquals("Event log cleared.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }
}