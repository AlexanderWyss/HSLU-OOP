package aufgaben.elements;

import static aufgaben.elements.Temperature.celsius;

public abstract class Element {
    private final String symbol;
    private final Temperature meltingPoint;
    private final Temperature boilingPoint;

    protected Element(final String symbol, final Temperature meltingPoint, final Temperature boilingPoint) {
        this.symbol = symbol;
        this.meltingPoint = meltingPoint;
        this.boilingPoint = boilingPoint;
    }
    protected Element(final String symbol, final double meltingPointCelsius, final double boilingPointCelsius) {
        this(symbol, celsius(meltingPointCelsius), celsius(boilingPointCelsius));
    }

    public String getSymbol() {
        return symbol;
    }

    public Temperature getMeltingPoint() {
        return meltingPoint;
    }

    public Temperature getBoilingPoint() {
        return boilingPoint;
    }

    public String getAggregationState(Temperature temperature) {
        if (temperature.getCelsius() >= boilingPoint.getCelsius()) {
            return "gaseous";
        } else if (temperature.getCelsius() >= meltingPoint.getCelsius()) {
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
