package com.example.prova02.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/prova02", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
