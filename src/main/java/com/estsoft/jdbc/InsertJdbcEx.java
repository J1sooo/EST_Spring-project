package com.estsoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertJdbcEx {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "1111";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            statement.addBatch("insert into students values (5,'name3',23,'address3',now())");
            statement.addBatch("insert into students values (6,'name4',24,'address4',now())");
            int[] result = statement.executeBatch();

            System.out.println("insert count: " + result.length);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}

