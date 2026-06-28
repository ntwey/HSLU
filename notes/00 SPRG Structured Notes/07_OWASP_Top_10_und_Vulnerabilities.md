# OWASP Top 10 & Applikations-Schwachstellen

## 1. Broken Access Control & Mass Assignment
* **Mass Assignment:** Ein Angreifer fügt dem HTTP-Request unerwartete Felder hinzu (z. B. `isAdmin=true` oder `account_balance=1000`). Wenn das Framework diese Eingaben unvalidiert in das Datenbank-Objekt überträgt, überschreibt der Angreifer sensible Felder. *Gegenmassnahme:* Strikte White- und Blacklisting von Attributen über Data Transfer Objects (DTOs).

## 2. Injections (SQLi & OS Command Injection)
Injections treten auf, wenn unvalidierter Input fälschlicherweise als Befehl interpretiert wird.

### OS Command Injection (Prüfungs-Fokus)
Wenn Applikationen Systembefehle auf dem Host-Betriebssystem ausführen und dabei Benutzereingaben verketten, kann ein Angreifer eigene Systembefehle anhängen.
* **Python Beispiel:**
```python
def process_input(user_input):
    command = f"echo {user_input}"
    return os.popen(command).read()
```
* **Der Exploit:** Ein Nutzer gibt `Test; rm -rf /` ein. Das System führt `echo Test` und danach den Löschbefehl aus.
* **Mitigation:** Niemals direkt System-Shells (`os.popen` oder `os.system`) mit Konkatenation nutzen. Stattdessen native APIs der Programmiersprache verwenden oder eine strenge Input Validation (Regex) durchführen.

### SQL Injection (SQLi)
* **Gegenmassnahmen:** Prepared Statements (trennen Logik und Daten).
* **Union-Based SQLi (Java Prüfungsbeispiel):**
  Angenommen, eine fehlerhafte Backend-Suche sieht so aus:
  `"SELECT ACCOUNT_ID, BALANCE, NAME FROM ACCOUNT WHERE NAME LIKE '%" + accountName + "%' AND USERID='" + currentUser + "'"`
  Ein Angreifer wählt als `accountName` den Payload:
  `' UNION SELECT ACCOUNT_ID, BALANCE, NAME FROM ACCOUNT WHERE USERID='user1234' --`
  *Wirkung:* Das anfängliche Hochkomma bricht aus dem `LIKE` aus, das `UNION SELECT` hängt eine komplett eigene Abfrage an, und das `--` (Kommentar) ignoriert den restlichen, von der Applikation generierten Code (wie die Prüfung des `currentUser`).

## 3. Path Traversal vs. Local File Inclusion (LFI)
Beide Schwachstellen entstehen durch fehlerhafte Validierung von Dateipfaden (z.B. in Python via `with open(filename, 'r')`).
* **Path Traversal:** Ermöglicht das **Lesen** beliebiger Dateien auf dem Dateisystem, auch ausserhalb des Webroots (z. B. `../../../../etc/passwd`).
* **Local File Inclusion (LFI):** Ermöglicht nicht nur das Lesen, sondern das **Einbinden und Ausführen** von Dateien durch den Interpreter der Applikation (z. B. in PHP via `include()`).

## 4. Cross-Site Scripting (XSS) & CSRF
* **XSS-Bedingungen:** XSS ist möglich, wenn HTML-Attribute oder das Document Object Model (DOM) Benutzerinput ohne Validierung oder Encoding aufnehmen.
* **DOM-based XSS (JavaScript Prüfungsbeispiel):**
```javascript
function displaySearchResults() {
    const searchQuery = new URLSearchParams(window.location.search).get('query');
    // SCHWACHSTELLE: Direktes Schreiben von URL-Parametern als HTML
    document.getElementById('results').innerHTML = "Ergebnisse für: " + searchQuery;
}
```
* **Exploit:** Ein Aufruf von `?query=<script>alert(1)</script>` führt den Code sofort im Browser aus.
* **Mitigation:** Anstelle von `.innerHTML` zwingend `.textContent` verwenden. Dadurch interpretiert der Browser die Eingabe als reinen Text und nicht als ausführbares HTML.

