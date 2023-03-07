package com.example.demojpa.services;

import com.example.demojpa.models.Author;
import com.example.demojpa.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public Author createAuthor(Author author){
        return authorRepository.save(author);
    }
}
