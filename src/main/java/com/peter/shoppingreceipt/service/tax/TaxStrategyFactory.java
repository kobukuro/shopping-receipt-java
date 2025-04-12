package com.peter.shoppingreceipt.service.tax;

import com.peter.shoppingreceipt.service.tax.strategy.CaliforniaTaxStrategy;
import com.peter.shoppingreceipt.service.tax.strategy.NewYorkTaxStrategy;
import com.peter.shoppingreceipt.service.tax.strategy.TaxCalculationStrategy;

public class TaxStrategyFactory {
    public static TaxCalculationStrategy getTaxStrategy(String location) {
        return switch (location) {
            case "CA" -> new CaliforniaTaxStrategy();
            case "NY" -> new NewYorkTaxStrategy();
            default -> throw new IllegalArgumentException("Unsupported location: " + location);
        };
    }
}
