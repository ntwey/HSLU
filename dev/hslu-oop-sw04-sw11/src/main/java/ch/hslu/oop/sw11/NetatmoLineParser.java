package ch.hslu.oop.sw11;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NetatmoLineParser {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("\"yyyy/MM/dd HH:mm:ss\"");

    public Measurement parse(String line) {
        if (line == null) {
            throw new IllegalArgumentException("line is null");
        }
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("line is empty");
        }

        String[] parts = trimmed.split(";");
        if (parts.length < 4) {
            throw new IllegalArgumentException("not enough columns: " + line);
        }

        String timestampString = parts[1];
        LocalDateTime timestamp = LocalDateTime.parse(timestampString, FORMATTER);

        float temperature = Float.parseFloat(parts[2]);
        int humidity = Integer.parseInt(parts[3]);

        return new Measurement(timestamp, temperature, humidity);
    }
}
