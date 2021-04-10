package android.icu.impl.number;

import android.icu.impl.PatternTokenizer;
import android.icu.impl.StandardPlural;
import android.icu.impl.number.Padder;
import android.icu.number.NumberFormatter;
import android.icu.text.DateFormat;
import android.icu.text.DecimalFormatSymbols;
import java.lang.reflect.Array;
import java.math.BigDecimal;

public class PatternStringUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    /* JADX INFO: Multiple debug info for r2v2 int: [D('groupingLength' int), D('groupingSize' int)] */
    /* JADX INFO: Multiple debug info for r6v12 int: [D('di' int), D('digitsStringScale' int)] */
    public static String propertiesToPatternString(DecimalFormatProperties properties) {
        int grouping;
        int grouping1;
        int grouping2;
        StringBuilder digitsString;
        int afterPrefixPos;
        String npp;
        String paddingString;
        int minInt;
        StringBuilder sb = new StringBuilder();
        int groupingSize = Math.min(properties.getSecondaryGroupingSize(), 100);
        int firstGroupingSize = Math.min(properties.getGroupingSize(), 100);
        int paddingWidth = Math.min(properties.getFormatWidth(), 100);
        Padder.PadPosition paddingLocation = properties.getPadPosition();
        String paddingString2 = properties.getPadString();
        int minInt2 = Math.max(Math.min(properties.getMinimumIntegerDigits(), 100), 0);
        int maxInt = Math.min(properties.getMaximumIntegerDigits(), 100);
        int minFrac = Math.max(Math.min(properties.getMinimumFractionDigits(), 100), 0);
        int maxFrac = Math.min(properties.getMaximumFractionDigits(), 100);
        int minSig = Math.min(properties.getMinimumSignificantDigits(), 100);
        int maxSig = Math.min(properties.getMaximumSignificantDigits(), 100);
        boolean alwaysShowDecimal = properties.getDecimalSeparatorAlwaysShown();
        int exponentDigits = Math.min(properties.getMinimumExponentDigits(), 100);
        boolean exponentShowPlusSign = properties.getExponentSignAlwaysShown();
        String pp = properties.getPositivePrefix();
        String ppp = properties.getPositivePrefixPattern();
        String ps = properties.getPositiveSuffix();
        String psp = properties.getPositiveSuffixPattern();
        String np = properties.getNegativePrefix();
        String npp2 = properties.getNegativePrefixPattern();
        String ns = properties.getNegativeSuffix();
        String nsp = properties.getNegativeSuffixPattern();
        if (ppp != null) {
            sb.append(ppp);
        }
        AffixUtils.escape(pp, sb);
        int afterPrefixPos2 = sb.length();
        if (groupingSize != Math.min(100, -1) && firstGroupingSize != Math.min(100, -1) && groupingSize != firstGroupingSize) {
            grouping1 = groupingSize;
            grouping = groupingSize;
            grouping2 = firstGroupingSize;
        } else if (groupingSize != Math.min(100, -1)) {
            grouping1 = 0;
            grouping = groupingSize;
            grouping2 = groupingSize;
        } else if (firstGroupingSize != Math.min(100, -1)) {
            grouping1 = 0;
            grouping = groupingSize;
            grouping2 = firstGroupingSize;
        } else {
            grouping1 = 0;
            grouping = 0;
            grouping2 = 0;
        }
        int groupingSize2 = grouping1 + grouping2 + 1;
        BigDecimal roundingInterval = properties.getRoundingIncrement();
        StringBuilder digitsString2 = new StringBuilder();
        int digitsStringScale = 0;
        if (maxSig != Math.min(100, -1)) {
            while (digitsString2.length() < minSig) {
                digitsString2.append('@');
            }
            digitsString = digitsString2;
            while (digitsString.length() < maxSig) {
                digitsString.append('#');
            }
        } else {
            digitsString = digitsString2;
            if (roundingInterval != null) {
                digitsStringScale = -roundingInterval.scale();
                String str = roundingInterval.scaleByPowerOfTen(roundingInterval.scale()).toPlainString();
                if (str.charAt(0) == 45) {
                    digitsString.append((CharSequence) str, 1, str.length());
                } else {
                    digitsString.append(str);
                }
            }
        }
        while (digitsString.length() + digitsStringScale < minInt2) {
            digitsString.insert(0, '0');
        }
        int digitsStringScale2 = digitsStringScale;
        while ((-digitsStringScale2) < minFrac) {
            digitsString.append('0');
            digitsStringScale2--;
        }
        int m0 = Math.max(groupingSize2, digitsString.length() + digitsStringScale2);
        int m02 = maxInt != 100 ? Math.max(maxInt, m0) - 1 : m0 - 1;
        int mN = maxFrac != 100 ? Math.min(-maxFrac, digitsStringScale2) : digitsStringScale2;
        int magnitude = m02;
        while (magnitude >= mN) {
            int digitsStringScale3 = ((digitsString.length() + digitsStringScale2) - magnitude) - 1;
            if (digitsStringScale3 >= 0) {
                minInt = minInt2;
                if (digitsStringScale3 < digitsString.length()) {
                    sb.append(digitsString.charAt(digitsStringScale3));
                    if (magnitude <= grouping2 && grouping > 0 && (magnitude - grouping2) % grouping == 0) {
                        sb.append(',');
                    } else if (magnitude <= 0 && magnitude == grouping2) {
                        sb.append(',');
                    } else if (magnitude == 0 && (alwaysShowDecimal || mN < 0)) {
                        sb.append('.');
                    }
                    magnitude--;
                    minInt2 = minInt;
                    digitsStringScale2 = digitsStringScale2;
                }
            } else {
                minInt = minInt2;
            }
            sb.append('#');
            if (magnitude <= grouping2) {
            }
            if (magnitude <= 0) {
            }
            sb.append('.');
            magnitude--;
            minInt2 = minInt;
            digitsStringScale2 = digitsStringScale2;
        }
        if (exponentDigits != Math.min(100, -1)) {
            sb.append('E');
            if (exponentShowPlusSign) {
                sb.append('+');
            }
            for (int i = 0; i < exponentDigits; i++) {
                sb.append('0');
            }
        }
        int beforeSuffixPos = sb.length();
        if (psp != null) {
            sb.append(psp);
        }
        AffixUtils.escape(ps, sb);
        if (paddingWidth != -1) {
            while (paddingWidth - sb.length() > 0) {
                sb.insert(afterPrefixPos2, '#');
                beforeSuffixPos++;
                digitsString = digitsString;
            }
            afterPrefixPos = afterPrefixPos2;
            int i2 = AnonymousClass1.$SwitchMap$android$icu$impl$number$Padder$PadPosition[paddingLocation.ordinal()];
            if (i2 == 1) {
                int addedLength = escapePaddingString(paddingString2, sb, 0);
                sb.insert(0, '*');
                afterPrefixPos += addedLength + 1;
                beforeSuffixPos += addedLength + 1;
            } else if (i2 == 2) {
                int addedLength2 = escapePaddingString(paddingString2, sb, afterPrefixPos);
                sb.insert(afterPrefixPos, '*');
                afterPrefixPos += addedLength2 + 1;
                beforeSuffixPos += addedLength2 + 1;
            } else if (i2 == 3) {
                paddingString = paddingString2;
                escapePaddingString(paddingString, sb, beforeSuffixPos);
                sb.insert(beforeSuffixPos, '*');
            } else if (i2 != 4) {
                paddingString = paddingString2;
            } else {
                sb.append('*');
                paddingString = paddingString2;
                escapePaddingString(paddingString, sb, sb.length());
            }
        } else {
            afterPrefixPos = afterPrefixPos2;
        }
        if (np != null || ns != null) {
            npp = npp2;
        } else if (npp2 != null || nsp == null) {
            if (npp2 != null) {
                if (npp2.length() == 1) {
                    npp = npp2;
                    if (npp.charAt(0) == '-' && nsp.length() == 0) {
                    }
                } else {
                    npp = npp2;
                }
            }
            return sb.toString();
        } else {
            npp = npp2;
        }
        sb.append(';');
        if (npp != null) {
            sb.append(npp);
        }
        AffixUtils.escape(np, sb);
        sb.append((CharSequence) sb, afterPrefixPos, beforeSuffixPos);
        if (nsp != null) {
            sb.append(nsp);
        }
        AffixUtils.escape(ns, sb);
        return sb.toString();
    }

    /* renamed from: android.icu.impl.number.PatternStringUtils$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$number$Padder$PadPosition = new int[Padder.PadPosition.values().length];

        static {
            try {
                $SwitchMap$android$icu$impl$number$Padder$PadPosition[Padder.PadPosition.BEFORE_PREFIX.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$impl$number$Padder$PadPosition[Padder.PadPosition.AFTER_PREFIX.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$impl$number$Padder$PadPosition[Padder.PadPosition.BEFORE_SUFFIX.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$icu$impl$number$Padder$PadPosition[Padder.PadPosition.AFTER_SUFFIX.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private static int escapePaddingString(CharSequence input, StringBuilder output, int startIndex) {
        if (input == null || input.length() == 0) {
            input = Padder.FALLBACK_PADDING_STRING;
        }
        int startLength = output.length();
        if (input.length() != 1) {
            output.insert(startIndex, PatternTokenizer.SINGLE_QUOTE);
            int offset = 1;
            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
                if (ch == '\'') {
                    output.insert(startIndex + offset, "''");
                    offset += 2;
                } else {
                    output.insert(startIndex + offset, ch);
                    offset++;
                }
            }
            output.insert(startIndex + offset, PatternTokenizer.SINGLE_QUOTE);
        } else if (input.equals("'")) {
            output.insert(startIndex, "''");
        } else {
            output.insert(startIndex, input);
        }
        return output.length() - startLength;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r20v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    public static String convertLocalized(String input, DecimalFormatSymbols symbols, boolean toLocalized) {
        char c;
        if (input == null) {
            return null;
        }
        int i = 2;
        String[][] table = (String[][]) Array.newInstance(String.class, 21, 2);
        int standIdx = !toLocalized ? 1 : 0;
        table[0][standIdx] = "%";
        table[0][toLocalized ? 1 : 0] = symbols.getPercentString();
        int i2 = 1;
        table[1][standIdx] = "‰";
        table[1][toLocalized] = symbols.getPerMillString();
        table[2][standIdx] = ".";
        table[2][toLocalized] = symbols.getDecimalSeparatorString();
        table[3][standIdx] = ",";
        table[3][toLocalized] = symbols.getGroupingSeparatorString();
        table[4][standIdx] = "-";
        table[4][toLocalized] = symbols.getMinusSignString();
        table[5][standIdx] = "+";
        table[5][toLocalized] = symbols.getPlusSignString();
        table[6][standIdx] = ";";
        table[6][toLocalized] = Character.toString(symbols.getPatternSeparator());
        table[7][standIdx] = "@";
        table[7][toLocalized] = Character.toString(symbols.getSignificantDigit());
        table[8][standIdx] = DateFormat.ABBR_WEEKDAY;
        table[8][toLocalized] = symbols.getExponentSeparator();
        table[9][standIdx] = "*";
        table[9][toLocalized] = Character.toString(symbols.getPadEscape());
        table[10][standIdx] = "#";
        table[10][toLocalized] = Character.toString(symbols.getDigit());
        for (int i3 = 0; i3 < 10; i3++) {
            table[i3 + 11][standIdx] = Character.toString((char) (i3 + 48));
            table[i3 + 11][toLocalized] = symbols.getDigitStringsLocal()[i3];
        }
        int i4 = 0;
        while (true) {
            int length = table.length;
            c = PatternTokenizer.SINGLE_QUOTE;
            if (i4 >= length) {
                break;
            }
            table[i4][toLocalized] = table[i4][toLocalized].replace(PatternTokenizer.SINGLE_QUOTE, (char) 8217);
            i4++;
        }
        StringBuilder result = new StringBuilder();
        int state = 0;
        int offset = 0;
        while (offset < input.length()) {
            char ch = input.charAt(offset);
            if (ch == c) {
                if (state == 0) {
                    result.append(c);
                    state = 1;
                } else if (state == i2) {
                    result.append(c);
                    state = 0;
                } else if (state == i) {
                    state = 3;
                } else if (state == 3) {
                    result.append(c);
                    result.append(c);
                    state = 1;
                } else if (state == 4) {
                    state = 5;
                } else {
                    result.append(c);
                    result.append(c);
                    state = 4;
                }
            } else if (state == 0 || state == 3 || state == 4) {
                int length2 = table.length;
                int i5 = 0;
                while (true) {
                    if (i5 < length2) {
                        String[] pair = table[i5];
                        if (input.regionMatches(offset, pair[0], 0, pair[0].length())) {
                            offset += pair[0].length() - i2;
                            if (state == 3 || state == 4) {
                                result.append(PatternTokenizer.SINGLE_QUOTE);
                                state = 0;
                            }
                            result.append(pair[i2]);
                        } else {
                            i5++;
                        }
                    } else {
                        int length3 = table.length;
                        int i6 = 0;
                        while (true) {
                            if (i6 < length3) {
                                String[] pair2 = table[i6];
                                if (input.regionMatches(offset, pair2[i2], 0, pair2[i2].length())) {
                                    if (state == 0) {
                                        result.append(PatternTokenizer.SINGLE_QUOTE);
                                        state = 4;
                                    }
                                    result.append(ch);
                                } else {
                                    i6++;
                                    i2 = 1;
                                }
                            } else {
                                if (state == 3 || state == 4) {
                                    result.append(PatternTokenizer.SINGLE_QUOTE);
                                    state = 0;
                                }
                                result.append(ch);
                            }
                        }
                    }
                }
            } else {
                result.append(ch);
                state = 2;
            }
            offset++;
            i2 = 1;
            i = 2;
            c = PatternTokenizer.SINGLE_QUOTE;
        }
        if (state == 3 || state == 4) {
            result.append(PatternTokenizer.SINGLE_QUOTE);
            state = 0;
        }
        if (state == 0) {
            return result.toString();
        }
        throw new IllegalArgumentException("Malformed localized pattern: unterminated quote");
    }

    public static void patternInfoToStringBuilder(AffixPatternProvider patternInfo, boolean isPrefix, int signum, NumberFormatter.SignDisplay signDisplay, StandardPlural plural, boolean perMilleReplacesPercent, StringBuilder output) {
        boolean prependSign;
        char candidate;
        int i = 1;
        boolean plusReplacesMinusSign = signum != -1 && (signDisplay == NumberFormatter.SignDisplay.ALWAYS || signDisplay == NumberFormatter.SignDisplay.ACCOUNTING_ALWAYS || (signum == 1 && (signDisplay == NumberFormatter.SignDisplay.EXCEPT_ZERO || signDisplay == NumberFormatter.SignDisplay.ACCOUNTING_EXCEPT_ZERO))) && !patternInfo.positiveHasPlusSign();
        boolean useNegativeAffixPattern = patternInfo.hasNegativeSubpattern() && (signum == -1 || (patternInfo.negativeHasMinusSign() && plusReplacesMinusSign));
        int flags = 0;
        if (useNegativeAffixPattern) {
            flags = 0 | 512;
        }
        if (isPrefix) {
            flags |= 256;
        }
        if (plural != null) {
            flags |= plural.ordinal();
        }
        if (!isPrefix || useNegativeAffixPattern) {
            prependSign = false;
        } else if (signum == -1) {
            prependSign = signDisplay != NumberFormatter.SignDisplay.NEVER;
        } else {
            prependSign = plusReplacesMinusSign;
        }
        int length = patternInfo.length(flags);
        if (!prependSign) {
            i = 0;
        }
        int length2 = length + i;
        output.setLength(0);
        for (int index = 0; index < length2; index++) {
            if (prependSign && index == 0) {
                candidate = '-';
            } else if (prependSign) {
                candidate = patternInfo.charAt(flags, index - 1);
            } else {
                candidate = patternInfo.charAt(flags, index);
            }
            if (plusReplacesMinusSign && candidate == '-') {
                candidate = '+';
            }
            if (perMilleReplacesPercent && candidate == '%') {
                candidate = 8240;
            }
            output.append(candidate);
        }
    }
}
