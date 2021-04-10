package android.icu.impl.coll;

import android.icu.impl.Normalizer2Impl;

public final class FCDUTF16CollationIterator extends UTF16CollationIterator {
    private int checkDir;
    private final Normalizer2Impl nfcImpl;
    private StringBuilder normalized;
    private int rawLimit;
    private CharSequence rawSeq;
    private int segmentLimit;
    private int segmentStart;

    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public int hashCode() {
        return 42;
    }

    public FCDUTF16CollationIterator(CollationData collationData) {
        super(collationData);
        this.nfcImpl = collationData.nfcImpl;
    }

    public FCDUTF16CollationIterator(CollationData collationData, boolean z, CharSequence charSequence, int i) {
        super(collationData, z, charSequence, i);
        this.rawSeq = charSequence;
        this.segmentStart = i;
        this.rawLimit = charSequence.length();
        this.nfcImpl = collationData.nfcImpl;
        this.checkDir = 1;
    }

    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public boolean equals(Object obj) {
        if (!(obj instanceof CollationIterator) || !equals(obj) || !(obj instanceof FCDUTF16CollationIterator)) {
            return false;
        }
        FCDUTF16CollationIterator fCDUTF16CollationIterator = (FCDUTF16CollationIterator) obj;
        int i = this.checkDir;
        if (i != fCDUTF16CollationIterator.checkDir) {
            return false;
        }
        if (i == 0) {
            if ((this.seq == this.rawSeq) != (fCDUTF16CollationIterator.seq == fCDUTF16CollationIterator.rawSeq)) {
                return false;
            }
        }
        if (this.checkDir != 0 || this.seq == this.rawSeq) {
            if (this.pos - 0 == fCDUTF16CollationIterator.pos - 0) {
                return true;
            }
            return false;
        } else if (this.segmentStart - 0 == fCDUTF16CollationIterator.segmentStart - 0 && this.pos - this.start == fCDUTF16CollationIterator.pos - fCDUTF16CollationIterator.start) {
            return true;
        } else {
            return false;
        }
    }

    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public int getOffset() {
        int i;
        if (this.checkDir != 0 || this.seq == this.rawSeq) {
            i = this.pos;
        } else if (this.pos == this.start) {
            i = this.segmentStart;
        } else {
            i = this.segmentLimit;
        }
        return i + 0;
    }

