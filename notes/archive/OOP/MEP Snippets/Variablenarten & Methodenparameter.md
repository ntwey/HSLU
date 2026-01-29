```Java
/**
 * Demonstriert die verschiedenen Arten von Variablen.
 */
public class VariablenDemo {
    // Instanzvariable: Gehört zum Objekt [cite: 33]
    private int instanzZahl;

    // Klassenvariable: Gehört zur Klasse (static) [cite: 33, 68]
    private static String klassenName = "OOP_Demo";

    /**
     * @param formalerParam Dies ist ein formaler Parameter [cite: 34]
     */
    public void berechne(int formalerParam) {
        // Lokale Variable: Nur innerhalb der Methode sichtbar [cite: 33]
        int lokaleZahl = 10;
        this.instanzZahl = formalerParam + lokaleZahl;
    }

    public static void main(String[] args) {
        VariablenDemo demo = new VariablenDemo();
        // Die Zahl '5' ist hier der aktuelle Parameter [cite: 34]
        demo.berechne(5); 
    }
}
```