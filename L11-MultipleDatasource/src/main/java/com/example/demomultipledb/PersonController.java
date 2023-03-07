package com.example.demomultipledb;

import com.example.demomultipledb.persondb.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);


    @PostMapping("/person")
    public void createPerson(@Valid @RequestBody CreatePersonRequest createPersonRequest){

        // Validations and Exception handling we have outsourced
        // Invoking service class' function
        personService.createPerson(createPersonRequest);
    }

    @GetMapping("/person")
    public Person getPersonById(@RequestParam("id") int id) throws PersonNotFoundException {
        return personService.getPerson(id);
    }

    @GetMapping("/person/all")
    public List<Person> getPeople(){
        return personService.getPeople();
    }
}
