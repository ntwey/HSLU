package ch.hslu.oop.sw04;

/**
 * Ein einfacher Motor, der ein- und ausgeschaltet werden kann.
 * Implementiert {@link CountingSwitchable} sowie {@link Named}.
 */
public final class Motor implements CountingSwitchable, Named {
    private boolean on;
    private long switchCount;
    private int rpm;
    private String name;

    public Motor(final String name) {
        this.name = name;
    }

    /** @return aktuelle Drehzahl in U/min. */
    public int getRpm() {
        return rpm;
    }

    @Override
    public void switchOn() {
        if (!on) {
            on = true;
            rpm = 800; // Leerlauf
            switchCount++;
        }
    }

    @Override
    public void switchOff() {
        if (on) {
            on = false;
            rpm = 0;
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
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
