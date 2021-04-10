package android.icu.impl;

import android.support.v4.view.PointerIconCompat;
import dalvik.bytecode.Opcodes;

public class CalendarCache {
    public static long EMPTY = Long.MIN_VALUE;
    private static final int[] primes = {61, 127, 509, PointerIconCompat.TYPE_GRABBING, 2039, 4093, Opcodes.OP_SPUT_BYTE_JUMBO, 16381, 32749, 65521, 131071, 262139};
    private int arraySize = primes[this.pIndex];
    private long[] keys;
    private int pIndex = 0;
    private int size = 0;
    private int threshold;
    private long[] values;

    public CalendarCache() {
        int i = this.arraySize;
        this.threshold = (i * 3) / 4;
        this.keys = new long[i];
        this.values = new long[i];
        makeArrays(i);
    }

    private void makeArrays(int newSize) {
        this.keys = new long[newSize];
        this.values = new long[newSize];
        for (int i = 0; i < newSize; i++) {
            this.values[i] = EMPTY;
        }
        this.arraySize = newSize;
        this.threshold = (int) (((double) this.arraySize) * 0.75d);
        this.size = 0;
    }

    public synchronized long get(long key) {
        return this.values[findIndex(key)];
    }

    public synchronized void put(long key, long value) {
        if (this.size >= this.threshold) {
            rehash();
        }
        int index = findIndex(key);
        this.keys[index] = key;
        this.values[index] = value;
        this.size++;
    }

    private final int findIndex(long key) {
        int index = hash(key);
        int delta = 0;
        while (this.values[index] != EMPTY && this.keys[index] != key) {
            if (delta == 0) {
                delta = hash2(key);
            }
            index = (index + delta) % this.arraySize;
        }
        return index;
    }

    private void rehash() {
        int oldSize = this.arraySize;
        long[] oldKeys = this.keys;
        long[] oldValues = this.values;
        int i = this.pIndex;
        int[] iArr = primes;
        if (i < iArr.length - 1) {
            int i2 = i + 1;
            this.pIndex = i2;
            this.arraySize = iArr[i2];
        } else {
            this.arraySize = (this.arraySize * 2) + 1;
        }
        this.size = 0;
        makeArrays(this.arraySize);
        for (int i3 = 0; i3 < oldSize; i3++) {
            if (oldValues[i3] != EMPTY) {
                put(oldKeys[i3], oldValues[i3]);
            }
        }
    }

    private final int hash(long key) {
        int i = this.arraySize;
        int h = (int) (((15821 * key) + 1) % ((long) i));
        if (h < 0) {
            return h + i;
        }
        return h;
    }

    private final int hash2(long key) {
        int i = this.arraySize;
        return (i - 2) - ((int) (key % ((long) (i - 2))));
    }
}
