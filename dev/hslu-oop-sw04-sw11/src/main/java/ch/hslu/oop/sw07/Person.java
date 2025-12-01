package ch.hslu.oop.sw07;

import java.util.Objects;

/**
 * Einfache Person mit ID, Vor- und Nachnamen.
 */
public final class Person implements Comparable<Person> {

    private final long id;
    private String firstName;
    private String lastName;

    public Person(final long id, final String firstName, final String lastName) {
        this.id = id;
        this.firstName = Objects.requireNonNull(firstName, "firstName");
        this.lastName = Objects.requireNonNull(lastName, "lastName");
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = Objects.requireNonNull(firstName, "firstName");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = Objects.requireNonNull(lastName, "lastName");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        final Person person = (Person) o;
        return this.id == person.id
                && Objects.equals(this.firstName, person.firstName)
                && Objects.equals(this.lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public int compareTo(final Person other) {
        if (other == null) {
            throw new NullPointerException("other");
        }
        return Long.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.format("Person{id=%d, firstName='%s', lastName='%s'}",
                id, firstName, lastName);
    }
}
