package ch.hslu.oop.sw04;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Sehr einfaches Fahrzeug, das aus Komponenten besteht.
 * Das Fahrzeug selbst ist schaltbar (Delegation an Komponenten) und reagiert
 * als {@link java.beans.PropertyChangeListener} auf Statusänderungen des Motors.
 */
public class Vehicle implements CountingSwitchable, Named, PropertyChangeListener {

    private static final Logger LOG = Logger.getLogger(Vehicle.class.getName());

    private final Motor engine;
    private final List<Switchable> components = new ArrayList<>();
    private boolean on;
    private long switchCount;
    private String name;

    public Vehicle(final String name, final Motor engine, final Switchable... others) {
        this.name = name;
        this.engine = engine;
        components.add(engine);
        if (others != null) {
            Collections.addAll(components, others);
        }
        // Auf Motor-Events reagieren
        if (this.engine != null) {
            this.engine.addPropertyChangeListener(this);
        }
    }

    @Override
    public void switchOn() {
        if (!on) {
            for (Switchable s : components) {
                s.switchOn();
            }
            on = true;
            switchCount++;
        }
    }

    @Override
    public void switchOff() {
        if (on) {
            for (Switchable s : components) {
                s.switchOff();
            }
            on = false;
            switchCount++;
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

    public Motor getEngine() {
        return engine;
    }

    /**
     * Behandlung der vom Motor kommenden Property-Change-Events.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        LOG.info(() -> String.format("%s: Property '%s' changed from %s to %s",
                getName(),
                event.getPropertyName(),
                event.getOldValue(),
                event.getNewValue()));
    }
}
