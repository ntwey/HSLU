package ch.hslu.oop.sw04;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Ein einfacher Motor, der ein- und ausgeschaltet werden kann.
 * Implementiert {@link CountingSwitchable} sowie {@link Named} und dient
 * als Event-Quelle für {@link PropertyChangeListener}.
 */
public class Motor implements CountingSwitchable, Named {

    private boolean on;
    private long switchCount;
    private int rpm;
    private String name;

    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    public Motor(final String name) {
        this.name = name;
    }

    /**
     * @return aktuelle Drehzahl in U/min.
     */
    public int getRpm() {
        return rpm;
    }

    @Override
    public void switchOn() {
        if (!on) {
            final boolean oldOn = this.on;
            final int oldRpm = this.rpm;
            on = true;
            rpm = 800; // Leerlauf
            switchCount++;
            firePropertyChange("on", oldOn, this.on);
            firePropertyChange("rpm", oldRpm, this.rpm);
        }
    }

    @Override
    public void switchOff() {
        if (on) {
            final boolean oldOn = this.on;
            final int oldRpm = this.rpm;
            on = false;
            rpm = 0;
            switchCount++;
            firePropertyChange("on", oldOn, this.on);
            firePropertyChange("rpm", oldRpm, this.rpm);
        }
    }

    @Override
    public boolean isSwitchedOn() {
        return on;
    }

    @Override
    public long getSwitchCount() {
        return switchCount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Registriert einen Listener.
     */
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        listeners.add(Objects.requireNonNull(listener, "listener"));
    }

    /**
     * Entfernt einen Listener.
     */
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        listeners.remove(listener);
    }

    private void firePropertyChange(final String propertyName,
                                    final Object oldValue,
                                    final Object newValue) {
        if (Objects.equals(oldValue, newValue)) {
            return;
        }
        if (listeners.isEmpty()) {
            return;
        }
        final PropertyChangeEvent event =
                new PropertyChangeEvent(this, propertyName, oldValue, newValue);
        for (PropertyChangeListener listener : listeners) {
            listener.propertyChange(event);
        }
    }
}
