package aufgaben.elements;

import java.util.EventObject;

public class TemperatureHistoryEvent extends EventObject {
    private final MeasurePoint measurePoint;
    private final TemperatureHistoryEventType type;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public TemperatureHistoryEvent(final Object source, final MeasurePoint measurePoint, final TemperatureHistoryEventType type) {
        super(source);
        this.measurePoint = measurePoint;
        this.type = type;
    }

    public MeasurePoint getMeasurePoint() {
        return measurePoint;
    }

    public TemperatureHistoryEventType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "TemperatureHistoryEvent{" +
                "temperature=" + measurePoint +
                ", type=" + type +
                ", source=" + source +
                '}';
    }
}
