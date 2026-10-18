Wissensmanagement: Privatheit, Technologie und Datenschutz

# Grundlagen und rechtliche Rahmenbedingungen

## Privatheit versus Datenschutz

[[Privatheit]] (Privacy) umfasst das Recht auf Vertraulichkeit von persönlichen Daten und das Recht auf eine private Sphäre, während der [[Datenschutz]] nicht die Daten selbst, sondern die dahinterstehenden natürlichen Personen vor missbräuchlicher Verarbeitung schützt. Im Zentrum steht das Grundrecht auf [[Informationelle Selbstbestimmung]], welches besagt, dass jede Person grundsätzlich selbst entscheiden darf, wer, wann und aus welchen Gründen Informationen über sie bearbeiten darf.

## Kategorien von Daten

In der Datenverarbeitung wird strikt zwischen verschiedenen Informationsarten unterschieden. Zu den [[Personendaten]] zählen alle Informationen, die sich auf eine identifizierte oder identifizierbare natürliche Person beziehen (wie Name, IP-Adresse oder Standort). Davon abzugrenzen sind reine Sachdaten ohne Personenbezug. Eine gesonderte und besonders geschützte Kategorie bilden die [[Besonders schützenswerte Daten]], wozu beispielsweise Informationen über die Religion, die Gesundheit, die Intimsphäre oder politische Ansichten gehören.

## Rechtliche Regelungen

Zwei zentrale Gesetzeswerke prägen den Datenschutz: Die europäische [[EU-DSGVO]] (Datenschutz-Grundverordnung) und das Schweizer [[revDSG]] (revidiertes Datenschutzgesetz). Während die EU-DSGVO stark auf eine explizite, aktive Einwilligung (Opt-in) fokussiert, ist in der Schweiz für Alltagsgeschäfte oft eine Information ausreichend und der Fokus liegt auf Transparenz sowie Treu und Glauben. Ein fundamentales Prinzip beider Gesetze ist die [[Zweckbindung]]: Daten dürfen ausschliesslich für den Zweck verwendet werden, der bei der Erhebung angegeben wurde.

# Identitätskonzepte und Anonymisierung

## Definition von Identität

Eine [[Identität]] ist eine Menge von Attributwerten, die ein Subjekt innerhalb einer Gruppe unterscheidbar und somit identifizierbar macht. Die [[Digitale Identität]] besteht aus technisch abbildbaren Attributen. Hierbei wird in der Praxis zwischen direkten Identifikatoren (wie Name oder E-Mail) und [[Quasi-Identifikatoren]] (wie Geburtsdatum, Geschlecht oder Postleitzahl) unterschieden, die erst in ihrer Kombination zu einer eindeutigen Identifikation führen.

## Anonymität und Pseudonymität

[[Anonymität]] ist dann gegeben, wenn ein Angreifer eine Person in einer Menge von Subjekten (der Anonymitätsmenge) nicht hinreichend identifizieren kann. Bei der [[Pseudonymität]] wird der reale Name durch einen anderen Identifikator ersetzt, beispielsweise durch einen Hash-Wert oder einen Benutzernamen. Wahre Anonymisierung ist schwer zu erreichen, da oftmals bereits die Verknüpfung weniger Datenpunkte zur Aufdeckung der Identität ausreicht.

## Anonymisierungs-Modelle und Risiken

Traditionelle Anonymisierung versagt häufig in der Ära von Big Data, da sogenannte Linkage-Attacks (Verknüpfungsangriffe) durch die Kombination mit externen Datensätzen eine [[Re-Identifizierung]] ermöglichen. Um diesen Angriffen entgegenzuwirken, existieren formale Modelle wie die [[K-Anonymität]] (bei der ein Datensatz von mindestens k-1 anderen ununterscheidbar sein muss), die [[L-Diversität]] (welche Homogenitätsangriffe verhindert) und die [[T-Closeness]].

# Technische und organisatorische Massnahmen (TOM)

