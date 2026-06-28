# API Security (OWASP API Top 10)

Moderne Applikationen verlagern die Business-Logik vermehrt in das Frontend und kommunizieren mit dem Backend rein über zustandslose Programmierschnittstellen (APIs). Dies erfordert gänzlich andere Sicherheitsmuster als klassische Webanwendungen.

## 1. API3: Broken Object Property Level Authorization (Mass Assignment)
Diese Schwachstelle tritt auf, wenn ein API-Endpunkt Client-Eingaben ungefiltert direkt an interne Datenmodelle oder Entitäten bindet (Auto-Binding).

* **Das Angriffs-Szenario:** Ein legitimer Endpunkt zur Aktualisierung des Nutzerprofils erwartet ein JSON wie `{"email": "user@test.ch"}`. Ein Angreifer analysiert die API-Struktur und erweitert den JSON-Payload mutwillig um schützenswerte Objekteigenschaften:
```json
{
  "email": "user@test.ch",
  "isAdmin": true,
  "accountBalance": 999999
}
```
Wenn das Backend diesen Payload ohne explizite Filterung direkt über ein ORM-Framework (z. B. Hibernate oder Entity Framework) in die Datenbank speichert, gelingt dem Angreifer eine kritische Privilegienerweiterung oder Datenmanipulation.

* **Effektive Gegenmassnahmen:** 1. Strikte Vermeidung von direktem Auto-Binding auf Datenbank-Entitäten.
  2. Konsequenter Einsatz von **Data Transfer Objects (DTOs)**, um präzise und exklusiv zu definieren, welche spezifischen Felder vom Client in diesem Kontext überhaupt übermittelt und modifiziert werden dürfen.
  3. Validierung und Minimierung aller im JSON zurückgegebenen Daten auf das absolut notwendige Minimum.

## 2. API4: Unrestricted Resource Consumption (API DoS)
APIs sind primär für die maschinelle Kommunikation ausgelegt und daher das Hauptziel für automatisierte Skripte und Bots. Fehlen restriktive Limits für den Ressourcenverbrauch, führt dies unweigerlich zu Denial of Service (DoS) oder massiven Cloud-Infrastrukturkosten.

* **Effektive Gegenmassnahmen:**
  * Durchsetzung von strikten **Ratenbegrenzungen (Rate Limits)** pro Zeiteinheit, gekoppelt an die IP-Adresse oder das Authentisierungs-Token (z. B. maximal 60 Requests pro Minute).
  * Erzwingen von maximalen Datengrössen für alle eingehenden HTTP-Payloads.
  * Implementierung von strikten Ausgabelimits bei Datenbankabfragen (zwingende Pagination mit `limit` und `offset` erzwingen), um das mutwillige Abfragen von Millionen Datensätzen mit einem einzigen Request zu blockieren.

## 3. API6: Unrestricted Access to Sensitive Business Flows
Selbst wenn die Autorisierung korrekt ist, können Angreifer legitime Geschäftsabläufe durch massive Automatisierung missbrauchen (z. B. Ticket-Scalping durch Bots, automatisiertes Aufkaufen von limitierten Waren, massenhaftes Spammen von SMS-Gateways).
* **Gegenmassnahmen:** Implementierung von fortgeschrittenem Device-Fingerprinting, Erkennung von menschlichem vs. nicht-menschlichem Verhalten (CAPTCHAs) und das gezielte Blockieren bekannter kommerzieller Proxy-Netzwerke.

## 4. API9: Improper Inventory Management (Shadow APIs)
Moderne agile Entwicklung führt zu einer schnellen Evolution von Schnittstellen. Oftmals werden alte API-Versionen (z. B. `/api/v1/login`) im Netz vergessen, während das Frontend bereits `/api/v3/login` nutzt. Diese ungesicherten, nicht dokumentierten Alt-Endpunkte nennt man **Shadow APIs**. Sie sind primäre Einfallstore, da sie selten vom Patch-Management erfasst werden.
* **Gegenmassnahmen:** Lückenlose Inventarisierung aller API-Hosts, Nutzung von Tools zur automatisierten Generierung von Dokumentation (z. B. OpenAPI / Swagger) und das konsequente, harte Abschalten (Deprecation) alter API-Versionen.

## 5. API10: Unsafe Consumption of APIs
Tritt auf, wenn Entwickler APIs von Drittanbietern blind vertrauen. Empfangene Daten von externen APIs müssen mit derselben Skepsis behandelt werden wie Benutzereingaben. Mangelnde Validierung führt hier zu SQL-Injections, SSRF oder Datenabfluss im eigenen System.
* **Gegenmassnahmen:** Alle empfangenen Daten von externen Schnittstellen strikt validieren, Verbindungen zu Drittanbietern konsequent verschlüsseln und regelmässige Sicherheitsüberprüfungen der Zulieferer-APIs durchführen.