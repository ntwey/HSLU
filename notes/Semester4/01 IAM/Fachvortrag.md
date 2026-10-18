#  
## Spickzettel: AGOV – Architektur und IAM-Grundlagen des eidgenössischen Authentifizierungsdienstes

## 1. Einführung & Überblick zu AGOV
* **Definition:** AGOV ist der zentrale, vollständig passwortfreie Authentifizierungsdienst der Schweizer Behörden für Systemlandschaften im gesamten X2G-Kontext (G2C, C2G, B2G).
* **Zweck & EMBAG:** Die strategische und rechtliche Grundlage bildet Art. 11 des Bundesgesetzes über den Einsatz elektronischer Mittel zur Erfüllung von Behördenaufgaben (EMBAG). AGOV ersetzt das bisherige CH-LOGIN und stellt Kantonen sowie Gemeinden eine hochsichere Basis-Infrastruktur bereit.
* **Rolle als Identity Broker:** AGOV fungiert nicht nur als Identity Provider (IdP), sondern als Identitätsverbund. Es übersetzt staatliche Vertrauensstrukturen (inklusive der künftigen Schweizer E-ID) medienbruchfrei in bestehende E-Government-Zielsysteme.

## 2. IAM-Technologien und Sicherheitsstandards (Zero-Trust)
* **OIDC, OAuth 2.0 & SAML:** AGOV nutzt moderne Föderationsprotokolle. Als Identity Broker ermöglicht es angebundenen Zielsystemen, Authentifizierungs-Tokens (auch von delegierten kantonalen IdPs) sicher zu konsumieren.
* **FIDO2 & WebAuthn:** Das Rückgrat der Phishing-resistenten, passwortfreien Authentisierung. AGOV verlangt zwingend den Standard FIDO2 Level 2 (Hardware Key Protection). Asymmetrische Schlüsselpaare verbleiben nicht-extrahierbar im isolierten Secure Element (z. B. TPM, Secure Enclave) des Endgeräts.
* **Vertrauensniveau LoA 3:** Verifizierte Identitäten erfüllen das Level of Assurance 3 gemäss IKT-Vorgabe I050 und eCH-0170 V2, was die zwingende Voraussetzung für rechtsverbindliche Transaktionen mit der öffentlichen Hand ist.
* **Si001 Stufe «hoch»:** Rein softwarebasierte oder synchronisierbare Passkeys (z. B. Apple iCloud, Google Cloud) sind strikt untersagt. Zugelassen sind ausschliesslich die *AGOV access App* sowie physische FIDO2-Sicherheitsschlüssel.

## 3. Detaillierter Registrierungs- & Onboarding-Prozess
Ziel ist die kryptografisch unwiderrufliche Bindung der physischen Identität einer natürlichen Person an ein physisches Token (KI-Agenten oder Software können kein Konto besitzen).
1. **Faktor-Initialisierung:** Installation der *AGOV access App* (erfordert zwingend aktuelle OS-Versionen ohne Root/Jailbreak zur Wahrung der Systemintegrität) oder Vorbereitung eines FIDO2-Hardware-Tokens.
2. **Konto-Erstellung & Hardware-Bindung:** Nach Profileröffnung via E-Mail generiert das Endgerät durch das Scannen eines systemseitigen QR-Codes (App) oder die Verbindung des FIDO2-Tokens (USB/NFC) das asymmetrische Schlüsselpaar. Der private Schlüssel verlässt das Gerät nie.
3. **Identitätsprüfung (Erreichung von LoA 3):** Um in E-Government-Systemen rechtsverbindlich agieren zu können, ist eine formelle Identifikation zwingend. Diese erfolgt asynchron via Online-Videoidentifikation oder postalisch (Zustellung eines A-Post-Briefs mit Code innert 2–3 Werktagen). Ausweiskopien werden dabei zweckgebunden (Art. 6 DSG) für die Revisionsfähigkeit hinterlegt. Die künftige E-ID wird diesen Schritt automatisieren.

## 4. Kurzüberblick Login & Recovery
* **Login-Prozess:** Basiert auf dem WebAuthn-Protokoll und lokaler Authentisierung. Nach Weiterleitung vom Zielsystem verifiziert sich die Person via Token-PIN (FIDO2-Stick) oder lokaler biometrischer Entsperrung (FaceID, TouchID). **Wichtig:** Biometrische Daten verlassen das Endgerät niemals und werden nicht an AGOV übertragen.
* **Recovery (Wiederherstellung):** Verliert eine Person den einzigen Loginfaktor, bricht die kryptografische Vertrauenskette. Die Wiederherstellung erfolgt als strikter Kaskaden-Prozess:
  1. Eingabe der E-Mail-Adresse und Bestätigung via hinterlegtem SMS-Code.
  2. Registrierung eines neuen hardwarebasierten Loginfaktors (neues Smartphone/FIDO2-Key).
  3. **Neu-Identifikation:** Da die Nachweisbarkeit des LoA-3-Niveaus unterbrochen wurde, muss die Identität zwingend neu bestätigt werden (erneute Videoidentifikation oder neuer A-Post-Code).
* **Architektonische Empfehlung:** Zur Vermeidung dieses komplexen Recovery-Prozesses wird dringend empfohlen, einen zweiten Loginfaktor (z. B. FIDO2-Backup-Stick) proaktiv im AGOV-Konto zu hinterlegen.