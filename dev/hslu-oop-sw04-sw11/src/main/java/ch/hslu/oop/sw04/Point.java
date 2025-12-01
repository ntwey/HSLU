package ch.hslu.oop.sw04;

import java.util.Objects;

public class Point {
    private int x;
    private int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Point(final Point point) {
        this(point.getX(), point.getY());
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

    public void moveRelative(final int dx, final int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void moveRelative(final Point vector) {
        this.moveRelative(vector.getX(), vector.getY());
    }

    public void moveRelative(final double angleRad, final double length) {
        final long dx = Math.round(Math.cos(angleRad) * length);
        final long dy = Math.round(Math.sin(angleRad) * length);
        this.moveRelative((int) dx, (int) dy);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point point)) return false;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
