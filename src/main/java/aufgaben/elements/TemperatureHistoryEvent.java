package aufgaben.elements;

import java.util.EventObject;

public class TemperatureHistoryEvent extends EventObject {
    private final Temperature temperature;
    private final TemperatureHistoryEventType type;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public TemperatureHistoryEvent(final Object source, final Temperature temperature, final TemperatureHistoryEventType type) {
        super(source);
        this.temperature = temperature;
        this.type = type;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public TemperatureHistoryEventType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "TemperatureHistoryEvent{" +
                "temperature=" + temperature +
                ", type=" + type +
                ", source=" + source +
                '}';
    }
}
