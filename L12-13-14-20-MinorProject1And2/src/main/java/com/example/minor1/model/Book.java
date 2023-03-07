package com.example.minor1.model;

import com.example.minor1.response.BookSearchResponse;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn
    private Author author;
//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name = "temp_id",referencedColumnName = "id"),
//            @JoinColumn(name = "temp_name",referencedColumnName = "name")
//    }) //here combination of both the columns(id and name)would act as foreign key of book table
//    private TempModel tempModel;
    private int cost;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    @ManyToOne
    @JoinColumn
    private Student student;
    @OneToMany(mappedBy = "book")
    private List<Transaction> transactionList;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;
    public BookSearchResponse to(){
        return BookSearchResponse.builder()
                .id(id)
                .name(name)
                .author(author)
                .cost(cost)
                .genre(genre)
                .student(student)
                .transactionList(transactionList)
                .createdOn(createdOn)
                .updatedOn(updatedOn)
                .build();
    }
}