Die Datenschutzgesetze verlangen den Einsatz angemessener Technischer und organisatorischer Massnahmen ([[TOM]]), um Personendaten vor unbefugter Bearbeitung zu schützen. Organisatorische Massnahmen umfassen Mitarbeiterschulungen, Notfallpläne und die Definition von Rollen. Technische Massnahmen beinhalten unter anderem die [[End-to-End-Verschlüsselung]], Zugangskontrollen mittels [[Multi-Faktor-Authentifizierung]] (2FA/MFA) und den Zugriffsschutz durch rollenbasierte Konzepte (RBAC).

## Privacy by Design und Privacy by Default

Das Architekturkonzept [[Privacy by Design]] (PbD) erfordert, dass der Datenschutz proaktiv und von Beginn an in die Entwicklung von Systemen integriert wird, statt erst nachträglich als Feature hinzugefügt zu werden. Eng damit verknüpft ist [[Privacy by Default]], welches sicherstellt, dass die Werkseinstellungen eines Systems stets die datenschutzfreundlichste Option darstellen. Ein praktisches Umsetzungsbeispiel hierfür ist das Tool [[Databunker]], eine Open-Source-Lösung zur sicheren Speicherung sensibler Benutzerdaten mit automatischer Verschlüsselung.

## Privacy Enhancing Technologies (PETs) und neue Schutzziele

Um den Datenschutz auf technischer Ebene zu maximieren, kommen Privacy Enhancing Technologies ([[PETs]]) zum Einsatz, wie beispielsweise das [[Onion Routing]] (TOR) oder Mix-Netze zur Anonymisierung der Kommunikation. Zu den modernen Schutzzielen der Datensicherheit zählen neben Vertraulichkeit, Integrität und Verfügbarkeit auch neue Aspekte: Transparenz, [[Unverkettbarkeit]] (Daten dürfen nicht zweckentfremdet verknüpft werden) und Intervenierbarkeit (Betroffene müssen Rechte wie Löschung oder Korrektur wirksam ausüben können).

# Tracking-Technologien

## Web Tracking

Im Web wird das Nutzerverhalten meist durch eindeutiges Tracking (via Login) oder wahrscheinliches Tracking (mittels Machine Learning ohne Login) analysiert. Traditionell kamen hierfür [[Cookies]] (First-Party und Third-Party) zum Einsatz. Da moderne Browser Third-Party-Cookies zunehmend blockieren, weicht die Werbeindustrie auf neue und aggressivere Methoden aus. Dazu gehören das [[Browser-Fingerprinting]] (statuslose Wiedererkennung über einzigartige Hardware- und Softwarekonfigurationen), das [[CNAME-Cloaking]] (Tarnung von Third-Party-Trackern als First-Party-Subdomains auf DNS-Ebene) sowie das serverseitige Tracking, welches für den Browser gänzlich unsichtbar abläuft.

## Device und Location Tracking

Über das reine Web hinaus sammeln mobile Apps, IoT-Geräte und Smart Speaker detaillierte physische sowie biometrische Daten. Beim mobilen Tracking werden kontinuierlich Standortdaten über GPS, Mobilfunkmasten und WLAN-Netze erfasst. Das Risiko für die Privatsphäre ist dabei massiv: Studien zeigen, dass mit nur vier Raum-Zeit-Koordinaten 95 Prozent aller Personen eindeutig identifiziert werden können.

# Open Source Intelligence (OSINT)

## Techniken und Profilbildung

[[OSINT]] (Open-Source Intelligence) bezeichnet die systematische Sammlung und Analyse von öffentlich zugänglichen Informationen im Internet. Zu den gängigen technischen Dimensionen gehören die Extraktion von Metadaten ([[EXIF]]) aus hochgeladenen Bildern, die umgekehrte Bildsuche sowie die Korrelation von Benutzernamen über verschiedene Plattformen hinweg. Durch diese Verknüpfung isolierter Datenpunkte entsteht der sogenannte Kontextkollaps, wodurch detaillierte und hochintime Persönlichkeitsprofile erstellt werden.

