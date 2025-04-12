package com.peter.shoppingreceipt.repository;


import com.peter.shoppingreceipt.entity.Product;
import com.peter.shoppingreceipt.entity.ProductCategory;

import java.util.List;

public class ProductDatabase {
    private static final List<Product> products = List.of(
            new Product("book", ProductCategory.BOOKS_AND_STATIONERY),
            new Product("potato chips", ProductCategory.FOOD),
            new Product("pencil", ProductCategory.BOOKS_AND_STATIONERY),
            new Product("shirt", ProductCategory.CLOTHING));

    public static Product getProductByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
