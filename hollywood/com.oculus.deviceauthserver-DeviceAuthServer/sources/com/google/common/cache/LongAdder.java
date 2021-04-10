package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.cache.Striped64;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
public final class LongAdder extends Striped64 implements Serializable, LongAddable {
    private static final long serialVersionUID = 7249069246863182397L;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.cache.Striped64
    public final long fn(long v, long x) {
        return v + x;
    }

    @Override // com.google.common.cache.LongAddable
    public void add(long x) {
        int n;
        Striped64.Cell a;
        Striped64.Cell[] as = this.cells;
        if (as == null) {
            long b = this.base;
            if (casBase(b, b + x)) {
                return;
            }
        }
        boolean uncontended = true;
        int[] hc = (int[]) threadHashCode.get();
        if (!(hc == null || as == null || (n = as.length) < 1 || (a = as[(n - 1) & hc[0]]) == null)) {
            long v = a.value;
            boolean cas = a.cas(v, v + x);
            uncontended = cas;
            if (cas) {
                return;
            }
        }
        retryUpdate(x, hc, uncontended);
    }

    @Override // com.google.common.cache.LongAddable
    public void increment() {
        add(1);
    }

    public void decrement() {
        add(-1);
    }

    @Override // com.google.common.cache.LongAddable
    public long sum() {
        long sum = this.base;
        Striped64.Cell[] as = this.cells;
        if (as != null) {
            for (Striped64.Cell a : as) {
                if (a != null) {
                    sum += a.value;
                }
            }
        }
        return sum;
    }

    public void reset() {
        internalReset(0);
    }

    public long sumThenReset() {
        long sum = this.base;
        Striped64.Cell[] as = this.cells;
        this.base = 0;
        if (as != null) {
            for (Striped64.Cell a : as) {
                if (a != null) {
                    sum += a.value;
                    a.value = 0;
                }
            }
        }
        return sum;
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

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeLong(sum());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.busy = 0;
        this.cells = null;
        this.base = s.readLong();
    }
}
