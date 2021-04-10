package android.icu.text;

import android.icu.impl.ICUConfig;
import android.icu.impl.PatternProps;
import android.icu.lang.UCharacter;
import android.icu.lang.UProperty;
import android.icu.math.MathContext;
import android.icu.text.NumberFormat;
import android.icu.text.PluralRules;
import android.icu.util.Currency;
import android.icu.util.CurrencyAmount;
import android.icu.util.ULocale;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.ChoiceFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Deprecated
public class DecimalFormat_ICU58_Android extends NumberFormat {
    private static final char CURRENCY_SIGN = 164;
    private static final int CURRENCY_SIGN_COUNT_IN_ISO_FORMAT = 2;
    private static final int CURRENCY_SIGN_COUNT_IN_PLURAL_FORMAT = 3;
    private static final int CURRENCY_SIGN_COUNT_IN_SYMBOL_FORMAT = 1;
    private static final int CURRENCY_SIGN_COUNT_ZERO = 0;
    static final int DOUBLE_FRACTION_DIGITS = 340;
    static final int DOUBLE_INTEGER_DIGITS = 309;
    static final int MAX_INTEGER_DIGITS = 2000000000;
    static final int MAX_SCIENTIFIC_INTEGER_DIGITS = 8;
    static final Unit NULL_UNIT = new Unit("", "");
    public static final int PAD_AFTER_PREFIX = 1;
    public static final int PAD_AFTER_SUFFIX = 3;
    public static final int PAD_BEFORE_PREFIX = 0;
    public static final int PAD_BEFORE_SUFFIX = 2;
    static final char PATTERN_DECIMAL_SEPARATOR = '.';
    static final char PATTERN_DIGIT = '#';
    static final char PATTERN_EIGHT_DIGIT = '8';
    static final char PATTERN_EXPONENT = 'E';
    static final char PATTERN_FIVE_DIGIT = '5';
    static final char PATTERN_FOUR_DIGIT = '4';
    static final char PATTERN_GROUPING_SEPARATOR = ',';
    static final char PATTERN_MINUS_SIGN = '-';
    static final char PATTERN_NINE_DIGIT = '9';
    static final char PATTERN_ONE_DIGIT = '1';
    static final char PATTERN_PAD_ESCAPE = '*';
    private static final char PATTERN_PERCENT = '%';
    private static final char PATTERN_PER_MILLE = 8240;
    static final char PATTERN_PLUS_SIGN = '+';
    private static final char PATTERN_SEPARATOR = ';';
    static final char PATTERN_SEVEN_DIGIT = '7';
    static final char PATTERN_SIGNIFICANT_DIGIT = '@';
    static final char PATTERN_SIX_DIGIT = '6';
    static final char PATTERN_THREE_DIGIT = '3';
    static final char PATTERN_TWO_DIGIT = '2';
    static final char PATTERN_ZERO_DIGIT = '0';
    private static final char QUOTE = '\'';
    private static final int STATUS_INFINITE = 0;
    private static final int STATUS_LENGTH = 3;
    private static final int STATUS_POSITIVE = 1;
    private static final int STATUS_UNDERFLOW = 2;
    private static final UnicodeSet commaEquivalents = new UnicodeSet(44, 44, 1548, 1548, 1643, 1643, UProperty.DOUBLE_LIMIT, UProperty.DOUBLE_LIMIT, 65040, 65041, 65104, 65105, 65292, 65292, 65380, 65380).freeze();
    static final int currentSerialVersion = 4;
    private static final UnicodeSet defaultGroupingSeparators = new UnicodeSet(32, 32, 39, 39, 44, 44, 46, 46, 160, 160, 1548, 1548, 1643, 1644, 8192, 8202, 8216, 8217, 8228, 8228, 8239, 8239, 8287, 8287, 12288, 12290, 65040, 65042, 65104, 65106, 65287, 65287, 65292, 65292, 65294, 65294, 65377, 65377, 65380, 65380).freeze();
    private static final UnicodeSet dotEquivalents = new UnicodeSet(46, 46, 8228, 8228, 12290, 12290, 65042, 65042, 65106, 65106, 65294, 65294, 65377, 65377).freeze();
    private static double epsilon = 1.0E-11d;
    static final UnicodeSet minusSigns = new UnicodeSet(45, 45, 8315, 8315, 8331, 8331, 8722, 8722, 10134, 10134, 65123, 65123, 65293, 65293).freeze();
    static final UnicodeSet plusSigns = new UnicodeSet(43, 43, 8314, 8314, 8330, 8330, 10133, 10133, 64297, 64297, 65122, 65122, 65291, 65291).freeze();
    static final double roundingIncrementEpsilon = 1.0E-9d;
    private static final long serialVersionUID = 864413376551465018L;
    static final boolean skipExtendedSeparatorParsing = ICUConfig.get("android.icu.text.DecimalFormat.SkipExtendedSeparatorParsing", "false").equals("true");
    private static final UnicodeSet strictCommaEquivalents = new UnicodeSet(44, 44, 1643, 1643, 65040, 65040, 65104, 65104, 65292, 65292).freeze();
    private static final UnicodeSet strictDefaultGroupingSeparators = new UnicodeSet(32, 32, 39, 39, 44, 44, 46, 46, 160, 160, 1643, 1644, 8192, 8202, 8216, 8217, 8228, 8228, 8239, 8239, 8287, 8287, 12288, 12288, 65040, 65040, 65104, 65104, 65106, 65106, 65287, 65287, 65292, 65292, 65294, 65294, 65377, 65377).freeze();
    private static final UnicodeSet strictDotEquivalents = new UnicodeSet(46, 46, 8228, 8228, 65106, 65106, 65294, 65294, 65377, 65377).freeze();
    private int PARSE_MAX_EXPONENT = 1000;
    private transient BigDecimal actualRoundingIncrement = null;
    private transient android.icu.math.BigDecimal actualRoundingIncrementICU = null;
    private transient Set<AffixForCurrency> affixPatternsForCurrency = null;
    private ArrayList<FieldPosition> attributes = new ArrayList<>();
    private ChoiceFormat currencyChoice;
    private CurrencyPluralInfo currencyPluralInfo = null;
    private int currencySignCount = 0;
    private Currency.CurrencyUsage currencyUsage = Currency.CurrencyUsage.STANDARD;
    private boolean decimalSeparatorAlwaysShown = false;
    private transient DigitList_Android digitList = new DigitList_Android();
    private boolean exponentSignAlwaysShown = false;
    private String formatPattern = "";
    private int formatWidth = 0;
    private byte groupingSize = 3;
    private byte groupingSize2 = 0;
    private transient boolean isReadyForParsing = false;
    private MathContext mathContext = new MathContext(0, 0);
    private int maxSignificantDigits = 6;
    private byte minExponentDigits;
    private int minSignificantDigits = 1;
    private int multiplier = 1;
    private String negPrefixPattern;
    private String negSuffixPattern;
    private String negativePrefix = "-";
    private String negativeSuffix = "";
    private char pad = ' ';
    private int padPosition = 0;
    private boolean parseBigDecimal = false;
    boolean parseRequireDecimalPoint = false;
    private String posPrefixPattern;
    private String posSuffixPattern;
    private String positivePrefix = "";
    private String positiveSuffix = "";
    private transient double roundingDouble = 0.0d;
    private transient double roundingDoubleReciprocal = 0.0d;
    private BigDecimal roundingIncrement = null;
    private transient android.icu.math.BigDecimal roundingIncrementICU = null;
    private int roundingMode = 6;
    private int serialVersionOnStream = 4;
    private int style = 0;
    private DecimalFormatSymbols symbols = null;
    private boolean useExponentialNotation;
    private boolean useSignificantDigits = false;

    public DecimalFormat_ICU58_Android() {
        ULocale def = ULocale.getDefault(ULocale.Category.FORMAT);
        String pattern = getPattern(def, 0);
        this.symbols = new DecimalFormatSymbols(def);
        setCurrency(Currency.getInstance(def));
        applyPatternWithoutExpandAffix(pattern, false);
        if (this.currencySignCount == 3) {
            this.currencyPluralInfo = new CurrencyPluralInfo(def);
        } else {
            expandAffixAdjustWidth(null);
        }
    }

    public DecimalFormat_ICU58_Android(String pattern) {
        ULocale def = ULocale.getDefault(ULocale.Category.FORMAT);
        this.symbols = new DecimalFormatSymbols(def);
        setCurrency(Currency.getInstance(def));
        applyPatternWithoutExpandAffix(pattern, false);
        if (this.currencySignCount == 3) {
            this.currencyPluralInfo = new CurrencyPluralInfo(def);
        } else {
            expandAffixAdjustWidth(null);
        }
    }

    public DecimalFormat_ICU58_Android(String pattern, DecimalFormatSymbols symbols2) {
        createFromPatternAndSymbols(pattern, symbols2);
    }

    private void createFromPatternAndSymbols(String pattern, DecimalFormatSymbols inputSymbols) {
        this.symbols = (DecimalFormatSymbols) inputSymbols.clone();
        if (pattern.indexOf(164) >= 0) {
            setCurrencyForSymbols();
        }
        applyPatternWithoutExpandAffix(pattern, false);
        if (this.currencySignCount == 3) {
            this.currencyPluralInfo = new CurrencyPluralInfo(this.symbols.getULocale());
        } else {
            expandAffixAdjustWidth(null);
        }
    }

    public DecimalFormat_ICU58_Android(String pattern, DecimalFormatSymbols symbols2, CurrencyPluralInfo infoInput, int style2) {
        create(pattern, symbols2, style2 == 6 ? (CurrencyPluralInfo) infoInput.clone() : infoInput, style2);
    }

    private void create(String pattern, DecimalFormatSymbols inputSymbols, CurrencyPluralInfo info, int inputStyle) {
        if (inputStyle != 6) {
            createFromPatternAndSymbols(pattern, inputSymbols);
        } else {
            this.symbols = (DecimalFormatSymbols) inputSymbols.clone();
            this.currencyPluralInfo = info;
            applyPatternWithoutExpandAffix(this.currencyPluralInfo.getCurrencyPluralPattern(PluralRules.KEYWORD_OTHER), false);
            setCurrencyForSymbols();
        }
        this.style = inputStyle;
    }

