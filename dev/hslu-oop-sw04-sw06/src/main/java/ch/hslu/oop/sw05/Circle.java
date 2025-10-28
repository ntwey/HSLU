package ch.hslu.oop.sw05;

/**
 * Kreis-Spezialisierung.
 */
public class Circle extends Shape {
    private final int radius;

    public Circle(final int x, final int y, final int radius) {
        super(x, y);
        if (radius <= 0) {
            throw new IllegalArgumentException("radius must be positive");
        }
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public int getDiameter() {
        return 2 * radius;
    }

    @Override
    public int getPerimeter() {
        // Ganzzahlnäherung: 2*pi*r ~ 2*3*radius
        return 2 * 3 * radius;
    }

    @Override
    public int getArea() {
        // Ganzzahlnäherung: pi*r^2 ~ 3*radius*radius
        return 3 * radius * radius;
    }
}
