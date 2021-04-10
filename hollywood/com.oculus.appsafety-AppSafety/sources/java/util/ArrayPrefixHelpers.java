package java.util;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

class ArrayPrefixHelpers {
    static final int CUMULATE = 1;
    static final int FINISHED = 4;
    static final int MIN_PARTITION = 16;
    static final int SUMMED = 2;

    private ArrayPrefixHelpers() {
    }

    static final class CumulateTask<T> extends CountedCompleter<Void> {
        private static final long serialVersionUID = 5293554502939613543L;
        final T[] array;
        final int fence;
        final BinaryOperator<T> function;
        final int hi;
        T in;
        CumulateTask<T> left;
        final int lo;
        final int origin;
        T out;
        CumulateTask<T> right;
        final int threshold;

        public CumulateTask(CumulateTask<T> parent, BinaryOperator<T> function2, T[] array2, int lo2, int hi2) {
            super(parent);
            this.function = function2;
            this.array = array2;
            this.origin = lo2;
            this.lo = lo2;
            this.fence = hi2;
            this.hi = hi2;
            int p = (hi2 - lo2) / (ForkJoinPool.getCommonPoolParallelism() << 3);
            this.threshold = p > 16 ? p : 16;
        }

        CumulateTask(CumulateTask<T> parent, BinaryOperator<T> function2, T[] array2, int origin2, int fence2, int threshold2, int lo2, int hi2) {
            super(parent);
            this.function = function2;
            this.array = array2;
            this.origin = origin2;
            this.fence = fence2;
            this.threshold = threshold2;
            this.lo = lo2;
            this.hi = hi2;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r6v5, resolved type: java.lang.Object[] */
        /* JADX DEBUG: Multi-variable search result rejected for r2v10, resolved type: java.lang.Object */
        /* JADX DEBUG: Multi-variable search result rejected for r10v5, resolved type: java.lang.Object[] */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            T[] a;
            int state;
            T sum;
            int th;
            int first;
            T sum2;
            CumulateTask<T> f;
            BinaryOperator<T> fn = this.function;
            if (fn == null || (a = this.array) == null) {
                throw new NullPointerException();
            }
            int th2 = this.threshold;
            int org2 = this.origin;
            int fnc = this.fence;
            CumulateTask<T> t = this;
            while (true) {
                int l = t.lo;
                if (l >= 0) {
                    int h = t.hi;
                    if (h > a.length) {
                        return;
                    }
                    if (h - l > th2) {
                        CumulateTask<T> lt = t.left;
                        CumulateTask<T> rt = t.right;
                        if (lt == null) {
                            int mid = (l + h) >>> 1;
                            CumulateTask<T> f2 = new CumulateTask<>(t, fn, a, org2, fnc, th2, mid, h);
                            t.right = f2;
                            CumulateTask<T> t2 = new CumulateTask<>(t, fn, a, org2, fnc, th2, l, mid);
                            t.left = t2;
                            t = t2;
                            f = f2;
                        } else {
                            T pin = t.in;
                            lt.in = pin;
                            f = null;
                            CumulateTask<T> t3 = null;
                            if (rt != null) {
                                T lout = lt.out;
                                rt.in = l == org2 ? lout : (T) fn.apply(pin, lout);
                                while (true) {
                                    int c = rt.getPendingCount();
                                    if ((c & 1) == 0) {
                                        if (rt.compareAndSetPendingCount(c, c | 1)) {
                                            t3 = rt;
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                            while (true) {
                                int c2 = lt.getPendingCount();
                                if ((c2 & 1) != 0) {
                                    t = t3;
                                    break;
                                } else if (lt.compareAndSetPendingCount(c2, c2 | 1)) {
                                    if (t3 != null) {
                                        f = t3;
                                    }
                                    t = lt;
                                }
                            }
                            if (t == null) {
                                return;
                            }
                        }
                        if (f != null) {
                            f.fork();
                        }
                    } else {
                        int h2 = h;
                        while (true) {
                            int b = t.getPendingCount();
                            if ((b & 4) == 0) {
                                if ((b & 1) != 0) {
                                    state = 4;
                                } else {
                                    state = l > org2 ? 2 : 6;
                                }
                                if (t.compareAndSetPendingCount(b, b | state)) {
                                    if (state != 2) {
                                        if (l == org2) {
                                            sum2 = a[org2];
                                            first = org2 + 1;
                                        } else {
                                            sum2 = t.in;
                                            first = l;
                                        }
                                        int i = first;
                                        while (i < h2) {
                                            Object apply = fn.apply(sum2, a[i]);
                                            sum2 = (T) apply;
                                            a[i] = apply;
                                            i++;
                                            h2 = h2;
                                        }
                                        sum = sum2;
                                    } else if (h2 < fnc) {
                                        int i2 = l + 1;
                                        T sum3 = a[l];
                                        while (i2 < h2) {
                                            i2++;
                                            sum3 = fn.apply(sum3, a[i2]);
                                        }
                                        sum = sum3;
                                    } else {
                                        sum = t.in;
                                    }
                                    t.out = sum;
                                    while (true) {
                                        CumulateTask<T> partmp = (CumulateTask) t.getCompleter();
                                        if (partmp != null) {
                                            int b2 = partmp.getPendingCount();
                                            if ((b2 & state & 4) != 0) {
                                                t = partmp;
                                                th = th2;
                                            } else if ((b2 & state & 2) != 0) {
                                                CumulateTask<T> lt2 = partmp.left;
                                                if (lt2 != null) {
                                                    CumulateTask<T> rt2 = partmp.right;
                                                    if (rt2 != null) {
                                                        T lout2 = lt2.out;
                                                        th = th2;
                                                        partmp.out = rt2.hi == fnc ? lout2 : (T) fn.apply(lout2, rt2.out);
                                                    } else {
                                                        th = th2;
                                                    }
                                                } else {
                                                    th = th2;
                                                }
                                                int refork = ((b2 & 1) == 0 && partmp.lo == org2) ? 1 : 0;
                                                int nextState = b2 | state | refork;
                                                if (nextState == b2 || partmp.compareAndSetPendingCount(b2, nextState)) {
                                                    state = 2;
                                                    t = partmp;
                                                    if (refork != 0) {
                                                        partmp.fork();
                                                    }
                                                }
                                            } else {
                                                th = th2;
                                                if (partmp.compareAndSetPendingCount(b2, b2 | state)) {
                                                    return;
                                                }
                                            }
                                            th2 = th;
                                        } else if ((state & 4) != 0) {
                                            t.quietlyComplete();
                                            return;
                                        } else {
                                            return;
                                        }
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    static final class LongCumulateTask extends CountedCompleter<Void> {
        private static final long serialVersionUID = -5074099945909284273L;
        final long[] array;
        final int fence;
        final LongBinaryOperator function;
        final int hi;
        long in;
        LongCumulateTask left;
        final int lo;
        final int origin;
        long out;
        LongCumulateTask right;
        final int threshold;

        public LongCumulateTask(LongCumulateTask parent, LongBinaryOperator function2, long[] array2, int lo2, int hi2) {
            super(parent);
            this.function = function2;
            this.array = array2;
            this.origin = lo2;
            this.lo = lo2;
            this.fence = hi2;
            this.hi = hi2;
            int p = (hi2 - lo2) / (ForkJoinPool.getCommonPoolParallelism() << 3);
            this.threshold = p > 16 ? p : 16;
        }

        LongCumulateTask(LongCumulateTask parent, LongBinaryOperator function2, long[] array2, int origin2, int fence2, int threshold2, int lo2, int hi2) {
            super(parent);
            this.function = function2;
            this.array = array2;
            this.origin = origin2;
            this.fence = fence2;
            this.threshold = threshold2;
            this.lo = lo2;
            this.hi = hi2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            long[] a;
            int state;
            int h;
            long sum;
            long sum2;
            int h2;
            int th;
            long j;
            int first;
            LongCumulateTask f;
            long j2;
            LongBinaryOperator fn = this.function;
            if (fn == null || (a = this.array) == null) {
                throw new NullPointerException();
            }
            int th2 = this.threshold;
            int org2 = this.origin;
            int fnc = this.fence;
            LongCumulateTask t = this;
            while (true) {
                int l = t.lo;
                if (l >= 0) {
                    int h3 = t.hi;
                    if (h3 > a.length) {
                        return;
                    }
                    if (h3 - l > th2) {
                        LongCumulateTask lt = t.left;
                        LongCumulateTask rt = t.right;
                        if (lt == null) {
                            int mid = (l + h3) >>> 1;
                            LongCumulateTask f2 = new LongCumulateTask(t, fn, a, org2, fnc, th2, mid, h3);
                            t.right = f2;
                            LongCumulateTask t2 = new LongCumulateTask(t, fn, a, org2, fnc, th2, l, mid);
                            t.left = t2;
                            t = t2;
                            f = f2;
                        } else {
                            long pin = t.in;
                            lt.in = pin;
                            f = null;
                            LongCumulateTask t3 = null;
                            if (rt != null) {
                                long lout = lt.out;
                                if (l == org2) {
                                    j2 = lout;
                                } else {
                                    j2 = fn.applyAsLong(pin, lout);
                                }
                                rt.in = j2;
                                while (true) {
                                    int c = rt.getPendingCount();
                                    if ((c & 1) == 0) {
                                        if (rt.compareAndSetPendingCount(c, c | 1)) {
                                            t3 = rt;
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                            while (true) {
                                int c2 = lt.getPendingCount();
                                if ((c2 & 1) != 0) {
                                    t = t3;
                                    break;
                                } else if (lt.compareAndSetPendingCount(c2, c2 | 1)) {
                                    if (t3 != null) {
                                        f = t3;
                                    }
                                    t = lt;
                                }
                            }
                            if (t == null) {
                                return;
                            }
                        }
                        if (f != null) {
                            f.fork();
                        }
                    } else {
                        int h4 = h3;
                        while (true) {
                            int b = t.getPendingCount();
                            int i = 4;
                            if ((b & 4) == 0) {
                                if ((b & 1) != 0) {
                                    state = 4;
                                } else {
                                    state = l > org2 ? 2 : 6;
                                }
                                if (t.compareAndSetPendingCount(b, b | state)) {
                                    if (state != 2) {
                                        if (l == org2) {
                                            sum = a[org2];
                                            first = org2 + 1;
                                        } else {
                                            sum = t.in;
                                            first = l;
                                        }
                                        int i2 = first;
                                        while (true) {
                                            h = h4;
                                            if (i2 >= h) {
                                                break;
                                            }
                                            long applyAsLong = fn.applyAsLong(sum, a[i2]);
                                            sum = applyAsLong;
                                            a[i2] = applyAsLong;
                                            i2++;
                                            h4 = h;
                                        }
                                    } else {
                                        h = h4;
                                        if (h < fnc) {
                                            long sum3 = a[l];
                                            sum = sum3;
                                            for (int i3 = l + 1; i3 < h; i3++) {
                                                sum = fn.applyAsLong(sum, a[i3]);
                                            }
                                        } else {
                                            sum = t.in;
                                        }
                                    }
                                    t.out = sum;
                                    while (true) {
                                        LongCumulateTask par = (LongCumulateTask) t.getCompleter();
                                        if (par != null) {
                                            int b2 = par.getPendingCount();
                                            if ((b2 & state & i) != 0) {
                                                th = th2;
                                                t = par;
                                                sum2 = sum;
                                                h2 = h;
                                            } else if ((b2 & state & 2) != 0) {
                                                LongCumulateTask lt2 = par.left;
                                                if (lt2 != null) {
                                                    LongCumulateTask rt2 = par.right;
                                                    if (rt2 != null) {
                                                        h2 = h;
                                                        long lout2 = lt2.out;
                                                        th = th2;
                                                        if (rt2.hi == fnc) {
                                                            sum2 = sum;
                                                            j = lout2;
                                                        } else {
                                                            sum2 = sum;
                                                            j = fn.applyAsLong(lout2, rt2.out);
                                                        }
                                                        par.out = j;
                                                    } else {
                                                        sum2 = sum;
                                                        h2 = h;
                                                        th = th2;
                                                    }
                                                } else {
                                                    th = th2;
                                                    sum2 = sum;
                                                    h2 = h;
                                                }
                                                int refork = ((b2 & 1) == 0 && par.lo == org2) ? 1 : 0;
                                                int nextState = b2 | state | refork;
                                                if (nextState == b2 || par.compareAndSetPendingCount(b2, nextState)) {
                                                    state = 2;
                                                    t = par;
                                                    if (refork != 0) {
                                                        par.fork();
                                                    }
                                                }
                                            } else {
                                                th = th2;
                                                sum2 = sum;
                                                h2 = h;
                                                if (par.compareAndSetPendingCount(b2, b2 | state)) {
                                                    return;
                                                }
                                            }
                                            th2 = th;
                                            h = h2;
                                            sum = sum2;
                                            i = 4;
                                        } else if ((state & 4) != 0) {
                                            t.quietlyComplete();
                                            return;
                                        } else {
                                            return;
                                        }
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    static final class DoubleCumulateTask extends CountedCompleter<Void> {
        private static final long serialVersionUID = -586947823794232033L;
        final double[] array;
        final int fence;
        final DoubleBinaryOperator function;
        final int hi;
        double in;
        DoubleCumulateTask left;
        final int lo;
        final int origin;
        double out;
        DoubleCumulateTask right;
        final int threshold;

        public DoubleCumulateTask(DoubleCumulateTask parent, DoubleBinaryOperator function2, double[] array2, int lo2, int hi2) {
            super(parent);
            this.function = function2;
            this.array = array2;
            this.origin = lo2;
            this.lo = lo2;
            this.fence = hi2;
            this.hi = hi2;
            int p = (hi2 - lo2) / (ForkJoinPool.getCommonPoolParallelism() << 3);
            this.threshold = p > 16 ? p : 16;
        }

        DoubleCumulateTask(DoubleCumulateTask parent, DoubleBinaryOperator function2, double[] array2, int origin2, int fence2, int threshold2, int lo2, int hi2) {
            super(parent);
            this.function = function2;
            this.array = array2;
            this.origin = origin2;
            this.fence = fence2;
            this.threshold = threshold2;
            this.lo = lo2;
            this.hi = hi2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            double[] a;
            int state;
            int h;
            double sum;
            double sum2;
            int h2;
            int th;
            double d;
            int first;
            DoubleCumulateTask f;
            double d2;
            DoubleBinaryOperator fn = this.function;
            if (fn == null || (a = this.array) == null) {
                throw new NullPointerException();
            }
            int th2 = this.threshold;
            int org2 = this.origin;
            int fnc = this.fence;
            DoubleCumulateTask t = this;
            while (true) {
                int l = t.lo;
                if (l >= 0) {
                    int h3 = t.hi;
                    if (h3 > a.length) {
                        return;
                    }
                    if (h3 - l > th2) {
                        DoubleCumulateTask lt = t.left;
                        DoubleCumulateTask rt = t.right;
                        if (lt == null) {
                            int mid = (l + h3) >>> 1;
                            DoubleCumulateTask f2 = new DoubleCumulateTask(t, fn, a, org2, fnc, th2, mid, h3);
                            t.right = f2;
                            DoubleCumulateTask t2 = new DoubleCumulateTask(t, fn, a, org2, fnc, th2, l, mid);
                            t.left = t2;
                            t = t2;
                            f = f2;
                        } else {
                            double pin = t.in;
                            lt.in = pin;
                            f = null;
                            DoubleCumulateTask t3 = null;
                            if (rt != null) {
                                double lout = lt.out;
                                if (l == org2) {
                                    d2 = lout;
                                } else {
                                    d2 = fn.applyAsDouble(pin, lout);
                                }
                                rt.in = d2;
                                while (true) {
                                    int c = rt.getPendingCount();
                                    if ((c & 1) == 0) {
                                        if (rt.compareAndSetPendingCount(c, c | 1)) {
                                            t3 = rt;
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                            while (true) {
                                int c2 = lt.getPendingCount();
                                if ((c2 & 1) != 0) {
                                    t = t3;
                                    break;
                                } else if (lt.compareAndSetPendingCount(c2, c2 | 1)) {
                                    if (t3 != null) {
                                        f = t3;
                                    }
                                    t = lt;
                                }
                            }
                            if (t == null) {
                                return;
                            }
                        }
                        if (f != null) {
                            f.fork();
                        }
                    } else {
                        int h4 = h3;
                        while (true) {
                            int b = t.getPendingCount();
                            int i = 4;
                            if ((b & 4) == 0) {
                                if ((b & 1) != 0) {
                                    state = 4;
                                } else {
                                    state = l > org2 ? 2 : 6;
                                }
                                if (t.compareAndSetPendingCount(b, b | state)) {
                                    if (state != 2) {
                                        if (l == org2) {
                                            sum = a[org2];
                                            first = org2 + 1;
                                        } else {
                                            sum = t.in;
                                            first = l;
                                        }
                                        int i2 = first;
                                        while (true) {
                                            h = h4;
                                            if (i2 >= h) {
                                                break;
                                            }
                                            double applyAsDouble = fn.applyAsDouble(sum, a[i2]);
                                            sum = applyAsDouble;
                                            a[i2] = applyAsDouble;
                                            i2++;
                                            h4 = h;
                                        }
                                    } else {
                                        h = h4;
                                        if (h < fnc) {
                                            double sum3 = a[l];
                                            sum = sum3;
                                            for (int i3 = l + 1; i3 < h; i3++) {
                                                sum = fn.applyAsDouble(sum, a[i3]);
                                            }
                                        } else {
                                            sum = t.in;
                                        }
                                    }
                                    t.out = sum;
                                    while (true) {
                                        DoubleCumulateTask par = (DoubleCumulateTask) t.getCompleter();
                                        if (par != null) {
                                            int b2 = par.getPendingCount();
                                            if ((b2 & state & i) != 0) {
                                                th = th2;
                                                t = par;
                                                sum2 = sum;
                                                h2 = h;
                                            } else if ((b2 & state & 2) != 0) {
                                                DoubleCumulateTask lt2 = par.left;
                                                if (lt2 != null) {
                                                    DoubleCumulateTask rt2 = par.right;
                                                    if (rt2 != null) {
                                                        h2 = h;
                                                        double lout2 = lt2.out;
                                                        th = th2;
                                                        if (rt2.hi == fnc) {
                                                            sum2 = sum;
                                                            d = lout2;
                                                        } else {
                                                            sum2 = sum;
                                                            d = fn.applyAsDouble(lout2, rt2.out);
                                                        }
                                                        par.out = d;
                                                    } else {
                                                        sum2 = sum;
                                                        h2 = h;
                                                        th = th2;
                                                    }
                                                } else {
                                                    th = th2;
                                                    sum2 = sum;
                                                    h2 = h;
                                                }
                                                int refork = ((b2 & 1) == 0 && par.lo == org2) ? 1 : 0;
                                                int nextState = b2 | state | refork;
                                                if (nextState == b2 || par.compareAndSetPendingCount(b2, nextState)) {
                                                    state = 2;
                                                    t = par;
                                                    if (refork != 0) {
                                                        par.fork();
                                                    }
                                                }
                                            } else {
                                                th = th2;
                                                sum2 = sum;
                                                h2 = h;
                                                if (par.compareAndSetPendingCount(b2, b2 | state)) {
                                                    return;
                                                }
                                            }
                                            th2 = th;
                                            h = h2;
                                            sum = sum2;
                                            i = 4;
                                        } else if ((state & 4) != 0) {
                                            t.quietlyComplete();
                                            return;
                                        } else {
                                            return;
                                        }
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    static final class IntCumulateTask extends CountedCompleter<Void> {
        private static final long serialVersionUID = 3731755594596840961L;
        final int[] array;
        final int fence;
        final IntBinaryOperator function;
        final int hi;
        int in;
        IntCumulateTask left;
        final int lo;
        final int origin;
        int out;
        IntCumulateTask right;
        final int threshold;

        public IntCumulateTask(IntCumulateTask parent, IntBinaryOperator function2, int[] array2, int lo2, int hi2) {
            super(parent);
            this.function = function2;
            this.array = array2;
            this.origin = lo2;
            this.lo = lo2;
            this.fence = hi2;
            this.hi = hi2;
            int p = (hi2 - lo2) / (ForkJoinPool.getCommonPoolParallelism() << 3);
            this.threshold = p > 16 ? p : 16;
        }

        IntCumulateTask(IntCumulateTask parent, IntBinaryOperator function2, int[] array2, int origin2, int fence2, int threshold2, int lo2, int hi2) {
            super(parent);
            this.function = function2;
            this.array = array2;
            this.origin = origin2;
            this.fence = fence2;
            this.threshold = threshold2;
            this.lo = lo2;
            this.hi = hi2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            int[] a;
            int state;
            int first;
            int i;
            int first2;
            int sum;
            IntCumulateTask f;
            int i2;
            IntBinaryOperator fn = this.function;
            if (fn == null || (a = this.array) == null) {
                throw new NullPointerException();
            }
            int th = this.threshold;
            int org2 = this.origin;
            int fnc = this.fence;
            IntCumulateTask t = this;
            while (true) {
                int l = t.lo;
                if (l >= 0) {
                    int h = t.hi;
                    if (h > a.length) {
                        return;
                    }
                    if (h - l > th) {
                        IntCumulateTask lt = t.left;
                        IntCumulateTask rt = t.right;
                        if (lt == null) {
                            int mid = (l + h) >>> 1;
                            IntCumulateTask f2 = new IntCumulateTask(t, fn, a, org2, fnc, th, mid, h);
                            t.right = f2;
                            IntCumulateTask t2 = new IntCumulateTask(t, fn, a, org2, fnc, th, l, mid);
                            t.left = t2;
                            t = t2;
                            f = f2;
                        } else {
                            int pin = t.in;
                            lt.in = pin;
                            f = null;
                            IntCumulateTask t3 = null;
                            if (rt != null) {
                                int lout = lt.out;
                                if (l == org2) {
                                    i2 = lout;
                                } else {
                                    i2 = fn.applyAsInt(pin, lout);
                                }
                                rt.in = i2;
                                while (true) {
                                    int c = rt.getPendingCount();
                                    if ((c & 1) == 0) {
                                        if (rt.compareAndSetPendingCount(c, c | 1)) {
                                            t3 = rt;
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                            while (true) {
                                int c2 = lt.getPendingCount();
                                if ((c2 & 1) != 0) {
                                    t = t3;
                                    break;
                                } else if (lt.compareAndSetPendingCount(c2, c2 | 1)) {
                                    if (t3 != null) {
                                        f = t3;
                                    }
                                    t = lt;
                                }
                            }
                            if (t == null) {
                                return;
                            }
                        }
                        if (f != null) {
                            f.fork();
                        }
                    } else {
                        int h2 = h;
                        while (true) {
                            int b = t.getPendingCount();
                            int i3 = 4;
                            if ((b & 4) == 0) {
                                int i4 = 2;
                                if ((b & 1) != 0) {
                                    state = 4;
                                } else {
                                    state = l > org2 ? 2 : 6;
                                }
                                if (t.compareAndSetPendingCount(b, b | state)) {
                                    if (state != 2) {
                                        if (l == org2) {
                                            sum = a[org2];
                                            first2 = org2 + 1;
                                        } else {
                                            sum = t.in;
                                            first2 = l;
                                        }
                                        int i5 = first2;
                                        while (i5 < h2) {
                                            int applyAsInt = fn.applyAsInt(sum, a[i5]);
                                            sum = applyAsInt;
                                            a[i5] = applyAsInt;
                                            i5++;
                                            h2 = h2;
                                        }
                                        first = sum;
                                    } else if (h2 < fnc) {
                                        int sum2 = a[l];
                                        for (int i6 = l + 1; i6 < h2; i6++) {
                                            sum2 = fn.applyAsInt(sum2, a[i6]);
                                        }
                                        first = sum2;
                                    } else {
                                        first = t.in;
                                    }
                                    t.out = first;
                                    while (true) {
                                        IntCumulateTask par = (IntCumulateTask) t.getCompleter();
                                        if (par != null) {
                                            int b2 = par.getPendingCount();
                                            if ((b2 & state & i3) != 0) {
                                                t = par;
                                            } else if ((b2 & state & i4) != 0) {
                                                IntCumulateTask lt2 = par.left;
                                                if (lt2 != null) {
                                                    IntCumulateTask rt2 = par.right;
                                                    if (rt2 != null) {
                                                        int lout2 = lt2.out;
                                                        if (rt2.hi == fnc) {
                                                            i = lout2;
                                                        } else {
                                                            i = fn.applyAsInt(lout2, rt2.out);
                                                        }
                                                        par.out = i;
                                                    }
                                                }
                                                int refork = ((b2 & 1) == 0 && par.lo == org2) ? 1 : 0;
                                                int nextState = b2 | state | refork;
                                                if (nextState == b2 || par.compareAndSetPendingCount(b2, nextState)) {
                                                    state = 2;
                                                    t = par;
                                                    if (refork != 0) {
                                                        par.fork();
                                                    }
                                                }
                                            } else if (par.compareAndSetPendingCount(b2, b2 | state)) {
                                                return;
                                            }
                                            i3 = 4;
                                            i4 = 2;
                                        } else if ((state & 4) != 0) {
                                            t.quietlyComplete();
                                            return;
                                        } else {
                                            return;
                                        }
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }
}
