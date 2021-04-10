package java.util;

import java.util.concurrent.CountedCompleter;

class ArraysParallelSortHelpers {
    ArraysParallelSortHelpers() {
    }

    static final class EmptyCompleter extends CountedCompleter<Void> {
        static final long serialVersionUID = 2446542900576103244L;

        EmptyCompleter(CountedCompleter<?> p) {
            super(p);
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
        }
    }

    static final class Relay extends CountedCompleter<Void> {
        static final long serialVersionUID = 2446542900576103244L;
        final CountedCompleter<?> task;

        Relay(CountedCompleter<?> task2) {
            super(null, 1);
            this.task = task2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void onCompletion(CountedCompleter<?> countedCompleter) {
            this.task.compute();
        }
    }

    static final class FJObject {
        FJObject() {
        }

        /* access modifiers changed from: package-private */
        public static final class Sorter<T> extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final T[] a;
            final int base;
            Comparator<? super T> comparator;
            final int gran;
            final int size;
            final T[] w;
            final int wbase;

            Sorter(CountedCompleter<?> par, T[] a2, T[] w2, int base2, int size2, int wbase2, int gran2, Comparator<? super T> comparator2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.base = base2;
                this.size = size2;
                this.wbase = wbase2;
                this.gran = gran2;
                this.comparator = comparator2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                Comparator<? super T> c = this.comparator;
                T[] a2 = this.a;
                T[] w2 = this.w;
                int b = this.base;
                int n = this.size;
                int wb = this.wbase;
                int g = this.gran;
                CountedCompleter<?> s = this;
                int n2 = n;
                while (n2 > g) {
                    int h = n2 >>> 1;
                    int q = h >>> 1;
                    int u = h + q;
                    Relay fc = new Relay(new Merger(s, w2, a2, wb, h, wb + h, n2 - h, b, g, c));
                    Relay rc = new Relay(new Merger(fc, a2, w2, b + h, q, b + u, n2 - u, wb + h, g, c));
                    new Sorter(rc, a2, w2, b + u, n2 - u, wb + u, g, c).fork();
                    new Sorter(rc, a2, w2, b + h, q, wb + h, g, c).fork();
                    Relay bc = new Relay(new Merger(fc, a2, w2, b, q, b + q, h - q, wb, g, c));
                    new Sorter(bc, a2, w2, b + q, h - q, wb + q, g, c).fork();
                    s = new EmptyCompleter(bc);
                    n2 = q;
                    g = g;
                    wb = wb;
                    b = b;
                    w2 = w2;
                    a2 = a2;
                    c = c;
                }
                TimSort.sort(a2, b, b + n2, c, w2, wb, n2);
                s.tryComplete();
            }
        }

        static final class Merger<T> extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final T[] a;
            Comparator<? super T> comparator;
            final int gran;
            final int lbase;
            final int lsize;
            final int rbase;
            final int rsize;
            final T[] w;
            final int wbase;

