package org.example;

public class Car {
    int id;
    int userId;
    String carBrand;
    String carModel;

    public Car(int id, int userId, String carBrand, String carModel) {
        this.id = id;
        this.userId = userId;
        this.carBrand = carBrand;
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + " " + userId + " "+ carBrand+ " "+ carModel;
    }
}
