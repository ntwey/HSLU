```Java
/**
 * Repräsentiert ein einfaches Buch (Klasse).
 * Demonstriert Zustand (Attribute) und Verhalten (Methoden).
 */
public class Buch {
    // Zustand / Attribute 
    private String titel;
    private int seiten; // Primitiver Datentyp

    /**
     * Konstruktor zur Initialisierung eines Objekts.
     * @param titel Der Titel (formaler Parameter) [cite: 34]
     */
    public Buch(String titel) {
        this.titel = titel;
    }

    /**
     * Gibt den Titel zurück (Verhalten).
     * @return Den Titel des Buches.
     */
    public String getTitel() {
        return titel;
    }
}
```

