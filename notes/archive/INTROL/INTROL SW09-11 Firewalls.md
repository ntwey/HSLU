# Zusammenfassung: Firewall-Technologien und Laborübungen (Basics & Advanced)

Diese Zusammenfassung deckt die theoretischen Grundlagen von Firewalls, die Basiskonfiguration (Zoning, Routing, NAT) sowie fortgeschrittene Techniken (SSL-Decryption, Threat Analysis, User-ID, App-ID) ab.

## 1. Theoretische Grundlagen: Was ist eine Firewall?

Eine Firewall ist eine Sicherheitskomponente (Hardware oder Software), die den Datenverkehr zwischen verschiedenen Netzwerken (z. B. internes Firmennetzwerk und Internet) überwacht und steuert[cite: 22].

### Das Konzept des Perimeters
Der "Perimeter" bezeichnet die Grenze zwischen dem vertrauenswürdigen internen Netzwerk (Intranet) und dem unsicheren externen Netzwerk (Internet/ISP)[cite: 19]. Eine Firewall setzt an dieser Grenze Regeln durch, um unerwünschte Zugriffe zu blockieren[cite: 20].

### Firewall-Generationen (Evolution der Technik)
Um die Funktionsweise moderner Firewalls zu verstehen, muss man ihre Entwicklungsstufen kennen:

1.  **Generation 1: Paketfilter (Stateless)**
    * **Funktionsweise:** Diese älteste Form arbeitet auf den Schichten 3 (Netzwerk) und 4 (Transport) des OSI-Modells. Sie prüft jedes Datenpaket isoliert anhand von statischen Kriterien: Quell-IP, Ziel-IP, Portnummer und Protokoll (TCP/UDP)[cite: 33].
    * **Nachteile:** Sie versteht keine Zusammenhänge zwischen Paketen (stateless). Antwortpakete müssen explizit erlaubt werden, was die Konfiguration komplex und fehleranfällig macht[cite: 41]. Angriffe, die legitime Ports nutzen, werden oft nicht erkannt.

2.  **Generation 2: Stateful Inspection**
    * **Theorie:** Diese Firewalls merken sich den Status einer Verbindung ("Stateful"). Sie führen eine Statustabelle, um zu wissen, ob ein Paket zu einer bereits bestehenden, erlaubten Verbindung gehört (z. B. ein Antwortpaket eines Webservers auf eine Anfrage eines Clients)[cite: 43].
    * **Vorteil:** Antwortpakete werden automatisch durchgelassen, wenn die initiale Anfrage erlaubt war. [cite_start]Dies erhöht die Sicherheit und vereinfacht das Regelwerk[cite: 46].

3.  **Generation 3: Application Layer Gateway (ALG) / Proxy**
    * **Theorie:** Diese Firewalls arbeiten auf Schicht 7 (Anwendung). Sie verstehen spezifische Protokolle (z. B. HTTP, FTP, SMTP) und agieren als Stellvertreter ("Proxy"). [cite_start]Es gibt keine direkte Verbindung zwischen Client und Zielserver; der Proxy baut die Verbindung stellvertretend auf[cite: 51].
    * **Vorteil:** Sie können Inhalte filtern und Protokollbefehle verstehen.

4.  **Next-Generation Firewall (NGFW)**
    * **Theorie:** Moderne Firewalls (wie die im Labor verwendete Palo Alto PA-440) vereinen alle vorherigen Funktionen und erweitern sie um "Deep Packet Inspection". [cite_start]Sie schauen in den Dateninhalt (Payload), um Anwendungen unabhängig vom Port zu erkennen (App-ID), Benutzer zu identifizieren (User-ID) und Bedrohungen wie Viren oder Exploits zu blockieren (Intrusion Prevention)[cite: 64, 66].

---

## 2. Praxis: Firewall Basics (Labor 5)

In diesem Teil geht es um die Grundkonfiguration einer NextGen-Firewall, um ein Firmennetzwerk (KMU-Szenario) zu schützen.

### 2.1 Netzwerkzonierung (Zoning)
[cite_start]Anstatt nur Interfaces zu verwalten, gruppieren moderne Firewalls Netze in logische "Zonen" mit gleichen Sicherheitsanforderungen[cite: 1426, 1901].
* **Trust / Inside:** Das interne, sichere Netzwerk (z. B. Mitarbeiter-PCs).
* **Untrust / Outside:** Das unsichere externe Netzwerk (Internet).
* **DMZ (Demilitarisierte Zone):** Ein isoliertes Netzwerk für Server, die aus dem Internet erreichbar sein müssen (z. B. Webserver). [cite_start]Sollte ein Server in der DMZ gehackt werden, ist das interne Netz (Inside) durch die Firewall weiterhin geschützt[cite: 1419, 80].

