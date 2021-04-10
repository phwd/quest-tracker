package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.text.DecimalFormatSymbols;

public class PercentMatcher extends SymbolMatcher {
    private static final PercentMatcher DEFAULT = new PercentMatcher();

    public String toString() {
        return "<PercentMatcher>";
    }

    public static PercentMatcher getInstance(DecimalFormatSymbols decimalFormatSymbols) {
        String percentString = decimalFormatSymbols.getPercentString();
        if (DEFAULT.uniSet.contains(percentString)) {
            return DEFAULT;
        }
        return new PercentMatcher(percentString);
    }

    private PercentMatcher(String str) {
        super(str, DEFAULT.uniSet);
    }

    private PercentMatcher() {
        super(StaticUnicodeSets.Key.PERCENT_SIGN);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public boolean isDisabled(ParsedNumber parsedNumber) {
        return (parsedNumber.flags & 2) != 0;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public void accept(StringSegment stringSegment, ParsedNumber parsedNumber) {
        parsedNumber.flags |= 2;
        parsedNumber.setCharsConsumed(stringSegment);
    }
}
