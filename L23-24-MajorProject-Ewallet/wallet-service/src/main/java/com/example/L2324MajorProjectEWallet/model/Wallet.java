package com.example.L2324MajorProjectEWallet.model;

import com.example.L2324MajorProjectEWallet.enums.UserIdentifierEnum;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long userId;
    private String phoneNumber;
    private Double balance;
    @Enumerated(value = EnumType.STRING)
    private UserIdentifierEnum userIdentifier;
    private String userIdentifierValue;

}
