package com.google.common.cache;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Random;
import sun.misc.Unsafe;

/* access modifiers changed from: package-private */
public abstract class Striped64 extends Number {
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final Unsafe UNSAFE;
    private static final long baseOffset;
    private static final long busyOffset;
    static final Random rng = new Random();
    static final ThreadLocal<int[]> threadHashCode = new ThreadLocal<>();
    volatile transient long base;
    volatile transient int busy;
    volatile transient Cell[] cells;

    /* access modifiers changed from: package-private */
    public abstract long fn(long j, long j2);

    /* access modifiers changed from: package-private */
    public static final class Cell {
        private static final Unsafe UNSAFE;
        private static final long valueOffset;
        volatile long value;

        Cell(long x) {
            this.value = x;
        }

        /* access modifiers changed from: package-private */
        public final boolean cas(long cmp, long val) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, cmp, val);
        }

        static {
            try {
                UNSAFE = Striped64.getUnsafe();
                valueOffset = UNSAFE.objectFieldOffset(Cell.class.getDeclaredField("value"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    static {
        try {
            UNSAFE = getUnsafe();
            baseOffset = UNSAFE.objectFieldOffset(Striped64.class.getDeclaredField("base"));
            busyOffset = UNSAFE.objectFieldOffset(Striped64.class.getDeclaredField("busy"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    Striped64() {
    }

    /* access modifiers changed from: package-private */
    public final boolean casBase(long cmp, long val) {
        return UNSAFE.compareAndSwapLong(this, baseOffset, cmp, val);
    }

    /* access modifiers changed from: package-private */
    public final boolean casBusy() {
        return UNSAFE.compareAndSwapInt(this, busyOffset, 0, 1);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public final void retryUpdate(long x, int[] hc, boolean wasUncontended) {
        int h;
        int n;
        int m;
        if (hc == null) {
            hc = new int[1];
            threadHashCode.set(hc);
            int r = rng.nextInt();
            if (r == 0) {
                h = 1;
            } else {
                h = r;
            }
            hc[0] = h;
        } else {
            h = hc[0];
        }
        boolean collide = false;
        while (true) {
            Cell[] as = this.cells;
            if (as != null && (n = as.length) > 0) {
                Cell a = as[(n - 1) & h];
                if (a == null) {
                    if (this.busy == 0) {
                        Cell r2 = new Cell(x);
                        if (this.busy == 0 && casBusy()) {
                            boolean created = false;
                            try {
                                Cell[] rs = this.cells;
                                if (rs != null && (m = rs.length) > 0) {
                                    int j = (m - 1) & h;
                                    if (rs[j] == null) {
                                        rs[j] = r2;
                                        created = true;
                                    }
                                }
                                if (created) {
                                    return;
                                }
                            } finally {
                                this.busy = 0;
                            }
                        }
                    }
                    collide = false;
                } else if (!wasUncontended) {
                    wasUncontended = true;
                } else {
                    long v = a.value;
                    if (a.cas(v, fn(v, x))) {
                        return;
                    }
                    if (n >= NCPU || this.cells != as) {
                        collide = false;
                    } else if (!collide) {
                        collide = true;
                    } else if (this.busy == 0 && casBusy()) {
                        try {
                            if (this.cells == as) {
                                Cell[] rs2 = new Cell[(n << 1)];
                                for (int i = 0; i < n; i++) {
                                    rs2[i] = as[i];
                                }
                                this.cells = rs2;
                            }
                            this.busy = 0;
                            collide = false;
                        } catch (Throwable th) {
                            this.busy = 0;
                            throw th;
                        }
                    }
                }
                int h2 = h ^ (h << 13);
                int h3 = h2 ^ (h2 >>> 17);
                h = h3 ^ (h3 << 5);
                hc[0] = h;
            } else if (this.busy == 0 && this.cells == as && casBusy()) {
                boolean init = false;
                try {
                    if (this.cells == as) {
                        Cell[] rs3 = new Cell[2];
                        rs3[h & 1] = new Cell(x);
                        this.cells = rs3;
                        init = true;
                    }
                    if (init) {
                        return;
                    }
                } finally {
                    this.busy = 0;
                }
            } else {
                long v2 = this.base;
                if (casBase(v2, fn(v2, x))) {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static Unsafe getUnsafe() {
        try {
            return Unsafe.getUnsafe();
        } catch (SecurityException e) {
            try {
                return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() {
                    /* class com.google.common.cache.Striped64.AnonymousClass1 */

                    @Override // java.security.PrivilegedExceptionAction
                    public Unsafe run() throws Exception {
                        Field[] declaredFields = Unsafe.class.getDeclaredFields();
                        for (Field f : declaredFields) {
                            f.setAccessible(true);
                            Object x = f.get(null);
                            if (Unsafe.class.isInstance(x)) {
                                return (Unsafe) Unsafe.class.cast(x);
                            }
                        }
                        throw new NoSuchFieldError("the Unsafe");
                    }
                });
            } catch (PrivilegedActionException e2) {
                throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
            }
        }
    }
}
