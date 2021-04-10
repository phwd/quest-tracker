package X;

import android.os.Process;

/* renamed from: X.1eR  reason: invalid class name and case insensitive filesystem */
public class RunnableC08351eR implements Runnable {
    public static final String __redex_internal_original_name = "com.bumptech.glide.load.engine.ActiveResources$1$1";
    public final /* synthetic */ ThreadFactoryC08361eS A00;
    public final /* synthetic */ Runnable A01;

    public RunnableC08351eR(ThreadFactoryC08361eS r1, Runnable runnable) {
        this.A00 = r1;
        this.A01 = runnable;
    }

    public final void run() {
        Process.setThreadPriority(10);
        this.A01.run();
    }
}