* **CSRF (Cross-Site Request Forgery):** Erfordert **keine** aktive Benutzerinteraktion auf der Zielseite (der Request geschieht im Hintergrund). *Gegenmassnahmen:* Einzigartige CSRF-Tokens oder das Setzen von `SameSite`-Cookies.
  
  

### OWASP Top 10 & Applikations-Schwachstellen (inkl. Erklärungen & Code-Beispielen)

Das **Open Worldwide Application Security Project (OWASP)** ist eine weltweite Non-Profit-Organisation, die sich der Verbesserung der Softwaresicherheit widmet. Die "OWASP Top 10" ist eine Liste der kritischsten Sicherheitsrisiken für Webanwendungen und dient Entwicklern als Checkliste für das Threat Modeling sowie zu Schulungszwecken. 

Typische Exploits, die von Malware und Angreifern genutzt werden, umfassen SQL Injections, Command Injections, Cross-Site Scripting (XSS) und Buffer Overflows.

---

### Broken Access Control (A01:2021 / A01:2025)
Schwache oder fehlende Zugriffskontrollen ermöglichen es Angreifern, unautorisiert auf Daten oder Funktionen zuzugreifen. 

**Erklärung & Beispiel: Mass Assignment**
Ein häufiges Problem ist das sogenannte "Mass Assignment" (oder "Binding"-Problem). Dies geschieht, wenn ein Framework HTTP-Request-Parameter automatisch und unvalidiert an interne Objekte (wie eine `User`-Klasse) bindet [1]. 

Angenommen, wir haben folgende Java-Klasse und einen Controller-Endpunkt:

