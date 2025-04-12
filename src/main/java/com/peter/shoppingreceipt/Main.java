package com.peter.shoppingreceipt;


import com.peter.shoppingreceipt.service.cart.ShoppingCart;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart1 = new ShoppingCart("CA");
        cart1.addItem("book", 1);
        cart1.addItem("potato chips", 1);
        cart1.printReceipt();
        System.out.println("---------------------");
        ShoppingCart cart2 = new ShoppingCart("NY");
        cart2.addItem("book", 1);
        cart2.addItem("pencil", 3);
        cart2.printReceipt();
        System.out.println("---------------------");
        ShoppingCart cart3 = new ShoppingCart("NY");
        cart3.addItem("pencil", 2);
        cart3.addItem("shirt", 1);
        cart3.printReceipt();
    }
}