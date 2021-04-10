package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.text.DecimalFormatSymbols;

public class MinusSignMatcher extends SymbolMatcher {
    private static final MinusSignMatcher DEFAULT = new MinusSignMatcher(false);
    private static final MinusSignMatcher DEFAULT_ALLOW_TRAILING = new MinusSignMatcher(true);
    private final boolean allowTrailing;

    public static MinusSignMatcher getInstance(DecimalFormatSymbols symbols, boolean allowTrailing2) {
        String symbolString = symbols.getMinusSignString();
        if (ParsingUtils.safeContains(DEFAULT.uniSet, symbolString)) {
            return allowTrailing2 ? DEFAULT_ALLOW_TRAILING : DEFAULT;
        }
        return new MinusSignMatcher(symbolString, allowTrailing2);
    }

    private MinusSignMatcher(String symbolString, boolean allowTrailing2) {
        super(symbolString, DEFAULT.uniSet);
        this.allowTrailing = allowTrailing2;
    }

    private MinusSignMatcher(boolean allowTrailing2) {
        super(StaticUnicodeSets.Key.MINUS_SIGN);
        this.allowTrailing = allowTrailing2;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public boolean isDisabled(ParsedNumber result) {
        return !this.allowTrailing && result.seenNumber();
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public void accept(StringSegment segment, ParsedNumber result) {
        result.flags |= 1;
        result.setCharsConsumed(segment);
    }

    public String toString() {
        return "<MinusSignMatcher>";
    }
}
