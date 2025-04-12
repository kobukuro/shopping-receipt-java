package com.peter.shoppingreceipt.service.tax.strategy;

import com.peter.shoppingreceipt.entity.ShoppingItem;

import java.math.BigDecimal;

public interface TaxCalculationStrategy {
    BigDecimal calculateTax(ShoppingItem item);
    BigDecimal roundTax(BigDecimal tax);
}
