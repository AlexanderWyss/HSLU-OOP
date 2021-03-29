package aufgaben.calc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void twoPositiveNumbers_add_correctResult() {
        assertEquals(46.87, getCalculator().add(12.75, 34.12), 0.00001);
    }

    @Test
    void twoNegativeNumbers_add_correctResult() {
        assertEquals(-46.87, getCalculator().add(-12.75, -34.12), 0.00001);
    }

    @Test
    void negativeAndPositiveNumber_add_correctResult() {
        assertEquals(21.37, getCalculator().add(-12.75, 34.12), 0.00001);
    }

    @Test
    void zeroAndZero_add_correctResult() {
        assertEquals(0, getCalculator().add(0, 0), 0.00001);
    }

    @Test
    void maxDouble_add_throwsException() {
        assertThrows(ArithmeticException.class, () -> getCalculator().add(Double.MAX_VALUE, Double.MAX_VALUE));
    }

    @Test
    void minDouble_add_throwsException() {
        assertThrows(ArithmeticException.class, () -> getCalculator().add(-Double.MAX_VALUE, -Double.MAX_VALUE));
    }

    private ICalculator getCalculator() {
        return new Calculator();
    }
}