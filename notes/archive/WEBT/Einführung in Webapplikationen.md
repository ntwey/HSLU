## Klassifikationen von Webapplikationen
### Statisch
	-Text
	-Hyperlinks
	 -Vorteile
		 -Lädt schneller
		 -Einfach HTML CSS
		 -Wenig Infrastrucktur
		 -SEO (Search Engine Optimation)
	 -Nachteile
		 - Keine Aktualisierung und Konstienz probleme (Lösbar durch Generatoren)
		 - Keine Anwendungsfunktionalität (Suchen, Bestellen)
### Dynamisch
	-Interagieren
		-Rezensionen schrieben etwas suchen
	-Formular wird gesendent
	-Verarbeitungsprogramm (User Input > Backend Verabeitet > Zurück an User)
	-Verwenden Web-Service
	-Vorteile
		- 
	-Nachteile
		- Lädt lenger 
### Mobile Web Applikationen
	-Ist halt Mobile CSS
## Single Page Application
-Läuft vor allem auf dem Client
-Template Rendering
-Modularisierung des Codes (Libs)

## Webservice
	-Black box Prinzip
	-Maschine zu Maschine Kommunikation
	-Offne standart (HTTP)
	-lose gekoppelt
	-

## Grundlegende Internet und Webkonzepte
### DNS
	-Domain Name Sytem 
	-Verbindet Hostnamen wie SBB.ch zu einer IP
	-Besteht aus einem Hierarchischem System von Name Servern
	-DNS Server gibt zurück:
		-IP-Adresse
		-Nächster Name Server
		- oder das die Domain nicht existiert
	-Aufbau von DNS
		-Top Level Domains
			-.com / .ch / .org
		-Second level domains
			-ibm / sbb / hslu
		-Third level Domain
			-www / cs / ee
		- Beispiel
			- (www.hslu.ch)
				- www Thrid Level
				- hslu Second Level
				- ch Top Level
### HTTP
	-Einfaches Request-Reponse Protokoll
	-Datenaustausch erfolgt über HTTP-Nachrichten
	-Aktuelle Version ist 3.0
	-Request-Struktur
		-Request Besteht aus
			-METHOD POST, GET, PUT, DELETE
			-URL SBB.CH
			-HTTP/Version HTTP/1.1
			-HEADER AGENT: MOZZILA FIREFOX
			-BODY JSON
	-Response-Struktur
		-Response Besteht aus
			-HTTP/VERSION, Status Code: HTTP/1.1 200 OK
			-Response Headers Server: Apache/2.4.46
			-BODY: HTML JSON CSS
		
	-HTTP Response Codes
		-1xx: Informelle Meldungen
		-2xx: Erfolg
		-3xx: Weiterleitung
		-4xx: Client fehler
			-Server Sagt: Deal with it
		-5xx: Server fehler
### URL
	-Formale Syntax:
		-<Protokol>://<Domain-Name>:<Port>/<Pfad>/<Dateiname>
	-Ports
		- Standart Es gibt viele Standart Port Nummern 
		- Ports können angepassed werden zum beispiel wenn mehrer Webserver den gleichen benutzen würden. 
		- Apache Webserver HTTP 80/ HTTPS 443
		- Tomcat Webserver: 8080
		- Mailserver: SMTP:25 IMAP:993
		- Printserver: 631
## Übungen