package aufgaben.shape;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {
    @Test
    void verifyEquals() {
        EqualsVerifier.simple().forClass(Point.class).verify();
    }

    // sw06
    @Test
    void a_point_moveRelativeXY_xAndYCorrect() {
        Point point = new Point(3, 5);

        point.moveRelative(6, 2);

        assertEquals(9, point.getX());
        assertEquals(7, point.getY());
    }

    @Test
    void a_point_moveRelativeNegativeXY_xAndYCorrect() {
        Point point = new Point(3, 5);

        point.moveRelative(-6, -2);

        assertEquals(-3, point.getX());
        assertEquals(3, point.getY());
    }

    @Test
    void a_pointWithNegativeXY_moveRelativeXY_xAndYCorrect() {
        Point point = new Point(-3, -5);

        point.moveRelative(-6, 7);

        assertEquals(-9, point.getX());
        assertEquals(2, point.getY());
    }

    @Test
    void a_pointZeroXY_moveRelativeXY_xAndYCorrect() {
        Point point = new Point(0, 0);

        point.moveRelative(-6, 7);

        assertEquals(-6, point.getX());
        assertEquals(7, point.getY());
    }

    @Test
    void a_point_moveRelativeZeroXY_xAndYCorrect() {
        Point point = new Point(4, -5);

        point.moveRelative(0, 0);

        assertEquals(4, point.getX());
        assertEquals(-5, point.getY());
    }

    @Test
    void b_point_moveRelative_xAndYCorrect() {
        Point point = new Point(3, 5);

        point.moveRelative(new Point(6, 2));

        assertEquals(9, point.getX());
        assertEquals(7, point.getY());
    }

    @Test
    void c_point_moveRelativeAngle_xAndYCorrect() {
        Point point = new Point(3, 5);

        point.moveRelativeAngle(35.7, 4);

        assertEquals(6, point.getX());
        assertEquals(7, point.getY());
    }

    @Test
    void c_pointNegativeXY_moveRelativeAngle_xAndYCorrect() {
        Point point = new Point(-3, -5);

        point.moveRelativeAngle(49.2, 2);

        assertEquals(-2, point.getX());
        assertEquals(-3, point.getY());
    }

    @Test
    void c_pointNegativeXPositiveY_moveRelativeAngle_xAndYCorrect() {
        Point point = new Point(-3, 5);

        point.moveRelativeAngle(60.1, 7);

        assertEquals(0, point.getX());
        assertEquals(11, point.getY());
    }

    @Test
    void c_point_moveRelativeNegativeAngle_xAndYCorrect() {
        Point point = new Point(3, 5);

        point.moveRelativeAngle(-220.1, 9);

        assertEquals(-4, point.getX());
        assertEquals(11, point.getY());
    }

    @Test
    void c_point_moveRelativeAngleNegativeValue_xAndYCorrect() {
        Point point = new Point(3, 5);

        point.moveRelativeAngle(195.1, -4);

        assertEquals(7, point.getX());
        assertEquals(6, point.getY());
    }

    // sw04
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