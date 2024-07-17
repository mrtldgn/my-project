package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();

        UserDAO userDAO = new UserDAO(connection);
        CarDAO carDAO = new CarDAO(connection);
        carDAO.addCar(new Car(1, 2, "a", "b"));
        connection.close();
    }
}
