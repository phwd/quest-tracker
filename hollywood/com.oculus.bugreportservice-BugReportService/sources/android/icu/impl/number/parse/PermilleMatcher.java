package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.text.DecimalFormatSymbols;

public class PermilleMatcher extends SymbolMatcher {
    private static final PermilleMatcher DEFAULT = new PermilleMatcher();

    public String toString() {
        return "<PermilleMatcher>";
    }

    public static PermilleMatcher getInstance(DecimalFormatSymbols decimalFormatSymbols) {
        String perMillString = decimalFormatSymbols.getPerMillString();
        if (DEFAULT.uniSet.contains(perMillString)) {
            return DEFAULT;
        }
        return new PermilleMatcher(perMillString);
    }

    private PermilleMatcher(String str) {
        super(str, DEFAULT.uniSet);
    }

    private PermilleMatcher() {
        super(StaticUnicodeSets.Key.PERMILLE_SIGN);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public boolean isDisabled(ParsedNumber parsedNumber) {
        return (parsedNumber.flags & 4) != 0;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public void accept(StringSegment stringSegment, ParsedNumber parsedNumber) {
        parsedNumber.flags |= 4;
        parsedNumber.setCharsConsumed(stringSegment);
    }
}
