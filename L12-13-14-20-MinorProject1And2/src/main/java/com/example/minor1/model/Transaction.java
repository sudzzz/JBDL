package com.example.minor1.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String externalTxnId;
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    private double payment;
    @ManyToOne
    @JoinColumn
    private Book book;
    @ManyToOne
    @JoinColumn
    private Student student;
    @CreationTimestamp
    private Date transactionDate;
}
