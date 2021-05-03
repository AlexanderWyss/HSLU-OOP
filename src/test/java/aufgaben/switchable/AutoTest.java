package aufgaben.switchable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutoTest {
    @Test
    void manualTests() {
        Motor motor = new Motor();
        Auto auto = new Auto(motor);
        motor.setRpm(1520);
        motor.switchOff();
        motor.switchOn();
    }
}