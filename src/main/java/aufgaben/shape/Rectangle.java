package aufgaben.shape;

public final class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(final int x, final int y, final int width, final int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public void changeDimension(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getPerimeter() {
        return 2 * width + 2 * height;
    }

    @Override
    public int getArea() {
        return width * height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
