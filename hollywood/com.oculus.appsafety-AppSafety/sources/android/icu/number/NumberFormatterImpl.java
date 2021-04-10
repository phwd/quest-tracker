package android.icu.number;

import android.icu.impl.StandardPlural;
import android.icu.impl.number.DecimalQuantity;
import android.icu.impl.number.DecimalQuantity_DualStorageBCD;
import android.icu.impl.number.MacroProps;
import android.icu.impl.number.MicroProps;
import android.icu.impl.number.MicroPropsGenerator;
import android.icu.impl.number.NumberStringBuilder;
import android.icu.number.NumberFormatter;
import android.icu.text.NumberFormat;
import android.icu.util.Currency;
import android.icu.util.MeasureUnit;

/* access modifiers changed from: package-private */
public class NumberFormatterImpl {
    private static final Currency DEFAULT_CURRENCY = Currency.getInstance("XXX");
    final MicroPropsGenerator microPropsGenerator;

    public NumberFormatterImpl(MacroProps macros) {
        this(macrosToMicroGenerator(macros, true));
    }

    public static int formatStatic(MacroProps macros, DecimalQuantity inValue, NumberStringBuilder outString) {
        MicroProps micros = preProcessUnsafe(macros, inValue);
        int length = writeNumber(micros, inValue, outString, 0);
        return length + writeAffixes(micros, outString, 0, length);
    }

    public static int getPrefixSuffixStatic(MacroProps macros, byte signum, StandardPlural plural, NumberStringBuilder output) {
        return getPrefixSuffixImpl(macrosToMicroGenerator(macros, false), signum, output);
    }

    private NumberFormatterImpl(MicroPropsGenerator microPropsGenerator2) {
        this.microPropsGenerator = microPropsGenerator2;
    }

    public int format(DecimalQuantity inValue, NumberStringBuilder outString) {
        MicroProps micros = preProcess(inValue);
        int length = writeNumber(micros, inValue, outString, 0);
        return length + writeAffixes(micros, outString, 0, length);
    }

    public MicroProps preProcess(DecimalQuantity inValue) {
        MicroProps micros = this.microPropsGenerator.processQuantity(inValue);
        micros.rounder.apply(inValue);
        if (micros.integerWidth.maxInt == -1) {
            inValue.setIntegerLength(micros.integerWidth.minInt, Integer.MAX_VALUE);
        } else {
            inValue.setIntegerLength(micros.integerWidth.minInt, micros.integerWidth.maxInt);
        }
        return micros;
    }

    private static MicroProps preProcessUnsafe(MacroProps macros, DecimalQuantity inValue) {
        MicroProps micros = macrosToMicroGenerator(macros, false).processQuantity(inValue);
        micros.rounder.apply(inValue);
        if (micros.integerWidth.maxInt == -1) {
            inValue.setIntegerLength(micros.integerWidth.minInt, Integer.MAX_VALUE);
        } else {
            inValue.setIntegerLength(micros.integerWidth.minInt, micros.integerWidth.maxInt);
        }
        return micros;
    }

    public int getPrefixSuffix(byte signum, StandardPlural plural, NumberStringBuilder output) {
        return getPrefixSuffixImpl(this.microPropsGenerator, signum, output);
    }

    private static int getPrefixSuffixImpl(MicroPropsGenerator generator, byte signum, NumberStringBuilder output) {
        DecimalQuantity_DualStorageBCD quantity = new DecimalQuantity_DualStorageBCD(0);
        if (signum < 0) {
            quantity.negate();
        }
        MicroProps micros = generator.processQuantity(quantity);
        micros.modMiddle.apply(output, 0, 0);
        return micros.modMiddle.getPrefixLength();
    }

    private static boolean unitIsCurrency(MeasureUnit unit) {
        return unit != null && "currency".equals(unit.getType());
    }

    private static boolean unitIsNoUnit(MeasureUnit unit) {
        return unit == null || "none".equals(unit.getType());
    }

    private static boolean unitIsPercent(MeasureUnit unit) {
        return unit != null && "percent".equals(unit.getSubtype());
    }

