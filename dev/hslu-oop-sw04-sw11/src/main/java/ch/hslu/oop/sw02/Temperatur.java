package ch.hslu.oop.sw02;

import java.util.Objects;

/**
 * Eine einfache Temperatur-Klasse, die intern Grad Celsius speichert.
 * Gültiger Bereich: 0 K bis 1000 K ([-273.15 °C, 726.85 °C]).
 * <p>
 * Die Klasse ist immutable: Nach der Erzeugung lässt sich der Wert nicht mehr ändern.
 */
public final class Temperatur {

    private static final double KELVIN_OFFSET = 273.15;
    private static final double KELVIN_MIN = 0.0;
    private static final double KELVIN_MAX = 1000.0;

    private final double celsius;

    /**
     * Erzeugt eine Standardtemperatur von 20 °C.
     */
    public Temperatur() {
        this(20.0);
    }

    /**
     * Erzeugt eine Temperatur in Grad Celsius.
     *
     * @param celsius Temperatur in °C.
     * @throws IllegalArgumentException wenn die Temperatur ausserhalb 0..1000 K liegt.
     */
    public Temperatur(final double celsius) {
        validateRangeCelsius(celsius);
        this.celsius = celsius;
    }

    /**
     * Factory-Methode zur Erzeugung aus Celsius.
     */
    public static Temperatur createFromCelsius(final double celsius) {
        return new Temperatur(celsius);
    }

    /**
     * Factory-Methode zur Erzeugung aus Kelvin.
     */
    public static Temperatur createFromKelvin(final double kelvin) {
        validateRangeKelvin(kelvin);
        return new Temperatur(kelvinToCelsius(kelvin));
    }

    private static void validateRangeCelsius(final double celsius) {
        final double kelvin = celsiusToKelvin(celsius);
        if (kelvin < KELVIN_MIN || kelvin > KELVIN_MAX) {
            throw new IllegalArgumentException(
                    String.format("Temperatur ausserhalb 0..1000 K: %.2f °C (%.2f K)", celsius, kelvin));
        }
    }

    private static void validateRangeKelvin(final double kelvin) {
        if (kelvin < KELVIN_MIN || kelvin > KELVIN_MAX) {
            throw new IllegalArgumentException(
                    String.format("Temperatur ausserhalb 0..1000 K: %.2f K", kelvin));
        }
    }

    /**
     * Umrechnung °C -> K.
     */
    public static double celsiusToKelvin(final double celsius) {
        return celsius + KELVIN_OFFSET;
    }

    /**
     * Umrechnung K -> °C.
     */
    public static double kelvinToCelsius(final double kelvin) {
        return kelvin - KELVIN_OFFSET;
    }

    public double getCelsius() {
        return this.celsius;
    }

    public double getKelvin() {
        return celsiusToKelvin(this.celsius);
    }

    public double getFahrenheit() {
        return this.celsius * 1.8 + 32.0;
    }

    @Override
    public String toString() {
        return String.format("Temperatur{%.2f °C | %.2f K | %.2f °F}", getCelsius(), getKelvin(), getFahrenheit());
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Temperatur that)) {
            return false;
        }
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
