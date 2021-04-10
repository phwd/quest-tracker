package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.impl.number.DecimalQuantity_DualStorageBCD;
import android.icu.impl.number.Grouper;
import android.icu.text.DecimalFormatSymbols;
import android.icu.text.UnicodeSet;

public class ScientificMatcher implements NumberParseMatcher {
    private final String customMinusSign;
    private final String customPlusSign;
    private final DecimalMatcher exponentMatcher;
    private final String exponentSeparatorString;

    public static ScientificMatcher getInstance(DecimalFormatSymbols symbols, Grouper grouper) {
        return new ScientificMatcher(symbols, grouper);
    }

    private ScientificMatcher(DecimalFormatSymbols symbols, Grouper grouper) {
        this.exponentSeparatorString = symbols.getExponentSeparator();
        this.exponentMatcher = DecimalMatcher.getInstance(symbols, grouper, 48);
        String minusSign = symbols.getMinusSignString();
        String str = null;
        this.customMinusSign = ParsingUtils.safeContains(minusSignSet(), minusSign) ? null : minusSign;
        String plusSign = symbols.getPlusSignString();
        this.customPlusSign = !ParsingUtils.safeContains(plusSignSet(), plusSign) ? plusSign : str;
    }

    private static UnicodeSet minusSignSet() {
        return StaticUnicodeSets.get(StaticUnicodeSets.Key.MINUS_SIGN);
    }

    private static UnicodeSet plusSignSet() {
        return StaticUnicodeSets.get(StaticUnicodeSets.Key.PLUS_SIGN);
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean match(StringSegment segment, ParsedNumber result) {
        boolean wasNull = false;
        if (!result.seenNumber() || (result.flags & 8) != 0) {
            return false;
        }
        int overlap1 = segment.getCommonPrefixLength(this.exponentSeparatorString);
        if (overlap1 == this.exponentSeparatorString.length()) {
            if (segment.length() == overlap1) {
                return true;
            }
            segment.adjustOffset(overlap1);
            int exponentSign = 1;
            if (segment.startsWith(minusSignSet())) {
                exponentSign = -1;
                segment.adjustOffsetByCodePoint();
            } else if (segment.startsWith(plusSignSet())) {
                segment.adjustOffsetByCodePoint();
            } else if (segment.startsWith(this.customMinusSign)) {
                int overlap2 = segment.getCommonPrefixLength(this.customMinusSign);
                if (overlap2 != this.customMinusSign.length()) {
                    segment.adjustOffset(-overlap1);
                    return true;
                }
                exponentSign = -1;
                segment.adjustOffset(overlap2);
            } else if (segment.startsWith(this.customPlusSign)) {
                int overlap22 = segment.getCommonPrefixLength(this.customPlusSign);
                if (overlap22 != this.customPlusSign.length()) {
                    segment.adjustOffset(-overlap1);
                    return true;
                }
                segment.adjustOffset(overlap22);
            }
            if (result.quantity == null) {
                wasNull = true;
            }
            if (wasNull) {
                result.quantity = new DecimalQuantity_DualStorageBCD();
            }
            int digitsOffset = segment.getOffset();
            boolean digitsReturnValue = this.exponentMatcher.match(segment, result, exponentSign);
            if (wasNull) {
                result.quantity = null;
            }
            if (segment.getOffset() != digitsOffset) {
                result.flags |= 8;
            } else {
                segment.adjustOffset(-overlap1);
            }
            return digitsReturnValue;
        } else if (overlap1 == segment.length()) {
            return true;
        } else {
            return false;
        }
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean smokeTest(StringSegment segment) {
        return segment.startsWith(this.exponentSeparatorString);
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber result) {
    }

    public String toString() {
        return "<ScientificMatcher " + this.exponentSeparatorString + ">";
    }
}
