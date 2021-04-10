package com.facebook.common.collectlite;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ManagedLongArray {
    private static final double INNER_ARRAY_GROWTH_FACTOR = 1.8d;
    long[] mArray;
    int mLength = 0;

    public static ManagedLongArray createWithInitialCapacity(int i) {
        return new ManagedLongArray(i);
    }

    private ManagedLongArray(int i) {
        this.mArray = new long[i];
    }

    public void add(long j) {
        growArrayIfNeeded();
        long[] jArr = this.mArray;
        int i = this.mLength;
        this.mLength = i + 1;
        jArr[i] = j;
    }

    public long get(int i) {
        if (i >= 0 && i < this.mLength) {
            return this.mArray[i];
        }
        throw new IndexOutOfBoundsException("Index " + i + " is out of bounds. Collection length " + this.mLength);
    }

    public int size() {
        return this.mLength;
    }

    public boolean isEmpty() {
        return this.mLength == 0;
    }

    public int indexOf(long j) {
        for (int i = 0; i < this.mLength; i++) {
            if (this.mArray[i] == j) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        this.mLength = 0;
    }

    public long[] getArrayCopy() {
        int i = this.mLength;
        long[] jArr = new long[i];
        System.arraycopy(this.mArray, 0, jArr, 0, i);
        return jArr;
    }

    public void swapElements(int i, int i2) {
        long[] jArr = this.mArray;
        long j = jArr[i];
        jArr[i] = jArr[i2];
        jArr[i2] = j;
    }

    private void growArrayIfNeeded() {
        int i = this.mLength;
        if (i >= this.mArray.length) {
            double d = (double) i;
            Double.isNaN(d);
            long[] jArr = new long[Math.max(i + 1, (int) (d * INNER_ARRAY_GROWTH_FACTOR))];
            System.arraycopy(this.mArray, 0, jArr, 0, this.mLength);
            this.mArray = jArr;
        }
    }
}
