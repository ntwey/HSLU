```Java 
public class LogikDemo {
    public void checkZahl(int n) {
        // Selektion: Switch-Expression (Java 14+) [cite: 43]
        String resultat = switch (n) {
            case 1, 2 -> "Klein";
            default -> "Gross";
        };

        // Schleifen: for-Schleife [cite: 45]
        for (int i = 0; i < n; i++) {
            System.out.println("Durchlauf: " + i);
        }
        
        // Typumwandlung (Casting) [cite: 39]
        double d = 10.5;
        int iCast = (int) d; 
    }
}
```