package android.icu.text;

import android.icu.math.BigDecimal;
import android.icu.text.DisplayContext;
import android.icu.util.Currency;
import android.icu.util.CurrencyAmount;
import android.icu.util.ULocale;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.MissingResourceException;

public abstract class NumberFormat extends UFormat {
    private static final char[] doubleCurrencySign = {164, 164};
    private static final String doubleCurrencyStr = null;
    private static final long serialVersionUID = -2308460125733713944L;
    private static NumberFormatShim shim;
    private DisplayContext capitalizationSetting = DisplayContext.CAPITALIZATION_NONE;
    private Currency currency;
    private boolean groupingUsed = true;
    private byte maxFractionDigits = 3;
    private byte maxIntegerDigits = 40;
    private int maximumFractionDigits = 3;
    private int maximumIntegerDigits = 40;
    private byte minFractionDigits = 0;
    private byte minIntegerDigits = 1;
    private int minimumFractionDigits = 0;
    private int minimumIntegerDigits = 1;
    private boolean parseIntegerOnly = false;
    private boolean parseStrict;
    private int serialVersionOnStream = 2;

    public abstract StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract StringBuffer format(long j, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract StringBuffer format(BigDecimal bigDecimal, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract StringBuffer format(java.math.BigDecimal bigDecimal, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract StringBuffer format(BigInteger bigInteger, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract Number parse(String str, ParsePosition parsePosition);

    @Override // java.text.Format
    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (obj instanceof Long) {
            return format(((Long) obj).longValue(), stringBuffer, fieldPosition);
        }
        if (obj instanceof BigInteger) {
            return format((BigInteger) obj, stringBuffer, fieldPosition);
        }
        if (obj instanceof java.math.BigDecimal) {
            return format((java.math.BigDecimal) obj, stringBuffer, fieldPosition);
        }
        if (obj instanceof BigDecimal) {
            return format((BigDecimal) obj, stringBuffer, fieldPosition);
        }
        if (obj instanceof CurrencyAmount) {
            return format((CurrencyAmount) obj, stringBuffer, fieldPosition);
        }
        if (obj instanceof Number) {
            return format(((Number) obj).doubleValue(), stringBuffer, fieldPosition);
        }
        throw new IllegalArgumentException("Cannot format given Object as a Number");
    }

    public final String format(double d) {
        return format(d, new StringBuffer(), new FieldPosition(0)).toString();
    }

    public final String format(long j) {
        StringBuffer stringBuffer = new StringBuffer(19);
        format(j, stringBuffer, new FieldPosition(0));
        return stringBuffer.toString();
    }

    public StringBuffer format(CurrencyAmount currencyAmount, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        synchronized (this) {
            Currency currency2 = getCurrency();
            Currency currency3 = currencyAmount.getCurrency();
            boolean equals = currency3.equals(currency2);
            if (!equals) {
                setCurrency(currency3);
            }
            format(currencyAmount.getNumber(), stringBuffer, fieldPosition);
            if (!equals) {
                setCurrency(currency2);
            }
        }
        return stringBuffer;
    }

    public boolean isParseIntegerOnly() {
        return this.parseIntegerOnly;
    }

    public void setParseIntegerOnly(boolean z) {
        this.parseIntegerOnly = z;
    }

    public boolean isParseStrict() {
        return this.parseStrict;
    }

    public DisplayContext getContext(DisplayContext.Type type) {
        DisplayContext displayContext;
        return (type != DisplayContext.Type.CAPITALIZATION || (displayContext = this.capitalizationSetting) == null) ? DisplayContext.CAPITALIZATION_NONE : displayContext;
    }

    public static final NumberFormat getInstance() {
        return getInstance(ULocale.getDefault(ULocale.Category.FORMAT), 0);
    }

    public static NumberFormat getInstance(ULocale uLocale) {
        return getInstance(uLocale, 0);
    }

    /* access modifiers changed from: package-private */
    public static abstract class NumberFormatShim {
        /* access modifiers changed from: package-private */
        public abstract NumberFormat createInstance(ULocale uLocale, int i);

        NumberFormatShim() {
        }
    }

    private static NumberFormatShim getShim() {
        if (shim == null) {
            try {
                shim = (NumberFormatShim) Class.forName("android.icu.text.NumberFormatServiceShim").newInstance();
            } catch (MissingResourceException e) {
                throw e;
            } catch (Exception e2) {
                throw new RuntimeException(e2.getMessage());
            }
        }
        return shim;
    }

    public int hashCode() {
        return (this.maximumIntegerDigits * 37) + this.maxFractionDigits;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NumberFormat numberFormat = (NumberFormat) obj;
        return this.maximumIntegerDigits == numberFormat.maximumIntegerDigits && this.minimumIntegerDigits == numberFormat.minimumIntegerDigits && this.maximumFractionDigits == numberFormat.maximumFractionDigits && this.minimumFractionDigits == numberFormat.minimumFractionDigits && this.groupingUsed == numberFormat.groupingUsed && this.parseIntegerOnly == numberFormat.parseIntegerOnly && this.parseStrict == numberFormat.parseStrict && this.capitalizationSetting == numberFormat.capitalizationSetting;
    }

    @Override // java.text.Format
    public Object clone() {
        return (NumberFormat) super.clone();
    }

    public boolean isGroupingUsed() {
        return this.groupingUsed;
    }

    public void setGroupingUsed(boolean z) {
        this.groupingUsed = z;
    }

    public int getMaximumIntegerDigits() {
        return this.maximumIntegerDigits;
    }

    public void setMaximumIntegerDigits(int i) {
        this.maximumIntegerDigits = Math.max(0, i);
        int i2 = this.minimumIntegerDigits;
        int i3 = this.maximumIntegerDigits;
        if (i2 > i3) {
            this.minimumIntegerDigits = i3;
        }
    }

    public int getMinimumIntegerDigits() {
        return this.minimumIntegerDigits;
    }

    public void setMinimumIntegerDigits(int i) {
        this.minimumIntegerDigits = Math.max(0, i);
        int i2 = this.minimumIntegerDigits;
        if (i2 > this.maximumIntegerDigits) {
            this.maximumIntegerDigits = i2;
        }
    }

    public int getMaximumFractionDigits() {
        return this.maximumFractionDigits;
    }

    public void setMaximumFractionDigits(int i) {
        this.maximumFractionDigits = Math.max(0, i);
        int i2 = this.maximumFractionDigits;
        if (i2 < this.minimumFractionDigits) {
            this.minimumFractionDigits = i2;
        }
    }

    public int getMinimumFractionDigits() {
        return this.minimumFractionDigits;
    }

    public void setMinimumFractionDigits(int i) {
        this.minimumFractionDigits = Math.max(0, i);
        int i2 = this.maximumFractionDigits;
        int i3 = this.minimumFractionDigits;
        if (i2 < i3) {
            this.maximumFractionDigits = i3;
        }
    }

    public void setCurrency(Currency currency2) {
        this.currency = currency2;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public static NumberFormat getInstance(ULocale uLocale, int i) {
        if (i >= 0 && i <= 9) {
            return getShim().createInstance(uLocale, i);
        }
        throw new IllegalArgumentException("choice should be from NUMBERSTYLE to STANDARDCURRENCYSTYLE");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v9, resolved type: android.icu.text.RuleBasedNumberFormat */
    /* JADX WARN: Multi-variable type inference failed */
    static NumberFormat createInstance(ULocale uLocale, int i) {
        DecimalFormat decimalFormat;
        String currencyPattern;
        String pattern = getPattern(uLocale, i);
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(uLocale);
        if ((i == 1 || i == 5 || i == 7 || i == 8 || i == 9) && (currencyPattern = decimalFormatSymbols.getCurrencyPattern()) != null) {
            pattern = currencyPattern;
        }
        if (i == 5) {
            pattern = pattern.replace("¤", doubleCurrencyStr);
        }
        NumberingSystem instance = NumberingSystem.getInstance(uLocale);
        if (instance == null) {
            return null;
        }
        int i2 = 4;
        if (instance == null || !instance.isAlgorithmic()) {
            DecimalFormat decimalFormat2 = new DecimalFormat(pattern, decimalFormatSymbols, i);
            if (i == 4) {
                decimalFormat2.setMaximumFractionDigits(0);
                decimalFormat2.setDecimalSeparatorAlwaysShown(false);
                decimalFormat2.setParseIntegerOnly(true);
            }
            if (i == 8) {
                decimalFormat2.setCurrencyUsage(Currency.CurrencyUsage.CASH);
            }
            if (i == 6) {
                decimalFormat2.setCurrencyPluralInfo(CurrencyPluralInfo.getInstance(uLocale));
            }
            decimalFormat = decimalFormat2;
        } else {
            String description = instance.getDescription();
            int indexOf = description.indexOf("/");
            int lastIndexOf = description.lastIndexOf("/");
            if (lastIndexOf > indexOf) {
                String substring = description.substring(0, indexOf);
                String substring2 = description.substring(indexOf + 1, lastIndexOf);
                description = description.substring(lastIndexOf + 1);
                ULocale uLocale2 = new ULocale(substring);
                if (substring2.equals("SpelloutRules")) {
                    i2 = 1;
                }
                uLocale = uLocale2;
            }
            RuleBasedNumberFormat ruleBasedNumberFormat = new RuleBasedNumberFormat(uLocale, i2);
            ruleBasedNumberFormat.setDefaultRuleSet(description);
            decimalFormat = ruleBasedNumberFormat;
        }
        decimalFormat.setLocale(decimalFormatSymbols.getLocale(ULocale.VALID_LOCALE), decimalFormatSymbols.getLocale(ULocale.ACTUAL_LOCALE));
        return decimalFormat;
    }

    protected static String getPattern(ULocale uLocale, int i) {
        return getPatternForStyle(uLocale, i);
    }

    public static String getPatternForStyle(ULocale uLocale, int i) {
        return getPatternForStyleAndNumberingSystem(uLocale, NumberingSystem.getInstance(uLocale).getName(), i);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (r5.equals("account") != false) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getPatternForStyleAndNumberingSystem(android.icu.util.ULocale r3, java.lang.String r4, int r5) {
        /*
        // Method dump skipped, instructions count: 122
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.NumberFormat.getPatternForStyleAndNumberingSystem(android.icu.util.ULocale, java.lang.String, int):java.lang.String");
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        int i = this.maximumIntegerDigits;
        byte b = Byte.MAX_VALUE;
        this.maxIntegerDigits = i > 127 ? Byte.MAX_VALUE : (byte) i;
        int i2 = this.minimumIntegerDigits;
        this.minIntegerDigits = i2 > 127 ? Byte.MAX_VALUE : (byte) i2;
        int i3 = this.maximumFractionDigits;
        this.maxFractionDigits = i3 > 127 ? Byte.MAX_VALUE : (byte) i3;
        int i4 = this.minimumFractionDigits;
        if (i4 <= 127) {
            b = (byte) i4;
        }
        this.minFractionDigits = b;
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    static {
        new String(doubleCurrencySign);
        throw null;
    }

    public static class Field extends Format.Field {
        public static final Field CURRENCY = new Field("currency");
        public static final Field DECIMAL_SEPARATOR = new Field("decimal separator");
        public static final Field EXPONENT = new Field("exponent");
        public static final Field EXPONENT_SIGN = new Field("exponent sign");
        public static final Field EXPONENT_SYMBOL = new Field("exponent symbol");
        public static final Field FRACTION = new Field("fraction");
        public static final Field GROUPING_SEPARATOR = new Field("grouping separator");
        public static final Field INTEGER = new Field("integer");
        public static final Field PERCENT = new Field("percent");
        public static final Field PERMILLE = new Field("per mille");
        public static final Field SIGN = new Field("sign");
        static final long serialVersionUID = -4516273749929385842L;

        protected Field(String str) {
            super(str);
        }

        /* access modifiers changed from: protected */
        @Override // java.text.AttributedCharacterIterator.Attribute
        public Object readResolve() {
            if (getName().equals(INTEGER.getName())) {
                return INTEGER;
            }
            if (getName().equals(FRACTION.getName())) {
                return FRACTION;
            }
            if (getName().equals(EXPONENT.getName())) {
                return EXPONENT;
            }
            if (getName().equals(EXPONENT_SIGN.getName())) {
                return EXPONENT_SIGN;
            }
            if (getName().equals(EXPONENT_SYMBOL.getName())) {
                return EXPONENT_SYMBOL;
            }
            if (getName().equals(CURRENCY.getName())) {
                return CURRENCY;
            }
            if (getName().equals(DECIMAL_SEPARATOR.getName())) {
                return DECIMAL_SEPARATOR;
            }
            if (getName().equals(GROUPING_SEPARATOR.getName())) {
                return GROUPING_SEPARATOR;
            }
            if (getName().equals(PERCENT.getName())) {
                return PERCENT;
            }
            if (getName().equals(PERMILLE.getName())) {
                return PERMILLE;
            }
            if (getName().equals(SIGN.getName())) {
                return SIGN;
            }
            throw new InvalidObjectException("An invalid object.");
        }
    }
}
