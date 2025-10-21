package ch.hslu.oop.sw05;

/**
 * Rechteck-Spezialisierung.
 */
public class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(final int x, final int y, final int width, final int height) {
        super(x, y);
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("width/height must be positive");
        }
        this.width = width;
        this.height = height;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    @Override
    public int getPerimeter() {
        return 2 * width + 2 * height;
    }

    @Override
    public int getArea() {
        return width * height;
    }
}
