package ch.hslu.oop.sw08;

import ch.hslu.oop.sw02.Temperatur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Verwaltet einen Verlauf von Temperatur-Werten.
 * <p>
 * Die Klasse speichert {@link Temperatur}-Objekte intern in einer Collection
 * und bietet einfache Auswertungsfunktionen (Minimum, Maximum, Durchschnitt).
 * Zusätzlich werden Events ausgelöst, wenn ein neues Minimum oder Maximum erreicht wird.
 */
public final class TemperaturVerlauf {

    private final List<Temperatur> werte = new ArrayList<>();

    private final List<TemperaturVerlaufListener> listeners = new ArrayList<>();

    private Temperatur minimum;
    private Temperatur maximum;

    /**
     * Fügt dem Verlauf einen Temperaturwert hinzu.
     *
     * @param temperatur Temperatur, darf nicht {@code null} sein.
     */
    public void add(final Temperatur temperatur) {
        final Temperatur value = Objects.requireNonNull(temperatur, "temperatur");
        werte.add(value);
        updateExtremaAndFireEvents(value);
    }

    /**
     * Entfernt alle gespeicherten Temperaturwerte.
     */
    public void clear() {
        werte.clear();
        minimum = null;
        maximum = null;
    }

    /**
     * Anzahl gespeicherter Temperaturwerte.
     *
     * @return Anzahl Werte.
     */
    public int getCount() {
        return werte.size();
    }

    /**
     * @return {@code true}, wenn keine Werte enthalten sind.
     */
    public boolean isEmpty() {
        return werte.isEmpty();
    }

    /**
     * Liefert den maximalen Temperaturwert.
     *
     * @return maximale Temperatur.
     * @throws IllegalStateException wenn keine Werte vorhanden sind.
     */
    public Temperatur getMax() {
        if (maximum == null) {
            throw new IllegalStateException("Keine Temperaturen im Verlauf.");
        }
        return maximum;
    }

    /**
     * Liefert den minimalen Temperaturwert.
     *
     * @return minimale Temperatur.
     * @throws IllegalStateException wenn keine Werte vorhanden sind.
     */
    public Temperatur getMin() {
        if (minimum == null) {
            throw new IllegalStateException("Keine Temperaturen im Verlauf.");
        }
        return minimum;
    }

    /**
     * Berechnet die Durchschnittstemperatur über alle enthaltenen Werte.
     * Implementierung auf Basis der Stream API.
     *
     * @return Durchschnitt in Grad Celsius.
     * @throws IllegalStateException wenn keine Werte vorhanden sind.
     */
    public double getAverageCelsius() {
        if (werte.isEmpty()) {
            throw new IllegalStateException("Keine Temperaturen im Verlauf.");
        }
        final OptionalDouble avg = werte.stream()
                .mapToDouble(Temperatur::getCelsius)
                .average();
        return avg.orElseThrow(() -> new IllegalStateException("Keine Temperaturen im Verlauf."));
    }

    /**
     * Allgemeine Zählmethode auf Basis eines {@link Predicate}-Filters.
     *
     * @param predicate Bedingung, die die zu zählenden Werte erfüllt.
     * @return Anzahl der Temperaturen, welche die Bedingung erfüllen.
     */
    public long countTemperatures(final Predicate<Temperatur> predicate) {
        Objects.requireNonNull(predicate, "predicate");
        return werte.stream()
                .filter(predicate)
                .count();
    }

    /**
     * Liefert alle Temperaturen sortiert nach ihrem Celsius-Wert.
     *
     * @return neue Liste mit sortierten Temperaturen.
     */
    public List<Temperatur> getSortedByCelsius() {
        return werte.stream()
                .sorted(Temperatur::compareTo)
                .collect(Collectors.toList());
    }

    /**
     * Liefert eine defensive Kopie aller gespeicherten Werte.
     *
     * @return Liste der Temperaturen.
     */
    public List<Temperatur> getWerte() {
        return new ArrayList<>(werte);
    }

    /**
     * Fügt einen Listener hinzu.
     */
    public void addTemperaturVerlaufListener(final TemperaturVerlaufListener listener) {
        listeners.add(Objects.requireNonNull(listener, "listener"));
    }

    /**
     * Entfernt einen Listener.
     */
    public void removeTemperaturVerlaufListener(final TemperaturVerlaufListener listener) {
        listeners.remove(listener);
    }

    private void updateExtremaAndFireEvents(final Temperatur neu) {
        // Minimum
        if (minimum == null || neu.compareTo(minimum) < 0) {
            Temperatur alt = minimum;
            minimum = neu;
            fireEvent(TemperaturEvent.Type.MINIMUM, alt, minimum);
        }
        // Maximum
        if (maximum == null || neu.compareTo(maximum) > 0) {
            Temperatur alt = maximum;
            maximum = neu;
            fireEvent(TemperaturEvent.Type.MAXIMUM, alt, maximum);
        }
    }

    private void fireEvent(final TemperaturEvent.Type type,
                           final Temperatur alt,
                           final Temperatur neu) {
        if (Objects.equals(alt, neu)) {
            return;
        }
        if (listeners.isEmpty()) {
            return;
        }
        final TemperaturEvent event = new TemperaturEvent(this, type, alt, neu);
        listeners.forEach(listener -> listener.extremumChanged(event));
    }

    @Override
    public String toString() {
        if (werte.isEmpty()) {
            return "TemperaturVerlauf{leer}";
        }
        return String.format("TemperaturVerlauf{count=%d, min=%s, max=%s, avg=%.2f °C}",
                getCount(), getMin(), getMax(), getAverageCelsius());
    }
}
