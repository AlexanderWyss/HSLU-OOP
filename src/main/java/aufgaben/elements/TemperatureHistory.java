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
}
