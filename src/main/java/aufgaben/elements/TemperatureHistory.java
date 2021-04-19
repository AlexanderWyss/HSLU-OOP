package aufgaben.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public final class TemperatureHistory {
    private final List<Temperature> history = new ArrayList<>();

    public void add(Temperature temperature) {
        history.add(temperature);
    }

    public int getCount() {
        return history.size();
    }

    public void clear() {
        history.clear();
    }

    public Temperature max() {
        return Collections.max(history);
    }

    public Temperature min() {
        return Collections.min(history);
    }

    public Temperature average() {
        return Temperature.celsius(history.stream() //
                .mapToDouble(Temperature::getCelsius) //
                .average() //
                .orElseThrow(() -> new NoSuchElementException("No Temperature in history.")));
    }
}
