# Security Architecture & Secure Design

Der Software-Architekt fungiert als Brücke zwischen den geschäftlichen Anforderungen und dem technischen Design. Er strukturiert die Software in Blöcke, definiert Schnittstellen (APIs) und legt fundamentale Architekturprinzipien fest.

## 1. Das OSI-Schichtenmodell (Open Systems Interconnection)
Für das Verständnis von Netzwerkschnittstellen und deren Absicherung ist das OSI-Modell das theoretische Fundament:
1. **Application:** HTTP, FTP, SMTP (Ebene der Web-Applikations-Sicherheit)
2. **Presentation:** Datentranslation, Serialisierung / Deserialisierung
3. **Session:** Verbindungsmanagement, Session-Handling
4. **Transport:** TCP, UDP (Ende-zu-Ende-Verbindung, Verschlüsselung via TLS)
5. **Network:** IP, ICMP (Routing, Paketweiterleitung)
6. **Data Link:** Ethernet, MAC-Adressierung
7. **Physical:** Physikalische Übertragung (Funk, Glasfaser, Kupfer)

## 2. HTTP-Struktur & Die Rolle des Reverse Proxies
Das HTTP-Protokoll ist stateless und modular aufgebaut (bestehend aus Method, Header und Body). Zur Absicherung moderner Webanwendungen wird im Netzwerk-Design ein **Reverse Proxy** als zentraler, gehärteter Einstiegspunkt vor die internen Netzwerke geschaltet.

### Kernfunktionen eines Reverse Proxies:
* **Web Application Firewall (WAF):** Filterung bösartiger Payloads (z. B. SQLi, XSS) auf Applikationsebene.
* **Load Balancing:** Gleichmässige Verteilung der Last auf dahinterliegende Server-Instanzen.
* **SSL/TLS-Terminierung:** Zentrale Verwaltung und Durchsetzung von Verschlüsselung und HTTPS-Zertifikaten.
* **Single Sign-On (SSO) & Request Dispatching:** Zentrale Authentifizierung und intelligentes Routing von Anfragen.
* **Rewriting:** Maskierung interner URL-Strukturen zur Reduktion der Angriffsfläche.

## 3. Web-Applikationsarchitekturen
* **Monolithisch:** UI, Business-Logik und Datenzugriff sind untrennbar in einem einzigen, grossen Softwareblock vereint. Schwer zu segmentieren.
* **Microservices:** Das System ist in kleine, unabhängige, spezialisierte Dienste unterteilt, die isoliert laufen und ausschliesslich über definierte Schnittstellen (APIs) kommunizieren.
* **Traditionell (Server-Side Rendering):** Business- und Präsentationslogik liegen komplett auf dem Server (z. B. JavaServer Faces, PHP). Der Server generiert fertiges HTML für den Client.
* **Modern (Client-Side Rendering):** Die View-Logik ist vollständig auf den Client ausgelagert (Single Page Applications mit Angular, Vue, React). Daten werden ausschliesslich im JSON-Format per API vom Backend geladen.
* **MVC-Pattern (Model-View-Controller):** Standard-Strukturmuster. Das *Model* verwaltet Datenstruktur und Geschäftslogik, die *View* übernimmt die Darstellung (HTML/UI) und der *Controller* verarbeitet Benutzereingaben und steuert den Datenfluss.

## 4. Secure Design Prinzipien ("Secure by Design")
Sicherheitslücken entstehen meist bereits in der Designphase durch zu hohe Komplexität. Folgende Prinzipien sind zwingend umzusetzen:
* **Least Privilege (Geringste Rechte):** Gewähre einem Subjekt (User, Prozess) nur die absolut minimal notwendigen Rechte, die für seine aktuelle Aufgabe zwingend erforderlich sind.
* **Defense in Depth (Mehrstufige Verteidigung):** Verlasse dich niemals auf ein einzelnes Sicherheitswerkzeug. Implementiere ein mehrschichtiges Sicherheitskonzept ("Castle Approach") über alle Ebenen.
* **Minimum Exposure (Minimale Angriffsfläche):** Deaktiviere alle ungenutzten Features, Ports, Services und Standard-Accounts. Je kleiner die Angriffsfläche, desto schwieriger der Einbruch.
* **Keep it Simple (KISS):** Komplexität ist der absolute Feind der Sicherheit. Einfache Systeme lassen sich weitaus zuverlässiger auditieren und absichern.
* **Fail Securely:** Systeme müssen bei Fehlern, Abstürzen oder Stromausfällen ausnahmslos in einen sicheren Zustand übergehen (z. B. elektronische Türen bleiben bei Stromausfall von aussen verschlossen).
* **Traceability (Lückenlose Protokollierung):** Implementierung eines manipulationssicheren Audit Trails (Wer hat wann was getan?), ohne jemals sensible Daten wie Passwörter im Klartext in die Logs zu schreiben.
* **Segmentation:** Logische Aufteilung von Anwendungen und Netzwerken in isolierte Zonen, um Kollaterschäden bei einem erfolgreichen Einbruch effektiv zu begrenzen.
* **Encrypt Everywhere:** Durchgehende, moderne Verschlüsselung aller Daten (in transit und at rest).

## 5. Die Zero Trust Strategie
Ein fundamentaler Paradigmenwechsel weg von der klassischen Perimeter-Sicherheit ("Vertrauen innerhalb des internen Firmennetzwerks") hin zu einem modernen, standortunabhängigen Sicherheitsmodell:
* **Leitsatz:** "Never trust, always verify" – Vertraue niemandem, verifiziere absolut alles, zu jeder Zeit.
* **Explizite Verifizierung:** Identität, Gerätestatus, Standort und Berechtigung werden bei *jeder einzelnen* Verbindung überprüft.
* **Annahme eines Breaches (Assume Breach):** Das Gesamtsystem wird strategisch so designt, als befände sich der Angreifer bereits vollumfänglich im internen Netzwerk.

Verknüpftes Thema: [[06_Authentifizierung_und_Autorisierung]] (Die technische Umsetzung von Identitätsprüfungen).