```Java
import java.util.ArrayList;

public class GenericsDemo {
    public void demo() {
        // Nutzung einer generischen Klasse (ArrayList<T>) [cite: 62]
        ArrayList<Integer> zahlen = new ArrayList<>();

        // AutoBoxing: Primitiver 'int' wird automatisch in 'Integer'-Objekt umgewandelt [cite: 97]
        int primitiv = 42;
        zahlen.add(primitiv); 

        // Unboxing: Objekt wird automatisch wieder zum Primitiv
        int wert = zahlen.get(0);
    }
}
```