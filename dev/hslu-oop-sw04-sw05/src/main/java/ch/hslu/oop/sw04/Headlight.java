package ch.hslu.oop.sw04;

/**
 * Eine einfache Scheinwerfer-Komponente.
 */
public final class Headlight implements Switchable, Named {
    private boolean on;
    private String name;

    public Headlight(final String name) {
        this.name = name;
    }

    @Override
    public void switchOn() {
        on = true;
    }

    @Override
    public void switchOff() {
        on = false;
    }

    @Override
    public boolean isSwitchedOn() {
        return on;
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
