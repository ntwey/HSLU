package ch.hslu.oop.sw04;

/**
 * Spezialisierte Schnittstelle, die die Anzahl der Schaltvorgänge erfasst.
 */
public interface CountingSwitchable extends Switchable {
    /**
     * @return Anzahl Ein- bzw. Ausschaltvorgänge seit Erstellung.
     */
    long getSwitchCount();
}
