package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.impl.number.parse.NumberParseMatcher;
import android.icu.text.UnicodeSet;

public class IgnorablesMatcher extends SymbolMatcher implements NumberParseMatcher.Flexible {
    public static final IgnorablesMatcher DEFAULT = new IgnorablesMatcher(StaticUnicodeSets.get(StaticUnicodeSets.Key.DEFAULT_IGNORABLES));
    public static final IgnorablesMatcher STRICT = new IgnorablesMatcher(StaticUnicodeSets.get(StaticUnicodeSets.Key.STRICT_IGNORABLES));

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public void accept(StringSegment stringSegment, ParsedNumber parsedNumber) {
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public boolean isDisabled(ParsedNumber parsedNumber) {
        return false;
    }

    public String toString() {
        return "<IgnorablesMatcher>";
    }

    private IgnorablesMatcher(UnicodeSet unicodeSet) {
        super("", unicodeSet);
    }
}
