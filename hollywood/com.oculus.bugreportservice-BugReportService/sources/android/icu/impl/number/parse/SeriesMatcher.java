package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;
import android.icu.impl.number.parse.NumberParseMatcher;
import java.util.ArrayList;
import java.util.List;

public class SeriesMatcher implements NumberParseMatcher {
    protected boolean frozen = false;
    protected List matchers = null;

    public void addMatcher(NumberParseMatcher numberParseMatcher) {
        if (this.matchers == null) {
            this.matchers = new ArrayList();
        }
        this.matchers.add(numberParseMatcher);
    }

    public void freeze() {
        this.frozen = true;
    }

    public int length() {
        List list = this.matchers;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean match(StringSegment stringSegment, ParsedNumber parsedNumber) {
        int i;
        if (this.matchers == null) {
            return false;
        }
        ParsedNumber parsedNumber2 = new ParsedNumber();
        parsedNumber2.copyFrom(parsedNumber);
        int offset = stringSegment.getOffset();
        int i2 = 0;
        boolean z = true;
        while (i2 < this.matchers.size()) {
            NumberParseMatcher numberParseMatcher = (NumberParseMatcher) this.matchers.get(i2);
            int offset2 = stringSegment.getOffset();
            boolean match = stringSegment.length() != 0 ? numberParseMatcher.match(stringSegment, parsedNumber) : true;
            boolean z2 = stringSegment.getOffset() != offset2;
            boolean z3 = numberParseMatcher instanceof NumberParseMatcher.Flexible;
            if (!z2 || !z3) {
                if (z2) {
                    i2++;
                    if (i2 < this.matchers.size() && stringSegment.getOffset() != (i = parsedNumber.charEnd) && i > offset2) {
                        stringSegment.setOffset(i);
                    }
                } else if (z3) {
                    i2++;
                } else {
                    stringSegment.setOffset(offset);
                    parsedNumber.copyFrom(parsedNumber2);
                    return match;
                }
            }
            z = match;
        }
        return z;
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean smokeTest(StringSegment stringSegment) {
        List list = this.matchers;
        if (list == null) {
            return false;
        }
        return ((NumberParseMatcher) list.get(0)).smokeTest(stringSegment);
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber parsedNumber) {
        if (this.matchers != null) {
            for (int i = 0; i < this.matchers.size(); i++) {
                ((NumberParseMatcher) this.matchers.get(i)).postProcess(parsedNumber);
            }
        }
    }
}