### 2.2 Schnittstellen (Interfaces) & Routing
[cite_start]Die Firewall arbeitet hier im **Layer-3-Modus**[cite: 1494]. Das bedeutet, jedes Interface besitzt eine eigene IP-Adresse und die Firewall fungiert als Router zwischen den Netzen.
* [cite_start]**Virtual Wire:** Ein transparenter Modus (Layer 2), der hier *nicht* verwendet wird, da wir Routing und NAT benötigen[cite: 1495, 1506].

### 2.3 Firewall-Regeln (Security Policies)
Regeln steuern, wer mit wem kommunizieren darf.
* **Whitelisting-Prinzip:** Standardmäßig ist alles verboten ("Deny All"). Nur explizit definierte Verbindungen werden erlaubt. [cite_start]Dies ist sicherer als Blacklisting (alles erlaubt, Böses verboten)[cite: 1566].
* **Top-Down-Prinzip:** Die Firewall arbeitet die Regelliste von oben nach unten ab. Die *erste* zutreffende Regel gewinnt. [cite_start]Spezifische Regeln müssen daher immer *über* allgemeinen Regeln stehen[cite: 1563].
    * *Beispiel:* Eine Regel, die dem Webserver Zugriff auf einen spezifischen FTP-Server erlaubt, muss vor einer Regel stehen, die alles blockiert.

### 2.4 NAT (Network Address Translation)
Da private IP-Adressen (z. B. 192.168.x.x) im Internet nicht geroutet werden, muss die Firewall Adressen übersetzen:
1.  **SNAT (Source NAT):** Wird verwendet, wenn interne Clients ins Internet wollen. Die Firewall ersetzt die private Quell-IP des Clients durch ihre eigene öffentliche IP (Masquerading). [cite_start]Der Server im Internet antwortet an die Firewall, und diese leitet das Paket an den Client weiter[cite: 1545, 1546].
2.  **DNAT (Destination NAT):** Wird verwendet, um Dienste (z. B. Webserver in der DMZ) öffentlich zugänglich zu machen. [cite_start]Anfragen aus dem Internet an die öffentliche IP der Firewall werden an die interne IP des Webservers weitergeleitet[cite: 1659, 1663].
    * [cite_start]*Wichtig:* Bei DNAT beziehen sich Firewall-Regeln auf die *Ziel-Zone* (wo der Server steht, z. B. DMZ) aber auf die *öffentliche Ziel-IP* (vor der Übersetzung)[cite: 1692].

---

## 3. Praxis: Firewall Advanced (Labor 6)

Hier werden die erweiterten Schutzfunktionen der NextGen-Firewall aktiviert.

### 3.1 SSL/TLS Decryption (Entschlüsselung)
**Theorie:** Ein Großteil des heutigen Datenverkehrs ist verschlüsselt (HTTPS). Eine Firewall kann verschlüsselte Pakete normalerweise nicht auf Viren oder verbotene Inhalte prüfen.
**Lösung:** Die Firewall agiert als "Man-in-the-Middle".
1.  Der Client baut eine Verbindung zur Firewall auf.
2.  Die Firewall baut stellvertretend die Verbindung zum Server auf.
3.  [cite_start]Die Firewall entschlüsselt den Verkehr, prüft ihn und verschlüsselt ihn wieder für den Client[cite: 3100, 3102].
**Voraussetzung:** Die Firewall muss Zertifikate on-the-fly generieren. [cite_start]Damit der Browser des Clients keinen Alarm schlägt ("Unsichere Verbindung"), muss das **Stammzertifikat (Root CA)** der Firewall auf dem Client als vertrauenswürdig installiert werden[cite: 3126, 3848].

### 3.2 Malware Threat Analysis
Nach der Entschlüsselung kann die Firewall den Inhalt prüfen:
1.  **Antivirus:** Prüft Dateien während des Downloads gegen bekannte Viren-Signaturen. [cite_start]Wird ein Virus erkannt (z. B. die EICAR-Testdatei), bricht die Firewall die TCP-Verbindung ab (Reset) und verhindert den Download[cite: 3178, 3215].
2.  **WildFire (Sandboxing):** Wenn eine Datei unbekannt ist (Zero-Day-Exploit), wird sie in die Cloud (WildFire) hochgeladen. Dort wird sie in einer gesicherten Umgebung (Sandbox) ausgeführt und ihr Verhalten analysiert. [cite_start]Wird sie als bösartig erkannt, wird weltweit eine Signatur erstellt und verteilt[cite: 3220, 3222].
    * *Problem:* Die Analyse dauert kurz. [cite_start]Der erste Download könnte durchgehen, aber danach ist die Bedrohung global blockiert[cite: 3244].

