package com.example.opt3designpatterns.Model;

public class Car implements Product,ProductFactory {
    private String username;
    private String brand;
    private int weight;
    private int engineDisplacement;
    private boolean rented;
    private boolean isOpVoorraad= true;

    public Car(){

    }
    public Car(String brand, int weight, int engineDisplacement) {
        this.brand = brand;
        this.weight = weight;
        this.engineDisplacement = engineDisplacement;
        this.rented = false;
    }

//    public Car() {
//
//    }

    @Override
    public String getDescription() {
        return "Car - Brand: " + brand + ", Weight: " + weight + "kg, Engine Displacement: " + engineDisplacement + "cc";
    }
    public String getBrand(){
        return brand;
    }

    public double getRentalPrice() {
        return 50.0;
    }

    public double getInsuranceCost() {
        return 0.01 * engineDisplacement;
    }

    public boolean isRented() {
        return rented;
    }

    @Override
    public boolean isOpvoorraad() {
        return isOpVoorraad ;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
    public void setIsOpVoorraad(){
        this.isOpVoorraad= isOpVoorraad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Product createProduct(String para1, int para2 ,int para3) {
        return new Car(para1,para2,para3);
    }


}
