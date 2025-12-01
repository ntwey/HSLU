package ch.hslu.oop.sw04;

/**
 * Schnittstelle für ein- und ausschaltbare Komponenten.
 * Die Semantik ist wie folgt:
 * <ul>
 *   <li>{@link #switchOn()} versetzt die Komponente in den eingeschalteten Zustand.</li>
 *   <li>{@link #switchOff()} versetzt die Komponente in den ausgeschalteten Zustand.</li>
 *   <li>{@link #isSwitchedOn()} liefert {@code true}, falls die Komponente eingeschaltet ist.</li>
 *   <li>{@link #isSwitchedOff()} ist das Negativ dazu.</li>
 * </ul>
 */
public interface Switchable {
    /**
     * Schaltet die Komponente ein.
     */
    void switchOn();

    /**
     * Schaltet die Komponente aus.
     */
    void switchOff();

    /**
     * @return {@code true}, wenn eingeschaltet.
     */
    boolean isSwitchedOn();

    /**
     * @return {@code true}, wenn ausgeschaltet.
     */
    default boolean isSwitchedOff() {
        return !isSwitchedOn();
    }
}