## Rechtliche und ethische Aspekte

Dass Daten öffentlich zugänglich sind, bedeutet nicht, dass sie beliebig zweckentfremdet oder für Profiling ausgewertet werden dürfen; die gesetzliche Zweckbindung gilt weiterhin. Der massenhafte und automatisierte Einsatz von OSINT bewegt sich oft in einer ethischen Grauzone, da die Analysen eine sehr viel tiefere Einsicht in das Leben einer Person gewähren, als diese ursprünglich bei der Veröffentlichung beabsichtigt hatte. Gefahren dieser Praktiken sind unter anderem Doxxing, Social Engineering, Stalking und Wirtschaftsspionage.

# Datenschutz in der Künstlichen Intelligenz

## Differential Privacy

In der modernen Datenökonomie und bei der Entwicklung von KI-Modellen entsteht ein Datendilemma: Unternehmen benötigen grosse Datenmengen für maschinelles Lernen, während die Nutzer den Schutz ihrer Privatsphäre fordern. Als Lösung hat sich [[Differential Privacy]] (DP) etabliert. Dies ist ein mathematisches Verfahren, bei dem den aggregierten Datensätzen gezielt statistisches Rauschen hinzugefügt wird. Die Identifikation von Einzelpersonen wird dadurch verunmöglicht, während das Modell auf Gruppenebene korrekte Muster erkennt. Dies gewährt den Nutzern [[Plausible Deniability]] (glaubhafte Abstreitbarkeit). Ein anschauliches Beispiel hierfür ist der Münzwurf-Algorithmus: Bei sensiblen Fragen kann das System durch einen simulierten «Münzwurf» zufällige Antworten senden, sodass nie mit Sicherheit gesagt werden kann, ob die erfasste Antwort der Wahrheit entspricht. Technologiegiganten wie Apple, Google, Microsoft sowie das US Census Bureau nutzen Differential Privacy bereits produktiv in der Praxis.

# Kryptografische Verfahren zum Datenschutz (Cryptographic Privacy)

## Zero-Knowledge Proofs (ZKP)

Ein [[Zero-Knowledge Proof]] (ZKP) ist ein kryptografisches Protokoll, das es einem [[Prover]] (Beweiser) ermöglicht, einem [[Verifier]] (Prüfer) zu beweisen, dass eine bestimmte Aussage über ein Geheimnis wahr ist, ohne das Geheimnis selbst preiszugeben. Ein ZKP basiert auf drei Säulen: Korrektheit (ein ehrlicher Prover kann den Beweis erbringen), Zuverlässigkeit (ein unehrlicher Prover kann den Beweis nicht fälschen) und Zero-Knowledge (der Verifier lernt absolut nichts ausser der Tatsache, dass die Aussage wahr ist). Praktische Anwendungen finden sich beispielsweise bei sicheren E-Voting-Systemen, bei denen bewiesen wird, dass eine Stimme gültig ist, ohne die Wahlentscheidung aufzudecken.

## Secure Multiparty Computation (MPC)

[[Secure Multiparty Computation]] (MPC) erlaubt es mehreren Parteien, gemeinsam eine Analyse über verteilte Datensätze durchzuführen, ohne ihre rohen Daten untereinander auszutauschen. Dies ist besonders nützlich, um «vertikal partitionierte» Datensätze zu verknüpfen, zum Beispiel wenn ein Krankenhaus und eine Versicherung Analysen zu Diabetes-Kosten durchführen möchten, ohne individuelle Patientendaten zu offenbaren. Ein Nachteil von MPC ist der signifikante Rechenaufwand, der für die Generierung von Zufallsgeheimnissen benötigt wird.

## Confidential Computing und Homomorphic Encryption

