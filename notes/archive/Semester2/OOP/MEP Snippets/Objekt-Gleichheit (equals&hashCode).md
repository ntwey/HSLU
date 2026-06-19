```Java
public class Punkt {
    private final int x, y;

    public Punkt(int x, int y) { this.x = x; this.y = y; }

    /**
     * equals-Contract: Prüft auf inhaltliche Gleichheit[cite: 64].
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Punkt p)) return false;
        return x == p.x && y == p.y;
    }

    /**
     * hashCode: Muss bei gleichen Objekten identisch sein[cite: 64].
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }
}
```