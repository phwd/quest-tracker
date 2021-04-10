package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;
import android.icu.text.DecimalFormatSymbols;
import android.icu.text.UnicodeSet;

public class NanMatcher extends SymbolMatcher {
    private static final NanMatcher DEFAULT = new NanMatcher("NaN");

    public String toString() {
        return "<NanMatcher>";
    }

    public static NanMatcher getInstance(DecimalFormatSymbols decimalFormatSymbols, int i) {
        String naN = decimalFormatSymbols.getNaN();
        if (DEFAULT.string.equals(naN)) {
            return DEFAULT;
        }
        return new NanMatcher(naN);
    }

    private NanMatcher(String str) {
        super(str, UnicodeSet.EMPTY);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public boolean isDisabled(ParsedNumber parsedNumber) {
        return parsedNumber.seenNumber();
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public void accept(StringSegment stringSegment, ParsedNumber parsedNumber) {
        parsedNumber.flags |= 64;
        parsedNumber.setCharsConsumed(stringSegment);
    }
}
