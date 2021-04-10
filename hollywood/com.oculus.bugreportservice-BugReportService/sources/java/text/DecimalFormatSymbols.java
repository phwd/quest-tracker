package java.text;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.Currency;
import java.util.Locale;
import libcore.icu.LocaleData;

public class DecimalFormatSymbols implements Cloneable, Serializable {
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("currencySymbol", String.class), new ObjectStreamField("decimalSeparator", Character.TYPE), new ObjectStreamField("digit", Character.TYPE), new ObjectStreamField("exponential", Character.TYPE), new ObjectStreamField("exponentialSeparator", String.class), new ObjectStreamField("groupingSeparator", Character.TYPE), new ObjectStreamField("infinity", String.class), new ObjectStreamField("intlCurrencySymbol", String.class), new ObjectStreamField("minusSign", Character.TYPE), new ObjectStreamField("monetarySeparator", Character.TYPE), new ObjectStreamField("NaN", String.class), new ObjectStreamField("patternSeparator", Character.TYPE), new ObjectStreamField("percent", Character.TYPE), new ObjectStreamField("perMill", Character.TYPE), new ObjectStreamField("serialVersionOnStream", Integer.TYPE), new ObjectStreamField("zeroDigit", Character.TYPE), new ObjectStreamField("locale", Locale.class), new ObjectStreamField("minusSignStr", String.class), new ObjectStreamField("percentStr", String.class)};
    static final long serialVersionUID = 5772796243397350300L;
    private String NaN;
    private transient android.icu.text.DecimalFormatSymbols cachedIcuDFS = null;
    private transient Currency currency;
    private String currencySymbol;
    private char decimalSeparator;
    private char digit;
    private char exponential;
    private String exponentialSeparator;
    private char groupingSeparator;
    private String infinity;
    private String intlCurrencySymbol;
    private Locale locale;
    private char minusSign;
    private char monetarySeparator;
    private char patternSeparator;
    private char perMill;
    private char percent;
    private int serialVersionOnStream = 3;
    private char zeroDigit;

    public DecimalFormatSymbols() {
        initialize(Locale.getDefault(Locale.Category.FORMAT));
    }

    public DecimalFormatSymbols(Locale locale2) {
        initialize(locale2);
    }

    public static final DecimalFormatSymbols getInstance(Locale locale2) {
        return new DecimalFormatSymbols(locale2);
    }

    public char getZeroDigit() {
        return this.zeroDigit;
    }

    public void setZeroDigit(char c) {
        this.zeroDigit = c;
        this.cachedIcuDFS = null;
    }

    public char getGroupingSeparator() {
        return this.groupingSeparator;
    }

    public void setGroupingSeparator(char c) {
        this.groupingSeparator = c;
        this.cachedIcuDFS = null;
    }

    public char getDecimalSeparator() {
        return this.decimalSeparator;
    }

    public void setDecimalSeparator(char c) {
        this.decimalSeparator = c;
        this.cachedIcuDFS = null;
    }

    public void setPerMill(char c) {
        this.perMill = c;
        this.cachedIcuDFS = null;
    }

    public void setPercent(char c) {
        this.percent = c;
        this.cachedIcuDFS = null;
    }

    public void setDigit(char c) {
        this.digit = c;
        this.cachedIcuDFS = null;
    }

    public void setPatternSeparator(char c) {
        this.patternSeparator = c;
        this.cachedIcuDFS = null;
    }

    public void setInfinity(String str) {
        this.infinity = str;
        this.cachedIcuDFS = null;
    }

    public void setNaN(String str) {
        this.NaN = str;
        this.cachedIcuDFS = null;
    }

    public char getMinusSign() {
        return this.minusSign;
    }

    public void setMinusSign(char c) {
        this.minusSign = c;
        this.cachedIcuDFS = null;
    }

    public void setCurrencySymbol(String str) {
        this.currencySymbol = str;
        this.cachedIcuDFS = null;
    }

    public String getInternationalCurrencySymbol() {
        return this.intlCurrencySymbol;
    }

    public void setInternationalCurrencySymbol(String str) {
        this.intlCurrencySymbol = str;
        this.currency = null;
        if (str != null) {
            try {
                this.currency = Currency.getInstance(str);
                this.currencySymbol = this.currency.getSymbol(this.locale);
            } catch (IllegalArgumentException unused) {
            }
        }
        this.cachedIcuDFS = null;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency2) {
        if (currency2 != null) {
            this.currency = currency2;
            this.intlCurrencySymbol = currency2.getCurrencyCode();
            this.currencySymbol = currency2.getSymbol(this.locale);
            this.cachedIcuDFS = null;
            return;
        }
        throw new NullPointerException();
    }

    public void setMonetaryDecimalSeparator(char c) {
        this.monetarySeparator = c;
        this.cachedIcuDFS = null;
    }

    public void setExponentSeparator(String str) {
        if (str != null) {
            this.exponentialSeparator = str;
            return;
        }
        throw new NullPointerException();
    }

    public Object clone() {
        try {
            return (DecimalFormatSymbols) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (DecimalFormatSymbols.class != obj.getClass()) {
            return false;
        }
        DecimalFormatSymbols decimalFormatSymbols = (DecimalFormatSymbols) obj;
        return this.zeroDigit == decimalFormatSymbols.zeroDigit && this.groupingSeparator == decimalFormatSymbols.groupingSeparator && this.decimalSeparator == decimalFormatSymbols.decimalSeparator && this.percent == decimalFormatSymbols.percent && this.perMill == decimalFormatSymbols.perMill && this.digit == decimalFormatSymbols.digit && this.minusSign == decimalFormatSymbols.minusSign && this.patternSeparator == decimalFormatSymbols.patternSeparator && this.infinity.equals(decimalFormatSymbols.infinity) && this.NaN.equals(decimalFormatSymbols.NaN) && this.currencySymbol.equals(decimalFormatSymbols.currencySymbol) && this.intlCurrencySymbol.equals(decimalFormatSymbols.intlCurrencySymbol) && this.currency == decimalFormatSymbols.currency && this.monetarySeparator == decimalFormatSymbols.monetarySeparator && this.exponentialSeparator.equals(decimalFormatSymbols.exponentialSeparator) && this.locale.equals(decimalFormatSymbols.locale);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((this.zeroDigit * '%') + this.groupingSeparator) * 37) + this.decimalSeparator) * 37) + this.percent) * 37) + this.perMill) * 37) + this.digit) * 37) + this.minusSign) * 37) + this.patternSeparator) * 37) + this.infinity.hashCode()) * 37) + this.NaN.hashCode()) * 37) + this.currencySymbol.hashCode()) * 37) + this.intlCurrencySymbol.hashCode()) * 37) + this.currency.hashCode()) * 37) + this.monetarySeparator) * 37) + this.exponentialSeparator.hashCode()) * 37) + this.locale.hashCode();
    }

    private void initialize(Locale locale2) {
        this.locale = locale2;
        if (locale2 != null) {
            Locale mapInvalidAndNullLocales = LocaleData.mapInvalidAndNullLocales(locale2);
            LocaleData localeData = LocaleData.get(mapInvalidAndNullLocales);
            Object[] objArr = new Object[3];
            objArr[0] = new String[]{String.valueOf(localeData.decimalSeparator), String.valueOf(localeData.groupingSeparator), String.valueOf(localeData.patternSeparator), localeData.percent, String.valueOf(localeData.zeroDigit), "#", localeData.minusSign, localeData.exponentSeparator, localeData.perMill, localeData.infinity, localeData.NaN};
            String[] strArr = (String[]) objArr[0];
            this.decimalSeparator = strArr[0].charAt(0);
            this.groupingSeparator = strArr[1].charAt(0);
            this.patternSeparator = strArr[2].charAt(0);
            this.percent = maybeStripMarkers(strArr[3], '%');
            this.zeroDigit = strArr[4].charAt(0);
            this.digit = strArr[5].charAt(0);
            this.minusSign = maybeStripMarkers(strArr[6], '-');
            this.exponential = strArr[7].charAt(0);
            this.exponentialSeparator = strArr[7];
            this.perMill = maybeStripMarkers(strArr[8], 8240);
            this.infinity = strArr[9];
            this.NaN = strArr[10];
            if (mapInvalidAndNullLocales.getCountry().length() > 0) {
                try {
                    this.currency = Currency.getInstance(mapInvalidAndNullLocales);
                } catch (IllegalArgumentException unused) {
                }
            }
            Currency currency2 = this.currency;
            if (currency2 != null) {
                this.intlCurrencySymbol = currency2.getCurrencyCode();
                if (objArr[1] == null || objArr[1] != this.intlCurrencySymbol) {
                    this.currencySymbol = this.currency.getSymbol(mapInvalidAndNullLocales);
                    objArr[1] = this.intlCurrencySymbol;
                    objArr[2] = this.currencySymbol;
                } else {
                    this.currencySymbol = (String) objArr[2];
                }
            } else {
                this.intlCurrencySymbol = "XXX";
                try {
                    this.currency = Currency.getInstance(this.intlCurrencySymbol);
                } catch (IllegalArgumentException unused2) {
                }
                this.currencySymbol = "¤";
            }
            this.monetarySeparator = this.decimalSeparator;
            return;
        }
        throw new NullPointerException("locale");
    }

    public static char maybeStripMarkers(String str, char c) {
        int length = str.length();
        if (length >= 1) {
            boolean z = false;
            char c2 = 0;
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (!(charAt == 8206 || charAt == 8207 || charAt == 1564)) {
                    if (z) {
                        return c;
                    }
                    z = true;
                    c2 = charAt;
                }
            }
            if (z) {
                return c2;
            }
        }
        return c;
    }

    /* access modifiers changed from: protected */
    public android.icu.text.DecimalFormatSymbols getIcuDecimalFormatSymbols() {
        android.icu.text.DecimalFormatSymbols decimalFormatSymbols = this.cachedIcuDFS;
        if (decimalFormatSymbols != null) {
            return decimalFormatSymbols;
        }
        this.cachedIcuDFS = new android.icu.text.DecimalFormatSymbols(this.locale);
        this.cachedIcuDFS.setPlusSign('+');
        this.cachedIcuDFS.setZeroDigit(this.zeroDigit);
        this.cachedIcuDFS.setDigit(this.digit);
        this.cachedIcuDFS.setDecimalSeparator(this.decimalSeparator);
        this.cachedIcuDFS.setGroupingSeparator(this.groupingSeparator);
        this.cachedIcuDFS.setMonetaryGroupingSeparator(this.groupingSeparator);
        this.cachedIcuDFS.setPatternSeparator(this.patternSeparator);
        this.cachedIcuDFS.setPercent(this.percent);
        this.cachedIcuDFS.setPerMill(this.perMill);
        this.cachedIcuDFS.setMonetaryDecimalSeparator(this.monetarySeparator);
        this.cachedIcuDFS.setMinusSign(this.minusSign);
        this.cachedIcuDFS.setInfinity(this.infinity);
        this.cachedIcuDFS.setNaN(this.NaN);
        this.cachedIcuDFS.setExponentSeparator(this.exponentialSeparator);
        try {
            this.cachedIcuDFS.setCurrency(android.icu.util.Currency.getInstance(this.currency.getCurrencyCode()));
        } catch (NullPointerException unused) {
            this.currency = Currency.getInstance("XXX");
        }
        this.cachedIcuDFS.setCurrencySymbol(this.currencySymbol);
        this.cachedIcuDFS.setInternationalCurrencySymbol(this.intlCurrencySymbol);
        return this.cachedIcuDFS;
    }

    protected static DecimalFormatSymbols fromIcuInstance(android.icu.text.DecimalFormatSymbols decimalFormatSymbols) {
        DecimalFormatSymbols decimalFormatSymbols2 = new DecimalFormatSymbols(decimalFormatSymbols.getLocale());
        decimalFormatSymbols2.setZeroDigit(decimalFormatSymbols.getZeroDigit());
        decimalFormatSymbols2.setDigit(decimalFormatSymbols.getDigit());
        decimalFormatSymbols2.setDecimalSeparator(decimalFormatSymbols.getDecimalSeparator());
        decimalFormatSymbols2.setGroupingSeparator(decimalFormatSymbols.getGroupingSeparator());
        decimalFormatSymbols2.setPatternSeparator(decimalFormatSymbols.getPatternSeparator());
        decimalFormatSymbols2.setPercent(decimalFormatSymbols.getPercent());
        decimalFormatSymbols2.setPerMill(decimalFormatSymbols.getPerMill());
        decimalFormatSymbols2.setMonetaryDecimalSeparator(decimalFormatSymbols.getMonetaryDecimalSeparator());
        decimalFormatSymbols2.setMinusSign(decimalFormatSymbols.getMinusSign());
        decimalFormatSymbols2.setInfinity(decimalFormatSymbols.getInfinity());
        decimalFormatSymbols2.setNaN(decimalFormatSymbols.getNaN());
        decimalFormatSymbols2.setExponentSeparator(decimalFormatSymbols.getExponentSeparator());
        try {
            if (decimalFormatSymbols.getCurrency() != null) {
                decimalFormatSymbols2.setCurrency(Currency.getInstance(decimalFormatSymbols.getCurrency().getCurrencyCode()));
            } else {
                decimalFormatSymbols2.setCurrency(Currency.getInstance("XXX"));
            }
        } catch (IllegalArgumentException unused) {
            decimalFormatSymbols2.setCurrency(Currency.getInstance("XXX"));
        }
        decimalFormatSymbols2.setInternationalCurrencySymbol(decimalFormatSymbols.getInternationalCurrencySymbol());
        decimalFormatSymbols2.setCurrencySymbol(decimalFormatSymbols.getCurrencySymbol());
        return decimalFormatSymbols2;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.putFields();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readFields();
        throw null;
    }
}
