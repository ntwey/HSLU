package ch.hslu.oop.sw08;

import ch.hslu.oop.sw02.Temperatur;

import java.util.EventObject;

/**
 * Event, das signalisiert, dass im {@link TemperaturVerlauf} ein neues
 * Minimum oder Maximum erreicht wurde.
 */
public final class TemperaturEvent extends EventObject {

    public enum Type {
        MINIMUM,
        MAXIMUM
    }

    private final Type type;
    private final Temperatur oldValue;
    private final Temperatur newValue;

    public TemperaturEvent(final Object source,
                           final Type type,
                           final Temperatur oldValue,
                           final Temperatur newValue) {
        super(source);
        this.type = type;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Type getType() {
        return type;
    }

    public Temperatur getOldValue() {
        return oldValue;
    }

    public Temperatur getNewValue() {
        return newValue;
    }
}
