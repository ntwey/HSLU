package ch.hslu.oop.sw06;

/**
 * Stickstoff (N), Z=7. Schmelzpunkt -210°C, Siedepunkt -196°C.
 */
public class Stickstoff extends Element {
    public Stickstoff() {
        super("Stickstoff", 7);
    }

    @Override
    public State getStateAt(final int t) {
        if (t < -210) return State.SOLID;
        if (t < -196) return State.LIQUID;
        return State.GAS;
    }
}
