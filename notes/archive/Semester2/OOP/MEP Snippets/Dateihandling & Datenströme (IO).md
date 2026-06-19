```java

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Beispiel für Dateizugriff mit Ressourcen-Management (try-with-resources).
 */
public class DateiService {

    /**
     * Schreibt Text in eine Datei unter Verwendung von UTF-8 Encoding[cite: 84, 85].
     */
    public void schreibeText(String pfad, String inhalt) {
        // FileWriter/BufferedWriter für Zeichen-Datenströme [cite: 83]
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(pfad), StandardCharsets.UTF_8))) {
            writer.write(inhalt);
        } catch (IOException e) {
            e.printStackTrace(); // Fehlerbehandlung [cite: 74]
        }
    }
}