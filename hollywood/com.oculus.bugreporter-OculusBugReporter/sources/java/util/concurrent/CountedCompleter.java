package java.util.concurrent;

import sun.misc.Unsafe;

public abstract class CountedCompleter<T> extends ForkJoinTask<T> {
    private static final long PENDING;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long serialVersionUID = 5232453752276485070L;
    final CountedCompleter<?> completer;
    volatile int pending;

    public abstract void compute();

    protected CountedCompleter(CountedCompleter<?> completer2, int initialPendingCount) {
        this.completer = completer2;
        this.pending = initialPendingCount;
    }

    protected CountedCompleter(CountedCompleter<?> completer2) {
        this.completer = completer2;
    }

    protected CountedCompleter() {
        this.completer = null;
    }

    public void onCompletion(CountedCompleter<?> countedCompleter) {
    }

    public boolean onExceptionalCompletion(Throwable ex, CountedCompleter<?> countedCompleter) {
        return true;
    }

    public final CountedCompleter<?> getCompleter() {
        return this.completer;
    }

    public final int getPendingCount() {
        return this.pending;
    }

    public final void setPendingCount(int count) {
        this.pending = count;
    }

    public final void addToPendingCount(int delta) {
        U.getAndAddInt(this, PENDING, delta);
    }

    public final boolean compareAndSetPendingCount(int expected, int count) {
        return U.compareAndSwapInt(this, PENDING, expected, count);
    }

    public final int decrementPendingCountUnlessZero() {
        int c;
        do {
            c = this.pending;
            if (c == 0) {
                break;
            }
        } while (!U.compareAndSwapInt(this, PENDING, c, c - 1));
        return c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.util.concurrent.CountedCompleter, java.util.concurrent.CountedCompleter<?> */
    public final CountedCompleter<?> getRoot() {
        CountedCompleter<?> a = this;
        while (true) {
            CountedCompleter<?> p = a.completer;
            if (p == null) {
                return a;
            }
            a = p;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:14:0x0002 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r2v3. Raw type applied. Possible types: java.util.concurrent.CountedCompleter<?> */
    public final void tryComplete() {
        CountedCompleter countedCompleter = this;
        CountedCompleter countedCompleter2 = countedCompleter;
        while (true) {
            int c = countedCompleter.pending;
            if (c == 0) {
                countedCompleter.onCompletion(countedCompleter2);
                countedCompleter2 = countedCompleter;
                CountedCompleter countedCompleter3 = countedCompleter.completer;
                countedCompleter = countedCompleter3;
                if (countedCompleter3 == null) {
                    countedCompleter2.quietlyComplete();
                    return;
                }
            } else if (U.compareAndSwapInt(countedCompleter, PENDING, c, c - 1)) {
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r2v3. Raw type applied. Possible types: java.util.concurrent.CountedCompleter<?> */
    public final void propagateCompletion() {
        CountedCompleter countedCompleter = this;
        while (true) {
            int c = countedCompleter.pending;
            if (c == 0) {
                CountedCompleter countedCompleter2 = countedCompleter.completer;
                countedCompleter = countedCompleter2;
                if (countedCompleter2 == null) {
                    countedCompleter.quietlyComplete();
                    return;
                }
            } else if (U.compareAndSwapInt(countedCompleter, PENDING, c, c - 1)) {
                return;
            }
        }
    }

    @Override // java.util.concurrent.ForkJoinTask
    public void complete(T rawResult) {
        setRawResult(rawResult);
        onCompletion(this);
        quietlyComplete();
        CountedCompleter<?> p = this.completer;
        if (p != null) {
            p.tryComplete();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v0, resolved type: java.util.concurrent.CountedCompleter<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public final CountedCompleter<?> firstComplete() {
        int c;
        do {
            c = this.pending;
            if (c == 0) {
                return this;
            }
        } while (!U.compareAndSwapInt(this, PENDING, c, c - 1));
        return null;
    }

    public final CountedCompleter<?> nextComplete() {
        CountedCompleter<?> p = this.completer;
        if (p != null) {
            return p.firstComplete();
        }
        quietlyComplete();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r1v0. Raw type applied. Possible types: java.util.concurrent.CountedCompleter<?> */
    public final void quietlyCompleteRoot() {
        CountedCompleter<?> a = this;
        while (true) {
            CountedCompleter<?> p = a.completer;
            if (p == null) {
                a.quietlyComplete();
                return;
            }
            a = p;
        }
    }

    public final void helpComplete(int maxTasks) {
        if (maxTasks > 0 && this.status >= 0) {
            Thread t = Thread.currentThread();
            if (t instanceof ForkJoinWorkerThread) {
                ForkJoinWorkerThread wt = (ForkJoinWorkerThread) t;
                wt.pool.helpComplete(wt.workQueue, this, maxTasks);
                return;
            }
            ForkJoinPool.common.externalHelpComplete(this, maxTasks);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0008  */
    @Override // java.util.concurrent.ForkJoinTask
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void internalPropagateException(java.lang.Throwable r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r0
        L_0x0002:
            boolean r2 = r0.onExceptionalCompletion(r5, r1)
            if (r2 == 0) goto L_0x001b
            r1 = r0
            java.util.concurrent.CountedCompleter<?> r2 = r0.completer
            r0 = r2
            if (r2 == 0) goto L_0x001b
            int r2 = r0.status
            if (r2 < 0) goto L_0x001b
            int r2 = r0.recordExceptionalCompletion(r5)
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r2 != r3) goto L_0x001b
            goto L_0x0002
        L_0x001b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.CountedCompleter.internalPropagateException(java.lang.Throwable):void");
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.ForkJoinTask
    public final boolean exec() {
        compute();
        return false;
    }

    @Override // java.util.concurrent.ForkJoinTask
    public T getRawResult() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.ForkJoinTask
    public void setRawResult(T t) {
    }

    static {
        try {
            PENDING = U.objectFieldOffset(CountedCompleter.class.getDeclaredField("pending"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}
