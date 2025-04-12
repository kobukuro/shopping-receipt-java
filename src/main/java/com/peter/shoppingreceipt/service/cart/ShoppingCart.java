package com.peter.shoppingreceipt.service.cart;


import com.peter.shoppingreceipt.entity.Product;
import com.peter.shoppingreceipt.entity.ShoppingItem;
import com.peter.shoppingreceipt.repository.ProductDatabase;
import com.peter.shoppingreceipt.service.tax.TaxStrategyFactory;
import com.peter.shoppingreceipt.service.tax.strategy.TaxCalculationStrategy;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<ShoppingItem> items = new ArrayList<>();
    private final TaxCalculationStrategy taxStrategy;

    public ShoppingCart(String location) {
        this.taxStrategy = TaxStrategyFactory.getTaxStrategy(location);
    }

    public void addItem(String productName, double price, int quantity) {
        Product product = ProductDatabase.getProductByName(productName);
        items.add(new ShoppingItem(product, price, quantity));
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (ShoppingItem item : items) {
            subtotal = subtotal.add(BigDecimal.valueOf(item.getSubtotal()));
        }
        return subtotal;
    }

    public BigDecimal getTotalTax() {
        BigDecimal totalTax = BigDecimal.ZERO;

        for (ShoppingItem item : items) {
            BigDecimal itemTax = taxStrategy.calculateTax(item);
            totalTax = totalTax.add(itemTax);
        }

        return taxStrategy.roundTax(totalTax);
    }

    public BigDecimal getTotal() {
        return getSubtotal().add(getTotalTax());
    }

    public void printReceipt() {
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.printf("%-15s %10s %10s\n", "item", "price", "qty");
        System.out.println();

        for (ShoppingItem item : items) {
            String name = item.getProduct().getName();
            double price = item.getPrice();
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
