package com.example.demomultipledb;
import com.example.demomultipledb.persondb.Person;
import com.example.demomultipledb.persondb.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    private static Logger logger = LoggerFactory.getLogger(PersonService.class);

    public void createPerson(CreatePersonRequest createPersonRequest) {

        Person person = createPersonRequest.to();

        // Explaining the use case of transient
//        if(person.getCountryCode() == "+91"){
//
//        }else{
//
//        }

        Person personFromDB = personRepository.save(person);

        logger.info("Person saved - {}", personFromDB);
    }

    public Person getPerson(int id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() ->
                        new PersonNotFoundException
                                ("Person with Id " + id + " not present!!"));
    }

    /*
    Even though we would be passing age from the controller, but the underlying repository
    layer will search primary key column
    */
//    public Person getPerson2(int age){
//        return personRepository.findById(age).get();
//    }

    public List<Person> getPeople() {

        return personRepository.findAll();
    }
}

