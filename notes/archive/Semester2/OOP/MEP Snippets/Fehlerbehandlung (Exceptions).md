```Java
/**
 * Eigene Exception-Definition[cite: 74].
 */
public class UngueltigeEingabeException extends Exception {
    public UngueltigeEingabeException(String msg) { super(msg); }
}

public class Rechner {
    /**
     * @throws UngueltigeEingabeException wenn b == 0.
     */
    public int dividiere(int a, int b) throws UngueltigeEingabeException {
        if (b == 0) {
            throw new UngueltigeEingabeException("Division durch Null!"); // Auslösen [cite: 74]
        }
        return a / b;
    }
}
```