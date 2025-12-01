# HSLU OOP – Lösungen SW04 & SW05

Dieses Maven-Projekt enthält Beispiel-Lösungen für die Übungen zu **Schnittstellen & Datenkapselung (SW04)** sowie **Vererbung (SW05)**.  
Java-Version: 21.

## Struktur
- `ch.hslu.oop.sw04` – Switchable/CountingSwitchable, Motor/Headlight/Vehicle, Named, Point/Line (mit sauberer Datenkapselung) und LinePrimitive (Variante mit primitiven Feldern).
- `ch.hslu.oop.sw05` – Abstrakte Basisklasse `Shape` mit Spezialisierungen `Circle`, `Rectangle`, `Square` (Variante: Square erbt von Rectangle).
- `ch.hslu.oop.Main` – Kleines Demo-Programm, das die Funktionalität zeigt.

## Build & Run
```bash
mvn -q -e -DskipTests package
java -jar target/hslu-oop-exercises-1.0.0.jar
```

## Hinweise
- Ausführliche JavaDoc ist enthalten (siehe Quellcode).
- Die Klasse `Square` illustriert eine mögliche Design-Variante (Erben von `Rectangle`).
- Die Klasse `Line` vermeidet Repräsentationsexposition durch **defensive Kopien**.
