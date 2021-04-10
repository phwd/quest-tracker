package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.text.DecimalFormatSymbols;

public class PermilleMatcher extends SymbolMatcher {
    private static final PermilleMatcher DEFAULT = new PermilleMatcher();

    public static PermilleMatcher getInstance(DecimalFormatSymbols symbols) {
        String symbolString = symbols.getPerMillString();
        if (DEFAULT.uniSet.contains(symbolString)) {
            return DEFAULT;
        }
        return new PermilleMatcher(symbolString);
    }

    private PermilleMatcher(String symbolString) {
        super(symbolString, DEFAULT.uniSet);
    }

    private PermilleMatcher() {
        super(StaticUnicodeSets.Key.PERMILLE_SIGN);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public boolean isDisabled(ParsedNumber result) {
        return (result.flags & 4) != 0;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public void accept(StringSegment segment, ParsedNumber result) {
        result.flags |= 4;
        result.setCharsConsumed(segment);
    }

    public String toString() {
        return "<PermilleMatcher>";
    }
}
