package ch.hslu.oop.sw06;

import ch.hslu.oop.sw04.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

final class PointOverloadTest {
    @Test
    void moveRelativeVectorXY() {
        Point p = new Point(1, 2);
        p.moveRelative(3, 4);
        assertEquals(4, p.getX());
        assertEquals(6, p.getY());
    }

    @Test
    void moveRelativeWithPointVector() {
        Point p = new Point(0, 0);
        p.moveRelative(new Point(2, -1));
        assertEquals(2, p.getX());
        assertEquals(-1, p.getY());
    }

    @Test
    void moveRelativePolarZeroAngle() {
        Point p = new Point(0, 0);
        p.moveRelative(0.0, 5.0); // 0 rad => (5,0)
        assertEquals(5, p.getX());
        assertEquals(0, p.getY());
    }
}
