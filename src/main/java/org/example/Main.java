package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        UserDAO userDAO = new UserDAO(connection);
        CarDAO carDAO = new CarDAO(connection);

        User user = new User(0, "John", "Doe", "john.doe@example.com");
        userDAO.addUser(user);

        carDAO.addCar(new Car(0, user.id, "Toyota", "Corolla"));
        carDAO.addCar(new Car(0, user.id, "Honda", "Civic"));

        ArrayList<Car> userCars = carDAO.listCarsOfUser(user.id);

        for (Car car : userCars) {
            System.out.println(car);
        }

        connection.close();
    }
}
