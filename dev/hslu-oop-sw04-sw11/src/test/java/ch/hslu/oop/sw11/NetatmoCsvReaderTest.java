package ch.hslu.oop.sw11;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NetatmoCsvReaderTest {

    @Test
    public void testReadFile() throws IOException {
        Path tempFile = Files.createTempFile("netatmo", ".csv");
        String content = ""
                + "1473517749;"2023/01/10 16:29:09";30.4;60\n"
                + "1473518057;"2023/01/10 16:34:17";31.0;59\n"
                + "1473518364;"2023/01/10 16:39:24";29.5;58\n";
        Files.write(tempFile, content.getBytes(StandardCharsets.UTF_8));

        NetatmoCsvReader reader = new NetatmoCsvReader();
        TemperatureCourse course = reader.readFile(tempFile);

        assertEquals(3, course.size());
        assertEquals(29.5f, course.getMinTemperature(), 0.0001f);
        assertEquals(31.0f, course.getMaxTemperature(), 0.0001f);

        float expectedAverage = (30.4f + 31.0f + 29.5f) / 3.0f;
        assertEquals(expectedAverage, course.getAverageTemperature(), 0.0001f);

        Measurement max = course.getMeasurementWithMaxTemperature();
        assertEquals(LocalDateTime.of(2023, 1, 10, 16, 34, 17), max.getTimestamp());
    }
}
