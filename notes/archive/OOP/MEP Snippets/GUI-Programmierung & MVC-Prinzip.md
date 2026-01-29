```Java
/**
 * Ein einfaches Beispiel für ein GUI-Element (View) mit Event-Handling.
 * Demonstriert anonyme innere Klassen zur Event-Behandlung[cite: 80, 81].
 */
public class EinfacheGUI {
    // In der Praxis: Trennung in Model (Daten), View (Layout), Control (Logik) [cite: 100]

    public void init() {
        // Pseudo-Code für Button-Klick-Handling
        Button btn = new Button("Klick mich");

        // Anonyme innere Klasse als Event-Handler [cite: 81]
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button wurde gedrückt!");
            }
        });
        
        // Modernere Alternative: Lambda [cite: 146]
        btn.addActionListener(e -> System.out.println("Lambda-Klick!"));
    }
}
```