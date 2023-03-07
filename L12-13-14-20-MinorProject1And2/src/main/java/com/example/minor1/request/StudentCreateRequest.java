package com.example.minor1.request;

import com.example.minor1.model.AccountStatus;
import com.example.minor1.model.Admin;
import com.example.minor1.model.Student;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCreateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String contact;
    private String address;
    private int age;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    public Student toStudent(){
        return Student.builder()
                .name(this.name)
                .address(this.address)
                .email(this.email)
                .age(this.age)
                .contact(this.contact)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }

    public Admin toAdmin(){
        return Admin.builder()
                .age(this.age)
                .name(this.name)
                .email(this.email)
                .contact(this.contact)
                .address(this.address)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }

    public UserCreateRequest toStudentUser(){
        return UserCreateRequest.builder()
                .student(toStudent())
                .username(username)
                .password(password)
                .build();
    }

    public UserCreateRequest toAdminUser(){
        return UserCreateRequest.builder()
                .admin(toAdmin())
                .username(username)
                .password(password)
                .build();
    }
}
