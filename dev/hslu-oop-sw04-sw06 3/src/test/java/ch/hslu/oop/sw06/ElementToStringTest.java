package ch.hslu.oop.sw06;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

final class ElementToStringTest {
    @Test
    void elementBaseToString() {
        Element e = new Blei();
        assertTrue(e.toString().contains("Blei"));
    }
    @Test
    void mercuryToStringContainsWarning() {
        Element e = new Quecksilber();
        assertTrue(e.toString().contains("GIFTIG"));
    }
}
