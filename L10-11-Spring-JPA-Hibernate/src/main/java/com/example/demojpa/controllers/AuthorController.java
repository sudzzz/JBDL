package com.example.demojpa.controllers;

import com.example.demojpa.models.Author;
import com.example.demojpa.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor(@RequestParam("age") int age, @RequestParam("name") String name){
        Author author = Author.builder()
                        .age(age)
                        .name(name)
                        .build();
        return new ResponseEntity<>(authorService.createAuthor(author), HttpStatus.CREATED);
    }
}
