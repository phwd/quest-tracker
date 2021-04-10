package android.icu.impl.coll;

public class UTF16CollationIterator extends CollationIterator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected int limit;
    protected int pos;
    protected CharSequence seq;
    protected int start;

    public UTF16CollationIterator(CollationData d) {
        super(d);
    }

    public UTF16CollationIterator(CollationData d, boolean numeric, CharSequence s, int p) {
        super(d, numeric);
        this.seq = s;
        this.start = 0;
        this.pos = p;
        this.limit = s.length();
    }

    @Override // android.icu.impl.coll.CollationIterator
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }
        UTF16CollationIterator o = (UTF16CollationIterator) other;
        if (this.pos - this.start == o.pos - o.start) {
            return true;
        }
        return false;
    }

    @Override // android.icu.impl.coll.CollationIterator
    public int hashCode() {
        return 42;
    }

    @Override // android.icu.impl.coll.CollationIterator
    public void resetToOffset(int newOffset) {
        reset();
        this.pos = this.start + newOffset;
    }

    @Override // android.icu.impl.coll.CollationIterator
    public int getOffset() {
        return this.pos - this.start;
    }

    public void setText(boolean numeric, CharSequence s, int p) {
        reset(numeric);
        this.seq = s;
        this.start = 0;
        this.pos = p;
        this.limit = s.length();
    }

    @Override // android.icu.impl.coll.CollationIterator
    public int nextCodePoint() {
        int i;
        int i2 = this.pos;
        if (i2 == this.limit) {
            return -1;
        }
        CharSequence charSequence = this.seq;
        this.pos = i2 + 1;
        char c = charSequence.charAt(i2);
        if (Character.isHighSurrogate(c) && (i = this.pos) != this.limit) {
            char trail = this.seq.charAt(i);
            if (Character.isLowSurrogate(trail)) {
                this.pos++;
                return Character.toCodePoint(c, trail);
            }
        }
        return c;
    }

    @Override // android.icu.impl.coll.CollationIterator
    public int previousCodePoint() {
        int i;
        int i2 = this.pos;
        if (i2 == this.start) {
            return -1;
        }
        CharSequence charSequence = this.seq;
        int i3 = i2 - 1;
        this.pos = i3;
        char c = charSequence.charAt(i3);
        if (Character.isLowSurrogate(c) && (i = this.pos) != this.start) {
            char lead = this.seq.charAt(i - 1);
            if (Character.isHighSurrogate(lead)) {
                this.pos--;
                return Character.toCodePoint(lead, c);
            }
        }
        return c;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.CollationIterator
    public long handleNextCE32() {
        int i = this.pos;
        if (i == this.limit) {
            return -4294967104L;
        }
        CharSequence charSequence = this.seq;
        this.pos = i + 1;
        char c = charSequence.charAt(i);
        return makeCodePointAndCE32Pair(c, this.trie.getFromU16SingleLead(c));
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.CollationIterator
    public char handleGetTrailSurrogate() {
        int i = this.pos;
        if (i == this.limit) {
            return 0;
        }
        char trail = this.seq.charAt(i);
        if (Character.isLowSurrogate(trail)) {
            this.pos++;
        }
        return trail;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.CollationIterator
    public void forwardNumCodePoints(int num) {
        int i;
        while (num > 0) {
            int i2 = this.pos;
            if (i2 != this.limit) {
                CharSequence charSequence = this.seq;
                this.pos = i2 + 1;
                num--;
                if (Character.isHighSurrogate(charSequence.charAt(i2)) && (i = this.pos) != this.limit && Character.isLowSurrogate(this.seq.charAt(i))) {
                    this.pos++;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.CollationIterator
    public void backwardNumCodePoints(int num) {
        int i;
        while (num > 0) {
            int i2 = this.pos;
            if (i2 != this.start) {
                CharSequence charSequence = this.seq;
                int i3 = i2 - 1;
                this.pos = i3;
                num--;
                if (Character.isLowSurrogate(charSequence.charAt(i3)) && (i = this.pos) != this.start && Character.isHighSurrogate(this.seq.charAt(i - 1))) {
                    this.pos--;
                }
            } else {
                return;
            }
        }
    }
}
