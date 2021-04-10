package android.icu.number;

import android.icu.util.Currency;

public abstract class CurrencyPrecision extends Precision {
    CurrencyPrecision() {
    }

    public Precision withCurrency(Currency currency) {
        if (currency != null) {
            return Precision.constructFromCurrency(this, currency);
        }
        throw new IllegalArgumentException("Currency must not be null");
    }
}
