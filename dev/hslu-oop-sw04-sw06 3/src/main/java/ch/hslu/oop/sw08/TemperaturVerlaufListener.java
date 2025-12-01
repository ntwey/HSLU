package ch.hslu.oop.sw08;

import java.util.EventListener;

/**
 * Listener-Interface für {@link TemperaturVerlauf}-Events.
 */
public interface TemperaturVerlaufListener extends EventListener {

    /**
     * Wird aufgerufen, wenn ein neues Minimum oder Maximum erreicht wurde.
     *
     * @param event das Event mit alten und neuen Extremwerten.
     */
    void extremumChanged(TemperaturEvent event);
}
