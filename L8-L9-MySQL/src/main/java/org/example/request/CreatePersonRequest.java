package org.example.request;

import lombok.*;
import org.example.model.Person;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreatePersonRequest {

    @NotBlank(message = "Date of birth should not be empty")
    private String dob; //mandatory
    @NotBlank(message = "First name should not be empty")
    private String firstName; //should not be empty
    private String lastName; // can be empty

    public Person to(){
        return Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dob(dob)
                .build();
    }
}
