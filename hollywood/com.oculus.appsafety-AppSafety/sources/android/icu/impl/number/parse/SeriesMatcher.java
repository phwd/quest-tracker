package android.icu.impl.number.parse;

import android.icu.impl.StringSegment;
import android.icu.impl.number.parse.NumberParseMatcher;
import java.util.ArrayList;
import java.util.List;

public class SeriesMatcher implements NumberParseMatcher {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected boolean frozen = false;
    protected List<NumberParseMatcher> matchers = null;

    public void addMatcher(NumberParseMatcher matcher) {
        if (this.matchers == null) {
            this.matchers = new ArrayList();
        }
        this.matchers.add(matcher);
    }

    public void freeze() {
        this.frozen = true;
    }

    public int length() {
        List<NumberParseMatcher> list = this.matchers;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean match(StringSegment segment, ParsedNumber result) {
        if (this.matchers == null) {
            return false;
        }
        ParsedNumber backup = new ParsedNumber();
        backup.copyFrom(result);
        int initialOffset = segment.getOffset();
        boolean maybeMore = true;
        int i = 0;
        while (i < this.matchers.size()) {
            NumberParseMatcher matcher = this.matchers.get(i);
            int matcherOffset = segment.getOffset();
            if (segment.length() != 0) {
                maybeMore = matcher.match(segment, result);
            } else {
                maybeMore = true;
            }
            boolean success = segment.getOffset() != matcherOffset;
            boolean isFlexible = matcher instanceof NumberParseMatcher.Flexible;
            if (!success || !isFlexible) {
                if (success) {
                    i++;
                    if (i < this.matchers.size() && segment.getOffset() != result.charEnd && result.charEnd > matcherOffset) {
                        segment.setOffset(result.charEnd);
                    }
                } else if (isFlexible) {
                    i++;
                } else {
                    segment.setOffset(initialOffset);
                    result.copyFrom(backup);
                    return maybeMore;
                }
            }
        }
        return maybeMore;
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public boolean smokeTest(StringSegment segment) {
        List<NumberParseMatcher> list = this.matchers;
        if (list == null) {
            return false;
        }
        return list.get(0).smokeTest(segment);
    }

    @Override // android.icu.impl.number.parse.NumberParseMatcher
    public void postProcess(ParsedNumber result) {
        if (this.matchers != null) {
            for (int i = 0; i < this.matchers.size(); i++) {
                this.matchers.get(i).postProcess(result);
            }
        }
    }

    public String toString() {
        return "<SeriesMatcher " + ((Object) this.matchers) + ">";
    }
}
