package aufgaben.temperature.history;

import aufgaben.temperature.Temperature;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public final class TemperatureHistory implements Iterable<MeasurePoint> {
    private final List<MeasurePoint> history = new ArrayList<>();
    private final List<TemperatureHistoryListener> listeners = new ArrayList<>();

    public void add(MeasurePoint measurePoint) {
        List<TemperatureHistoryEvent> events = generateEvents(measurePoint);
        history.add(measurePoint);
        fireEvents(events);
    }
    public void add(Temperature temperature) {
        add(new MeasurePoint(temperature, LocalDateTime.now()));
    }

    private void fireEvents(List<TemperatureHistoryEvent> events) {
        for (TemperatureHistoryEvent event : events) {
            fireTemperatureHistoryEvent(event);
        }
    }

    private List<TemperatureHistoryEvent> generateEvents(MeasurePoint measurePoint) {
        List<TemperatureHistoryEvent> events = new ArrayList<>();
        if (history.size() == 0 || max().getTemperature().getCelsius() < measurePoint.getTemperature().getCelsius()) {
            events.add(new TemperatureHistoryEvent(this, measurePoint, TemperatureHistoryEventType.MAX));
        }
        if (history.size() == 0 || min().getTemperature().getCelsius() > measurePoint.getTemperature().getCelsius()) {
            events.add(new TemperatureHistoryEvent(this, measurePoint, TemperatureHistoryEventType.MIN));
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

    public MeasurePoint max() throws NoSuchElementException {
        return Collections.max(history);
    }

    public MeasurePoint min() throws NoSuchElementException {
        return Collections.min(history);
    }

    public Temperature average() {
        return Temperature.celsius(history.stream().collect(Collectors.averagingDouble(m -> m.getTemperature().getCelsius())));
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
    public Iterator<MeasurePoint> iterator() {
        return history.iterator();
    }
}
