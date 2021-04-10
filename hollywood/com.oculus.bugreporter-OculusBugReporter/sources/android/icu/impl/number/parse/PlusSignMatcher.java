package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.text.DecimalFormatSymbols;

public class PlusSignMatcher extends SymbolMatcher {
    private static final PlusSignMatcher DEFAULT = new PlusSignMatcher(false);
    private static final PlusSignMatcher DEFAULT_ALLOW_TRAILING = new PlusSignMatcher(true);
    private final boolean allowTrailing;

    public static PlusSignMatcher getInstance(DecimalFormatSymbols symbols, boolean allowTrailing2) {
        String symbolString = symbols.getPlusSignString();
        if (ParsingUtils.safeContains(DEFAULT.uniSet, symbolString)) {
            return allowTrailing2 ? DEFAULT_ALLOW_TRAILING : DEFAULT;
        }
        return new PlusSignMatcher(symbolString, allowTrailing2);
    }

    private PlusSignMatcher(String symbolString, boolean allowTrailing2) {
        super(symbolString, DEFAULT.uniSet);
        this.allowTrailing = allowTrailing2;
    }

    private PlusSignMatcher(boolean allowTrailing2) {
        super(StaticUnicodeSets.Key.PLUS_SIGN);
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
        result.setCharsConsumed(segment);
    }

    public String toString() {
        return "<PlusSignMatcher>";
    }
}
