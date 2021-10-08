package aufgaben.elements;

import aufgaben.temperature.Temperature;
import aufgaben.temperature.TemperatureUnit;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static aufgaben.temperature.Temperature.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureTest {

    @Test
    void of_unitCelsius_valueParsedAsCelsius() {
        Temperature temp = of(23.5, TemperatureUnit.CELSIUS);
        assertEquals(23.5, temp.getCelsius());
    }

    @Test
    void of_unitFahrenheit_valueParsedAsFahrenheit() {
        Temperature temp = of(23.5, TemperatureUnit.FAHRENHEIT);
        assertEquals(23.5, temp.getFahrenheit());
    }

    @Test
    void of_unitKelvin_valueParsedAsKelvin() {
        Temperature temp = of(23.5, TemperatureUnit.KELVIN);
        assertEquals(23.5, temp.getKelvin());
    }

    @Test
    void of_supportsAllTemperatureUnits() {
        for (TemperatureUnit unit : TemperatureUnit.values()) {
            Temperature temp = of(0, unit);
            assertNotNull(temp);
        }
    }

    @Test
    void get_unitCelsius_valueParsedAsCelsius() {
        Temperature temp = celsius(23.5);
        assertEquals(23.5, temp.get(TemperatureUnit.CELSIUS));
    }

    @Test
    void get_unitFahrenheit_valueParsedAsFahrenheit() {
        Temperature temp = fahrenheit(23.5);
        assertEquals(23.5, temp.get(TemperatureUnit.FAHRENHEIT));
    }

    @Test
    void get_unitKelvin_valueParsedAsKelvin() {
        Temperature temp = kelvin(23.5);
        assertEquals(23.5, temp.get(TemperatureUnit.KELVIN));
    }

    @Test
    void get_supportsAllTemperatureUnits() {
        for (TemperatureUnit unit : TemperatureUnit.values()) {
            assertDoesNotThrow(() -> celsius(0).get(unit));
        }
    }

    @Test
    void add_unitCelsius_valueParsedAsCelsius() {
        Temperature temp = celsius(23.5).add(2.3, TemperatureUnit.CELSIUS);
        assertEquals(25.8, temp.getCelsius(), 0.00001);
    }

    @Test
    void add_unitFahrenheit_valueParsedAsFahrenheit() {
        Temperature temp = fahrenheit(23.5).add(2.3, TemperatureUnit.FAHRENHEIT);
        assertEquals(25.8, temp.getFahrenheit(), 0.00001);
    }

    @Test
    void add_unitKelvin_valueParsedAsKelvin() {
        Temperature temp = kelvin(23.5).add(2.3, TemperatureUnit.KELVIN);
        assertEquals(25.8, temp.getKelvin(), 0.00001);
    }

    @Test
    void add_supportsAllTemperatureUnits() {
        for (TemperatureUnit unit : TemperatureUnit.values()) {
            Temperature temp = celsius(0).add(1, unit);
            assertNotNull(temp);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void newCelsius_lessThan0Kelvin_throwIllegalArgumentException() {
        assertThatThrownBy(() -> celsius(-274))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Temperature is below zero kelvin.");
    }

    @Test
    void newFahrenheit_lessThan0Kelvin_throwIllegalArgumentException() {
        assertThatThrownBy(() -> fahrenheit(-460))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Temperature is below zero kelvin.");
    }

    @Test
    void newKelvin_lessThan0Kelvin_throwIllegalArgumentException() {
        assertThatThrownBy(() -> kelvin(-0.1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Temperature is below zero kelvin.");
    }

    @Test
    void twoEqualTemp_compareTo_return0() {
        Temperature tempA = celsius(5);
        Temperature tempB = celsius(5);

        assertEquals(0, tempA.compareTo(tempB));
    }

    @Test
    void tempAGreater_compareTo_returnPlusOne() {
        Temperature tempA = celsius(26);
        Temperature tempB = celsius(23);

        assertEquals(+1, tempA.compareTo(tempB));
    }

    @Test
    void tempBGreater_compareTo_returnMinusOne() {
        Temperature tempA = celsius(-22);
        Temperature tempB = celsius(-15);

        assertEquals(-1, tempA.compareTo(tempB));
    }

    @Test
    void verifyEquals() {
        EqualsVerifier.forClass(Temperature.class).verify();
    }

    // SW02
    @Test
    void newCelsius_correctlySetAndConverted() {
        Temperature temperature = celsius(15.45);

        assertEquals(15.45, temperature.getCelsius(), 0.001);
        assertEquals(59.81, temperature.getFahrenheit(), 0.001);
        assertEquals(288.6, temperature.getKelvin(), 0.001);
    }

    @Test
    void newFahrenheit_correctlySetAndConverted() {
        Temperature temperature = fahrenheit(59.81);

        assertEquals(15.45, temperature.getCelsius(), 0.001);
        assertEquals(59.81, temperature.getFahrenheit(), 0.001);
        assertEquals(288.6, temperature.getKelvin(), 0.001);
    }

    @Test
    void newKelvin_correctlySetAndConverted() {
        Temperature temperature = kelvin(288.6);

        assertEquals(15.45, temperature.getCelsius(), 0.001);
        assertEquals(59.81, temperature.getFahrenheit(), 0.001);
        assertEquals(288.6, temperature.getKelvin(), 0.001);
    }

    @Test
    void kelvin0_correctlySetAndConverted() {
        Temperature temperature = kelvin(0);

        assertEquals(-273.15, temperature.getCelsius(), 0.001);
        assertEquals(-459.67, temperature.getFahrenheit(), 0.001);
        assertEquals(0, temperature.getKelvin(), 0.001);
    }

    @Test
    void kelvin500_correctlySetAndConverted() {
        Temperature temperature = kelvin(500);

        assertEquals(226.85, temperature.getCelsius(), 0.001);
        assertEquals(440.33, temperature.getFahrenheit(), 0.001);
        assertEquals(500, temperature.getKelvin(), 0.001);
    }

    @Test
    void newCelsius_addCelsius_correctlySetAndConverted() {
        Temperature temperature = celsius(5.2);

        Temperature resultTemp = temperature.addCelsius(1.5);

        assertEquals(6.7, resultTemp.getCelsius(), 0.001);
        assertEquals(44.06, resultTemp.getFahrenheit(), 0.001);
        assertEquals(279.85, resultTemp.getKelvin(), 0.001);
    }

    @Test
    void newFahrenheit_addFahrenheit_correctlySetAndConverted() {
        Temperature temperature = fahrenheit(5.2);

        Temperature resultTemp = temperature.addFahrenheit(4.8);

        assertEquals(-12.222, resultTemp.getCelsius(), 0.001);
        assertEquals(10, resultTemp.getFahrenheit(), 0.001);
        assertEquals(260.928, resultTemp.getKelvin(), 0.001);
    }

    @Test
    void newKelvin_addKelvin_correctlySetAndConverted() {
        Temperature temperature = kelvin(15);

        Temperature resultTemp = temperature.addKelvin(-3.5);

        assertEquals(-261.65, resultTemp.getCelsius(), 0.001);
        assertEquals(-438.97, resultTemp.getFahrenheit(), 0.001);
        assertEquals(11.5, resultTemp.getKelvin(), 0.001);
    }
}