Um Daten während der Verarbeitung («Data in Use») zu schützen, kommen weitere Technologien zum Einsatz. [[Confidential Computing]] isoliert sensible Daten in einer hardwarebasierten sicheren Umgebung, dem [[Trusted Execution Environment]] (TEE), wodurch Daten und Code selbst vor dem Betriebssystem oder dem Cloud-Provider verborgen bleiben. Die [[Homomorphic Encryption]] (HE) geht noch einen Schritt weiter und ermöglicht es, mathematische Operationen direkt auf verschlüsselten Daten (Ciphertext) durchzuführen, ohne diese jemals zu entschlüsseln. Die vollhomomorphe Verschlüsselung (FHE) ist dabei die vielversprechendste Lösung, kämpft aktuell jedoch noch mit sehr hohen Rechenkosten und langsamer Geschwindigkeit.

# Big Data und praktische Anonymisierung

## Herausforderungen in Big Data

Die Technologie rund um [[Big Data]] sammelt und aggregiert beispiellose Mengen an Informationen, was einerseits Innovationen antreibt, andererseits massive Datenschutzrisiken durch Überwachung und Profilbildung schafft. Hierbei kollidiert das Konzept von Big Data fundamental mit klassischen Datenschutzprinzipien wie der Datenminimierung und der Zweckbindung, da oft Daten gesammelt werden, deren genauer künftiger Verwendungszweck noch gar nicht feststeht.

## Praktische Anonymisierung mit ARX

Bei der Anonymisierung von Datensätzen (z. B. einer Hotelgäste-Liste) werden die Datenfelder in identifizierende Attribute (werden entfernt), Quasi-Identifikatoren (werden generalisiert) und insensitive Attribute (bleiben erhalten) unterteilt. Mit Tools wie [[ARX]] lässt sich die K-Anonymität herstellen, indem beispielsweise das Alter zu einem 10-Jahres-Intervall generalisiert oder die Postleitzahl teilweise maskiert wird. Reicht die Generalisierung nicht aus, wendet das System das Prinzip der [[Suppression]] (Unterdrückung) an, bei dem problematische Werte komplett ausgeblendet werden, um die Identifikation zu verhindern.

## Synthetische Datengenerierung und Machine Learning

Eine Alternative zur herkömmlichen Anonymisierung sind [[Synthetische Daten]]. Hierbei handelt es sich um künstlich erzeugte Datensätze, welche die statistischen Verteilungen und Korrelationen der Originaldaten (z. B. medizinische Daten zu Diabetes) nachbilden, ohne reale Personen zu enthalten. In Laborversuchen kamen hierfür Methoden wie die [[GaussianCopula]] oder Machine-Learning-Modelle wie [[CTGAN]] zum Einsatz. Trotz hoher Datenqualität bergen synthetische Daten Risiken: Sie sind nicht automatisch anonym. Wenn ein Modell zu stark an den Originaldaten lernt (Overfitting), können seltene Merkmalskombinationen zur indirekten Wiedererkennung realer Personen führen (Membership-Inference-Angriffe).

# E-Pass, NFC und RFID-Authentifizierung

## Grundlagen des E-Pass-Standards

Der elektronische Reisepass (ePass) basiert weltweit auf dem Standard [[ICAO Doc 9303]]. Er kombiniert gedruckte Dokumente mit einem kontaktlosen RFID-Chip (Frequenz 13.56 MHz), der biometrische Daten und kryptografische Schlüssel speichert. Die Daten sind in der [[Logical Data Structure]] (LDS) organisiert, wobei beispielsweise in der Datengruppe 1 (DG1) die maschinenlesbare Zone (MRZ) und in DG2 das Gesichtsbild gespeichert sind.

## Sicherheitsmechanismen im ePass

Der Standard definiert mehrere Schutzschichten für die im Pass gespeicherten Daten:

