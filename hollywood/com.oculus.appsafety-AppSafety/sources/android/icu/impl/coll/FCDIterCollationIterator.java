package android.icu.impl.coll;

import android.icu.impl.Normalizer2Impl;
import android.icu.text.UCharacterIterator;

public final class FCDIterCollationIterator extends IterCollationIterator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int limit;
    private final Normalizer2Impl nfcImpl;
    private StringBuilder normalized;
    private int pos;
    private StringBuilder s;
    private int start;
    private State state = State.ITER_CHECK_FWD;

    /* access modifiers changed from: private */
    public enum State {
        ITER_CHECK_FWD,
        ITER_CHECK_BWD,
        ITER_IN_FCD_SEGMENT,
        IN_NORM_ITER_AT_LIMIT,
        IN_NORM_ITER_AT_START
    }

    public FCDIterCollationIterator(CollationData data, boolean numeric, UCharacterIterator ui, int startIndex) {
        super(data, numeric, ui);
        this.start = startIndex;
        this.nfcImpl = data.nfcImpl;
    }

    @Override // android.icu.impl.coll.CollationIterator, android.icu.impl.coll.IterCollationIterator
    public void resetToOffset(int newOffset) {
        super.resetToOffset(newOffset);
        this.start = newOffset;
        this.state = State.ITER_CHECK_FWD;
    }

    @Override // android.icu.impl.coll.CollationIterator, android.icu.impl.coll.IterCollationIterator
    public int getOffset() {
        if (this.state.compareTo((Enum) State.ITER_CHECK_BWD) <= 0) {
            return this.iter.getIndex();
        }
        if (this.state == State.ITER_IN_FCD_SEGMENT) {
            return this.pos;
        }
        if (this.pos == 0) {
            return this.start;
        }
        return this.limit;
    }

    @Override // android.icu.impl.coll.CollationIterator, android.icu.impl.coll.IterCollationIterator
    public int nextCodePoint() {
        int c;
        while (true) {
            if (this.state == State.ITER_CHECK_FWD) {
                c = this.iter.next();
                if (c < 0) {
                    return c;
                }
                if (CollationFCD.hasTccc(c) && (CollationFCD.maybeTibetanCompositeVowel(c) || CollationFCD.hasLccc(this.iter.current()))) {
                    this.iter.previous();
                    if (!nextSegment()) {
                        return -1;
                    }
                }
            } else if (this.state == State.ITER_IN_FCD_SEGMENT && this.pos != this.limit) {
                int c2 = this.iter.nextCodePoint();
                this.pos += Character.charCount(c2);
                return c2;
            } else if (this.state.compareTo((Enum) State.IN_NORM_ITER_AT_LIMIT) < 0 || this.pos == this.normalized.length()) {
                switchToForward();
            } else {
                int c3 = this.normalized.codePointAt(this.pos);
                this.pos += Character.charCount(c3);
                return c3;
            }
        }
        if (isLeadSurrogate(c)) {
            int trail = this.iter.next();
            if (isTrailSurrogate(trail)) {
                return Character.toCodePoint((char) c, (char) trail);
            }
            if (trail >= 0) {
                this.iter.previous();
            }
        }
        return c;
    }

    @Override // android.icu.impl.coll.CollationIterator, android.icu.impl.coll.IterCollationIterator
    public int previousCodePoint() {
        int i;
        int c;
        while (true) {
            if (this.state == State.ITER_CHECK_BWD) {
                c = this.iter.previous();
                if (c >= 0) {
                    if (!CollationFCD.hasLccc(c)) {
                        break;
                    }
                    int prev = -1;
                    if (!CollationFCD.maybeTibetanCompositeVowel(c)) {
                        int previous = this.iter.previous();
                        prev = previous;
                        if (!CollationFCD.hasTccc(previous)) {
                            if (isTrailSurrogate(c)) {
                                if (prev < 0) {
                                    prev = this.iter.previous();
                                }
                                if (isLeadSurrogate(prev)) {
                                    return Character.toCodePoint((char) prev, (char) c);
                                }
                            }
                            if (prev >= 0) {
                                this.iter.next();
                            }
                        }
                    }
                    this.iter.next();
                    if (prev >= 0) {
                        this.iter.next();
                    }
                    if (!previousSegment()) {
                        return -1;
                    }
                } else {
                    this.pos = 0;
                    this.start = 0;
                    this.state = State.ITER_IN_FCD_SEGMENT;
                    return -1;
                }
            } else if (this.state == State.ITER_IN_FCD_SEGMENT && this.pos != this.start) {
                int c2 = this.iter.previousCodePoint();
                this.pos -= Character.charCount(c2);
                return c2;
            } else if (this.state.compareTo((Enum) State.IN_NORM_ITER_AT_LIMIT) < 0 || (i = this.pos) == 0) {
                switchToBackward();
            } else {
                int c3 = this.normalized.codePointBefore(i);
                this.pos -= Character.charCount(c3);
                return c3;
            }
        }
        return c;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.CollationIterator, android.icu.impl.coll.IterCollationIterator
    public long handleNextCE32() {
        int c;
        while (true) {
            if (this.state != State.ITER_CHECK_FWD) {
                if (this.state != State.ITER_IN_FCD_SEGMENT || this.pos == this.limit) {
                    if (this.state.compareTo((Enum) State.IN_NORM_ITER_AT_LIMIT) >= 0 && this.pos != this.normalized.length()) {
                        StringBuilder sb = this.normalized;
                        int i = this.pos;
                        this.pos = i + 1;
                        c = sb.charAt(i);
                        break;
                    }
                    switchToForward();
                } else {
                    c = this.iter.next();
                    this.pos++;
                    break;
                }
            } else {
                c = this.iter.next();
                if (c >= 0) {
                    if (!CollationFCD.hasTccc(c) || (!CollationFCD.maybeTibetanCompositeVowel(c) && !CollationFCD.hasLccc(this.iter.current()))) {
                        break;
                    }
                    this.iter.previous();
                    if (!nextSegment()) {
                        return 192;
                    }
                } else {
                    return -4294967104L;
                }
            }
        }
        return makeCodePointAndCE32Pair(c, this.trie.getFromU16SingleLead((char) c));
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.CollationIterator, android.icu.impl.coll.IterCollationIterator
    public char handleGetTrailSurrogate() {
        if (this.state.compareTo((Enum) State.ITER_IN_FCD_SEGMENT) <= 0) {
            int trail = this.iter.next();
            if (isTrailSurrogate(trail)) {
                if (this.state == State.ITER_IN_FCD_SEGMENT) {
                    this.pos++;
                }
            } else if (trail >= 0) {
                this.iter.previous();
            }
            return (char) trail;
        }
        char trail2 = this.normalized.charAt(this.pos);
        if (Character.isLowSurrogate(trail2)) {
            this.pos++;
        }
        return trail2;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.CollationIterator, android.icu.impl.coll.IterCollationIterator
    public void forwardNumCodePoints(int num) {
        while (num > 0 && nextCodePoint() >= 0) {
            num--;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.coll.CollationIterator, android.icu.impl.coll.IterCollationIterator
    public void backwardNumCodePoints(int num) {
        while (num > 0 && previousCodePoint() >= 0) {
            num--;
        }
    }

    private void switchToForward() {
        if (this.state == State.ITER_CHECK_BWD) {
            int index = this.iter.getIndex();
            this.pos = index;
            this.start = index;
            if (this.pos == this.limit) {
                this.state = State.ITER_CHECK_FWD;
            } else {
                this.state = State.ITER_IN_FCD_SEGMENT;
            }
        } else {
            if (this.state != State.ITER_IN_FCD_SEGMENT) {
                if (this.state == State.IN_NORM_ITER_AT_START) {
                    this.iter.moveIndex(this.limit - this.start);
                }
                this.start = this.limit;
            }
            this.state = State.ITER_CHECK_FWD;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004b, code lost:
        r2 = r8.iter.nextCodePoint();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0051, code lost:
        if (r2 >= 0) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005c, code lost:
        if (r8.nfcImpl.getFCD16(r2) > 255) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005e, code lost:
        r8.iter.previousCodePoint();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0064, code lost:
        normalize(r8.s);
        r6 = r8.pos;
        r8.start = r6;
        r8.limit = r6 + r8.s.length();
        r8.state = android.icu.impl.coll.FCDIterCollationIterator.State.IN_NORM_ITER_AT_LIMIT;
        r8.pos = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007c, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007d, code lost:
        r8.s.appendCodePoint(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean nextSegment() {
        /*
        // Method dump skipped, instructions count: 167
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.FCDIterCollationIterator.nextSegment():boolean");
    }

    private void switchToBackward() {
        if (this.state == State.ITER_CHECK_FWD) {
            int index = this.iter.getIndex();
            this.pos = index;
            this.limit = index;
            if (this.pos == this.start) {
                this.state = State.ITER_CHECK_BWD;
            } else {
                this.state = State.ITER_IN_FCD_SEGMENT;
            }
        } else {
            if (this.state != State.ITER_IN_FCD_SEGMENT) {
                if (this.state == State.IN_NORM_ITER_AT_LIMIT) {
                    this.iter.moveIndex(this.start - this.limit);
                }
                this.limit = this.start;
            }
            this.state = State.ITER_CHECK_BWD;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        if (r3 <= 255) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0052, code lost:
        r1 = r7.iter.previousCodePoint();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0058, code lost:
        if (r1 >= 0) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005b, code lost:
        r3 = r7.nfcImpl.getFCD16(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0061, code lost:
        if (r3 != 0) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0063, code lost:
        r7.iter.nextCodePoint();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0069, code lost:
        r7.s.appendCodePoint(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006f, code lost:
        r7.s.reverse();
        normalize(r7.s);
        r5 = r7.pos;
        r7.limit = r5;
        r7.start = r5 - r7.s.length();
        r7.state = android.icu.impl.coll.FCDIterCollationIterator.State.IN_NORM_ITER_AT_START;
        r7.pos = r7.normalized.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0092, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean previousSegment() {
        /*
        // Method dump skipped, instructions count: 182
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.FCDIterCollationIterator.previousSegment():boolean");
    }

    private void normalize(CharSequence s2) {
        if (this.normalized == null) {
            this.normalized = new StringBuilder();
        }
        this.nfcImpl.decompose(s2, this.normalized);
    }
}
