package android.icu.text;

import java.util.Iterator;

public class UnicodeSetIterator {
    public static int IS_STRING = -1;
    public int codepoint;
    public int codepointEnd;
    protected int endElement;
    private int endRange = 0;
    protected int nextElement;
    private int range = 0;
    private UnicodeSet set;
    public String string;
    private Iterator stringIterator = null;

    public UnicodeSetIterator(UnicodeSet unicodeSet) {
        reset(unicodeSet);
    }

    public boolean next() {
        int i = this.nextElement;
        if (i <= this.endElement) {
            this.nextElement = i + 1;
            this.codepointEnd = i;
            this.codepoint = i;
            return true;
        }
        int i2 = this.range;
        if (i2 < this.endRange) {
            int i3 = i2 + 1;
            this.range = i3;
            loadRange(i3);
            int i4 = this.nextElement;
            this.nextElement = i4 + 1;
            this.codepointEnd = i4;
            this.codepoint = i4;
            return true;
        }
        Iterator it = this.stringIterator;
        if (it == null) {
            return false;
        }
        this.codepoint = IS_STRING;
        this.string = (String) it.next();
        if (!this.stringIterator.hasNext()) {
            this.stringIterator = null;
        }
        return true;
    }

    public void reset(UnicodeSet unicodeSet) {
        this.set = unicodeSet;
        reset();
    }

    public void reset() {
        this.endRange = this.set.getRangeCount() - 1;
        this.range = 0;
        this.endElement = -1;
        this.nextElement = 0;
        if (this.endRange >= 0) {
            loadRange(this.range);
        }
        if (this.set.hasStrings()) {
            this.stringIterator = this.set.strings.iterator();
        } else {
            this.stringIterator = null;
        }
    }

    /* access modifiers changed from: protected */
    public void loadRange(int i) {
        this.nextElement = this.set.getRangeStart(i);
        this.endElement = this.set.getRangeEnd(i);
    }
}
