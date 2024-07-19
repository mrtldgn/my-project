package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarDAO {
    public CarDAO(Connection connection) {
    }

    public void addCar(Car car) {
        String sql = "INSERT INTO cars (user_id, brand, model) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, car.userId);
            statement.setString(2, car.carBrand);
            statement.setString(3, car.carModel);

            statement.executeUpdate();
            System.out.println("Araç eklendi!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Car> listCars() throws SQLException {
        String sql = "SELECT * FROM cars";
        ArrayList<Car> cars = new ArrayList<Car>();

        Connection connection = DatabaseConnection.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int carId = resultSet.getInt("id");
            int userId = resultSet.getInt("user_id");
            String carBrand = resultSet.getString("brand");
            String carModel = resultSet.getString("model");
            cars.add(new Car(carId, userId, carBrand, carModel));
        }
        return cars;
    }

    public void updateCar(Car car) throws SQLException {
        String sql = "UPDATE cars SET user_id = ?, brand = ?, model = ? WHERE id = ?";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, car.userId);
        statement.setString(2, car.carBrand);
        statement.setString(3, car.carModel);
        statement.setInt(4, car.id);

        statement.executeUpdate();
        System.out.println("Araç güncellendi!");
    }

    public void deleteCar(int carId)  throws SQLException {
        String sql = "DELETE FROM cars WHERE id = ?";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, carId);

        statement.executeUpdate();
        System.out.println("Araç silindi!");
    }
    public void clearAll() throws SQLException {
        String sql = "DELETE FROM cars";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.executeUpdate();
        System.out.println("Araçlar silindi!");

    }
    public void printCars() throws SQLException {
        ArrayList<Car> cars = listCars();
        for (Car car : cars) {
             System.out.println(car);
        }
    }
    public ArrayList<Car> listCarsOfUser(int userId){
        String sql = "SELECT * FROM cars WHERE user_id = ?";
        ArrayList<Car> cars = new ArrayList<Car>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int carId = resultSet.getInt("id");
                String carBrand = resultSet.getString("brand");
                String carModel = resultSet.getString("model");
                cars.add(new Car(carId, userId, carBrand, carModel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

}

