package android.icu.util;

import android.icu.text.DictionaryData;
import android.icu.util.CodePointMap;
import android.icu.util.CodePointTrie;
import dalvik.bytecode.Opcodes;
import java.util.Arrays;

public final class MutableCodePointTrie extends CodePointMap implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final byte ALL_SAME = 0;
    private static final int ASCII_I_LIMIT = 8;
    private static final int ASCII_LIMIT = 128;
    private static final int BMP_I_LIMIT = 4096;
    private static final int BMP_LIMIT = 65536;
    private static final byte I3_16 = 2;
    private static final byte I3_18 = 3;
    private static final byte I3_BMP = 1;
    private static final byte I3_NULL = 0;
    private static final int INDEX_3_18BIT_BLOCK_LENGTH = 36;
    private static final int INITIAL_DATA_LENGTH = 16384;
    private static final int I_LIMIT = 69632;
    private static final int MAX_DATA_LENGTH = 1114112;
    private static final int MAX_UNICODE = 1114111;
    private static final int MEDIUM_DATA_LENGTH = 131072;
    private static final byte MIXED = 1;
    private static final byte SAME_AS = 2;
    private static final int SMALL_DATA_BLOCKS_PER_BMP_BLOCK = 4;
    private static final int UNICODE_LIMIT = 1114112;
    private int[] data = new int[16384];
    private int dataLength;
    private int dataNullOffset = -1;
    private int errorValue;
    private byte[] flags = new byte[I_LIMIT];
    private int highStart;
    private int highValue;
    private int[] index = new int[4096];
    private char[] index16;
    private int index3NullOffset = -1;
    private int initialValue;
    private int origInitialValue;

    public MutableCodePointTrie(int initialValue2, int errorValue2) {
        this.origInitialValue = initialValue2;
        this.initialValue = initialValue2;
        this.errorValue = errorValue2;
        this.highValue = initialValue2;
    }

    public MutableCodePointTrie clone() {
        try {
            MutableCodePointTrie builder = (MutableCodePointTrie) super.clone();
            builder.index = new int[(this.highStart <= 65536 ? 4096 : I_LIMIT)];
            builder.flags = new byte[I_LIMIT];
            int iLimit = this.highStart >> 4;
            for (int i = 0; i < iLimit; i++) {
                builder.index[i] = this.index[i];
                builder.flags[i] = this.flags[i];
            }
            builder.index3NullOffset = this.index3NullOffset;
            builder.data = (int[]) this.data.clone();
            builder.dataLength = this.dataLength;
            builder.dataNullOffset = this.dataNullOffset;
            builder.origInitialValue = this.origInitialValue;
            builder.initialValue = this.initialValue;
            builder.errorValue = this.errorValue;
            builder.highStart = this.highStart;
            builder.highValue = this.highValue;
            return builder;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public static MutableCodePointTrie fromCodePointMap(CodePointMap map) {
        int errorValue2 = map.get(-1);
        int initialValue2 = map.get(1114111);
        MutableCodePointTrie mutableTrie = new MutableCodePointTrie(initialValue2, errorValue2);
        CodePointMap.Range range = new CodePointMap.Range();
        int start = 0;
        while (map.getRange(start, null, range)) {
            int end = range.getEnd();
            int value = range.getValue();
            if (value != initialValue2) {
                if (start == end) {
                    mutableTrie.set(start, value);
                } else {
                    mutableTrie.setRange(start, end, value);
                }
            }
            start = end + 1;
        }
        return mutableTrie;
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

    @Override // android.icu.util.CodePointMap
    public int get(int c) {
        if (c < 0 || 1114111 < c) {
            return this.errorValue;
        }
        if (c >= this.highStart) {
            return this.highValue;
        }
        int i = c >> 4;
        if (this.flags[i] == 0) {
            return this.index[i];
        }
        return this.data[this.index[i] + (c & 15)];
    }

    private static final int maybeFilterValue(int value, int initialValue2, int nullValue, CodePointMap.ValueFilter filter) {
        if (value == initialValue2) {
            return nullValue;
        }
        if (filter != null) {
            return filter.apply(value);
        }
        return value;
    }

    /* JADX INFO: Multiple debug info for r1v1 int: [D('nullValue' int), D('value' int)] */
    @Override // android.icu.util.CodePointMap
    public boolean getRange(int start, CodePointMap.ValueFilter filter, CodePointMap.Range range) {
        if (start < 0 || 1114111 < start) {
            return false;
        }
        if (start >= this.highStart) {
            int value = this.highValue;
            if (filter != null) {
                value = filter.apply(value);
            }
            range.set(start, 1114111, value);
            return true;
        }
        int nullValue = this.initialValue;
        if (filter != null) {
            nullValue = filter.apply(nullValue);
        }
        int c = start;
        int trieValue = 0;
        int value2 = 0;
        boolean haveValue = false;
        int i = c >> 4;
        loop0:
        do {
            if (this.flags[i] == 0) {
                int trieValue2 = this.index[i];
                if (!haveValue) {
                    trieValue = trieValue2;
                    value2 = maybeFilterValue(trieValue2, this.initialValue, nullValue, filter);
                    haveValue = true;
                } else if (trieValue2 != trieValue) {
                    if (filter == null || maybeFilterValue(trieValue2, this.initialValue, nullValue, filter) != value2) {
                        range.set(start, c - 1, value2);
                        return true;
                    }
                    trieValue = trieValue2;
                }
                c = (c + 16) & -16;
            } else {
                int di = this.index[i] + (c & 15);
                int trieValue22 = this.data[di];
                if (!haveValue) {
                    trieValue = trieValue22;
                    value2 = maybeFilterValue(trieValue22, this.initialValue, nullValue, filter);
                    haveValue = true;
                } else if (trieValue22 != trieValue) {
                    if (filter == null || maybeFilterValue(trieValue22, this.initialValue, nullValue, filter) != value2) {
                        range.set(start, c - 1, value2);
                        return true;
                    }
                    trieValue = trieValue22;
                }
                while (true) {
                    c++;
                    if ((c & 15) == 0) {
                        break;
                    }
                    di++;
                    int trieValue23 = this.data[di];
                    if (trieValue23 != trieValue) {
                        if (filter == null || maybeFilterValue(trieValue23, this.initialValue, nullValue, filter) != value2) {
                            range.set(start, c - 1, value2);
                        } else {
                            trieValue = trieValue23;
                        }
                    }
                }
                range.set(start, c - 1, value2);
                return true;
            }
            i++;
        } while (c < this.highStart);
        if (maybeFilterValue(this.highValue, this.initialValue, nullValue, filter) != value2) {
            range.set(start, c - 1, value2);
        } else {
            range.set(start, 1114111, value2);
        }
        return true;
    }

    private void writeBlock(int block, int value) {
        Arrays.fill(this.data, block, block + 16, value);
    }

    public void set(int c, int value) {
        if (c < 0 || 1114111 < c) {
            throw new IllegalArgumentException("invalid code point");
        }
        ensureHighStart(c);
        this.data[(c & 15) + getDataBlock(c >> 4)] = value;
    }

    private void fillBlock(int block, int start, int limit, int value) {
        Arrays.fill(this.data, block + start, block + limit, value);
    }

    /* JADX INFO: Multiple debug info for r1v2 int: [D('block' int), D('rest' int)] */
    public void setRange(int start, int end, int value) {
        if (start < 0 || 1114111 < start || end < 0 || 1114111 < end || start > end) {
            throw new IllegalArgumentException("invalid code point range");
        }
        ensureHighStart(end);
        int limit = end + 1;
        if ((start & 15) != 0) {
            int block = getDataBlock(start >> 4);
            int nextStart = (start + 15) & -16;
            if (nextStart <= limit) {
                fillBlock(block, start & 15, 16, value);
                start = nextStart;
            } else {
                fillBlock(block, start & 15, limit & 15, value);
                return;
            }
        }
        int rest = limit & 15;
        int limit2 = limit & -16;
        while (start < limit2) {
            int i = start >> 4;
            if (this.flags[i] == 0) {
                this.index[i] = value;
            } else {
                fillBlock(this.index[i], 0, 16, value);
            }
            start += 16;
        }
        if (rest > 0) {
            fillBlock(getDataBlock(start >> 4), 0, rest, value);
        }
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

    /* JADX INFO: Multiple debug info for r6v1 int: [D('i' int), D('c' int)] */
    private void ensureHighStart(int c) {
        int i = this.highStart;
        if (c >= i) {
            int c2 = (c + 512) & -512;
            int i2 = i >> 4;
            int iLimit = c2 >> 4;
            if (iLimit > this.index.length) {
                int[] newIndex = new int[I_LIMIT];
                for (int j = 0; j < i2; j++) {
                    newIndex[j] = this.index[j];
                }
                this.index = newIndex;
            }
            do {
                this.flags[i2] = 0;
                this.index[i2] = this.initialValue;
                i2++;
            } while (i2 < iLimit);
            this.highStart = c2;
        }
    }

    private int allocDataBlock(int blockLength) {
        int capacity;
        int newBlock = this.dataLength;
        int newTop = newBlock + blockLength;
        int[] iArr = this.data;
        if (newTop > iArr.length) {
            if (iArr.length < 131072) {
                capacity = 131072;
            } else if (iArr.length < 1114112) {
                capacity = 1114112;
            } else {
                throw new AssertionError();
            }
            int[] newData = new int[capacity];
            for (int j = 0; j < this.dataLength; j++) {
                newData[j] = this.data[j];
            }
            this.data = newData;
        }
        this.dataLength = newTop;
        return newBlock;
    }

    private int getDataBlock(int i) {
        if (this.flags[i] == 1) {
            return this.index[i];
        }
        if (i < 4096) {
            int newBlock = allocDataBlock(64);
            int iStart = i & -4;
            int iLimit = iStart + 4;
            while (true) {
                writeBlock(newBlock, this.index[iStart]);
                this.flags[iStart] = 1;
                int[] iArr = this.index;
                int iStart2 = iStart + 1;
                iArr[iStart] = newBlock;
                newBlock += 16;
                if (iStart2 >= iLimit) {
                    return iArr[i];
                }
                iStart = iStart2;
            }
        } else {
            int newBlock2 = allocDataBlock(16);
            if (newBlock2 < 0) {
                return newBlock2;
            }
            writeBlock(newBlock2, this.index[i]);
            this.flags[i] = 1;
            this.index[i] = newBlock2;
            return newBlock2;
        }
    }

    private void maskValues(int mask) {
        this.initialValue &= mask;
        this.errorValue &= mask;
        this.highValue &= mask;
        int iLimit = this.highStart >> 4;
        for (int i = 0; i < iLimit; i++) {
            if (this.flags[i] == 0) {
                int[] iArr = this.index;
                iArr[i] = iArr[i] & mask;
            }
        }
        for (int i2 = 0; i2 < this.dataLength; i2++) {
            int[] iArr2 = this.data;
            iArr2[i2] = iArr2[i2] & mask;
        }
    }

    /* access modifiers changed from: private */
    public static boolean equalBlocks(int[] s, int si, int[] t, int ti, int length) {
        while (length > 0 && s[si] == t[ti]) {
            si++;
            ti++;
            length--;
        }
        return length == 0;
    }

    /* access modifiers changed from: private */
    public static boolean equalBlocks(char[] s, int si, int[] t, int ti, int length) {
        while (length > 0 && s[si] == t[ti]) {
            si++;
            ti++;
            length--;
        }
        return length == 0;
    }

    /* access modifiers changed from: private */
    public static boolean equalBlocks(char[] s, int si, char[] t, int ti, int length) {
        while (length > 0 && s[si] == t[ti]) {
            si++;
            ti++;
            length--;
        }
        return length == 0;
    }

    /* access modifiers changed from: private */
    public static boolean allValuesSameAs(int[] p, int pi, int length, int value) {
        int pLimit = pi + length;
        while (pi < pLimit && p[pi] == value) {
            pi++;
        }
        return pi == pLimit;
    }

    private static int findSameBlock(char[] p, int pStart, int length, char[] q, int qStart, int blockLength) {
        int length2 = length - blockLength;
        while (pStart <= length2) {
            if (equalBlocks(p, pStart, q, qStart, blockLength)) {
                return pStart;
            }
            pStart++;
        }
        return -1;
    }

    private static int findAllSameBlock(int[] p, int start, int limit, int value, int blockLength) {
        int limit2 = limit - blockLength;
        int block = start;
        while (block <= limit2) {
            if (p[block] == value) {
                for (int i = 1; i != blockLength; i++) {
                    if (p[block + i] != value) {
                        block += i;
                    }
                }
                return block;
            }
            block++;
        }
        return -1;
    }

    private static int getOverlap(int[] p, int length, int[] q, int qStart, int blockLength) {
        int overlap = blockLength - 1;
        while (overlap > 0 && !equalBlocks(p, length - overlap, q, qStart, overlap)) {
            overlap--;
        }
        return overlap;
    }

    private static int getOverlap(char[] p, int length, int[] q, int qStart, int blockLength) {
        int overlap = blockLength - 1;
        while (overlap > 0 && !equalBlocks(p, length - overlap, q, qStart, overlap)) {
            overlap--;
        }
        return overlap;
    }

    private static int getOverlap(char[] p, int length, char[] q, int qStart, int blockLength) {
        int overlap = blockLength - 1;
        while (overlap > 0 && !equalBlocks(p, length - overlap, q, qStart, overlap)) {
            overlap--;
        }
        return overlap;
    }

    private static int getAllSameOverlap(int[] p, int length, int value, int blockLength) {
        int min = length - (blockLength - 1);
        int i = length;
        while (min < i && p[i - 1] == value) {
            i--;
        }
        return length - i;
    }

    private static boolean isStartOfSomeFastBlock(int dataOffset, int[] index2, int fastILimit) {
        for (int i = 0; i < fastILimit; i += 4) {
            if (index2[i] == dataOffset) {
                return true;
            }
        }
        return false;
    }

    private int findHighStart() {
        int i = this.highStart >> 4;
        while (true) {
            boolean match = false;
            if (i <= 0) {
                return 0;
            }
            int i2 = i - 1;
            if (this.flags[i2] != 0) {
                int p = this.index[i2];
                int j = 0;
                while (true) {
                    if (j == 16) {
                        match = true;
                        break;
                    } else if (this.data[p + j] != this.highValue) {
                        match = false;
                        break;
                    } else {
                        j++;
                    }
                }
            } else if (this.index[i2] == this.highValue) {
                match = true;
            }
            if (!match) {
                return (i2 + 1) << 4;
            }
            i = i2;
        }
    }

    /* access modifiers changed from: private */
    public static final class AllSameBlocks {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final int CAPACITY = 32;
        static final int NEW_UNIQUE = -1;
        static final int OVERFLOW = -2;
        private int[] indexes = new int[32];
        private int length;
        private int mostRecent = -1;
        private int[] refCounts = new int[32];
        private int[] values = new int[32];

        AllSameBlocks() {
        }

        /* access modifiers changed from: package-private */
        public int findOrAdd(int index, int count, int value) {
            int i = this.mostRecent;
            if (i < 0 || this.values[i] != value) {
                int i2 = 0;
                while (true) {
                    int i3 = this.length;
                    if (i2 < i3) {
                        if (this.values[i2] == value) {
                            this.mostRecent = i2;
                            int[] iArr = this.refCounts;
                            iArr[i2] = iArr[i2] + count;
                            return this.indexes[i2];
                        }
                        i2++;
                    } else if (i3 == 32) {
                        return -2;
                    } else {
                        this.mostRecent = i3;
                        this.indexes[i3] = index;
                        this.values[i3] = value;
                        int[] iArr2 = this.refCounts;
                        this.length = i3 + 1;
                        iArr2[i3] = count;
                        return -1;
                    }
                }
            } else {
                int[] iArr3 = this.refCounts;
                iArr3[i] = iArr3[i] + count;
                return this.indexes[i];
            }
        }

        /* access modifiers changed from: package-private */
        public void add(int index, int count, int value) {
            int least = -1;
            int leastCount = MutableCodePointTrie.I_LIMIT;
            for (int i = 0; i < this.length; i++) {
                int[] iArr = this.refCounts;
                if (iArr[i] < leastCount) {
                    least = i;
                    leastCount = iArr[i];
                }
            }
            this.mostRecent = least;
            this.indexes[least] = index;
            this.values[least] = value;
            this.refCounts[least] = count;
        }

        /* access modifiers changed from: package-private */
        public int findMostUsed() {
            if (this.length == 0) {
                return -1;
            }
            int max = -1;
            int maxCount = 0;
            for (int i = 0; i < this.length; i++) {
                int[] iArr = this.refCounts;
                if (iArr[i] > maxCount) {
                    max = i;
                    maxCount = iArr[i];
                }
            }
            return this.indexes[max];
        }
    }

    /* access modifiers changed from: private */
    public static final class MixedBlocks {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int blockLength;
        private int length;
        private int mask;
        private int shift;
        private int[] table;

        private MixedBlocks() {
        }

        /* synthetic */ MixedBlocks(AnonymousClass1 x0) {
            this();
        }

        /* access modifiers changed from: package-private */
        public void init(int maxLength, int newBlockLength) {
            int newLength;
            int maxDataIndex = (maxLength - newBlockLength) + 1;
            if (maxDataIndex <= 4095) {
                newLength = 6007;
                this.shift = 12;
                this.mask = Opcodes.OP_IPUT_OBJECT_JUMBO;
            } else if (maxDataIndex <= 32767) {
                newLength = 50021;
                this.shift = 15;
                this.mask = 32767;
            } else if (maxDataIndex <= 131071) {
                newLength = 200003;
                this.shift = 17;
                this.mask = 131071;
            } else {
                newLength = 1500007;
                this.shift = 21;
                this.mask = DictionaryData.TRANSFORM_OFFSET_MASK;
            }
            int[] iArr = this.table;
            if (iArr == null || newLength > iArr.length) {
                this.table = new int[newLength];
            } else {
                Arrays.fill(iArr, 0, newLength, 0);
            }
            this.length = newLength;
            this.blockLength = newBlockLength;
        }

        /* access modifiers changed from: package-private */
        public void extend(int[] data, int minStart, int prevDataLength, int newDataLength) {
            int start;
            int start2 = prevDataLength - this.blockLength;
            if (start2 >= minStart) {
                start = start2 + 1;
            } else {
                start = minStart;
            }
            int end = newDataLength - this.blockLength;
            while (start <= end) {
                addEntry(data, null, start, makeHashCode(data, start), start);
                start++;
            }
        }

        /* access modifiers changed from: package-private */
        public void extend(char[] data, int minStart, int prevDataLength, int newDataLength) {
            int start;
            int start2 = prevDataLength - this.blockLength;
            if (start2 >= minStart) {
                start = start2 + 1;
            } else {
                start = minStart;
            }
            int end = newDataLength - this.blockLength;
            while (start <= end) {
                addEntry(null, data, start, makeHashCode(data, start), start);
                start++;
            }
        }

        /* access modifiers changed from: package-private */
        public int findBlock(int[] data, int[] blockData, int blockStart) {
            int entryIndex = findEntry(data, null, blockData, null, blockStart, makeHashCode(blockData, blockStart));
            if (entryIndex >= 0) {
                return (this.table[entryIndex] & this.mask) - 1;
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public int findBlock(char[] data, int[] blockData, int blockStart) {
            int entryIndex = findEntry(null, data, blockData, null, blockStart, makeHashCode(blockData, blockStart));
            if (entryIndex >= 0) {
                return (this.table[entryIndex] & this.mask) - 1;
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public int findBlock(char[] data, char[] blockData, int blockStart) {
            int entryIndex = findEntry(null, data, null, blockData, blockStart, makeHashCode(blockData, blockStart));
            if (entryIndex >= 0) {
                return (this.table[entryIndex] & this.mask) - 1;
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public int findAllSameBlock(int[] data, int blockValue) {
            int entryIndex = findEntry(data, blockValue, makeHashCode(blockValue));
            if (entryIndex >= 0) {
                return (this.table[entryIndex] & this.mask) - 1;
            }
            return -1;
        }

        private int makeHashCode(int[] blockData, int blockStart) {
            int blockLimit = this.blockLength + blockStart;
            int blockStart2 = blockStart + 1;
            int hashCode = blockData[blockStart];
            while (true) {
                int blockStart3 = blockStart2 + 1;
                hashCode = (hashCode * 37) + blockData[blockStart2];
                if (blockStart3 >= blockLimit) {
                    return hashCode;
                }
                blockStart2 = blockStart3;
            }
        }

        private int makeHashCode(char[] blockData, int blockStart) {
            int blockLimit = this.blockLength + blockStart;
            int blockStart2 = blockStart + 1;
            int blockStart3 = blockData[blockStart];
            while (true) {
                int blockStart4 = blockStart2 + 1;
                int hashCode = ((blockStart3 == 1 ? 1 : 0) * 37) + blockData[blockStart2];
                if (blockStart4 >= blockLimit) {
                    return hashCode;
                }
                blockStart2 = blockStart4;
                blockStart3 = hashCode;
            }
        }

        private int makeHashCode(int blockValue) {
            int hashCode = blockValue;
            for (int i = 1; i < this.blockLength; i++) {
                hashCode = (hashCode * 37) + blockValue;
            }
            return hashCode;
        }

        private void addEntry(int[] data32, char[] data16, int blockStart, int hashCode, int dataIndex) {
            int entryIndex = findEntry(data32, data16, data32, data16, blockStart, hashCode);
            if (entryIndex < 0) {
                this.table[~entryIndex] = (hashCode << this.shift) | (dataIndex + 1);
            }
        }

        private int findEntry(int[] data32, char[] data16, int[] blockData32, char[] blockData16, int blockStart, int hashCode) {
            int shiftedHashCode = hashCode << this.shift;
            int initialEntryIndex = modulo(hashCode, this.length - 1) + 1;
            int entryIndex = initialEntryIndex;
            while (true) {
                int entry = this.table[entryIndex];
                if (entry == 0) {
                    return ~entryIndex;
                }
                int i = this.mask;
                if (((~i) & entry) == shiftedHashCode) {
                    int dataIndex = (i & entry) - 1;
                    if (data32 != null) {
                        if (MutableCodePointTrie.equalBlocks(data32, dataIndex, blockData32, blockStart, this.blockLength)) {
                            break;
                        }
                    } else if (blockData32 != null) {
                        if (MutableCodePointTrie.equalBlocks(data16, dataIndex, blockData32, blockStart, this.blockLength)) {
                            break;
                        }
                    } else if (MutableCodePointTrie.equalBlocks(data16, dataIndex, blockData16, blockStart, this.blockLength)) {
                        break;
                    }
                }
                entryIndex = nextIndex(initialEntryIndex, entryIndex);
            }
            return entryIndex;
        }

        private int findEntry(int[] data, int blockValue, int hashCode) {
            int shiftedHashCode = hashCode << this.shift;
            int initialEntryIndex = modulo(hashCode, this.length - 1) + 1;
            int entryIndex = initialEntryIndex;
            while (true) {
                int entry = this.table[entryIndex];
                if (entry == 0) {
                    return ~entryIndex;
                }
                int i = this.mask;
                if (((~i) & entry) == shiftedHashCode && MutableCodePointTrie.allValuesSameAs(data, (i & entry) - 1, this.blockLength, blockValue)) {
                    return entryIndex;
                }
                entryIndex = nextIndex(initialEntryIndex, entryIndex);
            }
        }

        private int nextIndex(int initialEntryIndex, int entryIndex) {
            return (entryIndex + initialEntryIndex) % this.length;
        }

        private int modulo(int n, int m) {
            int i = n % m;
            if (i < 0) {
                return i + m;
            }
            return i;
        }
    }

    private int compactWholeDataBlocks(int fastILimit, AllSameBlocks allSameBlocks) {
        int newDataCapacity = 128 + 16 + 4;
        int iLimit = this.highStart >> 4;
        int blockLength = 64;
        int inc = 4;
        for (int i = 0; i < iLimit; i += inc) {
            if (i == fastILimit) {
                blockLength = 16;
                inc = 1;
            }
            int value = this.index[i];
            if (this.flags[i] == 1) {
                int[] iArr = this.data;
                value = iArr[value];
                if (allValuesSameAs(iArr, value + 1, blockLength - 1, value)) {
                    this.flags[i] = 0;
                    this.index[i] = value;
                } else {
                    newDataCapacity += blockLength;
                }
            } else if (inc > 1) {
                boolean allSame = true;
                int next_i = i + inc;
                int j = i + 1;
                while (true) {
                    if (j >= next_i) {
                        break;
                    } else if (this.index[j] != value) {
                        allSame = false;
                        break;
                    } else {
                        j++;
                    }
                }
                if (!allSame) {
                    if (getDataBlock(i) < 0) {
                        return -1;
                    }
                    newDataCapacity += blockLength;
                }
            }
            int other = allSameBlocks.findOrAdd(i, inc, value);
            if (other == -2) {
                int jInc = 4;
                int j2 = 0;
                while (true) {
                    if (j2 == i) {
                        allSameBlocks.add(i, inc, value);
                        break;
                    }
                    if (j2 == fastILimit) {
                        jInc = 1;
                    }
                    if (this.flags[j2] == 0 && this.index[j2] == value) {
                        allSameBlocks.add(j2, jInc + inc, value);
                        other = j2;
                        break;
                    }
                    j2 += jInc;
                }
            }
            if (other >= 0) {
                this.flags[i] = 2;
                this.index[i] = other;
            } else {
                newDataCapacity += blockLength;
            }
        }
        return newDataCapacity;
    }

    private int compactData(int fastILimit, int[] newData, int dataNullIndex, MixedBlocks mixedBlocks) {
        int newDataLength = 0;
        int i = 0;
        while (newDataLength < 128) {
            this.index[i] = newDataLength;
            newDataLength += 64;
            i += 4;
        }
        int blockLength = 64;
        mixedBlocks.init(newData.length, 64);
        int i2 = 0;
        mixedBlocks.extend(newData, 0, 0, newDataLength);
        int iLimit = this.highStart >> 4;
        int inc = 4;
        int fastLength = 0;
        for (int i3 = 8; i3 < iLimit; i3 += inc) {
            if (i3 == fastILimit) {
                blockLength = 16;
                inc = 1;
                fastLength = newDataLength;
                mixedBlocks.init(newData.length, 16);
                mixedBlocks.extend(newData, i2, i2, newDataLength);
            }
            byte[] bArr = this.flags;
            if (bArr[i3] == 0) {
                int value = this.index[i3];
                int n = mixedBlocks.findAllSameBlock(newData, value);
                while (true) {
                    if (n < 0) {
                        break;
                    }
                    if (i3 != dataNullIndex || i3 < fastILimit || n >= fastLength || !isStartOfSomeFastBlock(n, this.index, fastILimit)) {
                        break;
                    }
                    n = findAllSameBlock(newData, n + 1, newDataLength, value, blockLength);
                }
                if (n >= 0) {
                    this.index[i3] = n;
                } else {
                    int n2 = getAllSameOverlap(newData, newDataLength, value, blockLength);
                    this.index[i3] = newDataLength - n2;
                    while (n2 < blockLength) {
                        newData[newDataLength] = value;
                        n2++;
                        newDataLength++;
                    }
                    mixedBlocks.extend(newData, i2, newDataLength, newDataLength);
                }
            } else if (bArr[i3] == 1) {
                int block = this.index[i3];
                int n3 = mixedBlocks.findBlock(newData, this.data, block);
                if (n3 >= 0) {
                    this.index[i3] = n3;
                } else {
                    int n4 = getOverlap(newData, newDataLength, this.data, block, blockLength);
                    this.index[i3] = newDataLength - n4;
                    while (n4 < blockLength) {
                        newData[newDataLength] = this.data[n4 + block];
                        newDataLength++;
                        n4++;
                    }
                    i2 = 0;
                    mixedBlocks.extend(newData, 0, newDataLength, newDataLength);
                }
            } else {
                int[] iArr = this.index;
                iArr[i3] = iArr[iArr[i3]];
            }
        }
        return newDataLength;
    }

    /* JADX INFO: Multiple debug info for r5v15 int: [D('j' int), D('v' int)] */
    /* JADX INFO: Multiple debug info for r13v11 int: [D('upperBits' int), D('k' int)] */
    /* JADX INFO: Multiple debug info for r3v16 int: [D('j' int), D('v' int)] */
    /* JADX INFO: Multiple debug info for r8v17 int: [D('upperBits' int), D('k' int)] */
    private int compactIndex(int fastILimit, MixedBlocks mixedBlocks) {
        char[] index2;
        int n;
        int i2;
        int n2;
        int index1Length;
        int i3FirstNull;
        byte f;
        char[] index22;
        int fastIndexLength;
        int iLimit;
        int iStart;
        int index3Capacity;
        int i;
        int index16Capacity;
        int i3;
        char[] cArr;
        int n3;
        int index3Capacity2;
        int n4;
        int oredI3;
        int j;
        int fastIndexLength2 = fastILimit >> 2;
        if ((this.highStart >> 6) <= fastIndexLength2) {
            this.index3NullOffset = 32767;
            return fastIndexLength2;
        }
        char[] fastIndex = new char[fastIndexLength2];
        int i3FirstNull2 = -1;
        int i4 = 0;
        int j2 = 0;
        while (i4 < fastILimit) {
            int i32 = this.index[i4];
            fastIndex[j2] = (char) i32;
            if (i32 != this.dataNullOffset) {
                i3FirstNull2 = -1;
            } else if (i3FirstNull2 < 0) {
                i3FirstNull2 = j2;
            } else if (this.index3NullOffset < 0 && (j2 - i3FirstNull2) + 1 == 32) {
                this.index3NullOffset = i3FirstNull2;
            }
            int iNext = i4 + 4;
            while (true) {
                i4++;
                if (i4 >= iNext) {
                    break;
                }
                i32 += 16;
                this.index[i4] = i32;
            }
            j2++;
        }
        mixedBlocks.init(fastIndexLength2, 32);
        byte b = 0;
        mixedBlocks.extend(fastIndex, 0, 0, fastIndexLength2);
        int index3Capacity3 = 0;
        int i3FirstNull3 = this.index3NullOffset;
        boolean hasLongI3Blocks = false;
        int iStart2 = 4096;
        if (fastILimit < 4096) {
            iStart2 = 0;
        }
        int iLimit2 = this.highStart >> 4;
        int i5 = iStart2;
        while (i5 < iLimit2) {
            int j3 = i5;
            int jLimit = i5 + 32;
            int oredI32 = 0;
            boolean isNull = true;
            while (true) {
                int i33 = this.index[j3];
                oredI3 = oredI32 | i33;
                if (i33 != this.dataNullOffset) {
                    isNull = false;
                }
                j = j3 + 1;
                if (j >= jLimit) {
                    break;
                }
                j3 = j;
                oredI32 = oredI3;
                b = 0;
            }
            if (isNull) {
                this.flags[i5] = b;
                if (i3FirstNull3 < 0) {
                    if (oredI3 <= 65535) {
                        index3Capacity3 += 32;
                    } else {
                        index3Capacity3 += 36;
                        hasLongI3Blocks = true;
                    }
                    i3FirstNull3 = 0;
                }
            } else if (oredI3 <= 65535) {
                int n5 = mixedBlocks.findBlock(fastIndex, this.index, i5);
                if (n5 >= 0) {
                    this.flags[i5] = 1;
                    this.index[i5] = n5;
                } else {
                    this.flags[i5] = 2;
                    index3Capacity3 += 32;
                }
            } else {
                this.flags[i5] = 3;
                index3Capacity3 += 36;
                hasLongI3Blocks = true;
            }
            i5 = j;
            b = 0;
        }
        int index2Capacity = (iLimit2 - iStart2) >> 5;
        int index1Length2 = (index2Capacity + 31) >> 5;
        int index16Capacity2 = fastIndexLength2 + index1Length2 + index3Capacity3 + index2Capacity + 1;
        this.index16 = Arrays.copyOf(fastIndex, index16Capacity2);
        mixedBlocks.init(index16Capacity2, 32);
        MixedBlocks longI3Blocks = null;
        if (hasLongI3Blocks) {
            longI3Blocks = new MixedBlocks(null);
            longI3Blocks.init(index16Capacity2, 36);
        }
        char[] index23 = new char[index2Capacity];
        int i2Length = 0;
        int index3Start = fastIndexLength2 + index1Length2;
        int indexLength = index3Start;
        int indexLength2 = this.index3NullOffset;
        int k = iStart2;
        while (k < iLimit2) {
            byte f2 = this.flags[k];
            if (f2 != 0 || indexLength2 >= 0) {
                index1Length = index1Length2;
                i3FirstNull = indexLength2;
                f = f2;
            } else {
                index1Length = index1Length2;
                f = this.dataNullOffset <= 65535 ? (byte) 2 : 3;
                i3FirstNull = 0;
            }
            if (f == 0) {
                i3 = this.index3NullOffset;
                fastIndexLength = fastIndexLength2;
                i = k;
                index3Capacity = index3Capacity3;
                index22 = index23;
                index16Capacity = index16Capacity2;
                iStart = iStart2;
                iLimit = iLimit2;
            } else if (f == 1) {
                i3 = this.index[k];
                fastIndexLength = fastIndexLength2;
                i = k;
                index3Capacity = index3Capacity3;
                index22 = index23;
                index16Capacity = index16Capacity2;
                iStart = iStart2;
                iLimit = iLimit2;
            } else if (f == 2) {
                int n6 = mixedBlocks.findBlock(this.index16, this.index, k);
                if (n6 >= 0) {
                    i3 = n6;
                    index3Capacity = index3Capacity3;
                } else {
                    if (indexLength == index3Start) {
                        n4 = 0;
                        index3Capacity = index3Capacity3;
                        index3Capacity2 = 32;
                    } else {
                        index3Capacity = index3Capacity3;
                        index3Capacity2 = 32;
                        n4 = getOverlap(this.index16, indexLength, this.index, k, 32);
                    }
                    int i34 = indexLength - n4;
                    while (n4 < index3Capacity2) {
                        this.index16[indexLength] = (char) this.index[n4 + k];
                        indexLength++;
                        i34 = i34;
                        n4++;
                        index3Capacity2 = 32;
                    }
                    mixedBlocks.extend(this.index16, index3Start, indexLength, indexLength);
                    if (hasLongI3Blocks) {
                        longI3Blocks.extend(this.index16, index3Start, indexLength, indexLength);
                    }
                    i3 = i34;
                }
                fastIndexLength = fastIndexLength2;
                i = k;
                index22 = index23;
                index16Capacity = index16Capacity2;
                iStart = iStart2;
                iLimit = iLimit2;
            } else {
                index3Capacity = index3Capacity3;
                int j4 = k;
                int jLimit2 = k + 32;
                int k2 = indexLength;
                while (true) {
                    int k3 = k2 + 1;
                    index16Capacity = index16Capacity2;
                    int[] iArr = this.index;
                    int j5 = j4 + 1;
                    int v = iArr[j4];
                    iStart = iStart2;
                    cArr = this.index16;
                    int k4 = k3 + 1;
                    iLimit = iLimit2;
                    cArr[k3] = (char) v;
                    int j6 = j5 + 1;
                    int v2 = iArr[j5];
                    int k5 = k4 + 1;
                    fastIndexLength = fastIndexLength2;
                    cArr[k4] = (char) v2;
                    int j7 = j6 + 1;
                    int v3 = iArr[j6];
                    int k6 = k5 + 1;
                    i = k;
                    cArr[k5] = (char) v3;
                    int j8 = j7 + 1;
                    int v4 = iArr[j7];
                    int upperBits = ((v4 & 196608) >> 8) | ((v3 & 196608) >> 6) | ((v & 196608) >> 2) | ((v2 & 196608) >> 4);
                    int k7 = k6 + 1;
                    index22 = index23;
                    cArr[k6] = (char) v4;
                    int j9 = j8 + 1;
                    int v5 = iArr[j8];
                    int upperBits2 = upperBits | ((v5 & 196608) >> 10);
                    int k8 = k7 + 1;
                    cArr[k7] = (char) v5;
                    int j10 = j9 + 1;
                    int v6 = iArr[j9];
                    int upperBits3 = upperBits2 | ((v6 & 196608) >> 12);
                    int k9 = k8 + 1;
                    cArr[k8] = (char) v6;
                    int j11 = j10 + 1;
                    int v7 = iArr[j10];
                    int upperBits4 = upperBits3 | ((v7 & 196608) >> 14);
                    int k10 = k9 + 1;
                    cArr[k9] = (char) v7;
                    int j12 = j11 + 1;
                    int v8 = iArr[j11];
                    int k11 = k10 + 1;
                    cArr[k10] = (char) v8;
                    cArr[k11 - 9] = (char) (upperBits4 | ((v8 & 196608) >> 16));
                    if (j12 >= jLimit2) {
                        break;
                    }
                    k2 = k11;
                    j4 = j12;
                    index16Capacity2 = index16Capacity;
                    k = i;
                    iStart2 = iStart;
                    iLimit2 = iLimit;
                    fastIndexLength2 = fastIndexLength;
                    index23 = index22;
                }
                int n7 = longI3Blocks.findBlock(cArr, cArr, indexLength);
                if (n7 >= 0) {
                    i3 = 32768 | n7;
                } else {
                    if (indexLength == index3Start) {
                        n3 = 0;
                    } else {
                        char[] cArr2 = this.index16;
                        n3 = getOverlap(cArr2, indexLength, cArr2, indexLength, 36);
                    }
                    int i35 = 32768 | (indexLength - n3);
                    if (n3 > 0) {
                        while (n3 < 36) {
                            char[] cArr3 = this.index16;
                            cArr3[indexLength] = cArr3[n3 + indexLength];
                            indexLength++;
                            n3++;
                        }
                    } else {
                        indexLength += 36;
                    }
                    mixedBlocks.extend(this.index16, index3Start, indexLength, indexLength);
                    if (hasLongI3Blocks) {
                        longI3Blocks.extend(this.index16, index3Start, indexLength, indexLength);
                    }
                    i3 = i35;
                }
            }
            if (this.index3NullOffset < 0 && i3FirstNull >= 0) {
                this.index3NullOffset = i3;
            }
            index22[i2Length] = (char) i3;
            k = i + 32;
            i2Length++;
            indexLength2 = i3FirstNull;
            index16Capacity2 = index16Capacity;
            index2Capacity = index2Capacity;
            index1Length2 = index1Length;
            index3Capacity3 = index3Capacity;
            iStart2 = iStart;
            iLimit2 = iLimit;
            fastIndexLength2 = fastIndexLength;
            index23 = index22;
        }
        char[] index24 = index23;
        if (this.index3NullOffset < 0) {
            this.index3NullOffset = 32767;
        }
        if (indexLength < 32799) {
            int blockLength = 32;
            int i1 = fastIndexLength2;
            int i6 = 0;
            while (i6 < i2Length) {
                if (i2Length - i6 >= blockLength) {
                    index2 = index24;
                    n = mixedBlocks.findBlock(this.index16, index2, i6);
                } else {
                    index2 = index24;
                    blockLength = i2Length - i6;
                    n = findSameBlock(this.index16, index3Start, indexLength, index2, i6, blockLength);
                }
                if (n >= 0) {
                    i2 = n;
                } else {
                    if (indexLength == index3Start) {
                        n2 = 0;
                    } else {
                        n2 = getOverlap(this.index16, indexLength, index2, i6, blockLength);
                    }
                    i2 = indexLength - n2;
                    while (n2 < blockLength) {
                        this.index16[indexLength] = index2[n2 + i6];
                        indexLength++;
                        n2++;
                    }
                    mixedBlocks.extend(this.index16, index3Start, indexLength, indexLength);
                }
                this.index16[i1] = (char) i2;
                i6 += blockLength;
                index24 = index2;
                i1++;
            }
            return indexLength;
        }
        throw new IndexOutOfBoundsException("The trie data exceeds limitations of the data structure.");
    }

    private int compactTrie(int fastILimit) {
        this.highValue = get(1114111);
        int realHighStart = (findHighStart() + Opcodes.OP_CHECK_CAST_JUMBO) & -512;
        if (realHighStart == 1114112) {
            this.highValue = this.initialValue;
        }
        int fastLimit = fastILimit << 4;
        if (realHighStart < fastLimit) {
            for (int i = realHighStart >> 4; i < fastILimit; i++) {
                this.flags[i] = 0;
                this.index[i] = this.highValue;
            }
            this.highStart = fastLimit;
        } else {
            this.highStart = realHighStart;
        }
        int[] asciiData = new int[128];
        for (int i2 = 0; i2 < 128; i2++) {
            asciiData[i2] = get(i2);
        }
        AllSameBlocks allSameBlocks = new AllSameBlocks();
        int[] newData = Arrays.copyOf(asciiData, compactWholeDataBlocks(fastILimit, allSameBlocks));
        int dataNullIndex = allSameBlocks.findMostUsed();
        MixedBlocks mixedBlocks = new MixedBlocks(null);
        int newDataLength = compactData(fastILimit, newData, dataNullIndex, mixedBlocks);
        this.data = newData;
        this.dataLength = newDataLength;
        if (this.dataLength <= 262159) {
            if (dataNullIndex >= 0) {
                this.dataNullOffset = this.index[dataNullIndex];
                this.initialValue = this.data[this.dataNullOffset];
            } else {
                this.dataNullOffset = 1048575;
            }
            int indexLength = compactIndex(fastILimit, mixedBlocks);
            this.highStart = realHighStart;
            return indexLength;
        }
        throw new IndexOutOfBoundsException("The trie data exceeds limitations of the data structure.");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.util.MutableCodePointTrie$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$util$CodePointTrie$ValueWidth = new int[CodePointTrie.ValueWidth.values().length];

        static {
            try {
                $SwitchMap$android$icu$util$CodePointTrie$ValueWidth[CodePointTrie.ValueWidth.BITS_32.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$util$CodePointTrie$ValueWidth[CodePointTrie.ValueWidth.BITS_16.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$util$CodePointTrie$ValueWidth[CodePointTrie.ValueWidth.BITS_8.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00dd, code lost:
        if (r8[r6 - 2] == r13.highValue) goto L_0x0120;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.icu.util.CodePointTrie build(android.icu.util.CodePointTrie.Type r14, android.icu.util.CodePointTrie.ValueWidth r15) {
        /*
        // Method dump skipped, instructions count: 498
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.MutableCodePointTrie.build(android.icu.util.CodePointTrie$Type, android.icu.util.CodePointTrie$ValueWidth):android.icu.util.CodePointTrie");
    }
}