    @Deprecated
    public DecimalFormat_ICU58_Android(String pattern, DecimalFormatSymbols inputSymbols, int style2) {
        create(pattern, inputSymbols, style2 == 6 ? new CurrencyPluralInfo(inputSymbols.getULocale()) : null, style2);
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(double number, StringBuffer result, FieldPosition fieldPosition) {
        return format(number, result, fieldPosition, false);
    }

    private boolean isNegative(double number) {
        return number < 0.0d || (number == 0.0d && 1.0d / number < 0.0d);
    }

    private double round(double number) {
        boolean isNegative = isNegative(number);
        if (isNegative) {
            number = -number;
        }
        double d = this.roundingDouble;
        if (d > 0.0d) {
            return round(number, d, this.roundingDoubleReciprocal, this.roundingMode, isNegative);
        }
        return number;
    }

    private double multiply(double number) {
        int i = this.multiplier;
        if (i != 1) {
            return ((double) i) * number;
        }
        return number;
    }

    private StringBuffer format(double number, StringBuffer result, FieldPosition fieldPosition, boolean parseAttr) {
        double number2;
        double roundingIncReciprocal;
        double roundingIncReciprocal2;
        boolean z = false;
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        if (Double.isNaN(number)) {
            if (fieldPosition.getField() == 0) {
                fieldPosition.setBeginIndex(result.length());
            } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.INTEGER) {
                fieldPosition.setBeginIndex(result.length());
            }
            result.append(this.symbols.getNaN());
            if (parseAttr) {
                addAttribute(NumberFormat.Field.INTEGER, result.length() - this.symbols.getNaN().length(), result.length());
            }
            if (fieldPosition.getField() == 0) {
                fieldPosition.setEndIndex(result.length());
            } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.INTEGER) {
                fieldPosition.setEndIndex(result.length());
            }
            addPadding(result, fieldPosition, 0, 0);
            return result;
        }
        double number3 = multiply(number);
        boolean isNegative = isNegative(number3);
        double number4 = round(number3);
        if (Double.isInfinite(number4)) {
            int prefixLen = appendAffix(result, isNegative, true, fieldPosition, parseAttr);
            if (fieldPosition.getField() == 0) {
                fieldPosition.setBeginIndex(result.length());
            } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.INTEGER) {
                fieldPosition.setBeginIndex(result.length());
            }
            result.append(this.symbols.getInfinity());
            if (parseAttr) {
                addAttribute(NumberFormat.Field.INTEGER, result.length() - this.symbols.getInfinity().length(), result.length());
            }
            if (fieldPosition.getField() == 0) {
                fieldPosition.setEndIndex(result.length());
            } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.INTEGER) {
                fieldPosition.setEndIndex(result.length());
            }
            addPadding(result, fieldPosition, prefixLen, appendAffix(result, isNegative, false, fieldPosition, parseAttr));
            return result;
        }
        int precision = precision(false);
        if (!this.useExponentialNotation || precision <= 0 || number4 == 0.0d || this.roundingMode == 6) {
            number2 = number4;
        } else {
            int log10RoundingIncr = (1 - precision) + ((int) Math.floor(Math.log10(Math.abs(number4))));
            if (log10RoundingIncr < 0) {
                roundingIncReciprocal = android.icu.math.BigDecimal.ONE.movePointRight(-log10RoundingIncr).doubleValue();
                roundingIncReciprocal2 = 0.0d;
            } else {
                roundingIncReciprocal = 0.0d;
                roundingIncReciprocal2 = android.icu.math.BigDecimal.ONE.movePointRight(log10RoundingIncr).doubleValue();
            }
            number2 = round(number4, roundingIncReciprocal2, roundingIncReciprocal, this.roundingMode, isNegative);
        }
        synchronized (this.digitList) {
            try {
                DigitList_Android digitList_Android = this.digitList;
                if (!this.useExponentialNotation) {
                    try {
                        if (!areSignificantDigitsUsed()) {
                            z = true;
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
                digitList_Android.set(number2, precision, z);
                return subformat(number2, result, fieldPosition, isNegative, false, parseAttr);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public double adjustNumberAsInFormatting(double number) {
        if (Double.isNaN(number)) {
            return number;
        }
        double number2 = round(multiply(number));
        if (Double.isInfinite(number2)) {
            return number2;
        }
        return toDigitList(number2).getDouble();
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public DigitList_Android toDigitList(double number) {
        DigitList_Android result = new DigitList_Android();
        result.set(number, precision(false), false);
        return result;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean isNumberNegative(double number) {
        if (Double.isNaN(number)) {
            return false;
        }
        return isNegative(multiply(number));
    }

    private static double round(double number, double roundingInc, double roundingIncReciprocal, int mode, boolean isNegative) {
        double div;
        double div2 = roundingIncReciprocal == 0.0d ? number / roundingInc : number * roundingIncReciprocal;
        if (mode == 0) {
            div = Math.ceil(div2 - epsilon);
        } else if (mode == 1) {
            div = Math.floor(epsilon + div2);
        } else if (mode == 2) {
            double d = epsilon;
            div = isNegative ? Math.floor(d + div2) : Math.ceil(div2 - d);
        } else if (mode == 3) {
            double d2 = epsilon;
            div = isNegative ? Math.ceil(div2 - d2) : Math.floor(d2 + div2);
        } else if (mode != 7) {
            double ceil = Math.ceil(div2);
            double ceildiff = ceil - div2;
            double floor = Math.floor(div2);
            double floordiff = div2 - floor;
            if (mode == 4) {
                div = ceildiff <= epsilon + floordiff ? ceil : floor;
            } else if (mode == 5) {
                div = floordiff <= epsilon + ceildiff ? floor : ceil;
            } else if (mode == 6) {
                double d3 = epsilon;
                if (floordiff + d3 < ceildiff) {
                    div = floor;
                } else if (d3 + ceildiff < floordiff) {
                    div = ceil;
                } else {
                    double testFloor = floor / 2.0d;
                    div = testFloor == Math.floor(testFloor) ? floor : ceil;
                }
            } else {
                throw new IllegalArgumentException("Invalid rounding mode: " + mode);
            }
        } else if (div2 == Math.floor(div2)) {
            return number;
        } else {
            throw new ArithmeticException("Rounding necessary");
        }
        return roundingIncReciprocal == 0.0d ? div * roundingInc : div / roundingIncReciprocal;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(long number, StringBuffer result, FieldPosition fieldPosition) {
        return format(number, result, fieldPosition, false);
    }

    private StringBuffer format(long number, StringBuffer result, FieldPosition fieldPosition, boolean parseAttr) {
        DigitList_Android digitList_Android;
        long number2 = number;
        boolean tooBig = false;
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        if (this.actualRoundingIncrementICU != null) {
            return format(android.icu.math.BigDecimal.valueOf(number), result, fieldPosition);
        }
        boolean isNegative = number2 < 0;
        if (isNegative) {
            number2 = -number2;
        }
        int i = this.multiplier;
        if (i != 1) {
            if (number2 < 0) {
                if (number2 <= Long.MIN_VALUE / ((long) i)) {
                    tooBig = true;
                }
            } else if (number2 > Long.MAX_VALUE / ((long) i)) {
                tooBig = true;
            }
            if (tooBig) {
                return format(BigInteger.valueOf(isNegative ? -number2 : number2), result, fieldPosition, parseAttr);
            }
        }
        long number3 = number2 * ((long) this.multiplier);
        DigitList_Android digitList_Android2 = this.digitList;
        synchronized (digitList_Android2) {
            try {
                this.digitList.set(number3, precision(true));
                if (this.digitList.wasRounded()) {
                    if (this.roundingMode == 7) {
                        throw new ArithmeticException("Rounding necessary");
                    }
                }
                digitList_Android = digitList_Android2;
                StringBuffer subformat = subformat((double) number3, result, fieldPosition, isNegative, true, parseAttr);
                return subformat;
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        }
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(BigInteger number, StringBuffer result, FieldPosition fieldPosition) {
        return format(number, result, fieldPosition, false);
    }

    private StringBuffer format(BigInteger number, StringBuffer result, FieldPosition fieldPosition, boolean parseAttr) {
        StringBuffer subformat;
        if (this.actualRoundingIncrementICU != null) {
            return format(new android.icu.math.BigDecimal(number), result, fieldPosition);
        }
        int i = this.multiplier;
        boolean z = true;
        if (i != 1) {
            number = number.multiply(BigInteger.valueOf((long) i));
        }
        synchronized (this.digitList) {
            this.digitList.set(number, precision(true));
            if (this.digitList.wasRounded()) {
                if (this.roundingMode == 7) {
                    throw new ArithmeticException("Rounding necessary");
                }
            }
            int intValue = number.intValue();
            if (number.signum() >= 0) {
                z = false;
            }
            subformat = subformat(intValue, result, fieldPosition, z, true, parseAttr);
        }
        return subformat;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(BigDecimal number, StringBuffer result, FieldPosition fieldPosition) {
        return format(number, result, fieldPosition, false);
    }

    private StringBuffer format(BigDecimal number, StringBuffer result, FieldPosition fieldPosition, boolean parseAttr) {
        StringBuffer subformat;
        int i = this.multiplier;
        if (i != 1) {
            number = number.multiply(BigDecimal.valueOf((long) i));
        }
        BigDecimal bigDecimal = this.actualRoundingIncrement;
        if (bigDecimal != null) {
            number = number.divide(bigDecimal, 0, this.roundingMode).multiply(this.actualRoundingIncrement);
        }
        synchronized (this.digitList) {
            this.digitList.set(number, precision(false), !this.useExponentialNotation && !areSignificantDigitsUsed());
            if (this.digitList.wasRounded()) {
                if (this.roundingMode == 7) {
                    throw new ArithmeticException("Rounding necessary");
                }
            }
            subformat = subformat(number.doubleValue(), result, fieldPosition, number.signum() < 0, false, parseAttr);
        }
        return subformat;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(android.icu.math.BigDecimal number, StringBuffer result, FieldPosition fieldPosition) {
        StringBuffer subformat;
        int i = this.multiplier;
        if (i != 1) {
            number = number.multiply(android.icu.math.BigDecimal.valueOf((long) i), this.mathContext);
        }
        android.icu.math.BigDecimal bigDecimal = this.actualRoundingIncrementICU;
        if (bigDecimal != null) {
            number = number.divide(bigDecimal, 0, this.roundingMode).multiply(this.actualRoundingIncrementICU, this.mathContext);
        }
        synchronized (this.digitList) {
            this.digitList.set(number, precision(false), !this.useExponentialNotation && !areSignificantDigitsUsed());
            if (this.digitList.wasRounded()) {
                if (this.roundingMode == 7) {
                    throw new ArithmeticException("Rounding necessary");
                }
            }
            subformat = subformat(number.doubleValue(), result, fieldPosition, number.signum() < 0, false, false);
        }
        return subformat;
    }

    private boolean isGroupingPosition(int pos) {
        byte b;
        if (!isGroupingUsed() || pos <= 0 || (b = this.groupingSize) <= 0) {
            return false;
        }
        byte b2 = this.groupingSize2;
        boolean result = true;
        if (b2 <= 0 || pos <= b) {
            if (pos % this.groupingSize != 0) {
                result = false;
            }
            return result;
        }
        if ((pos - b) % b2 != 0) {
            result = false;
        }
        return result;
    }

    private int precision(boolean isIntegral) {
        if (areSignificantDigitsUsed()) {
            return getMaximumSignificantDigits();
        }
        if (this.useExponentialNotation) {
            return getMinimumIntegerDigits() + getMaximumFractionDigits();
        }
        if (isIntegral) {
            return 0;
        }
        return getMaximumFractionDigits();
    }

    private StringBuffer subformat(int number, StringBuffer result, FieldPosition fieldPosition, boolean isNegative, boolean isInteger, boolean parseAttr) {
        if (this.currencySignCount == 3) {
            return subformat(this.currencyPluralInfo.select(getFixedDecimal((double) number)), result, fieldPosition, isNegative, isInteger, parseAttr);
        }
        return subformat(result, fieldPosition, isNegative, isInteger, parseAttr);
    }

    /* access modifiers changed from: package-private */
    public PluralRules.FixedDecimal getFixedDecimal(double number) {
        return getFixedDecimal(number, this.digitList);
    }

    /* access modifiers changed from: package-private */
    public PluralRules.FixedDecimal getFixedDecimal(double number, DigitList_Android dl) {
        int minFractionalDigits;
        int maxFractionalDigits;
        long f;
        int fractionalDigitsInDigitList = dl.count - dl.decimalAt;
        if (this.useSignificantDigits) {
            maxFractionalDigits = this.maxSignificantDigits - dl.decimalAt;
            minFractionalDigits = this.minSignificantDigits - dl.decimalAt;
            if (minFractionalDigits < 0) {
                minFractionalDigits = 0;
            }
            if (maxFractionalDigits < 0) {
                maxFractionalDigits = 0;
            }
        } else {
            maxFractionalDigits = getMaximumFractionDigits();
            minFractionalDigits = getMinimumFractionDigits();
        }
        int v = fractionalDigitsInDigitList;
        if (v < minFractionalDigits) {
            v = minFractionalDigits;
        } else if (v > maxFractionalDigits) {
            v = maxFractionalDigits;
        }
        long f2 = 0;
        if (v > 0) {
            for (int i = Math.max(0, dl.decimalAt); i < dl.count; i++) {
                f2 = (f2 * 10) + ((long) (dl.digits[i] - 48));
            }
            for (int i2 = v; i2 < fractionalDigitsInDigitList; i2++) {
                f2 *= 10;
            }
            f = f2;
        } else {
            f = 0;
        }
        return new PluralRules.FixedDecimal(number, v, f);
    }

    private StringBuffer subformat(double number, StringBuffer result, FieldPosition fieldPosition, boolean isNegative, boolean isInteger, boolean parseAttr) {
        if (this.currencySignCount == 3) {
            return subformat(this.currencyPluralInfo.select(getFixedDecimal(number)), result, fieldPosition, isNegative, isInteger, parseAttr);
        }
        return subformat(result, fieldPosition, isNegative, isInteger, parseAttr);
    }

    private StringBuffer subformat(String pluralCount, StringBuffer result, FieldPosition fieldPosition, boolean isNegative, boolean isInteger, boolean parseAttr) {
        if (this.style == 6) {
            String currencyPluralPattern = this.currencyPluralInfo.getCurrencyPluralPattern(pluralCount);
            if (!this.formatPattern.equals(currencyPluralPattern)) {
                applyPatternWithoutExpandAffix(currencyPluralPattern, false);
            }
        }
        expandAffixAdjustWidth(pluralCount);
        return subformat(result, fieldPosition, isNegative, isInteger, parseAttr);
    }

    private StringBuffer subformat(StringBuffer result, FieldPosition fieldPosition, boolean isNegative, boolean isInteger, boolean parseAttr) {
        if (this.digitList.isZero()) {
            this.digitList.decimalAt = 0;
        }
        int prefixLen = appendAffix(result, isNegative, true, fieldPosition, parseAttr);
        if (this.useExponentialNotation) {
            subformatExponential(result, fieldPosition, parseAttr);
        } else {
            subformatFixed(result, fieldPosition, isInteger, parseAttr);
        }
        addPadding(result, fieldPosition, prefixLen, appendAffix(result, isNegative, false, fieldPosition, parseAttr));
        return result;
    }

    private void subformatFixed(StringBuffer result, FieldPosition fieldPosition, boolean isInteger, boolean parseAttr) {
        int maxSigDig;
        int minSigDig;
        int minIntDig;
        int count;
        int digitIndex;
        String grouping;
        int sigCount;
        String[] digits = this.symbols.getDigitStrings();
        String grouping2 = this.currencySignCount == 0 ? this.symbols.getGroupingSeparatorString() : this.symbols.getMonetaryGroupingSeparatorString();
        String decimal = this.currencySignCount == 0 ? this.symbols.getDecimalSeparatorString() : this.symbols.getMonetaryDecimalSeparatorString();
        boolean useSigDig = areSignificantDigitsUsed();
        int maxIntDig = getMaximumIntegerDigits();
        int minIntDig2 = getMinimumIntegerDigits();
        int intBegin = result.length();
        if (fieldPosition.getField() == 0 || fieldPosition.getFieldAttribute() == NumberFormat.Field.INTEGER) {
            fieldPosition.setBeginIndex(intBegin);
        }
        int fractionalDigitsCount = 0;
        int minSigDig2 = getMinimumSignificantDigits();
        int maxSigDig2 = getMaximumSignificantDigits();
        if (!useSigDig) {
            maxSigDig = minIntDig2;
            minIntDig = 0;
            minSigDig = Integer.MAX_VALUE;
        } else {
            maxSigDig = minIntDig2;
            minIntDig = minSigDig2;
            minSigDig = maxSigDig2;
        }
        long fractionalDigits = 0;
        int count2 = useSigDig ? Math.max(1, this.digitList.decimalAt) : maxSigDig;
        if (this.digitList.decimalAt > 0 && count2 < this.digitList.decimalAt) {
            count2 = this.digitList.decimalAt;
        }
        int digitIndex2 = 0;
        if (count2 > maxIntDig && maxIntDig >= 0) {
            count2 = maxIntDig;
            digitIndex2 = this.digitList.decimalAt - count2;
        }
        int sizeBeforeIntegerPart = result.length();
        int digitIndex3 = digitIndex2;
        int i = count2 - 1;
        int sigCount2 = 0;
        while (i >= 0) {
            if (i >= this.digitList.decimalAt || digitIndex3 >= this.digitList.count || sigCount2 >= minSigDig) {
                result.append(digits[0]);
                if (sigCount2 > 0) {
                    sigCount2++;
                }
            } else {
                result.append(digits[this.digitList.getDigitValue(digitIndex3)]);
                sigCount2++;
                digitIndex3++;
            }
            if (isGroupingPosition(i)) {
                result.append(grouping2);
                grouping = grouping2;
                if (fieldPosition.getFieldAttribute() == NumberFormat.Field.GROUPING_SEPARATOR && fieldPosition.getBeginIndex() == 0 && fieldPosition.getEndIndex() == 0) {
                    fieldPosition.setBeginIndex(result.length() - 1);
                    fieldPosition.setEndIndex(result.length());
                }
                if (parseAttr) {
                    sigCount = sigCount2;
                    addAttribute(NumberFormat.Field.GROUPING_SEPARATOR, result.length() - 1, result.length());
                } else {
                    sigCount = sigCount2;
                }
            } else {
                grouping = grouping2;
                sigCount = sigCount2;
            }
            i--;
            sigCount2 = sigCount;
            fractionalDigitsCount = fractionalDigitsCount;
            grouping2 = grouping;
        }
        int fractionalDigitsCount2 = fractionalDigitsCount;
        if (fieldPosition.getField() == 0 || fieldPosition.getFieldAttribute() == NumberFormat.Field.INTEGER) {
            fieldPosition.setEndIndex(result.length());
        }
        if (sigCount2 == 0 && this.digitList.count == 0) {
            sigCount2 = 1;
        }
        boolean fractionPresent = (!isInteger && digitIndex3 < this.digitList.count) || (!useSigDig ? getMinimumFractionDigits() > 0 : sigCount2 < minIntDig);
        if (!fractionPresent && result.length() == sizeBeforeIntegerPart) {
            result.append(digits[0]);
        }
        if (parseAttr) {
            addAttribute(NumberFormat.Field.INTEGER, intBegin, result.length());
        }
        if (this.decimalSeparatorAlwaysShown || fractionPresent) {
            if (fieldPosition.getFieldAttribute() == NumberFormat.Field.DECIMAL_SEPARATOR) {
                fieldPosition.setBeginIndex(result.length());
            }
            result.append(decimal);
            if (fieldPosition.getFieldAttribute() == NumberFormat.Field.DECIMAL_SEPARATOR) {
                fieldPosition.setEndIndex(result.length());
            }
            if (parseAttr) {
                addAttribute(NumberFormat.Field.DECIMAL_SEPARATOR, result.length() - 1, result.length());
            }
        }
        if (fieldPosition.getField() == 1) {
            fieldPosition.setBeginIndex(result.length());
        } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.FRACTION) {
            fieldPosition.setBeginIndex(result.length());
        }
        int fracBegin = result.length();
        boolean recordFractionDigits = fieldPosition instanceof UFieldPosition;
        int count3 = useSigDig ? Integer.MAX_VALUE : getMaximumFractionDigits();
        if (useSigDig && (sigCount2 == minSigDig || (sigCount2 >= minIntDig && digitIndex3 == this.digitList.count))) {
            count3 = 0;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= count3) {
                break;
            }
            if (!useSigDig && i2 >= getMinimumFractionDigits()) {
                if (!isInteger) {
                    if (digitIndex3 >= this.digitList.count) {
                        break;
                    }
                } else {
                    break;
                }
            }
            if (-1 - i2 <= this.digitList.decimalAt - 1) {
                if (isInteger || digitIndex3 >= this.digitList.count) {
                    count = count3;
                    result.append(digits[0]);
                    if (recordFractionDigits) {
                        fractionalDigitsCount2++;
                        fractionalDigits *= 10;
                    }
                } else {
                    int digitIndex4 = digitIndex3 + 1;
                    byte digit = this.digitList.getDigitValue(digitIndex3);
                    result.append(digits[digit]);
                    if (recordFractionDigits) {
                        fractionalDigitsCount2++;
                        count = count3;
                        digitIndex = digitIndex4;
                        fractionalDigits = (fractionalDigits * 10) + ((long) digit);
                    } else {
                        count = count3;
                        digitIndex = digitIndex4;
                    }
                    digitIndex3 = digitIndex;
                }
                sigCount2++;
                if (useSigDig) {
                    if (sigCount2 != minSigDig) {
                        if (digitIndex3 == this.digitList.count && sigCount2 >= minIntDig) {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    continue;
                }
            } else {
                result.append(digits[0]);
                if (recordFractionDigits) {
                    fractionalDigitsCount2++;
                    fractionalDigits *= 10;
                    count = count3;
                } else {
                    count = count3;
                }
            }
            i2++;
            intBegin = intBegin;
            count3 = count;
        }
        if (fieldPosition.getField() == 1) {
            fieldPosition.setEndIndex(result.length());
        } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.FRACTION) {
            fieldPosition.setEndIndex(result.length());
        }
        if (recordFractionDigits) {
            ((UFieldPosition) fieldPosition).setFractionDigits(fractionalDigitsCount2, fractionalDigits);
        }
        if (!parseAttr) {
            return;
        }
        if (this.decimalSeparatorAlwaysShown || fractionPresent) {
            addAttribute(NumberFormat.Field.FRACTION, fracBegin, result.length());
        }
    }

    private void subformatExponential(StringBuffer result, FieldPosition fieldPosition, boolean parseAttr) {
        int minFracDig;
        int exponent;
        String str;
        int minFracDig2;
        int integerDigits;
        int intEnd;
        int fracBegin;
        int fracBegin2;
        String[] digits = this.symbols.getDigitStringsLocal();
        String decimal = this.currencySignCount == 0 ? this.symbols.getDecimalSeparatorString() : this.symbols.getMonetaryDecimalSeparatorString();
        boolean useSigDig = areSignificantDigitsUsed();
        int maxIntDig = getMaximumIntegerDigits();
        int minIntDig = getMinimumIntegerDigits();
        if (fieldPosition.getField() == 0) {
            fieldPosition.setBeginIndex(result.length());
            fieldPosition.setEndIndex(-1);
        } else if (fieldPosition.getField() == 1) {
            fieldPosition.setBeginIndex(-1);
        } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.INTEGER) {
            fieldPosition.setBeginIndex(result.length());
            fieldPosition.setEndIndex(-1);
        } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.FRACTION) {
            fieldPosition.setBeginIndex(-1);
        }
        int intBegin = result.length();
        int fracBegin3 = -1;
        if (useSigDig) {
            minIntDig = 1;
            maxIntDig = 1;
            minFracDig = getMinimumSignificantDigits() - 1;
        } else {
            minFracDig = getMinimumFractionDigits();
            if (maxIntDig > 8) {
                maxIntDig = 1;
                if (1 < minIntDig) {
                    maxIntDig = minIntDig;
                }
            }
            if (maxIntDig > minIntDig) {
                minIntDig = 1;
            }
        }
        int exponent2 = this.digitList.decimalAt;
        if (maxIntDig <= 1 || maxIntDig == minIntDig) {
            exponent = exponent2 - ((minIntDig > 0 || minFracDig > 0) ? minIntDig : 1);
        } else {
            exponent = (exponent2 > 0 ? (exponent2 - 1) / maxIntDig : (exponent2 / maxIntDig) - 1) * maxIntDig;
        }
        int minimumDigits = minIntDig + minFracDig;
        int integerDigits2 = this.digitList.isZero() ? minIntDig : this.digitList.decimalAt - exponent;
        int totalDigits = this.digitList.count;
        if (minimumDigits > totalDigits) {
            totalDigits = minimumDigits;
        }
        if (integerDigits2 > totalDigits) {
            totalDigits = integerDigits2;
        }
        int fractionalDigitsCount = 0;
        int intEnd2 = -1;
        int i = 0;
        boolean recordFractionDigits = false;
        int exponent3 = exponent;
        long fractionalDigits = 0;
        while (true) {
            byte digit = 0;
            if (i >= totalDigits) {
                break;
            }
            if (i == integerDigits2) {
                if (fieldPosition.getField() == 0) {
                    integerDigits = integerDigits2;
                    fieldPosition.setEndIndex(result.length());
                    minFracDig2 = minFracDig;
                } else {
                    integerDigits = integerDigits2;
                    minFracDig2 = minFracDig;
                    if (fieldPosition.getFieldAttribute() == NumberFormat.Field.INTEGER) {
                        fieldPosition.setEndIndex(result.length());
                    }
                }
                if (parseAttr) {
                    intEnd2 = result.length();
                    addAttribute(NumberFormat.Field.INTEGER, intBegin, result.length());
                }
                if (fieldPosition.getFieldAttribute() == NumberFormat.Field.DECIMAL_SEPARATOR) {
                    fieldPosition.setBeginIndex(result.length());
                }
                result.append(decimal);
                if (fieldPosition.getFieldAttribute() == NumberFormat.Field.DECIMAL_SEPARATOR) {
                    fieldPosition.setEndIndex(result.length());
                }
                int fracBegin4 = result.length();
                if (parseAttr) {
                    fracBegin2 = fracBegin4;
                    addAttribute(NumberFormat.Field.DECIMAL_SEPARATOR, result.length() - 1, result.length());
                } else {
                    fracBegin2 = fracBegin4;
                }
                if (fieldPosition.getField() == 1) {
                    fieldPosition.setBeginIndex(result.length());
                } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.FRACTION) {
                    fieldPosition.setBeginIndex(result.length());
                }
                recordFractionDigits = fieldPosition instanceof UFieldPosition;
                fracBegin3 = fracBegin2;
            } else {
                integerDigits = integerDigits2;
                minFracDig2 = minFracDig;
            }
            if (i < this.digitList.count) {
                digit = this.digitList.getDigitValue(i);
            }
            result.append(digits[digit]);
            if (recordFractionDigits) {
                fractionalDigitsCount++;
                fracBegin = fracBegin3;
                intEnd = intEnd2;
                fractionalDigits = (fractionalDigits * 10) + ((long) digit);
            } else {
                fracBegin = fracBegin3;
                intEnd = intEnd2;
            }
            i++;
            fracBegin3 = fracBegin;
            integerDigits2 = integerDigits;
            minFracDig = minFracDig2;
            intEnd2 = intEnd;
        }
        if (this.digitList.isZero() && totalDigits == 0) {
            result.append(digits[0]);
        }
        if (fracBegin3 == -1 && this.decimalSeparatorAlwaysShown) {
            if (fieldPosition.getFieldAttribute() == NumberFormat.Field.DECIMAL_SEPARATOR) {
                fieldPosition.setBeginIndex(result.length());
            }
            result.append(decimal);
            if (fieldPosition.getFieldAttribute() == NumberFormat.Field.DECIMAL_SEPARATOR) {
                fieldPosition.setEndIndex(result.length());
            }
            if (parseAttr) {
                addAttribute(NumberFormat.Field.DECIMAL_SEPARATOR, result.length() - 1, result.length());
            }
        }
        if (fieldPosition.getField() == 0) {
            if (fieldPosition.getEndIndex() < 0) {
                fieldPosition.setEndIndex(result.length());
            }
        } else if (fieldPosition.getField() == 1) {
            if (fieldPosition.getBeginIndex() < 0) {
                fieldPosition.setBeginIndex(result.length());
            }
            fieldPosition.setEndIndex(result.length());
        } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.INTEGER) {
            if (fieldPosition.getEndIndex() < 0) {
                fieldPosition.setEndIndex(result.length());
            }
        } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.FRACTION) {
            if (fieldPosition.getBeginIndex() < 0) {
                fieldPosition.setBeginIndex(result.length());
            }
            fieldPosition.setEndIndex(result.length());
        }
        if (recordFractionDigits) {
            ((UFieldPosition) fieldPosition).setFractionDigits(fractionalDigitsCount, fractionalDigits);
        }
        if (parseAttr) {
            if (intEnd2 < 0) {
                addAttribute(NumberFormat.Field.INTEGER, intBegin, result.length());
            }
            if (fracBegin3 > 0) {
                addAttribute(NumberFormat.Field.FRACTION, fracBegin3, result.length());
            }
        }
        if (fieldPosition.getFieldAttribute() == NumberFormat.Field.EXPONENT_SYMBOL) {
            fieldPosition.setBeginIndex(result.length());
        }
        result.append(this.symbols.getExponentSeparator());
        if (fieldPosition.getFieldAttribute() == NumberFormat.Field.EXPONENT_SYMBOL) {
            fieldPosition.setEndIndex(result.length());
        }
        if (parseAttr) {
            addAttribute(NumberFormat.Field.EXPONENT_SYMBOL, result.length() - this.symbols.getExponentSeparator().length(), result.length());
        }
        if (this.digitList.isZero()) {
            exponent3 = 0;
        }
        if (exponent3 < 0) {
            exponent3 = -exponent3;
            if (fieldPosition.getFieldAttribute() == NumberFormat.Field.EXPONENT_SIGN) {
                fieldPosition.setBeginIndex(result.length());
            }
            result.append(this.symbols.getMinusSignString());
            if (fieldPosition.getFieldAttribute() == NumberFormat.Field.EXPONENT_SIGN) {
                fieldPosition.setEndIndex(result.length());
            }
            if (parseAttr) {
                addAttribute(NumberFormat.Field.EXPONENT_SIGN, result.length() - 1, result.length());
            }
        } else if (this.exponentSignAlwaysShown) {
            if (fieldPosition.getFieldAttribute() == NumberFormat.Field.EXPONENT_SIGN) {
                fieldPosition.setBeginIndex(result.length());
            }
            result.append(this.symbols.getPlusSignString());
            if (fieldPosition.getFieldAttribute() == NumberFormat.Field.EXPONENT_SIGN) {
                fieldPosition.setEndIndex(result.length());
            }
            if (parseAttr) {
                addAttribute(NumberFormat.Field.EXPONENT_SIGN, result.length() - 1, result.length());
            }
        }
        int expBegin = result.length();
        this.digitList.set((long) exponent3);
        int expDig = this.minExponentDigits;
        if (this.useExponentialNotation && expDig < 1) {
            expDig = 1;
        }
        for (int i2 = this.digitList.decimalAt; i2 < expDig; i2++) {
            result.append(digits[0]);
        }
        for (int i3 = 0; i3 < this.digitList.decimalAt; i3++) {
            if (i3 < this.digitList.count) {
                str = digits[this.digitList.getDigitValue(i3)];
            } else {
                str = digits[0];
            }
            result.append(str);
        }
        if (fieldPosition.getFieldAttribute() == NumberFormat.Field.EXPONENT) {
            fieldPosition.setBeginIndex(expBegin);
            fieldPosition.setEndIndex(result.length());
        }
        if (parseAttr) {
            addAttribute(NumberFormat.Field.EXPONENT, expBegin, result.length());
        }
    }

    private final void addPadding(StringBuffer result, FieldPosition fieldPosition, int prefixLen, int suffixLen) {
        int len;
        int i = this.formatWidth;
        if (i > 0 && (len = i - result.length()) > 0) {
            char[] padding = new char[len];
            for (int i2 = 0; i2 < len; i2++) {
                padding[i2] = this.pad;
            }
            int i3 = this.padPosition;
            if (i3 == 0) {
                result.insert(0, padding);
            } else if (i3 == 1) {
                result.insert(prefixLen, padding);
            } else if (i3 == 2) {
                result.insert(result.length() - suffixLen, padding);
            } else if (i3 == 3) {
                result.append(padding);
            }
            int i4 = this.padPosition;
            if (i4 == 0 || i4 == 1) {
                fieldPosition.setBeginIndex(fieldPosition.getBeginIndex() + len);
                fieldPosition.setEndIndex(fieldPosition.getEndIndex() + len);
            }
        }
    }

    @Override // android.icu.text.NumberFormat
    public Number parse(String text, ParsePosition parsePosition) {
        return (Number) parse(text, parsePosition, null);
    }

    @Override // android.icu.text.NumberFormat
    public CurrencyAmount parseCurrency(CharSequence text, ParsePosition pos) {
        return (CurrencyAmount) parse(text.toString(), pos, new Currency[1]);
    }

    /* JADX INFO: Multiple debug info for r0v0 int: [D('backup' int), D('i' int)] */
    private Object parse(String text, ParsePosition parsePosition, Currency[] currency) {
        boolean[] status;
        char c;
        char c2;
        Number n;
        double d;
        int i;
        int i2;
        int i3 = parsePosition.getIndex();
        if (this.formatWidth > 0 && ((i2 = this.padPosition) == 0 || i2 == 1)) {
            i3 = skipPadding(text, i3);
        }
        if (text.regionMatches(i3, this.symbols.getNaN(), 0, this.symbols.getNaN().length())) {
            int i4 = i3 + this.symbols.getNaN().length();
            if (this.formatWidth > 0 && ((i = this.padPosition) == 2 || i == 3)) {
                i4 = skipPadding(text, i4);
            }
            parsePosition.setIndex(i4);
            return new Double(Double.NaN);
        }
        boolean[] status2 = new boolean[3];
        if (this.currencySignCount != 0) {
            if (!parseForCurrency(text, parsePosition, currency, status2)) {
                return null;
            }
            status = status2;
            c = 2;
            c2 = 0;
        } else if (currency != null) {
            return null;
        } else {
            status = status2;
            c = 2;
            c2 = 0;
            if (!subparse(text, parsePosition, this.digitList, status2, currency, this.negPrefixPattern, this.negSuffixPattern, this.posPrefixPattern, this.posSuffixPattern, false, 0)) {
                parsePosition.setIndex(i3);
                return null;
            }
        }
        if (status[c2]) {
            if (status[1]) {
                d = Double.POSITIVE_INFINITY;
            } else {
                d = Double.NEGATIVE_INFINITY;
            }
            n = new Double(d);
        } else if (status[c]) {
            n = status[1] ? new Double("0.0") : new Double("-0.0");
        } else if (status[1] || !this.digitList.isZero()) {
            int mult = this.multiplier;
            while (mult % 10 == 0) {
                this.digitList.decimalAt--;
                mult /= 10;
            }
            if (this.parseBigDecimal || mult != 1 || !this.digitList.isIntegral()) {
                android.icu.math.BigDecimal big = this.digitList.getBigDecimalICU(status[1]);
                n = big;
                if (mult != 1) {
                    n = big.divide(android.icu.math.BigDecimal.valueOf((long) mult), this.mathContext);
                }
            } else if (this.digitList.decimalAt < 12) {
                long l = 0;
                if (this.digitList.count > 0) {
                    int nx = 0;
                    while (nx < this.digitList.count) {
                        l = ((10 * l) + ((long) ((char) this.digitList.digits[nx]))) - 48;
                        nx++;
                    }
                    while (true) {
                        int nx2 = nx + 1;
                        if (nx >= this.digitList.decimalAt) {
                            break;
                        }
                        l *= 10;
                        nx = nx2;
                    }
                    if (!status[1]) {
                        l = -l;
                    }
                }
                n = Long.valueOf(l);
            } else {
                BigInteger big2 = this.digitList.getBigInteger(status[1]);
                n = big2.bitLength() < 64 ? Long.valueOf(big2.longValue()) : big2;
            }
        } else {
            n = new Double("-0.0");
        }
        return currency != null ? new CurrencyAmount(n, currency[c2]) : n;
    }

    private boolean parseForCurrency(String text, ParsePosition parsePosition, Currency[] currency, boolean[] status) {
        int maxPosIndex;
        DigitList_Android tmpDigitList;
        boolean[] tmpStatus;
        boolean found;
        ParsePosition parsePosition2;
        int origPos;
        int maxPosIndex2;
        int i;
        int i2;
        int origPos2 = parsePosition.getIndex();
        if (!this.isReadyForParsing) {
            int savedCurrencySignCount = this.currencySignCount;
            setupCurrencyAffixForAllPatterns();
            if (savedCurrencySignCount == 3) {
                applyPatternWithoutExpandAffix(this.formatPattern, false);
            } else {
                applyPattern(this.formatPattern, false);
            }
            this.isReadyForParsing = true;
        }
        int maxErrorPos = -1;
        boolean[] savedStatus = null;
        boolean[] tmpStatus2 = new boolean[3];
        ParsePosition tmpPos = new ParsePosition(origPos2);
        DigitList_Android tmpDigitList2 = new DigitList_Android();
        if (this.style == 6) {
            tmpDigitList = tmpDigitList2;
            tmpStatus = tmpStatus2;
            maxPosIndex = origPos2;
            found = subparse(text, tmpPos, tmpDigitList2, tmpStatus2, currency, this.negPrefixPattern, this.negSuffixPattern, this.posPrefixPattern, this.posSuffixPattern, true, 1);
            origPos = origPos2;
            parsePosition2 = parsePosition;
        } else {
            tmpDigitList = tmpDigitList2;
            tmpStatus = tmpStatus2;
            maxPosIndex = origPos2;
            origPos = origPos2;
            parsePosition2 = parsePosition;
            found = subparse(text, tmpPos, tmpDigitList, tmpStatus, currency, this.negPrefixPattern, this.negSuffixPattern, this.posPrefixPattern, this.posSuffixPattern, true, 0);
        }
        if (found) {
            maxPosIndex2 = maxPosIndex;
            if (tmpPos.getIndex() > maxPosIndex2) {
                int maxPosIndex3 = tmpPos.getIndex();
                savedStatus = tmpStatus;
                this.digitList = tmpDigitList;
                maxPosIndex2 = maxPosIndex3;
            }
        } else {
            maxPosIndex2 = maxPosIndex;
            maxErrorPos = tmpPos.getErrorIndex();
        }
        Iterator<AffixForCurrency> it = this.affixPatternsForCurrency.iterator();
        int maxPosIndex4 = maxPosIndex2;
        int maxErrorPos2 = maxErrorPos;
        boolean found2 = found;
        while (it.hasNext()) {
            AffixForCurrency affix = it.next();
            boolean[] tmpStatus3 = new boolean[3];
            ParsePosition tmpPos2 = new ParsePosition(origPos);
            DigitList_Android tmpDigitList3 = new DigitList_Android();
            if (!subparse(text, tmpPos2, tmpDigitList3, tmpStatus3, currency, affix.getNegPrefix(), affix.getNegSuffix(), affix.getPosPrefix(), affix.getPosSuffix(), true, affix.getPatternType())) {
                if (tmpPos2.getErrorIndex() > maxErrorPos2) {
                    i2 = tmpPos2.getErrorIndex();
                } else {
                    i2 = maxErrorPos2;
                }
                maxErrorPos2 = i2;
            } else if (tmpPos2.getIndex() > maxPosIndex4) {
                int maxPosIndex5 = tmpPos2.getIndex();
                this.digitList = tmpDigitList3;
                found2 = true;
                maxPosIndex4 = maxPosIndex5;
                savedStatus = tmpStatus3;
            } else {
                found2 = true;
            }
            parsePosition2 = parsePosition;
            it = it;
            origPos = origPos;
        }
        boolean[] tmpStatus4 = new boolean[3];
        ParsePosition tmpPos3 = new ParsePosition(origPos);
        DigitList_Android tmpDigitList4 = new DigitList_Android();
        if (subparse(text, tmpPos3, tmpDigitList4, tmpStatus4, currency, this.negativePrefix, this.negativeSuffix, this.positivePrefix, this.positiveSuffix, false, 0)) {
            if (tmpPos3.getIndex() > maxPosIndex4) {
                maxPosIndex4 = tmpPos3.getIndex();
                savedStatus = tmpStatus4;
                this.digitList = tmpDigitList4;
            }
            found2 = true;
        } else {
            if (tmpPos3.getErrorIndex() > maxErrorPos2) {
                i = tmpPos3.getErrorIndex();
            } else {
                i = maxErrorPos2;
            }
            maxErrorPos2 = i;
        }
        if (!found2) {
            parsePosition.setErrorIndex(maxErrorPos2);
        } else {
            parsePosition.setIndex(maxPosIndex4);
            parsePosition.setErrorIndex(-1);
            for (int index = 0; index < 3; index++) {
                status[index] = savedStatus[index];
            }
        }
        return found2;
    }

    private void setupCurrencyAffixForAllPatterns() {
        if (this.currencyPluralInfo == null) {
            this.currencyPluralInfo = new CurrencyPluralInfo(this.symbols.getULocale());
        }
        this.affixPatternsForCurrency = new HashSet();
        String savedFormatPattern = this.formatPattern;
        applyPatternWithoutExpandAffix(getPattern(this.symbols.getULocale(), 1), false);
        this.affixPatternsForCurrency.add(new AffixForCurrency(this.negPrefixPattern, this.negSuffixPattern, this.posPrefixPattern, this.posSuffixPattern, 0));
        Iterator<String> iter = this.currencyPluralInfo.pluralPatternIterator();
        Set<String> currencyUnitPatternSet = new HashSet<>();
        while (iter.hasNext()) {
            String currencyPattern = this.currencyPluralInfo.getCurrencyPluralPattern(iter.next());
            if (currencyPattern != null && !currencyUnitPatternSet.contains(currencyPattern)) {
                currencyUnitPatternSet.add(currencyPattern);
                applyPatternWithoutExpandAffix(currencyPattern, false);
                this.affixPatternsForCurrency.add(new AffixForCurrency(this.negPrefixPattern, this.negSuffixPattern, this.posPrefixPattern, this.posSuffixPattern, 1));
            }
        }
        this.formatPattern = savedFormatPattern;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02a6, code lost:
        r20 = true;
        r37 = r2;
        r39 = r29;
        r33 = r5;
        r38 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x011e, code lost:
        r20 = true;
        r37 = r2;
        r38 = r3;
        r33 = r5;
        r39 = r29;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x017d, code lost:
        r20 = true;
        r37 = r2;
        r38 = r3;
        r39 = r29;
        r33 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01f1, code lost:
        r20 = true;
        r37 = r2;
        r39 = r29;
        r33 = r5;
        r38 = r31;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x043c  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x0454  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x045a  */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x0470  */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x0478  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x047a  */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x047f  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x0482  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x0486  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x0489  */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x048d  */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x0491  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean subparse(java.lang.String r41, java.text.ParsePosition r42, android.icu.text.DigitList_Android r43, boolean[] r44, android.icu.util.Currency[] r45, java.lang.String r46, java.lang.String r47, java.lang.String r48, java.lang.String r49, boolean r50, int r51) {
        /*
        // Method dump skipped, instructions count: 1217
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DecimalFormat_ICU58_Android.subparse(java.lang.String, java.text.ParsePosition, android.icu.text.DigitList_Android, boolean[], android.icu.util.Currency[], java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, int):boolean");
    }

    private int matchesDigit(String str, int start, int[] decVal) {
        String[] localeDigits = this.symbols.getDigitStringsLocal();
        for (int i = 0; i < 10; i++) {
            int digitStrLen = localeDigits[i].length();
            if (str.regionMatches(start, localeDigits[i], 0, digitStrLen)) {
                decVal[0] = i;
                return digitStrLen;
            }
        }
        int cp = str.codePointAt(start);
        decVal[0] = UCharacter.digit(cp, 10);
        if (decVal[0] >= 0) {
            return Character.charCount(cp);
        }
        return 0;
    }

    private UnicodeSet getEquivalentDecimals(String decimal, boolean strictParse) {
        UnicodeSet equivSet = UnicodeSet.EMPTY;
        if (strictParse) {
            if (strictDotEquivalents.contains(decimal)) {
                return strictDotEquivalents;
            }
            if (strictCommaEquivalents.contains(decimal)) {
                return strictCommaEquivalents;
            }
            return equivSet;
        } else if (dotEquivalents.contains(decimal)) {
            return dotEquivalents;
        } else {
            if (commaEquivalents.contains(decimal)) {
                return commaEquivalents;
            }
            return equivSet;
        }
    }

    private final int skipPadding(String text, int position) {
        while (position < text.length() && text.charAt(position) == this.pad) {
            position++;
        }
        return position;
    }

    private int compareAffix(String text, int pos, boolean isNegative, boolean isPrefix, String affixPat, boolean complexCurrencyParsing, int type, Currency[] currency) {
        if (currency != null || this.currencyChoice != null || (this.currencySignCount != 0 && complexCurrencyParsing)) {
            return compareComplexAffix(affixPat, text, pos, type, currency);
        }
        if (isPrefix) {
            return compareSimpleAffix(isNegative ? this.negativePrefix : this.positivePrefix, text, pos);
        }
        return compareSimpleAffix(isNegative ? this.negativeSuffix : this.positiveSuffix, text, pos);
    }

    private static boolean isBidiMark(int c) {
        return c == 8206 || c == 8207 || c == 1564;
    }

    private static String trimMarksFromAffix(String affix) {
        boolean hasBidiMark = false;
        int idx = 0;
        while (true) {
            if (idx >= affix.length()) {
                break;
            } else if (isBidiMark(affix.charAt(idx))) {
                hasBidiMark = true;
                break;
            } else {
                idx++;
            }
        }
        if (!hasBidiMark) {
            return affix;
        }
        StringBuilder buf = new StringBuilder();
        buf.append((CharSequence) affix, 0, idx);
        while (true) {
            idx++;
            if (idx >= affix.length()) {
                return buf.toString();
            }
            char c = affix.charAt(idx);
            if (!isBidiMark(c)) {
                buf.append(c);
            }
        }
    }

    private static int compareSimpleAffix(String affix, String input, int pos) {
        String trimmedAffix = affix.length() > 1 ? trimMarksFromAffix(affix) : affix;
        int i = 0;
        while (i < trimmedAffix.length()) {
            int c = UTF16.charAt(trimmedAffix, i);
            int len = UTF16.getCharCount(c);
            if (PatternProps.isWhiteSpace(c)) {
                boolean literalMatch = false;
                while (pos < input.length()) {
                    int ic = UTF16.charAt(input, pos);
                    if (ic != c) {
                        if (!isBidiMark(ic)) {
                            break;
                        }
                        pos++;
                    } else {
                        literalMatch = true;
                        i += len;
                        pos += len;
                        if (i == trimmedAffix.length()) {
                            break;
                        }
                        c = UTF16.charAt(trimmedAffix, i);
                        len = UTF16.getCharCount(c);
                        if (!PatternProps.isWhiteSpace(c)) {
                            break;
                        }
                    }
                }
                int i2 = skipPatternWhiteSpace(trimmedAffix, i);
                pos = skipUWhiteSpace(input, pos);
                if (pos == pos && !literalMatch) {
                    return -1;
                }
                i = skipUWhiteSpace(trimmedAffix, i2);
            } else {
                boolean match = false;
                while (pos < input.length()) {
                    int ic2 = UTF16.charAt(input, pos);
                    if (match || !equalWithSignCompatibility(ic2, c)) {
                        if (!isBidiMark(ic2)) {
                            break;
                        }
                        pos++;
                    } else {
                        i += len;
                        pos += len;
                        match = true;
                    }
                }
                if (!match) {
                    return -1;
                }
            }
        }
        return pos - pos;
    }

    private static boolean equalWithSignCompatibility(int lhs, int rhs) {
        return lhs == rhs || (minusSigns.contains(lhs) && minusSigns.contains(rhs)) || (plusSigns.contains(lhs) && plusSigns.contains(rhs));
    }

    private static int skipPatternWhiteSpace(String text, int pos) {
        while (pos < text.length()) {
            int c = UTF16.charAt(text, pos);
            if (!PatternProps.isWhiteSpace(c)) {
                break;
            }
            pos += UTF16.getCharCount(c);
        }
        return pos;
    }

    private static int skipUWhiteSpace(String text, int pos) {
        while (pos < text.length()) {
            int c = UTF16.charAt(text, pos);
            if (!UCharacter.isUWhiteSpace(c)) {
                break;
            }
            pos += UTF16.getCharCount(c);
        }
        return pos;
    }

    private static int skipBidiMarks(String text, int pos) {
        while (pos < text.length()) {
            int c = UTF16.charAt(text, pos);
            if (!isBidiMark(c)) {
                break;
            }
            pos += UTF16.getCharCount(c);
        }
        return pos;
    }

    private int compareComplexAffix(String affixPat, String text, int pos, int type, Currency[] currency) {
        int i;
        int i2 = 0;
        int pos2 = pos;
        while (i2 < affixPat.length() && pos2 >= 0) {
            int i3 = i2 + 1;
            char c = affixPat.charAt(i2);
            if (c == '\'') {
                while (true) {
                    int j = affixPat.indexOf(39, i3);
                    if (j == i3) {
                        pos2 = match(text, pos2, 39);
                        i2 = j + 1;
                        break;
                    } else if (j > i3) {
                        pos2 = match(text, pos2, affixPat.substring(i3, j));
                        i = j + 1;
                        if (i >= affixPat.length() || affixPat.charAt(i) != '\'') {
                            i2 = i;
                        } else {
                            pos2 = match(text, pos2, 39);
                            i3 = i + 1;
                        }
                    } else {
                        throw new RuntimeException();
                    }
                }
                i2 = i;
            } else {
                String affix = null;
                if (c == '%') {
                    affix = this.symbols.getPercentString();
                } else if (c == '+') {
                    affix = this.symbols.getPlusSignString();
                } else if (c == '-') {
                    affix = this.symbols.getMinusSignString();
                } else if (c == 164) {
                    boolean plural = true;
                    if (i3 < affixPat.length() && affixPat.charAt(i3) == 164) {
                        i3++;
                    }
                    if (i3 >= affixPat.length() || affixPat.charAt(i3) != 164) {
                        plural = false;
                    }
                    if (plural) {
                        i3++;
                    }
                    ULocale uloc = getLocale(ULocale.VALID_LOCALE);
                    if (uloc == null) {
                        uloc = this.symbols.getLocale(ULocale.VALID_LOCALE);
                    }
                    ParsePosition ppos = new ParsePosition(pos2);
                    String iso = Currency.parse(uloc, text, type, ppos);
                    if (iso != null) {
                        if (currency != null) {
                            currency[0] = Currency.getInstance(iso);
                        } else if (iso.compareTo(getEffectiveCurrency().getCurrencyCode()) != 0) {
                            pos2 = -1;
                            i2 = i3;
                        }
                        pos2 = ppos.getIndex();
                        i2 = i3;
                    } else {
                        pos2 = -1;
                        i2 = i3;
                    }
                } else if (c == 8240) {
                    affix = this.symbols.getPerMillString();
                }
                if (affix != null) {
                    pos2 = match(text, pos2, affix);
                    i2 = i3;
                } else {
                    pos2 = match(text, pos2, c);
                    if (PatternProps.isWhiteSpace(c)) {
                        i2 = skipPatternWhiteSpace(affixPat, i3);
                    } else {
                        i2 = i3;
                    }
                }
            }
        }
        return pos2 - pos;
    }

    static final int match(String text, int pos, int ch) {
        if (pos < 0 || pos >= text.length()) {
            return -1;
        }
        int pos2 = skipBidiMarks(text, pos);
        if (PatternProps.isWhiteSpace(ch)) {
            int pos3 = skipPatternWhiteSpace(text, pos2);
            if (pos3 == pos2) {
                return -1;
            }
            return pos3;
        } else if (pos2 >= text.length() || UTF16.charAt(text, pos2) != ch) {
            return -1;
        } else {
            return skipBidiMarks(text, UTF16.getCharCount(ch) + pos2);
        }
    }

    static final int match(String text, int pos, String str) {
        int i = 0;
        while (i < str.length() && pos >= 0) {
            int ch = UTF16.charAt(str, i);
            i += UTF16.getCharCount(ch);
            if (!isBidiMark(ch)) {
                pos = match(text, pos, ch);
                if (PatternProps.isWhiteSpace(ch)) {
                    i = skipPatternWhiteSpace(str, i);
                }
            }
        }
        return pos;
    }

    public DecimalFormatSymbols getDecimalFormatSymbols() {
        try {
            return (DecimalFormatSymbols) this.symbols.clone();
        } catch (Exception e) {
            return null;
        }
    }

    public void setDecimalFormatSymbols(DecimalFormatSymbols newSymbols) {
        this.symbols = (DecimalFormatSymbols) newSymbols.clone();
        setCurrencyForSymbols();
        expandAffixes(null);
    }

    private void setCurrencyForSymbols() {
        DecimalFormatSymbols def = new DecimalFormatSymbols(this.symbols.getULocale());
        if (!this.symbols.getCurrencySymbol().equals(def.getCurrencySymbol()) || !this.symbols.getInternationalCurrencySymbol().equals(def.getInternationalCurrencySymbol())) {
            setCurrency(null);
        } else {
            setCurrency(Currency.getInstance(this.symbols.getULocale()));
        }
    }

    public String getPositivePrefix() {
        return this.positivePrefix;
    }

    public void setPositivePrefix(String newValue) {
        this.positivePrefix = newValue;
        this.posPrefixPattern = null;
    }

    public String getNegativePrefix() {
        return this.negativePrefix;
    }

    public void setNegativePrefix(String newValue) {
        this.negativePrefix = newValue;
        this.negPrefixPattern = null;
    }

    public String getPositiveSuffix() {
        return this.positiveSuffix;
    }

    public void setPositiveSuffix(String newValue) {
        this.positiveSuffix = newValue;
        this.posSuffixPattern = null;
    }

    public String getNegativeSuffix() {
        return this.negativeSuffix;
    }

    public void setNegativeSuffix(String newValue) {
        this.negativeSuffix = newValue;
        this.negSuffixPattern = null;
    }

    public int getMultiplier() {
        return this.multiplier;
    }

    public void setMultiplier(int newValue) {
        if (newValue != 0) {
            this.multiplier = newValue;
            return;
        }
        throw new IllegalArgumentException("Bad multiplier: " + newValue);
    }

    public BigDecimal getRoundingIncrement() {
        android.icu.math.BigDecimal bigDecimal = this.roundingIncrementICU;
        if (bigDecimal == null) {
            return null;
        }
        return bigDecimal.toBigDecimal();
    }

    public void setRoundingIncrement(BigDecimal newValue) {
        if (newValue == null) {
            setRoundingIncrement((android.icu.math.BigDecimal) null);
        } else {
            setRoundingIncrement(new android.icu.math.BigDecimal(newValue));
        }
    }

    public void setRoundingIncrement(android.icu.math.BigDecimal newValue) {
        int i = newValue == null ? 0 : newValue.compareTo(android.icu.math.BigDecimal.ZERO);
        if (i >= 0) {
            if (i == 0) {
                setInternalRoundingIncrement(null);
            } else {
                setInternalRoundingIncrement(newValue);
            }
            resetActualRounding();
            return;
        }
        throw new IllegalArgumentException("Illegal rounding increment");
    }

    public void setRoundingIncrement(double newValue) {
        if (newValue >= 0.0d) {
            if (newValue == 0.0d) {
                setInternalRoundingIncrement(null);
            } else {
                setInternalRoundingIncrement(android.icu.math.BigDecimal.valueOf(newValue));
            }
            resetActualRounding();
            return;
        }
        throw new IllegalArgumentException("Illegal rounding increment");
    }

    @Override // android.icu.text.NumberFormat
    public int getRoundingMode() {
        return this.roundingMode;
    }

    @Override // android.icu.text.NumberFormat
    public void setRoundingMode(int roundingMode2) {
        if (roundingMode2 < 0 || roundingMode2 > 7) {
            throw new IllegalArgumentException("Invalid rounding mode: " + roundingMode2);
        }
        this.roundingMode = roundingMode2;
        resetActualRounding();
    }

    public int getFormatWidth() {
        return this.formatWidth;
    }

    public void setFormatWidth(int width) {
        if (width >= 0) {
            this.formatWidth = width;
            return;
        }
        throw new IllegalArgumentException("Illegal format width");
    }

    public char getPadCharacter() {
        return this.pad;
    }

    public void setPadCharacter(char padChar) {
        this.pad = padChar;
    }

    public int getPadPosition() {
        return this.padPosition;
    }

    public void setPadPosition(int padPos) {
        if (padPos < 0 || padPos > 3) {
            throw new IllegalArgumentException("Illegal pad position");
        }
        this.padPosition = padPos;
    }

    public boolean isScientificNotation() {
        return this.useExponentialNotation;
    }

    public void setScientificNotation(boolean useScientific) {
        this.useExponentialNotation = useScientific;
    }

    public byte getMinimumExponentDigits() {
        return this.minExponentDigits;
    }

    public void setMinimumExponentDigits(byte minExpDig) {
        if (minExpDig >= 1) {
            this.minExponentDigits = minExpDig;
            return;
        }
        throw new IllegalArgumentException("Exponent digits must be >= 1");
    }

    public boolean isExponentSignAlwaysShown() {
        return this.exponentSignAlwaysShown;
    }

    public void setExponentSignAlwaysShown(boolean expSignAlways) {
        this.exponentSignAlwaysShown = expSignAlways;
    }

    public int getGroupingSize() {
        return this.groupingSize;
    }

    public void setGroupingSize(int newValue) {
        this.groupingSize = (byte) newValue;
    }

    public int getSecondaryGroupingSize() {
        return this.groupingSize2;
    }

    public void setSecondaryGroupingSize(int newValue) {
        this.groupingSize2 = (byte) newValue;
    }

    public MathContext getMathContextICU() {
        return this.mathContext;
    }

    public java.math.MathContext getMathContext() {
        try {
            if (this.mathContext == null) {
                return null;
            }
            return new java.math.MathContext(this.mathContext.getDigits(), RoundingMode.valueOf(this.mathContext.getRoundingMode()));
        } catch (Exception e) {
            return null;
        }
    }

    public void setMathContextICU(MathContext newValue) {
        this.mathContext = newValue;
    }

    public void setMathContext(java.math.MathContext newValue) {
        this.mathContext = new MathContext(newValue.getPrecision(), 1, false, newValue.getRoundingMode().ordinal());
    }

    public boolean isDecimalSeparatorAlwaysShown() {
        return this.decimalSeparatorAlwaysShown;
    }

    public void setDecimalPatternMatchRequired(boolean value) {
        this.parseRequireDecimalPoint = value;
    }

    public boolean isDecimalPatternMatchRequired() {
        return this.parseRequireDecimalPoint;
    }

    public void setDecimalSeparatorAlwaysShown(boolean newValue) {
        this.decimalSeparatorAlwaysShown = newValue;
    }

    public CurrencyPluralInfo getCurrencyPluralInfo() {
        try {
            if (this.currencyPluralInfo == null) {
                return null;
            }
            return (CurrencyPluralInfo) this.currencyPluralInfo.clone();
        } catch (Exception e) {
            return null;
        }
    }

    public void setCurrencyPluralInfo(CurrencyPluralInfo newInfo) {
        this.currencyPluralInfo = (CurrencyPluralInfo) newInfo.clone();
        this.isReadyForParsing = false;
    }

    @Override // java.text.Format, android.icu.text.NumberFormat
    public Object clone() {
        try {
            DecimalFormat_ICU58_Android other = (DecimalFormat_ICU58_Android) super.clone();
            other.symbols = (DecimalFormatSymbols) this.symbols.clone();
            other.digitList = new DigitList_Android();
            if (this.currencyPluralInfo != null) {
                other.currencyPluralInfo = (CurrencyPluralInfo) this.currencyPluralInfo.clone();
            }
            other.attributes = new ArrayList<>();
            other.currencyUsage = this.currencyUsage;
            return other;
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    @Override // android.icu.text.NumberFormat
    public boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == null || !super.equals(obj)) {
            return false;
        }
        DecimalFormat_ICU58_Android other = (DecimalFormat_ICU58_Android) obj;
        if (this.currencySignCount != other.currencySignCount) {
            return false;
        }
        if ((this.style == 6 && (!equals(this.posPrefixPattern, other.posPrefixPattern) || !equals(this.posSuffixPattern, other.posSuffixPattern) || !equals(this.negPrefixPattern, other.negPrefixPattern) || !equals(this.negSuffixPattern, other.negSuffixPattern))) || this.multiplier != other.multiplier || this.groupingSize != other.groupingSize || this.groupingSize2 != other.groupingSize2 || this.decimalSeparatorAlwaysShown != other.decimalSeparatorAlwaysShown || (z = this.useExponentialNotation) != other.useExponentialNotation) {
            return false;
        }
        if ((z && this.minExponentDigits != other.minExponentDigits) || (z2 = this.useSignificantDigits) != other.useSignificantDigits) {
            return false;
        }
        if ((!z2 || (this.minSignificantDigits == other.minSignificantDigits && this.maxSignificantDigits == other.maxSignificantDigits)) && this.symbols.equals(other.symbols) && Objects.equals(this.currencyPluralInfo, other.currencyPluralInfo) && this.currencyUsage.equals(other.currencyUsage)) {
            return true;
        }
        return false;
    }

    private boolean equals(String pat1, String pat2) {
        if (pat1 == null || pat2 == null) {
            if (pat1 == null && pat2 == null) {
                return true;
            }
            return false;
        } else if (pat1.equals(pat2)) {
            return true;
        } else {
            return unquote(pat1).equals(unquote(pat2));
        }
    }

    private String unquote(String pat) {
        StringBuilder buf = new StringBuilder(pat.length());
        int i = 0;
        while (i < pat.length()) {
            int i2 = i + 1;
            char ch = pat.charAt(i);
            if (ch != '\'') {
                buf.append(ch);
            }
            i = i2;
        }
        return buf.toString();
    }

    @Override // android.icu.text.NumberFormat
    public int hashCode() {
        return (super.hashCode() * 37) + this.positivePrefix.hashCode();
    }

    public String toPattern() {
        if (this.style == 6) {
            return this.formatPattern;
        }
        return toPattern(false);
    }

    public String toLocalizedPattern() {
        if (this.style == 6) {
            return this.formatPattern;
        }
        return toPattern(true);
    }

    private void expandAffixes(String pluralCount) {
        this.currencyChoice = null;
        StringBuffer buffer = new StringBuffer();
        String str = this.posPrefixPattern;
        if (str != null) {
            expandAffix(str, pluralCount, buffer);
            this.positivePrefix = buffer.toString();
        }
        String str2 = this.posSuffixPattern;
        if (str2 != null) {
            expandAffix(str2, pluralCount, buffer);
            this.positiveSuffix = buffer.toString();
        }
        String str3 = this.negPrefixPattern;
        if (str3 != null) {
            expandAffix(str3, pluralCount, buffer);
            this.negativePrefix = buffer.toString();
        }
        String str4 = this.negSuffixPattern;
        if (str4 != null) {
            expandAffix(str4, pluralCount, buffer);
            this.negativeSuffix = buffer.toString();
        }
    }

    private void expandAffix(String pattern, String pluralCount, StringBuffer buffer) {
        String s;
        String str;
        buffer.setLength(0);
        int i = 0;
        while (i < pattern.length()) {
            int i2 = i + 1;
            char c = pattern.charAt(i);
            if (c == '\'') {
                while (true) {
                    int j = pattern.indexOf(39, i2);
                    if (j == i2) {
                        buffer.append('\'');
                        i = j + 1;
                        break;
                    } else if (j > i2) {
                        buffer.append(pattern.substring(i2, j));
                        int i3 = j + 1;
                        if (i3 >= pattern.length() || pattern.charAt(i3) != '\'') {
                            i = i3;
                        } else {
                            buffer.append('\'');
                            i2 = i3 + 1;
                        }
                    } else {
                        throw new RuntimeException();
                    }
                }
            } else {
                if (c == '%') {
                    buffer.append(this.symbols.getPercentString());
                } else if (c == '-') {
                    buffer.append(this.symbols.getMinusSignString());
                } else if (c == 164) {
                    boolean intl = i2 < pattern.length() && pattern.charAt(i2) == 164;
                    boolean plural = false;
                    if (intl && (i2 = i2 + 1) < pattern.length() && pattern.charAt(i2) == 164) {
                        plural = true;
                        intl = false;
                        i2++;
                    }
                    Currency currency = getCurrency();
                    if (currency == null) {
                        if (intl) {
                            str = this.symbols.getInternationalCurrencySymbol();
                        } else {
                            str = this.symbols.getCurrencySymbol();
                        }
                        s = str;
                    } else if (plural && pluralCount != null) {
                        s = currency.getName(this.symbols.getULocale(), 2, pluralCount, (boolean[]) null);
                    } else if (!intl) {
                        s = currency.getName(this.symbols.getULocale(), 0, (boolean[]) null);
                    } else {
                        s = currency.getCurrencyCode();
                    }
                    buffer.append(s);
                    i = i2;
                } else if (c != 8240) {
                    buffer.append(c);
                } else {
                    buffer.append(this.symbols.getPerMillString());
                }
                i = i2;
            }
        }
    }

    private int appendAffix(StringBuffer buf, boolean isNegative, boolean isPrefix, FieldPosition fieldPosition, boolean parseAttr) {
        String pattern;
        String affix;
        String affixPat;
        if (this.currencyChoice != null) {
            if (isPrefix) {
                affixPat = isNegative ? this.negPrefixPattern : this.posPrefixPattern;
            } else {
                affixPat = isNegative ? this.negSuffixPattern : this.posSuffixPattern;
            }
            StringBuffer affixBuf = new StringBuffer();
            expandAffix(affixPat, null, affixBuf);
            buf.append(affixBuf);
            return affixBuf.length();
        }
        if (isPrefix) {
            affix = isNegative ? this.negativePrefix : this.positivePrefix;
            pattern = isNegative ? this.negPrefixPattern : this.posPrefixPattern;
        } else {
            affix = isNegative ? this.negativeSuffix : this.positiveSuffix;
            pattern = isNegative ? this.negSuffixPattern : this.posSuffixPattern;
        }
        if (parseAttr) {
            int offset = affix.indexOf(this.symbols.getCurrencySymbol());
            if (offset > -1) {
                formatAffix2Attribute(isPrefix, NumberFormat.Field.CURRENCY, buf, offset, this.symbols.getCurrencySymbol().length());
            }
            int offset2 = affix.indexOf(this.symbols.getMinusSignString());
            if (offset2 > -1) {
                formatAffix2Attribute(isPrefix, NumberFormat.Field.SIGN, buf, offset2, this.symbols.getMinusSignString().length());
            }
            int offset3 = affix.indexOf(this.symbols.getPercentString());
            if (offset3 > -1) {
                formatAffix2Attribute(isPrefix, NumberFormat.Field.PERCENT, buf, offset3, this.symbols.getPercentString().length());
            }
            int offset4 = affix.indexOf(this.symbols.getPerMillString());
            if (offset4 > -1) {
                formatAffix2Attribute(isPrefix, NumberFormat.Field.PERMILLE, buf, offset4, this.symbols.getPerMillString().length());
            }
            int offset5 = pattern.indexOf("¤¤¤");
            if (offset5 > -1) {
                formatAffix2Attribute(isPrefix, NumberFormat.Field.CURRENCY, buf, offset5, affix.length() - offset5);
            }
        }
        if (fieldPosition.getFieldAttribute() == NumberFormat.Field.SIGN) {
            DecimalFormatSymbols decimalFormatSymbols = this.symbols;
            String sign = isNegative ? decimalFormatSymbols.getMinusSignString() : decimalFormatSymbols.getPlusSignString();
            int firstPos = affix.indexOf(sign);
            if (firstPos > -1) {
                int startPos = buf.length() + firstPos;
                fieldPosition.setBeginIndex(startPos);
                fieldPosition.setEndIndex(sign.length() + startPos);
            }
        } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.PERCENT) {
            int firstPos2 = affix.indexOf(this.symbols.getPercentString());
            if (firstPos2 > -1) {
                int startPos2 = buf.length() + firstPos2;
                fieldPosition.setBeginIndex(startPos2);
                fieldPosition.setEndIndex(this.symbols.getPercentString().length() + startPos2);
            }
        } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.PERMILLE) {
            int firstPos3 = affix.indexOf(this.symbols.getPerMillString());
            if (firstPos3 > -1) {
                int startPos3 = buf.length() + firstPos3;
                fieldPosition.setBeginIndex(startPos3);
                fieldPosition.setEndIndex(this.symbols.getPerMillString().length() + startPos3);
            }
        } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.CURRENCY) {
            if (affix.indexOf(this.symbols.getCurrencySymbol()) > -1) {
                String aff = this.symbols.getCurrencySymbol();
                int start = buf.length() + affix.indexOf(aff);
                fieldPosition.setBeginIndex(start);
                fieldPosition.setEndIndex(aff.length() + start);
            } else if (affix.indexOf(this.symbols.getInternationalCurrencySymbol()) > -1) {
                String aff2 = this.symbols.getInternationalCurrencySymbol();
                int start2 = buf.length() + affix.indexOf(aff2);
                fieldPosition.setBeginIndex(start2);
                fieldPosition.setEndIndex(aff2.length() + start2);
            } else if (pattern.indexOf("¤¤¤") > -1) {
                fieldPosition.setBeginIndex(buf.length() + pattern.indexOf("¤¤¤"));
                fieldPosition.setEndIndex(buf.length() + affix.length());
            }
        }
        buf.append(affix);
        return affix.length();
    }

    private void formatAffix2Attribute(boolean isPrefix, NumberFormat.Field fieldType, StringBuffer buf, int offset, int symbolSize) {
        int begin = offset;
        if (!isPrefix) {
            begin += buf.length();
        }
        addAttribute(fieldType, begin, begin + symbolSize);
    }

    private void addAttribute(NumberFormat.Field field, int begin, int end) {
        FieldPosition pos = new FieldPosition(field);
        pos.setBeginIndex(begin);
        pos.setEndIndex(end);
        this.attributes.add(pos);
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        return formatToCharacterIterator(obj, NULL_UNIT);
    }

    /* access modifiers changed from: package-private */
    public AttributedCharacterIterator formatToCharacterIterator(Object obj, Unit unit) {
        if (obj instanceof Number) {
            Number number = (Number) obj;
            StringBuffer text = new StringBuffer();
            unit.writePrefix(text);
            this.attributes.clear();
            if (obj instanceof BigInteger) {
                format((BigInteger) number, text, new FieldPosition(0), true);
            } else if (obj instanceof BigDecimal) {
                format((BigDecimal) number, text, new FieldPosition(0), true);
            } else if (obj instanceof Double) {
                format(number.doubleValue(), text, new FieldPosition(0), true);
            } else if ((obj instanceof Integer) || (obj instanceof Long)) {
                format(number.longValue(), text, new FieldPosition(0), true);
            } else {
                throw new IllegalArgumentException();
            }
            unit.writeSuffix(text);
            AttributedString as = new AttributedString(text.toString());
            for (int i = 0; i < this.attributes.size(); i++) {
                FieldPosition pos = this.attributes.get(i);
                Format.Field attribute = pos.getFieldAttribute();
                as.addAttribute(attribute, attribute, pos.getBeginIndex(), pos.getEndIndex());
            }
            return as.getIterator();
        }
        throw new IllegalArgumentException();
    }

    private void appendAffixPattern(StringBuffer buffer, boolean isNegative, boolean isPrefix, boolean localized) {
        String affixPat;
        String affix;
        if (isPrefix) {
            affixPat = isNegative ? this.negPrefixPattern : this.posPrefixPattern;
        } else {
            affixPat = isNegative ? this.negSuffixPattern : this.posSuffixPattern;
        }
        if (affixPat == null) {
            if (isPrefix) {
                affix = isNegative ? this.negativePrefix : this.positivePrefix;
            } else {
                affix = isNegative ? this.negativeSuffix : this.positiveSuffix;
            }
            buffer.append('\'');
            for (int i = 0; i < affix.length(); i++) {
                char ch = affix.charAt(i);
                if (ch == '\'') {
                    buffer.append(ch);
                }
                buffer.append(ch);
            }
            buffer.append('\'');
        } else if (!localized) {
            buffer.append(affixPat);
        } else {
            int i2 = 0;
            while (i2 < affixPat.length()) {
                char ch2 = affixPat.charAt(i2);
                if (ch2 == '%') {
                    ch2 = this.symbols.getPercent();
                } else if (ch2 == '\'') {
                    int j = affixPat.indexOf(39, i2 + 1);
                    if (j >= 0) {
                        buffer.append(affixPat.substring(i2, j + 1));
                        i2 = j;
                        i2++;
                    } else {
                        throw new IllegalArgumentException("Malformed affix pattern: " + affixPat);
                    }
                } else if (ch2 == '-') {
                    ch2 = this.symbols.getMinusSign();
                } else if (ch2 == 8240) {
                    ch2 = this.symbols.getPerMill();
                }
                if (ch2 == this.symbols.getDecimalSeparator() || ch2 == this.symbols.getGroupingSeparator()) {
                    buffer.append('\'');
                    buffer.append(ch2);
                    buffer.append('\'');
                    i2++;
                } else {
                    buffer.append(ch2);
                    i2++;
                }
            }
        }
    }

    private String toPattern(boolean localized) {
        String padSpec;
        char sigDigit;
        int maxSigDig;
        int minDig;
        int padPos;
        int maxDig;
        char zero;
        boolean z;
        int i;
        char c;
        char c2;
        int maxDig2;
        byte b;
        char c3;
        StringBuffer result = new StringBuffer();
        char zero2 = localized ? this.symbols.getZeroDigit() : PATTERN_ZERO_DIGIT;
        char digit = localized ? this.symbols.getDigit() : PATTERN_DIGIT;
        char sigDigit2 = 0;
        boolean useSigDig = areSignificantDigitsUsed();
        if (useSigDig) {
            sigDigit2 = localized ? this.symbols.getSignificantDigit() : PATTERN_SIGNIFICANT_DIGIT;
        }
        char group = localized ? this.symbols.getGroupingSeparator() : PATTERN_GROUPING_SEPARATOR;
        int roundingDecimalPos = 0;
        String roundingDigits = null;
        int padPos2 = this.formatWidth > 0 ? this.padPosition : -1;
        int i2 = 2;
        if (this.formatWidth > 0) {
            StringBuffer stringBuffer = new StringBuffer(2);
            if (localized) {
                c3 = this.symbols.getPadEscape();
            } else {
                c3 = PATTERN_PAD_ESCAPE;
            }
            stringBuffer.append(c3);
            stringBuffer.append(this.pad);
            padSpec = stringBuffer.toString();
        } else {
            padSpec = null;
        }
        android.icu.math.BigDecimal bigDecimal = this.roundingIncrementICU;
        if (bigDecimal != null) {
            int i3 = bigDecimal.scale();
            roundingDigits = this.roundingIncrementICU.movePointRight(i3).toString();
            roundingDecimalPos = roundingDigits.length() - i3;
        }
        int part = 0;
        while (true) {
            if (part >= i2) {
                break;
            }
            if (padPos2 == 0) {
                result.append(padSpec);
            }
            appendAffixPattern(result, part != 0, true, localized);
            if (padPos2 == 1) {
                result.append(padSpec);
            }
            int sub0Start = result.length();
            int g = isGroupingUsed() ? Math.max(0, (int) this.groupingSize) : 0;
            if (g <= 0 || (b = this.groupingSize2) <= 0) {
                sigDigit = sigDigit2;
            } else {
                sigDigit = sigDigit2;
                if (b != this.groupingSize) {
                    g += b;
                }
            }
            if (useSigDig) {
                minDig = getMinimumSignificantDigits();
                int maxSigDig2 = getMaximumSignificantDigits();
                maxDig = maxSigDig2;
                maxSigDig = padPos2;
                padPos = maxSigDig2;
            } else {
                minDig = getMinimumIntegerDigits();
                maxDig = getMaximumIntegerDigits();
                maxSigDig = padPos2;
                padPos = 0;
            }
            if (this.useExponentialNotation) {
                if (maxDig > 8) {
                    maxDig = 1;
                }
            } else if (useSigDig) {
                maxDig = Math.max(maxDig, g + 1);
            } else {
                maxDig = Math.max(Math.max(g, getMinimumIntegerDigits()), roundingDecimalPos) + 1;
            }
            int i4 = maxDig;
            while (i4 > 0) {
                if (!this.useExponentialNotation && i4 < maxDig && isGroupingPosition(i4)) {
                    result.append(group);
                }
                if (useSigDig) {
                    result.append((padPos < i4 || i4 <= padPos - minDig) ? digit : sigDigit);
                    maxDig2 = maxDig;
                } else {
                    if (roundingDigits != null) {
                        int pos = roundingDecimalPos - i4;
                        if (pos >= 0) {
                            maxDig2 = maxDig;
                            if (pos < roundingDigits.length()) {
                                result.append((char) ((roundingDigits.charAt(pos) - '0') + zero2));
                            }
                        } else {
                            maxDig2 = maxDig;
                        }
                    } else {
                        maxDig2 = maxDig;
                    }
                    result.append(i4 <= minDig ? zero2 : digit);
                }
                i4--;
                g = g;
                maxDig = maxDig2;
            }
            int maxDig3 = maxDig;
            if (!useSigDig) {
                if (getMaximumFractionDigits() > 0 || this.decimalSeparatorAlwaysShown) {
                    if (localized) {
                        c2 = this.symbols.getDecimalSeparator();
                    } else {
                        c2 = PATTERN_DECIMAL_SEPARATOR;
                    }
                    result.append(c2);
                }
                int pos2 = roundingDecimalPos;
                int i5 = 0;
                while (i5 < getMaximumFractionDigits()) {
                    if (roundingDigits == null || pos2 >= roundingDigits.length()) {
                        result.append(i5 < getMinimumFractionDigits() ? zero2 : digit);
                    } else {
                        if (pos2 < 0) {
                            c = zero2;
                        } else {
                            c = (char) ((roundingDigits.charAt(pos2) - '0') + zero2);
                        }
                        result.append(c);
                        pos2++;
                    }
                    i5++;
                }
            }
            if (this.useExponentialNotation) {
                if (localized) {
                    result.append(this.symbols.getExponentSeparator());
                } else {
                    result.append(PATTERN_EXPONENT);
                }
                if (this.exponentSignAlwaysShown) {
                    result.append(localized ? this.symbols.getPlusSign() : PATTERN_PLUS_SIGN);
                }
                for (int i6 = 0; i6 < this.minExponentDigits; i6++) {
                    result.append(zero2);
                }
            }
            if (padSpec == null || this.useExponentialNotation) {
                zero = zero2;
                z = true;
            } else {
                int length = (this.formatWidth - result.length()) + sub0Start;
                if (part == 0) {
                    zero = zero2;
                    i = this.positivePrefix.length() + this.positiveSuffix.length();
                } else {
                    zero = zero2;
                    i = this.negativeSuffix.length() + this.negativePrefix.length();
                }
                int add = length - i;
                while (add > 0) {
                    result.insert(sub0Start, digit);
                    int maxDig4 = maxDig3 + 1;
                    add--;
                    if (add <= 1 || !isGroupingPosition(maxDig4)) {
                        maxDig3 = maxDig4;
                    } else {
                        result.insert(sub0Start, group);
                        add--;
                        maxDig3 = maxDig4;
                    }
                }
                z = true;
            }
            if (maxSigDig == 2) {
                result.append(padSpec);
            }
            if (part == 0) {
                z = false;
            }
            appendAffixPattern(result, z, false, localized);
            if (maxSigDig == 3) {
                result.append(padSpec);
            }
            if (part == 0) {
                if (this.negativeSuffix.equals(this.positiveSuffix)) {
                    if (this.negativePrefix.equals(PATTERN_MINUS_SIGN + this.positivePrefix)) {
                        break;
                    }
                }
                result.append(localized ? this.symbols.getPatternSeparator() : PATTERN_SEPARATOR);
            }
            part++;
            padPos2 = maxSigDig;
            sigDigit2 = sigDigit;
            digit = digit;
            zero2 = zero;
            i2 = 2;
        }
        return result.toString();
    }

    public void applyPattern(String pattern) {
        applyPattern(pattern, false);
    }

    public void applyLocalizedPattern(String pattern) {
        applyPattern(pattern, true);
    }

    private void applyPattern(String pattern, boolean localized) {
        applyPatternWithoutExpandAffix(pattern, localized);
        expandAffixAdjustWidth(null);
    }

    private void expandAffixAdjustWidth(String pluralCount) {
        expandAffixes(pluralCount);
        int i = this.formatWidth;
        if (i > 0) {
            this.formatWidth = i + this.positivePrefix.length() + this.positiveSuffix.length();
        }
    }

    /* JADX INFO: Multiple debug info for r11v4 'pos'  int: [D('sub0Start' int), D('sub0Limit' int), D('pos' int)] */
    /* JADX INFO: Multiple debug info for r4v12 int: [D('digitTotalCount' int), D('expSignAlways' boolean)] */
    /* JADX INFO: Multiple debug info for r4v13 int: [D('digitTotalCount' int), D('scale' int)] */
    /* JADX INFO: Multiple debug info for r9v14 int: [D('minInt' int), D('exponent' java.lang.String)] */
    /* JADX INFO: Multiple debug info for r5v13 'padPos'  int: [D('padPos' int), D('decimalSeparator' char)] */
    /* JADX INFO: Multiple debug info for r8v6 'sub0Start'  int: [D('digit' char), D('sub0Start' int)] */
    /* JADX INFO: Multiple debug info for r11v8 'pos'  int: [D('pos' int), D('sub0Limit' int)] */
    /* JADX INFO: Multiple debug info for r3v38 'sigDigit'  char: [D('plus' char), D('sigDigit' char)] */
    /* JADX INFO: Multiple debug info for r7v13 'decimalPos'  int: [D('affix' java.lang.StringBuilder), D('decimalPos' int)] */
    /* JADX INFO: Multiple debug info for r12v15 int: [D('subpart' int), D('pos' int)] */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x0640, code lost:
        if (r12 > 2) goto L_0x064a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0384  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x03ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyPatternWithoutExpandAffix(java.lang.String r62, boolean r63) {
        /*
        // Method dump skipped, instructions count: 2166
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DecimalFormat_ICU58_Android.applyPatternWithoutExpandAffix(java.lang.String, boolean):void");
    }

    private void patternError(String msg, String pattern) {
        throw new IllegalArgumentException(msg + " in pattern \"" + pattern + '\"');
    }

    @Override // android.icu.text.NumberFormat
    public void setMaximumIntegerDigits(int newValue) {
        super.setMaximumIntegerDigits(Math.min(newValue, (int) MAX_INTEGER_DIGITS));
    }

    @Override // android.icu.text.NumberFormat
    public void setMinimumIntegerDigits(int newValue) {
        super.setMinimumIntegerDigits(Math.min(newValue, (int) DOUBLE_INTEGER_DIGITS));
    }

    public int getMinimumSignificantDigits() {
        return this.minSignificantDigits;
    }

    public int getMaximumSignificantDigits() {
        return this.maxSignificantDigits;
    }

    public void setMinimumSignificantDigits(int min) {
        if (min < 1) {
            min = 1;
        }
        int max = Math.max(this.maxSignificantDigits, min);
        this.minSignificantDigits = min;
        this.maxSignificantDigits = max;
        setSignificantDigitsUsed(true);
    }

    public void setMaximumSignificantDigits(int max) {
        if (max < 1) {
            max = 1;
        }
        this.minSignificantDigits = Math.min(this.minSignificantDigits, max);
        this.maxSignificantDigits = max;
        setSignificantDigitsUsed(true);
    }

    public boolean areSignificantDigitsUsed() {
        return this.useSignificantDigits;
    }

    public void setSignificantDigitsUsed(boolean useSignificantDigits2) {
        this.useSignificantDigits = useSignificantDigits2;
    }

    @Override // android.icu.text.NumberFormat
    public void setCurrency(Currency theCurrency) {
        super.setCurrency(theCurrency);
        if (theCurrency != null) {
            String s = theCurrency.getName(this.symbols.getULocale(), 0, (boolean[]) null);
            this.symbols.setCurrency(theCurrency);
            this.symbols.setCurrencySymbol(s);
        }
        if (this.currencySignCount != 0) {
            if (theCurrency != null) {
                setRoundingIncrement(theCurrency.getRoundingIncrement(this.currencyUsage));
                int d = theCurrency.getDefaultFractionDigits(this.currencyUsage);
                setMinimumFractionDigits(d);
                setMaximumFractionDigits(d);
            }
            if (this.currencySignCount != 3) {
                expandAffixes(null);
            }
        }
    }

    public void setCurrencyUsage(Currency.CurrencyUsage newUsage) {
        if (newUsage != null) {
            this.currencyUsage = newUsage;
            Currency theCurrency = getCurrency();
            if (theCurrency != null) {
                setRoundingIncrement(theCurrency.getRoundingIncrement(this.currencyUsage));
                int d = theCurrency.getDefaultFractionDigits(this.currencyUsage);
                setMinimumFractionDigits(d);
                _setMaximumFractionDigits(d);
                return;
            }
            return;
        }
        throw new NullPointerException("return value is null at method AAA");
    }

    public Currency.CurrencyUsage getCurrencyUsage() {
        return this.currencyUsage;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.NumberFormat
    @Deprecated
    public Currency getEffectiveCurrency() {
        Currency c = getCurrency();
        if (c == null) {
            return Currency.getInstance(this.symbols.getInternationalCurrencySymbol());
        }
        return c;
    }

    @Override // android.icu.text.NumberFormat
    public void setMaximumFractionDigits(int newValue) {
        _setMaximumFractionDigits(newValue);
        resetActualRounding();
    }

    private void _setMaximumFractionDigits(int newValue) {
        super.setMaximumFractionDigits(Math.min(newValue, (int) DOUBLE_FRACTION_DIGITS));
    }

    @Override // android.icu.text.NumberFormat
    public void setMinimumFractionDigits(int newValue) {
        super.setMinimumFractionDigits(Math.min(newValue, (int) DOUBLE_FRACTION_DIGITS));
    }

    public void setParseBigDecimal(boolean value) {
        this.parseBigDecimal = value;
    }

    public boolean isParseBigDecimal() {
        return this.parseBigDecimal;
    }

    public void setParseMaxDigits(int newValue) {
        if (newValue > 0) {
            this.PARSE_MAX_EXPONENT = newValue;
        }
    }

    public int getParseMaxDigits() {
        return this.PARSE_MAX_EXPONENT;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        this.attributes.clear();
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        if (getMaximumIntegerDigits() > MAX_INTEGER_DIGITS) {
            setMaximumIntegerDigits(MAX_INTEGER_DIGITS);
        }
        if (getMaximumFractionDigits() > DOUBLE_FRACTION_DIGITS) {
            _setMaximumFractionDigits(DOUBLE_FRACTION_DIGITS);
        }
        if (this.serialVersionOnStream < 2) {
            this.exponentSignAlwaysShown = false;
            setInternalRoundingIncrement(null);
            this.roundingMode = 6;
            this.formatWidth = 0;
            this.pad = ' ';
            this.padPosition = 0;
            if (this.serialVersionOnStream < 1) {
                this.useExponentialNotation = false;
            }
        }
        if (this.serialVersionOnStream < 3) {
            setCurrencyForSymbols();
        }
        if (this.serialVersionOnStream < 4) {
            this.currencyUsage = Currency.CurrencyUsage.STANDARD;
        }
        this.serialVersionOnStream = 4;
        this.digitList = new DigitList_Android();
        BigDecimal bigDecimal = this.roundingIncrement;
        if (bigDecimal != null) {
            setInternalRoundingIncrement(new android.icu.math.BigDecimal(bigDecimal));
        }
        resetActualRounding();
    }

    private void setInternalRoundingIncrement(android.icu.math.BigDecimal value) {
        this.roundingIncrementICU = value;
        this.roundingIncrement = value == null ? null : value.toBigDecimal();
    }

    /* access modifiers changed from: private */
    public static final class AffixForCurrency {
        private String negPrefixPatternForCurrency = null;
        private String negSuffixPatternForCurrency = null;
        private final int patternType;
        private String posPrefixPatternForCurrency = null;
        private String posSuffixPatternForCurrency = null;

        public AffixForCurrency(String negPrefix, String negSuffix, String posPrefix, String posSuffix, int type) {
            this.negPrefixPatternForCurrency = negPrefix;
            this.negSuffixPatternForCurrency = negSuffix;
            this.posPrefixPatternForCurrency = posPrefix;
            this.posSuffixPatternForCurrency = posSuffix;
            this.patternType = type;
        }

        public String getNegPrefix() {
            return this.negPrefixPatternForCurrency;
        }

        public String getNegSuffix() {
            return this.negSuffixPatternForCurrency;
        }

        public String getPosPrefix() {
            return this.posPrefixPatternForCurrency;
        }

        public String getPosSuffix() {
            return this.posSuffixPatternForCurrency;
        }

        public int getPatternType() {
            return this.patternType;
        }
    }

    /* access modifiers changed from: package-private */
    public static class Unit {
        private final String prefix;
        private final String suffix;

        public Unit(String prefix2, String suffix2) {
            this.prefix = prefix2;
            this.suffix = suffix2;
        }

        public void writeSuffix(StringBuffer toAppendTo) {
            toAppendTo.append(this.suffix);
        }

        public void writePrefix(StringBuffer toAppendTo) {
            toAppendTo.append(this.prefix);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Unit)) {
                return false;
            }
            Unit other = (Unit) obj;
            if (!this.prefix.equals(other.prefix) || !this.suffix.equals(other.suffix)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return this.prefix + "/" + this.suffix;
        }
    }

    private void resetActualRounding() {
        if (this.roundingIncrementICU != null) {
            android.icu.math.BigDecimal byWidth = getMaximumFractionDigits() > 0 ? android.icu.math.BigDecimal.ONE.movePointLeft(getMaximumFractionDigits()) : android.icu.math.BigDecimal.ONE;
            if (this.roundingIncrementICU.compareTo(byWidth) >= 0) {
                this.actualRoundingIncrementICU = this.roundingIncrementICU;
            } else {
                this.actualRoundingIncrementICU = byWidth.equals(android.icu.math.BigDecimal.ONE) ? null : byWidth;
            }
        } else if (this.roundingMode == 6 || isScientificNotation()) {
            this.actualRoundingIncrementICU = null;
        } else if (getMaximumFractionDigits() > 0) {
            this.actualRoundingIncrementICU = android.icu.math.BigDecimal.ONE.movePointLeft(getMaximumFractionDigits());
        } else {
            this.actualRoundingIncrementICU = android.icu.math.BigDecimal.ONE;
        }
        android.icu.math.BigDecimal bigDecimal = this.actualRoundingIncrementICU;
        if (bigDecimal == null) {
            setRoundingDouble(0.0d);
            this.actualRoundingIncrement = null;
            return;
        }
        setRoundingDouble(bigDecimal.doubleValue());
        this.actualRoundingIncrement = this.actualRoundingIncrementICU.toBigDecimal();
    }

    private void setRoundingDouble(double newValue) {
        this.roundingDouble = newValue;
        double d = this.roundingDouble;
        if (d > 0.0d) {
            double rawRoundedReciprocal = 1.0d / d;
            this.roundingDoubleReciprocal = Math.rint(rawRoundedReciprocal);
            if (Math.abs(rawRoundedReciprocal - this.roundingDoubleReciprocal) > roundingIncrementEpsilon) {
                this.roundingDoubleReciprocal = 0.0d;
                return;
            }
            return;
        }
        this.roundingDoubleReciprocal = 0.0d;
    }
}
