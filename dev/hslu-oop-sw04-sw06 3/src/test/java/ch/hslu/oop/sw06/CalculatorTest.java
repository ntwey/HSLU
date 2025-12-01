package ch.hslu.oop.sw06;

import ch.hslu.oop.sw06.calc.Calculator;
import ch.hslu.oop.sw06.calc.SimpleCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

final class CalculatorTest {
    private final Calculator calc = new SimpleCalculator();

    @Test
    void addZeros() {
        assertEquals(0, calc.addition(0, 0));
    }
    @Test
    void addPositive() {
        assertEquals(9, calc.addition(4, 5));
    }
    @Test
    void addNegative() {
        assertEquals(-1, calc.addition(2, -3));
    }
}
