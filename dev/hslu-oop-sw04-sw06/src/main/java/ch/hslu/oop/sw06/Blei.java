package ch.hslu.oop.sw06;

/** Blei (Pb), Z=82. Schmelzpunkt 327°C, Siedepunkt 1749°C. */
public class Blei extends Element {
    public Blei() {
        super("Blei", 82);
    }
    @Override
    public State getStateAt(final int t) {
        if (t < 327) return State.SOLID;
        if (t < 1749) return State.LIQUID;
        return State.GAS;
    }
}
