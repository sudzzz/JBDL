package org.example.repository;

import org.example.model.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonRepository {

    Person createPerson(Person person);

    Person getPersonById(int id);

    List<Person> getAllPersons();

    boolean deletePersonById(int id);
}
