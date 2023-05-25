package com.example.opt3designpatterns.Model;

public class ProductListWindow implements Observer {
    private Inventory inventory;

    public ProductListWindow(Inventory inventory) {
        this.inventory = inventory;
        inventory.registerObserver(this);
    }

    public void show() {
        System.out.println("Product List:");
        for (Product product : inventory.getProducts()) {
            System.out.println(product.getDescription());
        }
        System.out.println();
    }

    public void update() {
        show();
    }
}
