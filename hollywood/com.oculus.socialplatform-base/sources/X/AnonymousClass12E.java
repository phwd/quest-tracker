package X;

/* renamed from: X.12E  reason: invalid class name */
public class AnonymousClass12E implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.MoreExecutors$5$1";
    public final /* synthetic */ AnonymousClass12F A00;
    public final /* synthetic */ Runnable A01;

    public AnonymousClass12E(AnonymousClass12F r1, Runnable runnable) {
        this.A00 = r1;
        this.A01 = runnable;
    }

    public final void run() {
        this.A00.A00 = false;
        this.A01.run();
    }
}
