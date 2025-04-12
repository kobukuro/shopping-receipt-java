package com.peter.shoppingreceipt.service.tax;

import com.peter.shoppingreceipt.entity.ShoppingItem;
import com.peter.shoppingreceipt.service.tax.strategy.CaliforniaTaxStrategy;
import com.peter.shoppingreceipt.service.tax.strategy.NewYorkTaxStrategy;
import com.peter.shoppingreceipt.service.tax.strategy.TaxCalculationStrategy;

import java.math.BigDecimal;

public class TaxStrategyFactory {
    public static TaxCalculationStrategy getTaxStrategy(String location) {
        return switch (location) {
            case "CA" -> new CaliforniaTaxStrategy();
            case "NY" -> new NewYorkTaxStrategy();
            default ->
                // The default strategy, no tax rate.
                    new TaxCalculationStrategy() {
                        @Override
                        public BigDecimal calculateTax(ShoppingItem item) {
                            return BigDecimal.ZERO;
                        }

                        @Override
                        public BigDecimal roundTax(BigDecimal tax) {
                            return tax;
                        }
                    };
        };
    }
}
