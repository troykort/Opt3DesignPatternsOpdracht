package com.example.opt3designpatterns.Model;

public class RentedProductDetailWindow extends ProductDetailWindow {
    private String customerName;

    public RentedProductDetailWindow(Product product, String customerName) {
        super(product);
        this.customerName = customerName;
    }

    public void update() {
        show();
        System.out.println("Customer: " + customerName);
        System.out.println("Return button");
    }
}