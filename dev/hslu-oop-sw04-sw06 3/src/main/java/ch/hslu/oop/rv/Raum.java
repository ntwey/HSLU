package ch.hslu.oop.rv;

import java.util.Objects;

/**
 * Ein unveränderlicher Raum mit Raumnummer und Kapazität.
 * <p>
 * Die Klasse ist final, kann also nicht spezialisiert werden.
 * Neue Räume können nur innerhalb des Packages {@code ch.hslu.oop.rv}
 * erzeugt werden (Package-Sichtbarkeit des Konstruktors).
 */
public final class Raum {

    private final int nummer;
    private final int kapazitaet;
    private RaumStatus status = RaumStatus.FREI;

    /**
     * Package-privater Konstruktor – nur Klassen im selben Package
     * können Raum-Instanzen erzeugen.
     */
    Raum(final int nummer, final int kapazitaet) {
        if (nummer < 100 || nummer > 999) {
            throw new IllegalArgumentException("Raumnummer ausserhalb 100..999: " + nummer);
        }
        if (kapazitaet <= 2) {
            throw new IllegalArgumentException("Kapazitaet muss > 2 sein: " + kapazitaet);
        }
        this.nummer = nummer;
        this.kapazitaet = kapazitaet;
    }

    public int getNummer() {
        return nummer;
    }

    public int getKapazitaet() {
        return kapazitaet;
    }

    public RaumStatus getStatus() {
        return status;
    }

    /**
     * @return {@code true}, wenn der Raum frei ist.
     */
    public boolean isFrei() {
        return status == RaumStatus.FREI;
    }

    void reserviere() {
        if (status != RaumStatus.FREI) {
            throw new IllegalStateException("Raum " + nummer + " ist nicht frei.");
        }
        status = RaumStatus.BELEGT;
    }

    void freigeben() {
        if (status == RaumStatus.GESPERRT) {
            throw new IllegalStateException("Raum " + nummer + " ist gesperrt.");
        }
        status = RaumStatus.FREI;
    }

    void sperren() {
        status = RaumStatus.GESPERRT;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Raum)) {
            return false;
        }
        final Raum raum = (Raum) o;
        // Gleichheit nur über die Raumnummer
        return this.nummer == raum.nummer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nummer);
    }

    @Override
    public String toString() {
        return String.format("Raum{%d, Kapazitaet=%d, Status=%s}",
                nummer, kapazitaet, status);
    }
}
