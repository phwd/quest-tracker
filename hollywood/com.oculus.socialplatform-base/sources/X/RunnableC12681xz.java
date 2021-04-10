package X;

import android.os.Handler;

/* renamed from: X.1xz  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12681xz implements AbstractC12271xB, Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.android.schedulers.HandlerScheduler$ScheduledRunnable";
    public final Handler A00;
    public final Runnable A01;
    public volatile boolean A02;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A00.removeCallbacks(this);
    }

    public final void run() {
        try {
            this.A01.run();
        } catch (Throwable th) {
            AnonymousClass1y3.A01(th);
        }
    }

    public RunnableC12681xz(Handler handler, Runnable runnable) {
        this.A00 = handler;
        this.A01 = runnable;
    }
}
