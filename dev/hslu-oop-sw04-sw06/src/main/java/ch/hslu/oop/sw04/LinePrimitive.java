package ch.hslu.oop.sw04;

/**
 * Alternative Darstellung der Linie mit primitiven Feldern.
 */
public class LinePrimitive {
    private int x1, y1, x2, y2;

    public LinePrimitive(final int x1, final int y1, final int x2, final int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() { return x1; }
    public int getY1() { return y1; }
    public int getX2() { return x2; }
    public int getY2() { return y2; }

    public void setStart(final int x, final int y) {
        this.x1 = x; this.y1 = y;
    }

    public void setEnd(final int x, final int y) {
        this.x2 = x; this.y2 = y;
    }

    public void moveBy(final int dx, final int dy) {
        this.x1 += dx; this.y1 += dy;
        this.x2 += dx; this.y2 += dy;
    }
}
