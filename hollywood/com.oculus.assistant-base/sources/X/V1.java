package X;

public final class V1 implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.MoreExecutors$5$1";
    public final /* synthetic */ V2 A00;
    public final /* synthetic */ Runnable A01;

    public V1(V2 v2, Runnable runnable) {
        this.A00 = v2;
        this.A01 = runnable;
    }

    public final void run() {
        this.A00.A00 = false;
        this.A01.run();
    }
}
