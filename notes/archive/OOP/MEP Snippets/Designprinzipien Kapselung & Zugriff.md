```java
/**
 * Demonstriert Datenkapselung (Encapsulation).
 * Interner Zustand ist privat, Zugriff erfolgt über kontrollierte Schnittstellen.
 */
public class Konto {
    // Private Attribute: Information Hiding [cite: 119]
    private double saldo;
    private final String kontonummer; // 'final': Wert kann nach Initialisierung nicht mehr geändert werden [cite: 69]

    public Konto(String kontonummer) {
        this.kontonummer = kontonummer;
    }

    /**
     * Kontrollierter Zugriff (Setter mit Validierung).
     * @param betrag Der einzuzahlende Betrag.
     */
    public void einzahlen(double betrag) {
        if (betrag > 0) {
            this.saldo += betrag;
        }
    }

    // Getter ermöglichen lesenden Zugriff auf privaten Zustand [cite: 117]
    public double getSaldo() {
        return saldo;
    }
}
```