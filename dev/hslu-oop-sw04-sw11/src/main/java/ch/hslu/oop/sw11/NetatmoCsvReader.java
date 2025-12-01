package ch.hslu.oop.sw11;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class NetatmoCsvReader {

    private final NetatmoLineParser lineParser = new NetatmoLineParser();

    public TemperatureCourse readFile(Path path) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("path is null");
        }
        if (!Files.exists(path)) {
            throw new IOException("file does not exist: " + path);
        }

        TemperatureCourse course = new TemperatureCourse();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                try {
                    Measurement measurement = lineParser.parse(trimmed);
                    course.add(measurement);
                } catch (IllegalArgumentException ex) {
                    // ignore broken lines in a simple way
                }
            }
        }

        return course;
    }
}
