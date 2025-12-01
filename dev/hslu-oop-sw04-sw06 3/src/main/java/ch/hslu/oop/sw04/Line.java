package ch.hslu.oop.sw04;

/**
 * Linie mit Start- und Endpunkt. Achtung: Vermeidet Repräsentationsexposition
 * durch defensive Kopien in Gettern/Settern!
 */
public class Line {
    private Point start;
    private Point end;

    public Line(final Point start, final Point end) {
        // Defensive Kopien beim Konstruktor
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    public Line(final int x1, final int y1, final int x2, final int y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return defensive Kopie des Startpunktes.
     */
    public Point getStart() {
        return new Point(start.getX(), start.getY());
    }

    /**
     * Setzt den Startpunkt (defensive Kopie).
     */
    public void setStart(final Point p) {
        this.start = new Point(p.getX(), p.getY());
    }

    /**
     * @return defensive Kopie des Endpunktes.
     */
    public Point getEnd() {
        return new Point(end.getX(), end.getY());
    }

    /**
     * Setzt den Endpunkt (defensive Kopie).
     */
    public void setEnd(final Point p) {
        this.end = new Point(p.getX(), p.getY());
    }

    /**
     * Verschiebt die Linie um (dx, dy).
     */
    public void moveBy(final int dx, final int dy) {
        this.start.setX(this.start.getX() + dx);
        this.start.setY(this.start.getY() + dy);
        this.end.setX(this.end.getX() + dx);
        this.end.setY(this.end.getY() + dy);
    }
}
