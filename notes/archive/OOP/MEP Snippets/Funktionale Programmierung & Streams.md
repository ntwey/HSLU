
```Java
import java.util.List;

public class StreamDemo {
    public void filterNamen(List<String> namen) {
        namen.stream() // Stream erzeugen
             .filter(n -> n.startsWith("A")) // Intermediate Op: Lambda [cite: 88, 95]
             .map(String::toUpperCase)       // Intermediate Op
             .forEach(System.out::println);  // Terminal Op [cite: 95]
    }
}

```