    private static boolean unitIsPermille(MeasureUnit unit) {
        return unit != null && "permille".equals(unit.getSubtype());
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x01b2  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01bd  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01cb  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x01e0  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01ee  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01fa  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0246  */
    /* JADX WARNING: Removed duplicated region for block: B:145:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.icu.impl.number.MicroPropsGenerator macrosToMicroGenerator(android.icu.impl.number.MacroProps r27, boolean r28) {
        /*
        // Method dump skipped, instructions count: 641
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.number.NumberFormatterImpl.macrosToMicroGenerator(android.icu.impl.number.MacroProps, boolean):android.icu.impl.number.MicroPropsGenerator");
    }

    public static int writeAffixes(MicroProps micros, NumberStringBuilder string, int start, int end) {
        int length = micros.modInner.apply(string, start, end);
        if (micros.padding.isValid()) {
            micros.padding.padAndApply(micros.modMiddle, micros.modOuter, string, start, end + length);
            return length;
        }
        int length2 = length + micros.modMiddle.apply(string, start, end + length);
        return length2 + micros.modOuter.apply(string, start, end + length2);
    }

    public static int writeNumber(MicroProps micros, DecimalQuantity quantity, NumberStringBuilder string, int index) {
        String str;
        if (quantity.isInfinite()) {
            return 0 + string.insert(0 + index, micros.symbols.getInfinity(), NumberFormat.Field.INTEGER);
        }
        if (quantity.isNaN()) {
            return 0 + string.insert(0 + index, micros.symbols.getNaN(), NumberFormat.Field.INTEGER);
        }
        int length = 0 + writeIntegerDigits(micros, quantity, string, 0 + index);
        if (quantity.getLowerDisplayMagnitude() < 0 || micros.decimal == NumberFormatter.DecimalSeparatorDisplay.ALWAYS) {
            int i = length + index;
            if (micros.useCurrency) {
                str = micros.symbols.getMonetaryDecimalSeparatorString();
            } else {
                str = micros.symbols.getDecimalSeparatorString();
            }
            length += string.insert(i, str, NumberFormat.Field.DECIMAL_SEPARATOR);
        }
        return length + writeFractionDigits(micros, quantity, string, length + index);
    }

    private static int writeIntegerDigits(MicroProps micros, DecimalQuantity quantity, NumberStringBuilder string, int index) {
        int i;
        String str;
        int length = 0;
        int integerCount = quantity.getUpperDisplayMagnitude() + 1;
        for (int i2 = 0; i2 < integerCount; i2++) {
            if (micros.grouping.groupAtPosition(i2, quantity)) {
                if (micros.useCurrency) {
                    str = micros.symbols.getMonetaryGroupingSeparatorString();
                } else {
                    str = micros.symbols.getGroupingSeparatorString();
                }
                length += string.insert(index, str, NumberFormat.Field.GROUPING_SEPARATOR);
            }
            byte nextDigit = quantity.getDigit(i2);
            if (micros.symbols.getCodePointZero() != -1) {
                i = string.insertCodePoint(index, micros.symbols.getCodePointZero() + nextDigit, NumberFormat.Field.INTEGER);
            } else {
                i = string.insert(index, micros.symbols.getDigitStringsLocal()[nextDigit], NumberFormat.Field.INTEGER);
            }
            length += i;
        }
        return length;
    }

    private static int writeFractionDigits(MicroProps micros, DecimalQuantity quantity, NumberStringBuilder string, int index) {
        int i;
        int length = 0;
        int fractionCount = -quantity.getLowerDisplayMagnitude();
        for (int i2 = 0; i2 < fractionCount; i2++) {
            byte nextDigit = quantity.getDigit((-i2) - 1);
            if (micros.symbols.getCodePointZero() != -1) {
                i = string.insertCodePoint(length + index, micros.symbols.getCodePointZero() + nextDigit, NumberFormat.Field.FRACTION);
            } else {
                i = string.insert(length + index, micros.symbols.getDigitStringsLocal()[nextDigit], NumberFormat.Field.FRACTION);
            }
            length += i;
        }
        return length;
    }
}
