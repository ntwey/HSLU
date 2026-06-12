# Laborübung: Public Key Infrastructure (PKI) - Zusammenfassung

Diese Laborübung der Hochschule Luzern (Modul I.BA_ISLAB) behandelt den praktischen Umgang mit einer Public Key Infrastructure, von der Installation einer CA bis hin zur Durchführung von SSL-Interception.

## 1. Grundlagen & Vorbereitung
* **Symmetrische vs. Asymmetrische Verschlüsselung:** Symmetrische Verfahren nutzen denselben Schlüssel für Ver- und Entschlüsselung (schnell), während asymmetrische Verfahren ein Schlüsselpaar (Public/Private Key) nutzen (langsamer, ermöglicht digitale Signaturen).
* **X.509-Zertifikate:** Standardformat für digitale Zertifikate. Enthält Felder wie Version, Seriennummer, Aussteller (Issuer), Gültigkeitszeitraum, Inhaber (Subject) und den öffentlichen Schlüssel.
* **DNS-Konfiguration:** Für den Webzugriff wird ein A-Record (z.B. `www.gXXX.ckteck.com`) im Active Directory DNS-Manager erstellt, der auf die IP des Webservers zeigt.

## 2. Certification Authority (CA) Installation
* **Rollen:** Installation der "Active Directory Certificate Services" (AD CS) auf einem Windows Server 2022.
* **Architektur:** Einsatz einer **Two-Tier-Hierarchie**. Eine (Offline) Root CA dient als Vertrauensanker, während eine Subordinate CA (Issuing CA) im Alltag Zertifikate ausstellt.
* **Sicherheit:** Die Root CA sollte offline sein, um den privaten Schlüssel vor Netzwerkangriffen zu schützen. Eine Kompromittierung der Root CA würde das gesamte Vertrauensmodell zerstören.

## 3. HTTPS & Webserver-Konfiguration
* **Zertifikatserstellung:** Erstellung eines Private Keys (RSA 4096 Bit) und eines Certificate Signing Requests (CSR) auf einem Linux/Apache-Webserver.
* **Subject Alternative Names (SAN):** Moderne Browser verlangen SAN-Einträge, da das alte "Common Name" Feld als veraltet gilt.
* **Apache-Einbindung:** Aktivierung der Module `ssl` und `rewrite` sowie Konfiguration der Pfade für `.cer` und `.key` Dateien.

## 4. Zertifikatsbasierte Authentifizierung & Widerruf
* **Gegenseitige Authentifizierung:** Nicht nur der Server weist sich aus, auch der Client muss ein gültiges Zertifikat vorlegen.
* **Autoenrollment:** Über Gruppenrichtlinien (GPO) können Benutzerzertifikate automatisch an alle Domänenmitglieder verteilt werden.
* **Widerrufsmechanismen:**
    * **CRL (Certificate Revocation List):** Eine "schwarze Liste" von Seriennummern, die Clients periodisch herunterladen.
    * **OCSP (Online Certificate Status Protocol):** Eine Echtzeit-Abfrage des Zertifikatsstatus beim Online Responder.
* **Nonce Extension:** Eine Zufallszahl in der OCSP-Abfrage verhindert Replay-Attacken.

## 5. Code Signing
* **Sinn:** Nachweis der Herkunft und Integrität von Software/Skripten.
* **PowerShell Execution Policies:** * `Restricted`: Keine Skripte.
    * `AllSigned`: Nur digital signierte Skripte von vertrauenswürdigen Herausgebern.
    * `RemoteSigned`: Lokale Skripte ok, Internet-Skripte brauchen Signatur.
* **Trusted Publishers:** Damit ein signiertes Skript ohne Warnung läuft, muss das Zertifikat des Entwicklers im Store "Trusted Publishers" der Clients liegen.

## 6. SSL Interception (Man-in-the-Middle)
* **Konzept:** Ein Proxy (z.B. `mitmproxy`) schaltet sich zwischen Client und Zielwebseite. 
* **Funktionsweise:** Der Proxy generiert "on-the-fly" gefälschte Zertifikate für die Zielseiten. Wenn der Client der CA des Proxys vertraut, wird kein Browser-Fehler angezeigt.
* **Gefahr:** Der Administrator des Proxys kann den gesamten verschlüsselten Verkehr (Passwörter, E-Banking-Daten) im Klartext mitlesen.
* **Schutz:** Wachsamkeit bei der Installation von Stammzertifikaten fremder Anbieter.

---

## 🏆 Wichtigste Key Take-Aways

- **Vertrauensanker:** Sicherheit in einer PKI steht und fällt mit dem Schutz des Private Keys der Root CA. Diese gehört offline.
- **Transparenz:** Das Schloss-Symbol im Browser garantiert nur Verschlüsselung zum ersten Endpunkt, nicht zwingend die End-to-End-Sicherheit zum eigentlichen Server (Gefahr durch SSL Interception).
- **Integrität durch Code Signing:** Digitale Signaturen verhindern unbemerkte Manipulationen an Skripten; veränderte Dateien verlieren sofort ihre Gültigkeit.
- **Widerruf ist essenziell:** Ein Zertifikat muss sofort nach dem Verlust oder dem Austritt eines Mitarbeiters gesperrt werden (OCSP ist hierbei schneller und effizienter als CRL).
- **Zertifikatsketten:** Damit ein Zertifikat als gültig erkannt wird, muss die gesamte Kette (Root -> Intermediate -> End-Entity) für das System nachvollziehbar und vertrauenswürdig sein.