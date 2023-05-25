package com.example.opt3designpatterns.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

 public class Inventory implements Subject {
     private ObservableList<Product> products;
    private List<Observer> observers;

    public Inventory() {
        products = FXCollections.observableArrayList();
        observers = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
//        notifyObservers();
    }

    public void removeProduct(Product product) {
        products.remove(product);
//        notifyObservers();
    }

    public ObservableList<Product> getProducts() {
        return products;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

//     @Override
//     public void registerObserver(java.util.Observer observer) {
//
//     }

     @Override
     public void registerObserver(java.util.Observer observer) {

     }

     @Override
     public void removeObserver(java.util.Observer observer) {

     }

     public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}