# Lambdas & Stream API (Java) – kompakte Obsidian-Zusammenfassung

## Lambdas (funktionale Programmierung)

### Motivation
- Weniger Boilerplate als anonyme innere Klassen (z.B. Listener)
- Funktionen „in-place“ definieren
- Java seit Version 8: hybrid (OOP + FP)

### Was ist ein Lambda?
- Namenlose Funktion
- Implementiert die **eine** abstrakte Methode eines Functional Interface

### Syntax
- Grundform: `(params) -> { body }`
- Kurzformen:
  - Parametertypen meist weglassbar: `(x, y) -> x + y`
  - Ein Parameter: `x -> x * 2`
  - Kein Parameter: `() -> doSomething()`
  - Ein Ausdruck: ohne `{}`, `return`, `;`

### Methodenreferenzen
- Wenn das Lambda nur einen Methodenaufruf macht
- Form: `objekt::methode` oder `Klasse::methode`
- Bedingung: Signatur passt zum Functional Interface

### Functional Interfaces
- Interface mit genau **einer** abstrakten Methode (SAM)
- Lambdas werden vom Compiler zu Objekten, die dieses Interface implementieren
- Häufige Typen aus `java.util.function`:
  - `Predicate<T>`: `T -> boolean`
  - `Function<T,R>`: `T -> R`
  - `Consumer<T>`: `T -> void`
  - `Supplier<T>`: `() -> T`
  - `UnaryOperator<T>`: `T -> T`
  - `BinaryOperator<T>`: `(T,T) -> T`

### Empfehlung
- Lambdas kurz halten (lange Bodies werden unleserlich)
- Ziel ist Ausdrucksstärke, nicht minimale Zeichenanzahl

---

## Stream API (Datenstrom-Verarbeitung)

### Was ist ein Stream?
- Kontinuierlicher Fluss von Elementen
- Kein wahlfreier Zugriff wie bei Listen
- Elemente können schrittweise verarbeitet werden
- Gut für grosse Datenmengen, potenziell parallelisierbar

### Abgrenzung: I/O-Streams vs Stream API
- I/O-Streams: `java.io` / `java.nio` (Bytes/Zeichen lesen/schreiben)
- Stream API: `java.util.stream` (Objekt-/Werteströme verarbeiten)

### Pipeline-Konzept
- Quelle (Supplier) -> 0..n Zwischenoperationen -> 0..1 Endoperation
- Intermediate Operations liefern wieder einen Stream
- Terminal Operation konsumiert den Stream (danach nicht mehr nutzbar)

### Quellen (Supplier)
- `collection.stream()`
- `Stream.of(...)`
- Primitive Streams: `IntStream.range(...)`, `IntStream.of(...)`

### Intermediate Operations (Auswahl)
- `filter(predicate)` (reduziert Datenmenge)
- `map(mapper)` (transformiert Werte/Typen)
- `sorted()` (sortiert)

### Terminal Operations (Auswahl)
- `forEach(action)`
- `collect(...)`
- `min()`, `max()`, `findFirst()`

### Imperativ vs deklarativ
- Imperativ: Schleifen, temporäre Listen, mehr Boilerplate
- Stream: „Was“ statt „Wie“, Pipeline liest sich wie eine Spezifikation

---

## AutoBoxing / Unboxing (wichtig bei Streams)

### Problem
- Generics arbeiten mit Objekten: `Integer` statt `int`
- AutoBoxing/Unboxing kann unauffällig viele Objekte erzeugen
- Overhead: Zeit, Speicher, Garbage Collection

### Lösung
- Primitive Streams nutzen:
  - `IntStream`, `LongStream`, `DoubleStream`

---

## Hinweise & Best Practices
- Früh filtern (Datenmenge klein halten)
- Reihenfolge der Operationen beeinflusst Performance stark
- Nicht jede „super kompakte“ Stream-Lösung ist effizient
- Bei Zweifel Pipeline in mehrere Schritte aufteilen (Lesbarkeit)

---

## Kernaussagen
- Lambdas = kompakte Funktionen über Functional Interfaces
- Methodenreferenzen = noch kürzer, wenn nur ein Call nötig ist
- Stream API = deklarative Datenverarbeitung mit Pipelines
- Achtung auf AutoBoxing; primitive Streams bevorzugen

Quelle: Vorlesungsunterlagen Lambdas und Stream API 