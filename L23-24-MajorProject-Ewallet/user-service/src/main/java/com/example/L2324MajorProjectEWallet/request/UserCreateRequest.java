package com.example.L2324MajorProjectEWallet.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.L2324MajorProjectEWallet.enums.UserIdentifierEnum;
import com.example.L2324MajorProjectEWallet.model.User;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber; // will act as username in case of spring security
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String country;
    private String dob;
    @NotNull
    private UserIdentifierEnum userIdentifier;
    private String userIdentifierValue;

    public User to(){
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .dob(dob)
                .phoneNumber(phoneNumber)
                .userIdentifier(userIdentifier)
                .userIdentifierValue(userIdentifierValue)
                .country(country)
                .build();
    }
}
