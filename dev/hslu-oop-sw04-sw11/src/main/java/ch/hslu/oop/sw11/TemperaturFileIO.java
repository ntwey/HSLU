package ch.hslu.oop.sw11;

import ch.hslu.oop.sw02.Temperatur;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Hilfsmethoden zum Lesen und Schreiben von {@link Temperatur}-Werten
 * in Binär- und Textdateien.
 * <p>
 * Diese Klasse zeigt den Einsatz von Byte- und Zeichenströmen in Verbindung
 * mit der Temperatur-Klasse und dem Temperaturverlauf.
 */
public final class TemperaturFileIO {

    private TemperaturFileIO() {
        // utility class
    }

    /**
     * Schreibt eine Liste von Temperaturen in eine Binärdatei.
     * <p>
     * Format:
     * <ul>
     *   <li>int: Anzahl Werte</li>
     *   <li>double: Celsius-Wert pro Temperatur</li>
     * </ul>
     *
     * @param path   Zieldatei.
     * @param values Iterable von Temperaturen.
     */
    public static void writeBinary(final Path path,
                                   final Iterable<Temperatur> values) throws IOException {
        Objects.requireNonNull(path, "path");
        Objects.requireNonNull(values, "values");
        final List<Temperatur> list = new ArrayList<>();
        for (Temperatur t : values) {
            list.add(Objects.requireNonNull(t, "temperatur"));
        }

        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(Files.newOutputStream(path)))) {
            out.writeInt(list.size());
            for (Temperatur t : list) {
                out.writeDouble(t.getCelsius());
            }
        }
    }

    /**
     * Liest Temperaturen aus einer Binärdatei, welche mit {@link #writeBinary(Path, Iterable)}
     * geschrieben wurde.
     *
     * @param path Quelldatei.
     * @return Liste der gelesenen Temperaturen.
     */
    public static List<Temperatur> readBinary(final Path path) throws IOException {
        Objects.requireNonNull(path, "path");
        final List<Temperatur> result = new ArrayList<>();
        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(Files.newInputStream(path)))) {
            final int count = in.readInt();
            for (int i = 0; i < count; i++) {
                final double celsius = in.readDouble();
                result.add(Temperatur.createFromCelsius(celsius));
            }
        }
        return result;
    }

    /**
     * Schreibt Temperaturen als Textdatei, ein Wert pro Zeile in Celsius.
     *
     * @param path   Zieldatei.
     * @param values Iterable der Temperaturen.
     */
    public static void writeText(final Path path,
                                 final Iterable<Temperatur> values) throws IOException {
        Objects.requireNonNull(path, "path");
        Objects.requireNonNull(values, "values");
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Temperatur t : values) {
                writer.write(Double.toString(t.getCelsius()));
                writer.newLine();
            }
        }
    }

    /**
     * Liest Temperaturen aus einer Textdatei, ein Wert pro Zeile in Celsius.
     *
     * @param path Quelldatei.
     * @return Liste der gelesenen Temperaturen.
     */
    public static List<Temperatur> readText(final Path path) throws IOException {
        Objects.requireNonNull(path, "path");
        final List<Temperatur> result = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                final double celsius = Double.parseDouble(line);
                result.add(Temperatur.createFromCelsius(celsius));
            }
        }
        return result;
    }
}
