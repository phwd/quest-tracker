package java.text;

import android.icu.text.DecimalFormat_ICU58_Android;
import android.icu.text.NumberFormat;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.AttributedCharacterIterator;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import libcore.icu.LocaleData;

public class DecimalFormat extends NumberFormat {
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("positivePrefix", String.class), new ObjectStreamField("positiveSuffix", String.class), new ObjectStreamField("negativePrefix", String.class), new ObjectStreamField("negativeSuffix", String.class), new ObjectStreamField("posPrefixPattern", String.class), new ObjectStreamField("posSuffixPattern", String.class), new ObjectStreamField("negPrefixPattern", String.class), new ObjectStreamField("negSuffixPattern", String.class), new ObjectStreamField("multiplier", Integer.TYPE), new ObjectStreamField("groupingSize", Byte.TYPE), new ObjectStreamField("groupingUsed", Boolean.TYPE), new ObjectStreamField("decimalSeparatorAlwaysShown", Boolean.TYPE), new ObjectStreamField("parseBigDecimal", Boolean.TYPE), new ObjectStreamField("roundingMode", RoundingMode.class), new ObjectStreamField("symbols", DecimalFormatSymbols.class), new ObjectStreamField("useExponentialNotation", Boolean.TYPE), new ObjectStreamField("minExponentDigits", Byte.TYPE), new ObjectStreamField("maximumIntegerDigits", Integer.TYPE), new ObjectStreamField("minimumIntegerDigits", Integer.TYPE), new ObjectStreamField("maximumFractionDigits", Integer.TYPE), new ObjectStreamField("minimumFractionDigits", Integer.TYPE), new ObjectStreamField("serialVersionOnStream", Integer.TYPE)};
    static final long serialVersionUID = 864413376551465018L;
    private transient DecimalFormat_ICU58_Android icuDecimalFormat;
    private int maximumFractionDigits;
    private int maximumIntegerDigits;
    private int minimumFractionDigits;
    private int minimumIntegerDigits;
    private RoundingMode roundingMode = RoundingMode.HALF_EVEN;
    private DecimalFormatSymbols symbols = null;

    public DecimalFormat() {
        Locale locale = Locale.getDefault(Locale.Category.FORMAT);
        String str = LocaleData.get(locale).numberPattern;
        this.symbols = DecimalFormatSymbols.getInstance(locale);
        initPattern(str);
    }

    public DecimalFormat(String str, DecimalFormatSymbols decimalFormatSymbols) {
        this.symbols = (DecimalFormatSymbols) decimalFormatSymbols.clone();
        initPattern(str);
    }

    private void initPattern(String str) {
        this.icuDecimalFormat = new DecimalFormat_ICU58_Android(str, this.symbols.getIcuDecimalFormatSymbols());
        updateFieldsFromIcu();
    }

    private void updateFieldsFromIcu() {
        if (this.icuDecimalFormat.getMaximumIntegerDigits() == 309) {
            this.icuDecimalFormat.setMaximumIntegerDigits(2000000000);
        }
        this.maximumIntegerDigits = this.icuDecimalFormat.getMaximumIntegerDigits();
        this.minimumIntegerDigits = this.icuDecimalFormat.getMinimumIntegerDigits();
        this.maximumFractionDigits = this.icuDecimalFormat.getMaximumFractionDigits();
        this.minimumFractionDigits = this.icuDecimalFormat.getMinimumFractionDigits();
    }

    private static FieldPosition getIcuFieldPosition(FieldPosition fieldPosition) {
        NumberFormat.Field field;
        Format.Field fieldAttribute = fieldPosition.getFieldAttribute();
        if (fieldAttribute == null) {
            return fieldPosition;
        }
        if (fieldAttribute == NumberFormat.Field.INTEGER) {
            field = NumberFormat.Field.INTEGER;
        } else if (fieldAttribute == NumberFormat.Field.FRACTION) {
            field = NumberFormat.Field.FRACTION;
        } else if (fieldAttribute == NumberFormat.Field.DECIMAL_SEPARATOR) {
            field = NumberFormat.Field.DECIMAL_SEPARATOR;
        } else if (fieldAttribute == NumberFormat.Field.EXPONENT_SYMBOL) {
            field = NumberFormat.Field.EXPONENT_SYMBOL;
        } else if (fieldAttribute == NumberFormat.Field.EXPONENT_SIGN) {
            field = NumberFormat.Field.EXPONENT_SIGN;
        } else if (fieldAttribute == NumberFormat.Field.EXPONENT) {
            field = NumberFormat.Field.EXPONENT;
        } else if (fieldAttribute == NumberFormat.Field.GROUPING_SEPARATOR) {
            field = NumberFormat.Field.GROUPING_SEPARATOR;
        } else if (fieldAttribute == NumberFormat.Field.CURRENCY) {
            field = NumberFormat.Field.CURRENCY;
        } else if (fieldAttribute == NumberFormat.Field.PERCENT) {
            field = NumberFormat.Field.PERCENT;
        } else if (fieldAttribute == NumberFormat.Field.PERMILLE) {
            field = NumberFormat.Field.PERMILLE;
        } else if (fieldAttribute == NumberFormat.Field.SIGN) {
            field = NumberFormat.Field.SIGN;
        } else {
            throw new IllegalArgumentException("Unexpected field position attribute type.");
        }
        FieldPosition fieldPosition2 = new FieldPosition(field);
        fieldPosition2.setBeginIndex(fieldPosition.getBeginIndex());
        fieldPosition2.setEndIndex(fieldPosition.getEndIndex());
        return fieldPosition2;
    }

    private static NumberFormat.Field toJavaFieldAttribute(AttributedCharacterIterator.Attribute attribute) {
        String name = attribute.getName();
        if (name.equals(NumberFormat.Field.INTEGER.getName())) {
            return NumberFormat.Field.INTEGER;
        }
        if (name.equals(NumberFormat.Field.CURRENCY.getName())) {
            return NumberFormat.Field.CURRENCY;
        }
        if (name.equals(NumberFormat.Field.DECIMAL_SEPARATOR.getName())) {
            return NumberFormat.Field.DECIMAL_SEPARATOR;
        }
        if (name.equals(NumberFormat.Field.EXPONENT.getName())) {
            return NumberFormat.Field.EXPONENT;
        }
        if (name.equals(NumberFormat.Field.EXPONENT_SIGN.getName())) {
            return NumberFormat.Field.EXPONENT_SIGN;
        }
        if (name.equals(NumberFormat.Field.EXPONENT_SYMBOL.getName())) {
            return NumberFormat.Field.EXPONENT_SYMBOL;
        }
        if (name.equals(NumberFormat.Field.FRACTION.getName())) {
            return NumberFormat.Field.FRACTION;
        }
        if (name.equals(NumberFormat.Field.GROUPING_SEPARATOR.getName())) {
            return NumberFormat.Field.GROUPING_SEPARATOR;
        }
        if (name.equals(NumberFormat.Field.SIGN.getName())) {
            return NumberFormat.Field.SIGN;
        }
        if (name.equals(NumberFormat.Field.PERCENT.getName())) {
            return NumberFormat.Field.PERCENT;
        }
        if (name.equals(NumberFormat.Field.PERMILLE.getName())) {
            return NumberFormat.Field.PERMILLE;
        }
        throw new IllegalArgumentException("Unrecognized attribute: " + name);
    }

    @Override // java.text.Format, java.text.NumberFormat
    public final StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        boolean z;
        if ((obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte) || (obj instanceof AtomicInteger) || (obj instanceof AtomicLong) || (((z = obj instanceof BigInteger)) && ((BigInteger) obj).bitLength() < 64)) {
            return format(((Number) obj).longValue(), stringBuffer, fieldPosition);
        }
        if (obj instanceof BigDecimal) {
            format((BigDecimal) obj, stringBuffer, fieldPosition);
            return stringBuffer;
        } else if (z) {
            format((BigInteger) obj, stringBuffer, fieldPosition);
            return stringBuffer;
        } else if (obj instanceof Number) {
            return format(((Number) obj).doubleValue(), stringBuffer, fieldPosition);
        } else {
            throw new IllegalArgumentException("Cannot format given Object as a Number");
        }
    }

    @Override // java.text.NumberFormat
    public StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        FieldPosition icuFieldPosition = getIcuFieldPosition(fieldPosition);
        this.icuDecimalFormat.format(d, stringBuffer, icuFieldPosition);
        fieldPosition.setBeginIndex(icuFieldPosition.getBeginIndex());
        fieldPosition.setEndIndex(icuFieldPosition.getEndIndex());
        return stringBuffer;
    }

    @Override // java.text.NumberFormat
    public StringBuffer format(long j, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        FieldPosition icuFieldPosition = getIcuFieldPosition(fieldPosition);
        this.icuDecimalFormat.format(j, stringBuffer, icuFieldPosition);
        fieldPosition.setBeginIndex(icuFieldPosition.getBeginIndex());
        fieldPosition.setEndIndex(icuFieldPosition.getEndIndex());
        return stringBuffer;
    }

    private StringBuffer format(BigDecimal bigDecimal, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        FieldPosition icuFieldPosition = getIcuFieldPosition(fieldPosition);
        this.icuDecimalFormat.format(bigDecimal, stringBuffer, fieldPosition);
        fieldPosition.setBeginIndex(icuFieldPosition.getBeginIndex());
        fieldPosition.setEndIndex(icuFieldPosition.getEndIndex());
        return stringBuffer;
    }

    private StringBuffer format(BigInteger bigInteger, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        FieldPosition icuFieldPosition = getIcuFieldPosition(fieldPosition);
        this.icuDecimalFormat.format(bigInteger, stringBuffer, fieldPosition);
        fieldPosition.setBeginIndex(icuFieldPosition.getBeginIndex());
        fieldPosition.setEndIndex(icuFieldPosition.getEndIndex());
        return stringBuffer;
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        if (obj != null) {
            AttributedCharacterIterator formatToCharacterIterator = this.icuDecimalFormat.formatToCharacterIterator(obj);
            StringBuilder sb = new StringBuilder(formatToCharacterIterator.getEndIndex() - formatToCharacterIterator.getBeginIndex());
            for (int beginIndex = formatToCharacterIterator.getBeginIndex(); beginIndex < formatToCharacterIterator.getEndIndex(); beginIndex++) {
                sb.append(formatToCharacterIterator.current());
                formatToCharacterIterator.next();
            }
            AttributedString attributedString = new AttributedString(sb.toString());
            for (int beginIndex2 = formatToCharacterIterator.getBeginIndex(); beginIndex2 < formatToCharacterIterator.getEndIndex(); beginIndex2++) {
                formatToCharacterIterator.setIndex(beginIndex2);
                for (AttributedCharacterIterator.Attribute attribute : formatToCharacterIterator.getAttributes().keySet()) {
                    int runStart = formatToCharacterIterator.getRunStart();
                    int runLimit = formatToCharacterIterator.getRunLimit();
                    NumberFormat.Field javaFieldAttribute = toJavaFieldAttribute(attribute);
                    attributedString.addAttribute(javaFieldAttribute, javaFieldAttribute, runStart, runLimit);
                }
            }
            return attributedString.getIterator();
        }
        throw new NullPointerException("object == null");
    }

    @Override // java.text.NumberFormat
    public Number parse(String str, ParsePosition parsePosition) {
        Number parse;
        int i = parsePosition.index;
        if (i < 0 || i >= str.length() || (parse = this.icuDecimalFormat.parse(str, parsePosition)) == null) {
            return null;
        }
        if (isParseBigDecimal()) {
            if (parse instanceof Long) {
                return new BigDecimal(parse.longValue());
            }
            boolean z = parse instanceof Double;
            if (z) {
                Double d = (Double) parse;
                if (!d.isInfinite() && !d.isNaN()) {
                    return new BigDecimal(parse.toString());
                }
            }
            if (z) {
                Double d2 = (Double) parse;
                if (d2.isNaN() || d2.isInfinite()) {
                    return parse;
                }
            }
            if (parse instanceof android.icu.math.BigDecimal) {
                return ((android.icu.math.BigDecimal) parse).toBigDecimal();
            }
        }
        if ((parse instanceof android.icu.math.BigDecimal) || (parse instanceof BigInteger)) {
            return Double.valueOf(parse.doubleValue());
        }
        if (!isParseIntegerOnly() || !parse.equals(new Double(-0.0d))) {
            return parse;
        }
        return 0L;
    }

    public DecimalFormatSymbols getDecimalFormatSymbols() {
        return DecimalFormatSymbols.fromIcuInstance(this.icuDecimalFormat.getDecimalFormatSymbols());
    }

    public String getPositivePrefix() {
        return this.icuDecimalFormat.getPositivePrefix();
    }

    @Override // java.text.NumberFormat
    public void setGroupingUsed(boolean z) {
        this.icuDecimalFormat.setGroupingUsed(z);
    }

    public boolean isGroupingUsed() {
        return this.icuDecimalFormat.isGroupingUsed();
    }

    public int getGroupingSize() {
        return this.icuDecimalFormat.getGroupingSize();
    }

    public void setDecimalSeparatorAlwaysShown(boolean z) {
        this.icuDecimalFormat.setDecimalSeparatorAlwaysShown(z);
    }

    public boolean isParseBigDecimal() {
        return this.icuDecimalFormat.isParseBigDecimal();
    }

    public boolean isParseIntegerOnly() {
        return this.icuDecimalFormat.isParseIntegerOnly();
    }

    @Override // java.text.NumberFormat
    public void setParseIntegerOnly(boolean z) {
        super.setParseIntegerOnly(z);
        this.icuDecimalFormat.setParseIntegerOnly(z);
    }

    @Override // java.text.Format, java.text.NumberFormat
    public Object clone() {
        try {
            DecimalFormat decimalFormat = (DecimalFormat) super.clone();
            decimalFormat.icuDecimalFormat = (DecimalFormat_ICU58_Android) this.icuDecimalFormat.clone();
            decimalFormat.symbols = (DecimalFormatSymbols) this.symbols.clone();
            return decimalFormat;
        } catch (Exception unused) {
            throw new InternalError();
        }
    }

    @Override // java.text.NumberFormat
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DecimalFormat)) {
            return false;
        }
        DecimalFormat decimalFormat = (DecimalFormat) obj;
        return this.icuDecimalFormat.equals(decimalFormat.icuDecimalFormat) && compareIcuRoundingIncrement(decimalFormat.icuDecimalFormat);
    }

    private boolean compareIcuRoundingIncrement(DecimalFormat_ICU58_Android decimalFormat_ICU58_Android) {
        BigDecimal roundingIncrement = this.icuDecimalFormat.getRoundingIncrement();
        return roundingIncrement != null ? decimalFormat_ICU58_Android.getRoundingIncrement() != null && roundingIncrement.equals(decimalFormat_ICU58_Android.getRoundingIncrement()) : decimalFormat_ICU58_Android.getRoundingIncrement() == null;
    }

    @Override // java.text.NumberFormat
    public int hashCode() {
        return (super.hashCode() * 37) + getPositivePrefix().hashCode();
    }

    public String toPattern() {
        return this.icuDecimalFormat.toPattern();
    }

    @Override // java.text.NumberFormat
    public void setMaximumIntegerDigits(int i) {
        this.maximumIntegerDigits = Math.min(Math.max(0, i), Integer.MAX_VALUE);
        int i2 = this.maximumIntegerDigits;
        if (i2 > 309) {
            i2 = 309;
        }
        super.setMaximumIntegerDigits(i2);
        int i3 = this.minimumIntegerDigits;
        int i4 = this.maximumIntegerDigits;
        if (i3 > i4) {
            this.minimumIntegerDigits = i4;
            int i5 = this.minimumIntegerDigits;
            if (i5 > 309) {
                i5 = 309;
            }
            super.setMinimumIntegerDigits(i5);
        }
        this.icuDecimalFormat.setMaximumIntegerDigits(getMaximumIntegerDigits());
    }

    @Override // java.text.NumberFormat
    public void setMinimumIntegerDigits(int i) {
        this.minimumIntegerDigits = Math.min(Math.max(0, i), Integer.MAX_VALUE);
        int i2 = this.minimumIntegerDigits;
        if (i2 > 309) {
            i2 = 309;
        }
        super.setMinimumIntegerDigits(i2);
        int i3 = this.minimumIntegerDigits;
        if (i3 > this.maximumIntegerDigits) {
            this.maximumIntegerDigits = i3;
            int i4 = this.maximumIntegerDigits;
            if (i4 > 309) {
                i4 = 309;
            }
            super.setMaximumIntegerDigits(i4);
        }
        this.icuDecimalFormat.setMinimumIntegerDigits(getMinimumIntegerDigits());
    }

    @Override // java.text.NumberFormat
    public void setMaximumFractionDigits(int i) {
        this.maximumFractionDigits = Math.min(Math.max(0, i), Integer.MAX_VALUE);
        int i2 = this.maximumFractionDigits;
        if (i2 > 340) {
            i2 = 340;
        }
        super.setMaximumFractionDigits(i2);
        int i3 = this.minimumFractionDigits;
        int i4 = this.maximumFractionDigits;
        if (i3 > i4) {
            this.minimumFractionDigits = i4;
            int i5 = this.minimumFractionDigits;
            if (i5 > 340) {
                i5 = 340;
            }
            super.setMinimumFractionDigits(i5);
        }
        this.icuDecimalFormat.setMaximumFractionDigits(getMaximumFractionDigits());
    }

    @Override // java.text.NumberFormat
    public void setMinimumFractionDigits(int i) {
        this.minimumFractionDigits = Math.min(Math.max(0, i), Integer.MAX_VALUE);
        int i2 = this.minimumFractionDigits;
        if (i2 > 340) {
            i2 = 340;
        }
        super.setMinimumFractionDigits(i2);
        int i3 = this.minimumFractionDigits;
        if (i3 > this.maximumFractionDigits) {
            this.maximumFractionDigits = i3;
            int i4 = this.maximumFractionDigits;
            if (i4 > 340) {
                i4 = 340;
            }
            super.setMaximumFractionDigits(i4);
        }
        this.icuDecimalFormat.setMinimumFractionDigits(getMinimumFractionDigits());
    }

    public int getMaximumIntegerDigits() {
        return this.maximumIntegerDigits;
    }

    public int getMinimumIntegerDigits() {
        return this.minimumIntegerDigits;
    }

    public int getMaximumFractionDigits() {
        return this.maximumFractionDigits;
    }

    public int getMinimumFractionDigits() {
        return this.minimumFractionDigits;
    }

    /* access modifiers changed from: package-private */
    public void adjustForCurrencyDefaultFractionDigits() {
        int defaultFractionDigits;
        Currency currency = this.symbols.getCurrency();
        if (currency == null) {
            try {
                currency = Currency.getInstance(this.symbols.getInternationalCurrencySymbol());
            } catch (IllegalArgumentException unused) {
            }
        }
        if (currency != null && (defaultFractionDigits = currency.getDefaultFractionDigits()) != -1) {
            int minimumFractionDigits2 = getMinimumFractionDigits();
            if (minimumFractionDigits2 == getMaximumFractionDigits()) {
                setMinimumFractionDigits(defaultFractionDigits);
                setMaximumFractionDigits(defaultFractionDigits);
                return;
            }
            setMinimumFractionDigits(Math.min(defaultFractionDigits, minimumFractionDigits2));
            setMaximumFractionDigits(defaultFractionDigits);
        }
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
