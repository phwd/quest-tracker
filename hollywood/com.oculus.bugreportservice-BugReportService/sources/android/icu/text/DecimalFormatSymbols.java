package android.icu.text;

import android.icu.impl.CacheBase;
import android.icu.impl.CurrencyData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.SoftCache;
import android.icu.impl.UResource$Key;
import android.icu.impl.UResource$Sink;
import android.icu.impl.UResource$Table;
import android.icu.impl.UResource$Value;
import android.icu.util.Currency;
import android.icu.util.ICUCloneNotSupportedException;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;

public class DecimalFormatSymbols implements Cloneable, Serializable {
    private static final char[] DEF_DIGIT_CHARS_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final String[] DEF_DIGIT_STRINGS_ARRAY = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final String[] SYMBOL_DEFAULTS = {String.valueOf('.'), String.valueOf(','), String.valueOf('%'), String.valueOf('-'), String.valueOf('+'), "E", String.valueOf((char) 8240), "∞", "NaN", null, null, "×"};
    private static final String[] SYMBOL_KEYS = {"decimal", "group", "percentSign", "minusSign", "plusSign", "exponential", "perMille", "infinity", "nan", "currencyDecimal", "currencyGroup", "superscriptingExponent"};
    private static final CacheBase cachedLocaleData = new SoftCache() {
        /* class android.icu.text.DecimalFormatSymbols.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public CacheData createInstance(ULocale uLocale, Void r2) {
            return DecimalFormatSymbols.loadData(uLocale);
        }
    };
    private static final long serialVersionUID = 5772796243397350300L;
    private String NaN;
    private ULocale actualLocale;
    private transient int codePointZero;
    private transient Currency currency;
    private String currencyPattern;
    private String[] currencySpcAfterSym;
    private String[] currencySpcBeforeSym;
    private String currencySymbol;
    private char decimalSeparator;
    private String decimalSeparatorString;
    private char digit;
    private String[] digitStrings;
    private char[] digits;
    private String exponentMultiplicationSign;
    private String exponentSeparator;
    private char exponential;
    private char groupingSeparator;
    private String groupingSeparatorString;
    private String infinity;
    private String intlCurrencySymbol;
    private char minusSign;
    private String minusString;
    private char monetaryGroupingSeparator;
    private String monetaryGroupingSeparatorString;
    private char monetarySeparator;
    private String monetarySeparatorString;
    private char padEscape;
    private char patternSeparator;
    private char perMill;
    private String perMillString;
    private char percent;
    private String percentString;
    private char plusSign;
    private String plusString;
    private Locale requestedLocale;
    private int serialVersionOnStream;
    private char sigDigit;
    private ULocale ulocale;
    private ULocale validLocale;
    private char zeroDigit;

    public DecimalFormatSymbols() {
        this(ULocale.getDefault(ULocale.Category.FORMAT));
    }

    public DecimalFormatSymbols(Locale locale) {
        this(ULocale.forLocale(locale));
    }

    public DecimalFormatSymbols(ULocale uLocale) {
        this.exponentMultiplicationSign = null;
        this.serialVersionOnStream = 8;
        this.currencyPattern = null;
        initialize(uLocale, null);
    }

    private DecimalFormatSymbols(ULocale uLocale, NumberingSystem numberingSystem) {
        this.exponentMultiplicationSign = null;
        this.serialVersionOnStream = 8;
        this.currencyPattern = null;
        initialize(uLocale, numberingSystem);
    }

    public static DecimalFormatSymbols getInstance() {
        return new DecimalFormatSymbols();
    }

    public static DecimalFormatSymbols forNumberingSystem(ULocale uLocale, NumberingSystem numberingSystem) {
        return new DecimalFormatSymbols(uLocale, numberingSystem);
    }

    public char getZeroDigit() {
        return this.zeroDigit;
    }

    public void setZeroDigit(char c) {
        this.zeroDigit = c;
        this.digitStrings = (String[]) this.digitStrings.clone();
        this.digits = (char[]) this.digits.clone();
        this.digitStrings[0] = String.valueOf(c);
        this.digits[0] = c;
        for (int i = 1; i < 10; i++) {
            char c2 = (char) (c + i);
            this.digitStrings[i] = String.valueOf(c2);
            this.digits[i] = c2;
        }
        this.codePointZero = c;
    }

    public String[] getDigitStrings() {
        return (String[]) this.digitStrings.clone();
    }

    public String[] getDigitStringsLocal() {
        return this.digitStrings;
    }

    public int getCodePointZero() {
        return this.codePointZero;
    }

    public void setDigitStrings(String[] strArr) {
        int i;
        int i2;
        if (strArr == null) {
            throw new NullPointerException("The input digit string array is null");
        } else if (strArr.length == 10) {
            String[] strArr2 = new String[10];
            char[] cArr = new char[10];
            int i3 = -1;
            for (int i4 = 0; i4 < 10; i4++) {
                String str = strArr[i4];
                if (str != null) {
                    strArr2[i4] = str;
                    if (str.length() == 0) {
                        i2 = -1;
                        i = 0;
                    } else {
                        i2 = Character.codePointAt(strArr[i4], 0);
                        i = Character.charCount(i2);
                    }
                    if (i == str.length()) {
                        if (i != 1 || cArr == null) {
                            cArr = null;
                        } else {
                            cArr[i4] = (char) i2;
                        }
                        if (i4 == 0) {
                            i3 = i2;
                        } else if (i2 != i3 + i4) {
                            i3 = -1;
                        }
                    } else {
                        i3 = -1;
                        cArr = null;
                    }
                } else {
                    throw new IllegalArgumentException("The input digit string array contains a null element");
                }
            }
            this.digitStrings = strArr2;
            this.codePointZero = i3;
            if (cArr == null) {
                char[] cArr2 = DEF_DIGIT_CHARS_ARRAY;
                this.zeroDigit = cArr2[0];
                this.digits = cArr2;
                return;
            }
            this.zeroDigit = cArr[0];
            this.digits = cArr;
        } else {
            throw new IllegalArgumentException("Number of digit strings is not 10");
        }
    }

    public char getSignificantDigit() {
        return this.sigDigit;
    }

    public char getGroupingSeparator() {
        return this.groupingSeparator;
    }

    public void setGroupingSeparator(char c) {
        this.groupingSeparator = c;
        this.groupingSeparatorString = String.valueOf(c);
    }

    public String getGroupingSeparatorString() {
        return this.groupingSeparatorString;
    }

    public void setGroupingSeparatorString(String str) {
        if (str != null) {
            this.groupingSeparatorString = str;
            if (str.length() == 1) {
                this.groupingSeparator = str.charAt(0);
            } else {
                this.groupingSeparator = ',';
            }
        } else {
            throw new NullPointerException("The input grouping separator is null");
        }
    }

    public char getDecimalSeparator() {
        return this.decimalSeparator;
    }

    public void setDecimalSeparator(char c) {
        this.decimalSeparator = c;
        this.decimalSeparatorString = String.valueOf(c);
    }

    public String getDecimalSeparatorString() {
        return this.decimalSeparatorString;
    }

    public void setDecimalSeparatorString(String str) {
        if (str != null) {
            this.decimalSeparatorString = str;
            if (str.length() == 1) {
                this.decimalSeparator = str.charAt(0);
            } else {
                this.decimalSeparator = '.';
            }
        } else {
            throw new NullPointerException("The input decimal separator is null");
        }
    }

    public char getPerMill() {
        return this.perMill;
    }

    public void setPerMill(char c) {
        this.perMill = c;
        this.perMillString = String.valueOf(c);
    }

    public String getPerMillString() {
        return this.perMillString;
    }

    public void setPerMillString(String str) {
        if (str != null) {
            this.perMillString = str;
            if (str.length() == 1) {
                this.perMill = str.charAt(0);
            } else {
                this.perMill = 8240;
            }
        } else {
            throw new NullPointerException("The input permille string is null");
        }
    }

    public char getPercent() {
        return this.percent;
    }

    public void setPercent(char c) {
        this.percent = c;
        this.percentString = String.valueOf(c);
    }

    public String getPercentString() {
        return this.percentString;
    }

    public void setPercentString(String str) {
        if (str != null) {
            this.percentString = str;
            if (str.length() == 1) {
                this.percent = str.charAt(0);
            } else {
                this.percent = '%';
            }
        } else {
            throw new NullPointerException("The input percent sign is null");
        }
    }

    public char getDigit() {
        return this.digit;
    }

    public void setDigit(char c) {
        this.digit = c;
    }

    public char getPatternSeparator() {
        return this.patternSeparator;
    }

    public void setPatternSeparator(char c) {
        this.patternSeparator = c;
    }

    public String getInfinity() {
        return this.infinity;
    }

    public void setInfinity(String str) {
        this.infinity = str;
    }

    public String getNaN() {
        return this.NaN;
    }

    public void setNaN(String str) {
        this.NaN = str;
    }

    public char getMinusSign() {
        return this.minusSign;
    }

    public void setMinusSign(char c) {
        this.minusSign = c;
        this.minusString = String.valueOf(c);
    }

    public String getMinusSignString() {
        return this.minusString;
    }

    public void setMinusSignString(String str) {
        if (str != null) {
            this.minusString = str;
            if (str.length() == 1) {
                this.minusSign = str.charAt(0);
            } else {
                this.minusSign = '-';
            }
        } else {
            throw new NullPointerException("The input minus sign is null");
        }
    }

    public char getPlusSign() {
        return this.plusSign;
    }

    public void setPlusSign(char c) {
        this.plusSign = c;
        this.plusString = String.valueOf(c);
    }

    public String getPlusSignString() {
        return this.plusString;
    }

    public void setPlusSignString(String str) {
        if (str != null) {
            this.plusString = str;
            if (str.length() == 1) {
                this.plusSign = str.charAt(0);
            } else {
                this.plusSign = '+';
            }
        } else {
            throw new NullPointerException("The input plus sign is null");
        }
    }

    public String getCurrencySymbol() {
        return this.currencySymbol;
    }

    public void setCurrencySymbol(String str) {
        this.currencySymbol = str;
    }

    public String getInternationalCurrencySymbol() {
        return this.intlCurrencySymbol;
    }

    public void setInternationalCurrencySymbol(String str) {
        this.intlCurrencySymbol = str;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency2) {
        if (currency2 != null) {
            this.currency = currency2;
            this.intlCurrencySymbol = currency2.getCurrencyCode();
            this.currencySymbol = currency2.getSymbol(this.requestedLocale);
            return;
        }
        throw new NullPointerException();
    }

    public char getMonetaryDecimalSeparator() {
        return this.monetarySeparator;
    }

    public void setMonetaryDecimalSeparator(char c) {
        this.monetarySeparator = c;
        this.monetarySeparatorString = String.valueOf(c);
    }

    public String getMonetaryDecimalSeparatorString() {
        return this.monetarySeparatorString;
    }

    public void setMonetaryDecimalSeparatorString(String str) {
        if (str != null) {
            this.monetarySeparatorString = str;
            if (str.length() == 1) {
                this.monetarySeparator = str.charAt(0);
            } else {
                this.monetarySeparator = '.';
            }
        } else {
            throw new NullPointerException("The input monetary decimal separator is null");
        }
    }

    public void setMonetaryGroupingSeparator(char c) {
        this.monetaryGroupingSeparator = c;
        this.monetaryGroupingSeparatorString = String.valueOf(c);
    }

    public String getMonetaryGroupingSeparatorString() {
        return this.monetaryGroupingSeparatorString;
    }

    public void setMonetaryGroupingSeparatorString(String str) {
        if (str != null) {
            this.monetaryGroupingSeparatorString = str;
            if (str.length() == 1) {
                this.monetaryGroupingSeparator = str.charAt(0);
            } else {
                this.monetaryGroupingSeparator = ',';
            }
        } else {
            throw new NullPointerException("The input monetary grouping separator is null");
        }
    }

    /* access modifiers changed from: package-private */
    public String getCurrencyPattern() {
        return this.currencyPattern;
    }

    public void setExponentMultiplicationSign(String str) {
        this.exponentMultiplicationSign = str;
    }

    public String getExponentSeparator() {
        return this.exponentSeparator;
    }

    public void setExponentSeparator(String str) {
        this.exponentSeparator = str;
    }

    public char getPadEscape() {
        return this.padEscape;
    }

    public String getPatternForCurrencySpacing(int i, boolean z) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("unknown currency spacing: " + i);
        } else if (z) {
            return this.currencySpcBeforeSym[i];
        } else {
            return this.currencySpcAfterSym[i];
        }
    }

    public Locale getLocale() {
        return this.requestedLocale;
    }

    public ULocale getULocale() {
        return this.ulocale;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DecimalFormatSymbols)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        DecimalFormatSymbols decimalFormatSymbols = (DecimalFormatSymbols) obj;
        for (int i = 0; i <= 2; i++) {
            if (!(this.currencySpcBeforeSym[i].equals(decimalFormatSymbols.currencySpcBeforeSym[i]) && this.currencySpcAfterSym[i].equals(decimalFormatSymbols.currencySpcAfterSym[i]))) {
                return false;
            }
        }
        char[] cArr = decimalFormatSymbols.digits;
        if (cArr == null) {
            for (int i2 = 0; i2 < 10; i2++) {
                if (this.digits[i2] != decimalFormatSymbols.zeroDigit + i2) {
                    return false;
                }
            }
        } else if (!Arrays.equals(this.digits, cArr)) {
            return false;
        }
        return this.groupingSeparator == decimalFormatSymbols.groupingSeparator && this.decimalSeparator == decimalFormatSymbols.decimalSeparator && this.percent == decimalFormatSymbols.percent && this.perMill == decimalFormatSymbols.perMill && this.digit == decimalFormatSymbols.digit && this.minusSign == decimalFormatSymbols.minusSign && this.minusString.equals(decimalFormatSymbols.minusString) && this.patternSeparator == decimalFormatSymbols.patternSeparator && this.infinity.equals(decimalFormatSymbols.infinity) && this.NaN.equals(decimalFormatSymbols.NaN) && this.currencySymbol.equals(decimalFormatSymbols.currencySymbol) && this.intlCurrencySymbol.equals(decimalFormatSymbols.intlCurrencySymbol) && this.padEscape == decimalFormatSymbols.padEscape && this.plusSign == decimalFormatSymbols.plusSign && this.plusString.equals(decimalFormatSymbols.plusString) && this.exponentSeparator.equals(decimalFormatSymbols.exponentSeparator) && this.monetarySeparator == decimalFormatSymbols.monetarySeparator && this.monetaryGroupingSeparator == decimalFormatSymbols.monetaryGroupingSeparator && this.exponentMultiplicationSign.equals(decimalFormatSymbols.exponentMultiplicationSign);
    }

    public int hashCode() {
        return (((this.digits[0] * '%') + this.groupingSeparator) * 37) + this.decimalSeparator;
    }

    /* access modifiers changed from: private */
    public static final class DecFmtDataSink extends UResource$Sink {
        private String[] numberElements;

        public DecFmtDataSink(String[] strArr) {
            this.numberElements = strArr;
        }

        @Override // android.icu.impl.UResource$Sink
        public void put(UResource$Key uResource$Key, UResource$Value uResource$Value, boolean z) {
            UResource$Table table = uResource$Value.getTable();
            for (int i = 0; table.getKeyAndValue(i, uResource$Key, uResource$Value); i++) {
                int i2 = 0;
                while (true) {
                    if (i2 >= DecimalFormatSymbols.SYMBOL_KEYS.length) {
                        break;
                    } else if (uResource$Key.contentEquals(DecimalFormatSymbols.SYMBOL_KEYS[i2])) {
                        String[] strArr = this.numberElements;
                        if (strArr[i2] == null) {
                            strArr[i2] = uResource$Value.toString();
                        }
                    } else {
                        i2++;
                    }
                }
            }
        }
    }

    private void initialize(ULocale uLocale, NumberingSystem numberingSystem) {
        ULocale uLocale2;
        this.requestedLocale = uLocale.toLocale();
        this.ulocale = uLocale;
        if (numberingSystem == null) {
            uLocale2 = uLocale;
        } else {
            uLocale2 = uLocale.setKeywordValue("numbers", numberingSystem.getName());
        }
        CacheData cacheData = (CacheData) cachedLocaleData.getInstance(uLocale2, null);
        ULocale uLocale3 = cacheData.validLocale;
        setLocale(uLocale3, uLocale3);
        setDigitStrings(cacheData.digits);
        String[] strArr = cacheData.numberElements;
        setDecimalSeparatorString(strArr[0]);
        setGroupingSeparatorString(strArr[1]);
        this.patternSeparator = ';';
        setPercentString(strArr[2]);
        setMinusSignString(strArr[3]);
        setPlusSignString(strArr[4]);
        setExponentSeparator(strArr[5]);
        setPerMillString(strArr[6]);
        setInfinity(strArr[7]);
        setNaN(strArr[8]);
        setMonetaryDecimalSeparatorString(strArr[9]);
        setMonetaryGroupingSeparatorString(strArr[10]);
        setExponentMultiplicationSign(strArr[11]);
        this.digit = '#';
        this.padEscape = '*';
        this.sigDigit = '@';
        CurrencyData.CurrencyDisplayInfo instance = CurrencyData.provider.getInstance(uLocale, true);
        this.currency = Currency.getInstance(uLocale);
        Currency currency2 = this.currency;
        if (currency2 != null) {
            this.intlCurrencySymbol = currency2.getCurrencyCode();
            this.currencySymbol = this.currency.getName(uLocale, 0, null);
            CurrencyData.CurrencyFormatInfo formatInfo = instance.getFormatInfo(this.intlCurrencySymbol);
            if (formatInfo != null) {
                this.currencyPattern = formatInfo.currencyPattern;
                setMonetaryDecimalSeparatorString(formatInfo.monetaryDecimalSeparator);
                setMonetaryGroupingSeparatorString(formatInfo.monetaryGroupingSeparator);
            }
        } else {
            this.intlCurrencySymbol = "XXX";
            this.currencySymbol = "¤";
        }
        initSpacingInfo(instance.getSpacingInfo());
    }

    /* access modifiers changed from: private */
    public static CacheData loadData(ULocale uLocale) {
        String str;
        boolean z;
        NumberingSystem instance = NumberingSystem.getInstance(uLocale);
        String[] strArr = new String[10];
        if (instance == null || instance.getRadix() != 10 || instance.isAlgorithmic() || !NumberingSystem.isValidDigitString(instance.getDescription())) {
            strArr = DEF_DIGIT_STRINGS_ARRAY;
            str = "latn";
        } else {
            String description = instance.getDescription();
            int i = 0;
            int i2 = 0;
            while (i < 10) {
                int charCount = Character.charCount(description.codePointAt(i2)) + i2;
                strArr[i] = description.substring(i2, charCount);
                i++;
                i2 = charCount;
            }
            str = instance.getName();
        }
        ICUResourceBundle iCUResourceBundle = (ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", uLocale);
        ULocale uLocale2 = iCUResourceBundle.getULocale();
        String[] strArr2 = new String[SYMBOL_KEYS.length];
        DecFmtDataSink decFmtDataSink = new DecFmtDataSink(strArr2);
        try {
            iCUResourceBundle.getAllItemsWithFallback("NumberElements/" + str + "/" + "symbols", decFmtDataSink);
        } catch (MissingResourceException unused) {
        }
        int length = strArr2.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                z = false;
                break;
            } else if (strArr2[i3] == null) {
                z = true;
                break;
            } else {
                i3++;
            }
        }
        if (z && !str.equals("latn")) {
            iCUResourceBundle.getAllItemsWithFallback("NumberElements/latn/symbols", decFmtDataSink);
        }
        for (int i4 = 0; i4 < SYMBOL_KEYS.length; i4++) {
            if (strArr2[i4] == null) {
                strArr2[i4] = SYMBOL_DEFAULTS[i4];
            }
        }
        if (strArr2[9] == null) {
            strArr2[9] = strArr2[0];
        }
        if (strArr2[10] == null) {
            strArr2[10] = strArr2[1];
        }
        return new CacheData(uLocale2, strArr, strArr2);
    }

    private void initSpacingInfo(CurrencyData.CurrencySpacingInfo currencySpacingInfo) {
        this.currencySpcBeforeSym = currencySpacingInfo.getBeforeSymbols();
        this.currencySpcAfterSym = currencySpacingInfo.getAfterSymbols();
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    public final ULocale getLocale(ULocale.Type type) {
        return type == ULocale.ACTUAL_LOCALE ? this.actualLocale : this.validLocale;
    }

    /* access modifiers changed from: package-private */
    public final void setLocale(ULocale uLocale, ULocale uLocale2) {
        boolean z = true;
        boolean z2 = uLocale == null;
        if (uLocale2 != null) {
            z = false;
        }
        if (z2 == z) {
            this.validLocale = uLocale;
            this.actualLocale = uLocale2;
            return;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: private */
    public static class CacheData {
        final String[] digits;
        final String[] numberElements;
        final ULocale validLocale;

        public CacheData(ULocale uLocale, String[] strArr, String[] strArr2) {
            this.validLocale = uLocale;
            this.digits = strArr;
            this.numberElements = strArr2;
        }
    }
}
