package X;

/* renamed from: X.1xO  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12391xO implements Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.schedulers.ExecutorScheduler$DelayedDispose";
    public final RunnableC12381xN A00;
    public final /* synthetic */ AnonymousClass1xJ A01;

    public RunnableC12391xO(AnonymousClass1xJ r1, RunnableC12381xN r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        RunnableC12381xN r2 = this.A00;
        EnumC12511xi.replace(r2.direct, this.A01.A01(r2));
    }
}
