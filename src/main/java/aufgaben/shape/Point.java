package aufgaben.shape;

class Point extends Shape {

    public Point(final int x, final int y) {
        super(x, y);
    }

    public Point(Point point) {
        this(point.getX(), point.getY());
    }

    @Override
    public int getPerimeter() {
        return 0;
    }

    @Override
    public int getArea() {
        return 0;
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
}
