![[EXAM_SPRG_Probepruefung.pdf]]

**1) Warum ist es sinnvoll, Requirement Engineering speziell im Bereich IT-Security durchzuführen?**

- **Nutzen:** Security kann nicht nachträglich „hinzugefügt“ werden (Security by Design). Durch die frühzeitige Analyse werden Sicherheitsanforderungen von Beginn an in den Software-Lebenszyklus (SDLC) integriert. Dies reduziert die Kosten für Fehlerbehebungen in späteren Phasen massiv und verhindert grundlegende Architekturfehler, die später nur schwer zu korrigieren wären.
    

**2) Vorgehen beim Threat Modelling:**

1. **Systembeschreibung:** Festlegen des Umfangs, Identifizierung von Assets (was wollen wir schützen?) und Vertrauenszonen.
    
2. **Datenflussdiagramm (DFD) erstellen:** Visualisierung der Datenströme, Prozesse und Speicherorte.
    
3. **Bedrohungsidentifizierung:** Systematische Suche nach Bedrohungen (z. B. mittels STRIDE-Modell).
    
4. **Risikobewertung:** Einschätzung der Eintrittswahrscheinlichkeit und des Schadensausmaßes (z. B. mittels DREAD).
    
5. **Gegenmaßnahmen entwickeln:** Planung von Maßnahmen zur Minimierung der identifizierten Risiken.
    

**3) Techniken, um Requirements zu finden (Vorteile/Nachteile):**

- **Interviews mit Stakeholdern:**
    
    - _Vorteil:_ Tiefes Verständnis für spezifische Bedürfnisse.
        
    - _Nachteil:_ Zeitaufwendig; subjektive Wahrnehmung der Interviewten.
        
- **Workshop-basiertes Brainstorming:**
    
    - _Vorteil:_ Schnelles Generieren vieler Ideen; Konsensfindung in der Gruppe.
        
    - _Nachteil:_ Gruppendruck kann abweichende (aber wichtige) Meinungen unterdrücken.
        

**4) DREAD Vorgehen:**

- **a. Nutzen:** Es dient der quantitativen Risikoanalyse und Priorisierung von Bedrohungen.
    
- **b. Wann/Warum hilfreich:** Hilfreich bei einer Vielzahl identifizierter Bedrohungen, um zu entscheiden, welche zuerst adressiert werden müssen (Risikopriorisierung), damit Ressourcen effizient eingesetzt werden.
    

**5) Vulnerability, Threat und Threat-Action (Beispiele):** _(Schema: Schwachstelle -> Bedrohung -> Aktion)_

1. **Vulnerability:** Fehlendes Passwort-Hashing -> **Threat:** Credential Theft -> **Action:** Implementierung von bcrypt/Argon2.
    
2. **Vulnerability:** Ungefilterte Eingabefelder -> **Threat:** SQL-Injection -> **Action:** Nutzung von Prepared Statements.
    
3. **Vulnerability:** Veraltete Libraries -> **Threat:** Ausnutzung bekannter Exploits -> **Action:** Regelmäßiges Dependency-Update-Management.
    
4. **Vulnerability:** Klartext-Übertragung (HTTP) -> **Threat:** Man-in-the-Middle -> **Action:** Erzwingen von TLS/HTTPS.
    
5. **Vulnerability:** Zu weitreichende Berechtigungen -> **Threat:** Privilege Escalation -> **Action:** Einführung des "Least Privilege"-Prinzips.
    

**6) Usecases und Misusecases:**

- _Beispiel Usecase:_ Benutzer meldet sich am Online-Banking an, um Kontostand zu prüfen.
    
- _Beispiel Misusecase:_ Angreifer versucht, durch Brute-Force-Attacke das Passwort des Benutzers zu erraten, um unbefugten Zugriff auf das Konto zu erhalten.
    

**7) 7 Schritte des SDLC (Microsoft Ansatz):**

- Dieser Ansatz (oft als "Microsoft Security Development Lifecycle" bezeichnet) stellt Sicherheit in den Mittelpunkt jeder Entwicklungsphase. Von der Planung über das Design (Threat Modelling), die Implementierung, das Testing (Fuzzing, Code Reviews), den Release bis hin zur Wartung (Incident Response) wird sichergestellt, dass Sicherheitsaspekte nicht nur am Ende kontrolliert, sondern in jeden Schritt "eingebaut" werden.
    

**8) Threat Modelling Übung (Warenautomat):**

- **Assets:** Bargeld, Zahlungsdaten (Kreditkartennummern), Kundeninformationen, Betriebssoftware (Firmware), Preisdaten.
    
- **STRIDE-Beispiel:**
    
    - _Spoofing:_ Fälschen der Server-Identität, um gefälschte Preise an den Automaten zu senden.
        
    - _Tampering:_ Physische Manipulation des Kartenlesers (Skimming).
        
    - _Information Disclosure:_ Auslesen von Transaktionsdaten aus dem internen Speicher.
        
    - _Denial of Service:_ Überfluten der Netzwerkverbindung, sodass der Automat keine Zahlungen mehr autorisieren kann.
        
