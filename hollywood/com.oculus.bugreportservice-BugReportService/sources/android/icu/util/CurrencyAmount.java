package android.icu.util;

public class CurrencyAmount extends Measure {
    public CurrencyAmount(Number number, Currency currency) {
        super(number, currency);
    }

    public Currency getCurrency() {
        return (Currency) getUnit();
    }
}
