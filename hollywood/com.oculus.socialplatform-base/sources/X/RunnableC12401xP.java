package X;

/* renamed from: X.1xP  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12401xP implements Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.schedulers.ExecutorScheduler$ExecutorWorker$SequentialDispose";
    public final AnonymousClass1xW A00;
    public final Runnable A01;
    public final /* synthetic */ RunnableC12351xK A02;

    public RunnableC12401xP(RunnableC12351xK r1, AnonymousClass1xW r2, Runnable runnable) {
        this.A02 = r1;
        this.A00 = r2;
        this.A01 = runnable;
    }

    public final void run() {
        EnumC12511xi.replace(this.A00, this.A02.A01(this.A01));
    }
}
