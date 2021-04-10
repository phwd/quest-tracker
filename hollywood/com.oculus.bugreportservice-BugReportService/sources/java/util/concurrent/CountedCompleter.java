package java.util.concurrent;

import sun.misc.Unsafe;

public abstract class CountedCompleter extends ForkJoinTask {
    private static final long PENDING;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long serialVersionUID = 5232453752276485070L;
    final CountedCompleter completer = null;
    volatile int pending;

    public abstract void compute();

    @Override // java.util.concurrent.ForkJoinTask
    public Object getRawResult() {
        return null;
    }

    public boolean onExceptionalCompletion(Throwable th, CountedCompleter countedCompleter) {
        return true;
    }

    protected CountedCompleter() {
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.concurrent.ForkJoinTask
    public void internalPropagateException(Throwable th) {
        CountedCompleter countedCompleter;
        CountedCompleter countedCompleter2 = this;
        while (this.onExceptionalCompletion(th, countedCompleter2) && (countedCompleter = this.completer) != null && countedCompleter.status >= 0 && countedCompleter.recordExceptionalCompletion(th) == Integer.MIN_VALUE) {
            countedCompleter2 = this;
            this = countedCompleter;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.ForkJoinTask
    public final boolean exec() {
        compute();
        return false;
    }

    static {
        try {
            PENDING = U.objectFieldOffset(CountedCompleter.class.getDeclaredField("pending"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}
