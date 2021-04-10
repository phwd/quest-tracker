package java.text;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.text.Format;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import libcore.icu.LocaleData;

public abstract class NumberFormat extends Format {
    static final long serialVersionUID = -2308460125733713944L;
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
    private int serialVersionOnStream = 1;

    public abstract StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract StringBuffer format(long j, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract Number parse(String str, ParsePosition parsePosition);

    protected NumberFormat() {
    }

    @Override // java.text.Format
    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if ((obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte) || (obj instanceof AtomicInteger) || (obj instanceof AtomicLong) || ((obj instanceof BigInteger) && ((BigInteger) obj).bitLength() < 64)) {
            return format(((Number) obj).longValue(), stringBuffer, fieldPosition);
        }
        if (obj instanceof Number) {
            return format(((Number) obj).doubleValue(), stringBuffer, fieldPosition);
        }
        throw new IllegalArgumentException("Cannot format given Object as a Number");
    }

    public void setParseIntegerOnly(boolean z) {
        this.parseIntegerOnly = z;
    }

    public static NumberFormat getInstance(Locale locale) {
        return getInstance(locale, 0);
    }

    public static NumberFormat getIntegerInstance(Locale locale) {
        return getInstance(locale, 3);
    }

    public static NumberFormat getCurrencyInstance(Locale locale) {
        return getInstance(locale, 1);
    }

    public static NumberFormat getPercentInstance(Locale locale) {
        return getInstance(locale, 2);
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
        return this.maximumIntegerDigits == numberFormat.maximumIntegerDigits && this.minimumIntegerDigits == numberFormat.minimumIntegerDigits && this.maximumFractionDigits == numberFormat.maximumFractionDigits && this.minimumFractionDigits == numberFormat.minimumFractionDigits && this.groupingUsed == numberFormat.groupingUsed && this.parseIntegerOnly == numberFormat.parseIntegerOnly;
    }

    @Override // java.text.Format
    public Object clone() {
        return (NumberFormat) super.clone();
    }

    public void setGroupingUsed(boolean z) {
        this.groupingUsed = z;
    }

    public void setMaximumIntegerDigits(int i) {
        this.maximumIntegerDigits = Math.max(0, i);
        int i2 = this.minimumIntegerDigits;
        int i3 = this.maximumIntegerDigits;
        if (i2 > i3) {
            this.minimumIntegerDigits = i3;
        }
    }

    public void setMinimumIntegerDigits(int i) {
        this.minimumIntegerDigits = Math.max(0, i);
        int i2 = this.minimumIntegerDigits;
        if (i2 > this.maximumIntegerDigits) {
            this.maximumIntegerDigits = i2;
        }
    }

    public void setMaximumFractionDigits(int i) {
        this.maximumFractionDigits = Math.max(0, i);
        int i2 = this.maximumFractionDigits;
        if (i2 < this.minimumFractionDigits) {
            this.minimumFractionDigits = i2;
        }
    }

    public void setMinimumFractionDigits(int i) {
        this.minimumFractionDigits = Math.max(0, i);
        int i2 = this.maximumFractionDigits;
        int i3 = this.minimumFractionDigits;
        if (i2 < i3) {
            this.maximumFractionDigits = i3;
        }
    }

    private static NumberFormat getInstance(Locale locale, int i) {
        LocaleData localeData = LocaleData.get(locale);
        DecimalFormat decimalFormat = new DecimalFormat(new String[]{localeData.numberPattern, localeData.currencyPattern, localeData.percentPattern}[i == 3 ? 0 : i], DecimalFormatSymbols.getInstance(locale));
        if (i == 3) {
            decimalFormat.setMaximumFractionDigits(0);
            decimalFormat.setDecimalSeparatorAlwaysShown(false);
            decimalFormat.setParseIntegerOnly(true);
        } else if (i == 1) {
            decimalFormat.adjustForCurrencyDefaultFractionDigits();
        }
        return decimalFormat;
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
        private static final Map instanceMap = new HashMap(11);
        private static final long serialVersionUID = 7494728892700160890L;

        protected Field(String str) {
            super(str);
            if (Field.class == Field.class) {
                instanceMap.put(str, this);
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.text.AttributedCharacterIterator.Attribute
        public Object readResolve() {
            if (Field.class == Field.class) {
                Object obj = instanceMap.get(getName());
                if (obj != null) {
                    return obj;
                }
                throw new InvalidObjectException("unknown attribute name");
            }
            throw new InvalidObjectException("subclass didn't correctly implement readResolve");
        }
    }
}
