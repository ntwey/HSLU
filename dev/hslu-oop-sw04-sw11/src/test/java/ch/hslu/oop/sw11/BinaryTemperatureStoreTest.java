package ch.hslu.oop.sw11;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class BinaryTemperatureStoreTest {

    @Test
    public void testWriteAndReadInt() throws IOException {
        BinaryTemperatureStore store = new BinaryTemperatureStore();
        Path tempFile = Files.createTempFile("binary-int", ".dat");

        int expected = 42;
        store.writeInt(tempFile, expected);
        int actual = store.readInt(tempFile);

        assertEquals(expected, actual);
    }

    @Test
    public void testWriteAndReadTemperatures() throws IOException {
        BinaryTemperatureStore store = new BinaryTemperatureStore();
        Path tempFile = Files.createTempFile("binary-temps", ".dat");

        List<Float> values = Arrays.asList(10.5f, 12.0f, -3.2f);
        store.writeTemperatures(tempFile, values);
        List<Float> result = store.readTemperatures(tempFile);

        assertIterableEquals(values, result);
    }
}
