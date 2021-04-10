package X;

/* renamed from: X.1ww  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12171ww extends AbstractC12151wu implements Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.schedulers.ScheduledDirectPeriodicTask";
    public static final long serialVersionUID = 1811839108042568751L;

    public final void run() {
        this.runner = Thread.currentThread();
        try {
            this.runnable.run();
            this.runner = null;
        } catch (Throwable th) {
            this.runner = null;
            lazySet(AbstractC12151wu.A01);
            AnonymousClass1y3.A01(th);
        }
    }

    public RunnableC12171ww(Runnable runnable) {
        super(runnable);
    }
}
