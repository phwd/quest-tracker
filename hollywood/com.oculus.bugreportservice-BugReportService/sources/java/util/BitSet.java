package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;

public class BitSet implements Cloneable, Serializable {
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("bits", long[].class)};
    private static final long serialVersionUID = 7997698588986878753L;
    private transient boolean sizeIsSticky = false;
    private long[] words;
    private transient int wordsInUse = 0;

    private void checkInvariants() {
    }

    private static int wordIndex(int i) {
        return i >> 6;
    }

    public BitSet() {
        initWords(64);
        this.sizeIsSticky = false;
    }

    public BitSet(int i) {
        if (i >= 0) {
            initWords(i);
            this.sizeIsSticky = true;
            return;
        }
        throw new NegativeArraySizeException("nbits < 0: " + i);
    }

    private void initWords(int i) {
        this.words = new long[(wordIndex(i - 1) + 1)];
    }

    private void ensureCapacity(int i) {
        long[] jArr = this.words;
        if (jArr.length < i) {
            this.words = Arrays.copyOf(this.words, Math.max(jArr.length * 2, i));
            this.sizeIsSticky = false;
        }
    }

    private void expandTo(int i) {
        int i2 = i + 1;
        if (this.wordsInUse < i2) {
            ensureCapacity(i2);
            this.wordsInUse = i2;
        }
    }

    public void set(int i) {
        if (i >= 0) {
            int wordIndex = wordIndex(i);
            expandTo(wordIndex);
            long[] jArr = this.words;
            jArr[wordIndex] = jArr[wordIndex] | (1 << i);
            checkInvariants();
            return;
        }
        throw new IndexOutOfBoundsException("bitIndex < 0: " + i);
    }

    public boolean get(int i) {
        if (i >= 0) {
            checkInvariants();
            int wordIndex = wordIndex(i);
            if (wordIndex < this.wordsInUse) {
                if (((1 << i) & this.words[wordIndex]) != 0) {
                    return true;
                }
            }
            return false;
        }
        throw new IndexOutOfBoundsException("bitIndex < 0: " + i);
    }

    public int nextSetBit(int i) {
        if (i >= 0) {
            checkInvariants();
            int wordIndex = wordIndex(i);
            if (wordIndex >= this.wordsInUse) {
                return -1;
            }
            long j = this.words[wordIndex] & (-1 << i);
            while (j == 0) {
                wordIndex++;
                if (wordIndex == this.wordsInUse) {
                    return -1;
                }
                j = this.words[wordIndex];
            }
            return (wordIndex * 64) + Long.numberOfTrailingZeros(j);
        }
        throw new IndexOutOfBoundsException("fromIndex < 0: " + i);
    }

    public int nextClearBit(int i) {
        if (i >= 0) {
            checkInvariants();
            int wordIndex = wordIndex(i);
            if (wordIndex >= this.wordsInUse) {
                return i;
            }
            long j = (~this.words[wordIndex]) & (-1 << i);
            while (j == 0) {
                wordIndex++;
                int i2 = this.wordsInUse;
                if (wordIndex == i2) {
                    return i2 * 64;
                }
                j = ~this.words[wordIndex];
            }
            return (wordIndex * 64) + Long.numberOfTrailingZeros(j);
        }
        throw new IndexOutOfBoundsException("fromIndex < 0: " + i);
    }

    public int cardinality() {
        int i = 0;
        for (int i2 = 0; i2 < this.wordsInUse; i2++) {
            i += Long.bitCount(this.words[i2]);
        }
        return i;
    }

    public int hashCode() {
        int i = this.wordsInUse;
        long j = 1234;
        while (true) {
            i--;
            if (i < 0) {
                return (int) ((j >> 32) ^ j);
            }
            j ^= this.words[i] * ((long) (i + 1));
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitSet)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        BitSet bitSet = (BitSet) obj;
        checkInvariants();
        bitSet.checkInvariants();
        if (this.wordsInUse != bitSet.wordsInUse) {
            return false;
        }
        for (int i = 0; i < this.wordsInUse; i++) {
            if (this.words[i] != bitSet.words[i]) {
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
            BitSet bitSet = (BitSet) super.clone();
            bitSet.words = (long[]) this.words.clone();
            bitSet.checkInvariants();
            return bitSet;
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

    private void writeObject(ObjectOutputStream objectOutputStream) {
        checkInvariants();
        if (!this.sizeIsSticky) {
            trimToSize();
        }
        objectOutputStream.putFields();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readFields();
        throw null;
    }

    public String toString() {
        checkInvariants();
        int i = this.wordsInUse;
        StringBuilder sb = new StringBuilder(((i > 128 ? cardinality() : i * 64) * 6) + 2);
        sb.append('{');
        int nextSetBit = nextSetBit(0);
        if (nextSetBit != -1) {
            sb.append(nextSetBit);
            while (true) {
                int i2 = nextSetBit + 1;
                if (i2 < 0 || (nextSetBit = nextSetBit(i2)) < 0) {
                    break;
                }
                int nextClearBit = nextClearBit(nextSetBit);
                do {
                    sb.append(", ");
                    sb.append(nextSetBit);
                    nextSetBit++;
                } while (nextSetBit != nextClearBit);
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
