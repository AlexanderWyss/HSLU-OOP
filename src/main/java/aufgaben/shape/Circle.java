package aufgaben.shape;

public final class Circle extends Shape{
    private int diameter;

    public Circle(final int x, final int y, final int diameter) {
        super(x, y);
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    @Override
    public int getPerimeter() {
        return (int) (diameter * Math.PI);
    }

    @Override
    public int getArea() {
        return (int) (Math.pow(diameter / 2.0, 2) * Math.PI);
    }
}
