package android.icu.text;

import android.icu.impl.Utility;
import android.icu.text.RuleBasedTransliterator;

/* access modifiers changed from: package-private */
public class StringMatcher implements UnicodeMatcher, UnicodeReplacer {
    private final RuleBasedTransliterator.Data data;
    private int matchLimit;
    private int matchStart;
    private String pattern;
    private int segmentNumber;

    public StringMatcher(String theString, int segmentNum, RuleBasedTransliterator.Data theData) {
        this.data = theData;
        this.pattern = theString;
        this.matchLimit = -1;
        this.matchStart = -1;
        this.segmentNumber = segmentNum;
    }

    public StringMatcher(String theString, int start, int limit, int segmentNum, RuleBasedTransliterator.Data theData) {
        this(theString.substring(start, limit), segmentNum, theData);
    }

    @Override // android.icu.text.UnicodeMatcher
    public int matches(Replaceable text, int[] offset, int limit, boolean incremental) {
        int[] cursor = {offset[0]};
        if (limit < cursor[0]) {
            for (int i = this.pattern.length() - 1; i >= 0; i--) {
                char keyChar = this.pattern.charAt(i);
                UnicodeMatcher subm = this.data.lookupMatcher(keyChar);
                if (subm != null) {
                    int m = subm.matches(text, cursor, limit, incremental);
                    if (m != 2) {
                        return m;
                    }
                } else if (cursor[0] <= limit || keyChar != text.charAt(cursor[0])) {
                    return 0;
                } else {
                    cursor[0] = cursor[0] - 1;
                }
            }
            if (this.matchStart < 0) {
                this.matchStart = cursor[0] + 1;
                this.matchLimit = offset[0] + 1;
            }
        } else {
            for (int i2 = 0; i2 < this.pattern.length(); i2++) {
                if (incremental && cursor[0] == limit) {
                    return 1;
                }
                char keyChar2 = this.pattern.charAt(i2);
                UnicodeMatcher subm2 = this.data.lookupMatcher(keyChar2);
                if (subm2 != null) {
                    int m2 = subm2.matches(text, cursor, limit, incremental);
                    if (m2 != 2) {
                        return m2;
                    }
                } else if (cursor[0] >= limit || keyChar2 != text.charAt(cursor[0])) {
                    return 0;
                } else {
                    cursor[0] = cursor[0] + 1;
                }
            }
            this.matchStart = offset[0];
            this.matchLimit = cursor[0];
        }
        offset[0] = cursor[0];
        return 2;
    }

    @Override // android.icu.text.UnicodeMatcher
    public String toPattern(boolean escapeUnprintable) {
        StringBuffer result = new StringBuffer();
        StringBuffer quoteBuf = new StringBuffer();
        if (this.segmentNumber > 0) {
            result.append('(');
        }
        for (int i = 0; i < this.pattern.length(); i++) {
            char keyChar = this.pattern.charAt(i);
            UnicodeMatcher m = this.data.lookupMatcher(keyChar);
            if (m == null) {
                Utility.appendToRule(result, (int) keyChar, false, escapeUnprintable, quoteBuf);
            } else {
                Utility.appendToRule(result, m.toPattern(escapeUnprintable), true, escapeUnprintable, quoteBuf);
            }
        }
        if (this.segmentNumber > 0) {
            result.append(')');
        }
        Utility.appendToRule(result, -1, true, escapeUnprintable, quoteBuf);
        return result.toString();
    }

    @Override // android.icu.text.UnicodeMatcher
    public boolean matchesIndexValue(int v) {
        if (this.pattern.length() == 0) {
            return true;
        }
        int c = UTF16.charAt(this.pattern, 0);
        UnicodeMatcher m = this.data.lookupMatcher(c);
        if (m != null) {
            return m.matchesIndexValue(v);
        }
        if ((c & 255) == v) {
            return true;
        }
        return false;
    }

    @Override // android.icu.text.UnicodeMatcher
    public void addMatchSetTo(UnicodeSet toUnionTo) {
        int i = 0;
        while (i < this.pattern.length()) {
            int ch = UTF16.charAt(this.pattern, i);
            UnicodeMatcher matcher = this.data.lookupMatcher(ch);
            if (matcher == null) {
                toUnionTo.add(ch);
            } else {
                matcher.addMatchSetTo(toUnionTo);
            }
            i += UTF16.getCharCount(ch);
        }
    }

    @Override // android.icu.text.UnicodeReplacer
    public int replace(Replaceable text, int start, int limit, int[] cursor) {
        int i;
        int outLen = 0;
        int i2 = this.matchStart;
        if (i2 >= 0 && i2 != (i = this.matchLimit)) {
            text.copy(i2, i, limit);
            outLen = this.matchLimit - this.matchStart;
        }
        text.replace(start, limit, "");
        return outLen;
    }

    @Override // android.icu.text.UnicodeReplacer
    public String toReplacerPattern(boolean escapeUnprintable) {
        StringBuffer rule = new StringBuffer("$");
        Utility.appendNumber(rule, this.segmentNumber, 10, 1);
        return rule.toString();
    }

    public void resetMatch() {
        this.matchLimit = -1;
        this.matchStart = -1;
    }

    @Override // android.icu.text.UnicodeReplacer
    public void addReplacementSetTo(UnicodeSet toUnionTo) {
    }
}
