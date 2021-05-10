package aufgaben.elements;

import java.util.*;

public final class TemperatureHistory implements Iterable<Temperature> {
    private final List<Temperature> history = new ArrayList<>();
    private final List<TemperatureHistoryListener> listeners = new ArrayList<>();

    public void add(Temperature temperature) {
        List<TemperatureHistoryEvent> events = generateEvents(temperature);
        history.add(temperature);
        fireEvents(events);
    }

    private void fireEvents(List<TemperatureHistoryEvent> events) {
        for (TemperatureHistoryEvent event : events) {
            fireTemperatureHistoryEvent(event);
        }
    }

    private List<TemperatureHistoryEvent> generateEvents(Temperature temperature) {
        List<TemperatureHistoryEvent> events = new ArrayList<>();
        if (history.size() == 0 || max().getCelsius() < temperature.getCelsius()) {
            events.add(new TemperatureHistoryEvent(this, temperature, TemperatureHistoryEventType.MAX));
        }
        if (history.size() == 0 || min().getCelsius() > temperature.getCelsius()) {
            events.add(new TemperatureHistoryEvent(this, temperature, TemperatureHistoryEventType.MIN));
        }
        return events;
    }

    private void fireTemperatureHistoryEvent(TemperatureHistoryEvent event) {
        for (TemperatureHistoryListener listener : listeners) {
            listener.temperatureHistoryPerformed(event);
        }
    }

    public int getCount() {
        return history.size();
    }

    public void clear() {
        history.clear();
    }

    public Temperature max() throws NoSuchElementException {
        return Collections.max(history);
    }

    public Temperature min() throws NoSuchElementException {
        return Collections.min(history);
    }

    public Temperature average() throws NoSuchElementException {
        return Temperature.celsius(history.stream() //
                .mapToDouble(Temperature::getCelsius) //
                .average() //
                .orElseThrow(() -> new NoSuchElementException("No Temperature in history.")));
    }

    public void addListener(TemperatureHistoryListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    public void removeListener(TemperatureHistoryListener listener) {
        listeners.remove(listener);
    }

    @Override
    public Iterator<Temperature> iterator() {
        return history.iterator();
    }
}
