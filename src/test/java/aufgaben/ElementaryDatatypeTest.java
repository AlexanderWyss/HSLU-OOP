package aufgaben;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"NumericOverflow", "IntegerDivisionInFloatingPointContext"})
class ElementaryDatatypeTest {
    @Test
    void test_int() {
        assertEquals(2147483647, Integer.MAX_VALUE);
        assertEquals(-2147483648, Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, Integer.MAX_VALUE + 1);
    }

    @Test
    void test_float() {
        assertEquals(3.4028235E38f, Float.MAX_VALUE);
        assertEquals(3.4028235E38f, Float.MAX_VALUE + 1.0f);
    }

    @Test
    void test_calcIntFloat() {
        assertEquals(4, 2 + 5 / 2);
        assertEquals(4.5, 2 + 5 / 2f);
        assertEquals(4.5, 2 + 5f / 2);
        assertEquals(4, 2f + 5 / 2);
        assertEquals(4.5, 2 + (float) 5 / 2);
        assertEquals(4.5, 2 + 5 / (float) 2);
        assertEquals(4, 2 + (float) (5 / 2));
    }
}