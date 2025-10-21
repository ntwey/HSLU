package ch.hslu.oop.sw05;

/**
 * Abstrakte Basisklasse einer geometrischen Form mit (x,y)-Position.
 */
public abstract class Shape {
    private int x;
    private int y;

    protected Shape(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /** Verschiebt die Form an eine neue Position. */
    public final void move(final int newX, final int newY) {
        this.x = newX;
        this.y = newY;
    }

    /** @return Umfang als ganze Zahl. */
    public abstract int getPerimeter();

    /** @return Fläche als ganze Zahl. */
    public abstract int getArea();

    public final int getX() { return x; }
    public final int getY() { return y; }
}
