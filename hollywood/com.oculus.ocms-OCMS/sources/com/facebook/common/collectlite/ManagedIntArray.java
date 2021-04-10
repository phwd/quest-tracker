package com.facebook.common.collectlite;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ManagedIntArray {
    private static final double INNER_ARRAY_GROWTH_FACTOR = 1.8d;
    int[] mArray;
    int mLength = 0;

    public static ManagedIntArray createWithInitialCapacity(int i) {
        return new ManagedIntArray(i);
    }

    private ManagedIntArray(int i) {
        this.mArray = new int[i];
    }

    public void add(int i) {
        growArrayIfNeeded();
        int[] iArr = this.mArray;
        int i2 = this.mLength;
        this.mLength = i2 + 1;
        iArr[i2] = i;
    }

    public int get(int i) {
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

    public int indexOf(int i) {
        for (int i2 = 0; i2 < this.mLength; i2++) {
            if (this.mArray[i2] == i) {
                return i2;
            }
        }
        return -1;
    }

    public void clear() {
        this.mLength = 0;
    }

    public int[] getArrayCopy() {
        int i = this.mLength;
        int[] iArr = new int[i];
        System.arraycopy(this.mArray, 0, iArr, 0, i);
        return iArr;
    }

    public void swapElements(int i, int i2) {
        int[] iArr = this.mArray;
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(size() * 3);
        int size = size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(get(i));
        }
        return sb.toString();
    }

    private void growArrayIfNeeded() {
        int i = this.mLength;
        if (i >= this.mArray.length) {
            double d = (double) i;
            Double.isNaN(d);
            int[] iArr = new int[Math.max(i + 1, (int) (d * INNER_ARRAY_GROWTH_FACTOR))];
            System.arraycopy(this.mArray, 0, iArr, 0, this.mLength);
            this.mArray = iArr;
        }
    }
}
