package ch.hslu.oop.sw07;

import ch.hslu.oop.sw02.Temperatur;
import nl.jqno.equalsverifier.*;
import org.junit.jupiter.api.Test;


public final class PersonTest {
    @Test
    public void testConstructor() {
        final Person person = new Person(756000000L, "FirstName", "LastName");
        assert(person.getId() == 756000000L);
        assert(person.getFirstName().equals("FirstName"));
        assert(person.getLastName().equals("LastName"));
    }

    @Test
    public void testEqualFunctionWithEqualsVerifier() {
        EqualsVerifier.simple().forClass(Person.class).verify();
    }

    @Test
    public void testGivenPersonIsEqualToPerson() {
        final Person person = new Person(756000000L, "FirstName", "LastName");
        final Person person2 = new Person(756000000L, "FirstName", "LastName");
        assert(person.equals(person2));
        assert(person.hashCode() == person2.hashCode());
    }

    @Test
    public void testGivenPersonIsNotEqualToPerson() {
        final Person person = new Person(756000000L, "FirstName", "LastName");
        final Person person2 = new Person(756000001L, "OtherFirstName", "OtherLastName");
        assert(!person.equals(person2));
        assert(person.hashCode() != person2.hashCode());
    }

    @Test
    public void testGivenPersonIsNotEqualToNull() {
        final Person person = new Person(756000000L, "FirstName", "LastName");
        assert(!person.equals(null));
    }

    @Test
    public void testGivenPersonIsNotEqualToOtherClass() {
        final Person person = new Person(756000000L, "FirstName", "LastName");
        final Temperatur temperatur = new Temperatur(75600.0D);
        assert(!person.equals(temperatur));
    }

    @Test
    public void testGivenPersonCompareToPersonIs0() {
        final Person person = new Person(756000000L, "FirstName", "LastName");
        final Person person2 = new Person(756000000L, "FirstName", "LastName");
        assert(person.compareTo(person2) == 0);
    }

    @Test
    public void testGivenPersonCompareToPersonIsNot0() {
        final Person person = new Person(756000000L, "FirstName", "LastName");
        final Person person2 = new Person(756000001L, "FirstName", "LastName");
        assert(person.compareTo(person2) != 0);
    }
}
