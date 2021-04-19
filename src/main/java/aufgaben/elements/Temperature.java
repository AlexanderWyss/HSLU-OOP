package aufgaben.elements;

import java.util.Objects;

final class Temperature implements Comparable<Temperature> {
    public static final double KELVIN_OFFSET = 273.15;
    private double celsius;

    /**
     * default 20 degree celsius
     */
    public Temperature() {
        this(20);
    }

    private Temperature(final double celsius) {
        this.celsius = celsius;
    }

    public double getCelsius() {
        return this.celsius;
    }

    public void setCelsius(final double celsius) {
        this.celsius = celsius;
    }

    public void addCelsius(final double celsius) {
        this.celsius += celsius;
    }

    public static Temperature celsius(final double celsius) {
        return new Temperature(celsius);
    }

    public double getKelvin() {
        return toKelvin(this.celsius);
    }

    private static double toKelvin(final double celsius) {
        return celsius + KELVIN_OFFSET;
    }

    public void setKelvin(final double kelvin) {
        this.celsius = fromKelvin(kelvin);
    }

    private static double fromKelvin(final double kelvin) {
        return kelvin - KELVIN_OFFSET;
    }

    public void addKelvin(final double kelvin) {
        setKelvin(kelvin + getKelvin());
    }

    public static Temperature kelvin(final double kelvin) {
        return new Temperature(fromKelvin(kelvin));
    }

    public double getFahrenheit() {
        return toFahrenheit(this.celsius);
    }

    private static double toFahrenheit(final double celsius) {
        return (celsius * (9. / 5.)) + 32;
    }

    public void setFahrenheit(final double fahrenheit) {
        this.celsius = fromFahrenheit(fahrenheit);
    }

    private static double fromFahrenheit(final double fahrenheit) {
        return (fahrenheit - 32) * (5. / 9.);
    }

    public void addFahrenheit(final double fahrenheit) {
        setFahrenheit(fahrenheit + getFahrenheit());
    }

    public static Temperature fahrenheit(final double fahrenheit) {
        return new Temperature(fromFahrenheit(fahrenheit));
    }

    @Override
    public final boolean equals(Object o) {
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
    public final int hashCode() {
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
