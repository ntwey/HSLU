package ch.hslu.oop.sw06;

import java.util.Objects;

/**
 * Abstrakte Basisklasse für chemische Elemente.
 */
public abstract class Element {
    private final String name;
    private final int atomicNumber;

    protected Element(final String name, final int atomicNumber) {
        this.name = name;
        this.atomicNumber = atomicNumber;
    }

    /**
     * Aggregatzustand bei gegebener Temperatur in °C.
     */
    public abstract State getStateAt(final int temperatureCelsius);

    public final String getName() {
        return name;
    }

    public final int getAtomicNumber() {
        return atomicNumber;
    }

    @Override
    public String toString() {
        return name + " " + atomicNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Element element)) return false;
        return atomicNumber == element.atomicNumber && Objects.equals(name, element.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, atomicNumber);
    }

    public int compareTo(final Element element) {
        return atomicNumber - element.atomicNumber;
    }
}
