package java.text;

import android.icu.text.DecimalFormat_ICU58_Android;
import android.icu.text.NumberFormat;
import java.io.IOException;
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
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import libcore.icu.LocaleData;

public class DecimalFormat extends NumberFormat {
    static final int DOUBLE_FRACTION_DIGITS = 340;
    static final int DOUBLE_INTEGER_DIGITS = 309;
    static final int MAXIMUM_FRACTION_DIGITS = Integer.MAX_VALUE;
    static final int MAXIMUM_INTEGER_DIGITS = Integer.MAX_VALUE;
    static final int currentSerialVersion = 4;
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("positivePrefix", String.class), new ObjectStreamField("positiveSuffix", String.class), new ObjectStreamField("negativePrefix", String.class), new ObjectStreamField("negativeSuffix", String.class), new ObjectStreamField("posPrefixPattern", String.class), new ObjectStreamField("posSuffixPattern", String.class), new ObjectStreamField("negPrefixPattern", String.class), new ObjectStreamField("negSuffixPattern", String.class), new ObjectStreamField("multiplier", Integer.TYPE), new ObjectStreamField("groupingSize", Byte.TYPE), new ObjectStreamField("groupingUsed", Boolean.TYPE), new ObjectStreamField("decimalSeparatorAlwaysShown", Boolean.TYPE), new ObjectStreamField("parseBigDecimal", Boolean.TYPE), new ObjectStreamField("roundingMode", RoundingMode.class), new ObjectStreamField("symbols", DecimalFormatSymbols.class), new ObjectStreamField("useExponentialNotation", Boolean.TYPE), new ObjectStreamField("minExponentDigits", Byte.TYPE), new ObjectStreamField("maximumIntegerDigits", Integer.TYPE), new ObjectStreamField("minimumIntegerDigits", Integer.TYPE), new ObjectStreamField("maximumFractionDigits", Integer.TYPE), new ObjectStreamField("minimumFractionDigits", Integer.TYPE), new ObjectStreamField("serialVersionOnStream", Integer.TYPE)};
    static final long serialVersionUID = 864413376551465018L;
    private transient DecimalFormat_ICU58_Android icuDecimalFormat;
    private int maximumFractionDigits;
    private int maximumIntegerDigits;
    private int minimumFractionDigits;
    private int minimumIntegerDigits;
    private RoundingMode roundingMode;
    private DecimalFormatSymbols symbols;

    public DecimalFormat() {
        this.symbols = null;
        this.roundingMode = RoundingMode.HALF_EVEN;
        Locale def = Locale.getDefault(Locale.Category.FORMAT);
        String pattern = LocaleData.get(def).numberPattern;
        this.symbols = DecimalFormatSymbols.getInstance(def);
        initPattern(pattern);
    }

    public DecimalFormat(String pattern) {
        this.symbols = null;
        this.roundingMode = RoundingMode.HALF_EVEN;
        this.symbols = DecimalFormatSymbols.getInstance(Locale.getDefault(Locale.Category.FORMAT));
        initPattern(pattern);
    }

    public DecimalFormat(String pattern, DecimalFormatSymbols symbols2) {
        this.symbols = null;
        this.roundingMode = RoundingMode.HALF_EVEN;
        this.symbols = (DecimalFormatSymbols) symbols2.clone();
        initPattern(pattern);
    }

    private void initPattern(String pattern) {
        this.icuDecimalFormat = new DecimalFormat_ICU58_Android(pattern, this.symbols.getIcuDecimalFormatSymbols());
        updateFieldsFromIcu();
    }

    private void updateFieldsFromIcu() {
        if (this.icuDecimalFormat.getMaximumIntegerDigits() == DOUBLE_INTEGER_DIGITS) {
            this.icuDecimalFormat.setMaximumIntegerDigits(2000000000);
        }
        this.maximumIntegerDigits = this.icuDecimalFormat.getMaximumIntegerDigits();
        this.minimumIntegerDigits = this.icuDecimalFormat.getMinimumIntegerDigits();
        this.maximumFractionDigits = this.icuDecimalFormat.getMaximumFractionDigits();
        this.minimumFractionDigits = this.icuDecimalFormat.getMinimumFractionDigits();
    }

    private static FieldPosition getIcuFieldPosition(FieldPosition fp) {
        NumberFormat.Field attribute;
        Format.Field fieldAttribute = fp.getFieldAttribute();
        if (fieldAttribute == null) {
            return fp;
        }
        if (fieldAttribute == NumberFormat.Field.INTEGER) {
            attribute = NumberFormat.Field.INTEGER;
        } else if (fieldAttribute == NumberFormat.Field.FRACTION) {
            attribute = NumberFormat.Field.FRACTION;
        } else if (fieldAttribute == NumberFormat.Field.DECIMAL_SEPARATOR) {
            attribute = NumberFormat.Field.DECIMAL_SEPARATOR;
        } else if (fieldAttribute == NumberFormat.Field.EXPONENT_SYMBOL) {
            attribute = NumberFormat.Field.EXPONENT_SYMBOL;
        } else if (fieldAttribute == NumberFormat.Field.EXPONENT_SIGN) {
            attribute = NumberFormat.Field.EXPONENT_SIGN;
        } else if (fieldAttribute == NumberFormat.Field.EXPONENT) {
            attribute = NumberFormat.Field.EXPONENT;
        } else if (fieldAttribute == NumberFormat.Field.GROUPING_SEPARATOR) {
            attribute = NumberFormat.Field.GROUPING_SEPARATOR;
        } else if (fieldAttribute == NumberFormat.Field.CURRENCY) {
            attribute = NumberFormat.Field.CURRENCY;
        } else if (fieldAttribute == NumberFormat.Field.PERCENT) {
            attribute = NumberFormat.Field.PERCENT;
        } else if (fieldAttribute == NumberFormat.Field.PERMILLE) {
            attribute = NumberFormat.Field.PERMILLE;
        } else if (fieldAttribute == NumberFormat.Field.SIGN) {
            attribute = NumberFormat.Field.SIGN;
        } else {
            throw new IllegalArgumentException("Unexpected field position attribute type.");
        }
        FieldPosition icuFieldPosition = new FieldPosition(attribute);
        icuFieldPosition.setBeginIndex(fp.getBeginIndex());
        icuFieldPosition.setEndIndex(fp.getEndIndex());
        return icuFieldPosition;
    }

    private static NumberFormat.Field toJavaFieldAttribute(AttributedCharacterIterator.Attribute icuAttribute) {
        String name = icuAttribute.getName();
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
    public final StringBuffer format(Object number, StringBuffer toAppendTo, FieldPosition pos) {
        if ((number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte) || (number instanceof AtomicInteger) || (number instanceof AtomicLong) || ((number instanceof BigInteger) && ((BigInteger) number).bitLength() < 64)) {
            return format(((Number) number).longValue(), toAppendTo, pos);
        }
        if (number instanceof BigDecimal) {
            return format((BigDecimal) number, toAppendTo, pos);
        }
        if (number instanceof BigInteger) {
            return format((BigInteger) number, toAppendTo, pos);
        }
        if (number instanceof Number) {
            return format(((Number) number).doubleValue(), toAppendTo, pos);
        }
        throw new IllegalArgumentException("Cannot format given Object as a Number");
    }

    @Override // java.text.NumberFormat
    public StringBuffer format(double number, StringBuffer result, FieldPosition fieldPosition) {
        FieldPosition icuFieldPosition = getIcuFieldPosition(fieldPosition);
        this.icuDecimalFormat.format(number, result, icuFieldPosition);
        fieldPosition.setBeginIndex(icuFieldPosition.getBeginIndex());
        fieldPosition.setEndIndex(icuFieldPosition.getEndIndex());
        return result;
    }

    @Override // java.text.NumberFormat
    public StringBuffer format(long number, StringBuffer result, FieldPosition fieldPosition) {
        FieldPosition icuFieldPosition = getIcuFieldPosition(fieldPosition);
        this.icuDecimalFormat.format(number, result, icuFieldPosition);
        fieldPosition.setBeginIndex(icuFieldPosition.getBeginIndex());
        fieldPosition.setEndIndex(icuFieldPosition.getEndIndex());
        return result;
    }

    private StringBuffer format(BigDecimal number, StringBuffer result, FieldPosition fieldPosition) {
        FieldPosition icuFieldPosition = getIcuFieldPosition(fieldPosition);
        this.icuDecimalFormat.format(number, result, fieldPosition);
        fieldPosition.setBeginIndex(icuFieldPosition.getBeginIndex());
        fieldPosition.setEndIndex(icuFieldPosition.getEndIndex());
        return result;
    }

    private StringBuffer format(BigInteger number, StringBuffer result, FieldPosition fieldPosition) {
        FieldPosition icuFieldPosition = getIcuFieldPosition(fieldPosition);
        this.icuDecimalFormat.format(number, result, fieldPosition);
        fieldPosition.setBeginIndex(icuFieldPosition.getBeginIndex());
        fieldPosition.setEndIndex(icuFieldPosition.getEndIndex());
        return result;
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        if (obj != null) {
            AttributedCharacterIterator original = this.icuDecimalFormat.formatToCharacterIterator(obj);
            StringBuilder textBuilder = new StringBuilder(original.getEndIndex() - original.getBeginIndex());
            for (int i = original.getBeginIndex(); i < original.getEndIndex(); i++) {
                textBuilder.append(original.current());
                original.next();
            }
            AttributedString result = new AttributedString(textBuilder.toString());
            for (int i2 = original.getBeginIndex(); i2 < original.getEndIndex(); i2++) {
                original.setIndex(i2);
                for (AttributedCharacterIterator.Attribute attribute : original.getAttributes().keySet()) {
                    int start = original.getRunStart();
                    int end = original.getRunLimit();
                    NumberFormat.Field javaAttr = toJavaFieldAttribute(attribute);
                    result.addAttribute(javaAttr, javaAttr, start, end);
                }
            }
            return result.getIterator();
        }
        throw new NullPointerException("object == null");
    }

    @Override // java.text.NumberFormat
    public Number parse(String text, ParsePosition pos) {
        Number number;
        if (pos.index < 0 || pos.index >= text.length() || (number = this.icuDecimalFormat.parse(text, pos)) == null) {
            return null;
        }
        if (isParseBigDecimal()) {
            if (number instanceof Long) {
                return new BigDecimal(number.longValue());
            }
            if ((number instanceof Double) && !((Double) number).isInfinite() && !((Double) number).isNaN()) {
                return new BigDecimal(number.toString());
            }
            if ((number instanceof Double) && (((Double) number).isNaN() || ((Double) number).isInfinite())) {
                return number;
            }
            if (number instanceof android.icu.math.BigDecimal) {
                return ((android.icu.math.BigDecimal) number).toBigDecimal();
            }
        }
        if ((number instanceof android.icu.math.BigDecimal) || (number instanceof BigInteger)) {
            return Double.valueOf(number.doubleValue());
        }
        if (!isParseIntegerOnly() || !number.equals(new Double(-0.0d))) {
            return number;
        }
        return 0L;
    }

    public DecimalFormatSymbols getDecimalFormatSymbols() {
        return DecimalFormatSymbols.fromIcuInstance(this.icuDecimalFormat.getDecimalFormatSymbols());
    }

    public void setDecimalFormatSymbols(DecimalFormatSymbols newSymbols) {
        try {
            this.symbols = (DecimalFormatSymbols) newSymbols.clone();
            this.icuDecimalFormat.setDecimalFormatSymbols(this.symbols.getIcuDecimalFormatSymbols());
        } catch (Exception e) {
        }
    }

    public String getPositivePrefix() {
        return this.icuDecimalFormat.getPositivePrefix();
    }

    public void setPositivePrefix(String newValue) {
        this.icuDecimalFormat.setPositivePrefix(newValue);
    }

    public String getNegativePrefix() {
        return this.icuDecimalFormat.getNegativePrefix();
    }

    public void setNegativePrefix(String newValue) {
        this.icuDecimalFormat.setNegativePrefix(newValue);
    }

    public String getPositiveSuffix() {
        return this.icuDecimalFormat.getPositiveSuffix();
    }

    public void setPositiveSuffix(String newValue) {
        this.icuDecimalFormat.setPositiveSuffix(newValue);
    }

    public String getNegativeSuffix() {
        return this.icuDecimalFormat.getNegativeSuffix();
    }

    public void setNegativeSuffix(String newValue) {
        this.icuDecimalFormat.setNegativeSuffix(newValue);
    }

    public int getMultiplier() {
        return this.icuDecimalFormat.getMultiplier();
    }

    public void setMultiplier(int newValue) {
        this.icuDecimalFormat.setMultiplier(newValue);
    }

    @Override // java.text.NumberFormat
    public void setGroupingUsed(boolean newValue) {
        this.icuDecimalFormat.setGroupingUsed(newValue);
    }

    @Override // java.text.NumberFormat
    public boolean isGroupingUsed() {
        return this.icuDecimalFormat.isGroupingUsed();
    }

    public int getGroupingSize() {
        return this.icuDecimalFormat.getGroupingSize();
    }

    public void setGroupingSize(int newValue) {
        this.icuDecimalFormat.setGroupingSize(newValue);
    }

    public boolean isDecimalSeparatorAlwaysShown() {
        return this.icuDecimalFormat.isDecimalSeparatorAlwaysShown();
    }

    public void setDecimalSeparatorAlwaysShown(boolean newValue) {
        this.icuDecimalFormat.setDecimalSeparatorAlwaysShown(newValue);
    }

    public boolean isParseBigDecimal() {
        return this.icuDecimalFormat.isParseBigDecimal();
    }

    public void setParseBigDecimal(boolean newValue) {
        this.icuDecimalFormat.setParseBigDecimal(newValue);
    }

    @Override // java.text.NumberFormat
    public boolean isParseIntegerOnly() {
        return this.icuDecimalFormat.isParseIntegerOnly();
    }

    @Override // java.text.NumberFormat
    public void setParseIntegerOnly(boolean value) {
        super.setParseIntegerOnly(value);
        this.icuDecimalFormat.setParseIntegerOnly(value);
    }

    @Override // java.text.Format, java.text.NumberFormat
    public Object clone() {
        try {
            DecimalFormat other = (DecimalFormat) super.clone();
            other.icuDecimalFormat = (DecimalFormat_ICU58_Android) this.icuDecimalFormat.clone();
            other.symbols = (DecimalFormatSymbols) this.symbols.clone();
            return other;
        } catch (Exception e) {
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
        DecimalFormat other = (DecimalFormat) obj;
        if (!this.icuDecimalFormat.equals(other.icuDecimalFormat) || !compareIcuRoundingIncrement(other.icuDecimalFormat)) {
            return false;
        }
        return true;
    }

    private boolean compareIcuRoundingIncrement(DecimalFormat_ICU58_Android other) {
        BigDecimal increment = this.icuDecimalFormat.getRoundingIncrement();
        return increment != null ? other.getRoundingIncrement() != null && increment.equals(other.getRoundingIncrement()) : other.getRoundingIncrement() == null;
    }

    @Override // java.text.NumberFormat
    public int hashCode() {
        return (super.hashCode() * 37) + getPositivePrefix().hashCode();
    }

    public String toPattern() {
        return this.icuDecimalFormat.toPattern();
    }

    public String toLocalizedPattern() {
        return this.icuDecimalFormat.toLocalizedPattern();
    }

    public void applyPattern(String pattern) {
        this.icuDecimalFormat.applyPattern(pattern);
        updateFieldsFromIcu();
    }

    public void applyLocalizedPattern(String pattern) {
        this.icuDecimalFormat.applyLocalizedPattern(pattern);
        updateFieldsFromIcu();
    }

    @Override // java.text.NumberFormat
    public void setMaximumIntegerDigits(int newValue) {
        this.maximumIntegerDigits = Math.min(Math.max(0, newValue), Integer.MAX_VALUE);
        int i = this.maximumIntegerDigits;
        if (i > DOUBLE_INTEGER_DIGITS) {
            i = DOUBLE_INTEGER_DIGITS;
        }
        super.setMaximumIntegerDigits(i);
        int i2 = this.minimumIntegerDigits;
        int i3 = this.maximumIntegerDigits;
        if (i2 > i3) {
            this.minimumIntegerDigits = i3;
            int i4 = this.minimumIntegerDigits;
            if (i4 > DOUBLE_INTEGER_DIGITS) {
                i4 = DOUBLE_INTEGER_DIGITS;
            }
            super.setMinimumIntegerDigits(i4);
        }
        this.icuDecimalFormat.setMaximumIntegerDigits(getMaximumIntegerDigits());
    }

    @Override // java.text.NumberFormat
    public void setMinimumIntegerDigits(int newValue) {
        this.minimumIntegerDigits = Math.min(Math.max(0, newValue), Integer.MAX_VALUE);
        int i = this.minimumIntegerDigits;
        if (i > DOUBLE_INTEGER_DIGITS) {
            i = DOUBLE_INTEGER_DIGITS;
        }
        super.setMinimumIntegerDigits(i);
        int i2 = this.minimumIntegerDigits;
        if (i2 > this.maximumIntegerDigits) {
            this.maximumIntegerDigits = i2;
            int i3 = this.maximumIntegerDigits;
            if (i3 > DOUBLE_INTEGER_DIGITS) {
                i3 = DOUBLE_INTEGER_DIGITS;
            }
            super.setMaximumIntegerDigits(i3);
        }
        this.icuDecimalFormat.setMinimumIntegerDigits(getMinimumIntegerDigits());
    }

    @Override // java.text.NumberFormat
    public void setMaximumFractionDigits(int newValue) {
        this.maximumFractionDigits = Math.min(Math.max(0, newValue), Integer.MAX_VALUE);
        int i = this.maximumFractionDigits;
        if (i > DOUBLE_FRACTION_DIGITS) {
            i = DOUBLE_FRACTION_DIGITS;
        }
        super.setMaximumFractionDigits(i);
        int i2 = this.minimumFractionDigits;
        int i3 = this.maximumFractionDigits;
        if (i2 > i3) {
            this.minimumFractionDigits = i3;
            int i4 = this.minimumFractionDigits;
            if (i4 > DOUBLE_FRACTION_DIGITS) {
                i4 = DOUBLE_FRACTION_DIGITS;
            }
            super.setMinimumFractionDigits(i4);
        }
        this.icuDecimalFormat.setMaximumFractionDigits(getMaximumFractionDigits());
    }

    @Override // java.text.NumberFormat
    public void setMinimumFractionDigits(int newValue) {
        this.minimumFractionDigits = Math.min(Math.max(0, newValue), Integer.MAX_VALUE);
        int i = this.minimumFractionDigits;
        if (i > DOUBLE_FRACTION_DIGITS) {
            i = DOUBLE_FRACTION_DIGITS;
        }
        super.setMinimumFractionDigits(i);
        int i2 = this.minimumFractionDigits;
        if (i2 > this.maximumFractionDigits) {
            this.maximumFractionDigits = i2;
            int i3 = this.maximumFractionDigits;
            if (i3 > DOUBLE_FRACTION_DIGITS) {
                i3 = DOUBLE_FRACTION_DIGITS;
            }
            super.setMaximumFractionDigits(i3);
        }
        this.icuDecimalFormat.setMinimumFractionDigits(getMinimumFractionDigits());
    }

    @Override // java.text.NumberFormat
    public int getMaximumIntegerDigits() {
        return this.maximumIntegerDigits;
    }

    @Override // java.text.NumberFormat
    public int getMinimumIntegerDigits() {
        return this.minimumIntegerDigits;
    }

    @Override // java.text.NumberFormat
    public int getMaximumFractionDigits() {
        return this.maximumFractionDigits;
    }

    @Override // java.text.NumberFormat
    public int getMinimumFractionDigits() {
        return this.minimumFractionDigits;
    }

    @Override // java.text.NumberFormat
    public Currency getCurrency() {
        return this.symbols.getCurrency();
    }

    @Override // java.text.NumberFormat
    public void setCurrency(Currency currency) {
        if (currency != this.symbols.getCurrency() || !currency.getSymbol().equals(this.symbols.getCurrencySymbol())) {
            this.symbols.setCurrency(currency);
            this.icuDecimalFormat.setDecimalFormatSymbols(this.symbols.getIcuDecimalFormatSymbols());
            this.icuDecimalFormat.setMinimumFractionDigits(this.minimumFractionDigits);
            this.icuDecimalFormat.setMaximumFractionDigits(this.maximumFractionDigits);
        }
    }

    @Override // java.text.NumberFormat
    public RoundingMode getRoundingMode() {
        return this.roundingMode;
    }

    private static int convertRoundingMode(RoundingMode rm) {
        switch (rm) {
            case UP:
                return 0;
            case DOWN:
                return 1;
            case CEILING:
                return 2;
            case FLOOR:
                return 3;
            case HALF_UP:
                return 4;
            case HALF_DOWN:
                return 5;
            case HALF_EVEN:
                return 6;
            case UNNECESSARY:
                return 7;
            default:
                throw new IllegalArgumentException("Invalid rounding mode specified");
        }
    }

    @Override // java.text.NumberFormat
    public void setRoundingMode(RoundingMode roundingMode2) {
        if (roundingMode2 != null) {
            this.roundingMode = roundingMode2;
            this.icuDecimalFormat.setRoundingMode(convertRoundingMode(roundingMode2));
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public void adjustForCurrencyDefaultFractionDigits() {
        int digits;
        Currency currency = this.symbols.getCurrency();
        if (currency == null) {
            try {
                currency = Currency.getInstance(this.symbols.getInternationalCurrencySymbol());
            } catch (IllegalArgumentException e) {
            }
        }
        if (currency != null && (digits = currency.getDefaultFractionDigits()) != -1) {
            int oldMinDigits = getMinimumFractionDigits();
            if (oldMinDigits == getMaximumFractionDigits()) {
                setMinimumFractionDigits(digits);
                setMaximumFractionDigits(digits);
                return;
            }
            setMinimumFractionDigits(Math.min(digits, oldMinDigits));
            setMaximumFractionDigits(digits);
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException, ClassNotFoundException {
        ObjectOutputStream.PutField fields = stream.putFields();
        fields.put("positivePrefix", this.icuDecimalFormat.getPositivePrefix());
        fields.put("positiveSuffix", this.icuDecimalFormat.getPositiveSuffix());
        fields.put("negativePrefix", this.icuDecimalFormat.getNegativePrefix());
        fields.put("negativeSuffix", this.icuDecimalFormat.getNegativeSuffix());
        String str = null;
        fields.put("posPrefixPattern", str);
        fields.put("posSuffixPattern", str);
        fields.put("negPrefixPattern", str);
        fields.put("negSuffixPattern", str);
        fields.put("multiplier", this.icuDecimalFormat.getMultiplier());
        fields.put("groupingSize", (byte) this.icuDecimalFormat.getGroupingSize());
        fields.put("groupingUsed", this.icuDecimalFormat.isGroupingUsed());
        fields.put("decimalSeparatorAlwaysShown", this.icuDecimalFormat.isDecimalSeparatorAlwaysShown());
        fields.put("parseBigDecimal", this.icuDecimalFormat.isParseBigDecimal());
        fields.put("roundingMode", this.roundingMode);
        fields.put("symbols", this.symbols);
        fields.put("useExponentialNotation", false);
        fields.put("minExponentDigits", (byte) 0);
        fields.put("maximumIntegerDigits", this.icuDecimalFormat.getMaximumIntegerDigits());
        fields.put("minimumIntegerDigits", this.icuDecimalFormat.getMinimumIntegerDigits());
        fields.put("maximumFractionDigits", this.icuDecimalFormat.getMaximumFractionDigits());
        fields.put("minimumFractionDigits", this.icuDecimalFormat.getMinimumFractionDigits());
        fields.put("serialVersionOnStream", 4);
        stream.writeFields();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = stream.readFields();
        this.symbols = (DecimalFormatSymbols) fields.get("symbols", (Object) null);
        initPattern("#");
        String positivePrefix = (String) fields.get("positivePrefix", "");
        if (!Objects.equals(positivePrefix, this.icuDecimalFormat.getPositivePrefix())) {
            this.icuDecimalFormat.setPositivePrefix(positivePrefix);
        }
        String positiveSuffix = (String) fields.get("positiveSuffix", "");
        if (!Objects.equals(positiveSuffix, this.icuDecimalFormat.getPositiveSuffix())) {
            this.icuDecimalFormat.setPositiveSuffix(positiveSuffix);
        }
        String negativePrefix = (String) fields.get("negativePrefix", "-");
        if (!Objects.equals(negativePrefix, this.icuDecimalFormat.getNegativePrefix())) {
            this.icuDecimalFormat.setNegativePrefix(negativePrefix);
        }
        String negativeSuffix = (String) fields.get("negativeSuffix", "");
        if (!Objects.equals(negativeSuffix, this.icuDecimalFormat.getNegativeSuffix())) {
            this.icuDecimalFormat.setNegativeSuffix(negativeSuffix);
        }
        int multiplier = fields.get("multiplier", 1);
        if (multiplier != this.icuDecimalFormat.getMultiplier()) {
            this.icuDecimalFormat.setMultiplier(multiplier);
        }
        boolean groupingUsed = fields.get("groupingUsed", true);
        if (groupingUsed != this.icuDecimalFormat.isGroupingUsed()) {
            this.icuDecimalFormat.setGroupingUsed(groupingUsed);
        }
        int groupingSize = fields.get("groupingSize", (byte) 3);
        if (groupingSize != this.icuDecimalFormat.getGroupingSize()) {
            this.icuDecimalFormat.setGroupingSize(groupingSize);
        }
        boolean decimalSeparatorAlwaysShown = fields.get("decimalSeparatorAlwaysShown", false);
        if (decimalSeparatorAlwaysShown != this.icuDecimalFormat.isDecimalSeparatorAlwaysShown()) {
            this.icuDecimalFormat.setDecimalSeparatorAlwaysShown(decimalSeparatorAlwaysShown);
        }
        RoundingMode roundingMode2 = (RoundingMode) fields.get("roundingMode", RoundingMode.HALF_EVEN);
        if (convertRoundingMode(roundingMode2) != this.icuDecimalFormat.getRoundingMode()) {
            setRoundingMode(roundingMode2);
        }
        int maximumIntegerDigits2 = fields.get("maximumIntegerDigits", DOUBLE_INTEGER_DIGITS);
        if (maximumIntegerDigits2 != this.icuDecimalFormat.getMaximumIntegerDigits()) {
            this.icuDecimalFormat.setMaximumIntegerDigits(maximumIntegerDigits2);
        }
        int minimumIntegerDigits2 = fields.get("minimumIntegerDigits", DOUBLE_INTEGER_DIGITS);
        if (minimumIntegerDigits2 != this.icuDecimalFormat.getMinimumIntegerDigits()) {
            this.icuDecimalFormat.setMinimumIntegerDigits(minimumIntegerDigits2);
        }
        int maximumFractionDigits2 = fields.get("maximumFractionDigits", DOUBLE_FRACTION_DIGITS);
        if (maximumFractionDigits2 != this.icuDecimalFormat.getMaximumFractionDigits()) {
            this.icuDecimalFormat.setMaximumFractionDigits(maximumFractionDigits2);
        }
        int minimumFractionDigits2 = fields.get("minimumFractionDigits", DOUBLE_FRACTION_DIGITS);
        if (minimumFractionDigits2 != this.icuDecimalFormat.getMinimumFractionDigits()) {
            this.icuDecimalFormat.setMinimumFractionDigits(minimumFractionDigits2);
        }
        boolean parseBigDecimal = fields.get("parseBigDecimal", true);
        if (parseBigDecimal != this.icuDecimalFormat.isParseBigDecimal()) {
            this.icuDecimalFormat.setParseBigDecimal(parseBigDecimal);
        }
        updateFieldsFromIcu();
        if (fields.get("serialVersionOnStream", 0) < 3) {
            setMaximumIntegerDigits(super.getMaximumIntegerDigits());
            setMinimumIntegerDigits(super.getMinimumIntegerDigits());
            setMaximumFractionDigits(super.getMaximumFractionDigits());
            setMinimumFractionDigits(super.getMinimumFractionDigits());
        }
    }
}