    @Override // android.icu.impl.coll.UTF16CollationIterator
    public void setText(boolean z, CharSequence charSequence, int i) {
        super.setText(z, charSequence, i);
        this.rawSeq = charSequence;
        this.segmentStart = i;
        int length = charSequence.length();
        this.limit = length;
        this.rawLimit = length;
        this.checkDir = 1;
    }

    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public int nextCodePoint() {
        char charAt;
        int i;
        int i2;
        int i3;
        while (true) {
            int i4 = this.checkDir;
            if (i4 <= 0) {
                if (i4 == 0 && (i3 = this.pos) != this.limit) {
                    CharSequence charSequence = this.seq;
                    this.pos = i3 + 1;
                    charAt = charSequence.charAt(i3);
                    break;
                }
                switchToForward();
            } else {
                int i5 = this.pos;
                if (i5 == this.limit) {
                    return -1;
                }
                CharSequence charSequence2 = this.seq;
                this.pos = i5 + 1;
                charAt = charSequence2.charAt(i5);
                if (CollationFCD.hasTccc(charAt) && (CollationFCD.maybeTibetanCompositeVowel(charAt) || ((i = this.pos) != this.limit && CollationFCD.hasLccc(this.seq.charAt(i))))) {
                    this.pos--;
                    nextSegment();
                    CharSequence charSequence3 = this.seq;
                    int i6 = this.pos;
                    this.pos = i6 + 1;
                    charAt = charSequence3.charAt(i6);
                }
            }
        }
        if (Character.isHighSurrogate(charAt) && (i2 = this.pos) != this.limit) {
            char charAt2 = this.seq.charAt(i2);
            if (Character.isLowSurrogate(charAt2)) {
                this.pos++;
                return Character.toCodePoint(charAt, charAt2);
            }
        }
        return charAt;
    }

    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public int previousCodePoint() {
        char charAt;
        int i;
        int i2;
        int i3;
        while (true) {
            int i4 = this.checkDir;
            if (i4 >= 0) {
                if (i4 == 0 && (i3 = this.pos) != this.start) {
                    CharSequence charSequence = this.seq;
                    int i5 = i3 - 1;
                    this.pos = i5;
                    charAt = charSequence.charAt(i5);
                    break;
                }
                switchToBackward();
            } else {
                int i6 = this.pos;
                if (i6 == this.start) {
                    return -1;
                }
                CharSequence charSequence2 = this.seq;
                int i7 = i6 - 1;
                this.pos = i7;
                charAt = charSequence2.charAt(i7);
                if (CollationFCD.hasLccc(charAt) && (CollationFCD.maybeTibetanCompositeVowel(charAt) || ((i = this.pos) != this.start && CollationFCD.hasTccc(this.seq.charAt(i - 1))))) {
                    this.pos++;
                    previousSegment();
                    CharSequence charSequence3 = this.seq;
                    int i8 = this.pos - 1;
                    this.pos = i8;
                    charAt = charSequence3.charAt(i8);
                }
            }
        }
        if (Character.isLowSurrogate(charAt) && (i2 = this.pos) != this.start) {
            char charAt2 = this.seq.charAt(i2 - 1);
            if (Character.isHighSurrogate(charAt2)) {
                this.pos--;
                return Character.toCodePoint(charAt2, charAt);
            }
        }
        return charAt;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public long handleNextCE32() {
        char charAt;
        int i;
        int i2;
        while (true) {
            int i3 = this.checkDir;
            if (i3 <= 0) {
                if (i3 == 0 && (i2 = this.pos) != this.limit) {
                    CharSequence charSequence = this.seq;
                    this.pos = i2 + 1;
                    charAt = charSequence.charAt(i2);
                    break;
                }
                switchToForward();
            } else {
                int i4 = this.pos;
                if (i4 == this.limit) {
                    return -4294967104L;
                }
                CharSequence charSequence2 = this.seq;
                this.pos = i4 + 1;
                charAt = charSequence2.charAt(i4);
                if (CollationFCD.hasTccc(charAt) && (CollationFCD.maybeTibetanCompositeVowel(charAt) || ((i = this.pos) != this.limit && CollationFCD.hasLccc(this.seq.charAt(i))))) {
                    this.pos--;
                    nextSegment();
                    CharSequence charSequence3 = this.seq;
                    int i5 = this.pos;
                    this.pos = i5 + 1;
                    charAt = charSequence3.charAt(i5);
                }
            }
        }
        return makeCodePointAndCE32Pair(charAt, this.trie.getFromU16SingleLead(charAt));
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public void forwardNumCodePoints(int i) {
        while (i > 0 && nextCodePoint() >= 0) {
            i--;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.UTF16CollationIterator, android.icu.impl.coll.CollationIterator
    public void backwardNumCodePoints(int i) {
        while (i > 0 && previousCodePoint() >= 0) {
            i--;
        }
    }

    private void switchToForward() {
        if (this.checkDir < 0) {
            int i = this.pos;
            this.segmentStart = i;
            this.start = i;
            if (i == this.segmentLimit) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        if (r4 != r7.rawLimit) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
        r0 = java.lang.Character.codePointAt(r7.seq, r4);
        r2 = java.lang.Character.charCount(r0) + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
        if (r7.nfcImpl.getFCD16(r0) > 255) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
        normalize(r7.pos, r4);
        r7.pos = r7.start;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0050, code lost:
        r4 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void nextSegment() {
        /*
            r7 = this;
            int r0 = r7.pos
            r1 = 0
            r2 = r1
        L_0x0004:
            java.lang.CharSequence r3 = r7.seq
            int r3 = java.lang.Character.codePointAt(r3, r0)
            int r4 = java.lang.Character.charCount(r3)
            int r4 = r4 + r0
            android.icu.impl.Normalizer2Impl r5 = r7.nfcImpl
            int r3 = r5.getFCD16(r3)
            int r5 = r3 >> 8
            if (r5 != 0) goto L_0x0022
            int r6 = r7.pos
            if (r0 == r6) goto L_0x0022
            r7.segmentLimit = r0
            r7.limit = r0
            goto L_0x0061
        L_0x0022:
            if (r5 == 0) goto L_0x0052
            if (r2 > r5) goto L_0x002c
            boolean r0 = android.icu.impl.coll.CollationFCD.isFCD16OfTibetanCompositeVowel(r3)
            if (r0 == 0) goto L_0x0052
        L_0x002c:
            int r0 = r7.rawLimit
            if (r4 != r0) goto L_0x0031
            goto L_0x0046
        L_0x0031:
            java.lang.CharSequence r0 = r7.seq
            int r0 = java.lang.Character.codePointAt(r0, r4)
            int r2 = java.lang.Character.charCount(r0)
            int r2 = r2 + r4
            android.icu.impl.Normalizer2Impl r3 = r7.nfcImpl
            int r0 = r3.getFCD16(r0)
            r3 = 255(0xff, float:3.57E-43)
            if (r0 > r3) goto L_0x0050
        L_0x0046:
            int r0 = r7.pos
            r7.normalize(r0, r4)
            int r0 = r7.start
            r7.pos = r0
            goto L_0x0061
        L_0x0050:
            r4 = r2
            goto L_0x002c
        L_0x0052:
            r2 = r3 & 255(0xff, float:3.57E-43)
            int r0 = r7.rawLimit
            if (r4 == r0) goto L_0x005d
            if (r2 != 0) goto L_0x005b
            goto L_0x005d
        L_0x005b:
            r0 = r4
            goto L_0x0004
        L_0x005d:
            r7.segmentLimit = r4
            r7.limit = r4
        L_0x0061:
            r7.checkDir = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.FCDUTF16CollationIterator.nextSegment():void");
    }

    private void switchToBackward() {
        if (this.checkDir > 0) {
            int i = this.pos;
            this.segmentLimit = i;
            this.limit = i;
            if (i == this.segmentStart) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
        if (r3 <= 255) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        if (r4 != 0) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        r0 = java.lang.Character.codePointBefore(r7.seq, r4);
        r2 = r4 - java.lang.Character.charCount(r0);
        r3 = r7.nfcImpl.getFCD16(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
        if (r3 != 0) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004b, code lost:
        r4 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004d, code lost:
        normalize(r4, r7.pos);
        r7.pos = r7.limit;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void previousSegment() {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.FCDUTF16CollationIterator.previousSegment():void");
    }

    private void normalize(int i, int i2) {
        if (this.normalized == null) {
            this.normalized = new StringBuilder();
        }
        this.nfcImpl.decompose(this.rawSeq, i, i2, this.normalized, i2 - i);
        this.segmentStart = i;
        this.segmentLimit = i2;
        StringBuilder sb = this.normalized;
        this.seq = sb;
        this.start = 0;
        this.limit = this.start + sb.length();
    }
}
