package Lecture1;

import java.util.HashMap;
import java.util.Objects;

public class Person {

    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return this.age == person.age &&Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    public static void main(String[] args) {

        Person person1 = new Person(10, "John");
        Person person2 = new Person(20, "Selena");
        Person person3 = new Person(10, "John");

        System.out.println(person1.equals(person3));

        System.out.println(person1.hashCode() + " " + person3.hashCode());

        HashMap<Person, Boolean> personMap = new HashMap<>();
        personMap.put(person1, true);
        personMap.put(person3, true);

        System.out.println(personMap);


    }
}
