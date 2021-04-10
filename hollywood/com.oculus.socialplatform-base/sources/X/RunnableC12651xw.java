package X;

/* renamed from: X.1xw  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12651xw implements Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.schedulers.TrampolineScheduler$TrampolineWorker$AppendToQueueTask";
    public final C12621xt A00;
    public final /* synthetic */ C12601xr A01;

    public RunnableC12651xw(C12601xr r1, C12621xt r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        C12621xt r1 = this.A00;
        r1.A03 = true;
        this.A01.A00.remove(r1);
    }
}
