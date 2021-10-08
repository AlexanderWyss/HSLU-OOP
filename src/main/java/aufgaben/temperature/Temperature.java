package aufgaben.temperature;

import java.util.Objects;

public final class Temperature implements Comparable<Temperature> {
    public static final double KELVIN_OFFSET = 273.15;
    private final double celsius;

    private Temperature(final double celsius) {
        if (celsius < -KELVIN_OFFSET) {
            throw new IllegalArgumentException("Temperature is below zero kelvin.");
        }
        this.celsius = celsius;
    }

    public double getCelsius() {
        return this.celsius;
    }

    public Temperature addCelsius(final double celsius) {
        return celsius(this.celsius + celsius);
    }

    public static Temperature celsius(final double celsius) {
        return new Temperature(celsius);
    }

    public double getKelvin() {
        return this.celsius + KELVIN_OFFSET;
    }

    public Temperature addKelvin(final double kelvin) {
        return kelvin(kelvin + getKelvin());
    }

    public static Temperature kelvin(final double kelvin) {
        return new Temperature(kelvin - KELVIN_OFFSET);
    }

    public double getFahrenheit() {
        return (this.celsius * (9. / 5.)) + 32;
    }

    public Temperature addFahrenheit(final double fahrenheit) {
        return fahrenheit(fahrenheit + getFahrenheit());
    }

    public static Temperature fahrenheit(final double fahrenheit) {
        return new Temperature((fahrenheit - 32) * (5. / 9.));
    }

    public static Temperature of(final double value, final TemperatureUnit unit) {
        switch (unit) {
            case CELSIUS:
                return celsius(value);
            case FAHRENHEIT:
                return fahrenheit(value);
            case KELVIN:
                return kelvin(value);
            default:
                throw new IllegalArgumentException("Can not handle " + unit.name());
        }
    }

    public double get(final TemperatureUnit unit) {
        switch (unit) {
            case CELSIUS:
                return getCelsius();
            case FAHRENHEIT:
                return getFahrenheit();
            case KELVIN:
                return getKelvin();
            default:
                throw new IllegalArgumentException("Can not handle " + unit.name());
        }
    }

    public Temperature add(final double value, final TemperatureUnit unit) {
        switch (unit) {
            case CELSIUS:
                return addCelsius(value);
            case FAHRENHEIT:
                return addFahrenheit(value);
            case KELVIN:
                return addKelvin(value);
            default:
                throw new IllegalArgumentException("Can not handle " + unit.name());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Temperature)) {
            return false;
        }
        Temperature temperature = (Temperature) o;
        return Objects.equals(temperature.celsius, celsius);
    }

    @Override
    public int hashCode() {
        return Objects.hash(celsius);
    }

    @Override
    public int compareTo(Temperature temp) {
        return Double.compare(celsius, temp.celsius);
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "celsius=" + celsius +
                '}';
    }
}
