Javadoc ist ein Werkzeug, das aus speziellen Kommentaren im Java-Quellcode automatisch eine HTML-Dokumentation generiert. Es hilft dabei, die API-Schnittstellen für andere Entwickler (oder dein zukünftiges Ich) verständlich zu machen.

### Grundstruktur

Ein Javadoc-Kommentar beginnt immer mit `/**` (zwei Sterne) und endet mit `*/`. Jede Zeile dazwischen beginnt normalerweise mit einem `*`.

```Java
/**
 * Kurze Zusammenfassung der Klasse oder Methode (Einzeiler).
 * * Hier folgt eine detailliertere Beschreibung, falls nötig.
 * Man kann auch HTML-Tags wie <b>fett</b> oder <ul>/<li> verwenden.
 *
 * @author Roland Gisler
 * @version 1.0.0
 */
public class Rechner {

    /**
     * Berechnet den Quotienten zweier Ganzzahlen.
     * Dies ist eine wichtige Operation für die Modulo-Logik.
     *
     * @param dividend Die Zahl, die geteilt werden soll.
     * @param divisor Die Zahl, durch die geteilt wird (darf nicht 0 sein).
     * @return Das Ergebnis der Division als Ganzzahl.
     * @throws ArithmeticException wenn der Divisor 0 ist.
     */
    public int dividiere(int dividend, int divisor) {
        return dividend / divisor;
    }
}
```
## Die wichtigsten Javadoc-Tags

Für die Prüfung solltest du diese Tags im Schlaf beherrschen:

Tag	Bedeutung	Anwendung
@param	Beschreibt einen Methodenparameter	@param name Beschreibung
@return	Beschreibt den Rückgabewert	@return Was wird zurückgegeben?
@throws	Dokumentiert mögliche Exceptions @throws ExceptionName Wann tritt sie auf?
@see	Verweis auf andere Klassen/Methoden	@see Klasse#methode
{@link}	Inline-Verweis innerhalb eines Textes	{@link String}