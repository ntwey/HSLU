
### OWASP Top 10 - 2021

1. **A01:2021 – Broken Access Control** (Fehlerhafte Zugriffskontrolle)
    
2. **A02:2021 – Cryptographic Failures** (Kryptografische Fehler)
    
3. **A03:2021 – Injection** (Injektionsschwachstellen)
    
4. **A04:2021 – Insecure Design** (Unsicheres Design)
    
5. **A05:2021 – Security Misconfiguration** (Sicherheitsfehlkonfiguration)
    
6. **A06:2021 – Vulnerable and Outdated Components** (Verwundbare und veraltete Komponenten)
    
7. **A07:2021 – Identification and Authentication Failures** (Fehler bei der Identifizierung und Authentifizierung)
    
8. **A08:2021 – Software and Data Integrity Failures** (Software- und Datenintegritätsfehler)
    
9. **A09:2021 – Security Logging and Monitoring Failures** (Fehler bei der Sicherheits-Protokollierung und -Überwachung)
    
10. **A10:2021 – Server-Side Request Forgery (SSRF)** (Serverseitige Anforderungsfälschung)
    

### OWASP Top 10 - 2025

Die Liste für 2025 bringt einige signifikante Änderungen mit sich, die auf aktuellen Bedrohungsdaten basieren:

1. **A01:2025 – Broken Access Control** (Fehlerhafte Zugriffskontrolle)
    
2. **A02:2025 – Security Misconfiguration** (Sicherheitsfehlkonfiguration) _(gestiegen von Platz 5)_
    
3. **A03:2025 – Software Supply Chain Failures** (Fehler in der Software-Lieferkette) _(Neu/Fokusiert)_
    
4. **A04:2025 – Cryptographic Failures** (Kryptografische Fehler)
    
5. **A05:2025 – Injection** (Injektionsschwachstellen)
    
6. **A06:2025 – Insecure Design** (Unsicheres Design)
    
7. **A07:2025 – Authentication Failures** (Authentifizierungsfehler)
    
8. **A08:2025 – Software or Data Integrity Failures** (Software- oder Datenintegritätsfehler)
    
9. **A09:2025 – Security Logging and Alerting Failures** (Fehler bei der Protokollierung und Alarmierung)
    
10. **A10:2025 – Mishandling of Exceptional Conditions** (Fehlerhafte Behandlung von Ausnahmebedingungen) _(Neu)_
    

### Die wichtigsten Änderungen (2021 vs. 2025) auf einen Blick:

- **Zusammenlegung:** _Server-Side Request Forgery (SSRF)_, welches 2021 noch auf Platz 10 war, wurde nun in _Broken Access Control (A01)_ integriert.
    
- **Neuer Fokus:** _Software Supply Chain Failures (A03)_ ist stark in den Fokus gerückt. Es absorbiert teilweise die alten Schwachstellen bezüglich veralteter Komponenten (ehemals A06) und reagiert auf die massiven Angriffe auf Lieferketten (Third-Party-Bibliotheken, CI/CD-Pipelines) der letzten Jahre.
    
- **Neue Kategorie:** _Mishandling of Exceptional Conditions (A10)_ ist neu dabei. Dies bezieht sich darauf, wenn Applikationen abstürzen oder Fehler werfen und dabei sensible Systemdaten oder Stack-Traces an den Angreifer preisgeben.
    
- **Verschiebung:** _Security Misconfiguration (A02)_ ist deutlich nach oben geklettert, was vor allem der zunehmenden Komplexität von Cloud-Setups und fehlkonfigurierten Diensten geschuldet ist.

### OWASP API Security Top 10 - 2023

1. **API1:2023 – Broken Object Level Authorization (BOLA)** (Fehlerhafte Autorisierung auf Objektebene)
    
    - _Erklärung:_ Angreifer manipulieren IDs in der Anfrage (z.B. `/api/user/123` zu `/api/user/124`), um auf Daten anderer Nutzer zuzugreifen. Dies ist das mit Abstand häufigste API-Problem.
        
2. **API2:2023 – Broken Authentication** (Fehlerhafte Authentifizierung)
    
    - _Erklärung:_ Schwächen in der Umsetzung von Logins, Token-Verwaltung (z.B. JWTs) oder Passwörtern, die es Angreifern erlauben, Sitzungen zu übernehmen oder Identitäten zu fälschen.
        
