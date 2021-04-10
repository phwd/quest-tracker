package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;
import android.icu.impl.number.parse.NumberParseMatcher;
import android.icu.text.UnicodeSet;

public class PaddingMatcher extends SymbolMatcher implements NumberParseMatcher.Flexible {
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
        return "<PaddingMatcher>";
    }

    public static PaddingMatcher getInstance(String str) {
        return new PaddingMatcher(str);
    }

    private PaddingMatcher(String str) {
        super(str, UnicodeSet.EMPTY);
    }
}
