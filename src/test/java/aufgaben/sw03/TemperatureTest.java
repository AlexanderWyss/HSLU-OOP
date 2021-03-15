package aufgaben.sw03;

import org.junit.jupiter.api.Test;

import static aufgaben.sw03.Temperature.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureTest {
    @Test
    void d_aggregationState_N() {
        assertEquals("solid", kelvin(0).getAggregationState("N"));
        assertEquals("solid", celsius(-210.2f).getAggregationState("N"));
        assertEquals("liquid", celsius(-210.1f).getAggregationState("N"));
        assertEquals("liquid", celsius(-196.1f).getAggregationState("N"));
        assertEquals("gaseous", celsius(-196).getAggregationState("N"));
        assertEquals("gaseous", celsius(15).getAggregationState("N"));
    }

    @Test
    void d_aggregationState_Hg() {
        assertEquals("solid", kelvin(0).getAggregationState("Hg"));
        assertEquals("solid", celsius(-38.84f).getAggregationState("Hg"));
        assertEquals("liquid", celsius(-38.83f).getAggregationState("Hg"));
        assertEquals("liquid", celsius(356.9f).getAggregationState("Hg"));
        assertEquals("gaseous", celsius(357).getAggregationState("Hg"));
        assertEquals("gaseous", celsius(500).getAggregationState("Hg"));
    }

    @Test
    void d_aggregationState_Pb() {
        assertEquals("solid", kelvin(0).getAggregationState("Pb"));
        assertEquals("solid", celsius(327.42f).getAggregationState("Pb"));
        assertEquals("liquid", celsius(327.43f).getAggregationState("Pb"));
        assertEquals("liquid", celsius(1743.9f).getAggregationState("Pb"));
        assertEquals("gaseous", celsius(1744).getAggregationState("Pb"));
        assertEquals("gaseous", celsius(2000).getAggregationState("Pb"));
    }

    // SW02
    @Test
    void newCelsius_correctlySetAndConverted() {
        Temperature temperature = celsius(15.45f);

        assertEquals(15.45f, temperature.getCelsius(), 0.001f);
        assertEquals(59.81f, temperature.getFahrenheit(), 0.001f);
        assertEquals(288.6f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newFahrenheit_correctlySetAndConverted() {
        Temperature temperature = fahrenheit(59.81f);

        assertEquals(15.45f, temperature.getCelsius(), 0.001f);
        assertEquals(59.81f, temperature.getFahrenheit(), 0.001f);
        assertEquals(288.6f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newKelvin_correctlySetAndConverted() {
        Temperature temperature = kelvin(288.6f);

        assertEquals(15.45f, temperature.getCelsius(), 0.001f);
        assertEquals(59.81f, temperature.getFahrenheit(), 0.001f);
        assertEquals(288.6f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void kelvin0_correctlySetAndConverted() {
        Temperature temperature = kelvin(0);

        assertEquals(-273.15f, temperature.getCelsius(), 0.001f);
        assertEquals(-459.67f, temperature.getFahrenheit(), 0.001f);
        assertEquals(0f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void kelvin500_correctlySetAndConverted() {
        Temperature temperature = kelvin(500);

        assertEquals(226.85f, temperature.getCelsius(), 0.001f);
        assertEquals(440.33f, temperature.getFahrenheit(), 0.001f);
        assertEquals(500f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newCelsius_setCelsius_correctlySetAndConverted() {
        Temperature temperature = celsius(0f);

        temperature.setCelsius(4.8f);

        assertEquals(4.8f, temperature.getCelsius(), 0.001f);
        assertEquals(40.64f, temperature.getFahrenheit(), 0.001f);
        assertEquals(277.95f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newCelsius_setFahrenheit_correctlySetAndConverted() {
        Temperature temperature = celsius(0f);

        temperature.setFahrenheit(4.8f);

        assertEquals(-15.1111f, temperature.getCelsius(), 0.001f);
        assertEquals(4.8f, temperature.getFahrenheit(), 0.001f);
        assertEquals(258.0389f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newCelsius_setKelvin_correctlySetAndConverted() {
        Temperature temperature = celsius(0f);

        temperature.setKelvin(4.8f);

        assertEquals(-268.34998f, temperature.getCelsius(), 0.001f);
        assertEquals(-451.029964f, temperature.getFahrenheit(), 0.001f);
        assertEquals(4.8f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newCelsius_addCelsius_correctlySetAndConverted() {
        Temperature temperature = celsius(5.2f);

        temperature.addCelsius(1.5f);

        assertEquals(6.7f, temperature.getCelsius(), 0.001f);
        assertEquals(44.06f, temperature.getFahrenheit(), 0.001f);
        assertEquals(279.85f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newFahrenheit_addFahrenheit_correctlySetAndConverted() {
        Temperature temperature = fahrenheit(5.2f);

        temperature.addFahrenheit(4.8f);

        assertEquals(-12.222f, temperature.getCelsius(), 0.001f);
        assertEquals(10f, temperature.getFahrenheit(), 0.001f);
        assertEquals(260.928f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newKelvin_addKelvin_correctlySetAndConverted() {
        Temperature temperature = kelvin(15f);

        temperature.addKelvin(-3.5f);

        assertEquals(-261.65f, temperature.getCelsius(), 0.001f);
        assertEquals(-438.97f, temperature.getFahrenheit(), 0.001f);
        assertEquals(11.5f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newDefault_default20Celsius() {
        Temperature temperature = new Temperature();

        assertEquals(20f, temperature.getCelsius(), 0.001f);
    }
}