package ch.hslu.oop.sw02;

import java.util.Objects;

/**
 * Eine einfache Temperatur-Klasse, die intern Grad Celsius speichert.
 * Gültiger Bereich: 0 K bis 1000 K ([-273.15 °C, 726.85 °C]).
 */
public final class Temperatur {

    private static final double KELVIN_OFFSET = 273.15;
    private static final double KELVIN_MIN = 0.0;
    private static final double KELVIN_MAX = 1000.0;


    private double celsius;

    public Temperatur() {
        this(20.0);
    }

    public Temperatur(final double celsius) {
        validateRangeCelsius(celsius);
        this.celsius = celsius;
    }

    private static void validateRangeCelsius(final double celsius) {
        final double kelvin = celsius + KELVIN_OFFSET;
        if (kelvin < KELVIN_MIN || kelvin > KELVIN_MAX) {
            throw new IllegalArgumentException(
                    String.format("Temperatur ausserhalb 0..1000 K: %.2f °C (%.2f K)", celsius, kelvin));
        }
    }

    public double getCelsius() {
        return this.celsius;
    }

    public void setCelsius(final double celsius) {
        validateRangeCelsius(celsius);
        this.celsius = celsius;
    }

    public double getKelvin() {
        return this.celsius + KELVIN_OFFSET;
    }

    public void setKelvin(final double kelvin) {
        if (kelvin < KELVIN_MIN || kelvin > KELVIN_MAX) {
            throw new IllegalArgumentException("Kelvin ausserhalb Bereich 0..1000 K: " + kelvin);
        }
        this.celsius = kelvin - KELVIN_OFFSET;
    }

    public double getFahrenheit() {
        return this.celsius * 1.8 + 32.0;
    }

    public void changeByCelsius(final double deltaC) {
        final double neu = this.celsius + deltaC;
        validateRangeCelsius(neu);
        this.celsius = neu;
    }

    public void changeByKelvin(final double deltaK) {
        changeByCelsius(deltaK); // gleiche Schrittweite
    }

    @Override
    public String toString() {
        return String.format("Temperatur{%.2f °C | %.2f K | %.2f °F}", getCelsius(), getKelvin(), getFahrenheit());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Temperatur that)) return false;
        return Double.compare(celsius, that.celsius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(celsius);
    }

    public int compareTo(final Temperatur that) {
        return Double.compare(this.celsius, that.celsius);
    }
}
