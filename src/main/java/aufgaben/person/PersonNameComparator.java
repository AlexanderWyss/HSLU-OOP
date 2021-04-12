package aufgaben.person;

import java.util.Comparator;

public class PersonNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
        return a.getFullName().compareTo(b.getFullName());
    }
}
