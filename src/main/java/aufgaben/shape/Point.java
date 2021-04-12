package aufgaben.shape;

import java.util.Objects;

import static java.lang.Math.*;

class Point {
    private int x;
    private int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this(point.getX(), point.getY());
    }

    public final void move(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public void moveRelative(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void moveRelative(Point point) {
        moveRelative(point.getX(), point.getY());
    }

    public void moveRelativeAngle(double angle, double value) {
        final double radians = toRadians(angle);
        this.x += round(cos(radians) * value);
        this.y += round(sin(radians) * value);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int getQuadrant() {
        if (getX() > 0 && getY() > 0) {
            return 1;
        } else if (getX() < 0 && getY() > 0) {
            return 2;
        } else if (getX() < 0 && getY() < 0) {
            return 3;
        } else if (getX() > 0 && getY() < 0) {
            return 4;
        }
        return 0;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        return Objects.equals(point.x, x) && Objects.equals(point.y, y);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(x, y);
    }
}
