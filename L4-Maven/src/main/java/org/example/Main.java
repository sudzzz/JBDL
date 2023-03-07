package org.example;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jbdl","root","password");

        Statement statement = connection.createStatement();
        statement.execute("create table jbdl_33(id int, name varchar(30))");

    }
}