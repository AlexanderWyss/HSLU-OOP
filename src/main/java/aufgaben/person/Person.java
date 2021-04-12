package aufgaben.person;

import java.util.Objects;

public class Person implements Comparable<Person> {
    private final long id;
    private final String name;
    private final String firstname;

    public Person(long id, String name, String firstname) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getFullName() {
        return name + " " + firstname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        if (id != person.id) {
            return false;
        }
        return Objects.equals(firstname, person.firstname) && Objects.equals(name, person.name);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, name, firstname);
    }

    @Override
    public int compareTo(Person person) {
        return Long.compare(id, person.id);
    }
}
