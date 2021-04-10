package java.util;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import libcore.icu.ICU;

public final class Currency implements Serializable {
    private static ConcurrentMap instances = new ConcurrentHashMap(7);
    private static final long serialVersionUID = -158308464356906721L;
    private final String currencyCode;
    private final transient android.icu.util.Currency icuCurrency;

    private Currency(android.icu.util.Currency currency) {
        this.icuCurrency = currency;
        this.currencyCode = currency.getCurrencyCode();
    }

    public static Currency getInstance(String str) {
        Currency currency = (Currency) instances.get(str);
        if (currency != null) {
            return currency;
        }
        android.icu.util.Currency instance = android.icu.util.Currency.getInstance(str);
        if (instance == null) {
            return null;
        }
        Currency currency2 = new Currency(instance);
        Currency currency3 = (Currency) instances.putIfAbsent(str, currency2);
        return currency3 != null ? currency3 : currency2;
    }

    public static Currency getInstance(Locale locale) {
        String country = locale.getCountry();
        if (country != null) {
            android.icu.util.Currency instance = android.icu.util.Currency.getInstance(locale);
            String variant = locale.getVariant();
            if (!variant.isEmpty() && (variant.equals("EURO") || variant.equals("HK") || variant.equals("PREEURO"))) {
                country = country + "_" + variant;
            }
            String currencyCode2 = ICU.getCurrencyCode(country);
            if (currencyCode2 == null) {
                throw new IllegalArgumentException("Unsupported ISO 3166 country: " + locale);
            } else if (instance == null || instance.getCurrencyCode().equals("XXX")) {
                return null;
            } else {
                return getInstance(currencyCode2);
            }
        } else {
            throw new NullPointerException();
        }
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public String getSymbol(Locale locale) {
        if (locale != null) {
            return this.icuCurrency.getSymbol(locale);
        }
        throw new NullPointerException("locale == null");
    }

    public int getDefaultFractionDigits() {
        if (this.icuCurrency.getCurrencyCode().equals("XXX")) {
            return -1;
        }
        return this.icuCurrency.getDefaultFractionDigits();
    }

    public String toString() {
        return this.icuCurrency.toString();
    }

    private Object readResolve() {
        return getInstance(this.currencyCode);
    }
}