- **Passive Authentication (PA):** Prüft die digitale Signatur des [[Document Security Object]] (SOD), um sicherzustellen, dass die Daten authentisch sind und nicht manipuliert wurden.
- **Active Authentication (AA):** Ein Challenge-Response-Verfahren, das beweist, dass der Chip das Original und keine Kopie ist (Anti-Cloning-Feature).
- **Basic Access Control (BAC):** Schützt vor unbemerktem Auslesen, indem ein symmetrischer Schlüssel aus den gedruckten MRZ-Daten (Passnummer, Geburtsdatum) abgeleitet wird.
- **Extended Access Control (EAC):** Ein zusätzlicher Schutz für hochsensible Daten wie Fingerabdrücke, bei dem sich das Lesegerät über ein Zertifikat zwingend authentifizieren muss.

## Schwachstellen und Klon-Angriffe

Obwohl die Passive Authentication (PA) Manipulationen verhindert, schützt sie allein nicht vor dem Klonen. Da alle Daten öffentlich lesbar sind, können sie auf einen leeren RFID-Rohling kopiert werden. Ein Laborexperiment mit einem [[Raspberry Pi]] und einem [[MIFARE Classic 1K]] Tag demonstrierte diese Schwachstelle anschaulich: Ein Zutrittssystem, das seine Entscheidung ausschliesslich auf der öffentlichen [[UID]] (Unique Identifier) des Tags basiert, ist unsicher. Mit Tools wie dem [[Flipper Zero]] kann die UID problemlos ausgelesen und emuliert werden, wodurch sich ein Angreifer unautorisierten Zugang verschaffen kann.

# Anonymisierungsnetzwerke und Zensurumgehung

## Tor-Netzwerk und Onion Routing

Das [[Tor-Netzwerk]] nutzt das Prinzip des Onion Routings, um die Privatsphäre zu schützen. Die Verbindung wird über drei zufällige Knoten (Entry Guard, Middle Node, Exit Node) geleitet, wobei die Daten mehrschichtig verschlüsselt sind, ähnlich den Schichten einer Zwiebel. Eine besondere Funktion sind die [[Hidden Services]] (versteckte Dienste), welche über eine .onion-Adresse erreichbar sind. Da der Datenverkehr hierbei das Tor-Netzwerk nie verlässt und kein Exit-Node benötigt wird, ist das Anonymitätslevel für Nutzer und Anbieter enorm hoch. Die .onion-Adressen werden kryptografisch aus einem Public-Key-Hash generiert, was ein klassisches DNS überflüssig macht.

## Garlic Routing und I2P

Im Gegensatz zum pfadbasierten Onion Routing ist das [[I2P]]-Netzwerk (Invisible Internet Project) ein geschlossenes Peer-to-Peer-Netzwerk, bei dem jeder Client gleichzeitig auch als Knoten (Relay) fungiert. Es nutzt das [[Garlic Routing]], welches mehrere Nachrichten bündelt und zusammen verschlüsselt versendet, wodurch Traffic-Analysen durch Angreifer deutlich erschwert werden.

## Zensurumgehung und Deep Packet Inspection (DPI)

Staatliche Zensoren und Firewalls verwenden oft [[Deep Packet Inspection]] (DPI), um den Verbindungsaufbau zu analysieren und Tor-Traffic zu blockieren. Um diese Blockaden zu umgehen, kommen sogenannte [[Pluggable Transports]] zum Einsatz. Werkzeuge wie obfs4 tarnen den Traffic als zufälliges Rauschen, während Snowflake den Verkehr in WebRTC-Daten versteckt. Ein weiteres zentrales Problem ist der [[Server Name Indicator]] (SNI) beim HTTPS-Verbindungsaufbau, der im Klartext übertragen wird. Technologien wie Encrypted Client Hello ([[ECH]]) verschlüsseln diesen Header, um Zensur- und Überwachungsmassnahmen wirksam zu unterbinden

# Vertiefung Tracking-Technologien und Web-Privatsphäre

## Weiterentwicklung der Cookies und Alternativen

