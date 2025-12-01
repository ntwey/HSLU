package ch.hslu.oop.rv;

/**
 * Kleine Demo-Anwendung für die Raumverwaltung.
 */
public final class Demo {

    private Demo() {
        // no instances
    }

    public static void main(final String[] args) {
        final RaumVerwaltung verwaltung = new RaumVerwaltung();

        // Reservierungen gemäss Aufgabe
        verwaltung.reserviereRaum(11);
        verwaltung.reserviereRaum(6);
        verwaltung.reserviereRaum(17);

        System.out.println("Alle Räume:");
        for (Raum raum : verwaltung.getAlleRaeume()) {
            System.out.println(raum);
        }
    }
}
