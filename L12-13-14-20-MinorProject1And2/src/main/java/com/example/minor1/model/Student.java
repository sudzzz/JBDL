package com.example.minor1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @Column(unique = true,nullable = false)
    private String contact;
    private int age;
    private String address;
    @Column(unique = true)
    private String email;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;
    @OneToMany(mappedBy = "student")
    private List<Book> bookList;
    @OneToMany(mappedBy = "student")
    private List<Transaction> transactionList;
    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;
    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"student","admin","password"})
    private MyUser myUser;
}
