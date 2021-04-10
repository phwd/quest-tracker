package android.icu.util;

import android.icu.util.CodePointMap;
import android.icu.util.CodePointTrie;
import java.util.Arrays;

public final class MutableCodePointTrie extends CodePointMap implements Cloneable {
    private int[] data = new int[16384];
    private int dataLength;
    private int dataNullOffset = -1;
    private int errorValue;
    private byte[] flags = new byte[69632];
    private int highStart;
    private int highValue;
    private int[] index = new int[4096];
    private char[] index16;
    private int index3NullOffset = -1;
    private int initialValue;
    private int origInitialValue;

    public MutableCodePointTrie(int i, int i2) {
        this.origInitialValue = i;
        this.initialValue = i;
        this.errorValue = i2;
        this.highValue = i;
    }

    public MutableCodePointTrie clone() {
        try {
            MutableCodePointTrie mutableCodePointTrie = (MutableCodePointTrie) super.clone();
            mutableCodePointTrie.index = new int[(this.highStart <= 65536 ? 4096 : 69632)];
            mutableCodePointTrie.flags = new byte[69632];
            int i = this.highStart >> 4;
            for (int i2 = 0; i2 < i; i2++) {
                mutableCodePointTrie.index[i2] = this.index[i2];
                mutableCodePointTrie.flags[i2] = this.flags[i2];
            }
            mutableCodePointTrie.index3NullOffset = this.index3NullOffset;
            mutableCodePointTrie.data = (int[]) this.data.clone();
            mutableCodePointTrie.dataLength = this.dataLength;
            mutableCodePointTrie.dataNullOffset = this.dataNullOffset;
            mutableCodePointTrie.origInitialValue = this.origInitialValue;
            mutableCodePointTrie.initialValue = this.initialValue;
            mutableCodePointTrie.errorValue = this.errorValue;
            mutableCodePointTrie.highStart = this.highStart;
            mutableCodePointTrie.highValue = this.highValue;
            return mutableCodePointTrie;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    private void clear() {
        this.dataNullOffset = -1;
        this.index3NullOffset = -1;
        this.dataLength = 0;
        int i = this.origInitialValue;
        this.initialValue = i;
        this.highValue = i;
        this.highStart = 0;
        this.index16 = null;
    }

    public int get(int i) {
        if (i < 0 || 1114111 < i) {
            return this.errorValue;
        }
        if (i >= this.highStart) {
            return this.highValue;
        }
        int i2 = i >> 4;
        if (this.flags[i2] == 0) {
            return this.index[i2];
        }
        return this.data[this.index[i2] + (i & 15)];
    }

    private static final int maybeFilterValue(int i, int i2, int i3, CodePointMap.ValueFilter valueFilter) {
        if (i == i2) {
            return i3;
        }
        return valueFilter != null ? valueFilter.apply(i) : i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0087, code lost:
        if (r12 == null) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x008f, code lost:
        if (maybeFilterValue(r9, r10.initialValue, r2, r12) != r7) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0091, code lost:
        r13.set(r11, r0 - 1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0095, code lost:
        return true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0096 A[EDGE_INSN: B:63:0x0096->B:47:0x0096 ?: BREAK  , SYNTHETIC] */
    @Override // android.icu.util.CodePointMap
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getRange(int r11, android.icu.util.CodePointMap.ValueFilter r12, android.icu.util.CodePointMap.Range r13) {
        /*
        // Method dump skipped, instructions count: 176
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.MutableCodePointTrie.getRange(int, android.icu.util.CodePointMap$ValueFilter, android.icu.util.CodePointMap$Range):boolean");
    }

    private void writeBlock(int i, int i2) {
        Arrays.fill(this.data, i, i + 16, i2);
    }

    public void set(int i, int i2) {
        if (i < 0 || 1114111 < i) {
            throw new IllegalArgumentException("invalid code point");
        }
        ensureHighStart(i);
        this.data[getDataBlock(i >> 4) + (i & 15)] = i2;
    }

    public CodePointTrie buildImmutable(CodePointTrie.Type type, CodePointTrie.ValueWidth valueWidth) {
        if (type == null || valueWidth == null) {
            throw new IllegalArgumentException("The type and valueWidth must be specified.");
        }
        try {
            return build(type, valueWidth);
        } finally {
            clear();
        }
    }

    private void ensureHighStart(int i) {
        int i2 = this.highStart;
        if (i >= i2) {
            int i3 = (i + 512) & -512;
            int i4 = i2 >> 4;
            int i5 = i3 >> 4;
            if (i5 > this.index.length) {
                int[] iArr = new int[69632];
                for (int i6 = 0; i6 < i4; i6++) {
                    iArr[i6] = this.index[i6];
                }
                this.index = iArr;
            }
            do {
                this.flags[i4] = 0;
                this.index[i4] = this.initialValue;
                i4++;
            } while (i4 < i5);
            this.highStart = i3;
        }
    }

    private int allocDataBlock(int i) {
        int i2 = this.dataLength;
        int i3 = i + i2;
        int[] iArr = this.data;
        if (i3 > iArr.length) {
            int i4 = 1114112;
            if (iArr.length < 131072) {
                i4 = 131072;
            } else if (iArr.length >= 1114112) {
                throw new AssertionError();
            }
            int[] iArr2 = new int[i4];
            for (int i5 = 0; i5 < this.dataLength; i5++) {
                iArr2[i5] = this.data[i5];
            }
            this.data = iArr2;
        }
        this.dataLength = i3;
        return i2;
    }

    private int getDataBlock(int i) {
        if (this.flags[i] == 1) {
            return this.index[i];
        }
        if (i < 4096) {
            int allocDataBlock = allocDataBlock(64);
            int i2 = i & -4;
            int i3 = i2 + 4;
            while (true) {
                writeBlock(allocDataBlock, this.index[i2]);
                this.flags[i2] = 1;
                int[] iArr = this.index;
                int i4 = i2 + 1;
                iArr[i2] = allocDataBlock;
                allocDataBlock += 16;
                if (i4 >= i3) {
                    return iArr[i];
                }
                i2 = i4;
            }
        } else {
            int allocDataBlock2 = allocDataBlock(16);
            if (allocDataBlock2 < 0) {
                return allocDataBlock2;
            }
            writeBlock(allocDataBlock2, this.index[i]);
            this.flags[i] = 1;
            this.index[i] = allocDataBlock2;
            return allocDataBlock2;
        }
    }

    private void maskValues(int i) {
        this.initialValue &= i;
        this.errorValue &= i;
        this.highValue &= i;
        int i2 = this.highStart >> 4;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.flags[i3] == 0) {
                int[] iArr = this.index;
                iArr[i3] = iArr[i3] & i;
            }
        }
        for (int i4 = 0; i4 < this.dataLength; i4++) {
            int[] iArr2 = this.data;
            iArr2[i4] = iArr2[i4] & i;
        }
    }

    /* access modifiers changed from: private */
    public static boolean equalBlocks(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        while (i3 > 0 && iArr[i] == iArr2[i2]) {
            i++;
            i2++;
            i3--;
        }
        return i3 == 0;
    }

    /* access modifiers changed from: private */
    public static boolean equalBlocks(char[] cArr, int i, int[] iArr, int i2, int i3) {
        while (i3 > 0 && cArr[i] == iArr[i2]) {
            i++;
            i2++;
            i3--;
        }
        return i3 == 0;
    }

    /* access modifiers changed from: private */
    public static boolean equalBlocks(char[] cArr, int i, char[] cArr2, int i2, int i3) {
        while (i3 > 0 && cArr[i] == cArr2[i2]) {
            i++;
            i2++;
            i3--;
        }
        return i3 == 0;
    }

    /* access modifiers changed from: private */
    public static boolean allValuesSameAs(int[] iArr, int i, int i2, int i3) {
        int i4 = i2 + i;
        while (i < i4 && iArr[i] == i3) {
            i++;
        }
        return i == i4;
    }

    private static int findSameBlock(char[] cArr, int i, int i2, char[] cArr2, int i3, int i4) {
        int i5 = i2 - i4;
        while (i <= i5) {
            if (equalBlocks(cArr, i, cArr2, i3, i4)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static int findAllSameBlock(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i2 - i4;
        while (i <= i5) {
            if (iArr[i] == i3) {
                for (int i6 = 1; i6 != i4; i6++) {
                    int i7 = i + i6;
                    if (iArr[i7] != i3) {
                        i = i7;
                    }
                }
                return i;
            }
            i++;
        }
        return -1;
    }

    private static int getOverlap(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        int i4 = i3 - 1;
        while (i4 > 0 && !equalBlocks(iArr, i - i4, iArr2, i2, i4)) {
            i4--;
        }
        return i4;
    }

    private static int getOverlap(char[] cArr, int i, int[] iArr, int i2, int i3) {
        int i4 = i3 - 1;
        while (i4 > 0 && !equalBlocks(cArr, i - i4, iArr, i2, i4)) {
            i4--;
        }
        return i4;
    }

    private static int getOverlap(char[] cArr, int i, char[] cArr2, int i2, int i3) {
        int i4 = i3 - 1;
        while (i4 > 0 && !equalBlocks(cArr, i - i4, cArr2, i2, i4)) {
            i4--;
        }
        return i4;
    }

    private static int getAllSameOverlap(int[] iArr, int i, int i2, int i3) {
        int i4 = i - (i3 - 1);
        int i5 = i;
        while (i4 < i5 && iArr[i5 - 1] == i2) {
            i5--;
        }
        return i - i5;
    }

    private static boolean isStartOfSomeFastBlock(int i, int[] iArr, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 4) {
            if (iArr[i3] == i) {
                return true;
            }
        }
        return false;
    }

    private int findHighStart() {
        boolean z;
        int i = this.highStart >> 4;
        do {
            z = false;
            if (i <= 0) {
                return 0;
            }
            i--;
            if (this.flags[i] != 0) {
                int i2 = this.index[i];
                int i3 = 0;
                while (true) {
                    if (i3 == 16) {
                        break;
                    } else if (this.data[i2 + i3] != this.highValue) {
                        continue;
                        break;
                    } else {
                        i3++;
                    }
                }
            } else if (this.index[i] != this.highValue) {
                continue;
            }
            z = true;
            continue;
        } while (z);
        return (i + 1) << 4;
    }

    /* access modifiers changed from: private */
    public static final class AllSameBlocks {
        private int[] indexes = new int[32];
        private int length;
        private int mostRecent = -1;
        private int[] refCounts = new int[32];
        private int[] values = new int[32];

        AllSameBlocks() {
        }

        /* access modifiers changed from: package-private */
        public int findOrAdd(int i, int i2, int i3) {
            int i4 = this.mostRecent;
            if (i4 < 0 || this.values[i4] != i3) {
                int i5 = 0;
                while (true) {
                    int i6 = this.length;
                    if (i5 < i6) {
                        if (this.values[i5] == i3) {
                            this.mostRecent = i5;
                            int[] iArr = this.refCounts;
                            iArr[i5] = iArr[i5] + i2;
                            return this.indexes[i5];
                        }
                        i5++;
                    } else if (i6 == 32) {
                        return -2;
                    } else {
                        this.mostRecent = i6;
                        this.indexes[i6] = i;
                        this.values[i6] = i3;
                        int[] iArr2 = this.refCounts;
                        this.length = i6 + 1;
                        iArr2[i6] = i2;
                        return -1;
                    }
                }
            } else {
                int[] iArr3 = this.refCounts;
                iArr3[i4] = iArr3[i4] + i2;
                return this.indexes[i4];
            }
        }

        /* access modifiers changed from: package-private */
        public void add(int i, int i2, int i3) {
            int i4 = -1;
            int i5 = 69632;
            for (int i6 = 0; i6 < this.length; i6++) {
                int[] iArr = this.refCounts;
                if (iArr[i6] < i5) {
                    i5 = iArr[i6];
                    i4 = i6;
                }
            }
            this.mostRecent = i4;
            this.indexes[i4] = i;
            this.values[i4] = i3;
            this.refCounts[i4] = i2;
        }

        /* access modifiers changed from: package-private */
        public int findMostUsed() {
            int i = -1;
            if (this.length == 0) {
                return -1;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.length; i3++) {
                int[] iArr = this.refCounts;
                if (iArr[i3] > i2) {
                    i2 = iArr[i3];
                    i = i3;
                }
            }
            return this.indexes[i];
        }
    }

    /* access modifiers changed from: private */
    public static final class MixedBlocks {
        private int blockLength;
        private int length;
        private int mask;
        private int shift;
        private int[] table;

        private MixedBlocks() {
        }

        /* synthetic */ MixedBlocks(AnonymousClass1 r1) {
            this();
        }

        /* access modifiers changed from: package-private */
        public void init(int i, int i2) {
            int i3;
            int i4 = (i - i2) + 1;
            if (i4 <= 4095) {
                i3 = 6007;
                this.shift = 12;
                this.mask = 4095;
            } else if (i4 <= 32767) {
                i3 = 50021;
                this.shift = 15;
                this.mask = 32767;
            } else if (i4 <= 131071) {
                i3 = 200003;
                this.shift = 17;
                this.mask = 131071;
            } else {
                i3 = 1500007;
                this.shift = 21;
                this.mask = 2097151;
            }
            int[] iArr = this.table;
            if (iArr == null || i3 > iArr.length) {
                this.table = new int[i3];
            } else {
                Arrays.fill(iArr, 0, i3, 0);
            }
            this.length = i3;
            this.blockLength = i2;
        }

        /* access modifiers changed from: package-private */
        public void extend(int[] iArr, int i, int i2, int i3) {
            int i4 = i2 - this.blockLength;
            if (i4 >= i) {
                i = i4 + 1;
            }
            int i5 = i3 - this.blockLength;
            while (i <= i5) {
                addEntry(iArr, null, i, makeHashCode(iArr, i), i);
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public void extend(char[] cArr, int i, int i2, int i3) {
            int i4 = i2 - this.blockLength;
            if (i4 >= i) {
                i = i4 + 1;
            }
            int i5 = i3 - this.blockLength;
            while (i <= i5) {
                addEntry(null, cArr, i, makeHashCode(cArr, i), i);
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public int findBlock(int[] iArr, int[] iArr2, int i) {
            int findEntry = findEntry(iArr, null, iArr2, null, i, makeHashCode(iArr2, i));
            if (findEntry < 0) {
                return -1;
            }
            return (this.mask & this.table[findEntry]) - 1;
        }

        /* access modifiers changed from: package-private */
        public int findBlock(char[] cArr, int[] iArr, int i) {
            int findEntry = findEntry(null, cArr, iArr, null, i, makeHashCode(iArr, i));
            if (findEntry < 0) {
                return -1;
            }
            return (this.mask & this.table[findEntry]) - 1;
        }

        /* access modifiers changed from: package-private */
        public int findBlock(char[] cArr, char[] cArr2, int i) {
            int findEntry = findEntry(null, cArr, null, cArr2, i, makeHashCode(cArr2, i));
            if (findEntry < 0) {
                return -1;
            }
            return (this.mask & this.table[findEntry]) - 1;
        }

        /* access modifiers changed from: package-private */
        public int findAllSameBlock(int[] iArr, int i) {
            int findEntry = findEntry(iArr, i, makeHashCode(i));
            if (findEntry < 0) {
                return -1;
            }
            return (this.mask & this.table[findEntry]) - 1;
        }

        private int makeHashCode(int[] iArr, int i) {
            int i2 = this.blockLength + i;
            int i3 = i + 1;
            int i4 = iArr[i];
            while (true) {
                int i5 = i3 + 1;
                i4 = (i4 * 37) + iArr[i3];
                if (i5 >= i2) {
                    return i4;
                }
                i3 = i5;
            }
        }

        private int makeHashCode(char[] cArr, int i) {
            int i2 = this.blockLength + i;
            int i3 = i + 1;
            char c = cArr[i];
            while (true) {
                int i4 = i3 + 1;
                int i5 = ((c == 1 ? 1 : 0) * 37) + cArr[i3];
                if (i4 >= i2) {
                    return i5;
                }
                i3 = i4;
                c = i5;
            }
        }

        private int makeHashCode(int i) {
            int i2 = i;
            for (int i3 = 1; i3 < this.blockLength; i3++) {
                i2 = (i2 * 37) + i;
            }
            return i2;
        }

        private void addEntry(int[] iArr, char[] cArr, int i, int i2, int i3) {
            int findEntry = findEntry(iArr, cArr, iArr, cArr, i, i2);
            if (findEntry < 0) {
                this.table[~findEntry] = (i2 << this.shift) | (i3 + 1);
            }
        }

        private int findEntry(int[] iArr, char[] cArr, int[] iArr2, char[] cArr2, int i, int i2) {
            int i3 = i2 << this.shift;
            int modulo = modulo(i2, this.length - 1) + 1;
            int i4 = modulo;
            while (true) {
                int i5 = this.table[i4];
                if (i5 == 0) {
                    return ~i4;
                }
                int i6 = this.mask;
                if (((~i6) & i5) == i3) {
                    int i7 = (i5 & i6) - 1;
                    if (iArr != null) {
                        if (MutableCodePointTrie.equalBlocks(iArr, i7, iArr2, i, this.blockLength)) {
                            break;
                        }
                    } else if (iArr2 != null) {
                        if (MutableCodePointTrie.equalBlocks(cArr, i7, iArr2, i, this.blockLength)) {
                            break;
                        }
                    } else if (MutableCodePointTrie.equalBlocks(cArr, i7, cArr2, i, this.blockLength)) {
                        break;
                    }
                }
                i4 = nextIndex(modulo, i4);
            }
            return i4;
        }

        private int findEntry(int[] iArr, int i, int i2) {
            int i3 = i2 << this.shift;
            int modulo = modulo(i2, this.length - 1) + 1;
            int i4 = modulo;
            while (true) {
                int i5 = this.table[i4];
                if (i5 == 0) {
                    return ~i4;
                }
                int i6 = this.mask;
                if (((~i6) & i5) == i3 && MutableCodePointTrie.allValuesSameAs(iArr, (i5 & i6) - 1, this.blockLength, i)) {
                    return i4;
                }
                i4 = nextIndex(modulo, i4);
            }
        }

        private int nextIndex(int i, int i2) {
            return (i2 + i) % this.length;
        }

        private int modulo(int i, int i2) {
            int i3 = i % i2;
            return i3 < 0 ? i3 + i2 : i3;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int compactWholeDataBlocks(int r14, android.icu.util.MutableCodePointTrie.AllSameBlocks r15) {
        /*
        // Method dump skipped, instructions count: 139
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.MutableCodePointTrie.compactWholeDataBlocks(int, android.icu.util.MutableCodePointTrie$AllSameBlocks):int");
    }

    private int compactData(int i, int[] iArr, int i2, MixedBlocks mixedBlocks) {
        int i3;
        int i4;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            i3 = 4;
            if (i6 >= 128) {
                break;
            }
            this.index[i7] = i6;
            i6 += 64;
            i7 += 4;
        }
        int i8 = 64;
        mixedBlocks.init(iArr.length, 64);
        mixedBlocks.extend(iArr, 0, 0, i6);
        int i9 = this.highStart >> 4;
        int i10 = 8;
        int i11 = 0;
        while (i10 < i9) {
            if (i10 == i) {
                i8 = 16;
                mixedBlocks.init(iArr.length, 16);
                mixedBlocks.extend(iArr, i5, i5, i6);
                i11 = i6;
                i3 = 1;
            }
            byte[] bArr = this.flags;
            if (bArr[i10] == 0) {
                int i12 = this.index[i10];
                int findAllSameBlock = mixedBlocks.findAllSameBlock(iArr, i12);
                while (findAllSameBlock >= 0 && i10 == i2 && i10 >= i && findAllSameBlock < i11 && isStartOfSomeFastBlock(findAllSameBlock, this.index, i)) {
                    findAllSameBlock = findAllSameBlock(iArr, findAllSameBlock + 1, i6, i12, i8);
                }
                if (findAllSameBlock >= 0) {
                    this.index[i10] = findAllSameBlock;
                } else {
                    int allSameOverlap = getAllSameOverlap(iArr, i6, i12, i8);
                    this.index[i10] = i6 - allSameOverlap;
                    i4 = i6;
                    while (allSameOverlap < i8) {
                        iArr[i4] = i12;
                        allSameOverlap++;
                        i4++;
                    }
                    mixedBlocks.extend(iArr, i5, i6, i4);
                    i6 = i4;
                    i10 += i3;
                }
            } else if (bArr[i10] == 1) {
                int i13 = this.index[i10];
                int findBlock = mixedBlocks.findBlock(iArr, this.data, i13);
                if (findBlock >= 0) {
                    this.index[i10] = findBlock;
                } else {
                    int overlap = getOverlap(iArr, i6, this.data, i13, i8);
                    this.index[i10] = i6 - overlap;
                    i4 = i6;
                    while (overlap < i8) {
                        iArr[i4] = this.data[overlap + i13];
                        i4++;
                        overlap++;
                        i5 = 0;
                    }
                    mixedBlocks.extend(iArr, i5, i6, i4);
                    i6 = i4;
                    i10 += i3;
                }
            } else {
                int[] iArr2 = this.index;
                iArr2[i10] = iArr2[iArr2[i10]];
                i10 += i3;
            }
            i4 = i6;
            i6 = i4;
            i10 += i3;
        }
        return i6;
    }

    private int compactIndex(int i, MixedBlocks mixedBlocks) {
        boolean z;
        int i2;
        int i3;
        int i4;
        int i5;
        char[] cArr;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13 = i >> 2;
        if ((this.highStart >> 6) <= i13) {
            this.index3NullOffset = 32767;
            return i13;
        }
        char[] cArr2 = new char[i13];
        int i14 = 0;
        int i15 = -1;
        int i16 = 0;
        int i17 = 0;
        while (true) {
            z = true;
            if (i16 >= i) {
                break;
            }
            int i18 = this.index[i16];
            cArr2[i17] = (char) i18;
            if (i18 != this.dataNullOffset) {
                i15 = -1;
            } else if (i15 < 0) {
                i15 = i17;
            } else if (this.index3NullOffset < 0 && (i17 - i15) + 1 == 32) {
                this.index3NullOffset = i15;
            }
            int i19 = i16 + 4;
            while (true) {
                i16++;
                if (i16 >= i19) {
                    break;
                }
                i18 += 16;
                this.index[i16] = i18;
            }
            i17++;
        }
        mixedBlocks.init(i13, 32);
        mixedBlocks.extend(cArr2, 0, 0, i13);
        int i20 = this.index3NullOffset;
        int i21 = 4096;
        if (i < 4096) {
            i21 = 0;
        }
        int i22 = this.highStart >> 4;
        int i23 = i20;
        int i24 = 0;
        boolean z2 = false;
        int i25 = i21;
        while (true) {
            i2 = 65535;
            if (i25 >= i22) {
                break;
            }
            int i26 = i25 + 32;
            int i27 = i25;
            int i28 = 0;
            boolean z3 = z;
            while (true) {
                int i29 = this.index[i27];
                i11 = i28 | i29;
                if (i29 != this.dataNullOffset) {
                    z3 = false;
                }
                i12 = i27 + 1;
                if (i12 >= i26) {
                    break;
                }
                i27 = i12;
                i28 = i11;
            }
            if (z3) {
                this.flags[i25] = 0;
                if (i23 < 0) {
                    if (i11 <= 65535) {
                        i24 += 32;
                    } else {
                        i24 += 36;
                        z2 = true;
                    }
                    i23 = 0;
                }
            } else if (i11 <= 65535) {
                int findBlock = mixedBlocks.findBlock(cArr2, this.index, i25);
                if (findBlock >= 0) {
                    this.flags[i25] = 1;
                    this.index[i25] = findBlock;
                } else {
                    this.flags[i25] = 2;
                    i24 += 32;
                }
            } else {
                this.flags[i25] = 3;
                i24 += 36;
                z2 = true;
            }
            i25 = i12;
            z = true;
        }
        int i30 = (i22 - i21) >> 5;
        int i31 = ((i30 + 31) >> 5) + i13;
        int i32 = i24 + i31 + i30 + 1;
        this.index16 = Arrays.copyOf(cArr2, i32);
        mixedBlocks.init(i32, 32);
        MixedBlocks mixedBlocks2 = null;
        if (z2) {
            MixedBlocks mixedBlocks3 = new MixedBlocks(null);
            mixedBlocks3.init(i32, 36);
            mixedBlocks2 = mixedBlocks3;
        }
        char[] cArr3 = new char[i30];
        int i33 = i31;
        int i34 = this.index3NullOffset;
        int i35 = 0;
        while (i21 < i22) {
            byte b = this.flags[i21];
            if (b == 0 && i34 < 0) {
                b = this.dataNullOffset <= i2 ? (byte) 2 : 3;
                i34 = i14;
            }
            if (b == 0) {
                i5 = this.index3NullOffset;
            } else if (b == 1) {
                i5 = this.index[i21];
            } else if (b == 2) {
                int findBlock2 = mixedBlocks.findBlock(this.index16, this.index, i21);
                if (findBlock2 >= 0) {
                    i4 = i22;
                    i5 = findBlock2;
                } else {
                    if (i33 == i31) {
                        i10 = 0;
                        i9 = 32;
                    } else {
                        i9 = 32;
                        i10 = getOverlap(this.index16, i33, this.index, i21, 32);
                    }
                    i5 = i33 - i10;
                    int i36 = i33;
                    while (i10 < i9) {
                        this.index16[i36] = (char) this.index[i10 + i21];
                        i36++;
                        i22 = i22;
                        i10++;
                        i9 = 32;
                    }
                    i4 = i22;
                    mixedBlocks.extend(this.index16, i31, i33, i36);
                    if (z2) {
                        mixedBlocks2.extend(this.index16, i31, i33, i36);
                    }
                    i33 = i36;
                }
                if (this.index3NullOffset < 0 && i34 >= 0) {
                    this.index3NullOffset = i5;
                }
                cArr3[i35] = (char) i5;
                i21 += 32;
                i35++;
                i22 = i4;
                i14 = 0;
                i2 = 65535;
            } else {
                i4 = i22;
                int i37 = i21 + 32;
                int i38 = i21;
                int i39 = i33;
                while (true) {
                    int i40 = i39 + 1;
                    int[] iArr = this.index;
                    int i41 = i38 + 1;
                    int i42 = iArr[i38];
                    cArr = this.index16;
                    int i43 = i40 + 1;
                    cArr[i40] = (char) i42;
                    int i44 = i41 + 1;
                    int i45 = iArr[i41];
                    int i46 = ((i42 & 196608) >> 2) | ((i45 & 196608) >> 4);
                    int i47 = i43 + 1;
                    cArr[i43] = (char) i45;
                    int i48 = i44 + 1;
                    int i49 = iArr[i44];
                    int i50 = i46 | ((i49 & 196608) >> 6);
                    int i51 = i47 + 1;
                    cArr[i47] = (char) i49;
                    int i52 = i48 + 1;
                    int i53 = iArr[i48];
                    int i54 = i50 | ((i53 & 196608) >> 8);
                    int i55 = i51 + 1;
                    cArr[i51] = (char) i53;
                    int i56 = i52 + 1;
                    int i57 = iArr[i52];
                    int i58 = i54 | ((i57 & 196608) >> 10);
                    int i59 = i55 + 1;
                    cArr[i55] = (char) i57;
                    int i60 = i56 + 1;
                    int i61 = iArr[i56];
                    int i62 = i58 | ((i61 & 196608) >> 12);
                    int i63 = i59 + 1;
                    cArr[i59] = (char) i61;
                    int i64 = i60 + 1;
                    int i65 = iArr[i60];
                    int i66 = i62 | ((i65 & 196608) >> 14);
                    int i67 = i63 + 1;
                    cArr[i63] = (char) i65;
                    int i68 = i64 + 1;
                    int i69 = iArr[i64];
                    int i70 = i66 | ((i69 & 196608) >> 16);
                    int i71 = i67 + 1;
                    cArr[i67] = (char) i69;
                    cArr[i71 - 9] = (char) i70;
                    if (i68 >= i37) {
                        break;
                    }
                    i38 = i68;
                    i39 = i71;
                }
                int findBlock3 = mixedBlocks2.findBlock(cArr, cArr, i33);
                if (findBlock3 >= 0) {
                    i5 = findBlock3 | 32768;
                } else {
                    if (i33 == i31) {
                        i7 = 0;
                        i6 = 36;
                    } else {
                        char[] cArr4 = this.index16;
                        i6 = 36;
                        i7 = getOverlap(cArr4, i33, cArr4, i33, 36);
                    }
                    i5 = (i33 - i7) | 32768;
                    if (i7 > 0) {
                        i8 = i33;
                        while (i7 < i6) {
                            char[] cArr5 = this.index16;
                            cArr5[i8] = cArr5[i7 + i33];
                            i8++;
                            i7++;
                        }
                    } else {
                        i8 = i33 + 36;
                    }
                    mixedBlocks.extend(this.index16, i31, i33, i8);
                    if (z2) {
                        mixedBlocks2.extend(this.index16, i31, i33, i8);
                    }
                    i33 = i8;
                }
                this.index3NullOffset = i5;
                cArr3[i35] = (char) i5;
                i21 += 32;
                i35++;
                i22 = i4;
                i14 = 0;
                i2 = 65535;
            }
            i4 = i22;
            this.index3NullOffset = i5;
            cArr3[i35] = (char) i5;
            i21 += 32;
            i35++;
            i22 = i4;
            i14 = 0;
            i2 = 65535;
        }
        if (this.index3NullOffset < 0) {
            this.index3NullOffset = 32767;
        }
        if (i33 < 32799) {
            int i72 = i13;
            int i73 = 0;
            int i74 = 32;
            while (i73 < i35) {
                int i75 = i35 - i73;
                if (i75 >= i74) {
                    i75 = i74;
                    i3 = mixedBlocks.findBlock(this.index16, cArr3, i73);
                } else {
                    i3 = findSameBlock(this.index16, i31, i33, cArr3, i73, i75);
                }
                if (i3 < 0) {
                    int overlap = i33 == i31 ? 0 : getOverlap(this.index16, i33, cArr3, i73, i75);
                    int i76 = i33 - overlap;
                    int i77 = i33;
                    while (overlap < i75) {
                        this.index16[i77] = cArr3[overlap + i73];
                        i77++;
                        overlap++;
                    }
                    mixedBlocks.extend(this.index16, i31, i33, i77);
                    i3 = i76;
                    i33 = i77;
                }
                this.index16[i72] = (char) i3;
                i73 += i75;
                i74 = i75;
                i72++;
            }
            return i33;
        }
        throw new IndexOutOfBoundsException("The trie data exceeds limitations of the data structure.");
    }

    private int compactTrie(int i) {
        this.highValue = get(1114111);
        int findHighStart = (findHighStart() + 511) & -512;
        if (findHighStart == 1114112) {
            this.highValue = this.initialValue;
        }
        int i2 = i << 4;
        if (findHighStart < i2) {
            for (int i3 = findHighStart >> 4; i3 < i; i3++) {
                this.flags[i3] = 0;
                this.index[i3] = this.highValue;
            }
            this.highStart = i2;
        } else {
            this.highStart = findHighStart;
        }
        int[] iArr = new int[128];
        for (int i4 = 0; i4 < 128; i4++) {
            iArr[i4] = get(i4);
        }
        AllSameBlocks allSameBlocks = new AllSameBlocks();
        int[] copyOf = Arrays.copyOf(iArr, compactWholeDataBlocks(i, allSameBlocks));
        int findMostUsed = allSameBlocks.findMostUsed();
        MixedBlocks mixedBlocks = new MixedBlocks(null);
        int compactData = compactData(i, copyOf, findMostUsed, mixedBlocks);
        this.data = copyOf;
        this.dataLength = compactData;
        if (this.dataLength <= 262159) {
            if (findMostUsed >= 0) {
                this.dataNullOffset = this.index[findMostUsed];
                this.initialValue = this.data[this.dataNullOffset];
            } else {
                this.dataNullOffset = 1048575;
            }
            int compactIndex = compactIndex(i, mixedBlocks);
            this.highStart = findHighStart;
            return compactIndex;
        }
        throw new IndexOutOfBoundsException("The trie data exceeds limitations of the data structure.");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.util.MutableCodePointTrie$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$util$CodePointTrie$ValueWidth = new int[CodePointTrie.ValueWidth.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                android.icu.util.CodePointTrie$ValueWidth[] r0 = android.icu.util.CodePointTrie.ValueWidth.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.icu.util.MutableCodePointTrie.AnonymousClass1.$SwitchMap$android$icu$util$CodePointTrie$ValueWidth = r0
                int[] r0 = android.icu.util.MutableCodePointTrie.AnonymousClass1.$SwitchMap$android$icu$util$CodePointTrie$ValueWidth     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.icu.util.CodePointTrie$ValueWidth r1 = android.icu.util.CodePointTrie.ValueWidth.BITS_32     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = android.icu.util.MutableCodePointTrie.AnonymousClass1.$SwitchMap$android$icu$util$CodePointTrie$ValueWidth     // Catch:{ NoSuchFieldError -> 0x001f }
                android.icu.util.CodePointTrie$ValueWidth r1 = android.icu.util.CodePointTrie.ValueWidth.BITS_16     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = android.icu.util.MutableCodePointTrie.AnonymousClass1.$SwitchMap$android$icu$util$CodePointTrie$ValueWidth     // Catch:{ NoSuchFieldError -> 0x002a }
                android.icu.util.CodePointTrie$ValueWidth r1 = android.icu.util.CodePointTrie.ValueWidth.BITS_8     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.util.MutableCodePointTrie.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d1, code lost:
        if (r7[r6 - 2] == r13.highValue) goto L_0x0113;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.icu.util.CodePointTrie build(android.icu.util.CodePointTrie.Type r14, android.icu.util.CodePointTrie.ValueWidth r15) {
        /*
        // Method dump skipped, instructions count: 466
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.MutableCodePointTrie.build(android.icu.util.CodePointTrie$Type, android.icu.util.CodePointTrie$ValueWidth):android.icu.util.CodePointTrie");
    }
}
