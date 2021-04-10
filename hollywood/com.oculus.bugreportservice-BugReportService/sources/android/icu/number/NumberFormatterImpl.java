package android.icu.number;

import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.MacroProps;
import android.icu.impl.number.MicroProps;
import android.icu.impl.number.MicroPropsGenerator;
import android.icu.impl.number.NumberStringBuilder;
import android.icu.number.NumberFormatter;
import android.icu.text.NumberFormat;
import android.icu.util.Currency;
import android.icu.util.MeasureUnit;

class NumberFormatterImpl {
    private static final Currency DEFAULT_CURRENCY = Currency.getInstance("XXX");
    final MicroPropsGenerator microPropsGenerator;

    public NumberFormatterImpl(MacroProps macroProps) {
        this(macrosToMicroGenerator(macroProps, true));
    }

    public static int formatStatic(MacroProps macroProps, DecimalQuantity decimalQuantity, NumberStringBuilder numberStringBuilder) {
        MicroProps preProcessUnsafe = preProcessUnsafe(macroProps, decimalQuantity);
        int writeNumber = writeNumber(preProcessUnsafe, decimalQuantity, numberStringBuilder, 0);
        return writeNumber + writeAffixes(preProcessUnsafe, numberStringBuilder, 0, writeNumber);
    }

    private NumberFormatterImpl(MicroPropsGenerator microPropsGenerator2) {
        this.microPropsGenerator = microPropsGenerator2;
    }

    public int format(DecimalQuantity decimalQuantity, NumberStringBuilder numberStringBuilder) {
        MicroProps preProcess = preProcess(decimalQuantity);
        int writeNumber = writeNumber(preProcess, decimalQuantity, numberStringBuilder, 0);
        return writeNumber + writeAffixes(preProcess, numberStringBuilder, 0, writeNumber);
    }

    public MicroProps preProcess(DecimalQuantity decimalQuantity) {
        MicroProps processQuantity = this.microPropsGenerator.processQuantity(decimalQuantity);
        processQuantity.rounder.apply(decimalQuantity);
        IntegerWidth integerWidth = processQuantity.integerWidth;
        int i = integerWidth.maxInt;
        if (i == -1) {
            decimalQuantity.setIntegerLength(integerWidth.minInt, Integer.MAX_VALUE);
        } else {
            decimalQuantity.setIntegerLength(integerWidth.minInt, i);
        }
        return processQuantity;
    }

    private static MicroProps preProcessUnsafe(MacroProps macroProps, DecimalQuantity decimalQuantity) {
        MicroProps processQuantity = macrosToMicroGenerator(macroProps, false).processQuantity(decimalQuantity);
        processQuantity.rounder.apply(decimalQuantity);
        IntegerWidth integerWidth = processQuantity.integerWidth;
        int i = integerWidth.maxInt;
        if (i == -1) {
            decimalQuantity.setIntegerLength(integerWidth.minInt, Integer.MAX_VALUE);
        } else {
            decimalQuantity.setIntegerLength(integerWidth.minInt, i);
        }
        return processQuantity;
    }

    private static boolean unitIsCurrency(MeasureUnit measureUnit) {
        return measureUnit != null && "currency".equals(measureUnit.getType());
    }

    private static boolean unitIsNoUnit(MeasureUnit measureUnit) {
        return measureUnit == null || "none".equals(measureUnit.getType());
    }

    private static boolean unitIsPercent(MeasureUnit measureUnit) {
        return measureUnit != null && "percent".equals(measureUnit.getSubtype());
    }