Im Bereich des Web-Trackings bilden [[Cookies]] traditionell das Fundament der Zustandsspeicherung. Während First-Party-Cookies für die Grundfunktionen einer Website (wie Logins oder Warenkörbe) unerlässlich sind, dienen Third-Party-Cookies primär dem Cross-Site-Tracking. Da Browser wie Safari (via ITP) und Firefox Drittanbieter-Cookies standardmässig immer stärker blockieren, weicht der Markt auf neue Lösungen aus. Ein prominenter Ansatz ist Googles [[Topics API]]. Anstatt den Nutzer über eine eindeutige Identifikationsnummer (ID) über das Netz hinweg zu verfolgen, analysiert der Browser das lokale Surfverhalten, ordnet dem Nutzer bestimmte Interessensgebiete (Topics) zu und übermittelt lediglich diese aggregierten Themen an die Werbenetzwerke.

## Aggressives Tracking: CNAME-Cloaking

Eine der aggressivsten Methoden zur Umgehung von Tracking-Schutzmassnahmen ist das [[CNAME-Cloaking]]. Da Ad-Blocker externe Tracking-Domains normalerweise blockieren, richtet der Website-Betreiber einen DNS-Eintrag (CNAME) ein, der eine eigene Subdomain auf den externen Tracker umleitet. Für den Browser sieht die Verbindung somit wie ein erlaubter First-Party-Vorgang aus, wodurch der Schutz ausgehebelt wird. Studien zeigen, dass eine signifikante Anzahl von Websites diese Methode nutzt, um Sicherheitsmechanismen zu unterlaufen.

## Hardware-basiertes Tracking (Mobile und IoT)

Beim [[Mobile Tracking]] über Smartphone-Apps kommen neben der reinen Software-Analyse direkte Hardwarezugriffe hinzu. Sensoren wie GPS sowie die Erfassung von WLAN-Netzwerken, [[Bluetooth Beacons]] und Mobilfunkzellen ermöglichen eine hochpräzise Standortverfolgung. Um auf technischer Ebene die Privatsphäre bei ortsbasierten Diensten zu schützen, kann das Konzept der [[Cloaked Region]] angewandt werden: Dabei wird die genaue geografische Koordinate technisch verschleiert, um eine k-Anonymität innerhalb einer geografischen Zone herzustellen.

# Datenschutz in der Praxis: Richtlinien und Fallstudien

## Die Rolle der Privacy Policy (Datenschutzrichtlinie)

Eine [[Datenschutzrichtlinie]] ist ein Dokument, das aufzeigt, wie eine Organisation personenbezogene Daten sammelt, nutzt und weitergibt. In der Praxis sind diese Dokumente oft komplex und vage formuliert, was es Unternehmen ermöglicht, umfassend Daten zu sammeln, ohne gegen die eigenen Vorgaben zu verstossen. Ein typischer Abschnitt ist die «automatische Datensammlung», die auflistet, welche Gerätemerkmale (wie CPU-Auslastung oder Bildschirmauflösung) auch ohne explizite Eingabe des Nutzers im Hintergrund erfasst werden.

Fallstudien zu Datenschutzverletzungen

- **Temu:** Die App geriet in die Kritik, da sie weitreichende technische Daten der Endgeräte sammelt. Das Unternehmen beruft sich auf eine angebliche Anonymisierung der Daten; Experten betonen jedoch, dass durch die Kombination von Kauf- und Standortdaten eine Re-Identifizierung von Personen in der Regel problemlos möglich ist.
- **Google Inkognito-Modus:** Ein rechtlicher Vorfall zeigte, dass Google auch im sogenannten privaten Inkognito-Modus das Surfverhalten der Nutzer protokollierte. Dies offenbarte eine massive Diskrepanz zwischen der Erwartungshaltung der Nutzer an Privatsphäre und der tatsächlichen Tracking-Praxis der Industrie.
- **AOK und Big Data:** Im Gesundheitswesen stösst das Konzept von [[Big Data]] oft an die Grenzen des Datenschutzes. Bei der Auswertung von Patientendaten (z. B. durch die Krankenkasse AOK) stehen Prinzipien wie die strenge Zweckbindung und die Notwendigkeit einer Einwilligung im direkten Konflikt mit dem Wunsch, Daten auf Vorrat für noch unbekannte Forschungszwecke zu aggregieren.

