package android.icu.impl.number.parse;

import android.icu.impl.StaticUnicodeSets;
import android.icu.impl.StringSegment;
import android.icu.text.UnicodeSet;

public abstract class SymbolMatcher implements NumberParseMatcher {
    protected final String string;
    protected final UnicodeSet uniSet;

    /* access modifiers changed from: protected */
    public abstract void accept(StringSegment stringSegment, ParsedNumber parsedNumber);

    /* access modifiers changed from: protected */
    public abstract boolean isDisabled(ParsedNumber parsedNumber);

    protected SymbolMatcher(String symbolString, UnicodeSet symbolUniSet) {
        this.string = symbolString;
        this.uniSet = symbolUniSet;
    }

    protected SymbolMatcher(StaticUnicodeSets.Key key) {
        this.string = "";
        this.uniSet = StaticUnicodeSets.get(key);
    }

    public UnicodeSet getSet() {
        return this.uniSet;
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean match(StringSegment segment, ParsedNumber result) {
        if (isDisabled(result)) {
            return false;
        }
        int overlap = 0;
        if (!this.string.isEmpty() && (overlap = segment.getCommonPrefixLength(this.string)) == this.string.length()) {
            segment.adjustOffset(this.string.length());
            accept(segment, result);
            return false;
        } else if (segment.startsWith(this.uniSet)) {
            segment.adjustOffsetByCodePoint();
            accept(segment, result);
            return false;
        } else if (overlap == segment.length()) {
            return true;
        } else {
            return false;
        }
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean smokeTest(StringSegment segment) {
        return segment.startsWith(this.uniSet) || segment.startsWith(this.string);
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber result) {
    }
}
