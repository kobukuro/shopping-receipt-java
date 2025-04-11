package com.peter.shoppingreceipt.service;


import com.peter.shoppingreceipt.entity.Product;
import com.peter.shoppingreceipt.entity.ShoppingItem;
import com.peter.shoppingreceipt.entity.ProductCategory;
import com.peter.shoppingreceipt.repository.ProductDatabase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final String location;
    private final List<ShoppingItem> items = new ArrayList<>();

    public ShoppingCart(String location) {
        this.location = location;
    }

    public void addItem(String productName, int quantity) {
        Product product = ProductDatabase.getProductByName(productName);
        items.add(new ShoppingItem(product, quantity));
    }

    public double getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (ShoppingItem item : items) {
            subtotal = subtotal.add(new BigDecimal(String.valueOf(item.getSubtotal())));
        }
        return subtotal.doubleValue();
    }

    public double getTotalTax() {
        BigDecimal totalTax = BigDecimal.ZERO;

        for (ShoppingItem item : items) {
            BigDecimal taxRate = BigDecimal.ZERO;
            BigDecimal itemSubtotal = new BigDecimal(String.valueOf(item.getSubtotal()));

            if (location.equals("CA")) {
                if (item.getProduct().getCategory() != ProductCategory.FOOD) {
                    taxRate = new BigDecimal("0.0975");
                }
            } else if (location.equals("NY")) {
                if (item.getProduct().getCategory() != ProductCategory.FOOD &&
                        item.getProduct().getCategory() != ProductCategory.CLOTHING) {
                    taxRate = new BigDecimal("0.08875");
                }
            }

            BigDecimal itemTax = itemSubtotal.multiply(taxRate);
            totalTax = totalTax.add(itemTax);
        }

        BigDecimal nickel = new BigDecimal("0.05");
        totalTax = totalTax.divide(nickel, 0, RoundingMode.CEILING).multiply(nickel);

        return totalTax.doubleValue();
    }


    public double getTotal() {
        BigDecimal subtotal = new BigDecimal(String.valueOf(getSubtotal()));
        BigDecimal tax = new BigDecimal(String.valueOf(getTotalTax()));

        return subtotal.add(tax).doubleValue();
    }

    public void printReceipt() {
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.printf("%-15s %10s %10s\n", "item", "price", "qty");
        System.out.println();
        for (ShoppingItem item : items) {
            String name = item.getProduct().getName();
            double price = item.getProduct().getPrice();
            int qty = item.getQuantity();
            System.out.printf("%-15s %10s %10d\n",
                    name, "$" + df.format(price), qty);
        }

        System.out.printf("%-15s %21s\n",
                "subtotal:", "$" + df.format(getSubtotal()));
        System.out.printf("%-15s %21s\n",
                "tax:", "$" + df.format(getTotalTax()));
        System.out.printf("%-15s %21s\n",
                "total:", "$" + df.format(getTotal()));
    }
}
