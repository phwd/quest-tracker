package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;
import android.icu.text.DecimalFormatSymbols;
import android.icu.text.UnicodeSet;

public class NanMatcher extends SymbolMatcher {
    private static final NanMatcher DEFAULT = new NanMatcher("NaN");

    public static NanMatcher getInstance(DecimalFormatSymbols symbols, int parseFlags) {
        String symbolString = symbols.getNaN();
        if (DEFAULT.string.equals(symbolString)) {
            return DEFAULT;
        }
        return new NanMatcher(symbolString);
    }

    private NanMatcher(String symbolString) {
        super(symbolString, UnicodeSet.EMPTY);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public boolean isDisabled(ParsedNumber result) {
        return result.seenNumber();
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public void accept(StringSegment segment, ParsedNumber result) {
        result.flags |= 64;
        result.setCharsConsumed(segment);
    }

    public String toString() {
        return "<NanMatcher>";
    }
}
