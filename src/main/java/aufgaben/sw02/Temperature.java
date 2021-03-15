package aufgaben.sw02;

class Temperature {
    private float celsius;

    /**
     * default 20 degree celsius
     */
    public Temperature() {
        this(20);
    }

    private Temperature(final float celsius) {
        this.celsius = celsius;
    }

    public float getCelsius() {
        return this.celsius;
    }

    public void setCelsius(final float celsius) {
        this.celsius = celsius;
    }

    public void addCelsius(final float celsius) {
        this.celsius += celsius;
    }

    public static Temperature celsius(final float celsius) {
        return new Temperature(celsius);
    }

    public float getKelvin() {
        return toKelvin(this.celsius);
    }

    private static float toKelvin(final float celsius) {
        return celsius + 273.15f;
    }

    public void setKelvin(final float kelvin) {
        this.celsius = fromKelvin(kelvin);
    }

    private static float fromKelvin(final float kelvin) {
        return kelvin - 273.15f;
    }

    public void addKelvin(final float kelvin) {
        setKelvin(kelvin + getKelvin());
    }

    public static Temperature kelvin(final float kelvin) {
        return new Temperature(fromKelvin(kelvin));
    }

    public float getFahrenheit() {
        return toFahrenheit(this.celsius);
    }

    private static float toFahrenheit(final float celsius) {
        return (celsius * (9f/5f)) + 32f;
    }

    public void setFahrenheit(final float fahrenheit) {
        this.celsius = fromFahrenheit(fahrenheit);
    }

    private static float fromFahrenheit(final float fahrenheit) {
        return (fahrenheit - 32f) * (5f/9f);
    }

    public void addFahrenheit(final float fahrenheit) {
       setFahrenheit(fahrenheit + getFahrenheit());
    }

    public static Temperature fahrenheit(final float fahrenheit) {
        return new Temperature(fromFahrenheit(fahrenheit));
    }
}
