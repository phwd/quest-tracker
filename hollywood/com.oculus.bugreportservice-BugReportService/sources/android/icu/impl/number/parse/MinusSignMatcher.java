package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.text.DecimalFormatSymbols;

public class MinusSignMatcher extends SymbolMatcher {
    private static final MinusSignMatcher DEFAULT = new MinusSignMatcher(false);
    private static final MinusSignMatcher DEFAULT_ALLOW_TRAILING = new MinusSignMatcher(true);
    private final boolean allowTrailing;

    public String toString() {
        return "<MinusSignMatcher>";
    }

    public static MinusSignMatcher getInstance(DecimalFormatSymbols decimalFormatSymbols, boolean z) {
        String minusSignString = decimalFormatSymbols.getMinusSignString();
        if (ParsingUtils.safeContains(DEFAULT.uniSet, minusSignString)) {
            return z ? DEFAULT_ALLOW_TRAILING : DEFAULT;
        }
        return new MinusSignMatcher(minusSignString, z);
    }

    private MinusSignMatcher(String str, boolean z) {
        super(str, DEFAULT.uniSet);
        this.allowTrailing = z;
    }

    private MinusSignMatcher(boolean z) {
        super(StaticUnicodeSets.Key.MINUS_SIGN);
        this.allowTrailing = z;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public boolean isDisabled(ParsedNumber parsedNumber) {
        return !this.allowTrailing && parsedNumber.seenNumber();
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public void accept(StringSegment stringSegment, ParsedNumber parsedNumber) {
        parsedNumber.flags |= 1;
        parsedNumber.setCharsConsumed(stringSegment);
    }
}
