package X;

public class f8 implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.MoreExecutors$5$1";
    public final /* synthetic */ fd A00;
    public final /* synthetic */ Runnable A01;

    public f8(fd fdVar, Runnable runnable) {
        this.A00 = fdVar;
        this.A01 = runnable;
    }

    public final void run() {
        this.A00.A00 = false;
        this.A01.run();
    }
}