### 3.3 Authentifizierung & Captive Portal
**Theorie:** IP-Adressen sind keine Personen. Um Regeln für spezifische Benutzer (z. B. "Manager darf alles", "Praktikant darf wenig") zu erstellen, muss die Firewall wissen, wer hinter einer IP steckt.
**Umsetzung:**
* [cite_start]Ein **Captive Portal** fängt HTTP/HTTPS-Anfragen ab und zeigt eine Login-Seite, wenn der Benutzer noch nicht bekannt ist[cite: 3268].
* Nach dem Login verknüpft die Firewall die IP-Adresse mit dem Benutzernamen (User-ID).
* [cite_start]Regeln können nun auf "User" oder "Gruppen" statt auf IPs basieren[cite: 3371].

### 3.4 Layer 7 & App-ID
**Theorie:** Anwendungen lassen sich nicht mehr nur über Ports steuern (viele nutzen Port 80/443). **App-ID** erkennt die Anwendung anhand des Datenstroms.
* [cite_start]*Beispiel:* Man kann "YouTube-Streaming" blockieren, aber die restliche YouTube-Webseite erlauben[cite: 3398].
* **QUIC-Protokoll:** Ein neues Protokoll von Google (basiert auf UDP), das TCP+TLS ersetzt. Firewalls können QUIC oft schlechter inspizieren.
    * *Strategie:* Man blockiert QUIC (UDP 80/443), um den Browser zu zwingen, auf das leichter kontrollierbare TCP/HTTPS zurückzufallen (Fallback). [cite_start]So greifen die Filterregeln wieder[cite: 3416, 3421].

### 3.5 URL-Filtering
Webseiten werden in Kategorien eingeteilt (z. B. "Wetter", "Social Media"). Dies spart Administratoren Arbeit, da sie nicht jede URL einzeln blockieren müssen. [cite_start]Man erstellt Regeln wie "Blockiere Kategorie [Wetter]"[cite: 3348, 3354].
### 3.5 URL-Filtering (Vertiefung)

**Theorie:**
URL-Filtering ermöglicht es, den Zugriff auf Webseiten nicht anhand von IP-Adressen, sondern anhand von URLs oder Kategorien zu steuern. Dies ist wesentlich effizienter, da IP-Adressen von Webseiten sich häufig ändern (z. B. durch Content Delivery Networks) und eine URL oft auf demselben Server gehostet wird wie tausende andere.
Anstatt jede unerwünschte Seite einzeln zu blockieren, nutzen Firewalls **Kategorien** (z. B. "Gambling", "News", "Malware"). Diese Listen werden vom Hersteller (hier Palo Alto Networks) in der Cloud gepflegt und täglich aktualisiert.

**Praxis im Labor (Szenario "Wetter-Block"):**
Im Labor wird ein Szenario umgesetzt, bei dem der Zugriff auf Wetter-Webseiten blockiert werden soll, um die Internetbandbreite zu schonen.
1.  **Custom URL Category:** Da man nicht immer die vordefinierten Kategorien nutzen möchte, erstellt man ein eigenes Objekt (z. B. `block weather`) und fügt spezifische Domains hinzu (z. B. `meteo.ch`, `wetter.ch`). Hierbei können **Wildcards** (z. B. `*.wetter.ch`) verwendet werden.
2.  **Security Policy:** Man erstellt eine Regel, die den Zugriff auf diese Kategorie verbietet ("Deny").
    * *Wichtig:* Diese Regel muss in der Liste *über* der allgemeinen "Allow All"-Regel stehen.
3.  **User-basiertes Filtering:** Oft sollen Regeln nicht für alle gelten (z. B. darf die Geschäftsleitung alles sehen, Mitarbeiter aber nicht).
    * Hierzu verknüpft man die URL-Filter-Regel mit einer **Benutzergruppe** (z. B. "Normalbürger").
    * In der Firewall-Regel wird unter `Source User` diese Gruppe eingetragen.
    * **Ergebnis:** Ein Mitarbeiter (in der Gruppe) wird blockiert, ein Manager (nicht in der Gruppe) kann die Seite aufrufen, da für ihn die Block-Regel nicht greift (die Firewall springt zur nächsten Regel, die den Zugriff erlaubt).

---

### 3.6 Social Media Tracking & Privacy

**Theorie:**
Viele Webseiten binden Elemente von Drittanbietern ein, um Nutzer zu tracken. Ein prominentes Beispiel ist der **Facebook Pixel** oder der "Like-Button".
* **Funktionsweise:** Wenn ein Nutzer eine Webseite besucht, die diesen Pixel enthält, lädt der Browser ein winziges Bild oder Skript von Facebook-Servern nach. Facebook erfährt dadurch, dass Nutzer X (wenn er bei Facebook eingeloggt ist) die Webseite Y besucht hat. Dies dient dem Erstellen von Nutzerprofilen und gezielter Werbung (Retargeting).
* **Problem:** Dies geschieht oft ohne explizites Wissen des Nutzers und stellt ein Datenschutzrisiko dar.

