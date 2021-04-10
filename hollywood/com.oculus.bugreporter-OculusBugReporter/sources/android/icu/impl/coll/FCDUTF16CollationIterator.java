package android.icu.impl.coll;

import android.icu.impl.Normalizer2Impl;

public final class FCDUTF16CollationIterator extends UTF16CollationIterator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int rawStart = 0;
    private int checkDir;
    private final Normalizer2Impl nfcImpl;
    private StringBuilder normalized;
    private int rawLimit;
    private CharSequence rawSeq;
    private int segmentLimit;
    private int segmentStart;

    public FCDUTF16CollationIterator(CollationData d) {
        super(d);
        this.nfcImpl = d.nfcImpl;
    }

    public FCDUTF16CollationIterator(CollationData data, boolean numeric, CharSequence s, int p) {
        super(data, numeric, s, p);
        this.rawSeq = s;
        this.segmentStart = p;
        this.rawLimit = s.length();
        this.nfcImpl = data.nfcImpl;
        this.checkDir = 1;
    }

    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public boolean equals(Object other) {
        if (!(other instanceof CollationIterator) || !equals(other) || !(other instanceof FCDUTF16CollationIterator)) {
            return false;
        }
        FCDUTF16CollationIterator o = (FCDUTF16CollationIterator) other;
        int i = this.checkDir;
        if (i != o.checkDir) {
            return false;
        }
        if (i == 0) {
            if ((this.seq == this.rawSeq) != (o.seq == o.rawSeq)) {
                return false;
            }
        }
        if (this.checkDir != 0 || this.seq == this.rawSeq) {
            if (this.pos - 0 == o.pos - 0) {
                return true;
            }
            return false;
        } else if (this.segmentStart - 0 == o.segmentStart - 0 && this.pos - this.start == o.pos - o.start) {
            return true;
        } else {
            return false;
        }
    }

    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public int hashCode() {
        return 42;
    }

    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public void resetToOffset(int newOffset) {
        reset();
        this.seq = this.rawSeq;
        int i = newOffset + 0;
        this.pos = i;
        this.segmentStart = i;
        this.start = i;
        this.limit = this.rawLimit;
        this.checkDir = 1;
    }

    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public int getOffset() {
        if (this.checkDir != 0 || this.seq == this.rawSeq) {
            return this.pos + 0;
        }
        if (this.pos == this.start) {
            return this.segmentStart + 0;
        }
        return this.segmentLimit + 0;
    }

    @Override // android.icu.impl.coll.UTF16CollationIterator
    public void setText(boolean numeric, CharSequence s, int p) {
        super.setText(numeric, s, p);
        this.rawSeq = s;
        this.segmentStart = p;
        int length = s.length();
        this.limit = length;
        this.rawLimit = length;
        this.checkDir = 1;
    }

    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public int nextCodePoint() {
        char c;
        while (true) {
            int i = this.checkDir;
            if (i <= 0) {
                if (i == 0 && this.pos != this.limit) {
                    CharSequence charSequence = this.seq;
                    int i2 = this.pos;
                    this.pos = i2 + 1;
                    c = charSequence.charAt(i2);
                    break;
                }
                switchToForward();
            } else if (this.pos == this.limit) {
                return -1;
            } else {
                CharSequence charSequence2 = this.seq;
                int i3 = this.pos;
                this.pos = i3 + 1;
                c = charSequence2.charAt(i3);
                if (CollationFCD.hasTccc(c) && (CollationFCD.maybeTibetanCompositeVowel(c) || (this.pos != this.limit && CollationFCD.hasLccc(this.seq.charAt(this.pos))))) {
                    this.pos--;
                    nextSegment();
                    CharSequence charSequence3 = this.seq;
                    int i4 = this.pos;
                    this.pos = i4 + 1;
                    c = charSequence3.charAt(i4);
                }
            }
        }
        if (Character.isHighSurrogate(c) && this.pos != this.limit) {
            char trail = this.seq.charAt(this.pos);
            if (Character.isLowSurrogate(trail)) {
                this.pos++;
                return Character.toCodePoint(c, trail);
            }
        }
        return c;
    }

    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public int previousCodePoint() {
        char c;
        while (true) {
            int i = this.checkDir;
            if (i >= 0) {
                if (i == 0 && this.pos != this.start) {
                    CharSequence charSequence = this.seq;
                    int i2 = this.pos - 1;
                    this.pos = i2;
                    c = charSequence.charAt(i2);
                    break;
                }
                switchToBackward();
            } else if (this.pos == this.start) {
                return -1;
            } else {
                CharSequence charSequence2 = this.seq;
                int i3 = this.pos - 1;
                this.pos = i3;
                c = charSequence2.charAt(i3);
                if (CollationFCD.hasLccc(c) && (CollationFCD.maybeTibetanCompositeVowel(c) || (this.pos != this.start && CollationFCD.hasTccc(this.seq.charAt(this.pos - 1))))) {
                    this.pos++;
                    previousSegment();
                    CharSequence charSequence3 = this.seq;
                    int i4 = this.pos - 1;
                    this.pos = i4;
                    c = charSequence3.charAt(i4);
                }
            }
        }
        if (Character.isLowSurrogate(c) && this.pos != this.start) {
            char lead = this.seq.charAt(this.pos - 1);
            if (Character.isHighSurrogate(lead)) {
                this.pos--;
                return Character.toCodePoint(lead, c);
            }
        }
        return c;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public long handleNextCE32() {
        char c;
        while (true) {
            int i = this.checkDir;
            if (i <= 0) {
                if (i == 0 && this.pos != this.limit) {
                    CharSequence charSequence = this.seq;
                    int i2 = this.pos;
                    this.pos = i2 + 1;
                    c = charSequence.charAt(i2);
                    break;
                }
                switchToForward();
            } else if (this.pos == this.limit) {
                return -4294967104L;
            } else {
                CharSequence charSequence2 = this.seq;
                int i3 = this.pos;
                this.pos = i3 + 1;
                c = charSequence2.charAt(i3);
                if (CollationFCD.hasTccc(c) && (CollationFCD.maybeTibetanCompositeVowel(c) || (this.pos != this.limit && CollationFCD.hasLccc(this.seq.charAt(this.pos))))) {
                    this.pos--;
                    nextSegment();
                    CharSequence charSequence3 = this.seq;
                    int i4 = this.pos;
                    this.pos = i4 + 1;
                    c = charSequence3.charAt(i4);
                }
            }
        }
        return makeCodePointAndCE32Pair(c, this.trie.getFromU16SingleLead(c));
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public void forwardNumCodePoints(int num) {
        while (num > 0 && nextCodePoint() >= 0) {
            num--;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public void backwardNumCodePoints(int num) {
        while (num > 0 && previousCodePoint() >= 0) {
            num--;
        }
    }

    private void switchToForward() {
        if (this.checkDir < 0) {
            int i = this.pos;
            this.segmentStart = i;
            this.start = i;
            if (this.pos == this.segmentLimit) {
                this.limit = this.rawLimit;
                this.checkDir = 1;
                return;
            }
            this.checkDir = 0;
            return;
        }
        CharSequence charSequence = this.seq;
        CharSequence charSequence2 = this.rawSeq;
        if (charSequence != charSequence2) {
            this.seq = charSequence2;
            int i2 = this.segmentLimit;
            this.segmentStart = i2;
            this.start = i2;
            this.pos = i2;
        }
        this.limit = this.rawLimit;
        this.checkDir = 1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0030, code lost:
        if (r0 != r8.rawLimit) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        r3 = java.lang.Character.codePointAt(r8.seq, r0);
        r0 = r0 + java.lang.Character.charCount(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        if (r8.nfcImpl.getFCD16(r3) > 255) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0048, code lost:
        normalize(r8.pos, r0);
        r8.pos = r8.start;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005c, code lost:
        r8.segmentLimit = r0;
        r8.limit = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void nextSegment() {
        /*
        // Method dump skipped, instructions count: 102
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.FCDUTF16CollationIterator.nextSegment():void");
    }

    private void switchToBackward() {
        if (this.checkDir > 0) {
            int i = this.pos;
            this.segmentLimit = i;
            this.limit = i;
            if (this.pos == this.segmentStart) {
                this.start = 0;
                this.checkDir = -1;
                return;
            }
            this.checkDir = 0;
            return;
        }
        CharSequence charSequence = this.seq;
        CharSequence charSequence2 = this.rawSeq;
        if (charSequence != charSequence2) {
            this.seq = charSequence2;
            int i2 = this.segmentStart;
            this.segmentLimit = i2;
            this.limit = i2;
            this.pos = i2;
        }
        this.start = 0;
        this.checkDir = -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005d, code lost:
        r7.segmentStart = r0;
        r7.start = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void previousSegment() {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.FCDUTF16CollationIterator.previousSegment():void");
    }

    private void normalize(int from, int to) {
        if (this.normalized == null) {
            this.normalized = new StringBuilder();
        }
        this.nfcImpl.decompose(this.rawSeq, from, to, this.normalized, to - from);
        this.segmentStart = from;
        this.segmentLimit = to;
        this.seq = this.normalized;
        this.start = 0;
        this.limit = this.start + this.normalized.length();
    }
}
