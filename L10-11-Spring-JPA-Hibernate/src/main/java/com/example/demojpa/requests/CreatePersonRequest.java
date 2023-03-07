package com.example.demojpa.requests;

import com.example.demojpa.models.Person;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Locale;
import java.util.Random;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreatePersonRequest {

    @NotBlank(message = "FirstName should not be empty")
    private String firstName; // should not be empty

    private String lastName; // can be empty

    @NotBlank(message = "Date of birth should not be empty")
    private String dob; // mandatory

    @NotBlank
    private String location;

    public Person to(){
        //TODO: Need to remove the id and add auto increment at the model layer
        return Person.builder()
                .id(new Random().nextInt())
                .firstName(firstName)
                .lastName(lastName)
                .dob(dob)
//                .countryCode(getCountryCodeFromLocation())
                .build();
    }

//    public String getCountryCodeFromLocation(){
//        if(this.location.equalsIgnoreCase("india")){
//            return "+91";
//        }
//
//        return null;
//    }

}
