package org.example.service;

import org.example.model.Person;
import org.example.request.CreatePersonRequest;
import org.example.utils.BadPersonRequestException;

import java.util.List;

public interface PersonService {
    Person createPerson(CreatePersonRequest createPersonRequest);
    Person getPersonById(int id);
    List<Person> getAllPersons();
    Person deletePersonById(int id) throws BadPersonRequestException;
}