**Praxis im Labor:**
Eine klassische Firewall (Layer 3/4) kann dies nicht verhindern, da sie nur "Verbindung zu Facebook" sieht und diese entweder ganz erlaubt oder ganz verbietet. Eine Next-Generation Firewall (Layer 7) kann jedoch in den Datenstrom schauen.
* **Lösung:** Man erstellt eine Regel, die spezifisch die Anwendung `facebook-social-plugin` blockiert, aber die Hauptanwendung `facebook-base` erlaubt.
* **Effekt:** Der Nutzer kann Facebook normal nutzen, aber auf *anderen* Webseiten werden die Tracking-Pixel und Like-Buttons unterdrückt. Dies zeigt die Granularität von **App-ID**: Man steuert *Funktionen* einer Plattform, nicht nur die Plattform als Ganzes.

---

## 4. Erweiterte Theorie (Ergänzung aus den Grundlagen-Folien)

Dieser Abschnitt fasst die theoretischen Konzepte zusammen, die über die Laborübungen hinausgehen, aber in den Folien ("Firewall_Grundlagen") behandelt wurden.

### 4.1 Content Awareness (File & Data Filtering)
Moderne Firewalls verstehen nicht nur, *welche* Anwendung spricht, sondern auch *was* übertragen wird.
* **File Blocking:** Die Firewall kann den Transfer bestimmter Dateitypen verhindern, unabhängig von der Dateiendung (die man fälschen könnte). Sie analysiert den Datei-Header (Magic Numbers). So kann man z. B. das Herunterladen von `.exe`-Dateien oder verschlüsselten Zip-Archiven verbieten.
* **Data Filtering:** Dient dem Schutz vor Datenabfluss (Data Loss Prevention - DLP). Die Firewall sucht im Datenstrom nach Mustern, z. B. Kreditkartennummern oder Sozialversicherungsnummern, und blockiert den Versand dieser vertraulichen Informationen nach außen.

### 4.2 Web Application Firewall (WAF)
Während die bisher besprochene "Network Firewall" (wie die Palo Alto PA-440) primär die Clients im internen Netz schützt, dient eine WAF dem Schutz von **Webservern**.
* **Einsatzort:** Sie steht typischerweise vor einem Webserver (z. B. Online-Shop).
* **Funktion:** Sie analysiert HTTP/HTTPS-Anfragen auf Angriffe, die speziell auf Webanwendungen zielen, wie z. B.:
    * **SQL-Injection:** Versuch, Datenbankbefehle über Eingabefelder einzuschleusen.
    * **Cross-Site Scripting (XSS):** Einschleusen von schädlichen Skripten.
* **Unterschied zur Network Firewall:** Eine WAF versteht die Logik der Webanwendung tiefer und arbeitet oft als "Reverse Proxy" (nimmt Anfragen an und leitet sie geprüft weiter).

### 4.3 Firewall-Architekturen & Hochverfügbarkeit

Wie wird eine Firewall in das Netzwerk integriert?

1.  **DMZ (Demilitarisierte Zone):**
    * Ein isoliertes Netzsegment für Dienste, die aus dem Internet erreichbar sein müssen (Webserver, Mailserver).
    * **Sicherheitskonzept:** Zugriff vom Internet in die DMZ ist erlaubt (kontrolliert). Zugriff von der DMZ ins interne Netz (Intranet) ist streng verboten. Wird ein Server in der DMZ gehackt, ist das interne Netz noch sicher.

2.  **Hochverfügbarkeit (High Availability - HA):**
    * Da die Firewall der zentrale Flaschenhals ist, führt ein Ausfall zum Stillstand des gesamten Netzwerkverkehrs.
    * **Lösung:** Man nutzt zwei Firewalls im Cluster.
        * **Aktiv/Passiv:** Ein Gerät arbeitet, das andere wartet und übernimmt sofort bei einem Ausfall. Der "State" (Verbindungsstatus) wird ständig synchronisiert, sodass bestehende Downloads bei einem Ausfall nicht abbrechen.
        * **Aktiv/Aktiv:** Beide Geräte arbeiten gleichzeitig (Lastverteilung). Komplexer zu konfigurieren.

3.  **Personal Firewalls:**
    * Software, die direkt auf dem Endgerät (Laptop, Server) läuft (z. B. Windows Defender).
    * **Vorteil:** Schützt das Gerät auch, wenn es sich außerhalb des Firmennetzwerks befindet (z. B. im Homeoffice oder Hotel-WLAN).
    * **Konzept:** "Defense in Depth" – man verlässt sich nicht nur auf die zentrale Firewall, sondern sichert auch die Endpunkte ab.

