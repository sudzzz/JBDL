package com.example.minor1.request;

import com.example.minor1.model.Author;
import com.example.minor1.model.Book;
import com.example.minor1.model.Genre;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateRequest {

    @NotBlank      //NotNull can take "", which NotBlank will give error.
    private String name;
    @NonNull
    private Author author;
    @Positive
    private int cost;
    @NotNull
    private Genre genre;
    public Book to(){
       return Book.builder()
               .cost(this.cost)
               .author(this.author)
               .name(this.name)
               .genre(this.genre)
               .build();
    }
}
