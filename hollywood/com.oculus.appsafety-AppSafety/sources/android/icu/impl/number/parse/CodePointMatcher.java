package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;

public class CodePointMatcher implements NumberParseMatcher {
    private final int cp;

    public static CodePointMatcher getInstance(int cp2) {
        return new CodePointMatcher(cp2);
    }

    private CodePointMatcher(int cp2) {
        this.cp = cp2;
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean match(StringSegment segment, ParsedNumber result) {
        if (!segment.startsWith(this.cp)) {
            return false;
        }
        segment.adjustOffsetByCodePoint();
        result.setCharsConsumed(segment);
        return false;
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean smokeTest(StringSegment segment) {
        return segment.startsWith(this.cp);
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber result) {
    }

    public String toString() {
        return "<CodePointMatcher U+" + Integer.toHexString(this.cp) + ">";
    }
}
