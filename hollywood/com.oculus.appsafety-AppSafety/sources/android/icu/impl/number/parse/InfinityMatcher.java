package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.text.DecimalFormatSymbols;

public class InfinityMatcher extends SymbolMatcher {
    private static final InfinityMatcher DEFAULT = new InfinityMatcher();

    public static InfinityMatcher getInstance(DecimalFormatSymbols symbols) {
        String symbolString = symbols.getInfinity();
        if (ParsingUtils.safeContains(DEFAULT.uniSet, symbolString)) {
            return DEFAULT;
        }
        return new InfinityMatcher(symbolString);
    }

    private InfinityMatcher(String symbolString) {
        super(symbolString, DEFAULT.uniSet);
    }

    private InfinityMatcher() {
        super(StaticUnicodeSets.Key.INFINITY);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public boolean isDisabled(ParsedNumber result) {
        return (result.flags & 128) != 0;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public void accept(StringSegment segment, ParsedNumber result) {
        result.flags |= 128;
        result.setCharsConsumed(segment);
    }

    public String toString() {
        return "<InfinityMatcher>";
    }
}
