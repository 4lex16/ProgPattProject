package org.example.finalproject.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {
    private static String DATABASE_URL = "jdbc:sqlite:./src/main/resources/onlineQuizManagementSystemDatabase.db";

    private Connection connect() {
        Connection conn = null;
        try {
            DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
