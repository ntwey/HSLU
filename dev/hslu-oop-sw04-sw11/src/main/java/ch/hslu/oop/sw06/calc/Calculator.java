package ch.hslu.oop.sw06.calc;

/**
 * Schnittstelle für einen simplen Taschenrechner.
 */
public interface Calculator {
    /**
     * Addiert zwei Summanden als Ganzzahlen.
     *
     * @return Summe a + b (ohne Überlaufprüfung).
     */
    int addition(int a, int b);
}
