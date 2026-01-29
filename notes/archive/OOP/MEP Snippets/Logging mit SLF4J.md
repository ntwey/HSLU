```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessLogik {
    // Logger-Instanz für die Klasse [cite: 143]
    private static final Logger LOG = LoggerFactory.getLogger(BusinessLogik.class);

    public void prozess(int wert) {
        LOG.info("Prozess gestartet mit Wert: {}", wert); // Parametrisiertes Logging
        
        if (wert < 0) {
            LOG.error("Ungültiger Wert erkannt!"); // Logging von Fehlersituationen [cite: 142]
        }
    }
}
```