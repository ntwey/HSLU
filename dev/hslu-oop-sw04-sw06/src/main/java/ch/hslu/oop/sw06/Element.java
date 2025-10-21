package ch.hslu.oop.sw06;

/** Abstrakte Basisklasse für chemische Elemente. */
public abstract class Element {
    private final String name;
    private final int atomicNumber;

    protected Element(final String name, final int atomicNumber) {
        this.name = name;
        this.atomicNumber = atomicNumber;
    }

    /** Aggregatzustand bei gegebener Temperatur in °C. */
    public abstract State getStateAt(final int temperatureCelsius);

    public final String getName() { return name; }
    public final int getAtomicNumber() { return atomicNumber; }

    @Override
    public String toString() {
        return String.format("%s (Z=%d)", name, atomicNumber);
    }
}
