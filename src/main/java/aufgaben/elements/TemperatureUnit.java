package aufgaben.elements;

public enum TemperatureUnit {
    CELSIUS("C"), FAHRENHEIT("F"), KELVIN("K");

    private final String unit;

    TemperatureUnit(final String unit) {
        this.unit = unit;
    }

    public static TemperatureUnit fromUnit(String unit) {
        for (TemperatureUnit value : TemperatureUnit.values()) {
            if (value.unit.equalsIgnoreCase(unit)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unit not known.");
    }

    public String getUnit() {
        return unit;
    }
}
