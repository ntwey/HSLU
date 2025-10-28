package ch.hslu.oop.sw04;

/**
 * Einfache Schnittstelle, um Objekten einen Namen zu geben.
 */
public interface Named {
    /**
     * @return der aktuelle Name.
     */
    String getName();

    /**
     * Setzt den Namen.
     */
    void setName(final String name);
}
