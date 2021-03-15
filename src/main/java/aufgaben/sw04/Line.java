package aufgaben.sw04;

class Line {
    private Point a;
    private Point b;

    public Line(final Point a, final Point b) {
        this.a = a;
        this.b = b;
    }

    public Point getA() {
        return a;
    }

    public void setA(final Point a) {
        this.a = a;
    }

    public Point getB() {
        return b;
    }

    public void setB(final Point b) {
        this.b = b;
    }
}
