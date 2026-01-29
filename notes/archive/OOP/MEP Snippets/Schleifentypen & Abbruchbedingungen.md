```java
public class SchleifenDemo {
    public void demo() {
        // for-Schleife: Wenn die Anzahl der Durchläufe bekannt ist [cite: 45]
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }

        // while-Schleife: Prüfung VOR dem Durchlauf (0..n Mal) [cite: 45]
        boolean bedingung = true;
        while (bedingung) {
            // Sicherstellen einer sicheren Abbruchbedingung [cite: 47]
            bedingung = false; 
        }

        // do-while: Prüfung NACH dem Durchlauf (mind. 1 Mal) [cite: 45]
        do {
            System.out.println("Läuft mindestens einmal");
        } while (false);
    }
}
```