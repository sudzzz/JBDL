package org.example.repository.impl;

import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private static final Logger logger = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    private Connection connection; //Its bean is created in DBConfig class.

    private PreparedStatement createStatement;

    PersonRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        createTable(); //This is done to create the table when the object of PersonRepositoryImpl is initialized at the beginning
        this.createStatement = connection.prepareStatement(
                "insert into person (first_name, last_name, age, dob) " +
                        "VALUES (?, ?, ?, ?)");
    }

    /**
     * Insert / Update / Delete --> Use executeUpdate
     * Select --> Use executeQuery
     */

    @Override
    public Person createPerson(Person person) {

        try {
            //Mapping our java class to table
            createStatement.setString(1, person.getFirstName());
            createStatement.setString(2, person.getLastName());
            createStatement.setInt(3, person.getAge());
            createStatement.setString(4, person.getDob());

            int result = createStatement.executeUpdate();

            logger.info("Insert statement result - {}", result >= 1 ? true : false);

            return person;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person getPersonById(int id) {
        try {
            PreparedStatement selectStatement = connection.prepareStatement("select * from person where id = ?");
            selectStatement.setInt(1,id);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()){
                return personFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> personList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from person");

            while (resultSet.next()){
                //Mapping the data stored in db to java class
                Person person = personFromResultSet(resultSet);
                personList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public boolean deletePersonById(int id) {
        try {
            PreparedStatement deleteStatement = connection.prepareStatement("delete from person where id = ?");
            deleteStatement.setInt(1,id);

            int result = deleteStatement.executeUpdate();

            logger.info("Insert statement result - {}", result >= 1 ? true : false);

            return result >= 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Person createPersonStatic(Person person) {

        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("insert into person (id, first_name, last_name, age, dob) " +
                    "VALUES(1, 'ABC', 'DEF', 20, '01-01-2002')");

            logger.info("Insert statement result - {}", result >= 1 ? true : false);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private void createTable(){
        try {
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists person (id int primary key auto_increment , first_name varchar(30), " +
                    "last_name varchar(30), age int, dob varchar (12))");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Person personFromResultSet(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .id(resultSet.getInt("id"))
                .age(resultSet.getInt("age"))
                .dob(resultSet.getString("dob"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .build();
    }
}
