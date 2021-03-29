package aufgaben.elements;

public abstract class Element {
    private final String symbol;
    private final double meltingPoint;
    private final double boilingPoint;

    protected Element(final String symbol, final double meltingPointCelsius, final double boilingPointCelsius) {
        this.symbol = symbol;
        this.meltingPoint = meltingPointCelsius;
        this.boilingPoint = boilingPointCelsius;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getMeltingPoint() {
        return meltingPoint;
    }

    public double getBoilingPoint() {
        return boilingPoint;
    }

    public String getAggregationState(Temperature temperature) {
        if (temperature.getCelsius() >= boilingPoint) {
            return "gaseous";
        } else if (temperature.getCelsius() >= meltingPoint) {
            return "liquid";
        }
        return "solid";
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "symbol='" + symbol + '\'' +
                ", meltingPoint=" + meltingPoint +
                ", boilingPoint=" + boilingPoint +
                '}';
    }
}
