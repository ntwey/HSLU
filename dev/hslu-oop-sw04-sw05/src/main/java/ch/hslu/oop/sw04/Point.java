package ch.hslu.oop.sw04;

/**
 * Unverfänglicher Punkt in Z2 mit mutablen Koordinaten.
 */
public final class Point {
    private int x;
    private int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }
}
