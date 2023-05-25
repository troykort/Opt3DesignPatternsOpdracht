package com.example.opt3designpatterns;

import com.example.opt3designpatterns.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/LoginScreen.fxml"));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/com.example.opt3designpatterns/LoginScreen.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Screens/LoginScreen.fxml"));
        Parent root = loader.load();

//        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        ProductListWindow productListWindow = new ProductListWindow(inventory);

        // Add sample products to inventory
        Product car1 = new Car("Toyota", 1200, 1600);
        Product truck1 = new Truck(5000, 5000);
        Product drill1 = new Drill("Bosch", "Cordless");
        inventory.addProduct(car1);
        inventory.addProduct(truck1);
        inventory.addProduct(drill1);

        // Open product detail windows
        AvailableProductDetailWindow availableProductDetailWindow = new AvailableProductDetailWindow(car1);
        RentedProductDetailWindow rentedProductDetailWindow = new RentedProductDetailWindow(truck1, "John Doe");

        availableProductDetailWindow.update();
        rentedProductDetailWindow.update();

        // Simulate renting a product
        availableProductDetailWindow.rentProduct("Jane Smith");
        rentedProductDetailWindow.update();

        // Simulate returning a product
        rentedProductDetailWindow.returnProduct();
        rentedProductDetailWindow.update();
        System.out.println("------------------------------------");
        System.out.println(inventory.getProducts().get(0).getDescription());
        System.out.println("------------------------------------");

        launch();

    }
}