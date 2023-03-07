package com.example.demomultipledb.authordb;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    private int age;

    private String country;
}

// flyway, liquibase

// alter table add column
// alter table add constraint ON name (unique)