package com.example.opt3designpatterns.Model;

abstract class ProductDetailWindow implements Observer {
    protected Product product;

    public ProductDetailWindow(Product product) {
        this.product = product;
        product.setRented(false);
    }

    public void show() {
        System.out.println("Product Detail:");
        System.out.println(product.getDescription());
        System.out.println("Rented: " + (product.isRented() ? "Yes" : "No"));
    }

    public abstract void update();

    public void rentProduct(String customerName) {
        product.setRented(true);
        System.out.println("Product rented to: " + customerName);
    }

    public void returnProduct() {
        product.setRented(false);
        System.out.println("Product returned.");
    }
}