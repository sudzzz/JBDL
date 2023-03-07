package org.example.controller;

import org.example.model.Person;
import org.example.request.CreatePersonRequest;
import org.example.service.PersonService;
import org.example.utils.BadPersonRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(@RequestBody @Valid CreatePersonRequest personRequest){
        //Validations and Exceptions we have outsourced. We are only invoking Service layer function.
        logger.info("person -- {}",personRequest);
        return new ResponseEntity<>(personService.createPerson(personRequest), HttpStatus.CREATED);
    }

    @GetMapping("/person")
    public ResponseEntity<Person> getPersonById(@RequestParam("id") int id){
        logger.info("id -- {}",id);
        return new ResponseEntity<>(personService.getPersonById(id),HttpStatus.OK);
    }

    @GetMapping("/person/all")
    public ResponseEntity<List<Person>> getAllPersons(){
        return new ResponseEntity<>(personService.getAllPersons(),HttpStatus.OK);
    }

    @DeleteMapping("person/{id}")
    public ResponseEntity<Person> deletePersonById(@PathVariable("id") int id) throws BadPersonRequestException {
        return new ResponseEntity<>(personService.deletePersonById(id),HttpStatus.OK);
    }
}
