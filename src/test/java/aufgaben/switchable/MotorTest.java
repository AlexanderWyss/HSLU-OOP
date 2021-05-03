package aufgaben.switchable;

import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MotorTest {
    @Test
    void addPropertyChangeListener_rpmChanged_listenerCalled() {
        Motor motor = new Motor();
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        motor.addPropertyChangeListener(listener);

        motor.setRpm(5000);

        PropertyChangeEvent event = listener.getEvent();
        assertEquals(0, event.getOldValue());
        assertEquals(5000, event.getNewValue());
        assertEquals("rpm", event.getPropertyName());
        assertEquals(motor, event.getSource());
    }

    @Test
    void motorWithPropertyChangeListener_removePropertyChangeListener_listerRemoved() {
        Motor motor = new Motor();
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        motor.addPropertyChangeListener(listener);
        motor.setRpm(5000);

        motor.removePropertyChangeListener(listener);
        motor.setRpm(2000);

        List<PropertyChangeEvent> events = listener.getEvents();
        assertEquals(1, events.size());
    }

    @Test
    void addPropertyChangeListener_rpmChangedTwice_listenerCalledTwice() {
        Motor motor = new Motor();
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        motor.addPropertyChangeListener(listener);

        motor.setRpm(5000);
        motor.setRpm(2000);

        List<PropertyChangeEvent> events = listener.getEvents();
        assertEquals(2, events.size());
        PropertyChangeEvent event1 = events.get(0);
        assertEquals(0, event1.getOldValue());
        assertEquals(5000, event1.getNewValue());
        PropertyChangeEvent event2 = events.get(1);
        assertEquals(5000, event2.getOldValue());
        assertEquals(2000, event2.getNewValue());
    }

    @Test
    void add2PropertyChangeListener_rpmChanged_bothListenerCalled() {
        Motor motor = new Motor();
        TestPropertyChangeListener listener1 = new TestPropertyChangeListener();
        motor.addPropertyChangeListener(listener1);
        TestPropertyChangeListener listener2 = new TestPropertyChangeListener();
        motor.addPropertyChangeListener(listener2);

        motor.setRpm(5000);

        PropertyChangeEvent event1 = listener1.getEvent();
        assertEquals(0, event1.getOldValue());
        assertEquals(5000, event1.getNewValue());
        PropertyChangeEvent event2 = listener2.getEvent();
        assertEquals(0, event2.getOldValue());
        assertEquals(5000, event2.getNewValue());
    }

    @Test
    void addPropertyChangeListener_switchOn_listenerCalled() {
        Motor motor = new Motor();
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        motor.addPropertyChangeListener(listener);

        motor.switchOn();

        PropertyChangeEvent event = listener.getEvent();
        assertEquals(0, event.getOldValue());
        assertEquals(1000, event.getNewValue());
        assertEquals("rpm", event.getPropertyName());
        assertEquals(motor, event.getSource());
    }

    @Test
    void addPropertyChangeListener_switchOff_listenerCalled() {
        Motor motor = new Motor();
        motor.switchOn();
        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        motor.addPropertyChangeListener(listener);

        motor.switchOff();

        PropertyChangeEvent event = listener.getEvent();
        assertEquals(1000, event.getOldValue());
        assertEquals(0, event.getNewValue());
        assertEquals("rpm", event.getPropertyName());
        assertEquals(motor, event.getSource());
    }

    @Test
    void addPropertyChangeListenerNull_switchOn_noException() {
        Motor motor = new Motor();
        motor.addPropertyChangeListener(null);

        assertDoesNotThrow(motor::switchOn);
    }

    @Test
    void justTestItAlready() {
        Motor motor = new Motor();
        assertFalse(motor.isSwitchedOn());
        assertTrue(motor.isSwitchedOff());
        motor.switchOn();
        assertTrue(motor.isSwitchedOn());
        assertFalse(motor.isSwitchedOff());
        motor.switchOff();
        assertFalse(motor.isSwitchedOn());
        assertTrue(motor.isSwitchedOff());
    }

    private class TestPropertyChangeListener implements PropertyChangeListener {
        private List<PropertyChangeEvent> events = new ArrayList<>();

        @Override
        public void propertyChange(PropertyChangeEvent event) {
            events.add(event);
        }

        public List<PropertyChangeEvent> getEvents() {
            return events;
        }

        public PropertyChangeEvent getEvent() {
            assertEquals(1, events.size());
            return events.get(0);
        }
    }
}