# Zusammenfassung Teil 3: Spezialthemen & Architekturen

Dieser Teil deckt die verbleibenden Inhalte aus dem Anhang von Labor 6 und den erweiterten Theorie-Folien ab.

## 5. Das Problem mit QUIC (Labor 6 - Anhang B)

Ein sehr wichtiges, modernes Thema, das in den Unterlagen speziell hervorgehoben wird, ist das **QUIC-Protokoll**. Es stellt herkömmliche Firewalls vor große Probleme.

### 5.1 Was ist QUIC?
Normalerweise nutzt das Web (HTTP/HTTPS) das Protokoll **TCP**, welches zuerst einen "Handshake" macht. Firewalls lieben TCP, weil sie genau sehen, wann eine Verbindung beginnt und endet.
Google hat jedoch **QUIC** entwickelt, um das Web schneller zu machen. Es basiert auf **UDP** (User Datagram Protocol).
* **Vorteil:** Es ist extrem schnell (kein langer Handshake, integrierte Verschlüsselung).
* **Verbreitung:** Wird von Google-Diensten (YouTube, Gmail, Suche) und Chrome standardmäßig genutzt.

### 5.2 Warum ist QUIC ein Sicherheitsrisiko?
Da QUIC auf UDP (Port 80 oder 443) basiert und *alles* (auch den Verbindungsaufbau) verschlüsselt, sieht eine Firewall nur einen undurchsichtigen Strom von UDP-Paketen.
* **Blindflug:** Die Firewall erkennt nicht mehr, welche "App" darin läuft. Sie sieht nur "UDP Traffic".
* **Verlust der Kontrolle:** Sicherheitsfunktionen wie **App-ID** (z. B. "Blockiere nur YouTube-Uploads") oder **URL-Filtering** funktionieren oft nicht mehr zuverlässig. Die Firewall kann den verschlüsselten Header nicht lesen (anders als bei TLS/TCP, wo der Start noch sichtbar ist).

### 5.3 Die Lösung im Labor (Best Practice)
Da man QUIC oft nicht inspizieren kann, ist die Strategie paradoxerweise: **Blockieren**.
1.  Man erstellt eine Firewall-Regel, die **UDP auf Port 80 und 443 blockiert** (das ist der QUIC-Verkehr).
2.  **Der Fallback:** Moderne Browser (Chrome) sind schlau. Wenn sie merken, dass QUIC blockiert wird, fallen sie automatisch auf das klassische **TCP/HTTPS** zurück.
3.  **Ergebnis:** Der Verkehr läuft nun wieder über TCP, und die Firewall kann ihre Inspektions-Mechanismen (SSL Decryption, App-ID) wieder voll anwenden. Man zwingt den Browser also auf den sicheren, kontrollierbaren Weg.

---

## 6. Erweiterte Firewall-Architekturen

In größeren Firmen reicht eine einzelne Firewall oft nicht aus. Die Theorie-Folien stellen komplexere Designs vor:

### 6.1 Sandwich-Architektur (Hochsicherheit)
Hier werden zwei verschiedene Firewalls hintereinander geschaltet (z. B. eine von Cisco, dahinter eine von Palo Alto).
* **Idee:** Wenn ein Hacker eine Sicherheitslücke in der ersten Firewall findet, steht er immer noch vor der zweiten. Da beide von verschiedenen Herstellern sind, ist es unwahrscheinlich, dass beide dieselbe Lücke haben.

### 6.2 Interne Firewalls (Micro-Segmentation)
Früher war das interne Netz (Intranet) offen ("harte Schale, weicher Kern"). Heute geht man davon aus, dass Hacker bereits *im* Netz sind.
* **Lösung:** Man setzt Firewalls auch *zwischen* internen Abteilungen ein (z. B. darf die Buchhaltung nicht auf die Entwickler-Server zugreifen).

### 6.3 Multi-Homing
Die Firma ist über mehrere Internet-Provider (ISPs) angebunden. Die Firewall muss intelligent entscheiden, welche Leitung genutzt wird (Load Balancing) oder bei Ausfall umschalten (Failover).

---

## 7. Open Source & Host-Firewalls

Nicht jede Firewall ist eine teure Hardware-Box (Appliance). Die Unterlagen erwähnen wichtige Software-Alternativen:

