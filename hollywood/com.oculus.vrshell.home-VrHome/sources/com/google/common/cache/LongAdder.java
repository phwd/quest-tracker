package com.google.common.cache;

import com.google.common.cache.Striped64;
import java.io.Serializable;

/* access modifiers changed from: package-private */
public final class LongAdder extends Striped64 implements LongAddable, Serializable {
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
            uncontended = a.cas(v, v + x);
            if (uncontended) {
                return;
            }
        }
        retryUpdate(x, hc, uncontended);
    }

    @Override // com.google.common.cache.LongAddable
    public void increment() {
        add(1);
    }

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
}
