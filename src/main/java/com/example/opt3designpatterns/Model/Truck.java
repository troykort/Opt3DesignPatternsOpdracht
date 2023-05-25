package com.example.opt3designpatterns.Model;

public class Truck  implements Product,ProductFactory {
    private String username;
    private int loadCapacity;
    private int engineDisplacement;
    private boolean rented;
    private boolean isOpVoorraad= true;

    public Truck(int loadCapacity, int engineDisplacement) {
        this.loadCapacity = loadCapacity;
        this.engineDisplacement = engineDisplacement;
        this.rented = false;
    }
    @Override
    public String getDescription() {
        return "Truck - Load Capacity: " + loadCapacity + "kg, Engine Displacement: " + engineDisplacement + "cc";
    }

    public double getRentalPrice() {
        return 0.10 * loadCapacity;
    }

    public double getInsuranceCost() {
        return 0.01 * engineDisplacement;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
    @Override
    public boolean isOpvoorraad() {
        return isOpVoorraad ;
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
        int convertedPara2 = Integer.parseInt(para1);

        return new Truck(convertedPara2,para2);
    }
}