package com.example.opt3designpatterns.Model;

public interface Product {
    String getDescription();
    double getRentalPrice();
    double getInsuranceCost();
    void setRented(boolean para);
    boolean isRented();
    boolean isOpvoorraad();

    void setUsername(String username);

    String getUsername();

}
