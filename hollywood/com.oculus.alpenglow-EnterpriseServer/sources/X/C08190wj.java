package X;

import android.os.Process;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0wj  reason: invalid class name and case insensitive filesystem */
public final class C08190wj extends Thread {
    public static final String __redex_internal_original_name = "com.facebook.rti.common.concurrent.RtiThread";
    public final int A00 = 9;

    public C08190wj(Runnable runnable, String str) {
        super(runnable, str);
    }

    public final void run() {
        Process.setThreadPriority(this.A00);
        super.run();
    }
}
