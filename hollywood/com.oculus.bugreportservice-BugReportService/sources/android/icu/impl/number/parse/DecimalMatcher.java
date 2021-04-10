package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.impl.number.Grouper;
import android.icu.lang.UCharacter;
import android.icu.text.DecimalFormatSymbols;
import android.icu.text.UnicodeSet;

public class DecimalMatcher implements NumberParseMatcher {
    private final String decimalSeparator;
    private final UnicodeSet decimalUniSet;
    private final String[] digitStrings;
    private final int grouping1;
    private final int grouping2;
    private final boolean groupingDisabled;
    private final String groupingSeparator;
    private final UnicodeSet groupingUniSet;
    private final boolean integerOnly;
    private final UnicodeSet leadSet;
    private final boolean requireGroupingMatch;
    private final UnicodeSet separatorSet;

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber parsedNumber) {
    }

    public String toString() {
        return "<DecimalMatcher>";
    }

    public static DecimalMatcher getInstance(DecimalFormatSymbols decimalFormatSymbols, Grouper grouper, int i) {
        return new DecimalMatcher(decimalFormatSymbols, grouper, i);
    }

    private DecimalMatcher(DecimalFormatSymbols decimalFormatSymbols, Grouper grouper, int i) {
        StaticUnicodeSets.Key key;
        if ((i & 2) != 0) {
            this.groupingSeparator = decimalFormatSymbols.getMonetaryGroupingSeparatorString();
            this.decimalSeparator = decimalFormatSymbols.getMonetaryDecimalSeparatorString();
        } else {
            this.groupingSeparator = decimalFormatSymbols.getGroupingSeparatorString();
            this.decimalSeparator = decimalFormatSymbols.getDecimalSeparatorString();
        }
        boolean z = true;
        boolean z2 = (i & 4) != 0;
        StaticUnicodeSets.Key key2 = z2 ? StaticUnicodeSets.Key.STRICT_ALL_SEPARATORS : StaticUnicodeSets.Key.ALL_SEPARATORS;
        this.groupingUniSet = StaticUnicodeSets.get(key2);
        StaticUnicodeSets.Key chooseFrom = StaticUnicodeSets.chooseFrom(this.decimalSeparator, z2 ? StaticUnicodeSets.Key.STRICT_COMMA : StaticUnicodeSets.Key.COMMA, z2 ? StaticUnicodeSets.Key.STRICT_PERIOD : StaticUnicodeSets.Key.PERIOD);
        if (chooseFrom != null) {
            this.decimalUniSet = StaticUnicodeSets.get(chooseFrom);
        } else if (!this.decimalSeparator.isEmpty()) {
            UnicodeSet unicodeSet = new UnicodeSet();
            unicodeSet.add(this.decimalSeparator.codePointAt(0));
            unicodeSet.freeze();
            this.decimalUniSet = unicodeSet;
        } else {
            this.decimalUniSet = UnicodeSet.EMPTY;
        }
        if (key2 == null || chooseFrom == null) {
            UnicodeSet unicodeSet2 = new UnicodeSet();
            unicodeSet2.addAll(this.groupingUniSet);
            unicodeSet2.addAll(this.decimalUniSet);
            unicodeSet2.freeze();
            this.separatorSet = unicodeSet2;
            this.leadSet = null;
        } else {
            this.separatorSet = this.groupingUniSet;
            if (z2) {
                key = StaticUnicodeSets.Key.DIGITS_OR_ALL_SEPARATORS;
            } else {
                key = StaticUnicodeSets.Key.DIGITS_OR_STRICT_ALL_SEPARATORS;
            }
            this.leadSet = StaticUnicodeSets.get(key);
        }
        int codePointZero = decimalFormatSymbols.getCodePointZero();
        if (codePointZero == -1 || !UCharacter.isDigit(codePointZero) || UCharacter.digit(codePointZero) != 0) {
            this.digitStrings = decimalFormatSymbols.getDigitStringsLocal();
        } else {
            this.digitStrings = null;
        }
        this.requireGroupingMatch = (i & 8) != 0;
        this.groupingDisabled = (i & 32) != 0;
        this.integerOnly = (i & 16) == 0 ? false : z;
        this.grouping1 = grouper.getPrimary();
        this.grouping2 = grouper.getSecondary();
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean match(StringSegment stringSegment, ParsedNumber parsedNumber) {
        return match(stringSegment, parsedNumber, 0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r20v0, resolved type: android.icu.impl.number.parse.DecimalMatcher */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0168 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0205 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0213  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x0247  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x01a5 A[ADDED_TO_REGION, EDGE_INSN: B:186:0x01a5->B:119:0x01a5 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ee A[ADDED_TO_REGION] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean match(android.icu.impl.StringSegment r21, android.icu.impl.number.parse.ParsedNumber r22, int r23) {
        /*
        // Method dump skipped, instructions count: 629
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.number.parse.DecimalMatcher.match(android.icu.impl.StringSegment, android.icu.impl.number.parse.ParsedNumber, int):boolean");
    }

    private boolean validateGroup(int i, int i2, boolean z) {
        if (this.requireGroupingMatch) {
            if (i == -1) {
                return true;
            }
            if (i == 0) {
                if (z) {
                    return true;
                }
                return i2 != 0 && i2 <= this.grouping2;
            } else if (i == 1) {
                return z ? i2 == this.grouping1 : i2 == this.grouping2;
            } else {
                return true;
            }
        } else if (i == 1) {
            return i2 != 1;
        } else {
            return true;
        }
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean smokeTest(StringSegment stringSegment) {
        UnicodeSet unicodeSet;
        if (this.digitStrings == null && (unicodeSet = this.leadSet) != null) {
            return stringSegment.startsWith(unicodeSet);
        }
        if (stringSegment.startsWith(this.separatorSet) || UCharacter.isDigit(stringSegment.getCodePoint())) {
            return true;
        }
        if (this.digitStrings == null) {
            return false;
        }
        int i = 0;
        while (true) {
            String[] strArr = this.digitStrings;
            if (i >= strArr.length) {
                return false;
            }
            if (stringSegment.startsWith(strArr[i])) {
                return true;
            }
            i++;
        }
    }
}