# Smart Devices und das «Belauschen»-Mythen

## Sprachassistenten im Alltag

Intelligente Sprachassistenten wie der [[Amazon Echo]] (Alexa) sind weit verbreitet und verarbeiten Spracheingaben in der Cloud. Oft existiert bei Nutzern die Befürchtung, diese Geräte würden permanent den gesamten Alltag belauschen. Die Wissenschaft entkräftet diesen Mythos primär durch das Datenvolumen-Argument: Ein kontinuierliches Übertragen von Audio-Streams an Cloud-Server würde gigantische Datenmengen erzeugen, die bei einer Netzwerkanalyse sofort auffallen würden. Die Geräte hören lokal lediglich auf ihr spezifisches Aktivierungswort (Wake Word) und übertragen erst anschliessend Daten.

# Schutzziele der Informationssicherheit und des Datenschutzes

## Die klassische CIA-Triade

In der IT-Sicherheit bilden drei fundamentale Ziele die Basis, oft als [[CIA-Triade]] bezeichnet:

- **Vertraulichkeit (Confidentiality):** Informationen dürfen nicht in falsche Hände geraten.
- **Integrität (Integrity):** Daten und Systeme dürfen nicht unbemerkt und unbefugt verändert werden.
- **Verfügbarkeit (Availability):** Die benötigten Informationen und Dienste müssen verlässlich abrufbar sein.

## Die neuen Datenschutz-Schutzziele

Während die CIA-Triade primär die Daten und Systeme schützt, stehen beim Datenschutz die betroffenen Personen im Zentrum. Hierfür wurden ergänzende Schutzziele etabliert:

- **Transparenz:** Die Datenverarbeitung muss stets nachvollziehbar und überprüfbar sein.
- **Unverkettbarkeit:** Personenbezogene Daten dürfen nicht zweckentfremdet oder ohne gesetzliche Grundlage aus verschiedenen Quellen zu neuen Profilen verknüpft werden.
- **Intervenierbarkeit:** Betroffene müssen technisch in der Lage sein, ihre Rechte (z. B. auf Löschung, Auskunft oder Sperrung) jederzeit wirksam auszuüben.

## Zielkonflikte (Trade-offs)

In der Praxis stehen die Ziele der IT-Sicherheit oft im direkten Widerspruch zum Datenschutz. Ein klassischer [[Zielkonflikt]] entsteht beispielsweise bei der Verfügbarkeit: Um Daten vor Verlust zu schützen, werden sie häufig weltweit auf redundanten Servern repliziert. Diese globale Verteilung steht jedoch im Konflikt mit dem Datenschutz, da die Daten dadurch in Länder abfliessen können, in denen ein deutlich schwächeres oder gar kein angemessenes Datenschutzniveau herrscht.

# Internationale Rechtliche Rahmenbedingungen

## Branchen- und länderspezifische Gesetze

Ergänzend zu den zuvor behandelten Gesetzen (EU-DSGVO und Schweizer revDSG) spielen international zwei weitere Rahmenwerke eine massgebliche Rolle:

- **[[HIPAA]] (Health Insurance Portability and Accountability Act):** Ein strenges US-amerikanisches Gesetz aus dem Jahr 1996, das spezifische Sicherheits- und Vertraulichkeitsstandards für den Umgang mit gesundheitsbezogenen Daten (PHI) vorschreibt.
- **[[CCPA]] (California Consumer Privacy Act):** Ein wichtiges US-Verbraucherschutzgesetz, das den Einwohnern Kaliforniens weitreichende Rechte bezüglich der Erhebung und des Verkaufs ihrer persönlichen Daten einräumt.