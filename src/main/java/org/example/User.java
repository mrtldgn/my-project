package org.example;

public class User {
    int id;
    String firstName;
    String lastName;
    String email;

    public User(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + " " + firstName + " "+ lastName+ " "+ email;
    }
}