### 7.1 Linux Netfilter / iptables
Das ist die Ur-Firewall, die in jedem Linux-System steckt. Sie arbeitet strikt nach Tabellen und Ketten ("Chains"):
* **INPUT Chain:** Pakete, die an den Computer selbst gerichtet sind (z. B. SSH-Login).
* **OUTPUT Chain:** Pakete, die der Computer selbst sendet.
* **FORWARD Chain:** Pakete, die der Computer nur weiterleitet (wichtig, wenn der Linux-Server als Router für andere dient).
* *Praxis:* Die Konfiguration erfolgt über die Kommandozeile und ist sehr mächtig, aber komplex.

### 7.2 pfSense / OPNsense
Dies sind vollständige Firewall-Distributionen (basierend auf FreeBSD), die man auf eigener Hardware installieren kann.
* **Vorteil:** Kostenlose Open-Source-Software, die fast so viel kann wie teure kommerzielle Firewalls (VPN, Geoblocking, Plugins).
* **Einsatz:** Beliebt bei kleinen Firmen (KMUs) oder ambitionierten Heimnetzwerken ("Prosumer"). Sie bieten eine grafische Weboberfläche, sodass man keine kryptischen Befehle tippen muss.

### 7.3 Personal Firewalls (Windows Defender)
Eine Firewall, die direkt auf dem Endgerät (Client) läuft.
* **Wichtig für:** Laptops, die das Firmennetz verlassen (Homeoffice, Hotel). Die Firmen-Firewall schützt sie dort nicht, also muss sich das Gerät selbst schützen.

# Zusammenfassung Teil 4: Operative Konzepte & Troubleshooting

Während die vorherigen Teile sich auf Regeln und Schutzfunktionen konzentrierten, geht es hier darum, wie man die Firewall bedient, Fehler findet und wie sie intern arbeitet.

## 1. Das Konfigurations-Modell (Candidate vs. Running)
Anders als einfache Router oder alte Firewalls, die Änderungen sofort anwenden, nutzen professionelle Systeme (wie im Labor die Palo Alto) ein zweistufiges Modell.

### 1.1 Candidate Configuration (Entwurf)
* Wenn Sie eine Änderung vornehmen (z. B. eine neue Regel erstellen), wird diese **nicht sofort aktiv**.
* Sie wird in einer temporären Datenbank gespeichert, der "Candidate Config".
* **Vorteil:** Sie können mehrere komplexe Änderungen vorbereiten (z. B. neues Objekt + neue NAT-Regel + neue Security-Rule), ohne dass zwischendurch der Verkehr abbricht oder Sicherheitslücken entstehen.

### 1.2 Commit (Aktivierung)
* Erst durch den Befehl **"Commit"** wird der Entwurf in die "Running Configuration" übernommen und aktiv.
* Dabei prüft das System auf Logikfehler (z. B. "Objekt wird genutzt, existiert aber nicht").
* *Labor-Praxis:* Ein häufiger Fehler ist, dass Studenten alles konfigurieren, aber den "Commit" vergessen und sich wundern, warum nichts funktioniert.

---

## 2. Management Plane vs. Data Plane (Architektur)
Eine professionelle Firewall trennt strikt zwischen "Verwaltung" und "Datenverarbeitung".

* **Data Plane (Daten-Ebene):** Hier laufen die eigentlichen Datenpakete der Nutzer durch. Eigene Prozessoren (FPGAs/ASICs) kümmern sich nur um Routing, NAT, Entschlüsselung und Virenscan. Wenn die Firewall unter voller Last steht (DDoS-Angriff), ist die Data Plane zu 100% ausgelastet.
* **Management Plane (Verwaltungs-Ebene):** Ein separater Prozessor (CPU) und Arbeitsspeicher, der nur für das Web-Interface (GUI), CLI (Konsole) und Logging zuständig ist.
* **Der Clou:** Selbst wenn das Netzwerk durch einen Angriff komplett lahmgelegt ist (Data Plane 100%), können Sie sich noch als Admin einloggen (Management Plane), da diese Ressourcen getrennt sind. Sie sperren sich nicht selbst aus.
* *Im Labor:* Deshalb nutzen Sie ein spezielles Kabel am "MGMT"-Port, der nicht mit dem normalen Datenverkehr gemischt wird ("Out-of-Band Management").

---

## 3. Monitoring & Troubleshooting (Der "Monitor"-Tab)
Wenn etwas nicht geht, ist "Raten" schlecht. Die Firewall protokolliert alles.

### 3.1 Traffic Logs
* Hier sehen Sie jede Verbindung. Wichtig ist die Spalte **"Action"**:
    * `Allow`: Verbindung erlaubt.
    * `Deny` / `Drop`: Verbindung blockiert.
* **Wichtig:** Wenn eine Verbindung blockiert wird, steht dort oft auch die **"Policy"** (Regel), die verantwortlich war. Meistens ist es die "Interzone-Default" (die implizite "Alles Verboten"-Regel ganz unten).
* *Application:* Hier sehen Sie, als was die Firewall den Verkehr erkannt hat (z. B. "google-base" oder "ssl"). Wenn hier "unknown-tcp" steht, konnte App-ID den Verkehr nicht identifizieren (oft ein Zeichen für proprietäre oder verschlüsselte Verbindungen ohne Decryption).

