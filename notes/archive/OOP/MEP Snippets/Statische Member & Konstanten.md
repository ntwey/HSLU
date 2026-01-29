```java
/**
 * Zeigt den Einsatz von 'static' (Klassen-Ebene) und 'final' (Konstante).
 */
public class Konfiguration {
    // Statische Konstante: Gilt für die gesamte Klasse, nicht pro Objekt 
    public static final String APP_NAME = "OOP-App";
    
    // Statische Variable: Zähler über alle Instanzen hinweg
    private static int instanzZaehler = 0;

    public Konfiguration() {
        instanzZaehler++;
    }

    /**
     * Statische Methode: Kann ohne Objekt-Instanz aufgerufen werden[cite: 68].
     * @return Anzahl der erstellten Instanzen.
     */
    public static int getInstanzAnzahl() {
        return instanzZaehler;
    }
}
```