package ch.hslu.oop.rv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Einfache Verwaltung von Räumen.
 * <p>
 * Verwendet eine sortierte Map, um schnellen Zugriff über die Raumnummer
 * zu ermöglichen.
 */
public final class RaumVerwaltung {

    private final SortedMap<Integer, Raum> raeume = new TreeMap<>();

    public RaumVerwaltung() {
        // Standard-Testdaten gemäss Aufgabenstellung
        addRaum(new Raum(600, 18));
        addRaum(new Raum(602, 6));
        addRaum(new Raum(603, 12));
        addRaum(new Raum(605, 24));
        addRaum(new Raum(610, 12));
    }

    private void addRaum(final Raum raum) {
        raeume.put(raum.getNummer(), raum);
    }

    /**
     * Liefert einen Raum nach Nummer oder {@code null}, falls nicht vorhanden.
     */
    public Raum getRaum(final int nummer) {
        return raeume.get(nummer);
    }

    /**
     * @return unveränderliche Liste aller Räume, nach Nummer sortiert.
     */
    public List<Raum> getAlleRaeume() {
        return Collections.unmodifiableList(new ArrayList<>(raeume.values()));
    }

    /**
     * Reserviert für die angegebene Personenzahl einen passenden Raum.
     * <p>
     * Es wird der jeweils kleinste noch passende (und freie) Raum gewählt.
     *
     * @param personen Anzahl Personen.
     * @return reservierter Raum oder {@code null}, falls keiner passt.
     */
    public Raum reserviereRaum(final int personen) {
        if (personen <= 0) {
            throw new IllegalArgumentException("Personenzahl muss > 0 sein.");
        }
        final Optional<Raum> kandidat = raeume.values().stream()
                .filter(r -> r.isFrei() && r.getKapazitaet() >= personen)
                .min(Comparator.comparingInt(Raum::getKapazitaet)
                        .thenComparingInt(Raum::getNummer));
        if (kandidat.isPresent()) {
            final Raum raum = kandidat.get();
            raum.reserviere();
            return raum;
        }
        return null;
    }

    /**
     * Gibt einen Raum wieder frei.
     *
     * @param raum zu befreiender Raum.
     * @return {@code true}, wenn der Raum bekannt war und freigegeben wurde.
     */
    public boolean freigeben(final Raum raum) {
        if (raum == null) {
            return false;
        }
        for (Map.Entry<Integer, Raum> entry : raeume.entrySet()) {
            if (entry.getValue() == raum) {
                raum.freigeben();
                return true;
            }
        }
        return false;
    }

    /**
     * Überladene Variante: Freigabe anhand der Raumnummer.
     *
     * @param nummer Raumnummer.
     * @return {@code true}, wenn der Raum bekannt war und freigegeben wurde.
     */
    public boolean freigeben(final int nummer) {
        final Raum raum = raeume.get(nummer);
        if (raum == null) {
            return false;
        }
        raum.freigeben();
        return true;
    }
}
