package android.icu.text;

import android.icu.impl.ICUConfig;
import android.icu.impl.PatternProps;
import android.icu.lang.UCharacter;
import android.icu.math.MathContext;
import android.icu.text.NumberFormat;
import android.icu.text.PluralRules;
import android.icu.util.Currency;
import android.icu.util.CurrencyAmount;
import android.icu.util.ULocale;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
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

public class DecimalFormat_ICU58_Android extends NumberFormat {
    static final Unit NULL_UNIT = new Unit("", "");
    private static final UnicodeSet commaEquivalents;
    private static final UnicodeSet defaultGroupingSeparators;
    private static final UnicodeSet dotEquivalents;
    private static double epsilon = 1.0E-11d;
    static final UnicodeSet minusSigns;
    static final UnicodeSet plusSigns;
    private static final long serialVersionUID = 864413376551465018L;
    static final boolean skipExtendedSeparatorParsing = ICUConfig.get("android.icu.text.DecimalFormat.SkipExtendedSeparatorParsing", "false").equals("true");
    private static final UnicodeSet strictCommaEquivalents;
    private static final UnicodeSet strictDefaultGroupingSeparators;
    private static final UnicodeSet strictDotEquivalents;
    private int PARSE_MAX_EXPONENT = 1000;
    private transient BigDecimal actualRoundingIncrement = null;
    private transient android.icu.math.BigDecimal actualRoundingIncrementICU = null;
    private transient Set affixPatternsForCurrency = null;
    private ArrayList attributes = new ArrayList();
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

    private static boolean isBidiMark(int i) {
        return i == 8206 || i == 8207 || i == 1564;
    }

    private boolean isNegative(double d) {
        return d < 0.0d || (d == 0.0d && 1.0d / d < 0.0d);
    }

    public DecimalFormat_ICU58_Android() {
        ULocale uLocale = ULocale.getDefault(ULocale.Category.FORMAT);
        String pattern = NumberFormat.getPattern(uLocale, 0);
        this.symbols = new DecimalFormatSymbols(uLocale);
        setCurrency(Currency.getInstance(uLocale));
        applyPatternWithoutExpandAffix(pattern, false);
        if (this.currencySignCount == 3) {
            this.currencyPluralInfo = new CurrencyPluralInfo(uLocale);
        } else {
            expandAffixAdjustWidth(null);
        }
    }

    public DecimalFormat_ICU58_Android(String str, DecimalFormatSymbols decimalFormatSymbols) {
        createFromPatternAndSymbols(str, decimalFormatSymbols);
    }

    private void createFromPatternAndSymbols(String str, DecimalFormatSymbols decimalFormatSymbols) {
        this.symbols = (DecimalFormatSymbols) decimalFormatSymbols.clone();
        if (str.indexOf(164) >= 0) {
            setCurrencyForSymbols();
        }
        applyPatternWithoutExpandAffix(str, false);
        if (this.currencySignCount == 3) {
            this.currencyPluralInfo = new CurrencyPluralInfo(this.symbols.getULocale());
        } else {
            expandAffixAdjustWidth(null);
        }
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        format(d, stringBuffer, fieldPosition, false);
        return stringBuffer;
    }

    private double round(double d) {
        boolean isNegative = isNegative(d);
        if (isNegative) {
            d = -d;
        }
        double d2 = this.roundingDouble;
        return d2 > 0.0d ? round(d, d2, this.roundingDoubleReciprocal, this.roundingMode, isNegative) : d;
    }

    private double multiply(double d) {
        int i = this.multiplier;
        return i != 1 ? d * ((double) i) : d;
    }