### 3.2 System Logs
* Hier stehen administrative Ereignisse: "Admin X hat sich eingeloggt", "Interface link down" (Kabel gezogen), "Datenbank-Update fehlgeschlagen".

### 3.3 ACC (Application Command Center)
* Dies ist eine grafische Übersicht (Dashboard). Es zeigt nicht einzelne Pakete, sondern Trends: "Welche Anwendung verbraucht die meiste Bandbreite?", "Aus welchem Land kommen die meisten Angriffe?", "Welcher User verursacht das höchste Risiko?".

---

## 4. Spezifische Schutzmechanismen (Ergänzung)

### 4.1 Zone Protection (DoS-Schutz)
In den Grundlagen wurde erwähnt, dass man Zonen (Trust/Untrust) hat. Man kann auf der Zone selbst Schutzprofile aktivieren ("Zone Protection Profiles").
* **Flood Protection:** Verhindert, dass ein Angreifer die Firewall mit zu vielen Verbindungsanfragen (SYN Flood) überflutet. Die Firewall nutzt "SYN Cookies", um legitime Anfragen von Angriffen zu unterscheiden, bevor sie Ressourcen reserviert.
* **Reconnaissance Protection:** Erkennt Port-Scans (wenn jemand versucht, offene Türen zu finden) und blockiert die Quell-IP temporär.

### 4.2 DNS Sinkhole (Teil der Anti-Spyware)
Ein sehr eleganter Mechanismus im Labor 6 (Threat Prevention):
* Wenn ein infizierter Client versucht, einen "Command & Control"-Server von Hackern zu kontaktieren, löst er dessen Namen per DNS auf (z. B. `ping evil-hacker.com`).
* Die Firewall erkennt die böse Domain. Anstatt die Anfrage einfach zu löschen (was beim Client zu einem Timeout führt), antwortet die Firewall mit einer **falschen IP-Adresse** (der Sinkhole-IP).
* **Vorteil:** Der Client versucht nun, diese Sinkhole-IP zu kontaktieren. Da diese IP der Firewall gehört, sieht der Admin im Log sofort: "Client X ist infiziert", weil er versucht, das Sinkhole zu erreichen. So findet man infizierte Geräte im internen Netz.
# Zusammenfassung Teil 5: Infrastruktur & NAT-Vertiefung

Dieser Teil behandelt Funktionen, die die Firewall zu einem zentralen Netzwerk-Knoten machen, sowie die kniffligen Details der Adressübersetzung.

## 1. Die Firewall als Netzwerk-Diener (Infrastructure Services)
Eine Firewall ist oft mehr als nur ein "Türsteher". In vielen Netzwerken (besonders in kleineren wie im Labor-Setup) übernimmt sie auch Aufgaben eines Routers und Servers.

### 1.1 DHCP-Server (Dynamic Host Configuration Protocol)
* **Problem:** Wenn Sie Ihren Laptop an das "Inside"-Interface anschließen, braucht er eine IP-Adresse.
* **Lösung:** Die Firewall fungiert als DHCP-Server.
* **Funktionsweise:** Sie konfigurieren auf dem Interface (z. B. ethernet1/2) einen DHCP-Bereich (z. B. `192.168.1.100` bis `192.168.1.200`). Wenn ein Client sich verbindet, weist die Firewall ihm automatisch eine IP, die Subnetzmaske und sich selbst als **Standard-Gateway** zu.
* *Wichtig:* Ohne Gateway weiß der Client nicht, wohin er Pakete fürs Internet schicken soll.

### 1.2 DNS-Proxy
* **Problem:** Clients müssen Namen (google.com) in IP-Adressen umwandeln. Clients in einem isolierten Netz erreichen aber oft die externen DNS-Server (8.8.8.8) nicht direkt oder man möchte dies kontrollieren.
* **Lösung:** Man aktiviert den DNS-Proxy auf der Firewall.
* **Ablauf:**
    1. Der Client fragt die Firewall: "Wer ist google.com?".
    2. Die Firewall (die Zugriff aufs Internet hat) fragt den echten DNS-Server im Internet.
    3. Die Firewall gibt die Antwort an den Client zurück.
* **Vorteil:** Die Firewall kann diese Anfragen filtern (z. B. Anfragen zu "malware.com" abfangen -> DNS Sinkhole, siehe Teil 3).

---

