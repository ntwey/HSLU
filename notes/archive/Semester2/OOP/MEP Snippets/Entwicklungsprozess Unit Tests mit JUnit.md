```Java

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*; // AssertJ Library [cite: 141, 145]

class RechnungTest {
    
    @Test
    void testAddition() {
        int result = 2 + 3;
        // AssertJ bietet eine flüssige API für Tests [cite: 145]
        assertThat(result).isEqualTo(5);
    }

    @Test
    void testFehlerhandling() {
        // Explizites Testen von Exceptions [cite: 75, 140]
        assertThatThrownBy(() -> {
            throw new IllegalArgumentException("Fehler");
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Fehler");
    }
}

```