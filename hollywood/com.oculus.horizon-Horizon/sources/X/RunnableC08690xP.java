package X;

/* renamed from: X.0xP  reason: invalid class name and case insensitive filesystem */
public class RunnableC08690xP implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.MoreExecutors$5$1";
    public final /* synthetic */ ExecutorC08700xQ A00;
    public final /* synthetic */ Runnable A01;

    public RunnableC08690xP(ExecutorC08700xQ r1, Runnable runnable) {
        this.A00 = r1;
        this.A01 = runnable;
    }

    public final void run() {
        this.A00.A00 = false;
        this.A01.run();
    }
}
