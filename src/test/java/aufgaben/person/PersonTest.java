package aufgaben.person;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void personData_newPerson_valuesCorrectlySet() {
        Person person = new Person(4, "Wyss", "Alexander");

        assertEquals(4, person.getId());
        assertEquals("Wyss", person.getName());
        assertEquals("Alexander", person.getFirstname());
    }

    @Test
    void verifyEquals() {
        EqualsVerifier.forClass(Person.class).verify();
    }

    @Test
    void twoEqualPersons_equals_returnTrue() {
        Person personA = new Person(1, "Riva", "Geralt");
        Person personB = new Person(1, "Riva", "Geralt");

        boolean result = personA.equals(personB);

        assertTrue(result);
    }

    @Test
    void twoPersonsWithDifferentId_equals_returnFalse() {
        Person personA = new Person(1, "Riva", "Geralt");
        Person personB = new Person(2, "Riva", "Geralt");

        boolean result = personA.equals(personB);

        assertFalse(result);
    }

    @Test
    void twoEqualPersons_hashCode_hashCodeAreEqual() {
        Person personA = new Person(1, "Riva", "Geralt");
        Person personB = new Person(1, "Riva", "Geralt");

        assertEquals(personA.hashCode(), personB.hashCode());
    }

    @Test
    void twoPersonsWithDifferentId_hashCode_hashCodesAreNotEqual() {
        Person personA = new Person(1, "Riva", "Geralt");
        Person personB = new Person(2, "Riva", "Geralt");

        assertNotEquals(personA.hashCode(), personB.hashCode());
    }

    @Test
    void personsWithSameId_compareTo_return0() {
        Person personA = new Person(1, "Riva", "Geralt");
        Person personB = new Person(1, "Riva", "Geralt");

        assertEquals(0, personA.compareTo(personB));
    }

    @Test
    void personAWithGreaterId_compareTo_returnPlusOne() {
        Person personA = new Person(3, "Riva", "Geralt");
        Person personB = new Person(1, "Riva", "Geralt");

        assertEquals(1, personA.compareTo(personB));
    }

    @Test
    void personBWithGreaterId_compareTo_returnMinusOne() {
        Person personA = new Person(-2, "Riva", "Geralt");
        Person personB = new Person(1, "Riva", "Geralt");

        assertEquals(-1, personA.compareTo(personB));
    }

    @Test
    void person_getFullName_correctName() {
        Person person = new Person(0, "Schneider", "Ferdinand");

        assertEquals("Schneider Ferdinand", person.getFullName());
    }
}