Zwei zentrale Qualitätsmerkmale im Design.
* **Hohe Kohäsion**: Eine Klasse hat eine klar definierte Aufgabe (Single Responsibility).
* **Lose Kopplung**: Klassen sind so wenig wie möglich voneinander abhängig (Einsatz von Interfaces/Observer)
```Java

/**
 * BEISPIEL FÜR SCHLECHTE KOPPLUNG (Eng verzahnt)
 */
class Motor { public void start() {} }
class Auto {
    private Motor m = new Motor(); // Auto ist fest an Motor gebunden
}

/**
 * BEISPIEL FÜR LOSE KOPPLUNG (Über Abstraktion/Interface) [cite: 122]
 */
interface Antrieb { void start(); }
class BessererMotor implements Antrieb { public void start() {} }
class BesseresAuto {
    private Antrieb antrieb; // Kennt nur das Interface
    public BesseresAuto(Antrieb a) { this.antrieb = a; }
}
```