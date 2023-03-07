package com.example.minor1.request;

import com.example.minor1.model.Admin;
import com.example.minor1.model.Student;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {
    private String username;
    private String password;
    private String authority;
    private Student student;
    private Admin admin;
}
