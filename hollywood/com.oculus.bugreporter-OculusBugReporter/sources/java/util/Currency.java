package java.util;

import java.io.Serializable;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import libcore.icu.ICU;

public final class Currency implements Serializable {
    private static HashSet<Currency> available = null;
    private static ConcurrentMap<String, Currency> instances = new ConcurrentHashMap(7);
    private static final long serialVersionUID = -158308464356906721L;
    private final String currencyCode;
    private final transient android.icu.util.Currency icuCurrency;

    private Currency(android.icu.util.Currency icuCurrency2) {
        this.icuCurrency = icuCurrency2;
        this.currencyCode = icuCurrency2.getCurrencyCode();
    }

    public static Currency getInstance(String currencyCode2) {
        Currency instance = instances.get(currencyCode2);
        if (instance != null) {
            return instance;
        }
        android.icu.util.Currency icuInstance = android.icu.util.Currency.getInstance(currencyCode2);
        if (icuInstance == null) {
            return null;
        }
        Currency currencyVal = new Currency(icuInstance);
        Currency instance2 = instances.putIfAbsent(currencyCode2, currencyVal);
        return instance2 != null ? instance2 : currencyVal;
    }

    public static Currency getInstance(Locale locale) {
        String country = locale.getCountry();
        if (country != null) {
            android.icu.util.Currency icuInstance = android.icu.util.Currency.getInstance(locale);
            String variant = locale.getVariant();
            if (!variant.isEmpty() && (variant.equals("EURO") || variant.equals("HK") || variant.equals("PREEURO"))) {
                country = country + "_" + variant;
            }
            String currencyCode2 = ICU.getCurrencyCode(country);
            if (currencyCode2 == null) {
                throw new IllegalArgumentException("Unsupported ISO 3166 country: " + ((Object) locale));
            } else if (icuInstance == null || icuInstance.getCurrencyCode().equals("XXX")) {
                return null;
            } else {
                return getInstance(currencyCode2);
            }
        } else {
            throw new NullPointerException();
        }
    }

    public static Set<Currency> getAvailableCurrencies() {
        synchronized (Currency.class) {
            if (available == null) {
                available = new HashSet<>();
                for (android.icu.util.Currency icuCurrency2 : android.icu.util.Currency.getAvailableCurrencies()) {
                    Currency currency = getInstance(icuCurrency2.getCurrencyCode());
                    if (currency == null) {
                        currency = new Currency(icuCurrency2);
                        instances.put(currency.currencyCode, currency);
                    }
                    available.add(currency);
                }
            }
        }
        return (Set) available.clone();
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public String getSymbol() {
        return getSymbol(Locale.getDefault(Locale.Category.DISPLAY));
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

    public int getNumericCode() {
        return this.icuCurrency.getNumericCode();
    }

    public String getDisplayName() {
        return getDisplayName(Locale.getDefault(Locale.Category.DISPLAY));
    }

    public String getDisplayName(Locale locale) {
        return this.icuCurrency.getDisplayName((Locale) Objects.requireNonNull(locale));
    }

    public String toString() {
        return this.icuCurrency.toString();
    }

    private Object readResolve() {
        return getInstance(this.currencyCode);
    }
}
