# Labor 3 – Web Authentication

## Zielsetzung
- Verschiedene Web-Authentifizierungsverfahren verstehen:
  1. Cookie-based Authentication
  2. OAuth 2.0 mit OpenID Connect
  3. WebAuthn (FIDO2-basierte Public-Key Authentifizierung)

## Teil 1 – Cookie-basierte Authentication
- Stateful Authentication
- Server speichert Session-ID → Cookie im Browser
- Ablauf:
  1. Login → Credentials senden
  2. Server erstellt Session
  3. Browser speichert Session-Cookie
- Schwachstellen:
  - Session Hijacking
  - Session Fixation
  - Kein Schutz gegen Replay ohne HTTPS

## Teil 2 – OAuth 2.0 + OpenID Connect
- Delegierte Autorisierung
- Rollen:
  - Resource Owner
  - Client
  - Authorization Server
  - Resource Server
- Ablauf (Authorization Code Flow):
  1. Login Link → Redirect
  2. Authorization Request
  3. Login/Consent
  4. Authorization Code zurück an Client
  5. Token Request (Client Secret + Code)
  6. Access Token / ID Token erhalten
  7. Zugriff auf APIs möglich

## Teil 3 – WebAuthn
- Passwortlose Authentifizierung (Public-Key gestützt)
- Registrierung (Sign-Up):
  - Server sendet Challenge
  - Client erzeugt Schlüsselpaar (TPM, Secure Enclave)
  - Public Key wird serverseitig gespeichert
- Login:
  - Challenge → Signatur des Private Keys
  - Keine Übertragung von Geheimnissen
- Vorteile:
  - Phishing-Resistent
  - Keine Passwörter speicherbar/klau-bar

## Erkenntnisse
- Moderne Authentifizierungssysteme setzen auf Kombination aus:
  - Sicherheit
  - Benutzerfreundlichkeit
  - Delegation
  - Public-Key Kryptographie
