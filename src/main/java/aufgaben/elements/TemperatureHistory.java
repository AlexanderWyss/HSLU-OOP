package aufgaben.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
