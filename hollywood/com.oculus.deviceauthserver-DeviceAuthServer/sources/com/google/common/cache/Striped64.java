package com.google.common.cache;

import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/* access modifiers changed from: package-private */
public abstract class Striped64 extends Number {
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final AtomicLongFieldUpdater<Striped64> baseUpdater = AtomicLongFieldUpdater.newUpdater(Striped64.class, "base");
    private static final AtomicIntegerFieldUpdater<Striped64> busyUpdater = AtomicIntegerFieldUpdater.newUpdater(Striped64.class, "busy");
    static final Random rng = new Random();
    static final ThreadLocal<int[]> threadHashCode = new ThreadLocal<>();
    volatile transient long base;
    volatile transient int busy;
    volatile transient Cell[] cells;

    /* access modifiers changed from: package-private */
    public abstract long fn(long j, long j2);

    /* access modifiers changed from: package-private */
    public static final class Cell {
        private static final AtomicLongFieldUpdater<Cell> valueUpdater = AtomicLongFieldUpdater.newUpdater(Cell.class, "value");
        volatile long p0;
        volatile long p1;
        volatile long p2;
        volatile long p3;
        volatile long p4;
        volatile long p5;
        volatile long p6;
        volatile long q0;
        volatile long q1;
        volatile long q2;
        volatile long q3;
        volatile long q4;
        volatile long q5;
        volatile long q6;
        volatile long value;

        Cell(long x) {
            this.value = x;
        }

        /* access modifiers changed from: package-private */
        public final boolean cas(long cmp, long val) {
            return valueUpdater.compareAndSet(this, cmp, val);
        }
    }

    Striped64() {
    }

    /* access modifiers changed from: package-private */
    public final boolean casBase(long cmp, long val) {
        return baseUpdater.compareAndSet(this, cmp, val);
    }

    /* access modifiers changed from: package-private */
    public final boolean casBusy() {
        return busyUpdater.compareAndSet(this, 0, 1);
    }

    /* JADX INFO: Multiple debug info for r0v1 com.google.common.cache.Striped64$Cell[]: [D('as' com.google.common.cache.Striped64$Cell[]), D('h' int)] */
    /* access modifiers changed from: package-private */
    public final void retryUpdate(long x, int[] hc, boolean wasUncontended) {
        int[] hc2;
        int r;
        int i;
        int h;
        int h2;
        int h3;
        int i2;
        Throwable th;
        int m;
        int i3 = 0;
        if (hc == null) {
            int i4 = 1;
            int[] iArr = new int[1];
            hc2 = iArr;
            threadHashCode.set(iArr);
            int r2 = rng.nextInt();
            if (r2 != 0) {
                i4 = r2;
            }
            hc2[i3] = i4;
            r = i4;
        } else {
            r = hc[i3];
            hc2 = hc;
        }
        boolean wasUncontended2 = wasUncontended;
        boolean collide = false;
        int h4 = r;
        while (true) {
            Cell[] as = this.cells;
            if (as != null) {
                int n = as.length;
                if (n > 0) {
                    Cell a = as[(n - 1) & h4];
                    if (a == null) {
                        if (this.busy == 0) {
                            Cell r3 = new Cell(x);
                            if (this.busy == 0 && casBusy()) {
                                boolean created = false;
                                try {
                                    Cell[] rs = this.cells;
                                    if (rs != null && (m = rs.length) > 0) {
                                        int j = (m - 1) & h4;
                                        if (rs[j] == null) {
                                            rs[j] = r3;
                                            created = true;
                                        }
                                    }
                                    if (created) {
                                        return;
                                    }
                                } finally {
                                    this.busy = i3;
                                }
                            }
                        }
                        collide = false;
                        h3 = h4;
                    } else if (!wasUncontended2) {
                        wasUncontended2 = true;
                        h3 = h4;
                    } else {
                        long v = a.value;
                        h3 = h4;
                        if (!a.cas(v, fn(v, x))) {
                            if (n >= NCPU || this.cells != as) {
                                collide = false;
                            } else if (!collide) {
                                collide = true;
                            } else if (this.busy == 0 && casBusy()) {
                                try {
                                    if (this.cells == as) {
                                        Cell[] rs2 = new Cell[(n << 1)];
                                        i2 = 0;
                                        try {
                                            System.arraycopy(as, 0, rs2, 0, n);
                                            this.cells = rs2;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            this.busy = i2;
                                            throw th;
                                        }
                                    }
                                    i3 = 0;
                                    this.busy = i3;
                                    collide = false;
                                    h4 = h3;
                                } catch (Throwable th3) {
                                    th = th3;
                                    i2 = 0;
                                    this.busy = i2;
                                    throw th;
                                }
                            }
                        } else {
                            return;
                        }
                    }
                    int h5 = h3 ^ (h3 << 13);
                    int h6 = h5 ^ (h5 >>> 17);
                    h = h6 ^ (h6 << 5);
                    hc2[0] = h;
                    i = 0;
                    i3 = i;
                    h4 = h;
                } else {
                    h2 = h4;
                }
            } else {
                h2 = h4;
            }
            if (this.busy == 0 && this.cells == as && casBusy()) {
                boolean init = false;
                try {
                    if (this.cells == as) {
                        Cell[] rs3 = new Cell[2];
                        rs3[h2 & 1] = new Cell(x);
                        this.cells = rs3;
                        init = true;
                    }
                    if (!init) {
                        i = 0;
                    } else {
                        return;
                    }
                } finally {
                    this.busy = 0;
                }
            } else {
                i = 0;
                long v2 = this.base;
                if (casBase(v2, fn(v2, x))) {
                    return;
                }
            }
            h = h2;
            i3 = i;
            h4 = h;
        }
    }

    /* access modifiers changed from: package-private */
    public final void internalReset(long initialValue) {
        Cell[] as = this.cells;
        this.base = initialValue;
        if (as != null) {
            for (Cell a : as) {
                if (a != null) {
                    a.value = initialValue;
                }
            }
        }
    }
}
