package com.example.opt3designpatterns.Model;

public class AvailableProductDetailWindow extends ProductDetailWindow {
    private String customerName;

    public AvailableProductDetailWindow(Product product) {
        super(product);
        this.customerName = "";
    }

    public void update() {
        show();
        System.out.println("Customer name: ");
        System.out.println("Rent button");
        System.out.println("Insurance checkbox");
    }

    public void rentProduct(String customerName) {
        this.customerName = customerName;
        super.rentProduct(customerName);
    }
}
