package com.example.opt3designpatterns.Model;

public class Drill implements Product {
     private String username;
    private String brand;
    private String type;
    private boolean rented;
    private boolean isOpVoorraad= true;

    public Drill(String brand, String type) {
        this.brand = brand;
        this.type = type;
        this.rented = false;
    }
@Override
    public String getDescription() {
        return "Drill - Brand: " + brand + ", Type: " + type;
    }

    public double getRentalPrice() {
        return 5.0;
    }

    public double getInsuranceCost() {
        return 1.0;
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

    @Override
    public void setUsername(String username) {
        this.username = username;

    }

    public void setIsOpVoorraad(){
        this.isOpVoorraad= isOpVoorraad;
    }
    public String getUsername() {
        return username;
    }


}