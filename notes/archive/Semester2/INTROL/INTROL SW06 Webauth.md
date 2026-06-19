# Zusammenfassung: Web Authentication (Labor 3)

Dieses Labor behandelt die Evolution der Web-Authentifizierung: von klassischen Cookies über delegierte Rechte (OAuth) bis hin zu passwortlosen Verfahren (WebAuthn).

---

## 1. Cookie-basierte Authentifizierung (Die "alte" Schule)

### Theorie & Problemstellung
Das Internet basiert auf dem **HTTP-Protokoll**, welches **zustandslos (stateless)** ist. Das bedeutet: Der Webserver vergisst sofort nach dem Absenden einer Webseite, wer du bist. Wenn du auf der nächsten Seite klickst, wüsste der Server nicht mehr, dass du dich gerade eingeloggt hast.

**Die Lösung:** Cookies.
Ein Cookie ist wie ein "Besucherausweis", den dir der Server gibt.

### Der Ablauf im Labor
1.  **Login (POST-Request):** Du sendest deinen Benutzernamen und dein Passwort an den Server.
2.  **Session-Erstellung:** Wenn die Daten stimmen, erstellt der Server intern eine "Session" (Sitzung) und generiert eine **Session-ID**.
3.  **Set-Cookie:** In der Antwort des Servers (Response) steht der Header `Set-Cookie: session_id=xyz...`. Damit befiehlt der Server dem Browser: "Merk dir diese ID!".
4.  **Folge-Requests:** Wenn du nun eine geschützte Seite aufrufst (z. B. `/dashboard`), sendet dein Browser **automatisch** bei jedem Request diesen Ausweis mit (`Cookie: session_id=xyz...`).
5.  **Validierung:** Der Server schaut in seiner Liste nach: "Kenne ich die ID xyz? Ja, das ist der User Tino." -> Zugriff erlaubt.

### Vor- und Nachteile
* **Vorteil:** Einfach zu implementieren; User muss sich nur einmal einloggen.
* **Nachteil:** Der Server muss sich alle aktiven Sessions merken (Speicherbedarf); anfällig für Diebstahl der Session-ID (Session Hijacking).

---

## 2. OAuth 2.0 & OpenID Connect (OIDC)

### Theorie & Problemstellung
Früher musste man jedem Dienst sein Passwort geben. OAuth löst das Problem, dass du dich bei einer Webseite (z. B. einer To-Do-App) anmelden willst, aber deine Identität von einem vertrauenswürdigen Drittanbieter (z. B. Google, Facebook oder hier Auth0) kommen soll.

* **OAuth 2.0:** Regelt die **Autorisierung** (Wer darf was? z. B. auf eine API zugreifen).
* **OpenID Connect (OIDC):** Ist ein Zusatz auf OAuth, der die **Authentifizierung** regelt (Wer bist du?).

### Setup im Labor: Man-in-the-Middle (MitM)
Um den verschlüsselten Datenverkehr (HTTPS) zwischen der App und dem Anbieter (Auth0) zu sehen, wurde **OWASP ZAP** eingesetzt.
* **Das Problem:** HTTPS verschlüsselt alles. Wir sehen nichts.
* **Der Trick:** ZAP stellt sich dazwischen. Dem Browser gaukelt ZAP vor, der Server zu sein (mit einem gefälschten Zertifikat), und dem Server gaukelt er vor, der Browser zu sein. Damit das funktioniert, muss man das "Root-Zertifikat" von ZAP in den Browser importieren, damit dieser dem "Angreifer" (uns) vertraut.

### Der Ablauf (Authorization Code Flow)
Dies ist der sicherste Weg für Web-Apps:

