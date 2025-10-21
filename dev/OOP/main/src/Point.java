public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    /**
     * 1 = Quadrant I  (x>0, y>0)
     * 2 = Quadrant II (x<0, y>0)
     * 3 = Quadrant III(x<0, y<0)
     * 4 = Quadrant IV (x>0, y<0)
     * 0 = liegt auf Achse oder im Ursprung
     */
    public int getQuadrant() {
        if (x == 0 || y == 0) return 0;
        if (x > 0 && y > 0) return 1;
        if (x < 0 && y > 0) return 2;
        if (x < 0 && y < 0) return 3;
        return 4; // x>0 && y<0
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