3. **API3:2023 – Broken Object Property Level Authorization** (Fehlerhafte Autorisierung auf Eigenschaftsebene)
    
    - _Erklärung:_ Eine Kombination aus den früheren Punkten "Excessive Data Exposure" (API gibt mehr Daten zurück als nötig) und "Mass Assignment" (Nutzer können über die API versteckte Datenfelder wie `is_admin=true` überschreiben).
        
4. **API4:2023 – Unrestricted Resource Consumption** (Unbeschränkter Ressourcenverbrauch)
    
    - _Erklärung:_ Fehlende Limits (Rate Limiting, Limitierung von Payload-Größen oder Paginierung), die es Angreifern erlauben, den Server durch unzählige oder gigantische Anfragen lahmzulegen (DoS) oder enorme Cloud-Kosten zu verursachen.
        
5. **API5:2023 – Broken Function Level Authorization (BFLA)** (Fehlerhafte Autorisierung auf Funktionsebene)
    
    - _Erklärung:_ Ein normaler Benutzer kann administrative oder privilegierte API-Endpunkte (z.B. `DELETE /api/users/`) aufrufen, weil die Rechteprüfung für die Funktion fehlt.
        
6. **API6:2023 – Unrestricted Access to Sensitive Business Flows** (Unbeschränkter Zugriff auf sensible Geschäftsabläufe) _(Neu 2023)_
    
    - _Erklärung:_ Ein API-Endpunkt wird missbraucht, um z.B. massenhaft Konzerttickets durch Bots zu kaufen oder durch automatisierte Anfragen einen wirtschaftlichen Schaden anzurichten.
        
7. **API7:2023 – Server Side Request Forgery (SSRF)** (Serverseitige Anforderungsfälschung) _(Neu 2023)_
    
    - _Erklärung:_ Die API nimmt eine URL als Eingabe entgegen und ruft diese ungeprüft serverseitig auf. Dadurch können Angreifer interne Netzwerke scannen oder Cloud-Metadaten abgreifen.
        
8. **API8:2023 – Security Misconfiguration** (Sicherheitsfehlkonfiguration)
    
    - _Erklärung:_ Schlecht konfigurierte Header, offene CORS-Richtlinien, unnötige HTTP-Methoden oder ausführliche Fehlermeldungen (Stack Traces), die Angreifern Informationen liefern.
        
9. **API9:2023 – Improper Inventory Management** (Fehlerhafte Bestandsverwaltung)
    
    - _Erklärung:_ Sogenannte "Zombie-APIs" oder "Shadow-APIs" – alte, nicht dokumentierte oder vergessene API-Versionen (z.B. `/api/v1/`), die noch erreichbar sind und oft veraltete Sicherheitsstandards haben.
        
10. **API10:2023 – Unsafe Consumption of APIs** (Unsichere Nutzung fremder APIs) _(Neu 2023)_
    
    - _Erklärung:_ Eine API vertraut den Antworten von APIs von Drittanbietern (z.B. Zahlungsdienstleistern oder Maps-Diensten) blind, ohne die dort empfangenen Daten zu validieren oder zu filtern.
        

### Was hat sich gegenüber der Vorversion (2019) geändert?

- **Zusammenfassung:** Die Probleme der versehentlichen Datenpreisgabe (_Excessive Data Exposure_) und der unerlaubten Datenüberschreibung (_Mass Assignment_) wurden logisch zu **API3:2023 (Broken Object Property Level Authorization)** zusammengefasst, da sie dieselbe Ursache haben.
    
- **Geschäftslogik & Bots:** Mit **API6:2023** wurde ein starker Fokus auf den Missbrauch von Geschäftslogik durch Automatisierung/Bots gelegt.
    
- **SSRF (API7):** Ähnlich wie bei Webanwendungen ist SSRF auch bei APIs zu einem massiven Problem geworden (besonders in Cloud-Umgebungen wie AWS/Azure) und hat einen eigenen Platz in der Liste bekommen.
    
- **Drittanbieter-Risiken (API10):** Da moderne Applikationen stark vernetzt sind, wurde die blinde Vertrauensstellung gegenüber Third-Party-APIs als neuer, kritischer Angriffsvektor identifiziert.