- **Gegenmaßnahmen:** Verschlüsselung aller Netzwerkverbindungen (TLS), physische Sicherung des Gehäuses, regelmäßige Sicherheitsupdates der Firmware, Logging von Transaktionen zur Anomalieerkennung.

**1. OAuth-Flow(s) ohne direkten Benutzerzugriff:**

- **Antwort: c, d** (Client Credential Flow für machine-to-machine, Device Code Flow für Geräte mit eingeschränkter Eingabe).
    

**2. Aussagen über CSRF:**

- **Antwort: a, c** (SameSite-Cookies und Tokens sind Standard-Mitigationen).
    

**3. Zero Trust-Sicherheitsarchitektur:**

- **Antwort: a** (Ständige Authentifizierungsüberprüfung ist das Kernprinzip).
    

**4. Code-Schwachstelle (Command Injection):**

- **Schwachstelle:** Der `user_input` wird ungefiltert direkt in einen Shell-Befehl eingebettet (`f"echo {user_input}"`).
    
- **Mitigation:** Verwende niemals Funktionen, die Shell-Befehle ausführen (wie `os.popen` oder `os.system`) mit Benutzereingaben. Wenn externe Prozesse nötig sind, verwende das `subprocess`-Modul mit einer Liste von Argumenten (ohne Shell-Ausführung), um Injection zu verhindern.
    

**5. Schutz gegen SQL-Injection:**

- **Antwort: a, b, c** (Prepared Statements sind die wichtigste Maßnahme; Input Validation und Stored Procedures (wenn sie korrekt parametrisiert sind) helfen ebenfalls).
    

**6. Code-Schwachstelle (Path Traversal / LFI):**

- **Schwachstelle:** Path Traversal (oder Local File Inclusion). Der Benutzer kann beliebige Dateipfade eingeben (z. B. `../../etc/passwd`), um Dateien außerhalb des beabsichtigten Verzeichnisses zu lesen.
    
- **Mitigation:** Benutzereingaben niemals direkt als Dateipfad verwenden. Validierung des Inputs (Whitelist von erlaubten Dateinamen), Verwendung von `basename` des Inputs oder Nutzung von APIs, die Pfade sicher isolieren (Sandboxing/Chroot).
    

**7. Zugriffskontrolle (ABAC/RBAC):**

- **Antwort: a, b** (ABAC ist flexibler, RBAC arbeitet mit Gruppen).
    

**8. SQL-Injection (User ID):**

- **Antwort: a** (Mit `1 OR 1=1` wird die Bedingung `WHERE user_id = 1 OR 1=1` wahr, was alle Datensätze zurückgibt).
    

**9. JWT Aspekte:**

- **Antwort: a, b, d** (Signatur, Ablaufzeit und Issuer-Validierung sind essenziell).
    

**10. XSS-Schwachstelle:**

- **Antwort: a, d** (Direktes Einfügen von Input in das DOM oder dynamische Script-Generierung ohne Encoding).
    

**11. Dependency Management:**

- **Antwort: Keine der genannten** (Automatische Updates ohne Prüfung sind gefährlich, einmaliges Scannen reicht nicht, interne Repositories erhöhen die Sicherheit durch Kontrolle sehr wohl).
    

**12. Path Traversal vs. LFI:**

- **Antwort: d** (Path Traversal liest Pfade, LFI bindet Skripte/Dateien innerhalb des Webroots aus).
    

**13. Passwort-Policy:**

- **Antwort: b** (Länge ist heute wichtiger als die Komplexitätsregeln wie Sonderzeichen).
    

**14. SQL-Injection für Bankkonto-Suche:**

- **Antwort: d** (`' OR USERID='user1234` schließt das Query korrekt ab, überschreibt die Bedingung und die `--` Kommentare am Ende entfernen das restliche SQL-Statement des Programmierers).
    

**15. Schwachstelle displaySearchResults (DOM-based XSS):**

- **Schwachstelle:** DOM-based XSS. Die Applikation schreibt Benutzereingaben aus der URL direkt in das `innerHTML` des DOMs.
    
- **Mitigation:** Statt `innerHTML` die Eigenschaft `textContent` verwenden, da diese den Inhalt als Text und nicht als HTML interpretiert.
    
- **Ausnutzung:** Ein Angreifer könnte einen Link senden, der im `query`-Parameter ein Script enthält, z. B. `?query=<img src=x onerror=alert(1)>`.
    

**16. Mass Assignment:**

- **Antwort: a, c** (Angreifer können Modell-Attribute manipulieren; Whitelisting der erlaubten Felder ist eine Standard-Gegenmaßnahme).
    

**17. Buffer Overflow:**

- **Antwort: a, b, d** (Klassische Definition, Schutzmechanismen und DEP-Funktionsweise sind korrekt).