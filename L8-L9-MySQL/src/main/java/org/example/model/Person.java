package org.example.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Person {

    private Integer id; // automatically generated by the server | FE need not send this.
    private String firstName;
    private String lastName;
    private Integer age;
    private String dob;

}