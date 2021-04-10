package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.LongBuffer;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class BitSet implements Cloneable, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ADDRESS_BITS_PER_WORD = 6;
    private static final int BITS_PER_WORD = 64;
    private static final int BIT_INDEX_MASK = 63;
    private static final long WORD_MASK = -1;
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("bits", long[].class)};
    private static final long serialVersionUID = 7997698588986878753L;
    private transient boolean sizeIsSticky = false;
    private long[] words;
    private transient int wordsInUse = 0;

    private static int wordIndex(int bitIndex) {
        return bitIndex >> 6;
    }

    private void checkInvariants() {
    }

    private void recalculateWordsInUse() {
        int i = this.wordsInUse - 1;
        while (i >= 0 && this.words[i] == 0) {
            i--;
        }
        this.wordsInUse = i + 1;
    }

    public BitSet() {
        initWords(64);
        this.sizeIsSticky = false;
    }

    public BitSet(int nbits) {
        if (nbits >= 0) {
            initWords(nbits);
            this.sizeIsSticky = true;
            return;
        }
        throw new NegativeArraySizeException("nbits < 0: " + nbits);
    }

    private void initWords(int nbits) {
        this.words = new long[(wordIndex(nbits - 1) + 1)];
    }

    private BitSet(long[] words2) {
        this.words = words2;
        this.wordsInUse = words2.length;
        checkInvariants();
    }

    public static BitSet valueOf(long[] longs) {
        int n = longs.length;
        while (n > 0 && longs[n - 1] == 0) {
            n--;
        }
        return new BitSet(Arrays.copyOf(longs, n));
    }

    public static BitSet valueOf(LongBuffer lb) {
        LongBuffer lb2 = lb.slice();
        int n = lb2.remaining();
        while (n > 0 && lb2.get(n - 1) == 0) {
            n--;
        }
        long[] words2 = new long[n];
        lb2.get(words2);
        return new BitSet(words2);
    }

    public static BitSet valueOf(byte[] bytes) {
        return valueOf(ByteBuffer.wrap(bytes));
    }

    public static BitSet valueOf(ByteBuffer bb) {
        ByteBuffer bb2 = bb.slice().order(ByteOrder.LITTLE_ENDIAN);
        int n = bb2.remaining();
        while (n > 0 && bb2.get(n - 1) == 0) {
            n--;
        }
        long[] words2 = new long[((n + 7) / 8)];
        bb2.limit(n);
        int i = 0;
        while (bb2.remaining() >= 8) {
            words2[i] = bb2.getLong();
            i++;
        }
        int remaining = bb2.remaining();
        for (int j = 0; j < remaining; j++) {
            words2[i] = words2[i] | ((((long) bb2.get()) & 255) << (j * 8));
        }
        return new BitSet(words2);
    }

    /* JADX INFO: Multiple debug info for r3v3 byte[]: [D('x' long), D('bytes' byte[])] */
    public byte[] toByteArray() {
        int n = this.wordsInUse;
        if (n == 0) {
            return new byte[0];
        }
        int len = (n - 1) * 8;
        for (long x = this.words[n - 1]; x != 0; x >>>= 8) {
            len++;
        }
        byte[] bytes = new byte[len];
        ByteBuffer bb = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        for (int i = 0; i < n - 1; i++) {
            bb.putLong(this.words[i]);
        }
        for (long x2 = this.words[n - 1]; x2 != 0; x2 >>>= 8) {
            bb.put((byte) ((int) (255 & x2)));
        }
        return bytes;
    }

    public long[] toLongArray() {
        return Arrays.copyOf(this.words, this.wordsInUse);
    }

    private void ensureCapacity(int wordsRequired) {
        long[] jArr = this.words;
        if (jArr.length < wordsRequired) {
            this.words = Arrays.copyOf(this.words, Math.max(jArr.length * 2, wordsRequired));
            this.sizeIsSticky = false;
        }
    }

    private void expandTo(int wordIndex) {
        int wordsRequired = wordIndex + 1;
        if (this.wordsInUse < wordsRequired) {
            ensureCapacity(wordsRequired);
            this.wordsInUse = wordsRequired;
        }
    }

    private static void checkRange(int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex < 0: " + fromIndex);
        } else if (toIndex < 0) {
            throw new IndexOutOfBoundsException("toIndex < 0: " + toIndex);
        } else if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
        }
    }

    public void flip(int bitIndex) {
        if (bitIndex >= 0) {
            int wordIndex = wordIndex(bitIndex);
            expandTo(wordIndex);
            long[] jArr = this.words;
            jArr[wordIndex] = jArr[wordIndex] ^ (1 << bitIndex);
            recalculateWordsInUse();
            checkInvariants();
            return;
        }
        throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);
    }

    public void flip(int fromIndex, int toIndex) {
        checkRange(fromIndex, toIndex);
        if (fromIndex != toIndex) {
            int startWordIndex = wordIndex(fromIndex);
            int endWordIndex = wordIndex(toIndex - 1);
            expandTo(endWordIndex);
            long firstWordMask = -1 << fromIndex;
            long lastWordMask = -1 >>> (-toIndex);
            if (startWordIndex == endWordIndex) {
                long[] jArr = this.words;
                jArr[startWordIndex] = jArr[startWordIndex] ^ (firstWordMask & lastWordMask);
            } else {
                long[] jArr2 = this.words;
                jArr2[startWordIndex] = jArr2[startWordIndex] ^ firstWordMask;
                for (int i = startWordIndex + 1; i < endWordIndex; i++) {
                    long[] jArr3 = this.words;
                    jArr3[i] = ~jArr3[i];
                }
                long[] jArr4 = this.words;
                jArr4[endWordIndex] = jArr4[endWordIndex] ^ lastWordMask;
            }
            recalculateWordsInUse();
            checkInvariants();
        }
    }

    public void set(int bitIndex) {
        if (bitIndex >= 0) {
            int wordIndex = wordIndex(bitIndex);
            expandTo(wordIndex);
            long[] jArr = this.words;
            jArr[wordIndex] = jArr[wordIndex] | (1 << bitIndex);
            checkInvariants();
            return;
        }
        throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);
    }

    public void set(int bitIndex, boolean value) {
        if (value) {
            set(bitIndex);
        } else {
            clear(bitIndex);
        }
    }

    public void set(int fromIndex, int toIndex) {
        checkRange(fromIndex, toIndex);
        if (fromIndex != toIndex) {
            int startWordIndex = wordIndex(fromIndex);
            int endWordIndex = wordIndex(toIndex - 1);
            expandTo(endWordIndex);
            long firstWordMask = -1 << fromIndex;
            long lastWordMask = -1 >>> (-toIndex);
            if (startWordIndex == endWordIndex) {
                long[] jArr = this.words;
                jArr[startWordIndex] = jArr[startWordIndex] | (firstWordMask & lastWordMask);
            } else {
                long[] jArr2 = this.words;
                jArr2[startWordIndex] = jArr2[startWordIndex] | firstWordMask;
                for (int i = startWordIndex + 1; i < endWordIndex; i++) {
                    this.words[i] = -1;
                }
                long[] jArr3 = this.words;
                jArr3[endWordIndex] = jArr3[endWordIndex] | lastWordMask;
            }
            checkInvariants();
        }
    }

    public void set(int fromIndex, int toIndex, boolean value) {
        if (value) {
            set(fromIndex, toIndex);
        } else {
            clear(fromIndex, toIndex);
        }
    }

    public void clear(int bitIndex) {
        if (bitIndex >= 0) {
            int wordIndex = wordIndex(bitIndex);
            if (wordIndex < this.wordsInUse) {
                long[] jArr = this.words;
                jArr[wordIndex] = jArr[wordIndex] & (~(1 << bitIndex));
                recalculateWordsInUse();
                checkInvariants();
                return;
            }
            return;
        }
        throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);
    }

    public void clear(int fromIndex, int toIndex) {
        int startWordIndex;
        checkRange(fromIndex, toIndex);
        if (fromIndex != toIndex && (startWordIndex = wordIndex(fromIndex)) < this.wordsInUse) {
            int endWordIndex = wordIndex(toIndex - 1);
            if (endWordIndex >= this.wordsInUse) {
                toIndex = length();
                endWordIndex = this.wordsInUse - 1;
            }
            long firstWordMask = -1 << fromIndex;
            long lastWordMask = -1 >>> (-toIndex);
            if (startWordIndex == endWordIndex) {
                long[] jArr = this.words;
                jArr[startWordIndex] = jArr[startWordIndex] & (~(firstWordMask & lastWordMask));
            } else {
                long[] jArr2 = this.words;
                jArr2[startWordIndex] = jArr2[startWordIndex] & (~firstWordMask);
                for (int i = startWordIndex + 1; i < endWordIndex; i++) {
                    this.words[i] = 0;
                }
                long[] jArr3 = this.words;
                jArr3[endWordIndex] = jArr3[endWordIndex] & (~lastWordMask);
            }
            recalculateWordsInUse();
            checkInvariants();
        }
    }

    public void clear() {
        while (true) {
            int i = this.wordsInUse;
            if (i > 0) {
                long[] jArr = this.words;
                int i2 = i - 1;
                this.wordsInUse = i2;
                jArr[i2] = 0;
            } else {
                return;
            }
        }
    }

    public boolean get(int bitIndex) {
        if (bitIndex >= 0) {
            checkInvariants();
            int wordIndex = wordIndex(bitIndex);
            return wordIndex < this.wordsInUse && (this.words[wordIndex] & (1 << bitIndex)) != 0;
        }
        throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);
    }

    public BitSet get(int fromIndex, int toIndex) {
        long j;
        long j2;
        int toIndex2 = toIndex;
        checkRange(fromIndex, toIndex);
        checkInvariants();
        int len = length();
        boolean wordAligned = false;
        if (len <= fromIndex || fromIndex == toIndex2) {
            return new BitSet(0);
        }
        if (toIndex2 > len) {
            toIndex2 = len;
        }
        BitSet result = new BitSet(toIndex2 - fromIndex);
        int targetWords = wordIndex((toIndex2 - fromIndex) - 1) + 1;
        int sourceIndex = wordIndex(fromIndex);
        if ((fromIndex & 63) == 0) {
            wordAligned = true;
        }
        int i = 0;
        while (i < targetWords - 1) {
            long[] jArr = result.words;
            if (wordAligned) {
                j2 = this.words[sourceIndex];
            } else {
                long[] jArr2 = this.words;
                j2 = (jArr2[sourceIndex] >>> fromIndex) | (jArr2[sourceIndex + 1] << (-fromIndex));
            }
            jArr[i] = j2;
            i++;
            sourceIndex++;
        }
        long lastWordMask = -1 >>> (-toIndex2);
        long[] jArr3 = result.words;
        int i2 = targetWords - 1;
        if (((toIndex2 - 1) & 63) < (fromIndex & 63)) {
            long[] jArr4 = this.words;
            j = (jArr4[sourceIndex] >>> fromIndex) | ((jArr4[sourceIndex + 1] & lastWordMask) << (-fromIndex));
        } else {
            j = (this.words[sourceIndex] & lastWordMask) >>> fromIndex;
        }
        jArr3[i2] = j;
        result.wordsInUse = targetWords;
        result.recalculateWordsInUse();
        result.checkInvariants();
        return result;
    }

    public int nextSetBit(int fromIndex) {
        if (fromIndex >= 0) {
            checkInvariants();
            int u = wordIndex(fromIndex);
            if (u >= this.wordsInUse) {
                return -1;
            }
            long word = this.words[u] & (-1 << fromIndex);
            while (word == 0) {
                u++;
                if (u == this.wordsInUse) {
                    return -1;
                }
                word = this.words[u];
            }
            return (u * 64) + Long.numberOfTrailingZeros(word);
        }
        throw new IndexOutOfBoundsException("fromIndex < 0: " + fromIndex);
    }

    public int nextClearBit(int fromIndex) {
        if (fromIndex >= 0) {
            checkInvariants();
            int u = wordIndex(fromIndex);
            if (u >= this.wordsInUse) {
                return fromIndex;
            }
            long word = (~this.words[u]) & (-1 << fromIndex);
            while (word == 0) {
                u++;
                int i = this.wordsInUse;
                if (u == i) {
                    return i * 64;
                }
                word = ~this.words[u];
            }
            return (u * 64) + Long.numberOfTrailingZeros(word);
        }
        throw new IndexOutOfBoundsException("fromIndex < 0: " + fromIndex);
    }

    public int previousSetBit(int fromIndex) {
        if (fromIndex >= 0) {
            checkInvariants();
            int u = wordIndex(fromIndex);
            if (u >= this.wordsInUse) {
                return length() - 1;
            }
            long word = this.words[u] & (-1 >>> (-(fromIndex + 1)));
            while (word == 0) {
                int u2 = u - 1;
                if (u == 0) {
                    return -1;
                }
                word = this.words[u2];
                u = u2;
            }
            return (((u + 1) * 64) - 1) - Long.numberOfLeadingZeros(word);
        } else if (fromIndex == -1) {
            return -1;
        } else {
            throw new IndexOutOfBoundsException("fromIndex < -1: " + fromIndex);
        }
    }

    public int previousClearBit(int fromIndex) {
        if (fromIndex >= 0) {
            checkInvariants();
            int u = wordIndex(fromIndex);
            if (u >= this.wordsInUse) {
                return fromIndex;
            }
            long word = (~this.words[u]) & (-1 >>> (-(fromIndex + 1)));
            while (word == 0) {
                int u2 = u - 1;
                if (u == 0) {
                    return -1;
                }
                word = ~this.words[u2];
                u = u2;
            }
            return (((u + 1) * 64) - 1) - Long.numberOfLeadingZeros(word);
        } else if (fromIndex == -1) {
            return -1;
        } else {
            throw new IndexOutOfBoundsException("fromIndex < -1: " + fromIndex);
        }
    }

    public int length() {
        int i = this.wordsInUse;
        if (i == 0) {
            return 0;
        }
        return ((i - 1) * 64) + (64 - Long.numberOfLeadingZeros(this.words[i - 1]));
    }

    public boolean isEmpty() {
        return this.wordsInUse == 0;
    }

    public boolean intersects(BitSet set) {
        for (int i = Math.min(this.wordsInUse, set.wordsInUse) - 1; i >= 0; i--) {
            if ((this.words[i] & set.words[i]) != 0) {
                return true;
            }
        }
        return false;
    }

    public int cardinality() {
        int sum = 0;
        for (int i = 0; i < this.wordsInUse; i++) {
            sum += Long.bitCount(this.words[i]);
        }
        return sum;
    }

    public void and(BitSet set) {
        if (this != set) {
            while (true) {
                int i = this.wordsInUse;
                if (i <= set.wordsInUse) {
                    break;
                }
                long[] jArr = this.words;
                int i2 = i - 1;
                this.wordsInUse = i2;
                jArr[i2] = 0;
            }
            for (int i3 = 0; i3 < this.wordsInUse; i3++) {
                long[] jArr2 = this.words;
                jArr2[i3] = jArr2[i3] & set.words[i3];
            }
            recalculateWordsInUse();
            checkInvariants();
        }
    }

    public void or(BitSet set) {
        if (this != set) {
            int wordsInCommon = Math.min(this.wordsInUse, set.wordsInUse);
            int i = this.wordsInUse;
            int i2 = set.wordsInUse;
            if (i < i2) {
                ensureCapacity(i2);
                this.wordsInUse = set.wordsInUse;
            }
            for (int i3 = 0; i3 < wordsInCommon; i3++) {
                long[] jArr = this.words;
                jArr[i3] = jArr[i3] | set.words[i3];
            }
            if (wordsInCommon < set.wordsInUse) {
                System.arraycopy((Object) set.words, wordsInCommon, (Object) this.words, wordsInCommon, this.wordsInUse - wordsInCommon);
            }
            checkInvariants();
        }
    }

    public void xor(BitSet set) {
        int wordsInCommon = Math.min(this.wordsInUse, set.wordsInUse);
        int i = this.wordsInUse;
        int i2 = set.wordsInUse;
        if (i < i2) {
            ensureCapacity(i2);
            this.wordsInUse = set.wordsInUse;
        }
        for (int i3 = 0; i3 < wordsInCommon; i3++) {
            long[] jArr = this.words;
            jArr[i3] = jArr[i3] ^ set.words[i3];
        }
        int i4 = set.wordsInUse;
        if (wordsInCommon < i4) {
            System.arraycopy((Object) set.words, wordsInCommon, (Object) this.words, wordsInCommon, i4 - wordsInCommon);
        }
        recalculateWordsInUse();
        checkInvariants();
    }

    public void andNot(BitSet set) {
        for (int i = Math.min(this.wordsInUse, set.wordsInUse) - 1; i >= 0; i--) {
            long[] jArr = this.words;
            jArr[i] = jArr[i] & (~set.words[i]);
        }
        recalculateWordsInUse();
        checkInvariants();
    }

    public int hashCode() {
        long h = 1234;
        int i = this.wordsInUse;
        while (true) {
            i--;
            if (i < 0) {
                return (int) ((h >> 32) ^ h);
            }
            h ^= this.words[i] * ((long) (i + 1));
        }
    }

    public int size() {
        return this.words.length * 64;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitSet)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        BitSet set = (BitSet) obj;
        checkInvariants();
        set.checkInvariants();
        if (this.wordsInUse != set.wordsInUse) {
            return false;
        }
        for (int i = 0; i < this.wordsInUse; i++) {
            if (this.words[i] != set.words[i]) {
                return false;
            }
        }
        return true;
    }

    public Object clone() {
        if (!this.sizeIsSticky) {
            trimToSize();
        }
        try {
            BitSet result = (BitSet) super.clone();
            result.words = (long[]) this.words.clone();
            result.checkInvariants();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    private void trimToSize() {
        int i = this.wordsInUse;
        long[] jArr = this.words;
        if (i != jArr.length) {
            this.words = Arrays.copyOf(jArr, i);
            checkInvariants();
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        checkInvariants();
        if (!this.sizeIsSticky) {
            trimToSize();
        }
        s.putFields().put("bits", this.words);
        s.writeFields();
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        this.words = (long[]) s.readFields().get("bits", (Object) null);
        this.wordsInUse = this.words.length;
        recalculateWordsInUse();
        long[] jArr = this.words;
        boolean z = true;
        if (jArr.length <= 0 || jArr[jArr.length - 1] != 0) {
            z = false;
        }
        this.sizeIsSticky = z;
        checkInvariants();
    }

    public String toString() {
        checkInvariants();
        int i = this.wordsInUse;
        StringBuilder b = new StringBuilder(((i > 128 ? cardinality() : i * 64) * 6) + 2);
        b.append('{');
        int i2 = nextSetBit(0);
        if (i2 != -1) {
            b.append(i2);
            while (true) {
                int i3 = i2 + 1;
                if (i3 < 0) {
                    break;
                }
                int nextSetBit = nextSetBit(i3);
                i2 = nextSetBit;
                if (nextSetBit < 0) {
                    break;
                }
                int endOfRun = nextClearBit(i2);
                do {
                    b.append(", ");
                    b.append(i2);
                    i2++;
                } while (i2 != endOfRun);
            }
        }
        b.append('}');
        return b.toString();
    }

    public IntStream stream() {
        return StreamSupport.intStream(new Supplier() {
            /* class java.util.$$Lambda$BitSet$ifk7HV82uu42BYsPVrvRaHrugk */

            @Override // java.util.function.Supplier
            public final Object get() {
                return BitSet.this.lambda$stream$0$BitSet();
            }
        }, 16469, false);
    }

    public /* synthetic */ Spliterator.OfInt lambda$stream$0$BitSet() {
        return Spliterators.spliterator((PrimitiveIterator.OfInt) new PrimitiveIterator.OfInt() {
            /* class java.util.BitSet.AnonymousClass1BitSetIterator */
            int next = BitSet.this.nextSetBit(0);

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next != -1;
            }

            @Override // java.util.PrimitiveIterator.OfInt
            public int nextInt() {
                int i = this.next;
                if (i != -1) {
                    int ret = this.next;
                    this.next = BitSet.this.nextSetBit(i + 1);
                    return ret;
                }
                throw new NoSuchElementException();
            }
        }, (long) cardinality(), 21);
    }
}
