package ch.hslu.oop.sw04;

/**
 * Unverfänglicher Punkt in Z2 mit mutablen Koordinaten.
 * Enthält überladene moveRelative-Methoden (SW06) und einen Copy-Constructor.
 */
public class Point {
    private int x;
    private int y;

    /** Standardkonstruktor. */
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /** Copy-Constructor ohne Coderedundanzen. */
    public Point(final Point point) {
        this(point.getX(), point.getY());
    }

    public int getX() { return x; }
    public void setX(final int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(final int y) { this.y = y; }

    /** Verschiebt relativ um einen kartesischen Vektor (dx, dy). */
    public void moveRelative(final int dx, final int dy) {
        this.x += dx;
        this.y += dy;
    }

    /** Überladung: Verwendet einen anderen Point als Vektor. */
    public void moveRelative(final Point vector) {
        this.moveRelative(vector.getX(), vector.getY());
    }

    /**
     * Überladung: Verschiebt relativ via Polarkoordinaten.
     * @param angleRad Winkel im Bogenmaß (radians).
     * @param length Betrag der Verschiebung.
     */
    public void moveRelative(final double angleRad, final double length) {
        final long dx = Math.round(Math.cos(angleRad) * length);
        final long dy = Math.round(Math.sin(angleRad) * length);
        this.moveRelative((int) dx, (int) dy);
    }
}
