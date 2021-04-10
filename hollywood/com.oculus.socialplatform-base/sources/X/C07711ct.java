package X;

import android.os.Process;
import android.os.StrictMode;

/* renamed from: X.1ct  reason: invalid class name and case insensitive filesystem */
public class C07711ct extends Thread {
    public static final String __redex_internal_original_name = "com.bumptech.glide.load.engine.executor.GlideExecutor$DefaultThreadFactory$1";
    public final /* synthetic */ ThreadFactoryC07721cu A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C07711ct(ThreadFactoryC07721cu r1, Runnable runnable, String str) {
        super(runnable, str);
        this.A00 = r1;
    }

    public final void run() {
        Process.setThreadPriority(9);
        ThreadFactoryC07721cu r2 = this.A00;
        if (r2.A02) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
        }
        try {
            super.run();
        } catch (Throwable th) {
            r2.A01.A5M(th);
        }
    }
}
