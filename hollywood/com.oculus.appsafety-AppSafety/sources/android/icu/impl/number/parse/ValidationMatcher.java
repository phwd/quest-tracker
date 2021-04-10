package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;

public abstract class ValidationMatcher implements NumberParseMatcher {
    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean match(StringSegment segment, ParsedNumber result) {
        return false;
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean smokeTest(StringSegment segment) {
        return false;
    }
}