## 2. Interface Management Profiles (Ping & SSH)
Ein klassischer Anfängerfehler im Labor: Sie haben die Firewall konfiguriert, aber Sie können das Gateway (die Firewall-IP) nicht "pingen".
* **Der Grund:** Aus Sicherheitsgründen ist eine Firewall standardmäßig "unsichtbar" (Stealth Mode). Sie antwortet nicht auf Ping (ICMP) und lässt keinen SSH-Zugriff auf ihren Daten-Interfaces zu.
* **Die Lösung:** Sie müssen ein **Interface Management Profile** erstellen.
    * Profil anlegen (z. B. Name: "Allow-Ping").
    * Checkbox bei "Ping" setzen.
    * Dieses Profil dem Interface (z. B. ethernet1/1) zuweisen.
* *Sicherheit:* Auf dem externen Interface (Internet) sollte man *nie* SSH oder das Web-Interface erlauben, da sonst jeder aus dem Internet die Firewall hacken könnte. Ping ist meist okay zur Diagnose.

---

## 3. Das NAT-Paradoxon (Sehr wichtig!)
Das ist der schwierigste theoretische Teil bei Palo Alto Firewalls. Wenn Sie **Destination NAT** (DNAT) machen (z. B. um einen Webserver in der DMZ öffentlich erreichbar zu machen), müssen Sie die Regeln sehr genau verstehen.

Stellen Sie sich vor:
* **User im Internet:** IP `80.80.80.80`
* **Firewall Extern:** IP `203.0.113.1`
* **Webserver (DMZ):** IP `10.0.0.10`

### Der Paketfluss bei DNAT
Wenn der User den Webserver aufruft, schickt er ein Paket an `203.0.113.1` (die öffentliche IP der Firewall).

1.  **NAT-Regel (Was passiert?):**
    * "Wenn jemand auf `203.0.113.1` zugreift, leite es weiter an `10.0.0.10`."
    * Dies ändert die *Destination IP* im Paket.

2.  **Sicherheits-Regel (Wer darf was?):**
    * Hier passiert der Fehler: Welche IP und welche Zone trage ich in die "Allow"-Regel ein?
    * **Die Regel:** Palo Alto prüft die Sicherheits-Policy **nach** der Zonen-Erkennung, aber basierend auf der **Original-IP** (Pre-NAT IP).
    * **Konkret heißt das:**
        * **Destination Zone:** `DMZ` (Denn dort steht der Server *wirklich*).
        * **Destination IP:** `203.0.113.1` (Die *öffentliche* IP, die der User ursprünglich angesprochen hat).

**Merksatz:** "Security Policy schaut auf die **Zone wo es hingeht** (Post-NAT Zone), aber auf die **Adresse wie sie ankam** (Pre-NAT IP)."

---

## 4. VPN (Virtual Private Network)
Obwohl es in den Labor-Unterlagen nicht im Detail konfiguriert wurde, taucht es in der Theorie (Grundlagen-Folien) und als Feature von pfSense auf.

### 4.1 Site-to-Site VPN
* **Szenario:** Eine Firma hat zwei Standorte (z. B. Luzern und Zürich).
* **Funktion:** Die Firewalls beider Standorte bauen über das unsichere Internet einen verschlüsselten Tunnel (IPsec) auf.
* **Ergebnis:** Für die Mitarbeiter sieht es so aus, als wären beide Netzwerke direkt verbunden. Ein PC in Luzern kann auf den Server in Zürich zugreifen, ohne dass Daten im Internet lesbar sind.

### 4.2 Remote Access VPN (Client-to-Site)
* **Szenario:** Homeoffice.
* **Funktion:** Auf dem Laptop läuft eine Software (z. B. GlobalProtect Agent). Dieser baut einen Tunnel zur Firmen-Firewall auf.
* **Sicherheit:** Der gesamte Firmen-Verkehr (oder alles, "Full Tunnel") fließt verschlüsselt zur Firewall. Dort wird er geprüft (Virenscan, URL-Filter), als ob der Mitarbeiter im Büro säße.

---

## 5. Intra-Zone vs. Inter-Zone (Default Regeln)
Ganz unten im Regelwerk der Firewall (oft unsichtbar, aber da) gibt es zwei Standard-Regeln:

1.  **Intra-Zone-Default (Allow):**
    * Verkehr *innerhalb* derselben Zone (z. B. von PC A im "Trust" zu PC B im "Trust") ist **standardmäßig erlaubt**. Die Firewall schaut hier oft nicht tief hinein, es sei denn, man erstellt explizite Block-Regeln.
2.  **Inter-Zone-Default (Deny):**
    * Verkehr *zwischen* verschiedenen Zonen (z. B. von "Trust" zu "Untrust") ist **standardmäßig verboten**.
    * Deshalb müssen Sie für *jeden* Internetzugriff explizit eine Regel erstellen ("Trust to Untrust -> Allow").