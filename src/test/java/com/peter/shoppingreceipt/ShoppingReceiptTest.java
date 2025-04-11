package com.peter.shoppingreceipt;

import com.peter.shoppingreceipt.service.ShoppingCart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Shopping Receipt application.
 */
public class ShoppingReceiptTest {

    /**
     * Tests use case 1: California purchase with a book and potato chips.
     */
    @Test
    public void testUseCase1() {
        ShoppingCart cart1 = new ShoppingCart("CA");
        cart1.addItem("book", 1);
        cart1.addItem("potato chips", 1);

        assertEquals(21.98, cart1.getSubtotal());
        assertEquals(1.80, cart1.getTotalTax());
        assertEquals(23.78, cart1.getTotal());
    }

    /**
     * Tests use case 2: New York purchase with a book and pencils.
     */
    @Test
    public void testUseCase2() {
        ShoppingCart cart2 = new ShoppingCart("NY");
        cart2.addItem("book", 1);
        cart2.addItem("pencil", 3);

        assertEquals(26.96, cart2.getSubtotal());
        assertEquals(2.4, cart2.getTotalTax());
        assertEquals(29.36, cart2.getTotal());
    }

    /**
     * Tests use case 3: New York purchase with pencils and a shirt.
     */
    @Test
    public void testUseCase3() {
        ShoppingCart cart3 = new ShoppingCart("NY");
        cart3.addItem("pencil", 2);
        cart3.addItem("shirt", 1);

        assertEquals(35.97, cart3.getSubtotal());
        assertEquals(0.55, cart3.getTotalTax());
        assertEquals(36.52, cart3.getTotal());
    }
}
