package aufgaben.switchable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Auto {
    private static final Logger LOGGER = LogManager.getLogger(Auto.class);
    private final Motor motor;

    public Auto(final Motor motor) {
        this.motor = motor;
        motor.addPropertyChangeListener(event -> LOGGER.info("Motor {} changed. Old Value: '{}', New Value: '{}'}",
                event.getPropertyName(), event.getOldValue(), event.getNewValue()));
    }
}
