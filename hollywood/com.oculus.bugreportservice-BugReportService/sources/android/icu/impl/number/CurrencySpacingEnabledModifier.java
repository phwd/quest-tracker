package android.icu.impl.number;

import android.icu.text.DecimalFormatSymbols;
import android.icu.text.NumberFormat;
import android.icu.text.UnicodeSet;

public class CurrencySpacingEnabledModifier extends ConstantMultiFieldModifier {
    private static final UnicodeSet UNISET_DIGIT;
    private static final UnicodeSet UNISET_NOTS;
    private final String afterPrefixInsert;
    private final UnicodeSet afterPrefixUnicodeSet;
    private final String beforeSuffixInsert;
    private final UnicodeSet beforeSuffixUnicodeSet;

    static {
        UnicodeSet unicodeSet = new UnicodeSet("[:digit:]");
        unicodeSet.freeze();
        UNISET_DIGIT = unicodeSet;
        UnicodeSet unicodeSet2 = new UnicodeSet("[:^S:]");
        unicodeSet2.freeze();
        UNISET_NOTS = unicodeSet2;
    }

    public CurrencySpacingEnabledModifier(NumberStringBuilder numberStringBuilder, NumberStringBuilder numberStringBuilder2, boolean z, boolean z2, DecimalFormatSymbols decimalFormatSymbols) {
        super(numberStringBuilder, numberStringBuilder2, z, z2);
        if (numberStringBuilder.length() <= 0 || numberStringBuilder.fieldAt(numberStringBuilder.length() - 1) != NumberFormat.Field.CURRENCY) {
            this.afterPrefixUnicodeSet = null;
            this.afterPrefixInsert = null;
        } else {
            if (getUnicodeSet(decimalFormatSymbols, 0, (byte) 0).contains(numberStringBuilder.getLastCodePoint())) {
                this.afterPrefixUnicodeSet = getUnicodeSet(decimalFormatSymbols, 1, (byte) 0);
                this.afterPrefixUnicodeSet.freeze();
                this.afterPrefixInsert = getInsertString(decimalFormatSymbols, (byte) 0);
            } else {
                this.afterPrefixUnicodeSet = null;
                this.afterPrefixInsert = null;
            }
        }
        if (numberStringBuilder2.length() <= 0 || numberStringBuilder2.fieldAt(0) != NumberFormat.Field.CURRENCY) {
            this.beforeSuffixUnicodeSet = null;
            this.beforeSuffixInsert = null;
            return;
        }
        if (getUnicodeSet(decimalFormatSymbols, 0, (byte) 1).contains(numberStringBuilder2.getLastCodePoint())) {
            this.beforeSuffixUnicodeSet = getUnicodeSet(decimalFormatSymbols, 1, (byte) 1);
            this.beforeSuffixUnicodeSet.freeze();
            this.beforeSuffixInsert = getInsertString(decimalFormatSymbols, (byte) 1);
            return;
        }
        this.beforeSuffixUnicodeSet = null;
        this.beforeSuffixInsert = null;
    }

    @Override // android.icu.impl.number.Modifier, android.icu.impl.number.ConstantMultiFieldModifier
    public int apply(NumberStringBuilder numberStringBuilder, int i, int i2) {
        UnicodeSet unicodeSet;
        UnicodeSet unicodeSet2;
        int i3 = i2 - i;
        int i4 = 0;
        if (i3 > 0 && (unicodeSet2 = this.afterPrefixUnicodeSet) != null && unicodeSet2.contains(numberStringBuilder.codePointAt(i))) {
            i4 = 0 + numberStringBuilder.insert(i, this.afterPrefixInsert, (NumberFormat.Field) null);
        }
        if (i3 > 0 && (unicodeSet = this.beforeSuffixUnicodeSet) != null && unicodeSet.contains(numberStringBuilder.codePointBefore(i2))) {
            i4 += numberStringBuilder.insert(i2 + i4, this.beforeSuffixInsert, (NumberFormat.Field) null);
        }
        return i4 + super.apply(numberStringBuilder, i, i2 + i4);
    }

    public static int applyCurrencySpacing(NumberStringBuilder numberStringBuilder, int i, int i2, int i3, int i4, DecimalFormatSymbols decimalFormatSymbols) {
        int i5 = 0;
        boolean z = i2 > 0;
        boolean z2 = i4 > 0;
        boolean z3 = (i3 - i) - i2 > 0;
        if (z && z3) {
            i5 = 0 + applyCurrencySpacingAffix(numberStringBuilder, i + i2, (byte) 0, decimalFormatSymbols);
        }
        return (!z2 || !z3) ? i5 : i5 + applyCurrencySpacingAffix(numberStringBuilder, i3 + i5, (byte) 1, decimalFormatSymbols);
    }

    private static int applyCurrencySpacingAffix(NumberStringBuilder numberStringBuilder, int i, byte b, DecimalFormatSymbols decimalFormatSymbols) {
        NumberFormat.Field field;
        if (b == 0) {
            field = numberStringBuilder.fieldAt(i - 1);
        } else {
            field = numberStringBuilder.fieldAt(i);
        }
        if (field != NumberFormat.Field.CURRENCY) {
            return 0;
        }
        if (!getUnicodeSet(decimalFormatSymbols, 0, b).contains(b == 0 ? numberStringBuilder.codePointBefore(i) : numberStringBuilder.codePointAt(i))) {
            return 0;
        }
        if (!getUnicodeSet(decimalFormatSymbols, 1, b).contains(b == 0 ? numberStringBuilder.codePointAt(i) : numberStringBuilder.codePointBefore(i))) {
            return 0;
        }
        return numberStringBuilder.insert(i, getInsertString(decimalFormatSymbols, b), (NumberFormat.Field) null);
    }

    private static UnicodeSet getUnicodeSet(DecimalFormatSymbols decimalFormatSymbols, short s, byte b) {
        boolean z = false;
        int i = s == 0 ? 0 : 1;
        if (b == 1) {
            z = true;
        }
        String patternForCurrencySpacing = decimalFormatSymbols.getPatternForCurrencySpacing(i, z);
        if (patternForCurrencySpacing.equals("[:digit:]")) {
            return UNISET_DIGIT;
        }
        if (patternForCurrencySpacing.equals("[:^S:]")) {
            return UNISET_NOTS;
        }
        return new UnicodeSet(patternForCurrencySpacing);
    }

    private static String getInsertString(DecimalFormatSymbols decimalFormatSymbols, byte b) {
        boolean z = true;
        if (b != 1) {
            z = false;
        }
        return decimalFormatSymbols.getPatternForCurrencySpacing(2, z);
    }
}
