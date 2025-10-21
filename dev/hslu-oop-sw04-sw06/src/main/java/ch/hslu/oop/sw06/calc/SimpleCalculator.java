package ch.hslu.oop.sw06.calc;

/** Einfache Implementation des Calculators. */
public class SimpleCalculator implements Calculator {
    @Override
    public int addition(final int a, final int b) {
        return a + b; // bewusst ohne Overflow-Handling
    }
}
