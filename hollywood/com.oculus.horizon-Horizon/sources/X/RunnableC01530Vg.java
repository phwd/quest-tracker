package X;

import java.util.Queue;

/* renamed from: X.0Vg  reason: invalid class name and case insensitive filesystem */
public class RunnableC01530Vg implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.common.analytics.defaultlogger.DefaultAnalyticsLogger$AnalyticsBackgroundWorker";
    public final /* synthetic */ C06570ne A00;

    public RunnableC01530Vg(C06570ne r1) {
        this.A00 = r1;
    }

    public final void run() {
        C06570ne r2 = this.A00;
        r2.A0A.set(false);
        while (true) {
            Queue<Runnable> queue = r2.A09;
            if (!queue.isEmpty()) {
                queue.remove().run();
            } else {
                return;
            }
        }
    }
}
