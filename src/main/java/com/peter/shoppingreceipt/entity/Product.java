package com.peter.shoppingreceipt.entity;

public class Product {
    private final String name;
    private final ProductCategory category;

    public Product(String name, ProductCategory category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }
}
