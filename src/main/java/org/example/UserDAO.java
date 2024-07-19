package org.example;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void addUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.firstName);
            statement.setString(2, user.lastName);
            statement.setString(3, user.email);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                user.id = rs.getInt(1);
            }
            System.out.println("Kullanıcı eklendi: " + user.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> listUsers() {
        String sql = "SELECT * FROM users";
        ArrayList<User> users = new ArrayList<User>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                users.add(new User(id, firstName, lastName, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ? WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.firstName);
        statement.setString(2, user.lastName);
        statement.setString(3, user.email);
        statement.setInt(4, user.id);

        statement.executeUpdate();
        System.out.println("Kullanıcı güncellendi!");
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        System.out.println("Kullanıcı silindi!");
    }

    public void printUsers() {
        ArrayList<User> users = listUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
