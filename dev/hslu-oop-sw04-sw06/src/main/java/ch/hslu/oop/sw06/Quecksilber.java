package ch.hslu.oop.sw06;

/** Quecksilber (Hg), Z=80. Schmelzpunkt -39°C, Siedepunkt 357°C. */
public class Quecksilber extends Element {
    public Quecksilber() {
        super("Quecksilber", 80);
    }
    @Override
    public State getStateAt(final int t) {
        if (t < -39) return State.SOLID;
        if (t < 357) return State.LIQUID;
        return State.GAS;
    }
    @Override
    public String toString() {
        // Wiederverwendung der Basisklassen-Implementierung, plus Warnhinweis
        return super.toString() + " – GIFTIG";
    }
}
