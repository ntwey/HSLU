package ch.hslu.oop.sw11;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NetatmoLineParserTest {

    @Test
    public void testParseValidLine() {
        NetatmoLineParser parser = new NetatmoLineParser();
        String line = "1473517749;\"2023/01/10 16:29:09\";30.4;60";

        Measurement m = parser.parse(line);

        assertEquals(LocalDateTime.of(2023, 1, 10, 16, 29, 9), m.getTimestamp());
        assertEquals(30.4f, m.getTemperature(), 0.0001f);
        assertEquals(60, m.getHumidity());
    }

    @Test
    public void testParseInvalidLineTooShort() {
        NetatmoLineParser parser = new NetatmoLineParser();
        String line = "123;\"2023/01/10 16:29:09\";30.4";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(line));
    }
}
