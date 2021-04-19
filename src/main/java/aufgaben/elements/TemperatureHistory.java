package aufgaben.elements;

import java.util.ArrayList;
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
}
