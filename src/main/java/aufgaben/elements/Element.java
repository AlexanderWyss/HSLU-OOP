package aufgaben.elements;

import java.util.Objects;

import static aufgaben.elements.Temperature.celsius;

public abstract class Element implements Comparable<Element> {
    private final int atomicNumber;
    private final String symbol;
    private final String name;
    private final Temperature meltingPoint;
    private final Temperature boilingPoint;

    protected Element(final int atomicNumber, final String symbol, final String name, final Temperature meltingPoint, final Temperature boilingPoint) {
        this.atomicNumber = atomicNumber;
        this.symbol = symbol;
        this.name = name;
        this.meltingPoint = meltingPoint;
        this.boilingPoint = boilingPoint;
    }

    protected Element(final int atomicNumber, final String symbol, final String name, final double meltingPointCelsius, final double boilingPointCelsius) {
        this(atomicNumber, symbol, name, celsius(meltingPointCelsius), celsius(boilingPointCelsius));
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public Temperature getMeltingPoint() {
        return meltingPoint;
    }

    public Temperature getBoilingPoint() {
        return boilingPoint;
    }

    public AggregationState getAggregationState(Temperature temperature) {
        if (temperature.getCelsius() >= boilingPoint.getCelsius()) {
            return AggregationState.GASEOUS;
        } else if (temperature.getCelsius() >= meltingPoint.getCelsius()) {
            return AggregationState.LIQUID;
        }
        return AggregationState.SOLID;
    }

    @Override
    public String toString() {
        return "Element{" +
                "atomicNumber=" + atomicNumber +
                ", symbol='" + symbol + '\'' +
                ", meltingPoint=" + meltingPoint +
                ", boilingPoint=" + boilingPoint +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Element)) {
            return false;
        }
        Element element = (Element) o;
        return Objects.equals(element.atomicNumber, atomicNumber)
                && Objects.equals(element.symbol, symbol)
                && Objects.equals(element.name, name)
                && Objects.equals(element.meltingPoint, meltingPoint)
                && Objects.equals(element.boilingPoint, boilingPoint);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(atomicNumber, symbol, name, meltingPoint, boilingPoint);
    }

    @Override
    public int compareTo(Element element) {
        return Double.compare(atomicNumber, element.atomicNumber);
    }
}
