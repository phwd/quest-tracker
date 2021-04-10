package X;

import android.os.Process;

/* renamed from: X.0PT  reason: invalid class name */
public class AnonymousClass0PT implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.imagepipeline.core.PriorityThreadFactory$1";
    public final /* synthetic */ AnonymousClass0PU A00;
    public final /* synthetic */ Runnable A01;

    public AnonymousClass0PT(AnonymousClass0PU r1, Runnable runnable) {
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
