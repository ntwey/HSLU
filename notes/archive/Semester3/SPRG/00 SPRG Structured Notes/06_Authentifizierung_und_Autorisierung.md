# Authentifizierung & Autorisierung

Identitäts- und Berechtigungsmanagement (IAM) regelt den geschützten Zugriff auf Systemressourcen. Dabei gilt die strikte Abgrenzung: Authentifizierung prüft, *wer* ein Benutzer ist, während die Autorisierung bestimmt, *was* die authentifizierte Person tun darf.

## 1. Sichere Authentifizierung & Passwort-Policies
* **Best Practices für Passwort-Policies:** Die Sicherheit eines Passworts steigt exponentiell mit seiner Länge. Eine gute Policy erzwingt primär eine **ausreichende Länge (z. B. > 12 Zeichen)**. Der rein künstliche Zwang zu Sonderzeichen verleitet Nutzer oft zu vorhersehbaren Mustern (z.B. `Passwort1!`) und ist weniger effektiv als schlichte Länge. Passwörter müssen zudem als Salted Hash (z. B. mit Argon2 oder bcrypt) gespeichert werden und dürfen niemals im Klartext oder nur einfach verschlüsselt vorliegen.
* **Multi-Faktor-Authentifizierung (MFA):** Da Passwörter allein bei Leaks oder Phishing unzureichend sind, ist MFA zur Risikominimierung unerlässlich. Sie kombiniert Wissen (Passwort), Besitz (Token/Handy) und Inhärenz (Biometrie).
* **Session Management:** Um Angriffe wie Cross-Site Scripting (XSS) und Cross-Site Request Forgery (CSRF) zu minimieren, müssen Session-Cookies korrekt konfiguriert werden. Dies erfordert die Attribute `Secure` (Übertragung nur über HTTPS), `HttpOnly` (kein Zugriff via JavaScript) und zwingend `SameSite` (Schutz vor CSRF). Die Erneuerung der Session-ID nach jedem erfolgreichen Login verhindert zudem Angriffe durch Session Fixation.

## 2. Token-basierte Authentifizierung (JWT)
JSON Web Tokens (JWT) bestehen aus Header, Payload und Signature und ermöglichen eine zustandslose Authentifizierung. 
* **Entscheidende Aspekte für die Sicherheit (Prüfungs-Fokus):**
  1. **Signaturintegrität:** Die kryptografische Signatur muss bei jedem Request geprüft werden, um Manipulationen der Payload zu verhindern. Der Algorithmus `none` darf dabei in der Überprüfung niemals akzeptiert werden.
  2. **Ablaufdatum (Expiration Date - `exp`):** Ein JWT muss zwingend ein kurzes Ablaufdatum besitzen, da es nicht serverseitig invalidiert (gelöscht) werden kann.
  3. **Issuer Validierung (`iss`):** Der Backend-Service muss prüfen, ob das Token wirklich vom vertrauenswürdigen Autorisierungsserver ausgestellt wurde.

## 3. Methoden und Modelle der Autorisierung
Die Autorisierung sollte immer nach den Prinzipien **"Deny by Default"** (Zugriff standardmässig verweigern, nur explizit erlauben) und **"Least Privilege"** (nur die absolut minimal nötigen Rechte vergeben) implementiert werden.
* **RBAC (Role-Based Access Control):** Berechtigungen werden abstrakten **Gruppen/Rollen** zugeordnet (Benutzer $\rightarrow$ Rolle $\rightarrow$ Recht).
* **ABAC (Attribute-Based Access Control):** Berücksichtigt dynamische Eigenschaften (z.B. Uhrzeit, Abteilung, IP-Adresse). ABAC ist massgeblich **flexibler** und feingranularer als RBAC, aber komplexer in der Umsetzung.

### Häufige Autorisierungs-Schwachstellen
* **IDOR (Insecure Direct Object Reference):** Eine kritische Schwachstelle, bei der Nutzer über URL- oder Parameter-Manipulation (z. B. Änderung von `/api/user/123` auf `/api/user/124`) auf fremde Daten zugreifen können, weil das System die Berechtigung für das neue Objekt nicht serverseitig erneut prüft.
* **Path Traversal:** Das Umgehen von pfadbasierten Zugriffskontrollen (z. B. wenn ein User durch Eingabe von `../managers/` auf geschützte Ressourcen zugreift, die ausserhalb seines Profils liegen).

## 4. OAuth 2.0, OIDC & Spezifische Flows (Prüfungs-Fokus)
Während **OAuth 2.0** ein Framework zur reinen *Autorisierung* (Delegierung von Zugriffsrechten, v.a. für APIs) ist, baut **OIDC (OpenID Connect)** darauf auf und dient explizit der *Authentifizierung* (Identitätsprüfung und Föderation, z.B. Social Logins). Die Wahl des "Flows" hängt vom Gerätetyp ab:
* **Authorisation Code Flow (mit PKCE):** Der Goldstandard für Web-Apps und mobile Apps mit Benutzerinteraktion.
* **Client Credential Flow:** Wird genutzt für Maschine-zu-Maschine-Kommunikation (Backend zu Backend), **ohne** dass ein direkter Benutzer involviert ist.
* **Device Code Flow:** Ermöglicht die Nutzung durch Geräte **ohne direkte Benutzereingabe/Browser** (z. B. Smart-TVs, IoT-Geräte oder Kommandozeilen-Tools). Das Gerät zeigt einen Code, den der Nutzer auf dem Handy eingibt.

Verknüpftes Thema: [[07_OWASP_Top_10_und_Vulnerabilities]] (Schwachstellen bei fehlerhafter Autorisierung).