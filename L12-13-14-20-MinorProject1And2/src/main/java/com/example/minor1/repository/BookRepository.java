package com.example.minor1.repository;

import com.example.minor1.model.Book;
import com.example.minor1.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer>{
    //select * from book where name = ?1
    List<Book> findByName(String name);
    //select * from book,author where author.name = ?1 and author.id = book.author_id;
    List<Book> findByAuthor_Name(String author_name);
    List<Book> findByGenre(Genre genre);
}
