package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.cache.Striped64;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* access modifiers changed from: package-private */
@GwtCompatible
public final class LongAdder extends Striped64 implements LongAddable, Serializable {
    private static final long serialVersionUID = 7249069246863182397L;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.cache.Striped64
    public final long fn(long j, long j2) {
        return j + j2;
    }

    @Override // com.google.common.cache.LongAddable
    public void add(long j) {
        int length;
        Striped64.Cell cell;
        Striped64.Cell[] cellArr = this.cells;
        if (cellArr == null) {
            long j2 = this.base;
            if (casBase(j2, j2 + j)) {
                return;
            }
        }
        int[] iArr = (int[]) threadHashCode.get();
        boolean z = true;
        if (!(iArr == null || cellArr == null || (length = cellArr.length) < 1 || (cell = cellArr[(length - 1) & iArr[0]]) == null)) {
            long j3 = cell.value;
            z = cell.cas(j3, j3 + j);
            if (z) {
                return;
            }
        }
        retryUpdate(j, iArr, z);
    }

    @Override // com.google.common.cache.LongAddable
    public void increment() {
        add(1);
    }

    public long sum() {
        long j = this.base;
        Striped64.Cell[] cellArr = this.cells;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j += cell.value;
                }
            }
        }
        return j;
    }

    public String toString() {
        return Long.toString(sum());
    }

    public long longValue() {
        return sum();
    }

    public int intValue() {
        return (int) sum();
    }

    public float floatValue() {
        return (float) sum();
    }

    public double doubleValue() {
        return (double) sum();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeLong(sum());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.busy = 0;
        this.cells = null;
        this.base = objectInputStream.readLong();
    }
}
