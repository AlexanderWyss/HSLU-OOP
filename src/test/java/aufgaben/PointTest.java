package aufgaben;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    @Test
    void h_getQuadrant_1() {
        assertEquals(1, new Point(1, 1).getQuadrant());
        assertEquals(1, new Point(4, 1).getQuadrant());
        assertEquals(1, new Point(4, 5).getQuadrant());
    }

    @Test
    void h_getQuadrant_2() {
        assertEquals(2, new Point(-1, 1).getQuadrant());
        assertEquals(2, new Point(-4, 1).getQuadrant());
        assertEquals(2, new Point(-4, 5).getQuadrant());
    }

    @Test
    void h_getQuadrant_3() {
        assertEquals(3, new Point(-1, -1).getQuadrant());
        assertEquals(3, new Point(-4, -1).getQuadrant());
        assertEquals(3, new Point(-4, -5).getQuadrant());
    }

    @Test
    void h_getQuadrant_4() {
        assertEquals(4, new Point(1, -1).getQuadrant());
        assertEquals(4, new Point(4, -1).getQuadrant());
        assertEquals(4, new Point(4, -5).getQuadrant());
    }

    @Test
    void i_getQuadrant_noQuadrant() {
        assertEquals(0, new Point(0, 1).getQuadrant());
        assertEquals(0, new Point(4, 0).getQuadrant());
        assertEquals(0, new Point(0, 0).getQuadrant());
    }
}