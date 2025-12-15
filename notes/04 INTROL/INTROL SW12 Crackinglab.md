# Labor 7 – Cracking Lab

## Zielsetzung
- Sichere Passwörter verstehen
- Hashing & Salting praktisch nachvollziehen
- Passwortangriffe durchführen (Hashcat, Rainbow Tables)
- Angriffspfade erkennen

## 1. Gute Passwörter
- Länge > Komplexität
- Passphrasen empfohlen
- Kein Wiederverwenden über Dienste

## 2. Hashing
- Passwörter werden nicht im Klartext gespeichert
- Hashfunktionen:
  - SHA-256
  - NTLM
- Eigenschaften:
  - deterministisch
  - irreversibel
  - kollisionsarm

## 3. Salting
- Zufallswert wird zum Passwort hinzugefügt
- verhindert Rainbow-Table-Angriffe
- Salt muss gespeichert werden

## 4. Angriffsmöglichkeiten

### Rainbow Tables
- Vorgefertigte Tabellen für Hash → Passwort
- Nur sinnvoll bei ungesalzten Hashes

### Brute Force
- systematisches Durchprobieren
- exponentielle Dauer je nach Zeichensatz/Länge

### Mask Attack
- Heuristischer Angriff
- z. B. Struktur: `P?????1`

### Offline Cracking
- Hashes werden extrahiert
- Angriff kann ohne Rate-Limits stattfinden

### Hashcat
- GPU-basiertes Passwort-Cracking
- Unterstützt:
  - Wordlists
  - Mask Attacks
  - Rainbow
  - Bruteforce

## 5. Zusatz: Remote Access (VNC)
- Nach Passwortkompromittierung möglich
- Auflisten von Dateien, RDP/VNC Verbindung

## Erkenntnisse
- Hashing schützt nur begrenzt
- Passwortqualität ist entscheidend
- Angreifer arbeiten offline, schnell und ohne Limitierung
