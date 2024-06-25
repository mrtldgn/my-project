package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/userdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "yeni_sifre";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Bağlantı başarılı!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
