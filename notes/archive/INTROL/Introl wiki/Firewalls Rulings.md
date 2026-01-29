# Firewall-Regeln (IPv6) - Analyse und Lösung

## Aufgabenstellung verstehen

Bei dieser Aufgabe geht es um eine **Paket-Filter-Firewall** (Stateless Packet Filtering). Das bedeutet, die Firewall entscheidet für jedes einzelne Datenpaket isoliert, ob es durchgelassen (`accept`), abgewiesen (`reject`) oder stillschweigend verworfen (`deny`) wird.

Da es sich (implizit durch die Art der Fragen) um eine **zustandslose** (stateless) Firewall handelt, müssen **Hin- und Rückwege** separat definiert werden. Wenn ein interner Computer eine Webseite aufruft (ausgehend), muss der Antwortverkehr des Webservers (eingehend) explizit erlaubt werden.

### Die Spalten erklärt
* **Richtung:** `in` (Traffic kommt aus dem Internet ins Firmennetz) oder `out` (Traffic geht vom Firmennetz ins Internet).
* **Quelle/Ziel:** Die IPv6-Adressen. `/56` deutet auf das ganze Subnetz der Firma hin, `/128` auf einen einzelnen Host (Server).
* **Ports:** `80` ist der Standard-Port für unverschlüsseltes Web (HTTP). `any` bedeutet jeder beliebige Port.

---

## Lösung und Analyse der fehlenden Werte

Hier ist die vervollständigte Tabelle. Die **fettgedruckten** Werte sind die Lösungen für die leeren Felder.

| Richtung | Quelle                    | Ziel                    | Quellport | Zielport  | Regel  | Erklärung                                                                                                                         |
| :------- | :------------------------ | :---------------------- | :-------- | :-------- | :----- | :-------------------------------------------------------------------------------------------------------------------------------- |
| out      | `2001:DB8:100::/56`       | any                     | any       | 80        | accept | **Bestehend:** Internes Netz darf Webseiten im Internet aufrufen.                                                                 |
| in       | any                       | **`2001:DB8:100::/56`** | 80        | **`any`** | accept | **Antwortverkehr:** Das Internet (Port 80) antwortet dem internen Netz auf einem hohen Zufallsport (Ephemeral Port).              |
| in       | any                       | `2001:DB8:100::5/128`   | **`any`** | 80        | accept | **Server-Zugriff:** Externer Zugriff auf den spezifischen internen Webserver (::5). Quellports von Clients sind zufällig (`any`). |
| out      | **`2001:DB8:100::5/128`** | any                     | **`80`**  | any       | accept | **Server-Antwort:** Der interne Webserver antwortet dem externen Client. Die Antwort kommt von Port 80.                           |
| out      | all                       | all                     | any       | any       | reject | **Bestehend:** Alles andere ausgehende wird abgelehnt (mit Fehlermeldung).                                                        |
| in       | all                       | all                     | any       | **`any`** | deny   | **Sicherheit:** Alles andere eingehende wird kommentarlos verworfen.                                                              |

---

## Detail-Erklärung der Lücken

### Zeile 2 (Antwort auf Zeile 1)
Hier kommt die Antwort eines externen Webservers zurück ins Firmennetz.
* **Ziel:** Muss das interne Netzwerk sein, also `2001:DB8:100::/56`.
* **Zielport:** Wenn ein Client eine Webseite aufruft, öffnet er lokal einen zufälligen High-Port (z.B. 54321). Da wir diesen nicht kennen, müssen wir hier `any` (oder einen Bereich >1023) erlauben.

### Zeile 3 (Zugriff auf Webserver)
Hier greift jemand von außen auf den Webserver der Firma zu.
* **Quellport:** Der Client im Internet nutzt einen zufälligen Port, um die Anfrage zu senden. Daher muss hier `any` stehen.

### Zeile 4 (Antwort von Zeile 3)
Der interne Webserver (::5) schickt die Webseite an den Besucher zurück.
* **Quelle:** Das Paket kommt vom Server selbst, also `2001:DB8:100::5/128` (oder nur die IP).
* **Quellport:** Ein Webserver sendet Daten immer von seinem Dienst-Port, also Port `80`.

### Zeile 6 (Cleanup Rule)
Die "Alles andere verbieten"-Regel am Ende.
* **Zielport:** Um wirklich *alles* zu blockieren, was nicht vorher erlaubt wurde, muss hier `any` stehen.

> [!NOTE] Wichtiger Hinweis zu IPv6
> `2001:DB8:100::/56` beschreibt das Netzwerksegment.
> `2001:DB8:100::5/128` beschreibt genau einen Computer (den Server).