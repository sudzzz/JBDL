package org.example.service.impl;

import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.example.request.CreatePersonRequest;
import org.example.service.PersonService;
import org.example.utils.BadPersonRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person createPerson(CreatePersonRequest createPersonRequest) {
        Person person = createPersonRequest.to();
        if(Objects.isNull(person.getAge())){
            person.setAge(calculateAgeFromDOB(person.getDob()));
        }
        return personRepository.createPerson(person);
    }

    @Override
    public Person getPersonById(int id) {
        return personRepository.getPersonById(id);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    @Override
    public Person deletePersonById(int id) throws BadPersonRequestException {
        Person person = getPersonById(id);
        boolean isDeleted = personRepository.deletePersonById(id);
        if(isDeleted)
            return person;
        throw new BadPersonRequestException("PersonId " + id + " is not present in db");
    }

    private Integer calculateAgeFromDOB(String dob){
        if(dob == null)
            return null;
        LocalDate dobDate = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now();

        return Period.between(dobDate,currentDate).getYears();

    }
}
