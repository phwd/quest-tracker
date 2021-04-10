package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.text.DecimalFormatSymbols;

public class PercentMatcher extends SymbolMatcher {
    private static final PercentMatcher DEFAULT = new PercentMatcher();

    public static PercentMatcher getInstance(DecimalFormatSymbols symbols) {
        String symbolString = symbols.getPercentString();
        if (DEFAULT.uniSet.contains(symbolString)) {
            return DEFAULT;
        }
        return new PercentMatcher(symbolString);
    }

    private PercentMatcher(String symbolString) {
        super(symbolString, DEFAULT.uniSet);
    }

    private PercentMatcher() {
        super(StaticUnicodeSets.Key.PERCENT_SIGN);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public boolean isDisabled(ParsedNumber result) {
        return (result.flags & 2) != 0;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public void accept(StringSegment segment, ParsedNumber result) {
        result.flags |= 2;
        result.setCharsConsumed(segment);
    }

    public String toString() {
        return "<PercentMatcher>";
    }
}
