package aufgaben.shape;

public class Square extends Shape {
    private int width;

    protected Square(final int x, final int y, final int width) {
        super(x, y);
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    @Override
    public int getPerimeter() {
        return width * 4;
    }

    @Override
    public int getArea() {
        return width * width;
    }
}
