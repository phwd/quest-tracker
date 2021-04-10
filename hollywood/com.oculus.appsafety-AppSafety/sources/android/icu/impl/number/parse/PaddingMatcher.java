package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;
import android.icu.impl.number.parse.NumberParseMatcher;
import android.icu.text.UnicodeSet;

public class PaddingMatcher extends SymbolMatcher implements NumberParseMatcher.Flexible {
    public static PaddingMatcher getInstance(String padString) {
        return new PaddingMatcher(padString);
    }

    private PaddingMatcher(String symbolString) {
        super(symbolString, UnicodeSet.EMPTY);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public boolean isDisabled(ParsedNumber result) {
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.parse.SymbolMatcher
    public void accept(StringSegment segment, ParsedNumber result) {
    }

    public String toString() {
        return "<PaddingMatcher>";
    }
}