```java
public class User {
    private String userid;
    private String password;
    private String email;
    private boolean isAdmin; // Kritisches Feld!

    // Getters & Setters
}

@RequestMapping(value = "/addUser", method = RequestMethod.POST)
public String submit(User user) {
    userService.add(user);
    return "successPage";
}
````

- **Der normale Request** eines ehrlichen Nutzers sieht so aus: `POST /addUser ... userid=bobbytables&password=hashedpass&email=bobby@tables.com`.
- **Der Exploit:** Ein Angreifer fügt dem Request einfach das Feld `&isAdmin=true` hinzu. Da der Controller die Eingabe blind in das `User`-Objekt überträgt, wird der neue Benutzer unautorisiert zum Administrator gemacht.
- _Gegenmassnahme:_ Strikte Validierung und das Nutzen von Data Transfer Objects (DTOs), die nur die erlaubten Felder (wie Name und Email) enthalten.

---

### Cryptographic Failures (A02:2021 / A04:2025)

Diese Schwachstelle zielt auf den Verlust oder die Kompromittierung sensibler Daten ab (z.B. Passwörter oder Gesundheitsdaten).

**Erklärung & Beispiele:** Fehler in der Kryptografie passieren oft bei der Speicherung oder Übertragung von Daten:

- **Fehlende Verschlüsselung (in transit):** Wenn Login-Daten über unverschlüsseltes HTTP statt HTTPS gesendet werden, kann ein Angreifer im Netzwerk (z. B. durch eine Man-in-the-Middle Attacke) Passwörter im Klartext mitlesen.
- **Fehlendes Hashing (at rest):** Wenn Passwörter im Klartext in der Datenbank liegen, kann ein Angreifer, der Zugriff auf die Datenbank erlangt, diese direkt stehlen und im Darknet verkaufen.
- _Gegenmassnahme:_ Sicheres Hashing mit Salting für Passwörter und konsequente HTTPS-Verschlüsselung verwenden.

---

### Injections (A03:2021 / A05:2025)

Injections treten auf, wenn unvalidierter Input fälschlicherweise als Befehl vom Interpreter ausgeführt wird.

**Erklärung & Beispiel: Local File Inclusion (LFI) / Path Traversal** Ein klassisches Injection-Beispiel in PHP, bei dem Benutzereingaben unvalidiert in Dateipfad-Operationen genutzt werden:

```
<?php
$page = $_GET['page'];
include($page . '.php');
?>
```

- **Der Exploit:** Der Code erwartet eigentlich einen Aufruf wie `?page=home`. Ein Angreifer kann jedoch `?page=../../../etc/passwd%00` übergeben. Durch das unvalidierte Einbinden (`include`) wird der Webserver gezwungen, sensible Systemdateien auszulesen und anzuzeigen.
- _Gegenmassnahme:_ Input Validation (Prüfung auf Whitelists von erlaubten Seiten) und niemals Benutzereingaben direkt in Datei- oder Datenbankoperationen (wie SQL Injections) übergeben.

---

### Security Misconfiguration (A05:2021 / A02:2025)

Unsichere Standardkonfigurationen, fehlendes Hardening oder zu detaillierte Fehlermeldungen (Stacktraces) geben Angreifern unnötige Informationen über das System preis.

- _Gegenmassnahmen:_ Wiederholbares, automatisiertes Hardening beim Deployment und Entfernen von ungenutzten Standard-Features.

---

### Vulnerable and Outdated Components (A06:2021)

Der Einsatz von veralteten oder ungepatchten Third-Party-Bibliotheken (CVEs) birgt enorme Risiken, da fertige Exploits oft öffentlich verfügbar sind.

**Historische Beispiele aus der Praxis:**

- **Heartbleed (2014):** Ein banaler, fehlender Bounds-Check in der OpenSSL-Implementierung erlaubte es Angreifern, über manipulierte TLS-Heartbeat-Nachrichten den Speicher von Servern unberechtigt auszulesen und private Schlüssel offenzulegen.
- **British Airways Hack:** Angreifer injizierten lediglich 22 Zeilen bösartigen JavaScript-Code in eine Buchungsseite, welcher im Hintergrund unbemerkt über 380'000 Kreditkartendaten kopierte.

---

###  Software and Data Integrity Failures (A08:2021 / A08:2025)

Betrifft Systeme, die beispielsweise unsichere CI/CD-Pipelines nutzen oder automatische Updates von Paketen ohne Integritätsprüfung (wie digitale Signaturen) durchführen.

---

### Server-Side Request Forgery - SSRF (A10:2021)

SSRF tritt auf, wenn ein Webserver angewiesen wird, eine vom Benutzer bereitgestellte URL aufzurufen, ohne diese ausreichend zu validieren. Angreifer missbrauchen den Webserver, um interne, eigentlich geschützte Systeme im Backend-Netzwerk abzufragen.

---

Exkurs: Memory Safety & Buffer Overflows

Auch wenn in modernen Web-Frameworks seltener, sind speicherbezogene Fehler in C/C++ fundamental für das Systemverständnis:

**Erklärung & Ablauf eines Exploits:** Der Stack verwaltet lokale Variablen und den Kontrollfluss von Funktionen. Ruft man eine Funktion auf, wird ein "Stack-Frame" erstellt, welcher neben Variablen (wie einem Puffer) auch kritische Verwaltungsdaten wie die "Return Address" enthält.

Da Puffer linear in Richtung der Return Address wachsen, kann ein Angreifer folgendes tun:

1. Er übergibt eine Zeichenkette, die länger ist, als der Puffer fassen kann (z. B. mehr als 4 Bytes).
2. Die überschüssigen Daten (Padding) laufen über den Puffer hinaus und überschreiben die legitime Return Address auf dem Stack.
3. Die überschriebene Return Address zwingt die CPU, zu einem vom Angreifer platzierten Schadcode ("Shellcode") zu springen.
4. _Gegenmassnahme:_ Nutzung von speichersicheren Sprachen oder Compiler-Optionen wie Stack Canaries und DEP/NX. Niemals unsichere C-Funktionen (wie `strcpy` oder `gets`) verwenden, die keine Längenprüfung des Inputs (Bounds-Check) durchführen