package com.example.MajorProjectEWallet.model;


import com.example.MajorProjectEWallet.enums.TransactionStatusEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String transactionId;

    private String sender;

    private String receiver;
    private String purpose;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatusEnum transactionStatus;

    private Double amount;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

}