package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.text.DecimalFormatSymbols;

public class InfinityMatcher extends SymbolMatcher {
    private static final InfinityMatcher DEFAULT = new InfinityMatcher();

    public String toString() {
        return "<InfinityMatcher>";
    }

    public static InfinityMatcher getInstance(DecimalFormatSymbols decimalFormatSymbols) {
        String infinity = decimalFormatSymbols.getInfinity();
        if (ParsingUtils.safeContains(DEFAULT.uniSet, infinity)) {
            return DEFAULT;
        }
        return new InfinityMatcher(infinity);
    }

    private InfinityMatcher(String str) {
        super(str, DEFAULT.uniSet);
    }

    private InfinityMatcher() {
        super(StaticUnicodeSets.Key.INFINITY);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public boolean isDisabled(ParsedNumber parsedNumber) {
        return (parsedNumber.flags & 128) != 0;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public void accept(StringSegment stringSegment, ParsedNumber parsedNumber) {
        parsedNumber.flags |= 128;
        parsedNumber.setCharsConsumed(stringSegment);
    }
}
