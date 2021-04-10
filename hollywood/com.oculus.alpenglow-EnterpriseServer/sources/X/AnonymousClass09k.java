package X;

/* renamed from: X.09k  reason: invalid class name */
public class AnonymousClass09k implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.MoreExecutors$5$1";
    public final /* synthetic */ ExecutorC007909g A00;
    public final /* synthetic */ Runnable A01;

    public AnonymousClass09k(ExecutorC007909g r1, Runnable runnable) {
        this.A00 = r1;
        this.A01 = runnable;
    }

    public final void run() {
        this.A00.A00 = false;
        this.A01.run();
    }
}
