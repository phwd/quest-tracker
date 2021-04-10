package X;

import android.os.SystemClock;

/* renamed from: X.1v3  reason: invalid class name */
public class AnonymousClass1v3 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.common.concurrent.SerialExecutor$RunnableWrapper";
    public final Runnable A00;
    public volatile long A01;
    public volatile long A02;
    public final /* synthetic */ AnonymousClass1v2 A03;

    public AnonymousClass1v3(AnonymousClass1v2 r1, Runnable runnable) {
        this.A03 = r1;
        this.A00 = runnable;
        SystemClock.uptimeMillis();
    }

    public final void run() {
        SystemClock.uptimeMillis();
        AnonymousClass1v2 r1 = this.A03;
        SystemClock.currentThreadTimeMillis();
        this.A00.run();
        SystemClock.currentThreadTimeMillis();
        SystemClock.uptimeMillis();
        synchronized (r1) {
            r1.A00 = false;
        }
        AnonymousClass1v2.A00(r1);
    }
}
