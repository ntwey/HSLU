package ch.hslu.oop.sw11;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BinaryTemperatureStore {

    public void writeInt(Path path, int value) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("path is null");
        }
        try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(path))) {
            out.writeInt(value);
            out.flush();
        }
    }

    public int readInt(Path path) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("path is null");
        }
        try (DataInputStream in = new DataInputStream(Files.newInputStream(path))) {
            return in.readInt();
        }
    }

    public void writeTemperatures(Path path, List<Float> temperatures) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("path is null");
        }
        if (temperatures == null) {
            throw new IllegalArgumentException("temperatures is null");
        }

        try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(path))) {
            out.writeInt(temperatures.size());
            for (Float temp : temperatures) {
                if (temp == null) {
                    out.writeFloat(Float.NaN);
                } else {
                    out.writeFloat(temp);
                }
            }
            out.flush();
        }
    }

    public List<Float> readTemperatures(Path path) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("path is null");
        }

        List<Float> result = new ArrayList<>();
        try (DataInputStream in = new DataInputStream(Files.newInputStream(path))) {
            int count = in.readInt();
            for (int i = 0; i < count; i++) {
                result.add(in.readFloat());
            }
        }
        return result;
    }
}
