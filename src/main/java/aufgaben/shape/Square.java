package aufgaben.shape;

public class Square extends Shape {
    private int length;

    protected Square(final int x, final int y, final int length) {
        super(x, y);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    @Override
    public int getPerimeter() {
        return length * 4;
    }

    @Override
    public int getArea() {
        return length * length;
    }
}
