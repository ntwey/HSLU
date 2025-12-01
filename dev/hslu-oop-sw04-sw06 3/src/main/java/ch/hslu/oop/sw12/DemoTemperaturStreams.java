package ch.hslu.oop.sw12;

import ch.hslu.oop.sw02.Temperatur;
import ch.hslu.oop.sw08.TemperaturVerlauf;

/**
 * Kleine Demo-Klasse, die die Verwendung der Stream-API mit {@link TemperaturVerlauf}
 * zeigt.
 */
public final class DemoTemperaturStreams {

    private DemoTemperaturStreams() {
        // no instances
    }

    public static void main(final String[] args) {
        final TemperaturVerlauf verlauf = new TemperaturVerlauf();
        verlauf.add(Temperatur.createFromCelsius(10.0));
        verlauf.add(Temperatur.createFromCelsius(15.0));
        verlauf.add(Temperatur.createFromCelsius(20.0));
        verlauf.add(Temperatur.createFromCelsius(-5.0));

        System.out.println("Alle Werte:");
        verlauf.getWerte().forEach(System.out::println);

        System.out.printf("Minimum: %s%n", verlauf.getMin());
        System.out.printf("Maximum: %s%n", verlauf.getMax());
        System.out.printf("Durchschnitt (Stream): %.2f °C%n", verlauf.getAverageCelsius());

        final long countAbove10 = verlauf.countTemperatures(t -> t.getCelsius() > 10.0);
        System.out.printf("Anzahl Werte > 10 °C: %d%n", countAbove10);
    }
}
