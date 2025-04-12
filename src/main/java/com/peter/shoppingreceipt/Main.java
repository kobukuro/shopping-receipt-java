package com.peter.shoppingreceipt;


import com.peter.shoppingreceipt.service.cart.ShoppingCart;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart1 = new ShoppingCart("CA");
        cart1.addItem("book", 17.99, 1);
        cart1.addItem("potato chips", 3.99, 1);
        cart1.printReceipt();
        System.out.println("---------------------");
        ShoppingCart cart2 = new ShoppingCart("NY");
        cart2.addItem("book", 17.99, 1);
        cart2.addItem("pencil", 2.99, 3);
        cart2.printReceipt();
        System.out.println("---------------------");
        ShoppingCart cart3 = new ShoppingCart("NY");
        cart3.addItem("pencil", 2.99, 2);
        cart3.addItem("shirt", 29.99, 1);
        cart3.printReceipt();
    }
}