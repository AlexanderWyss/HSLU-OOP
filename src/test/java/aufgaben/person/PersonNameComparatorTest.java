package aufgaben.person;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonNameComparatorTest {
    @Test
    void personWithSameNameAndFirstname_compare_returnZero() {
        Person a = new Person(0, "Name", "Firstname");
        Person b = new Person(0, "Name", "Firstname");

        assertEquals(0, getComparator().compare(a, b));
    }

    @Test
    void personAWithNameAlphabeticallyFirst_compare_returnMinus1() {
        Person a = new Person(0, "NameA", "FirstnameB");
        Person b = new Person(0, "NameB", "FirstnameA");

        assertEquals(-1, getComparator().compare(a, b));
    }

    @Test
    void personBWithNameAlphabeticallyFirstAndSameFirstname_compare_returnPlus1() {
        Person a = new Person(0, "NameB", "FirstnameA");
        Person b = new Person(0, "NameA", "FirstnameB");

        assertEquals(1, getComparator().compare(a, b));
    }

    @Test
    void withSameNameAndPersonAWithFirstnameAlphabeticallyFirst_compare_returnMinus1() {
        Person a = new Person(0, "Name", "FirstnameA");
        Person b = new Person(0, "Name", "FirstnameB");

        assertEquals(-1, getComparator().compare(a, b));
    }

    @Test
    void withSameNameAndPersonBWithFirstnameAlphabeticallyFirst_compare_returnMinus1() {
        Person a = new Person(0, "Name", "FirstnameB");
        Person b = new Person(0, "Name", "FirstnameA");

        assertEquals(1, getComparator().compare(a, b));
    }

    @Test
    void multiplePersonInList_sort_correctlyOrdered() {
        Person a = new Person(0, "NameA", "FirstnameA");
        Person b = new Person(0, "NameA", "FirstnameB");
        Person c = new Person(0, "NameB", "FirstnameA");
        Person d = new Person(0, "NameB", "FirstnameB");
        Person e = new Person(0, "NameC", "FirstnameA");

        List<Person> sortedPersons = Stream.of(d, a, c, b, e).sorted(getComparator()).collect(Collectors.toList());

        assertThat(sortedPersons, contains(a, b, c, d, e));
    }

    private Comparator<Person> getComparator() {
        return new PersonNameComparator();
    }
}