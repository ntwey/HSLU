package ch.hslu.oop.sw05;

/**
 * Quadrat – Variante: Erbt von Rectangle (eine mögliche Designentscheidung).
 */
public class Square extends Rectangle {
    public Square(final int x, final int y, final int sideLength) {
        super(x, y, sideLength, sideLength);
    }
}
