package aufgaben.sw02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureTest {
    @Test
    void newCelsius_correctlySetAndConverted() {
        Temperature temperature = Temperature.celsius(15.45f);

        assertEquals(15.45f, temperature.getCelsius(), 0.001f);
        assertEquals(59.81f, temperature.getFahrenheit(), 0.001f);
        assertEquals(288.6f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newFahrenheit_correctlySetAndConverted() {
        Temperature temperature = Temperature.fahrenheit(59.81f);

        assertEquals(15.45f, temperature.getCelsius(), 0.001f);
        assertEquals(59.81f, temperature.getFahrenheit(), 0.001f);
        assertEquals(288.6f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newKelvin_correctlySetAndConverted() {
        Temperature temperature = Temperature.kelvin(288.6f);

        assertEquals(15.45f, temperature.getCelsius(), 0.001f);
        assertEquals(59.81f, temperature.getFahrenheit(), 0.001f);
        assertEquals(288.6f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void kelvin0_correctlySetAndConverted() {
        Temperature temperature = Temperature.kelvin(0);

        assertEquals(-273.15f, temperature.getCelsius(), 0.001f);
        assertEquals(-459.67f, temperature.getFahrenheit(), 0.001f);
        assertEquals(0f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void kelvin500_correctlySetAndConverted() {
        Temperature temperature = Temperature.kelvin(500);

        assertEquals(226.85f, temperature.getCelsius(), 0.001f);
        assertEquals(440.33f, temperature.getFahrenheit(), 0.001f);
        assertEquals(500f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newCelsius_setCelsius_correctlySetAndConverted() {
        Temperature temperature = Temperature.celsius(0f);

        temperature.setCelsius(4.8f);

        assertEquals(4.8f, temperature.getCelsius(), 0.001f);
        assertEquals(40.64f, temperature.getFahrenheit(), 0.001f);
        assertEquals(277.95f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newCelsius_setFahrenheit_correctlySetAndConverted() {
        Temperature temperature = Temperature.celsius(0f);

        temperature.setFahrenheit(4.8f);

        assertEquals(-15.1111f, temperature.getCelsius(), 0.001f);
        assertEquals(4.8f, temperature.getFahrenheit(), 0.001f);
        assertEquals(258.0389f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newCelsius_setKelvin_correctlySetAndConverted() {
        Temperature temperature = Temperature.celsius(0f);

        temperature.setKelvin(4.8f);

        assertEquals(-268.34998f, temperature.getCelsius(), 0.001f);
        assertEquals(-451.029964f, temperature.getFahrenheit(), 0.001f);
        assertEquals(4.8f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newCelsius_addCelsius_correctlySetAndConverted() {
        Temperature temperature = Temperature.celsius(5.2f);

        temperature.addCelsius(1.5f);

        assertEquals(6.7f, temperature.getCelsius(), 0.001f);
        assertEquals(44.06f, temperature.getFahrenheit(), 0.001f);
        assertEquals(279.85f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newFahrenheit_addFahrenheit_correctlySetAndConverted() {
        Temperature temperature = Temperature.fahrenheit(5.2f);

        temperature.addFahrenheit(4.8f);

        assertEquals(-12.222f, temperature.getCelsius(), 0.001f);
        assertEquals(10f, temperature.getFahrenheit(), 0.001f);
        assertEquals(260.928f, temperature.getKelvin(), 0.001f);
    }

    @Test
    void newKelvin_addKelvin_correctlySetAndConverted() {
        Temperature temperature = Temperature.kelvin(15f);

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