package X;

import android.os.Process;

/* renamed from: X.1o8  reason: invalid class name */
public class AnonymousClass1o8 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.imagepipeline.core.PriorityThreadFactory$1";
    public final /* synthetic */ AnonymousClass1o7 A00;
    public final /* synthetic */ Runnable A01;

    public AnonymousClass1o8(AnonymousClass1o7 r1, Runnable runnable) {
        this.A00 = r1;
        this.A01 = runnable;
    }

    public final void run() {
        try {
            Process.setThreadPriority(this.A00.A00);
        } catch (Throwable unused) {
        }
        this.A01.run();
    }
}
