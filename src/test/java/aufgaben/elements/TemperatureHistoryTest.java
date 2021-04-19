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

        Temperature maxFromHistory = history.max();

        assertEquals(maxTemp, maxFromHistory);
    }

    @Test
    void historyWithMaxTempAtEnd_max_getMaxTemperature() {
        TemperatureHistory history = new TemperatureHistory();
        Temperature maxTemp = Temperature.celsius(25);
        history.add(Temperature.celsius(17));
        history.add(Temperature.celsius(-5));
        history.add(maxTemp);

        Temperature maxFromHistory = history.max();

        assertEquals(maxTemp, maxFromHistory);
    }

    @Test
    void historyWithMaxTempBetween_max_getMaxTemperature() {
        TemperatureHistory history = new TemperatureHistory();
        Temperature maxTemp = Temperature.celsius(25);
        history.add(Temperature.celsius(17));
        history.add(maxTemp);
        history.add(Temperature.celsius(-5));

        Temperature maxFromHistory = history.max();

        assertEquals(maxTemp, maxFromHistory);
    }
    
    @Test
    void historyWithMinTempAtBeginning_min_getMinTemperature() {
        TemperatureHistory history = new TemperatureHistory();
        Temperature minTemp = Temperature.celsius(-7);
        history.add(minTemp);
        history.add(Temperature.celsius(-5));
        history.add(Temperature.celsius(17));

        Temperature minFromHistory = history.min();

        assertEquals(minTemp, minFromHistory);
    }

    @Test
    void historyWithMinTempAtEnd_min_getMinTemperature() {
        TemperatureHistory history = new TemperatureHistory();
        Temperature minTemp = Temperature.celsius(-7);
        history.add(Temperature.celsius(17));
        history.add(Temperature.celsius(-5));
        history.add(minTemp);

        Temperature minFromHistory = history.min();

        assertEquals(minTemp, minFromHistory);
    }

    @Test
    void historyWithMinTempBetween_min_getMinTemperature() {
        TemperatureHistory history = new TemperatureHistory();
        Temperature minTemp = Temperature.celsius(-7);
        history.add(Temperature.celsius(17));
        history.add(minTemp);
        history.add(Temperature.celsius(-5));

        Temperature minFromHistory = history.min();

        assertEquals(minTemp, minFromHistory);
    }
}