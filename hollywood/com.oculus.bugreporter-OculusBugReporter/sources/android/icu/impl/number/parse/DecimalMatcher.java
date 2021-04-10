package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.impl.number.Grouper;
import android.icu.lang.UCharacter;
import android.icu.text.DecimalFormatSymbols;
import android.icu.text.UnicodeSet;

public class DecimalMatcher implements NumberParseMatcher {
    static final /* synthetic */ boolean $assertionsDisabled = false;
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

    public static DecimalMatcher getInstance(DecimalFormatSymbols symbols, Grouper grouper, int parseFlags) {
        return new DecimalMatcher(symbols, grouper, parseFlags);
    }

    private DecimalMatcher(DecimalFormatSymbols symbols, Grouper grouper, int parseFlags) {
        StaticUnicodeSets.Key key;
        if ((parseFlags & 2) != 0) {
            this.groupingSeparator = symbols.getMonetaryGroupingSeparatorString();
            this.decimalSeparator = symbols.getMonetaryDecimalSeparatorString();
        } else {
            this.groupingSeparator = symbols.getGroupingSeparatorString();
            this.decimalSeparator = symbols.getDecimalSeparatorString();
        }
        boolean z = true;
        boolean strictSeparators = (parseFlags & 4) != 0;
        StaticUnicodeSets.Key groupingKey = strictSeparators ? StaticUnicodeSets.Key.STRICT_ALL_SEPARATORS : StaticUnicodeSets.Key.ALL_SEPARATORS;
        this.groupingUniSet = StaticUnicodeSets.get(groupingKey);
        StaticUnicodeSets.Key decimalKey = StaticUnicodeSets.chooseFrom(this.decimalSeparator, strictSeparators ? StaticUnicodeSets.Key.STRICT_COMMA : StaticUnicodeSets.Key.COMMA, strictSeparators ? StaticUnicodeSets.Key.STRICT_PERIOD : StaticUnicodeSets.Key.PERIOD);
        if (decimalKey != null) {
            this.decimalUniSet = StaticUnicodeSets.get(decimalKey);
        } else if (!this.decimalSeparator.isEmpty()) {
            this.decimalUniSet = new UnicodeSet().add(this.decimalSeparator.codePointAt(0)).freeze();
        } else {
            this.decimalUniSet = UnicodeSet.EMPTY;
        }
        if (groupingKey == null || decimalKey == null) {
            this.separatorSet = new UnicodeSet().addAll(this.groupingUniSet).addAll(this.decimalUniSet).freeze();
            this.leadSet = null;
        } else {
            this.separatorSet = this.groupingUniSet;
            if (strictSeparators) {
                key = StaticUnicodeSets.Key.DIGITS_OR_ALL_SEPARATORS;
            } else {
                key = StaticUnicodeSets.Key.DIGITS_OR_STRICT_ALL_SEPARATORS;
            }
            this.leadSet = StaticUnicodeSets.get(key);
        }
        int cpZero = symbols.getCodePointZero();
        if (cpZero == -1 || !UCharacter.isDigit(cpZero) || UCharacter.digit(cpZero) != 0) {
            this.digitStrings = symbols.getDigitStringsLocal();
        } else {
            this.digitStrings = null;
        }
        this.requireGroupingMatch = (parseFlags & 8) != 0;
        this.groupingDisabled = (parseFlags & 32) != 0;
        this.integerOnly = (parseFlags & 16) == 0 ? false : z;
        this.grouping1 = grouper.getPrimary();
        this.grouping2 = grouper.getSecondary();
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean match(StringSegment segment, ParsedNumber result) {
        return match(segment, result, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00f9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean match(android.icu.impl.StringSegment r27, android.icu.impl.number.parse.ParsedNumber r28, int r29) {
        /*
        // Method dump skipped, instructions count: 740
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.number.parse.DecimalMatcher.match(android.icu.impl.StringSegment, android.icu.impl.number.parse.ParsedNumber, int):boolean");
    }

    private boolean validateGroup(int sepType, int count, boolean isPrimary) {
        if (this.requireGroupingMatch) {
            if (sepType == -1) {
                return true;
            }
            if (sepType == 0) {
                if (isPrimary) {
                    return true;
                }
                return count != 0 && count <= this.grouping2;
            } else if (sepType == 1) {
                return isPrimary ? count == this.grouping1 : count == this.grouping2;
            } else {
                return true;
            }
        } else if (sepType == 1) {
            return count != 1;
        } else {
            return true;
        }
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean smokeTest(StringSegment segment) {
        UnicodeSet unicodeSet;
        if (this.digitStrings == null && (unicodeSet = this.leadSet) != null) {
            return segment.startsWith(unicodeSet);
        }
        if (segment.startsWith(this.separatorSet) || UCharacter.isDigit(segment.getCodePoint())) {
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
            if (segment.startsWith(strArr[i])) {
                return true;
            }
            i++;
        }
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber result) {
    }

    public String toString() {
        return "<DecimalMatcher>";
    }
}
