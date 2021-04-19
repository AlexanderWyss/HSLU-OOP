package aufgaben.elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureHistoryTest {

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
    void historyWith2Temperature_clear_getCountReturnZero() {
        TemperatureHistory history = new TemperatureHistory();
        history.add(Temperature.celsius(25));
        history.add(Temperature.celsius(-5));
        history.add(Temperature.celsius(17));

        history.clear();

        assertEquals(0, history.getCount());
    }
}