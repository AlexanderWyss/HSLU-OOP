package aufgaben.elements;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureHistoryTest {

    @Test
    void addListener_addTemp_maxAndMinEvent() {
        TemperatureHistory history = new TemperatureHistory();
        TestTemperatureHistoryListener listener = new TestTemperatureHistoryListener();
        history.addListener(listener);

        Temperature temp = Temperature.celsius(15);
        history.add(temp);

        assertEquals(2, listener.getEvents().size());
        TemperatureHistoryEvent maxEvent = listener.getEvents().get(0);
        assertEquals(temp, maxEvent.getMeasurePoint().getTemperature());
        assertEquals(TemperatureHistoryEventType.MAX, maxEvent.getType());
        assertEquals(history, maxEvent.getSource());
        TemperatureHistoryEvent minEvent = listener.getEvents().get(1);
        assertEquals(temp, minEvent.getMeasurePoint().getTemperature());
        assertEquals(TemperatureHistoryEventType.MIN, minEvent.getType());
        assertEquals(history, minEvent.getSource());
    }

    @Test
    void addListener_newMaxTemp_maxEvent() {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(10));
        TestTemperatureHistoryListener listener = new TestTemperatureHistoryListener();
        history.addListener(listener);

        Temperature temp = Temperature.celsius(15);
        history.add(temp);

        TemperatureHistoryEvent event = listener.getEvent();
        assertEquals(temp, event.getMeasurePoint().getTemperature());
        assertEquals(TemperatureHistoryEventType.MAX, event.getType());
        assertEquals(history, event.getSource());
    }

    @Test
    void addListener_tempAddedToHistoryBeforeListenerCall() {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(10));
        history.addListener(event -> assertEquals(2, history.getCount()));
        history.add(Temperature.celsius(20));
    }

    @Test
    void addListener_newMinTemp_minEvent() {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(10));
        TestTemperatureHistoryListener listener = new TestTemperatureHistoryListener();
        history.addListener(listener);

        Temperature temp = Temperature.celsius(-10);
        history.add(temp);

        TemperatureHistoryEvent event = listener.getEvent();
        assertEquals(temp, event.getMeasurePoint().getTemperature());
        assertEquals(TemperatureHistoryEventType.MIN, event.getType());
        assertEquals(history, event.getSource());
    }

    @Test
    void addListener_addTempEqualToPreviousMaxTemp_noEvent() {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(10));
        history.add(Temperature.celsius(5));
        TestTemperatureHistoryListener listener = new TestTemperatureHistoryListener();
        history.addListener(listener);

        history.add(Temperature.celsius(10));

        assertEquals(0, listener.getEvents().size());
    }

    @Test
    void addListener_addTempEqualToPreviousMinTemp_noEvent() {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(10));
        history.add(Temperature.celsius(5));
        TestTemperatureHistoryListener listener = new TestTemperatureHistoryListener();
        history.addListener(listener);

        history.add(Temperature.celsius(5));

        assertEquals(0, listener.getEvents().size());
    }

    @Test
    void addListener_neitherMaxOrMinValue_noEvent() {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(10));
        history.add(Temperature.celsius(5));
        TestTemperatureHistoryListener listener = new TestTemperatureHistoryListener();
        history.addListener(listener);

        history.add(Temperature.celsius(7));

        assertEquals(0, listener.getEvents().size());
    }

    @Test
    void historyWithListener_removeListener_noMoreEvents() {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(10));
        TestTemperatureHistoryListener listener = new TestTemperatureHistoryListener();
        history.addListener(listener);
        history.add(Temperature.celsius(-10));

        history.removeListener(listener);
        history.add(Temperature.celsius(-20));

        assertEquals(1, listener.getEvents().size());
    }

    @Test
    void historyAddNullListener_newMaxTemp_noException() {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(10));

        history.addListener(null);

        assertDoesNotThrow(() -> history.add(Temperature.celsius(20)));
    }

    @Test
    void historyWith2Listeners_newMaxTemp_bothListenerCalled() {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(10));
        TestTemperatureHistoryListener listener1 = new TestTemperatureHistoryListener();
        history.addListener(listener1);
        TestTemperatureHistoryListener listener2 = new TestTemperatureHistoryListener();
        history.addListener(listener2);

        history.add(Temperature.celsius(20));

        assertEquals(1, listener1.getEvents().size());
        assertEquals(1, listener2.getEvents().size());
    }

    @Test
    void newHistory_getCount_zero() {
        assertEquals(0, new TemperatureHistory().getCount());
    }

    @Test
    void temperature_add_getCount1() {
        Temperature temp = Temperature.celsius(25);

        TemperatureHistory history = new TemperatureHistory();
        history.add(temp);

        assertEquals(1, history.getCount());
    }

    @Test
    void threeTemperature_add_getCount1() {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(25));
        history.add(Temperature.celsius(-5));
        history.add(Temperature.celsius(17));

        assertEquals(3, history.getCount());
    }

    @Test
    void emptyHistory_clear_doNoting() {
        TemperatureHistory history = new TemperatureHistory();
        history.clear();
        assertEquals(0, history.getCount());
    }

    @Test
    void historyWith3Temperature_clear_getCountReturnZero() {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(25));
        history.add(Temperature.celsius(-5));
        history.add(Temperature.celsius(17));

        history.clear();

        assertEquals(0, history.getCount());
    }

    @Test
    void historyWithMaxTempAtBeginning_max_getMaxTemperature() {
        TemperatureHistory history = new TemperatureHistory();
        Temperature maxTemp = Temperature.celsius(25);
        history.add(maxTemp);
        history.add(Temperature.celsius(-5));
        history.add(Temperature.celsius(17));

        Temperature maxFromHistory = history.max().getTemperature();

        assertEquals(maxTemp, maxFromHistory);
    }

    @Test
    void historyWithMaxTempAtEnd_max_getMaxTemperature() {
        TemperatureHistory history = new TemperatureHistory();
        Temperature maxTemp = Temperature.celsius(25);
        history.add(Temperature.celsius(17));
        history.add(Temperature.celsius(-5));
        history.add(maxTemp);

        Temperature maxFromHistory = history.max().getTemperature();

        assertEquals(maxTemp, maxFromHistory);
    }

    @Test
    void historyWithMaxTempBetween_max_getMaxTemperature() {
        TemperatureHistory history = new TemperatureHistory();
        Temperature maxTemp = Temperature.celsius(25);
        history.add(Temperature.celsius(17));
        history.add(maxTemp);
        history.add(Temperature.celsius(-5));

        Temperature maxFromHistory = history.max().getTemperature();

        assertEquals(maxTemp, maxFromHistory);
    }

    @Test
    void emptyHistory_max_throwNoSuchElementException() {
        TemperatureHistory history = new TemperatureHistory();

        assertThrows(NoSuchElementException.class, history::max);
    }

    @Test
    void historyWithMinTempAtBeginning_min_getMinTemperature() {
        TemperatureHistory history = new TemperatureHistory();
        Temperature minTemp = Temperature.celsius(-7);
        history.add(minTemp);
        history.add(Temperature.celsius(-5));
        history.add(Temperature.celsius(17));

        Temperature minFromHistory = history.min().getTemperature();

        assertEquals(minTemp, minFromHistory);
    }

    @Test
    void historyWithMinTempAtEnd_min_getMinTemperature() {
        TemperatureHistory history = new TemperatureHistory();
        Temperature minTemp = Temperature.celsius(-7);
        history.add(Temperature.celsius(17));
        history.add(Temperature.celsius(-5));
        history.add(minTemp);

        Temperature minFromHistory = history.min().getTemperature();

        assertEquals(minTemp, minFromHistory);
    }

    @Test
    void historyWithMinTempBetween_min_getMinTemperature() {
        TemperatureHistory history = new TemperatureHistory();
        Temperature minTemp = Temperature.celsius(-7);
        history.add(Temperature.celsius(17));
        history.add(minTemp);
        history.add(Temperature.celsius(-5));

        Temperature minFromHistory = history.min().getTemperature();

        assertEquals(minTemp, minFromHistory);
    }

    @Test
    void emptyHistory_min_throwNoSuchElementException() {
        TemperatureHistory history = new TemperatureHistory();

        assertThrows(NoSuchElementException.class, history::min);
    }

    @Test
    void historyWith3Temp_average_returnsAverageTemp() {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(17));
        history.add(Temperature.celsius(-5));
        history.add(Temperature.celsius(25));

        Temperature average = history.average();

        assertEquals(12.33333, average.getCelsius(), 0.00001);
    }

    @Test
    void emptyHistory_average_returnZero() {
        TemperatureHistory history = new TemperatureHistory();
        assertEquals(0, history.average().getCelsius());
    }

    private static class TestTemperatureHistoryListener implements TemperatureHistoryListener {
        private final List<TemperatureHistoryEvent> events = new ArrayList<>();

        @Override
        public void temperatureHistoryPerformed(TemperatureHistoryEvent event) {
            this.events.add(event);
        }

        public TemperatureHistoryEvent getEvent() {
            assertEquals(1, events.size());
            return events.get(0);
        }

        public List<TemperatureHistoryEvent> getEvents() {
            return events;
        }
    }
}