1.  **Login-Link:** Der User klickt auf "Login". Die App leitet den User weiter zu Auth0 (Identity Provider).
2.  **Authentifizierung:** Der User gibt SEIN PASSWORT NUR BEI AUTH0 EIN, nicht bei der App.
3.  **Authorization Code:** Nach erfolgreichem Login schickt Auth0 den User zurück zur App und hängt einen **Code** an die URL an. Dies ist noch kein Schlüssel, sondern nur ein "Abholschein".
4.  **Token Exchange (Back-Channel):**
    * Der Browser übergibt den Code an den Server der App.
    * Der Server der App (Backend) redet nun direkt mit Auth0 (nicht über den Browser!) und tauscht den "Code" + "Client Secret" (ein Geheimnis, das nur die App kennt) gegen **Tokens** ein.
    * *Warum so kompliziert?* Damit das Access Token niemals im Browser (Frontend) sichtbar ist, wo es gestohlen werden könnte.

### Die Tokens (JWT - JSON Web Tokens)
Auth0 liefert zwei wichtige Tokens zurück. Man kann sie auf `jwt.io` entschlüsseln:
1.  **ID Token:** Enthält Infos über den User (Name, E-Mail, Profilbild). Dient dem "Ausweisen".
2.  **Access Token:** Enthält Berechtigungen (Scopes). Dient als "Schlüssel", um Daten von einer API abzurufen.

---

## 3. WebAuthn (FIDO2) - Passwortlos

### Theorie & Problemstellung
Passwörter sind unsicher (werden erraten, gestohlen, wiederverwendet). WebAuthn nutzt **Public Key Cryptography (Asymmetrische Verschlüsselung)**, um Passwörter komplett abzuschaffen.

* **Der Schlüssel:** Es gibt ein Schlüsselpaar.
    * **Private Key:** Bleibt IMMER auf deinem Gerät (im TPM-Chip, YubiKey oder Smartphone). Er verlässt das Gerät nie.
    * **Public Key:** Wird an den Server gesendet. Er ist wie ein offenes Vorhängeschloss.

### Komponenten
* **User Agent:** Dein Browser (z. B. Edge).
* **Authenticator:** Der Sicherheitschip (TPM) in deinem PC oder ein USB-Key, geschützt durch PIN oder Biometrie (Windows Hello).
* **Relying Party:** Die Webseite.

### Ablauf 1: Registrierung (Sign-Up)
1.  User sagt: "Ich will mich registrieren."
2.  Webseite sagt: "Okay, gib mir deinen Public Key."
3.  User entsperrt seinen Authenticator (z. B. via PIN/Fingerabdruck).
4.  Authenticator erstellt ein NEUES Schlüsselpaar.
5.  Der **Public Key** und ein "Attestation Object" (Beweis, dass der Key von einem sicheren Chip kommt) werden an den Server gesendet.
6.  Server speichert den Public Key.

### Ablauf 2: Login (Challenge-Response)
Wie beweist du, dass du der Besitzer des Private Keys bist, ohne ihn zu zeigen?

1.  **Challenge:** Du willst dich einloggen. Der Server schickt dir eine zufällige Zeichenfolge (Challenge), z. B. "GH78KL...".
2.  **Signatur:** Dein Browser gibt die Challenge an den Authenticator (TPM). Du gibst deine PIN ein (um den TPM freizuschalten). Der TPM **signiert** die Challenge mit dem **Private Key**. Das Ergebnis ist eine digitale Unterschrift.
3.  **Verifikation:** Du schickst die unterschriebene Challenge zurück.
4.  Der Server nimmt deinen gespeicherten **Public Key** und prüft mathematisch: "Wurde diese Challenge wirklich mit dem passenden Private Key unterschrieben?".
5.  Wenn ja -> Login erfolgreich. Das Passwort wurde nie übertragen, weil es keines gibt.

### Sicherheitsvorteile
* **Kein Phishing:** Man kann kein Passwort auf einer gefälschten Seite eingeben, da die Kryptografie an die Domain (URL) gebunden ist.
* **Keine Server-Hacks:** Wenn der Server gehackt wird, klauen die Diebe nur Public Keys (nutzlos für Angriffe).
* **Replay-Schutz:** Da die "Challenge" jedes Mal anders ist, kann man einen abgefangenen Datenverkehr nicht für einen erneuten Login nutzen.