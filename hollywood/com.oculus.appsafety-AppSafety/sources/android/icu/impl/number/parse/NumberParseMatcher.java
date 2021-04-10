package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;

public interface NumberParseMatcher {

    public interface Flexible {
    }

    boolean match(StringSegment stringSegment, ParsedNumber parsedNumber);

    void postProcess(ParsedNumber parsedNumber);

    boolean smokeTest(StringSegment stringSegment);
}
