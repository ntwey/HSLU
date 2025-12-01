package ch.hslu.oop.sw06;

import ch.hslu.oop.sw06.util.MaxUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

final class MaxUtilTest {
    @Test
    void maxFirstGreater() {
        assertEquals(5, MaxUtil.max(5, 3));
    }
    @Test
    void maxSecondGreater() {
        assertEquals(7, MaxUtil.max(2, 7));
    }
    @Test
    void maxEqualValues() {
        assertEquals(4, MaxUtil.max(4, 4));
    }
}
