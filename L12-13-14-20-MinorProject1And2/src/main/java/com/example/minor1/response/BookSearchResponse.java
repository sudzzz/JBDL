package com.example.minor1.response;

import com.example.minor1.model.Author;
import com.example.minor1.model.Genre;
import com.example.minor1.model.Student;
import com.example.minor1.model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchResponse {
    private int id;
    private String name;
    @JsonIgnoreProperties({"bookList","addedOn"}) //Ignores the properties and don't fall in loop. To ignore whole object use @JsonIgnore
    private Author author;
    private int cost;
    private Genre genre;
    private Student student;
    private List<Transaction> transactionList;
    private Date createdOn;
    private Date updatedOn;
}