    private static boolean unitIsPermille(MeasureUnit measureUnit) {
        return measureUnit != null && "permille".equals(measureUnit.getSubtype());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v6, types: [android.icu.impl.number.AffixPatternProvider] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.icu.impl.number.MicroPropsGenerator macrosToMicroGenerator(android.icu.impl.number.MacroProps r21, boolean r22) {
        /*
        // Method dump skipped, instructions count: 573
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.number.NumberFormatterImpl.macrosToMicroGenerator(android.icu.impl.number.MacroProps, boolean):android.icu.impl.number.MicroPropsGenerator");
    }

    public static int writeAffixes(MicroProps microProps, NumberStringBuilder numberStringBuilder, int i, int i2) {
        int apply = microProps.modInner.apply(numberStringBuilder, i, i2);
        if (microProps.padding.isValid()) {
            microProps.padding.padAndApply(microProps.modMiddle, microProps.modOuter, numberStringBuilder, i, i2 + apply);
            return apply;
        }
        int apply2 = apply + microProps.modMiddle.apply(numberStringBuilder, i, i2 + apply);
        return apply2 + microProps.modOuter.apply(numberStringBuilder, i, i2 + apply2);
    }

    public static int writeNumber(MicroProps microProps, DecimalQuantity decimalQuantity, NumberStringBuilder numberStringBuilder, int i) {
        String str;
        int insert;
        if (decimalQuantity.isInfinite()) {
            insert = numberStringBuilder.insert(i + 0, microProps.symbols.getInfinity(), NumberFormat.Field.INTEGER);
        } else if (decimalQuantity.isNaN()) {
            insert = numberStringBuilder.insert(i + 0, microProps.symbols.getNaN(), NumberFormat.Field.INTEGER);
        } else {
            int writeIntegerDigits = writeIntegerDigits(microProps, decimalQuantity, numberStringBuilder, i + 0) + 0;
            if (decimalQuantity.getLowerDisplayMagnitude() < 0 || microProps.decimal == NumberFormatter.DecimalSeparatorDisplay.ALWAYS) {
                int i2 = writeIntegerDigits + i;
                if (microProps.useCurrency) {
                    str = microProps.symbols.getMonetaryDecimalSeparatorString();
                } else {
                    str = microProps.symbols.getDecimalSeparatorString();
                }
                writeIntegerDigits += numberStringBuilder.insert(i2, str, NumberFormat.Field.DECIMAL_SEPARATOR);
            }
            return writeFractionDigits(microProps, decimalQuantity, numberStringBuilder, i + writeIntegerDigits) + writeIntegerDigits;
        }
        return insert + 0;
    }

    private static int writeIntegerDigits(MicroProps microProps, DecimalQuantity decimalQuantity, NumberStringBuilder numberStringBuilder, int i) {
        int i2;
        String str;
        int upperDisplayMagnitude = decimalQuantity.getUpperDisplayMagnitude() + 1;
        int i3 = 0;
        for (int i4 = 0; i4 < upperDisplayMagnitude; i4++) {
            if (microProps.grouping.groupAtPosition(i4, decimalQuantity)) {
                if (microProps.useCurrency) {
                    str = microProps.symbols.getMonetaryGroupingSeparatorString();
                } else {
                    str = microProps.symbols.getGroupingSeparatorString();
                }
                i3 += numberStringBuilder.insert(i, str, NumberFormat.Field.GROUPING_SEPARATOR);
            }
            byte digit = decimalQuantity.getDigit(i4);
            if (microProps.symbols.getCodePointZero() != -1) {
                i2 = numberStringBuilder.insertCodePoint(i, microProps.symbols.getCodePointZero() + digit, NumberFormat.Field.INTEGER);
            } else {
                i2 = numberStringBuilder.insert(i, microProps.symbols.getDigitStringsLocal()[digit], NumberFormat.Field.INTEGER);
            }
            i3 += i2;
        }
        return i3;
    }

    private static int writeFractionDigits(MicroProps microProps, DecimalQuantity decimalQuantity, NumberStringBuilder numberStringBuilder, int i) {
        int i2;
        int i3 = -decimalQuantity.getLowerDisplayMagnitude();
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            byte digit = decimalQuantity.getDigit((-i5) - 1);
            if (microProps.symbols.getCodePointZero() != -1) {
                i2 = numberStringBuilder.insertCodePoint(i4 + i, microProps.symbols.getCodePointZero() + digit, NumberFormat.Field.FRACTION);
            } else {
                i2 = numberStringBuilder.insert(i4 + i, microProps.symbols.getDigitStringsLocal()[digit], NumberFormat.Field.FRACTION);
            }
            i4 += i2;
        }
        return i4;
    }
}
