package aufgaben.elements;

import aufgaben.temperature.TemperatureUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureUnitTest {
    @Test
    void c_fromUnit_celsius() {
        assertEquals(TemperatureUnit.CELSIUS, TemperatureUnit.fromUnit("C"));
    }

    @Test
    void cLowerCase_fromUnit_celsius() {
        assertEquals(TemperatureUnit.CELSIUS, TemperatureUnit.fromUnit("c"));
    }

    @Test
    void f_fromUnit_fahrenheit() {
        assertEquals(TemperatureUnit.FAHRENHEIT, TemperatureUnit.fromUnit("F"));
    }

    @Test
    void k_fromUnit_kelvin() {
        assertEquals(TemperatureUnit.KELVIN, TemperatureUnit.fromUnit("K"));
    }
}