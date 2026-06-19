```Java
/**
 * Interface definiert einen Vertrag (Schnittstelle)[cite: 49].
 */
interface Beweglich {
    void bewegen();
}

/**
 * Abstrakte Klasse dient als Basis für Spezialisierungen[cite: 48, 52].
 */
abstract class Fahrzeug implements Beweglich {
    // Polymorphismus: Methode überschreiben [cite: 58, 59]
    @Override
    public abstract void bewegen();
}

class Auto extends Fahrzeug {
    @Override
    public void bewegen() {
        System.out.println("Auto fährt.");
    }
}
```