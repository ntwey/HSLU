```Java
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class MeinTest {
    @Test
    void testDividiere() {
        Rechner r = new Rechner();
        // AssertJ für lesbare Assertions [cite: 141]
        assertThat(4).isEqualTo(2 + 2);
        
        // Testen von Exceptions [cite: 75, 140]
        assertThatThrownBy(() -> r.dividiere(10, 0))
            .isInstanceOf(UngueltigeEingabeException.class);
    }
}
```