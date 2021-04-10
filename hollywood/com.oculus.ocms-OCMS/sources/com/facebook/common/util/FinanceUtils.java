package com.facebook.common.util;

import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;

public class FinanceUtils {
    public static final int DEFAULT_DECIMAL_PLACES = 2;
    public static final int DEFAULT_OFFSET = 100;

    private FinanceUtils() {
    }

    public static String getPriceDisplayString(String str, long j, int i) {
        Preconditions.checkArgument(i >= 0);
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        currencyInstance.setCurrency(Currency.getInstance(str));
        currencyInstance.setMinimumFractionDigits(i);
        currencyInstance.setMaximumFractionDigits(i);
        return currencyInstance.format(new BigDecimal(j).divide(new BigDecimal(100))).replaceAll("\\s", "");
    }
}