            Merger(CountedCompleter<?> par, T[] a2, T[] w2, int lbase2, int lsize2, int rbase2, int rsize2, int wbase2, int gran2, Comparator<? super T> comparator2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.lbase = lbase2;
                this.lsize = lsize2;
                this.rbase = rbase2;
                this.rsize = rsize2;
                this.wbase = wbase2;
                this.gran = gran2;
                this.comparator = comparator2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                int rh;
                int lh;
                T t;
                Comparator<? super T> c = this.comparator;
                T[] a2 = this.a;
                T[] w2 = this.w;
                int lb = this.lbase;
                int ln = this.lsize;
                int rb = this.rbase;
                int rn = this.rsize;
                int k = this.wbase;
                int g = this.gran;
                if (a2 == null || w2 == null || lb < 0 || rb < 0 || k < 0 || c == null) {
                    throw new IllegalStateException();
                }
                int ln2 = ln;
                int rn2 = rn;
                while (true) {
                    int i = 1;
                    if (ln2 >= rn2) {
                        if (ln2 <= g) {
                            break;
                        }
                        int rh2 = rn2;
                        int lh2 = ln2 >>> 1;
                        T split = a2[lh2 + lb];
                        int lo = 0;
                        while (lo < rh2) {
                            int rm = (lo + rh2) >>> i;
                            if (c.compare(split, a2[rm + rb]) <= 0) {
                                rh2 = rm;
                            } else {
                                lo = rm + 1;
                            }
                            i = 1;
                        }
                        rh = rh2;
                        lh = lh2;
                        Merger<T> m = new Merger<>(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g, c);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m.fork();
                        g = g;
                        k = k;
                        a2 = a2;
                        rb = rb;
                    } else if (rn2 <= g) {
                        break;
                    } else {
                        int lh3 = ln2;
                        int rh3 = rn2 >>> 1;
                        T split2 = a2[rh3 + rb];
                        int lo2 = 0;
                        while (lo2 < lh3) {
                            int lm = (lo2 + lh3) >>> 1;
                            if (c.compare(split2, a2[lm + lb]) <= 0) {
                                lh3 = lm;
                            } else {
                                lo2 = lm + 1;
                            }
                        }
                        lh = lh3;
                        rh = rh3;
                        Merger<T> m2 = new Merger<>(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g, c);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m2.fork();
                        g = g;
                        k = k;
                        a2 = a2;
                        rb = rb;
                    }
                }
                int lf = lb + ln2;
                int rf = rb + rn2;
                while (lb < lf && rb < rf) {
                    T al = a2[lb];
                    T ar = a2[rb];
                    if (c.compare(al, ar) <= 0) {
                        lb++;
                        t = al;
                    } else {
                        rb++;
                        t = ar;
                    }
                    w2[k] = t;
                    k++;
                }
                if (rb < rf) {
                    System.arraycopy(a2, rb, w2, k, rf - rb);
                } else if (lb < lf) {
                    System.arraycopy(a2, lb, w2, k, lf - lb);
                }
                tryComplete();
            }
        }
    }

    static final class FJByte {
        FJByte() {
        }

        static final class Sorter extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final byte[] a;
            final int base;
            final int gran;
            final int size;
            final byte[] w;
            final int wbase;

            Sorter(CountedCompleter<?> par, byte[] a2, byte[] w2, int base2, int size2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.base = base2;
                this.size = size2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                byte[] a2 = this.a;
                byte[] w2 = this.w;
                int b = this.base;
                int n = this.size;
                int wb = this.wbase;
                int g = this.gran;
                CountedCompleter<?> s = this;
                int n2 = n;
                while (n2 > g) {
                    int h = n2 >>> 1;
                    int q = h >>> 1;
                    int u = h + q;
                    Relay fc = new Relay(new Merger(s, w2, a2, wb, h, wb + h, n2 - h, b, g));
                    Relay rc = new Relay(new Merger(fc, a2, w2, b + h, q, b + u, n2 - u, wb + h, g));
                    new Sorter(rc, a2, w2, b + u, n2 - u, wb + u, g).fork();
                    new Sorter(rc, a2, w2, b + h, q, wb + h, g).fork();
                    Relay bc = new Relay(new Merger(fc, a2, w2, b, q, b + q, h - q, wb, g));
                    new Sorter(bc, a2, w2, b + q, h - q, wb + q, g).fork();
                    s = new EmptyCompleter(bc);
                    n2 = q;
                    b = b;
                    wb = wb;
                    w2 = w2;
                    g = g;
                    a2 = a2;
                }
                DualPivotQuicksort.sort(a2, b, (b + n2) - 1);
                s.tryComplete();
            }
        }

        static final class Merger extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final byte[] a;
            final int gran;
            final int lbase;
            final int lsize;
            final int rbase;
            final int rsize;
            final byte[] w;
            final int wbase;

            Merger(CountedCompleter<?> par, byte[] a2, byte[] w2, int lbase2, int lsize2, int rbase2, int rsize2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.lbase = lbase2;
                this.lsize = lsize2;
                this.rbase = rbase2;
                this.rsize = rsize2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                int rh;
                int lh;
                byte t;
                byte[] a2 = this.a;
                byte[] w2 = this.w;
                int lb = this.lbase;
                int ln = this.lsize;
                int rb = this.rbase;
                int rn = this.rsize;
                int k = this.wbase;
                int g = this.gran;
                if (a2 == null || w2 == null || lb < 0 || rb < 0 || k < 0) {
                    throw new IllegalStateException();
                }
                int ln2 = ln;
                int rn2 = rn;
                while (true) {
                    if (ln2 >= rn2) {
                        if (ln2 <= g) {
                            break;
                        }
                        int rh2 = rn2;
                        int lh2 = ln2 >>> 1;
                        byte split = a2[lh2 + lb];
                        int lo = 0;
                        while (lo < rh2) {
                            int rm = (lo + rh2) >>> 1;
                            if (split <= a2[rm + rb]) {
                                rh2 = rm;
                            } else {
                                lo = rm + 1;
                            }
                        }
                        rh = rh2;
                        lh = lh2;
                        Merger m = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m.fork();
                        a2 = a2;
                    } else if (rn2 <= g) {
                        break;
                    } else {
                        int lh3 = ln2;
                        int rh3 = rn2 >>> 1;
                        byte split2 = a2[rh3 + rb];
                        int lo2 = 0;
                        while (lo2 < lh3) {
                            int lm = (lo2 + lh3) >>> 1;
                            if (split2 <= a2[lm + lb]) {
                                lh3 = lm;
                            } else {
                                lo2 = lm + 1;
                            }
                        }
                        lh = lh3;
                        rh = rh3;
                        Merger m2 = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m2.fork();
                        a2 = a2;
                    }
                }
                int lf = lb + ln2;
                int rf = rb + rn2;
                while (lb < lf && rb < rf) {
                    byte al = a2[lb];
                    byte ar = a2[rb];
                    if (al <= ar) {
                        lb++;
                        t = al;
                    } else {
                        rb++;
                        t = ar;
                    }
                    w2[k] = t;
                    k++;
                }
                if (rb < rf) {
                    System.arraycopy(a2, rb, w2, k, rf - rb);
                } else if (lb < lf) {
                    System.arraycopy(a2, lb, w2, k, lf - lb);
                }
                tryComplete();
            }
        }
    }

    static final class FJChar {
        FJChar() {
        }

        static final class Sorter extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final char[] a;
            final int base;
            final int gran;
            final int size;
            final char[] w;
            final int wbase;

            Sorter(CountedCompleter<?> par, char[] a2, char[] w2, int base2, int size2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.base = base2;
                this.size = size2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                char[] a2 = this.a;
                char[] w2 = this.w;
                int b = this.base;
                int n = this.size;
                int wb = this.wbase;
                int g = this.gran;
                CountedCompleter<?> s = this;
                int n2 = n;
                while (n2 > g) {
                    int h = n2 >>> 1;
                    int q = h >>> 1;
                    int u = h + q;
                    Relay fc = new Relay(new Merger(s, w2, a2, wb, h, wb + h, n2 - h, b, g));
                    Relay rc = new Relay(new Merger(fc, a2, w2, b + h, q, b + u, n2 - u, wb + h, g));
                    new Sorter(rc, a2, w2, b + u, n2 - u, wb + u, g).fork();
                    new Sorter(rc, a2, w2, b + h, q, wb + h, g).fork();
                    Relay bc = new Relay(new Merger(fc, a2, w2, b, q, b + q, h - q, wb, g));
                    new Sorter(bc, a2, w2, b + q, h - q, wb + q, g).fork();
                    s = new EmptyCompleter(bc);
                    n2 = q;
                    w2 = w2;
                    g = g;
                    wb = wb;
                    b = b;
                    a2 = a2;
                }
                DualPivotQuicksort.sort(a2, b, (b + n2) - 1, w2, wb, n2);
                s.tryComplete();
            }
        }

        static final class Merger extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final char[] a;
            final int gran;
            final int lbase;
            final int lsize;
            final int rbase;
            final int rsize;
            final char[] w;
            final int wbase;

            Merger(CountedCompleter<?> par, char[] a2, char[] w2, int lbase2, int lsize2, int rbase2, int rsize2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.lbase = lbase2;
                this.lsize = lsize2;
                this.rbase = rbase2;
                this.rsize = rsize2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                int rh;
                int lh;
                char t;
                char[] a2 = this.a;
                char[] w2 = this.w;
                int lb = this.lbase;
                int ln = this.lsize;
                int rb = this.rbase;
                int rn = this.rsize;
                int k = this.wbase;
                int g = this.gran;
                if (a2 == null || w2 == null || lb < 0 || rb < 0 || k < 0) {
                    throw new IllegalStateException();
                }
                int ln2 = ln;
                int rn2 = rn;
                while (true) {
                    if (ln2 >= rn2) {
                        if (ln2 <= g) {
                            break;
                        }
                        int rh2 = rn2;
                        int lh2 = ln2 >>> 1;
                        char split = a2[lh2 + lb];
                        int lo = 0;
                        while (lo < rh2) {
                            int rm = (lo + rh2) >>> 1;
                            if (split <= a2[rm + rb]) {
                                rh2 = rm;
                            } else {
                                lo = rm + 1;
                            }
                        }
                        rh = rh2;
                        lh = lh2;
                        Merger m = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m.fork();
                        a2 = a2;
                    } else if (rn2 <= g) {
                        break;
                    } else {
                        int lh3 = ln2;
                        int rh3 = rn2 >>> 1;
                        char split2 = a2[rh3 + rb];
                        int lo2 = 0;
                        while (lo2 < lh3) {
                            int lm = (lo2 + lh3) >>> 1;
                            if (split2 <= a2[lm + lb]) {
                                lh3 = lm;
                            } else {
                                lo2 = lm + 1;
                            }
                        }
                        lh = lh3;
                        rh = rh3;
                        Merger m2 = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m2.fork();
                        a2 = a2;
                    }
                }
                int lf = lb + ln2;
                int rf = rb + rn2;
                while (lb < lf && rb < rf) {
                    char al = a2[lb];
                    char ar = a2[rb];
                    if (al <= ar) {
                        lb++;
                        t = al;
                    } else {
                        rb++;
                        t = ar;
                    }
                    w2[k] = t;
                    k++;
                }
                if (rb < rf) {
                    System.arraycopy((Object) a2, rb, (Object) w2, k, rf - rb);
                } else if (lb < lf) {
                    System.arraycopy((Object) a2, lb, (Object) w2, k, lf - lb);
                }
                tryComplete();
            }
        }
    }

    static final class FJShort {
        FJShort() {
        }

        static final class Sorter extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final short[] a;
            final int base;
            final int gran;
            final int size;
            final short[] w;
            final int wbase;

            Sorter(CountedCompleter<?> par, short[] a2, short[] w2, int base2, int size2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.base = base2;
                this.size = size2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                short[] a2 = this.a;
                short[] w2 = this.w;
                int b = this.base;
                int n = this.size;
                int wb = this.wbase;
                int g = this.gran;
                CountedCompleter<?> s = this;
                int n2 = n;
                while (n2 > g) {
                    int h = n2 >>> 1;
                    int q = h >>> 1;
                    int u = h + q;
                    Relay fc = new Relay(new Merger(s, w2, a2, wb, h, wb + h, n2 - h, b, g));
                    Relay rc = new Relay(new Merger(fc, a2, w2, b + h, q, b + u, n2 - u, wb + h, g));
                    new Sorter(rc, a2, w2, b + u, n2 - u, wb + u, g).fork();
                    new Sorter(rc, a2, w2, b + h, q, wb + h, g).fork();
                    Relay bc = new Relay(new Merger(fc, a2, w2, b, q, b + q, h - q, wb, g));
                    new Sorter(bc, a2, w2, b + q, h - q, wb + q, g).fork();
                    s = new EmptyCompleter(bc);
                    n2 = q;
                    w2 = w2;
                    g = g;
                    wb = wb;
                    b = b;
                    a2 = a2;
                }
                DualPivotQuicksort.sort(a2, b, (b + n2) - 1, w2, wb, n2);
                s.tryComplete();
            }
        }

        static final class Merger extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final short[] a;
            final int gran;
            final int lbase;
            final int lsize;
            final int rbase;
            final int rsize;
            final short[] w;
            final int wbase;

            Merger(CountedCompleter<?> par, short[] a2, short[] w2, int lbase2, int lsize2, int rbase2, int rsize2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.lbase = lbase2;
                this.lsize = lsize2;
                this.rbase = rbase2;
                this.rsize = rsize2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                int rh;
                int lh;
                short t;
                short[] a2 = this.a;
                short[] w2 = this.w;
                int lb = this.lbase;
                int ln = this.lsize;
                int rb = this.rbase;
                int rn = this.rsize;
                int k = this.wbase;
                int g = this.gran;
                if (a2 == null || w2 == null || lb < 0 || rb < 0 || k < 0) {
                    throw new IllegalStateException();
                }
                int ln2 = ln;
                int rn2 = rn;
                while (true) {
                    if (ln2 >= rn2) {
                        if (ln2 <= g) {
                            break;
                        }
                        int rh2 = rn2;
                        int lh2 = ln2 >>> 1;
                        short split = a2[lh2 + lb];
                        int lo = 0;
                        while (lo < rh2) {
                            int rm = (lo + rh2) >>> 1;
                            if (split <= a2[rm + rb]) {
                                rh2 = rm;
                            } else {
                                lo = rm + 1;
                            }
                        }
                        rh = rh2;
                        lh = lh2;
                        Merger m = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m.fork();
                        a2 = a2;
                    } else if (rn2 <= g) {
                        break;
                    } else {
                        int lh3 = ln2;
                        int rh3 = rn2 >>> 1;
                        short split2 = a2[rh3 + rb];
                        int lo2 = 0;
                        while (lo2 < lh3) {
                            int lm = (lo2 + lh3) >>> 1;
                            if (split2 <= a2[lm + lb]) {
                                lh3 = lm;
                            } else {
                                lo2 = lm + 1;
                            }
                        }
                        lh = lh3;
                        rh = rh3;
                        Merger m2 = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m2.fork();
                        a2 = a2;
                    }
                }
                int lf = lb + ln2;
                int rf = rb + rn2;
                while (lb < lf && rb < rf) {
                    short al = a2[lb];
                    short ar = a2[rb];
                    if (al <= ar) {
                        lb++;
                        t = al;
                    } else {
                        rb++;
                        t = ar;
                    }
                    w2[k] = t;
                    k++;
                }
                if (rb < rf) {
                    System.arraycopy((Object) a2, rb, (Object) w2, k, rf - rb);
                } else if (lb < lf) {
                    System.arraycopy((Object) a2, lb, (Object) w2, k, lf - lb);
                }
                tryComplete();
            }
        }
    }

    static final class FJInt {
        FJInt() {
        }

        /* access modifiers changed from: package-private */
        public static final class Sorter extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final int[] a;
            final int base;
            final int gran;
            final int size;
            final int[] w;
            final int wbase;

            Sorter(CountedCompleter<?> par, int[] a2, int[] w2, int base2, int size2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.base = base2;
                this.size = size2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                int[] a2 = this.a;
                int[] w2 = this.w;
                int b = this.base;
                int n = this.size;
                int wb = this.wbase;
                int g = this.gran;
                CountedCompleter<?> s = this;
                int n2 = n;
                while (n2 > g) {
                    int h = n2 >>> 1;
                    int q = h >>> 1;
                    int u = h + q;
                    Relay fc = new Relay(new Merger(s, w2, a2, wb, h, wb + h, n2 - h, b, g));
                    Relay rc = new Relay(new Merger(fc, a2, w2, b + h, q, b + u, n2 - u, wb + h, g));
                    new Sorter(rc, a2, w2, b + u, n2 - u, wb + u, g).fork();
                    new Sorter(rc, a2, w2, b + h, q, wb + h, g).fork();
                    Relay bc = new Relay(new Merger(fc, a2, w2, b, q, b + q, h - q, wb, g));
                    new Sorter(bc, a2, w2, b + q, h - q, wb + q, g).fork();
                    s = new EmptyCompleter(bc);
                    n2 = q;
                    w2 = w2;
                    g = g;
                    wb = wb;
                    b = b;
                    a2 = a2;
                }
                DualPivotQuicksort.sort(a2, b, (b + n2) - 1, w2, wb, n2);
                s.tryComplete();
            }
        }

        static final class Merger extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final int[] a;
            final int gran;
            final int lbase;
            final int lsize;
            final int rbase;
            final int rsize;
            final int[] w;
            final int wbase;

            Merger(CountedCompleter<?> par, int[] a2, int[] w2, int lbase2, int lsize2, int rbase2, int rsize2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.lbase = lbase2;
                this.lsize = lsize2;
                this.rbase = rbase2;
                this.rsize = rsize2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                int rh;
                int lh;
                int t;
                int[] a2 = this.a;
                int[] w2 = this.w;
                int lb = this.lbase;
                int ln = this.lsize;
                int rb = this.rbase;
                int rn = this.rsize;
                int k = this.wbase;
                int g = this.gran;
                if (a2 == null || w2 == null || lb < 0 || rb < 0 || k < 0) {
                    throw new IllegalStateException();
                }
                int ln2 = ln;
                int rn2 = rn;
                while (true) {
                    if (ln2 >= rn2) {
                        if (ln2 <= g) {
                            break;
                        }
                        int rh2 = rn2;
                        int lh2 = ln2 >>> 1;
                        int split = a2[lh2 + lb];
                        int lo = 0;
                        while (lo < rh2) {
                            int rm = (lo + rh2) >>> 1;
                            if (split <= a2[rm + rb]) {
                                rh2 = rm;
                            } else {
                                lo = rm + 1;
                            }
                        }
                        rh = rh2;
                        lh = lh2;
                        Merger m = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m.fork();
                        a2 = a2;
                    } else if (rn2 <= g) {
                        break;
                    } else {
                        int lh3 = ln2;
                        int rh3 = rn2 >>> 1;
                        int split2 = a2[rh3 + rb];
                        int lo2 = 0;
                        while (lo2 < lh3) {
                            int lm = (lo2 + lh3) >>> 1;
                            if (split2 <= a2[lm + lb]) {
                                lh3 = lm;
                            } else {
                                lo2 = lm + 1;
                            }
                        }
                        lh = lh3;
                        rh = rh3;
                        Merger m2 = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m2.fork();
                        a2 = a2;
                    }
                }
                int lf = lb + ln2;
                int rf = rb + rn2;
                while (lb < lf && rb < rf) {
                    int al = a2[lb];
                    int ar = a2[rb];
                    if (al <= ar) {
                        lb++;
                        t = al;
                    } else {
                        rb++;
                        t = ar;
                    }
                    w2[k] = t;
                    k++;
                }
                if (rb < rf) {
                    System.arraycopy((Object) a2, rb, (Object) w2, k, rf - rb);
                } else if (lb < lf) {
                    System.arraycopy((Object) a2, lb, (Object) w2, k, lf - lb);
                }
                tryComplete();
            }
        }
    }

    static final class FJLong {
        FJLong() {
        }

        /* access modifiers changed from: package-private */
        public static final class Sorter extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final long[] a;
            final int base;
            final int gran;
            final int size;
            final long[] w;
            final int wbase;

            Sorter(CountedCompleter<?> par, long[] a2, long[] w2, int base2, int size2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.base = base2;
                this.size = size2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                long[] a2 = this.a;
                long[] w2 = this.w;
                int b = this.base;
                int n = this.size;
                int wb = this.wbase;
                int g = this.gran;
                CountedCompleter<?> s = this;
                int n2 = n;
                while (n2 > g) {
                    int h = n2 >>> 1;
                    int q = h >>> 1;
                    int u = h + q;
                    Relay fc = new Relay(new Merger(s, w2, a2, wb, h, wb + h, n2 - h, b, g));
                    Relay rc = new Relay(new Merger(fc, a2, w2, b + h, q, b + u, n2 - u, wb + h, g));
                    new Sorter(rc, a2, w2, b + u, n2 - u, wb + u, g).fork();
                    new Sorter(rc, a2, w2, b + h, q, wb + h, g).fork();
                    Relay bc = new Relay(new Merger(fc, a2, w2, b, q, b + q, h - q, wb, g));
                    new Sorter(bc, a2, w2, b + q, h - q, wb + q, g).fork();
                    s = new EmptyCompleter(bc);
                    n2 = q;
                    w2 = w2;
                    g = g;
                    wb = wb;
                    b = b;
                    a2 = a2;
                }
                DualPivotQuicksort.sort(a2, b, (b + n2) - 1, w2, wb, n2);
                s.tryComplete();
            }
        }

        static final class Merger extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final long[] a;
            final int gran;
            final int lbase;
            final int lsize;
            final int rbase;
            final int rsize;
            final long[] w;
            final int wbase;

            Merger(CountedCompleter<?> par, long[] a2, long[] w2, int lbase2, int lsize2, int rbase2, int rsize2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.lbase = lbase2;
                this.lsize = lsize2;
                this.rbase = rbase2;
                this.rsize = rsize2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                int rh;
                int lh;
                long t;
                long[] a2 = this.a;
                long[] w2 = this.w;
                int lb = this.lbase;
                int ln = this.lsize;
                int rb = this.rbase;
                int rn = this.rsize;
                int k = this.wbase;
                int g = this.gran;
                if (a2 == null || w2 == null || lb < 0 || rb < 0 || k < 0) {
                    throw new IllegalStateException();
                }
                int ln2 = ln;
                int rn2 = rn;
                while (true) {
                    if (ln2 >= rn2) {
                        if (ln2 <= g) {
                            break;
                        }
                        int rh2 = rn2;
                        int lh2 = ln2 >>> 1;
                        long split = a2[lh2 + lb];
                        int lo = 0;
                        while (lo < rh2) {
                            int rm = (lo + rh2) >>> 1;
                            if (split <= a2[rm + rb]) {
                                rh2 = rm;
                            } else {
                                lo = rm + 1;
                            }
                        }
                        rh = rh2;
                        lh = lh2;
                        Merger m = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m.fork();
                        a2 = a2;
                    } else if (rn2 <= g) {
                        break;
                    } else {
                        int lh3 = ln2;
                        int rh3 = rn2 >>> 1;
                        long split2 = a2[rh3 + rb];
                        int lo2 = 0;
                        while (lo2 < lh3) {
                            int lm = (lo2 + lh3) >>> 1;
                            if (split2 <= a2[lm + lb]) {
                                lh3 = lm;
                            } else {
                                lo2 = lm + 1;
                            }
                        }
                        lh = lh3;
                        rh = rh3;
                        Merger m2 = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m2.fork();
                        a2 = a2;
                    }
                }
                int lf = lb + ln2;
                int rf = rb + rn2;
                while (lb < lf && rb < rf) {
                    long al = a2[lb];
                    long ar = a2[rb];
                    if (al <= ar) {
                        lb++;
                        t = al;
                    } else {
                        rb++;
                        t = ar;
                    }
                    w2[k] = t;
                    k++;
                }
                if (rb < rf) {
                    System.arraycopy((Object) a2, rb, (Object) w2, k, rf - rb);
                } else if (lb < lf) {
                    System.arraycopy((Object) a2, lb, (Object) w2, k, lf - lb);
                }
                tryComplete();
            }
        }
    }

    static final class FJFloat {
        FJFloat() {
        }

        static final class Sorter extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final float[] a;
            final int base;
            final int gran;
            final int size;
            final float[] w;
            final int wbase;

            Sorter(CountedCompleter<?> par, float[] a2, float[] w2, int base2, int size2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.base = base2;
                this.size = size2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                float[] a2 = this.a;
                float[] w2 = this.w;
                int b = this.base;
                int n = this.size;
                int wb = this.wbase;
                int g = this.gran;
                CountedCompleter<?> s = this;
                int n2 = n;
                while (n2 > g) {
                    int h = n2 >>> 1;
                    int q = h >>> 1;
                    int u = h + q;
                    Relay fc = new Relay(new Merger(s, w2, a2, wb, h, wb + h, n2 - h, b, g));
                    Relay rc = new Relay(new Merger(fc, a2, w2, b + h, q, b + u, n2 - u, wb + h, g));
                    new Sorter(rc, a2, w2, b + u, n2 - u, wb + u, g).fork();
                    new Sorter(rc, a2, w2, b + h, q, wb + h, g).fork();
                    Relay bc = new Relay(new Merger(fc, a2, w2, b, q, b + q, h - q, wb, g));
                    new Sorter(bc, a2, w2, b + q, h - q, wb + q, g).fork();
                    s = new EmptyCompleter(bc);
                    n2 = q;
                    w2 = w2;
                    g = g;
                    wb = wb;
                    b = b;
                    a2 = a2;
                }
                DualPivotQuicksort.sort(a2, b, (b + n2) - 1, w2, wb, n2);
                s.tryComplete();
            }
        }

        static final class Merger extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final float[] a;
            final int gran;
            final int lbase;
            final int lsize;
            final int rbase;
            final int rsize;
            final float[] w;
            final int wbase;

            Merger(CountedCompleter<?> par, float[] a2, float[] w2, int lbase2, int lsize2, int rbase2, int rsize2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.lbase = lbase2;
                this.lsize = lsize2;
                this.rbase = rbase2;
                this.rsize = rsize2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                int rh;
                int lh;
                float t;
                float[] a2 = this.a;
                float[] w2 = this.w;
                int lb = this.lbase;
                int ln = this.lsize;
                int rb = this.rbase;
                int rn = this.rsize;
                int k = this.wbase;
                int g = this.gran;
                if (a2 == null || w2 == null || lb < 0 || rb < 0 || k < 0) {
                    throw new IllegalStateException();
                }
                int ln2 = ln;
                int rn2 = rn;
                while (true) {
                    if (ln2 >= rn2) {
                        if (ln2 <= g) {
                            break;
                        }
                        int rh2 = rn2;
                        int lh2 = ln2 >>> 1;
                        float split = a2[lh2 + lb];
                        int lo = 0;
                        while (lo < rh2) {
                            int rm = (lo + rh2) >>> 1;
                            if (split <= a2[rm + rb]) {
                                rh2 = rm;
                            } else {
                                lo = rm + 1;
                            }
                        }
                        rh = rh2;
                        lh = lh2;
                        Merger m = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m.fork();
                        a2 = a2;
                    } else if (rn2 <= g) {
                        break;
                    } else {
                        int lh3 = ln2;
                        int rh3 = rn2 >>> 1;
                        float split2 = a2[rh3 + rb];
                        int lo2 = 0;
                        while (lo2 < lh3) {
                            int lm = (lo2 + lh3) >>> 1;
                            if (split2 <= a2[lm + lb]) {
                                lh3 = lm;
                            } else {
                                lo2 = lm + 1;
                            }
                        }
                        lh = lh3;
                        rh = rh3;
                        Merger m2 = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m2.fork();
                        a2 = a2;
                    }
                }
                int lf = lb + ln2;
                int rf = rb + rn2;
                while (lb < lf && rb < rf) {
                    float al = a2[lb];
                    float ar = a2[rb];
                    if (al <= ar) {
                        lb++;
                        t = al;
                    } else {
                        rb++;
                        t = ar;
                    }
                    w2[k] = t;
                    k++;
                }
                if (rb < rf) {
                    System.arraycopy((Object) a2, rb, (Object) w2, k, rf - rb);
                } else if (lb < lf) {
                    System.arraycopy((Object) a2, lb, (Object) w2, k, lf - lb);
                }
                tryComplete();
            }
        }
    }

    static final class FJDouble {
        FJDouble() {
        }

        /* access modifiers changed from: package-private */
        public static final class Sorter extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final double[] a;
            final int base;
            final int gran;
            final int size;
            final double[] w;
            final int wbase;

            Sorter(CountedCompleter<?> par, double[] a2, double[] w2, int base2, int size2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.base = base2;
                this.size = size2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                double[] a2 = this.a;
                double[] w2 = this.w;
                int b = this.base;
                int n = this.size;
                int wb = this.wbase;
                int g = this.gran;
                CountedCompleter<?> s = this;
                int n2 = n;
                while (n2 > g) {
                    int h = n2 >>> 1;
                    int q = h >>> 1;
                    int u = h + q;
                    Relay fc = new Relay(new Merger(s, w2, a2, wb, h, wb + h, n2 - h, b, g));
                    Relay rc = new Relay(new Merger(fc, a2, w2, b + h, q, b + u, n2 - u, wb + h, g));
                    new Sorter(rc, a2, w2, b + u, n2 - u, wb + u, g).fork();
                    new Sorter(rc, a2, w2, b + h, q, wb + h, g).fork();
                    Relay bc = new Relay(new Merger(fc, a2, w2, b, q, b + q, h - q, wb, g));
                    new Sorter(bc, a2, w2, b + q, h - q, wb + q, g).fork();
                    s = new EmptyCompleter(bc);
                    n2 = q;
                    w2 = w2;
                    g = g;
                    wb = wb;
                    b = b;
                    a2 = a2;
                }
                DualPivotQuicksort.sort(a2, b, (b + n2) - 1, w2, wb, n2);
                s.tryComplete();
            }
        }

        static final class Merger extends CountedCompleter<Void> {
            static final long serialVersionUID = 2446542900576103244L;
            final double[] a;
            final int gran;
            final int lbase;
            final int lsize;
            final int rbase;
            final int rsize;
            final double[] w;
            final int wbase;

            Merger(CountedCompleter<?> par, double[] a2, double[] w2, int lbase2, int lsize2, int rbase2, int rsize2, int wbase2, int gran2) {
                super(par);
                this.a = a2;
                this.w = w2;
                this.lbase = lbase2;
                this.lsize = lsize2;
                this.rbase = rbase2;
                this.rsize = rsize2;
                this.wbase = wbase2;
                this.gran = gran2;
            }

            @Override // java.util.concurrent.CountedCompleter
            public final void compute() {
                int rh;
                int lh;
                double t;
                double[] a2 = this.a;
                double[] w2 = this.w;
                int lb = this.lbase;
                int ln = this.lsize;
                int rb = this.rbase;
                int rn = this.rsize;
                int k = this.wbase;
                int g = this.gran;
                if (a2 == null || w2 == null || lb < 0 || rb < 0 || k < 0) {
                    throw new IllegalStateException();
                }
                int ln2 = ln;
                int rn2 = rn;
                while (true) {
                    if (ln2 >= rn2) {
                        if (ln2 <= g) {
                            break;
                        }
                        int rh2 = rn2;
                        int lh2 = ln2 >>> 1;
                        double split = a2[lh2 + lb];
                        int lo = 0;
                        while (lo < rh2) {
                            int rm = (lo + rh2) >>> 1;
                            if (split <= a2[rm + rb]) {
                                rh2 = rm;
                            } else {
                                lo = rm + 1;
                            }
                        }
                        rh = rh2;
                        lh = lh2;
                        Merger m = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m.fork();
                        a2 = a2;
                    } else if (rn2 <= g) {
                        break;
                    } else {
                        int lh3 = ln2;
                        int rh3 = rn2 >>> 1;
                        double split2 = a2[rh3 + rb];
                        int lo2 = 0;
                        while (lo2 < lh3) {
                            int lm = (lo2 + lh3) >>> 1;
                            if (split2 <= a2[lm + lb]) {
                                lh3 = lm;
                            } else {
                                lo2 = lm + 1;
                            }
                        }
                        lh = lh3;
                        rh = rh3;
                        Merger m2 = new Merger(this, a2, w2, lb + lh, ln2 - lh, rb + rh, rn2 - rh, k + lh + rh, g);
                        rn2 = rh;
                        ln2 = lh;
                        addToPendingCount(1);
                        m2.fork();
                        a2 = a2;
                    }
                }
                int lf = lb + ln2;
                int rf = rb + rn2;
                while (lb < lf && rb < rf) {
                    double al = a2[lb];
                    double ar = a2[rb];
                    if (al <= ar) {
                        lb++;
                        t = al;
                    } else {
                        rb++;
                        t = ar;
                    }
                    w2[k] = t;
                    k++;
                }
                if (rb < rf) {
                    System.arraycopy((Object) a2, rb, (Object) w2, k, rf - rb);
                } else if (lb < lf) {
                    System.arraycopy((Object) a2, lb, (Object) w2, k, lf - lb);
                }
                tryComplete();
            }
        }
    }
}
