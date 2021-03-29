package aufgaben.shape;

import static java.lang.Math.*;

public abstract class Shape {
    private int x;
    private int y;

    protected Shape(final int x, final int y) {
        this.x = x;
        this.y = y;
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

    public abstract int getPerimeter();

    public abstract int getArea();
}