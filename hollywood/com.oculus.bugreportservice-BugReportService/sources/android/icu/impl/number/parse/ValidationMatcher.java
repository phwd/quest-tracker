package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;

public abstract class ValidationMatcher implements NumberParseMatcher {
    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean match(StringSegment stringSegment, ParsedNumber parsedNumber) {
        return false;
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean smokeTest(StringSegment stringSegment) {
        return false;
    }
}
