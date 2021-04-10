package X;

import android.os.Process;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Vn  reason: invalid class name */
public final class AnonymousClass0Vn extends Thread {
    public static final String __redex_internal_original_name = "com.facebook.rti.common.concurrent.RtiThread";
    public final int A00 = 9;

    public AnonymousClass0Vn(Runnable runnable, String str) {
        super(runnable, str);
    }

    public final void run() {
        Process.setThreadPriority(this.A00);
        super.run();
    }
}
