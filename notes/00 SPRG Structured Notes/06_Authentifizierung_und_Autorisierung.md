# Authentifizierung & Autorisierung

Identitäts- und Berechtigungsmanagement (IAM) regelt den geschützten Zugriff auf Systemressourcen.

## 1. Sichere Authentifizierung & Passwort-Policies
* **Best Practices für Passwort-Policies:** Die Sicherheit eines Passworts steigt exponentiell mit seiner Länge. Eine gute Policy erzwingt primär eine **ausreichende Länge (z. B. > 12 Zeichen)**. Der rein künstliche Zwang zu Sonderzeichen verleitet Nutzer oft zu vorhersehbaren Mustern (z.B. `Passwort1!`) und ist weniger effektiv als schlichte Länge. Passwörter müssen zudem als Salted Hash gespeichert werden.
* **Session Management:** Setzen der Cookie-Attribute `Secure` und `HttpOnly`. Erneuerung der Session-ID nach jedem Login verhindert Session Fixation.

## 2. Token-basierte Authentifizierung (JWT)
JSON Web Tokens (JWT) ermöglichen zustandslose Authentifizierung.
* **Entscheidende Aspekte für die Sicherheit (Prüfungs-Fokus):**
  1. **Signaturintegrität:** Die kryptografische Signatur muss bei jedem Request geprüft werden, um Manipulationen der Payload zu verhindern.
  2. **Ablaufdatum (Expiration Date - `exp`):** Ein JWT muss zwingend ein kurzes Ablaufdatum besitzen, da es nicht serverseitig invalidiert (gelöscht) werden kann.
  3. **Issuer Validierung (`iss`):** Der Backend-Service muss prüfen, ob das Token wirklich vom vertrauenswürdigen Autorisierungsserver ausgestellt wurde.

## 3. Methoden und Modelle der Autorisierung
* **RBAC (Role-Based Access Control):** Berechtigungen werden abstrakten **Gruppen/Rollen** zugeordnet (Benutzer $\rightarrow$ Rolle $\rightarrow$ Recht).
* **ABAC (Attribute-Based Access Control):** Berücksichtigt dynamische Eigenschaften (z.B. Uhrzeit, Abteilung). ABAC ist massgeblich **flexibler** als RBAC, aber komplexer in der Umsetzung.

## 4. OAuth 2.0 & Spezifische Flows (Prüfungs-Fokus)
OAuth 2.0 ist ein Framework zur Autorisierung. Die Wahl des "Flows" hängt vom Gerätetyp ab:
* **Authorisation Code Flow (mit PKCE):** Der Goldstandard für Web-Apps und mobile Apps mit Benutzerinteraktion.
* **Client Credential Flow:** Wird genutzt für Maschine-zu-Maschine-Kommunikation (Backend zu Backend), **ohne** dass ein direkter Benutzer involviert ist.
* **Device Code Flow:** Ermöglicht die Nutzung durch Geräte **ohne direkte Benutzereingabe/Browser** (z. B. Smart-TVs, IoT-Geräte oder Kommandozeilen-Tools). Das Gerät zeigt einen Code, den der Nutzer auf dem Handy eingibt.

Verknüpftes Thema: [[07_OWASP_Top_10_und_Vulnerabilities]] (Schwachstellen bei fehlerhafter Autorisierung).