package com.peter.shoppingreceipt.service.tax.strategy;

import com.peter.shoppingreceipt.entity.ProductCategory;
import com.peter.shoppingreceipt.entity.ShoppingItem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NewYorkTaxStrategy implements TaxCalculationStrategy {
    private static final BigDecimal TAX_RATE = new BigDecimal("0.08875");
    private static final BigDecimal NICKEL = new BigDecimal("0.05");

    @Override
    public BigDecimal calculateTax(ShoppingItem item) {
        if (item.getProduct().getCategory() != ProductCategory.FOOD &&
                item.getProduct().getCategory() != ProductCategory.CLOTHING) {
            return BigDecimal.valueOf(item.getSubtotal()).multiply(TAX_RATE);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal roundTax(BigDecimal tax) {
        return tax.divide(NICKEL, 0, RoundingMode.CEILING).multiply(NICKEL);
    }
}
