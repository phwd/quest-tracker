package X;

import android.os.Build;

/* renamed from: X.0hl  reason: invalid class name and case insensitive filesystem */
public class RunnableC04460hl implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.systrace.TraceListenerNotifier$1";
    public final /* synthetic */ C04470hm A00;
    public final /* synthetic */ long A01;

    public RunnableC04460hl(C04470hm r1, long j) {
        this.A00 = r1;
        this.A01 = j;
    }

    public final void run() {
        C04470hm r6 = this.A00;
        synchronized (r6.A01) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            } else {
                do {
                } while (C04470hm.A03.lastModified() == this.A01);
            }
            r6.A00();
        }
    }
}
