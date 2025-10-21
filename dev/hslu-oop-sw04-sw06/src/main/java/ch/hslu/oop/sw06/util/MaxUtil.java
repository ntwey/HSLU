package ch.hslu.oop.sw06.util;

/** Triviale Hilfsfunktionen für Unit-Tests. */
public class MaxUtil {
    private MaxUtil() { }
    /** Maximum aus zwei ints. */
    public static int max(final int a, final int b) {
        return (a >= b) ? a : b;
    }
    /** Überladung: Maximum aus drei ints. */
    public static int max(final int a, final int b, final int c) {
        return max(max(a, b), c);
    }
}
