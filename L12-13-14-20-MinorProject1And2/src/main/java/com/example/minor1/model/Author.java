package com.example.minor1.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(name = "land")
    private String country;
    @OneToMany(mappedBy = "author",fetch = FetchType.EAGER) //Whenever we do select * from author, eager will also do select * from book where author_id = id
    private List<Book> bookList;
    @CreationTimestamp
    private Date addedOn;
}
