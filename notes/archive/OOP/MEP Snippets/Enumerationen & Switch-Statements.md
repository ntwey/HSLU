```java
/**
 * Definition einer Enumeration[cite: 70].
 */
public enum Wochentag {
    MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG, SAMSTAG, SONNTAG
}

class Kalender {
    public void pruefeTag(Wochentag tag) {
        // Switch-Statement zur Selektion [cite: 43]
        switch (tag) {
            case SAMSTAG, SONNTAG -> System.out.println("Wochenende!");
            default -> System.out.println("Arbeitstag.");
        }
    }
}
```