package android.icu.impl.number;

import android.icu.text.DecimalFormatSymbols;
import android.icu.text.NumberFormat;
import android.icu.text.UnicodeSet;

public class CurrencySpacingEnabledModifier extends ConstantMultiFieldModifier {
    static final short IN_CURRENCY = 0;
    static final short IN_NUMBER = 1;
    static final byte PREFIX = 0;
    static final byte SUFFIX = 1;
    private static final UnicodeSet UNISET_DIGIT = new UnicodeSet("[:digit:]").freeze();
    private static final UnicodeSet UNISET_NOTS = new UnicodeSet("[:^S:]").freeze();
    private final String afterPrefixInsert;
    private final UnicodeSet afterPrefixUnicodeSet;
    private final String beforeSuffixInsert;
    private final UnicodeSet beforeSuffixUnicodeSet;

    public CurrencySpacingEnabledModifier(NumberStringBuilder prefix, NumberStringBuilder suffix, boolean overwrite, boolean strong, DecimalFormatSymbols symbols) {
        super(prefix, suffix, overwrite, strong);
        if (prefix.length() <= 0 || prefix.fieldAt(prefix.length() - 1) != NumberFormat.Field.CURRENCY) {
            this.afterPrefixUnicodeSet = null;
            this.afterPrefixInsert = null;
        } else {
            if (getUnicodeSet(symbols, 0, (byte) 0).contains(prefix.getLastCodePoint())) {
                this.afterPrefixUnicodeSet = getUnicodeSet(symbols, 1, (byte) 0);
                this.afterPrefixUnicodeSet.freeze();
                this.afterPrefixInsert = getInsertString(symbols, (byte) 0);
            } else {
                this.afterPrefixUnicodeSet = null;
                this.afterPrefixInsert = null;
            }
        }
        if (suffix.length() <= 0 || suffix.fieldAt(0) != NumberFormat.Field.CURRENCY) {
            this.beforeSuffixUnicodeSet = null;
            this.beforeSuffixInsert = null;
            return;
        }
        if (getUnicodeSet(symbols, 0, (byte) 1).contains(suffix.getLastCodePoint())) {
            this.beforeSuffixUnicodeSet = getUnicodeSet(symbols, 1, (byte) 1);
            this.beforeSuffixUnicodeSet.freeze();
            this.beforeSuffixInsert = getInsertString(symbols, (byte) 1);
            return;
        }
        this.beforeSuffixUnicodeSet = null;
        this.beforeSuffixInsert = null;
    }

    @Override // android.icu.impl.number.Modifier, android.icu.impl.number.ConstantMultiFieldModifier
    public int apply(NumberStringBuilder output, int leftIndex, int rightIndex) {
        UnicodeSet unicodeSet;
        UnicodeSet unicodeSet2;
        int length = 0;
        if (rightIndex - leftIndex > 0 && (unicodeSet2 = this.afterPrefixUnicodeSet) != null && unicodeSet2.contains(output.codePointAt(leftIndex))) {
            length = 0 + output.insert(leftIndex, this.afterPrefixInsert, (NumberFormat.Field) null);
        }
        if (rightIndex - leftIndex > 0 && (unicodeSet = this.beforeSuffixUnicodeSet) != null && unicodeSet.contains(output.codePointBefore(rightIndex))) {
            length += output.insert(rightIndex + length, this.beforeSuffixInsert, (NumberFormat.Field) null);
        }
        return length + super.apply(output, leftIndex, rightIndex + length);
    }

    public static int applyCurrencySpacing(NumberStringBuilder output, int prefixStart, int prefixLen, int suffixStart, int suffixLen, DecimalFormatSymbols symbols) {
        int length = 0;
        boolean hasPrefix = prefixLen > 0;
        boolean hasSuffix = suffixLen > 0;
        boolean hasNumber = (suffixStart - prefixStart) - prefixLen > 0;
        if (hasPrefix && hasNumber) {
            length = 0 + applyCurrencySpacingAffix(output, prefixStart + prefixLen, (byte) 0, symbols);
        }
        if (!hasSuffix || !hasNumber) {
            return length;
        }
        return length + applyCurrencySpacingAffix(output, suffixStart + length, (byte) 1, symbols);
    }

    private static int applyCurrencySpacingAffix(NumberStringBuilder output, int index, byte affix, DecimalFormatSymbols symbols) {
        NumberFormat.Field affixField;
        if (affix == 0) {
            affixField = output.fieldAt(index - 1);
        } else {
            affixField = output.fieldAt(index);
        }
        if (affixField != NumberFormat.Field.CURRENCY) {
            return 0;
        }
        if (!getUnicodeSet(symbols, 0, affix).contains(affix == 0 ? output.codePointBefore(index) : output.codePointAt(index))) {
            return 0;
        }
        if (!getUnicodeSet(symbols, 1, affix).contains(affix == 0 ? output.codePointAt(index) : output.codePointBefore(index))) {
            return 0;
        }
        return output.insert(index, getInsertString(symbols, affix), (NumberFormat.Field) null);
    }

    private static UnicodeSet getUnicodeSet(DecimalFormatSymbols symbols, short position, byte affix) {
        int i;
        boolean z = false;
        if (position == 0) {
            i = 0;
        } else {
            i = 1;
        }
        if (affix == 1) {
            z = true;
        }
        String pattern = symbols.getPatternForCurrencySpacing(i, z);
        if (pattern.equals("[:digit:]")) {
            return UNISET_DIGIT;
        }
        if (pattern.equals("[:^S:]")) {
            return UNISET_NOTS;
        }
        return new UnicodeSet(pattern);
    }

    private static String getInsertString(DecimalFormatSymbols symbols, byte affix) {
        boolean z = true;
        if (affix != 1) {
            z = false;
        }
        return symbols.getPatternForCurrencySpacing(2, z);
    }
}
