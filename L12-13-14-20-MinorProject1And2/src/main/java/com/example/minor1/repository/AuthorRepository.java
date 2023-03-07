package com.example.minor1.repository;

import com.example.minor1.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    /**
     *There are 2 ways to write queries:-
     * 1. Native Query - Works as SQL on tables
     *    @Query(value = "select a from author a where a.email = :my_email",nativeQuery = true) --> author -> table name in database
     *    Author getAuthorGivenEmailId(String my_email);
     *    Or
     *    @Query(value = "select a from author a where a.email = ?1",nativeQuery = true) --> ?1 represents the first parameter index of the function.
     *    Author getAuthorGivenEmailId(String my_email);
     * 2. JPQL - Java persistence query language. Works on java objects.
     *    @Query(value = "select a from Author a where a.email = :my_email") --> Author --> Entity Class name
     *    Author getAuthorGivenEmailIdJPQL(String email);
     * 3. Don't have to write the query --> We have to write the names of the functions in such a way that it matches the JPA standards.
     * By default, every query is JPQL.
     */
    @Query(value = "select a from author a where a.email = :my_email",nativeQuery = true)
    Author getAuthorGivenEmailIdNativeQuery(String my_email);

    @Query(value = "select a from Author a where a.email = :my_email")
    Author getAuthorGivenEmailIdJPQL(String my_email);

    //Find all books by country name
    @Query(value = "select a from author a where a.land = ?1",nativeQuery = true)
    List<Author> getBooksByCountryNative(String country);

    @Query(value = "select a from Author a where a.country = ?1")
    List<Author> getBooksByCountryJPQL(String country);

    //Using JPA standards
    Author findByEmail(String email);

    //Find all authors above age 30 living in India and their names starting with A.
    List<Author> findByAgeGreaterThanEqualAndCountryAndNameStartingWith(int age,String country, String prefix);
    //Above function is equivalent to
    //select * from author where age >= ?1 and country = ?2 and name like ?3%;

}
