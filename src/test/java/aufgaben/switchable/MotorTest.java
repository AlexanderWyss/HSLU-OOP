package aufgaben.switchable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MotorTest {
    @Test
    void justTestItAlready() {
        Motor motor = new Motor();
        assertFalse(motor.isSwitchedOn());
        assertTrue(motor.isSwitchedOff());
        motor.switchOn();
        assertTrue(motor.isSwitchedOn());
        assertFalse(motor.isSwitchedOff());
        motor.switchOff();
        assertFalse(motor.isSwitchedOn());
        assertTrue(motor.isSwitchedOff());
    }
}