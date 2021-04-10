package com.facebook.crudolib.params;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
class SimpleIntArrayMap<K> {
    private Object[] mKeys;
    private int mSize;
    private int[] mValues;

    public SimpleIntArrayMap(int i) {
        if (i > 0) {
            this.mKeys = new Object[i];
            this.mValues = new int[i];
            this.mSize = 0;
            return;
        }
        throw new IllegalArgumentException("Non-positive capacity not allowed");
    }

    public int size() {
        return this.mSize;
    }

    public void put(K k, int i) {
        int indexOfKey = indexOfKey(k);
        if (indexOfKey >= 0) {
            this.mValues[indexOfKey] = i;
            return;
        }
        ensureCapacity(this.mSize + 1);
        Object[] objArr = this.mKeys;
        int i2 = this.mSize;
        objArr[i2] = k;
        this.mValues[i2] = i;
        this.mSize = i2 + 1;
    }

    public int get(K k, int i) {
        int indexOfKey = indexOfKey(k);
        return indexOfKey >= 0 ? this.mValues[indexOfKey] : i;
    }

    public int valueAt(int i) {
        return this.mValues[i];
    }

    public K keyAt(int i) {
        return (K) this.mKeys[i];
    }

    public int indexOfKey(K k) {
        for (int i = 0; i < this.mSize; i++) {
            if (this.mKeys[i].equals(k)) {
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity(int i) {
        int length = this.mKeys.length;
        if (length < i) {
            while (length < i) {
                length *= 2;
            }
            Object[] objArr = new Object[length];
            System.arraycopy(this.mKeys, 0, objArr, 0, this.mSize);
            this.mKeys = objArr;
            int[] iArr = new int[length];
            System.arraycopy(this.mValues, 0, iArr, 0, this.mSize);
            this.mValues = iArr;
        }
    }
}
