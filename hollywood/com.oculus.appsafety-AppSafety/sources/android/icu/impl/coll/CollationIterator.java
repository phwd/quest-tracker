package android.icu.impl.coll;

import android.icu.impl.Normalizer2Impl;
import android.icu.impl.Trie2_32;
import android.icu.util.BytesTrie;
import android.icu.util.CharsTrie;
import android.icu.util.ICUException;

public abstract class CollationIterator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected static final long NO_CP_AND_CE32 = -4294967104L;
    private CEBuffer ceBuffer;
    private int cesIndex;
    protected final CollationData data;
    private boolean isNumeric;
    private int numCpFwd;
    private SkippedState skipped;
    protected final Trie2_32 trie;

    /* access modifiers changed from: protected */
    public abstract void backwardNumCodePoints(int i);

    /* access modifiers changed from: protected */
    public abstract void forwardNumCodePoints(int i);

    public abstract int getOffset();

    public abstract int nextCodePoint();

    public abstract int previousCodePoint();

    public abstract void resetToOffset(int i);

    /* access modifiers changed from: private */
    public static final class CEBuffer {
        private static final int INITIAL_CAPACITY = 40;
        private long[] buffer = new long[40];
        int length = 0;

        CEBuffer() {
        }

        /* access modifiers changed from: package-private */
        public void append(long ce) {
            if (this.length >= 40) {
                ensureAppendCapacity(1);
            }
            long[] jArr = this.buffer;
            int i = this.length;
            this.length = i + 1;
            jArr[i] = ce;
        }

        /* access modifiers changed from: package-private */
        public void appendUnsafe(long ce) {
            long[] jArr = this.buffer;
            int i = this.length;
            this.length = i + 1;
            jArr[i] = ce;
        }

        /* access modifiers changed from: package-private */
        public void ensureAppendCapacity(int appCap) {
            int i;
            int capacity = this.buffer.length;
            if (this.length + appCap > capacity) {
                do {
                    if (capacity < 1000) {
                        capacity *= 4;
                    } else {
                        capacity *= 2;
                    }
                    i = this.length;
                } while (capacity < i + appCap);
                long[] newBuffer = new long[capacity];
                System.arraycopy((Object) this.buffer, 0, (Object) newBuffer, 0, i);
                this.buffer = newBuffer;
            }
        }

        /* access modifiers changed from: package-private */
        public void incLength() {
            if (this.length >= 40) {
                ensureAppendCapacity(1);
            }
            this.length++;
        }

        /* access modifiers changed from: package-private */
        public long set(int i, long ce) {
            this.buffer[i] = ce;
            return ce;
        }

        /* access modifiers changed from: package-private */
        public long get(int i) {
            return this.buffer[i];
        }

        /* access modifiers changed from: package-private */
        public long[] getCEs() {
            return this.buffer;
        }
    }

    /* access modifiers changed from: private */
    public static final class SkippedState {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final StringBuilder newBuffer = new StringBuilder();
        private final StringBuilder oldBuffer = new StringBuilder();
        private int pos;
        private int skipLengthAtMatch;
        private CharsTrie.State state = new CharsTrie.State();

        SkippedState() {
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            this.oldBuffer.setLength(0);
            this.pos = 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            return this.oldBuffer.length() == 0;
        }

        /* access modifiers changed from: package-private */
        public boolean hasNext() {
            return this.pos < this.oldBuffer.length();
        }

        /* access modifiers changed from: package-private */
        public int next() {
            int c = this.oldBuffer.codePointAt(this.pos);
            this.pos += Character.charCount(c);
            return c;
        }

        /* access modifiers changed from: package-private */
        public void incBeyond() {
            this.pos++;
        }

        /* access modifiers changed from: package-private */
        public int backwardNumCodePoints(int n) {
            int length = this.oldBuffer.length();
            int i = this.pos;
            int beyond = i - length;
            if (beyond <= 0) {
                this.pos = this.oldBuffer.offsetByCodePoints(i, -n);
                return 0;
            } else if (beyond >= n) {
                this.pos = i - n;
                return n;
            } else {
                this.pos = this.oldBuffer.offsetByCodePoints(length, beyond - n);
                return beyond;
            }
        }

        /* access modifiers changed from: package-private */
        public void setFirstSkipped(int c) {
            this.skipLengthAtMatch = 0;
            this.newBuffer.setLength(0);
            this.newBuffer.appendCodePoint(c);
        }

        /* access modifiers changed from: package-private */
        public void skip(int c) {
            this.newBuffer.appendCodePoint(c);
        }

        /* access modifiers changed from: package-private */
        public void recordMatch() {
            this.skipLengthAtMatch = this.newBuffer.length();
        }

        /* access modifiers changed from: package-private */
        public void replaceMatch() {
            int oldLength = this.oldBuffer.length();
            if (this.pos > oldLength) {
                this.pos = oldLength;
            }
            this.oldBuffer.delete(0, this.pos).insert(0, (CharSequence) this.newBuffer, 0, this.skipLengthAtMatch);
            this.pos = 0;
        }

        /* access modifiers changed from: package-private */
        public void saveTrieState(CharsTrie trie) {
            trie.saveState(this.state);
        }

        /* access modifiers changed from: package-private */
        public void resetToTrieState(CharsTrie trie) {
            trie.resetToState(this.state);
        }
    }

    public CollationIterator(CollationData d) {
        this.trie = d.trie;
        this.data = d;
        this.numCpFwd = -1;
        this.isNumeric = false;
        this.ceBuffer = null;
    }

    public CollationIterator(CollationData d, boolean numeric) {
        this.trie = d.trie;
        this.data = d;
        this.numCpFwd = -1;
        this.isNumeric = numeric;
        this.ceBuffer = new CEBuffer();
    }

    public boolean equals(Object other) {
        if (other == null || !getClass().equals(other.getClass())) {
            return false;
        }
        CollationIterator o = (CollationIterator) other;
        if (!(this.ceBuffer.length == o.ceBuffer.length && this.cesIndex == o.cesIndex && this.numCpFwd == o.numCpFwd && this.isNumeric == o.isNumeric)) {
            return false;
        }
        for (int i = 0; i < this.ceBuffer.length; i++) {
            if (this.ceBuffer.get(i) != o.ceBuffer.get(i)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return 0;
    }

    public final long nextCE() {
        CollationData d;
        if (this.cesIndex < this.ceBuffer.length) {
            CEBuffer cEBuffer = this.ceBuffer;
            int i = this.cesIndex;
            this.cesIndex = i + 1;
            return cEBuffer.get(i);
        }
        this.ceBuffer.incLength();
        long cAndCE32 = handleNextCE32();
        int c = (int) (cAndCE32 >> 32);
        int ce32 = (int) cAndCE32;
        int t = ce32 & 255;
        if (t < 192) {
            CEBuffer cEBuffer2 = this.ceBuffer;
            int i2 = this.cesIndex;
            this.cesIndex = i2 + 1;
            return cEBuffer2.set(i2, (((long) (ce32 & 65280)) << 16) | (((long) (-65536 & ce32)) << 32) | ((long) (t << 8)));
        }
        if (t != 192) {
            d = this.data;
        } else if (c < 0) {
            CEBuffer cEBuffer3 = this.ceBuffer;
            int i3 = this.cesIndex;
            this.cesIndex = i3 + 1;
            return cEBuffer3.set(i3, Collation.NO_CE);
        } else {
            d = this.data.base;
            ce32 = d.getCE32(c);
            t = ce32 & 255;
            if (t < 192) {
                CEBuffer cEBuffer4 = this.ceBuffer;
                int i4 = this.cesIndex;
                this.cesIndex = i4 + 1;
                return cEBuffer4.set(i4, (((long) (ce32 & 65280)) << 16) | (((long) (-65536 & ce32)) << 32) | ((long) (t << 8)));
            }
        }
        if (t != 193) {
            return nextCEFromCE32(d, c, ce32);
        }
        CEBuffer cEBuffer5 = this.ceBuffer;
        int i5 = this.cesIndex;
        this.cesIndex = i5 + 1;
        return cEBuffer5.set(i5, (((long) (ce32 - t)) << 32) | 83887360);
    }

    public final int fetchCEs() {
        while (nextCE() != Collation.NO_CE) {
            this.cesIndex = this.ceBuffer.length;
        }
        return this.ceBuffer.length;
    }

    /* access modifiers changed from: package-private */
    public final void setCurrentCE(long ce) {
        this.ceBuffer.set(this.cesIndex - 1, ce);
    }

    public final long previousCE(UVector32 offsets) {
        CollationData d;
        if (this.ceBuffer.length > 0) {
            CEBuffer cEBuffer = this.ceBuffer;
            int i = cEBuffer.length - 1;
            cEBuffer.length = i;
            return cEBuffer.get(i);
        }
        offsets.removeAllElements();
        int limitOffset = getOffset();
        int c = previousCodePoint();
        if (c < 0) {
            return Collation.NO_CE;
        }
        if (this.data.isUnsafeBackward(c, this.isNumeric)) {
            return previousCEUnsafe(c, offsets);
        }
        int ce32 = this.data.getCE32(c);
        if (ce32 == 192) {
            d = this.data.base;
            ce32 = d.getCE32(c);
        } else {
            d = this.data;
        }
        if (Collation.isSimpleOrLongCE32(ce32)) {
            return Collation.ceFromCE32(ce32);
        }
        appendCEsFromCE32(d, c, ce32, false);
        if (this.ceBuffer.length > 1) {
            offsets.addElement(getOffset());
            while (offsets.size() <= this.ceBuffer.length) {
                offsets.addElement(limitOffset);
            }
        }
        CEBuffer cEBuffer2 = this.ceBuffer;
        int i2 = cEBuffer2.length - 1;
        cEBuffer2.length = i2;
        return cEBuffer2.get(i2);
    }

    public final int getCEsLength() {
        return this.ceBuffer.length;
    }

    public final long getCE(int i) {
        return this.ceBuffer.get(i);
    }

    public final long[] getCEs() {
        return this.ceBuffer.getCEs();
    }

    /* access modifiers changed from: package-private */
    public final void clearCEs() {
        this.ceBuffer.length = 0;
        this.cesIndex = 0;
    }

    public final void clearCEsIfNoneRemaining() {
        if (this.cesIndex == this.ceBuffer.length) {
            clearCEs();
        }
    }

    /* access modifiers changed from: protected */
    public final void reset() {
        this.ceBuffer.length = 0;
        this.cesIndex = 0;
        SkippedState skippedState = this.skipped;
        if (skippedState != null) {
            skippedState.clear();
        }
    }

    /* access modifiers changed from: protected */
    public final void reset(boolean numeric) {
        if (this.ceBuffer == null) {
            this.ceBuffer = new CEBuffer();
        }
        reset();
        this.isNumeric = numeric;
    }

    /* access modifiers changed from: protected */
    public long handleNextCE32() {
        int c = nextCodePoint();
        if (c < 0) {
            return NO_CP_AND_CE32;
        }
        return makeCodePointAndCE32Pair(c, this.data.getCE32(c));
    }

    /* access modifiers changed from: protected */
    public long makeCodePointAndCE32Pair(int c, int ce32) {
        return (((long) c) << 32) | (((long) ce32) & 4294967295L);
    }

    /* access modifiers changed from: protected */
    public char handleGetTrailSurrogate() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean forbidSurrogateCodePoints() {
        return false;
    }

    /* access modifiers changed from: protected */
    public int getDataCE32(int c) {
        return this.data.getCE32(c);
    }

    /* access modifiers changed from: protected */
    public int getCE32FromBuilderData(int ce32) {
        throw new ICUException("internal program error: should be unreachable");
    }

    /* access modifiers changed from: protected */
    public final void appendCEsFromCE32(CollationData d, int c, int ce32, boolean forward) {
        int nextCp;
        while (Collation.isSpecialCE32(ce32)) {
            switch (Collation.tagFromCE32(ce32)) {
                case 0:
                case 3:
                    throw new ICUException("internal program error: should be unreachable");
                case 1:
                    this.ceBuffer.append(Collation.ceFromLongPrimaryCE32(ce32));
                    return;
                case 2:
                    this.ceBuffer.append(Collation.ceFromLongSecondaryCE32(ce32));
                    return;
                case 4:
                    this.ceBuffer.ensureAppendCapacity(2);
                    CEBuffer cEBuffer = this.ceBuffer;
                    cEBuffer.set(cEBuffer.length, Collation.latinCE0FromCE32(ce32));
                    CEBuffer cEBuffer2 = this.ceBuffer;
                    cEBuffer2.set(cEBuffer2.length + 1, Collation.latinCE1FromCE32(ce32));
                    this.ceBuffer.length += 2;
                    return;
                case 5:
                    int index = Collation.indexFromCE32(ce32);
                    int length = Collation.lengthFromCE32(ce32);
                    this.ceBuffer.ensureAppendCapacity(length);
                    while (true) {
                        int index2 = index + 1;
                        this.ceBuffer.appendUnsafe(Collation.ceFromCE32(d.ce32s[index]));
                        length--;
                        if (length > 0) {
                            index = index2;
                        } else {
                            return;
                        }
                    }
                case 6:
                    int index3 = Collation.indexFromCE32(ce32);
                    int length2 = Collation.lengthFromCE32(ce32);
                    this.ceBuffer.ensureAppendCapacity(length2);
                    while (true) {
                        int index4 = index3 + 1;
                        this.ceBuffer.appendUnsafe(d.ces[index3]);
                        length2--;
                        if (length2 > 0) {
                            index3 = index4;
                        } else {
                            return;
                        }
                    }
                case 7:
                    ce32 = getCE32FromBuilderData(ce32);
                    if (ce32 != 192) {
                        break;
                    } else {
                        d = this.data.base;
                        ce32 = d.getCE32(c);
                        break;
                    }
                case 8:
                    if (forward) {
                        backwardNumCodePoints(1);
                    }
                    ce32 = getCE32FromPrefix(d, ce32);
                    if (!forward) {
                        break;
                    } else {
                        forwardNumCodePoints(1);
                        break;
                    }
                case 9:
                    int index5 = Collation.indexFromCE32(ce32);
                    int defaultCE32 = d.getCE32FromContexts(index5);
                    if (forward) {
                        if (this.skipped == null && this.numCpFwd < 0) {
                            nextCp = nextCodePoint();
                            if (nextCp >= 0) {
                                if ((ce32 & 512) != 0 && !CollationFCD.mayHaveLccc(nextCp)) {
                                    backwardNumCodePoints(1);
                                    ce32 = defaultCE32;
                                    break;
                                }
                            } else {
                                ce32 = defaultCE32;
                                break;
                            }
                        } else {
                            nextCp = nextSkippedCodePoint();
                            if (nextCp >= 0) {
                                if ((ce32 & 512) != 0 && !CollationFCD.mayHaveLccc(nextCp)) {
                                    backwardNumSkipped(1);
                                    ce32 = defaultCE32;
                                    break;
                                }
                            } else {
                                ce32 = defaultCE32;
                                break;
                            }
                        }
                        ce32 = nextCE32FromContraction(d, ce32, d.contexts, index5 + 2, defaultCE32, nextCp);
                        if (ce32 != 1) {
                            break;
                        } else {
                            return;
                        }
                    } else {
                        ce32 = defaultCE32;
                        break;
                    }
                case 10:
                    if (!this.isNumeric) {
                        ce32 = d.ce32s[Collation.indexFromCE32(ce32)];
                        break;
                    } else {
                        appendNumericCEs(ce32, forward);
                        return;
                    }
                case 11:
                    ce32 = d.ce32s[0];
                    break;
                case 12:
                    int[] jamoCE32s = d.jamoCE32s;
                    int c2 = c - Normalizer2Impl.Hangul.HANGUL_BASE;
                    int t = c2 % 28;
                    int c3 = c2 / 28;
                    int v = c3 % 21;
                    int c4 = c3 / 21;
                    if ((ce32 & 256) == 0) {
                        appendCEsFromCE32(d, -1, jamoCE32s[c4], forward);
                        appendCEsFromCE32(d, -1, jamoCE32s[v + 19], forward);
                        if (t != 0) {
                            ce32 = jamoCE32s[t + 39];
                            c = -1;
                            break;
                        } else {
                            return;
                        }
                    } else {
                        this.ceBuffer.ensureAppendCapacity(t == 0 ? 2 : 3);
                        CEBuffer cEBuffer3 = this.ceBuffer;
                        cEBuffer3.set(cEBuffer3.length, Collation.ceFromCE32(jamoCE32s[c4]));
                        CEBuffer cEBuffer4 = this.ceBuffer;
                        cEBuffer4.set(cEBuffer4.length + 1, Collation.ceFromCE32(jamoCE32s[v + 19]));
                        this.ceBuffer.length += 2;
                        if (t != 0) {
                            this.ceBuffer.appendUnsafe(Collation.ceFromCE32(jamoCE32s[t + 39]));
                            return;
                        }
                        return;
                    }
                case 13:
                    char trail = handleGetTrailSurrogate();
                    if (!Character.isLowSurrogate(trail)) {
                        ce32 = -1;
                        break;
                    } else {
                        c = Character.toCodePoint((char) c, trail);
                        int ce322 = ce32 & 768;
                        if (ce322 != 0) {
                            if (ce322 != 256) {
                                int cE32FromSupplementary = d.getCE32FromSupplementary(c);
                                ce32 = cE32FromSupplementary;
                                if (cE32FromSupplementary != 192) {
                                    break;
                                }
                            }
                            d = d.base;
                            ce32 = d.getCE32FromSupplementary(c);
                            break;
                        } else {
                            ce32 = -1;
                            break;
                        }
                    }
                case 14:
                    this.ceBuffer.append(d.getCEFromOffsetCE32(c, ce32));
                    return;
                case 15:
                    if (isSurrogate(c) && forbidSurrogateCodePoints()) {
                        ce32 = -195323;
                        break;
                    } else {
                        this.ceBuffer.append(Collation.unassignedCEFromCodePoint(c));
                        return;
                    }
            }
        }
        this.ceBuffer.append(Collation.ceFromSimpleCE32(ce32));
    }

    private static final boolean isSurrogate(int c) {
        return (c & -2048) == 55296;
    }

    protected static final boolean isLeadSurrogate(int c) {
        return (c & -1024) == 55296;
    }

    protected static final boolean isTrailSurrogate(int c) {
        return (c & -1024) == 56320;
    }

    private final long nextCEFromCE32(CollationData d, int c, int ce32) {
        this.ceBuffer.length--;
        appendCEsFromCE32(d, c, ce32, true);
        CEBuffer cEBuffer = this.ceBuffer;
        int i = this.cesIndex;
        this.cesIndex = i + 1;
        return cEBuffer.get(i);
    }

    private final int getCE32FromPrefix(CollationData d, int ce32) {
        BytesTrie.Result match;
        int index = Collation.indexFromCE32(ce32);
        int ce322 = d.getCE32FromContexts(index);
        int lookBehind = 0;
        CharsTrie prefixes = new CharsTrie(d.contexts, index + 2);
        do {
            int c = previousCodePoint();
            if (c < 0) {
                break;
            }
            lookBehind++;
            match = prefixes.nextForCodePoint(c);
            if (match.hasValue()) {
                ce322 = prefixes.getValue();
            }
        } while (match.hasNext());
        forwardNumCodePoints(lookBehind);
        return ce322;
    }

    private final int nextSkippedCodePoint() {
        SkippedState skippedState = this.skipped;
        if (skippedState != null && skippedState.hasNext()) {
            return this.skipped.next();
        }
        if (this.numCpFwd == 0) {
            return -1;
        }
        int c = nextCodePoint();
        SkippedState skippedState2 = this.skipped;
        if (skippedState2 != null && !skippedState2.isEmpty() && c >= 0) {
            this.skipped.incBeyond();
        }
        int i = this.numCpFwd;
        if (i > 0 && c >= 0) {
            this.numCpFwd = i - 1;
        }
        return c;
    }

    private final void backwardNumSkipped(int n) {
        SkippedState skippedState = this.skipped;
        if (skippedState != null && !skippedState.isEmpty()) {
            n = this.skipped.backwardNumCodePoints(n);
        }
        backwardNumCodePoints(n);
        int i = this.numCpFwd;
        if (i >= 0) {
            this.numCpFwd = i + n;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006b, code lost:
        if ((r18 & 1024) == 0) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006f, code lost:
        if ((r18 & 256) == 0) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0071, code lost:
        if (r1 >= r0) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0074, code lost:
        if (r1 <= 1) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0076, code lost:
        backwardNumSkipped(r1);
        r14 = r0 - (r1 - 1);
        r15 = 1;
        r13 = nextSkippedCodePoint();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0085, code lost:
        r14 = r0;
        r15 = r1;
        r13 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0090, code lost:
        if (r17.getFCD16(r13) <= 255) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009e, code lost:
        return nextCE32FromDiscontiguousContraction(r17, r2, r12, r14, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009f, code lost:
        r15 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a2, code lost:
        backwardNumSkipped(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a5, code lost:
        return r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int nextCE32FromContraction(android.icu.impl.coll.CollationData r17, int r18, java.lang.CharSequence r19, int r20, int r21, int r22) {
        /*
        // Method dump skipped, instructions count: 166
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.CollationIterator.nextCE32FromContraction(android.icu.impl.coll.CollationData, int, java.lang.CharSequence, int, int, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00aa A[EDGE_INSN: B:51:0x00aa->B:36:0x00aa ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int nextCE32FromDiscontiguousContraction(android.icu.impl.coll.CollationData r9, android.icu.util.CharsTrie r10, int r11, int r12, int r13) {
        /*
        // Method dump skipped, instructions count: 242
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.CollationIterator.nextCE32FromDiscontiguousContraction(android.icu.impl.coll.CollationData, android.icu.util.CharsTrie, int, int, int):int");
    }

    private final long previousCEUnsafe(int c, UVector32 offsets) {
        int c2;
        int numBackward = 1;
        do {
            c2 = previousCodePoint();
            if (c2 < 0) {
                break;
            }
            numBackward++;
        } while (this.data.isUnsafeBackward(c2, this.isNumeric));
        this.numCpFwd = numBackward;
        this.cesIndex = 0;
        int offset = getOffset();
        while (true) {
            int i = this.numCpFwd;
            if (i > 0) {
                this.numCpFwd = i - 1;
                nextCE();
                this.cesIndex = this.ceBuffer.length;
                offsets.addElement(offset);
                offset = getOffset();
                while (offsets.size() < this.ceBuffer.length) {
                    offsets.addElement(offset);
                }
            } else {
                offsets.addElement(offset);
                this.numCpFwd = -1;
                backwardNumCodePoints(numBackward);
                this.cesIndex = 0;
                CEBuffer cEBuffer = this.ceBuffer;
                int i2 = cEBuffer.length - 1;
                cEBuffer.length = i2;
                return cEBuffer.get(i2);
            }
        }
    }

    private final void appendNumericCEs(int ce32, boolean forward) {
        int c;
        StringBuilder digits = new StringBuilder();
        if (forward) {
            while (true) {
                digits.append(Collation.digitFromCE32(ce32));
                if (this.numCpFwd != 0 && (c = nextCodePoint()) >= 0) {
                    ce32 = this.data.getCE32(c);
                    if (ce32 == 192) {
                        ce32 = this.data.base.getCE32(c);
                    }
                    if (!Collation.hasCE32Tag(ce32, 10)) {
                        backwardNumCodePoints(1);
                        break;
                    }
                    int i = this.numCpFwd;
                    if (i > 0) {
                        this.numCpFwd = i - 1;
                    }
                }
            }
        } else {
            while (true) {
                digits.append(Collation.digitFromCE32(ce32));
                int c2 = previousCodePoint();
                if (c2 < 0) {
                    break;
                }
                ce32 = this.data.getCE32(c2);
                if (ce32 == 192) {
                    ce32 = this.data.base.getCE32(c2);
                }
                if (!Collation.hasCE32Tag(ce32, 10)) {
                    forwardNumCodePoints(1);
                    break;
                }
            }
            digits.reverse();
        }
        int pos = 0;
        while (true) {
            if (pos >= digits.length() - 1 || digits.charAt(pos) != 0) {
                int segmentLength = digits.length() - pos;
                if (segmentLength > 254) {
                    segmentLength = 254;
                }
                appendNumericSegmentCEs(digits.subSequence(pos, pos + segmentLength));
                pos += segmentLength;
                if (pos >= digits.length()) {
                    return;
                }
            } else {
                pos++;
            }
        }
    }

    private final void appendNumericSegmentCEs(CharSequence digits) {
        int pos;
        int pair;
        int length = digits.length();
        long numericPrimary = this.data.numericPrimary;
        if (length <= 7) {
            int value = digits.charAt(0);
            for (int i = 1; i < length; i++) {
                value = (value * 10) + digits.charAt(i);
            }
            if (value < 74) {
                this.ceBuffer.append(Collation.makeCE(((long) ((2 + value) << 16)) | numericPrimary));
                return;
            }
            int value2 = value - 74;
            int firstByte = 2 + 74;
            if (value2 < 40 * 254) {
                this.ceBuffer.append(Collation.makeCE(((long) (((value2 / 254) + firstByte) << 16)) | numericPrimary | ((long) (((value2 % 254) + 2) << 8))));
                return;
            }
            int value3 = value2 - (40 * 254);
            int firstByte2 = firstByte + 40;
            if (value3 < 16 * 254 * 254) {
                int value4 = value3 / 254;
                this.ceBuffer.append(Collation.makeCE(((long) ((value3 % 254) + 2)) | numericPrimary | ((long) (((value4 % 254) + 2) << 8)) | ((long) ((((value4 / 254) % 254) + firstByte2) << 16))));
                return;
            }
        }
        long primary = ((long) ((((length + 1) / 2) + 128) << 16)) | numericPrimary;
        while (digits.charAt(length - 1) == 0 && digits.charAt(length - 2) == 0) {
            length -= 2;
        }
        if ((length & 1) != 0) {
            pair = digits.charAt(0);
            pos = 1;
        } else {
            pair = (digits.charAt(0) * 10) + digits.charAt(1);
            pos = 2;
        }
        int pair2 = (pair * 2) + 11;
        int shift = 8;
        while (pos < length) {
            if (shift == 0) {
                this.ceBuffer.append(Collation.makeCE(primary | ((long) pair2)));
                primary = numericPrimary;
                shift = 16;
            } else {
                primary |= (long) (pair2 << shift);
                shift -= 8;
            }
            pair2 = (((digits.charAt(pos) * '\n') + digits.charAt(pos + 1)) * 2) + 11;
            pos += 2;
        }
        this.ceBuffer.append(Collation.makeCE(primary | ((long) ((pair2 - 1) << shift))));
    }
}
