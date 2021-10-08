package aufgaben.temperature.history;

import aufgaben.temperature.Temperature;

import java.time.LocalDateTime;
import java.util.Objects;

public class MeasurePoint implements Comparable<MeasurePoint> {
    private final Temperature temperature;
    private final LocalDateTime datetime;

    public MeasurePoint(Temperature temperature, LocalDateTime datetime) {
        this.temperature = temperature;
        this.datetime = datetime;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    @Override
    public int compareTo(MeasurePoint o) {
        return getTemperature().compareTo(o.getTemperature());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeasurePoint)) return false;
        MeasurePoint that = (MeasurePoint) o;
        return Objects.equals(temperature, that.temperature) && Objects.equals(datetime, that.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, datetime);
    }

    @Override
    public String toString() {
        return "MeasurePoint{" +
                "temperature=" + temperature +
                ", datetime=" + datetime +
                '}';
    }
}
