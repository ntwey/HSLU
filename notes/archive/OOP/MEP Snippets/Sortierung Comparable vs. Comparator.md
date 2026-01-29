```java
/**
 * Implementiert Comparable für die natürliche Ordnung (z.B. nach ID)[cite: 66].
 */
public class Student implements Comparable<Student> {
    private int id;
    private String name;

    public Student(int id, String name) { this.id = id; this.name = name; }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }
}

/**
 * Externer Comparator für eine alternative Ordnung (z.B. nach Name)[cite: 67].
 */
class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
}
```