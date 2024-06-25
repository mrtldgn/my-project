package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        // DatabaseConnection sınıfından bir bağlantı al
        Connection connection = DatabaseConnection.getConnection();

        UserDAO userDAO = new UserDAO(connection);
        CarDAO carDAO = new CarDAO(connection);


        userDAO.printUsers();

        carDAO.printCars();

        carDAO.clearAll();


        //carDAO.addCar(new Car(15, 15, "bmw","X5"));

        connection.close();
    }
}
