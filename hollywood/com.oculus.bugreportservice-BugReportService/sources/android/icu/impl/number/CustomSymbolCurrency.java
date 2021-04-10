package android.icu.impl.number;

import android.icu.text.DecimalFormatSymbols;
import android.icu.util.Currency;
import android.icu.util.ULocale;

public class CustomSymbolCurrency extends Currency {
    private static final long serialVersionUID = 2497493016770137670L;
    private String symbol1;
    private String symbol2;

    public static Currency resolve(Currency currency, ULocale uLocale, DecimalFormatSymbols decimalFormatSymbols) {
        if (currency == null) {
            currency = decimalFormatSymbols.getCurrency();
        }
        if (currency == null) {
            return Currency.getInstance("XXX");
        }
        if (!currency.equals(decimalFormatSymbols.getCurrency())) {
            return currency;
        }
        String currencySymbol = decimalFormatSymbols.getCurrencySymbol();
        String internationalCurrencySymbol = decimalFormatSymbols.getInternationalCurrencySymbol();
        String name = currency.getName(decimalFormatSymbols.getULocale(), 0, null);
        String currencyCode = currency.getCurrencyCode();
        if (!name.equals(currencySymbol) || !currencyCode.equals(internationalCurrencySymbol)) {
            return new CustomSymbolCurrency(currencyCode, currencySymbol, internationalCurrencySymbol);
        }
        return currency;
    }

    public CustomSymbolCurrency(String str, String str2, String str3) {
        super(str);
        this.symbol1 = str2;
        this.symbol2 = str3;
    }

    @Override // android.icu.util.Currency
    public String getName(ULocale uLocale, int i, boolean[] zArr) {
        if (i == 0) {
            return this.symbol1;
        }
        return super.getName(uLocale, i, zArr);
    }

    @Override // android.icu.util.Currency
    public String getName(ULocale uLocale, int i, String str, boolean[] zArr) {
        return super.getName(uLocale, i, str, zArr);
    }

    @Override // android.icu.util.Currency
    public String getCurrencyCode() {
        return this.symbol2;
    }

    @Override // android.icu.util.MeasureUnit
    public int hashCode() {
        return this.symbol2.hashCode() ^ (super.hashCode() ^ this.symbol1.hashCode());
    }

    @Override // android.icu.util.MeasureUnit
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            CustomSymbolCurrency customSymbolCurrency = (CustomSymbolCurrency) obj;
            return customSymbolCurrency.symbol1.equals(this.symbol1) && customSymbolCurrency.symbol2.equals(this.symbol2);
        }
    }
}