    private StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition, boolean z) {
        double d2;
        double d3;
        boolean z2 = false;
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        if (Double.isNaN(d)) {
            if (fieldPosition.getField() == 0) {
                fieldPosition.setBeginIndex(stringBuffer.length());
            } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.INTEGER) {
                fieldPosition.setBeginIndex(stringBuffer.length());
            }
            stringBuffer.append(this.symbols.getNaN());
            if (z) {
                addAttribute(NumberFormat.Field.INTEGER, stringBuffer.length() - this.symbols.getNaN().length(), stringBuffer.length());
            }
            if (fieldPosition.getField() == 0) {
                fieldPosition.setEndIndex(stringBuffer.length());
            } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.INTEGER) {
                fieldPosition.setEndIndex(stringBuffer.length());
            }
            addPadding(stringBuffer, fieldPosition, 0, 0);
            return stringBuffer;
        }
        double multiply = multiply(d);
        boolean isNegative = isNegative(multiply);
        double round = round(multiply);
        if (Double.isInfinite(round)) {
            int appendAffix = appendAffix(stringBuffer, isNegative, true, fieldPosition, z);
            if (fieldPosition.getField() == 0) {
                fieldPosition.setBeginIndex(stringBuffer.length());
            } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.INTEGER) {
                fieldPosition.setBeginIndex(stringBuffer.length());
            }
            stringBuffer.append(this.symbols.getInfinity());
            if (z) {
                addAttribute(NumberFormat.Field.INTEGER, stringBuffer.length() - this.symbols.getInfinity().length(), stringBuffer.length());
            }
            if (fieldPosition.getField() == 0) {
                fieldPosition.setEndIndex(stringBuffer.length());
            } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.INTEGER) {
                fieldPosition.setEndIndex(stringBuffer.length());
            }
            addPadding(stringBuffer, fieldPosition, appendAffix, appendAffix(stringBuffer, isNegative, false, fieldPosition, z));
            return stringBuffer;
        }
        int precision = precision(false);
        if (this.useExponentialNotation && precision > 0 && round != 0.0d && this.roundingMode != 6) {
            int floor = (1 - precision) + ((int) Math.floor(Math.log10(Math.abs(round))));
            if (floor < 0) {
                d2 = android.icu.math.BigDecimal.ONE.movePointRight(-floor).doubleValue();
                d3 = 0.0d;
            } else {
                d3 = android.icu.math.BigDecimal.ONE.movePointRight(floor).doubleValue();
                d2 = 0.0d;
            }
            round = round(round, d3, d2, this.roundingMode, isNegative);
        }
        synchronized (this.digitList) {
            DigitList_Android digitList_Android = this.digitList;
            if (!this.useExponentialNotation && !areSignificantDigitsUsed()) {
                z2 = true;
            }
            digitList_Android.set(round, precision, z2);
            subformat(round, stringBuffer, fieldPosition, isNegative, false, z);
        }
        return stringBuffer;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004b, code lost:
        if (r2 == java.lang.Math.floor(r2)) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006a, code lost:
        if (r2 <= (r6 + android.icu.text.DecimalFormat_ICU58_Android.epsilon)) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0072, code lost:
        if (r6 <= (r2 + android.icu.text.DecimalFormat_ICU58_Android.epsilon)) goto L_0x00b6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static double round(double r14, double r16, double r18, int r20, boolean r21) {
        /*
        // Method dump skipped, instructions count: 190
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DecimalFormat_ICU58_Android.round(double, double, double, int, boolean):double");
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(long j, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return format(j, stringBuffer, fieldPosition, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.StringBuffer format(long r14, java.lang.StringBuffer r16, java.text.FieldPosition r17, boolean r18) {
        /*
        // Method dump skipped, instructions count: 143
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DecimalFormat_ICU58_Android.format(long, java.lang.StringBuffer, java.text.FieldPosition, boolean):java.lang.StringBuffer");
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(BigInteger bigInteger, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return format(bigInteger, stringBuffer, fieldPosition, false);
    }

    private StringBuffer format(BigInteger bigInteger, StringBuffer stringBuffer, FieldPosition fieldPosition, boolean z) {
        if (this.actualRoundingIncrementICU != null) {
            return format(new android.icu.math.BigDecimal(bigInteger), stringBuffer, fieldPosition);
        }
        int i = this.multiplier;
        boolean z2 = true;
        if (i != 1) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf((long) i));
        }
        synchronized (this.digitList) {
            this.digitList.set(bigInteger, precision(true));
            if (this.digitList.wasRounded()) {
                if (this.roundingMode == 7) {
                    throw new ArithmeticException("Rounding necessary");
                }
            }
            int intValue = bigInteger.intValue();
            if (bigInteger.signum() >= 0) {
                z2 = false;
            }
            subformat(intValue, stringBuffer, fieldPosition, z2, true, z);
        }
        return stringBuffer;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(BigDecimal bigDecimal, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        format(bigDecimal, stringBuffer, fieldPosition, false);
        return stringBuffer;
    }

    private StringBuffer format(BigDecimal bigDecimal, StringBuffer stringBuffer, FieldPosition fieldPosition, boolean z) {
        int i = this.multiplier;
        if (i != 1) {
            bigDecimal = bigDecimal.multiply(BigDecimal.valueOf((long) i));
        }
        BigDecimal bigDecimal2 = this.actualRoundingIncrement;
        if (bigDecimal2 != null) {
            bigDecimal = bigDecimal.divide(bigDecimal2, 0, this.roundingMode).multiply(this.actualRoundingIncrement);
        }
        synchronized (this.digitList) {
            this.digitList.set(bigDecimal, precision(false), !this.useExponentialNotation && !areSignificantDigitsUsed());
            if (this.digitList.wasRounded()) {
                if (this.roundingMode == 7) {
                    throw new ArithmeticException("Rounding necessary");
                }
            }
            subformat(bigDecimal.doubleValue(), stringBuffer, fieldPosition, bigDecimal.signum() < 0, false, z);
        }
        return stringBuffer;
    }

    @Override // android.icu.text.NumberFormat
    public StringBuffer format(android.icu.math.BigDecimal bigDecimal, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        int i = this.multiplier;
        if (i != 1) {
            bigDecimal = bigDecimal.multiply(android.icu.math.BigDecimal.valueOf((long) i), this.mathContext);
        }
        android.icu.math.BigDecimal bigDecimal2 = this.actualRoundingIncrementICU;
        if (bigDecimal2 != null) {
            bigDecimal = bigDecimal.divide(bigDecimal2, 0, this.roundingMode).multiply(this.actualRoundingIncrementICU, this.mathContext);
        }
        synchronized (this.digitList) {
            this.digitList.set(bigDecimal, precision(false), !this.useExponentialNotation && !areSignificantDigitsUsed());
            if (this.digitList.wasRounded()) {
                if (this.roundingMode == 7) {
                    throw new ArithmeticException("Rounding necessary");
                }
            }
            subformat(bigDecimal.doubleValue(), stringBuffer, fieldPosition, bigDecimal.signum() < 0, false, false);
        }
        return stringBuffer;
    }

    private boolean isGroupingPosition(int i) {
        byte b;
        if (!isGroupingUsed() || i <= 0 || (b = this.groupingSize) <= 0) {
            return false;
        }
        byte b2 = this.groupingSize2;
        if (b2 <= 0 || i <= b) {
            if (i % this.groupingSize != 0) {
                return false;
            }
        } else if ((i - b) % b2 != 0) {
            return false;
        }
        return true;
    }

    private int precision(boolean z) {
        if (areSignificantDigitsUsed()) {
            return getMaximumSignificantDigits();
        }
        if (this.useExponentialNotation) {
            return getMinimumIntegerDigits() + getMaximumFractionDigits();
        }
        if (z) {
            return 0;
        }
        return getMaximumFractionDigits();
    }

    private StringBuffer subformat(int i, StringBuffer stringBuffer, FieldPosition fieldPosition, boolean z, boolean z2, boolean z3) {
        if (this.currencySignCount == 3) {
            subformat(this.currencyPluralInfo.select(getFixedDecimal((double) i)), stringBuffer, fieldPosition, z, z2, z3);
            return stringBuffer;
        }
        subformat(stringBuffer, fieldPosition, z, z2, z3);
        return stringBuffer;
    }

    /* access modifiers changed from: package-private */
    public PluralRules.FixedDecimal getFixedDecimal(double d) {
        return getFixedDecimal(d, this.digitList);
    }

    /* access modifiers changed from: package-private */
    public PluralRules.FixedDecimal getFixedDecimal(double d, DigitList_Android digitList_Android) {
        int i;
        int i2;
        int i3 = digitList_Android.count;
        int i4 = digitList_Android.decimalAt;
        int i5 = i3 - i4;
        if (this.useSignificantDigits) {
            int i6 = this.maxSignificantDigits - i4;
            i = this.minSignificantDigits - i4;
            if (i < 0) {
                i = 0;
            }
            i2 = i6 < 0 ? 0 : i6;
        } else {
            i2 = getMaximumFractionDigits();
            i = getMinimumFractionDigits();
        }
        int i7 = i5 < i ? i : i5 > i2 ? i2 : i5;
        long j = 0;
        if (i7 > 0) {
            for (int max = Math.max(0, digitList_Android.decimalAt); max < digitList_Android.count; max++) {
                j = (j * 10) + ((long) (digitList_Android.digits[max] - 48));
            }
            for (int i8 = i7; i8 < i5; i8++) {
                j *= 10;
            }
        }
        return new PluralRules.FixedDecimal(d, i7, j);
    }

    private StringBuffer subformat(double d, StringBuffer stringBuffer, FieldPosition fieldPosition, boolean z, boolean z2, boolean z3) {
        if (this.currencySignCount == 3) {
            subformat(this.currencyPluralInfo.select(getFixedDecimal(d)), stringBuffer, fieldPosition, z, z2, z3);
            return stringBuffer;
        }
        subformat(stringBuffer, fieldPosition, z, z2, z3);
        return stringBuffer;
    }

    private StringBuffer subformat(String str, StringBuffer stringBuffer, FieldPosition fieldPosition, boolean z, boolean z2, boolean z3) {
        if (this.style == 6) {
            String currencyPluralPattern = this.currencyPluralInfo.getCurrencyPluralPattern(str);
            if (!this.formatPattern.equals(currencyPluralPattern)) {
                applyPatternWithoutExpandAffix(currencyPluralPattern, false);
            }
        }
        expandAffixAdjustWidth(str);
        subformat(stringBuffer, fieldPosition, z, z2, z3);
        return stringBuffer;
    }

    private StringBuffer subformat(StringBuffer stringBuffer, FieldPosition fieldPosition, boolean z, boolean z2, boolean z3) {
        if (this.digitList.isZero()) {
            this.digitList.decimalAt = 0;
        }
        int appendAffix = appendAffix(stringBuffer, z, true, fieldPosition, z3);
        if (this.useExponentialNotation) {
            subformatExponential(stringBuffer, fieldPosition, z3);
        } else {
            subformatFixed(stringBuffer, fieldPosition, z2, z3);
        }
        addPadding(stringBuffer, fieldPosition, appendAffix, appendAffix(stringBuffer, z, false, fieldPosition, z3));
        return stringBuffer;
    }

    /* JADX WARNING: Removed duplicated region for block: B:138:0x023e  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0246  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0257  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x025e  */
    /* JADX WARNING: Removed duplicated region for block: B:162:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0184  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void subformatFixed(java.lang.StringBuffer r24, java.text.FieldPosition r25, boolean r26, boolean r27) {
        /*
        // Method dump skipped, instructions count: 624
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DecimalFormat_ICU58_Android.subformatFixed(java.lang.StringBuffer, java.text.FieldPosition, boolean, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0317, code lost:
        if (r10 < 1) goto L_0x031b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void subformatExponential(java.lang.StringBuffer r21, java.text.FieldPosition r22, boolean r23) {
        /*
        // Method dump skipped, instructions count: 869
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DecimalFormat_ICU58_Android.subformatExponential(java.lang.StringBuffer, java.text.FieldPosition, boolean):void");
    }

    private final void addPadding(StringBuffer stringBuffer, FieldPosition fieldPosition, int i, int i2) {
        int length;
        int i3 = this.formatWidth;
        if (i3 > 0 && (length = i3 - stringBuffer.length()) > 0) {
            char[] cArr = new char[length];
            for (int i4 = 0; i4 < length; i4++) {
                cArr[i4] = this.pad;
            }
            int i5 = this.padPosition;
            if (i5 == 0) {
                stringBuffer.insert(0, cArr);
            } else if (i5 == 1) {
                stringBuffer.insert(i, cArr);
            } else if (i5 == 2) {
                stringBuffer.insert(stringBuffer.length() - i2, cArr);
            } else if (i5 == 3) {
                stringBuffer.append(cArr);
            }
            int i6 = this.padPosition;
            if (i6 == 0 || i6 == 1) {
                fieldPosition.setBeginIndex(fieldPosition.getBeginIndex() + length);
                fieldPosition.setEndIndex(fieldPosition.getEndIndex() + length);
            }
        }
    }

    @Override // android.icu.text.NumberFormat
    public Number parse(String str, ParsePosition parsePosition) {
        return (Number) parse(str, parsePosition, null);
    }

    private Object parse(String str, ParsePosition parsePosition, Currency[] currencyArr) {
        boolean[] zArr;
        char c;
        int i;
        int i2;
        Double d;
        int i3;
        int i4;
        int index = parsePosition.getIndex();
        int skipPadding = (this.formatWidth <= 0 || !((i4 = this.padPosition) == 0 || i4 == 1)) ? index : skipPadding(str, index);
        if (str.regionMatches(skipPadding, this.symbols.getNaN(), 0, this.symbols.getNaN().length())) {
            int length = skipPadding + this.symbols.getNaN().length();
            if (this.formatWidth > 0 && ((i3 = this.padPosition) == 2 || i3 == 3)) {
                length = skipPadding(str, length);
            }
            parsePosition.setIndex(length);
            return new Double(Double.NaN);
        }
        boolean[] zArr2 = new boolean[3];
        if (this.currencySignCount != 0) {
            if (!parseForCurrency(str, parsePosition, currencyArr, zArr2)) {
                return null;
            }
            zArr = zArr2;
            c = 2;
            i = 0;
            i2 = 1;
        } else if (currencyArr != null) {
            return null;
        } else {
            zArr = zArr2;
            c = 2;
            i = 0;
            i2 = 1;
            if (!subparse(str, parsePosition, this.digitList, zArr2, currencyArr, this.negPrefixPattern, this.negSuffixPattern, this.posPrefixPattern, this.posSuffixPattern, false, 0)) {
                parsePosition.setIndex(index);
                return null;
            }
        }
        if (zArr[i]) {
            d = new Double(zArr[i2] ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
        } else if (zArr[c]) {
            d = zArr[i2] ? new Double("0.0") : new Double("-0.0");
        } else if (zArr[i2] || !this.digitList.isZero()) {
            int i5 = this.multiplier;
            while (i5 % 10 == 0) {
                this.digitList.decimalAt -= i2;
                i5 /= 10;
            }
            if (this.parseBigDecimal || i5 != i2 || !this.digitList.isIntegral()) {
                android.icu.math.BigDecimal bigDecimalICU = this.digitList.getBigDecimalICU(zArr[i2]);
                d = i5 != i2 ? bigDecimalICU.divide(android.icu.math.BigDecimal.valueOf((long) i5), this.mathContext) : bigDecimalICU;
            } else {
                DigitList_Android digitList_Android = this.digitList;
                if (digitList_Android.decimalAt < 12) {
                    long j = 0;
                    if (digitList_Android.count > 0) {
                        int i6 = i;
                        while (true) {
                            DigitList_Android digitList_Android2 = this.digitList;
                            if (i6 >= digitList_Android2.count) {
                                break;
                            }
                            j = ((j * 10) + ((long) ((char) digitList_Android2.digits[i6]))) - 48;
                            i6++;
                        }
                        while (true) {
                            int i7 = i6 + 1;
                            if (i6 >= this.digitList.decimalAt) {
                                break;
                            }
                            j *= 10;
                            i6 = i7;
                        }
                        if (!zArr[i2]) {
                            j = -j;
                        }
                    }
                    d = Long.valueOf(j);
                } else {
                    BigInteger bigInteger = digitList_Android.getBigInteger(zArr[i2]);
                    int bitLength = bigInteger.bitLength();
                    d = bigInteger;
                    if (bitLength < 64) {
                        d = Long.valueOf(bigInteger.longValue());
                    }
                }
            }
        } else {
            d = new Double("-0.0");
        }
        return currencyArr != null ? new CurrencyAmount(d, currencyArr[i]) : d;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x017e  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0193  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean parseForCurrency(java.lang.String r28, java.text.ParsePosition r29, android.icu.util.Currency[] r30, boolean[] r31) {
        /*
        // Method dump skipped, instructions count: 424
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DecimalFormat_ICU58_Android.parseForCurrency(java.lang.String, java.text.ParsePosition, android.icu.util.Currency[], boolean[]):boolean");
    }

    private void setupCurrencyAffixForAllPatterns() {
        if (this.currencyPluralInfo == null) {
            this.currencyPluralInfo = new CurrencyPluralInfo(this.symbols.getULocale());
        }
        this.affixPatternsForCurrency = new HashSet();
        String str = this.formatPattern;
        applyPatternWithoutExpandAffix(NumberFormat.getPattern(this.symbols.getULocale(), 1), false);
        this.affixPatternsForCurrency.add(new AffixForCurrency(this.negPrefixPattern, this.negSuffixPattern, this.posPrefixPattern, this.posSuffixPattern, 0));
        Iterator pluralPatternIterator = this.currencyPluralInfo.pluralPatternIterator();
        HashSet hashSet = new HashSet();
        while (pluralPatternIterator.hasNext()) {
            String currencyPluralPattern = this.currencyPluralInfo.getCurrencyPluralPattern((String) pluralPatternIterator.next());
            if (currencyPluralPattern != null && !hashSet.contains(currencyPluralPattern)) {
                hashSet.add(currencyPluralPattern);
                applyPatternWithoutExpandAffix(currencyPluralPattern, false);
                this.affixPatternsForCurrency.add(new AffixForCurrency(this.negPrefixPattern, this.negSuffixPattern, this.posPrefixPattern, this.posSuffixPattern, 1));
            }
        }
        this.formatPattern = str;
    }

    static {
        UnicodeSet unicodeSet = new UnicodeSet(46, 46, 8228, 8228, 12290, 12290, 65042, 65042, 65106, 65106, 65294, 65294, 65377, 65377);
        unicodeSet.freeze();
        dotEquivalents = unicodeSet;
        UnicodeSet unicodeSet2 = new UnicodeSet(44, 44, 1548, 1548, 1643, 1643, 12289, 12289, 65040, 65041, 65104, 65105, 65292, 65292, 65380, 65380);
        unicodeSet2.freeze();
        commaEquivalents = unicodeSet2;
        UnicodeSet unicodeSet3 = new UnicodeSet(46, 46, 8228, 8228, 65106, 65106, 65294, 65294, 65377, 65377);
        unicodeSet3.freeze();
        strictDotEquivalents = unicodeSet3;
        UnicodeSet unicodeSet4 = new UnicodeSet(44, 44, 1643, 1643, 65040, 65040, 65104, 65104, 65292, 65292);
        unicodeSet4.freeze();
        strictCommaEquivalents = unicodeSet4;
        UnicodeSet unicodeSet5 = new UnicodeSet(32, 32, 39, 39, 44, 44, 46, 46, 160, 160, 1548, 1548, 1643, 1644, 8192, 8202, 8216, 8217, 8228, 8228, 8239, 8239, 8287, 8287, 12288, 12290, 65040, 65042, 65104, 65106, 65287, 65287, 65292, 65292, 65294, 65294, 65377, 65377, 65380, 65380);
        unicodeSet5.freeze();
        defaultGroupingSeparators = unicodeSet5;
        UnicodeSet unicodeSet6 = new UnicodeSet(32, 32, 39, 39, 44, 44, 46, 46, 160, 160, 1643, 1644, 8192, 8202, 8216, 8217, 8228, 8228, 8239, 8239, 8287, 8287, 12288, 12288, 65040, 65040, 65104, 65104, 65106, 65106, 65287, 65287, 65292, 65292, 65294, 65294, 65377, 65377);
        unicodeSet6.freeze();
        strictDefaultGroupingSeparators = unicodeSet6;
        UnicodeSet unicodeSet7 = new UnicodeSet(45, 45, 8315, 8315, 8331, 8331, 8722, 8722, 10134, 10134, 65123, 65123, 65293, 65293);
        unicodeSet7.freeze();
        minusSigns = unicodeSet7;
        UnicodeSet unicodeSet8 = new UnicodeSet(43, 43, 8314, 8314, 8330, 8330, 10133, 10133, 64297, 64297, 65122, 65122, 65291, 65291);
        unicodeSet8.freeze();
        plusSigns = unicodeSet8;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v11, types: [int] */
    /* JADX WARN: Type inference failed for: r17v4 */
    /* JADX WARN: Type inference failed for: r17v6 */
    /* JADX WARN: Type inference failed for: r17v7 */
    /* JADX WARN: Type inference failed for: r17v8 */
    /* JADX WARN: Type inference failed for: r17v9 */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01dc, code lost:
        r0 = r30.codePointAt(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01e1, code lost:
        if (r23 != false) goto L_0x0206;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01e7, code lost:
        if (r2.contains(r0) == false) goto L_0x0206;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01e9, code lost:
        if (r3 == false) goto L_0x01f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01ec, code lost:
        if (r6 != -1) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01ee, code lost:
        if (r11 == -1) goto L_0x01f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01f2, code lost:
        if (r15 == r29.groupingSize) goto L_0x01f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x01f9, code lost:
        if (isParseIntegerOnly() == false) goto L_0x01fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01fc, code lost:
        r32.decimalAt = r8;
        java.lang.String.valueOf(java.lang.Character.toChars(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0205, code lost:
        throw null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x020a, code lost:
        if (isGroupingUsed() == false) goto L_0x0228;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x020c, code lost:
        if (r25 != false) goto L_0x0228;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0212, code lost:
        if (r20.contains(r0) == false) goto L_0x0228;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0214, code lost:
        if (r23 == false) goto L_0x0217;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0217, code lost:
        if (r3 == false) goto L_0x0220;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0219, code lost:
        if (r26 == false) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x021c, code lost:
        if (r6 == -1) goto L_0x0220;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0220, code lost:
        java.lang.String.valueOf(java.lang.Character.toChars(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0227, code lost:
        throw null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0228, code lost:
        r22 = r3;
        r28 = r0;
        r13 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x023d, code lost:
        if (r30.regionMatches(true, r7, r17, 0, r17.length()) == false) goto L_0x02d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x023f, code lost:
        r0 = r17.length() + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0248, code lost:
        if (r0 >= r30.length()) goto L_0x0278;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x024a, code lost:
        r1 = r29.symbols.getPlusSignString();
        r2 = r29.symbols.getMinusSignString();
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x025f, code lost:
        if (r30.regionMatches(r0, r1, 0, r1.length()) == false) goto L_0x0267;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0261, code lost:
        r0 = r0 + r1.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x026f, code lost:
        if (r30.regionMatches(r0, r2, 0, r2.length()) == false) goto L_0x0279;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0271, code lost:
        r0 = r0 + r2.length();
        r1 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0278, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0279, code lost:
        r1 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x027a, code lost:
        r2 = new android.icu.text.DigitList_Android();
        r2.count = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0285, code lost:
        if (r0 >= r30.length()) goto L_0x0298;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0287, code lost:
        r3 = matchesDigit(r30, r0, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x028b, code lost:
        if (r3 <= 0) goto L_0x0298;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x028d, code lost:
        r2.append((char) (r14[r4] + 48));
        r0 = r0 + r3;
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x029a, code lost:
        if (r2.count <= 0) goto L_0x02d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x029c, code lost:
        if (r22 == false) goto L_0x02a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x029e, code lost:
        if (r25 == false) goto L_0x02a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x02a0, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x02a2, code lost:
        r3 = r2.count;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x02a6, code lost:
        if (r3 <= 10) goto L_0x02b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x02a8, code lost:
        if (r1 == 0) goto L_0x02ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x02aa, code lost:
        r33[2] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x02ae, code lost:
        r33[0] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x02b3, code lost:
        r2.decimalAt = r3;
        r2 = r2.getLong();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x02b9, code lost:
        if (r1 == 0) goto L_0x02bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x02bb, code lost:
        r18 = -r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x02bf, code lost:
        r18 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x02c1, code lost:
        r7 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x02d1, code lost:
        r13 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x02d2, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x011f, code lost:
        r22 = r3;
        r28 = r0;
        r8 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01a8, code lost:
        r22 = r3;
        r28 = r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x035c  */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x0374  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x037b  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x0391  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x039a  */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x039c  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x03a1  */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x03a4  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x03a7  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x03aa  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x03ad  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x03b1  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008e  */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean subparse(java.lang.String r30, java.text.ParsePosition r31, android.icu.text.DigitList_Android r32, boolean[] r33, android.icu.util.Currency[] r34, java.lang.String r35, java.lang.String r36, java.lang.String r37, java.lang.String r38, boolean r39, int r40) {
        /*
        // Method dump skipped, instructions count: 989
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DecimalFormat_ICU58_Android.subparse(java.lang.String, java.text.ParsePosition, android.icu.text.DigitList_Android, boolean[], android.icu.util.Currency[], java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, int):boolean");
    }

    private int matchesDigit(String str, int i, int[] iArr) {
        String[] digitStringsLocal = this.symbols.getDigitStringsLocal();
        for (int i2 = 0; i2 < 10; i2++) {
            int length = digitStringsLocal[i2].length();
            if (str.regionMatches(i, digitStringsLocal[i2], 0, length)) {
                iArr[0] = i2;
                return length;
            }
        }
        int codePointAt = str.codePointAt(i);
        iArr[0] = UCharacter.digit(codePointAt, 10);
        if (iArr[0] >= 0) {
            return Character.charCount(codePointAt);
        }
        return 0;
    }

    private UnicodeSet getEquivalentDecimals(String str, boolean z) {
        UnicodeSet unicodeSet = UnicodeSet.EMPTY;
        if (z) {
            if (strictDotEquivalents.contains(str)) {
                return strictDotEquivalents;
            }
            if (strictCommaEquivalents.contains(str)) {
                return strictCommaEquivalents;
            }
            return unicodeSet;
        } else if (dotEquivalents.contains(str)) {
            return dotEquivalents;
        } else {
            return commaEquivalents.contains(str) ? commaEquivalents : unicodeSet;
        }
    }

    private final int skipPadding(String str, int i) {
        while (i < str.length() && str.charAt(i) == this.pad) {
            i++;
        }
        return i;
    }

    private int compareAffix(String str, int i, boolean z, boolean z2, String str2, boolean z3, int i2, Currency[] currencyArr) {
        String str3;
        if (currencyArr != null || this.currencyChoice != null || (this.currencySignCount != 0 && z3)) {
            return compareComplexAffix(str2, str, i, i2, currencyArr);
        }
        if (z2) {
            return compareSimpleAffix(z ? this.negativePrefix : this.positivePrefix, str, i);
        }
        if (z) {
            str3 = this.negativeSuffix;
        } else {
            str3 = this.positiveSuffix;
        }
        return compareSimpleAffix(str3, str, i);
    }

    private static String trimMarksFromAffix(String str) {
        boolean z;
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                z = false;
                break;
            } else if (isBidiMark(str.charAt(i))) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) str, 0, i);
        for (int i2 = i + 1; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (!isBidiMark(charAt)) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    private static int compareSimpleAffix(String str, String str2, int i) {
        if (str.length() > 1) {
            str = trimMarksFromAffix(str);
        }
        int i2 = i;
        int i3 = 0;
        while (i3 < str.length()) {
            int charAt = UTF16.charAt(str, i3);
            int charCount = UTF16.getCharCount(charAt);
            if (PatternProps.isWhiteSpace(charAt)) {
                boolean z = false;
                while (true) {
                    if (i2 < str2.length()) {
                        int charAt2 = UTF16.charAt(str2, i2);
                        if (charAt2 != charAt) {
                            if (!isBidiMark(charAt2)) {
                                break;
                            }
                            i2++;
                        } else {
                            i3 += charCount;
                            i2 += charCount;
                            if (i3 == str.length()) {
                                break;
                            }
                            charAt = UTF16.charAt(str, i3);
                            charCount = UTF16.getCharCount(charAt);
                            if (!PatternProps.isWhiteSpace(charAt)) {
                                break;
                            }
                            z = true;
                        }
                    } else {
                        break;
                    }
                }
                z = true;
                int skipPatternWhiteSpace = skipPatternWhiteSpace(str, i3);
                int skipUWhiteSpace = skipUWhiteSpace(str2, i2);
                if (skipUWhiteSpace == i2 && !z) {
                    return -1;
                }
                i3 = skipUWhiteSpace(str, skipPatternWhiteSpace);
                i2 = skipUWhiteSpace;
            } else {
                int i4 = i3;
                boolean z2 = false;
                while (i2 < str2.length()) {
                    int charAt3 = UTF16.charAt(str2, i2);
                    if (z2 || !equalWithSignCompatibility(charAt3, charAt)) {
                        if (!isBidiMark(charAt3)) {
                            break;
                        }
                        i2++;
                    } else {
                        i4 += charCount;
                        i2 += charCount;
                        z2 = true;
                    }
                }
                if (!z2) {
                    return -1;
                }
                i3 = i4;
            }
        }
        return i2 - i;
    }

    private static boolean equalWithSignCompatibility(int i, int i2) {
        return i == i2 || (minusSigns.contains(i) && minusSigns.contains(i2)) || (plusSigns.contains(i) && plusSigns.contains(i2));
    }

    private static int skipPatternWhiteSpace(String str, int i) {
        while (i < str.length()) {
            int charAt = UTF16.charAt(str, i);
            if (!PatternProps.isWhiteSpace(charAt)) {
                break;
            }
            i += UTF16.getCharCount(charAt);
        }
        return i;
    }

    private static int skipUWhiteSpace(String str, int i) {
        while (i < str.length()) {
            int charAt = UTF16.charAt(str, i);
            if (!UCharacter.isUWhiteSpace(charAt)) {
                break;
            }
            i += UTF16.getCharCount(charAt);
        }
        return i;
    }

    private static int skipBidiMarks(String str, int i) {
        while (i < str.length()) {
            int charAt = UTF16.charAt(str, i);
            if (!isBidiMark(charAt)) {
                break;
            }
            i += UTF16.getCharCount(charAt);
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00be, code lost:
        if (r3.compareTo(getEffectiveCurrency().getCurrencyCode()) != 0) goto L_0x00c7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int compareComplexAffix(java.lang.String r8, java.lang.String r9, int r10, int r11, android.icu.util.Currency[] r12) {
        /*
        // Method dump skipped, instructions count: 249
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DecimalFormat_ICU58_Android.compareComplexAffix(java.lang.String, java.lang.String, int, int, android.icu.util.Currency[]):int");
    }

    static final int match(String str, int i, int i2) {
        if (i >= 0 && i < str.length()) {
            int skipBidiMarks = skipBidiMarks(str, i);
            if (PatternProps.isWhiteSpace(i2)) {
                int skipPatternWhiteSpace = skipPatternWhiteSpace(str, skipBidiMarks);
                if (skipPatternWhiteSpace == skipBidiMarks) {
                    return -1;
                }
                return skipPatternWhiteSpace;
            } else if (skipBidiMarks < str.length() && UTF16.charAt(str, skipBidiMarks) == i2) {
                return skipBidiMarks(str, skipBidiMarks + UTF16.getCharCount(i2));
            }
        }
        return -1;
    }

    static final int match(String str, int i, String str2) {
        int i2 = 0;
        while (i2 < str2.length() && i >= 0) {
            int charAt = UTF16.charAt(str2, i2);
            i2 += UTF16.getCharCount(charAt);
            if (!isBidiMark(charAt)) {
                i = match(str, i, charAt);
                if (PatternProps.isWhiteSpace(charAt)) {
                    i2 = skipPatternWhiteSpace(str2, i2);
                }
            }
        }
        return i;
    }

    public DecimalFormatSymbols getDecimalFormatSymbols() {
        try {
            return (DecimalFormatSymbols) this.symbols.clone();
        } catch (Exception unused) {
            return null;
        }
    }

    private void setCurrencyForSymbols() {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(this.symbols.getULocale());
        if (!this.symbols.getCurrencySymbol().equals(decimalFormatSymbols.getCurrencySymbol()) || !this.symbols.getInternationalCurrencySymbol().equals(decimalFormatSymbols.getInternationalCurrencySymbol())) {
            setCurrency(null);
        } else {
            setCurrency(Currency.getInstance(this.symbols.getULocale()));
        }
    }

    public String getPositivePrefix() {
        return this.positivePrefix;
    }

    public BigDecimal getRoundingIncrement() {
        android.icu.math.BigDecimal bigDecimal = this.roundingIncrementICU;
        if (bigDecimal == null) {
            return null;
        }
        return bigDecimal.toBigDecimal();
    }

    public void setRoundingIncrement(android.icu.math.BigDecimal bigDecimal) {
        int compareTo = bigDecimal == null ? 0 : bigDecimal.compareTo(android.icu.math.BigDecimal.ZERO);
        if (compareTo >= 0) {
            if (compareTo == 0) {
                setInternalRoundingIncrement(null);
            } else {
                setInternalRoundingIncrement(bigDecimal);
            }
            resetActualRounding();
            return;
        }
        throw new IllegalArgumentException("Illegal rounding increment");
    }

    public void setRoundingIncrement(double d) {
        if (d >= 0.0d) {
            if (d == 0.0d) {
                setInternalRoundingIncrement(null);
            } else {
                setInternalRoundingIncrement(android.icu.math.BigDecimal.valueOf(d));
            }
            resetActualRounding();
            return;
        }
        throw new IllegalArgumentException("Illegal rounding increment");
    }

    public boolean isScientificNotation() {
        return this.useExponentialNotation;
    }

    public int getGroupingSize() {
        return this.groupingSize;
    }

    public boolean isDecimalPatternMatchRequired() {
        return this.parseRequireDecimalPoint;
    }

    public void setDecimalSeparatorAlwaysShown(boolean z) {
        this.decimalSeparatorAlwaysShown = z;
    }

    @Override // java.text.Format, android.icu.text.NumberFormat
    public Object clone() {
        try {
            DecimalFormat_ICU58_Android decimalFormat_ICU58_Android = (DecimalFormat_ICU58_Android) super.clone();
            decimalFormat_ICU58_Android.symbols = (DecimalFormatSymbols) this.symbols.clone();
            decimalFormat_ICU58_Android.digitList = new DigitList_Android();
            if (this.currencyPluralInfo != null) {
                decimalFormat_ICU58_Android.currencyPluralInfo = (CurrencyPluralInfo) this.currencyPluralInfo.clone();
            }
            decimalFormat_ICU58_Android.attributes = new ArrayList();
            decimalFormat_ICU58_Android.currencyUsage = this.currencyUsage;
            return decimalFormat_ICU58_Android;
        } catch (Exception unused) {
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
        DecimalFormat_ICU58_Android decimalFormat_ICU58_Android = (DecimalFormat_ICU58_Android) obj;
        if (this.currencySignCount != decimalFormat_ICU58_Android.currencySignCount) {
            return false;
        }
        if ((this.style == 6 && (!equals(this.posPrefixPattern, decimalFormat_ICU58_Android.posPrefixPattern) || !equals(this.posSuffixPattern, decimalFormat_ICU58_Android.posSuffixPattern) || !equals(this.negPrefixPattern, decimalFormat_ICU58_Android.negPrefixPattern) || !equals(this.negSuffixPattern, decimalFormat_ICU58_Android.negSuffixPattern))) || this.multiplier != decimalFormat_ICU58_Android.multiplier || this.groupingSize != decimalFormat_ICU58_Android.groupingSize || this.groupingSize2 != decimalFormat_ICU58_Android.groupingSize2 || this.decimalSeparatorAlwaysShown != decimalFormat_ICU58_Android.decimalSeparatorAlwaysShown || (z = this.useExponentialNotation) != decimalFormat_ICU58_Android.useExponentialNotation) {
            return false;
        }
        if ((z && this.minExponentDigits != decimalFormat_ICU58_Android.minExponentDigits) || (z2 = this.useSignificantDigits) != decimalFormat_ICU58_Android.useSignificantDigits) {
            return false;
        }
        if ((!z2 || (this.minSignificantDigits == decimalFormat_ICU58_Android.minSignificantDigits && this.maxSignificantDigits == decimalFormat_ICU58_Android.maxSignificantDigits)) && this.symbols.equals(decimalFormat_ICU58_Android.symbols) && Objects.equals(this.currencyPluralInfo, decimalFormat_ICU58_Android.currencyPluralInfo) && this.currencyUsage.equals(decimalFormat_ICU58_Android.currencyUsage)) {
            return true;
        }
        return false;
    }

    private boolean equals(String str, String str2) {
        if (str == null || str2 == null) {
            if (str == null && str2 == null) {
                return true;
            }
            return false;
        } else if (str.equals(str2)) {
            return true;
        } else {
            return unquote(str).equals(unquote(str2));
        }
    }

    private String unquote(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 1;
            char charAt = str.charAt(i);
            if (charAt != '\'') {
                sb.append(charAt);
            }
            i = i2;
        }
        return sb.toString();
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

    private void expandAffixes(String str) {
        this.currencyChoice = null;
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = this.posPrefixPattern;
        if (str2 != null) {
            expandAffix(str2, str, stringBuffer);
            this.positivePrefix = stringBuffer.toString();
        }
        String str3 = this.posSuffixPattern;
        if (str3 != null) {
            expandAffix(str3, str, stringBuffer);
            this.positiveSuffix = stringBuffer.toString();
        }
        String str4 = this.negPrefixPattern;
        if (str4 != null) {
            expandAffix(str4, str, stringBuffer);
            this.negativePrefix = stringBuffer.toString();
        }
        String str5 = this.negSuffixPattern;
        if (str5 != null) {
            expandAffix(str5, str, stringBuffer);
            this.negativeSuffix = stringBuffer.toString();
        }
    }

    private void expandAffix(String str, String str2, StringBuffer stringBuffer) {
        String str3;
        stringBuffer.setLength(0);
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 1;
            char charAt = str.charAt(i);
            if (charAt == '\'') {
                while (true) {
                    int indexOf = str.indexOf(39, i2);
                    if (indexOf == i2) {
                        stringBuffer.append('\'');
                        i = indexOf + 1;
                        break;
                    } else if (indexOf > i2) {
                        stringBuffer.append(str.substring(i2, indexOf));
                        i = indexOf + 1;
                        if (i >= str.length() || str.charAt(i) != '\'') {
                            break;
                        }
                        stringBuffer.append('\'');
                        i2 = i + 1;
                    } else {
                        throw new RuntimeException();
                    }
                }
            } else {
                if (charAt == '%') {
                    stringBuffer.append(this.symbols.getPercentString());
                } else if (charAt == '-') {
                    stringBuffer.append(this.symbols.getMinusSignString());
                } else if (charAt == 164) {
                    boolean z = true;
                    boolean z2 = i2 < str.length() && str.charAt(i2) == 164;
                    if (!z2 || (i2 = i2 + 1) >= str.length() || str.charAt(i2) != 164) {
                        z = false;
                    } else {
                        i2++;
                        z2 = false;
                    }
                    Currency currency = getCurrency();
                    if (currency != null) {
                        if (z && str2 != null) {
                            str3 = currency.getName(this.symbols.getULocale(), 2, str2, (boolean[]) null);
                        } else if (!z2) {
                            str3 = currency.getName(this.symbols.getULocale(), 0, null);
                        } else {
                            str3 = currency.getCurrencyCode();
                        }
                    } else if (z2) {
                        str3 = this.symbols.getInternationalCurrencySymbol();
                    } else {
                        str3 = this.symbols.getCurrencySymbol();
                    }
                    stringBuffer.append(str3);
                } else if (charAt != 8240) {
                    stringBuffer.append(charAt);
                } else {
                    stringBuffer.append(this.symbols.getPerMillString());
                }
                i = i2;
            }
        }
    }

    private int appendAffix(StringBuffer stringBuffer, boolean z, boolean z2, FieldPosition fieldPosition, boolean z3) {
        String str;
        String str2;
        String str3;
        if (this.currencyChoice != null) {
            if (z2) {
                str3 = z ? this.negPrefixPattern : this.posPrefixPattern;
            } else {
                str3 = z ? this.negSuffixPattern : this.posSuffixPattern;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            expandAffix(str3, null, stringBuffer2);
            stringBuffer.append(stringBuffer2);
            return stringBuffer2.length();
        }
        if (z2) {
            str2 = z ? this.negativePrefix : this.positivePrefix;
            str = z ? this.negPrefixPattern : this.posPrefixPattern;
        } else {
            str2 = z ? this.negativeSuffix : this.positiveSuffix;
            str = z ? this.negSuffixPattern : this.posSuffixPattern;
        }
        if (z3) {
            int indexOf = str2.indexOf(this.symbols.getCurrencySymbol());
            if (indexOf > -1) {
                formatAffix2Attribute(z2, NumberFormat.Field.CURRENCY, stringBuffer, indexOf, this.symbols.getCurrencySymbol().length());
            }
            int indexOf2 = str2.indexOf(this.symbols.getMinusSignString());
            if (indexOf2 > -1) {
                formatAffix2Attribute(z2, NumberFormat.Field.SIGN, stringBuffer, indexOf2, this.symbols.getMinusSignString().length());
            }
            int indexOf3 = str2.indexOf(this.symbols.getPercentString());
            if (indexOf3 > -1) {
                formatAffix2Attribute(z2, NumberFormat.Field.PERCENT, stringBuffer, indexOf3, this.symbols.getPercentString().length());
            }
            int indexOf4 = str2.indexOf(this.symbols.getPerMillString());
            if (indexOf4 > -1) {
                formatAffix2Attribute(z2, NumberFormat.Field.PERMILLE, stringBuffer, indexOf4, this.symbols.getPerMillString().length());
            }
            int indexOf5 = str.indexOf("¤¤¤");
            if (indexOf5 > -1) {
                formatAffix2Attribute(z2, NumberFormat.Field.CURRENCY, stringBuffer, indexOf5, str2.length() - indexOf5);
            }
        }
        if (fieldPosition.getFieldAttribute() == NumberFormat.Field.SIGN) {
            DecimalFormatSymbols decimalFormatSymbols = this.symbols;
            String minusSignString = z ? decimalFormatSymbols.getMinusSignString() : decimalFormatSymbols.getPlusSignString();
            int indexOf6 = str2.indexOf(minusSignString);
            if (indexOf6 > -1) {
                int length = stringBuffer.length() + indexOf6;
                fieldPosition.setBeginIndex(length);
                fieldPosition.setEndIndex(length + minusSignString.length());
            }
        } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.PERCENT) {
            int indexOf7 = str2.indexOf(this.symbols.getPercentString());
            if (indexOf7 > -1) {
                int length2 = stringBuffer.length() + indexOf7;
                fieldPosition.setBeginIndex(length2);
                fieldPosition.setEndIndex(length2 + this.symbols.getPercentString().length());
            }
        } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.PERMILLE) {
            int indexOf8 = str2.indexOf(this.symbols.getPerMillString());
            if (indexOf8 > -1) {
                int length3 = stringBuffer.length() + indexOf8;
                fieldPosition.setBeginIndex(length3);
                fieldPosition.setEndIndex(length3 + this.symbols.getPerMillString().length());
            }
        } else if (fieldPosition.getFieldAttribute() == NumberFormat.Field.CURRENCY) {
            if (str2.indexOf(this.symbols.getCurrencySymbol()) > -1) {
                String currencySymbol = this.symbols.getCurrencySymbol();
                int length4 = stringBuffer.length() + str2.indexOf(currencySymbol);
                fieldPosition.setBeginIndex(length4);
                fieldPosition.setEndIndex(currencySymbol.length() + length4);
            } else if (str2.indexOf(this.symbols.getInternationalCurrencySymbol()) > -1) {
                String internationalCurrencySymbol = this.symbols.getInternationalCurrencySymbol();
                int length5 = stringBuffer.length() + str2.indexOf(internationalCurrencySymbol);
                fieldPosition.setBeginIndex(length5);
                fieldPosition.setEndIndex(internationalCurrencySymbol.length() + length5);
            } else if (str.indexOf("¤¤¤") > -1) {
                fieldPosition.setBeginIndex(stringBuffer.length() + str.indexOf("¤¤¤"));
                fieldPosition.setEndIndex(stringBuffer.length() + str2.length());
            }
        }
        stringBuffer.append(str2);
        return str2.length();
    }

    private void formatAffix2Attribute(boolean z, NumberFormat.Field field, StringBuffer stringBuffer, int i, int i2) {
        if (!z) {
            i += stringBuffer.length();
        }
        addAttribute(field, i, i2 + i);
    }

    private void addAttribute(NumberFormat.Field field, int i, int i2) {
        FieldPosition fieldPosition = new FieldPosition(field);
        fieldPosition.setBeginIndex(i);
        fieldPosition.setEndIndex(i2);
        this.attributes.add(fieldPosition);
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        return formatToCharacterIterator(obj, NULL_UNIT);
    }

    /* access modifiers changed from: package-private */
    public AttributedCharacterIterator formatToCharacterIterator(Object obj, Unit unit) {
        if (obj instanceof Number) {
            Number number = (Number) obj;
            StringBuffer stringBuffer = new StringBuffer();
            unit.writePrefix(stringBuffer);
            this.attributes.clear();
            if (obj instanceof BigInteger) {
                format((BigInteger) number, stringBuffer, new FieldPosition(0), true);
            } else if (obj instanceof BigDecimal) {
                format((BigDecimal) number, stringBuffer, new FieldPosition(0), true);
            } else if (obj instanceof Double) {
                format(number.doubleValue(), stringBuffer, new FieldPosition(0), true);
            } else if ((obj instanceof Integer) || (obj instanceof Long)) {
                format(number.longValue(), stringBuffer, new FieldPosition(0), true);
            } else {
                throw new IllegalArgumentException();
            }
            unit.writeSuffix(stringBuffer);
            AttributedString attributedString = new AttributedString(stringBuffer.toString());
            for (int i = 0; i < this.attributes.size(); i++) {
                FieldPosition fieldPosition = (FieldPosition) this.attributes.get(i);
                Format.Field fieldAttribute = fieldPosition.getFieldAttribute();
                attributedString.addAttribute(fieldAttribute, fieldAttribute, fieldPosition.getBeginIndex(), fieldPosition.getEndIndex());
            }
            return attributedString.getIterator();
        }
        throw new IllegalArgumentException();
    }

    private void appendAffixPattern(StringBuffer stringBuffer, boolean z, boolean z2, boolean z3) {
        String str;
        int i;
        String str2;
        if (z2) {
            str = z ? this.negPrefixPattern : this.posPrefixPattern;
        } else {
            str = z ? this.negSuffixPattern : this.posSuffixPattern;
        }
        int i2 = 0;
        if (str == null) {
            if (z2) {
                str2 = z ? this.negativePrefix : this.positivePrefix;
            } else {
                str2 = z ? this.negativeSuffix : this.positiveSuffix;
            }
            stringBuffer.append('\'');
            while (i2 < str2.length()) {
                char charAt = str2.charAt(i2);
                if (charAt == '\'') {
                    stringBuffer.append(charAt);
                }
                stringBuffer.append(charAt);
                i2++;
            }
            stringBuffer.append('\'');
        } else if (!z3) {
            stringBuffer.append(str);
        } else {
            while (i2 < str.length()) {
                char charAt2 = str.charAt(i2);
                if (charAt2 == '%') {
                    charAt2 = this.symbols.getPercent();
                } else if (charAt2 == '\'') {
                    i = str.indexOf(39, i2 + 1);
                    if (i >= 0) {
                        stringBuffer.append(str.substring(i2, i + 1));
                        i2 = i + 1;
                    } else {
                        throw new IllegalArgumentException("Malformed affix pattern: " + str);
                    }
                } else if (charAt2 == '-') {
                    charAt2 = this.symbols.getMinusSign();
                } else if (charAt2 == 8240) {
                    charAt2 = this.symbols.getPerMill();
                }
                if (charAt2 == this.symbols.getDecimalSeparator() || charAt2 == this.symbols.getGroupingSeparator()) {
                    stringBuffer.append('\'');
                    stringBuffer.append(charAt2);
                    stringBuffer.append('\'');
                } else {
                    stringBuffer.append(charAt2);
                }
                i = i2;
                i2 = i + 1;
            }
        }
    }

    private String toPattern(boolean z) {
        String str;
        String str2;
        int i;
        char c;
        char c2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        char c3;
        int i9;
        int i10;
        byte b;
        StringBuffer stringBuffer = new StringBuffer();
        char zeroDigit = z ? this.symbols.getZeroDigit() : '0';
        char digit = z ? this.symbols.getDigit() : '#';
        boolean areSignificantDigitsUsed = areSignificantDigitsUsed();
        int i11 = 0;
        char significantDigit = areSignificantDigitsUsed ? z ? this.symbols.getSignificantDigit() : '@' : 0;
        char groupingSeparator = z ? this.symbols.getGroupingSeparator() : ',';
        int i12 = this.formatWidth > 0 ? this.padPosition : -1;
        if (this.formatWidth > 0) {
            StringBuffer stringBuffer2 = new StringBuffer(2);
            stringBuffer2.append(z ? this.symbols.getPadEscape() : '*');
            stringBuffer2.append(this.pad);
            str = stringBuffer2.toString();
        } else {
            str = null;
        }
        android.icu.math.BigDecimal bigDecimal = this.roundingIncrementICU;
        if (bigDecimal != null) {
            int scale = bigDecimal.scale();
            str2 = this.roundingIncrementICU.movePointRight(scale).toString();
            i = str2.length() - scale;
        } else {
            str2 = null;
            i = 0;
        }
        int i13 = 0;
        for (int i14 = 2; i13 < i14; i14 = 2) {
            if (i12 == 0) {
                stringBuffer.append(str);
            }
            appendAffixPattern(stringBuffer, i13 != 0 ? true : i11, true, z);
            if (i12 == 1) {
                stringBuffer.append(str);
            }
            int length = stringBuffer.length();
            int max = isGroupingUsed() ? Math.max(i11, (int) this.groupingSize) : i11;
            if (max <= 0 || (b = this.groupingSize2) <= 0) {
                c = significantDigit;
            } else {
                c = significantDigit;
                if (b != this.groupingSize) {
                    max += b;
                }
            }
            if (areSignificantDigitsUsed) {
                i5 = getMinimumSignificantDigits();
                i4 = getMaximumSignificantDigits();
                c2 = digit;
                i2 = i12;
                i3 = i4;
            } else {
                i5 = getMinimumIntegerDigits();
                i4 = getMaximumIntegerDigits();
                c2 = digit;
                i2 = i12;
                i3 = 0;
            }
            if (this.useExponentialNotation) {
                i6 = i4 > 8 ? 1 : i4;
            } else if (areSignificantDigitsUsed) {
                i6 = Math.max(i4, max + 1);
            } else {
                i6 = Math.max(Math.max(max, getMinimumIntegerDigits()), i) + 1;
            }
            int i15 = i6;
            while (i15 > 0) {
                if (!this.useExponentialNotation && i15 < i6 && isGroupingPosition(i15)) {
                    stringBuffer.append(groupingSeparator);
                }
                if (areSignificantDigitsUsed) {
                    stringBuffer.append((i3 < i15 || i15 <= i3 - i5) ? c2 : c);
                    i9 = i3;
                } else {
                    if (str2 == null || (i10 = i - i15) < 0) {
                        i9 = i3;
                    } else {
                        i9 = i3;
                        if (i10 < str2.length()) {
                            stringBuffer.append((char) ((str2.charAt(i10) - '0') + zeroDigit));
                        }
                    }
                    stringBuffer.append(i15 <= i5 ? zeroDigit : c2);
                }
                i15--;
                i3 = i9;
            }
            if (!areSignificantDigitsUsed) {
                if (getMaximumFractionDigits() > 0 || this.decimalSeparatorAlwaysShown) {
                    stringBuffer.append(z ? this.symbols.getDecimalSeparator() : '.');
                }
                int i16 = i;
                int i17 = 0;
                while (i17 < getMaximumFractionDigits()) {
                    if (str2 == null || i16 >= str2.length()) {
                        stringBuffer.append(i17 < getMinimumFractionDigits() ? zeroDigit : c2);
                    } else {
                        if (i16 < 0) {
                            c3 = zeroDigit;
                        } else {
                            c3 = (char) ((str2.charAt(i16) - '0') + zeroDigit);
                        }
                        stringBuffer.append(c3);
                        i16++;
                    }
                    i17++;
                }
            }
            if (this.useExponentialNotation) {
                if (z) {
                    stringBuffer.append(this.symbols.getExponentSeparator());
                } else {
                    stringBuffer.append('E');
                }
                if (this.exponentSignAlwaysShown) {
                    stringBuffer.append(z ? this.symbols.getPlusSign() : '+');
                }
                for (int i18 = 0; i18 < this.minExponentDigits; i18++) {
                    stringBuffer.append(zeroDigit);
                }
            }
            if (str != null && !this.useExponentialNotation) {
                int length2 = (this.formatWidth - stringBuffer.length()) + length;
                if (i13 == 0) {
                    i8 = this.positivePrefix.length();
                    i7 = this.positiveSuffix.length();
                } else {
                    i8 = this.negativePrefix.length();
                    i7 = this.negativeSuffix.length();
                }
                int i19 = length2 - (i8 + i7);
                while (i19 > 0) {
                    stringBuffer.insert(length, c2);
                    i6++;
                    i19--;
                    if (i19 > 1 && isGroupingPosition(i6)) {
                        stringBuffer.insert(length, groupingSeparator);
                        i19--;
                    }
                    c2 = c2;
                }
            }
            boolean z2 = true;
            if (i2 == 2) {
                stringBuffer.append(str);
            }
            if (i13 == 0) {
                z2 = false;
            }
            appendAffixPattern(stringBuffer, z2, false, z);
            if (i2 == 3) {
                stringBuffer.append(str);
            }
            if (i13 == 0) {
                if (this.negativeSuffix.equals(this.positiveSuffix)) {
                    if (this.negativePrefix.equals('-' + this.positivePrefix)) {
                        break;
                    }
                }
                stringBuffer.append(z ? this.symbols.getPatternSeparator() : ';');
            }
            i13++;
            i12 = i2;
            digit = c2;
            i11 = 0;
            significantDigit = c;
        }
        return stringBuffer.toString();
    }

    private void applyPattern(String str, boolean z) {
        applyPatternWithoutExpandAffix(str, z);
        expandAffixAdjustWidth(null);
    }

    private void expandAffixAdjustWidth(String str) {
        expandAffixes(str);
        int i = this.formatWidth;
        if (i > 0) {
            this.formatWidth = i + this.positivePrefix.length() + this.positiveSuffix.length();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r10v9, resolved type: int */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0452, code lost:
        throw null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0274  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0286  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0173  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyPatternWithoutExpandAffix(java.lang.String r53, boolean r54) {
        /*
        // Method dump skipped, instructions count: 1738
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DecimalFormat_ICU58_Android.applyPatternWithoutExpandAffix(java.lang.String, boolean):void");
    }

    private void patternError(String str, String str2) {
        throw new IllegalArgumentException(str + " in pattern \"" + str2 + '\"');
    }

    @Override // android.icu.text.NumberFormat
    public void setMaximumIntegerDigits(int i) {
        super.setMaximumIntegerDigits(Math.min(i, 2000000000));
    }

    @Override // android.icu.text.NumberFormat
    public void setMinimumIntegerDigits(int i) {
        super.setMinimumIntegerDigits(Math.min(i, 309));
    }

    public int getMinimumSignificantDigits() {
        return this.minSignificantDigits;
    }

    public int getMaximumSignificantDigits() {
        return this.maxSignificantDigits;
    }

    public void setMinimumSignificantDigits(int i) {
        if (i < 1) {
            i = 1;
        }
        int max = Math.max(this.maxSignificantDigits, i);
        this.minSignificantDigits = i;
        this.maxSignificantDigits = max;
        setSignificantDigitsUsed(true);
    }

    public void setMaximumSignificantDigits(int i) {
        if (i < 1) {
            i = 1;
        }
        this.minSignificantDigits = Math.min(this.minSignificantDigits, i);
        this.maxSignificantDigits = i;
        setSignificantDigitsUsed(true);
    }

    public boolean areSignificantDigitsUsed() {
        return this.useSignificantDigits;
    }

    public void setSignificantDigitsUsed(boolean z) {
        this.useSignificantDigits = z;
    }

    @Override // android.icu.text.NumberFormat
    public void setCurrency(Currency currency) {
        super.setCurrency(currency);
        if (currency != null) {
            String name = currency.getName(this.symbols.getULocale(), 0, null);
            this.symbols.setCurrency(currency);
            this.symbols.setCurrencySymbol(name);
        }
        if (this.currencySignCount != 0) {
            if (currency != null) {
                setRoundingIncrement(currency.getRoundingIncrement(this.currencyUsage));
                int defaultFractionDigits = currency.getDefaultFractionDigits(this.currencyUsage);
                setMinimumFractionDigits(defaultFractionDigits);
                setMaximumFractionDigits(defaultFractionDigits);
            }
            if (this.currencySignCount != 3) {
                expandAffixes(null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Currency getEffectiveCurrency() {
        Currency currency = getCurrency();
        return currency == null ? Currency.getInstance(this.symbols.getInternationalCurrencySymbol()) : currency;
    }

    @Override // android.icu.text.NumberFormat
    public void setMaximumFractionDigits(int i) {
        _setMaximumFractionDigits(i);
        resetActualRounding();
    }

    private void _setMaximumFractionDigits(int i) {
        super.setMaximumFractionDigits(Math.min(i, 340));
    }

    @Override // android.icu.text.NumberFormat
    public void setMinimumFractionDigits(int i) {
        super.setMinimumFractionDigits(Math.min(i, 340));
    }

    public boolean isParseBigDecimal() {
        return this.parseBigDecimal;
    }

    public int getParseMaxDigits() {
        return this.PARSE_MAX_EXPONENT;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        this.attributes.clear();
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    private void setInternalRoundingIncrement(android.icu.math.BigDecimal bigDecimal) {
        BigDecimal bigDecimal2;
        this.roundingIncrementICU = bigDecimal;
        if (bigDecimal == null) {
            bigDecimal2 = null;
        } else {
            bigDecimal2 = bigDecimal.toBigDecimal();
        }
        this.roundingIncrement = bigDecimal2;
    }

    /* access modifiers changed from: private */
    public static final class AffixForCurrency {
        private String negPrefixPatternForCurrency = null;
        private String negSuffixPatternForCurrency = null;
        private final int patternType;
        private String posPrefixPatternForCurrency = null;
        private String posSuffixPatternForCurrency = null;

        public AffixForCurrency(String str, String str2, String str3, String str4, int i) {
            this.negPrefixPatternForCurrency = str;
            this.negSuffixPatternForCurrency = str2;
            this.posPrefixPatternForCurrency = str3;
            this.posSuffixPatternForCurrency = str4;
            this.patternType = i;
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

        public Unit(String str, String str2) {
            this.prefix = str;
            this.suffix = str2;
        }

        public void writeSuffix(StringBuffer stringBuffer) {
            stringBuffer.append(this.suffix);
        }

        public void writePrefix(StringBuffer stringBuffer) {
            stringBuffer.append(this.prefix);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Unit)) {
                return false;
            }
            Unit unit = (Unit) obj;
            return this.prefix.equals(unit.prefix) && this.suffix.equals(unit.suffix);
        }

        public String toString() {
            return this.prefix + "/" + this.suffix;
        }
    }

    private void resetActualRounding() {
        if (this.roundingIncrementICU != null) {
            android.icu.math.BigDecimal movePointLeft = getMaximumFractionDigits() > 0 ? android.icu.math.BigDecimal.ONE.movePointLeft(getMaximumFractionDigits()) : android.icu.math.BigDecimal.ONE;
            if (this.roundingIncrementICU.compareTo(movePointLeft) >= 0) {
                this.actualRoundingIncrementICU = this.roundingIncrementICU;
            } else {
                if (movePointLeft.equals(android.icu.math.BigDecimal.ONE)) {
                    movePointLeft = null;
                }
                this.actualRoundingIncrementICU = movePointLeft;
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

    private void setRoundingDouble(double d) {
        this.roundingDouble = d;
        double d2 = this.roundingDouble;
        if (d2 > 0.0d) {
            double d3 = 1.0d / d2;
            this.roundingDoubleReciprocal = Math.rint(d3);
            if (Math.abs(d3 - this.roundingDoubleReciprocal) > 1.0E-9d) {
                this.roundingDoubleReciprocal = 0.0d;
                return;
            }
            return;
        }
        this.roundingDoubleReciprocal = 0.0d;
    }
}
