package com.peter.shoppingreceipt.repository;


import com.peter.shoppingreceipt.entity.Product;
import com.peter.shoppingreceipt.entity.ProductCategory;

import java.util.List;

public class ProductDatabase {
    private static final List<Product> products = List.of(
            new Product("book", 17.99, ProductCategory.BOOKS_AND_STATIONERY),
            new Product("potato chips", 3.99, ProductCategory.FOOD),
            new Product("pencil", 2.99, ProductCategory.BOOKS_AND_STATIONERY),
            new Product("shirt", 29.99, ProductCategory.CLOTHING));

    public static Product getProductByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
