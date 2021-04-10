package X;

import java.util.concurrent.TimeUnit;

/* renamed from: X.1xs  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12611xs implements Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.schedulers.TrampolineScheduler$SleepingRunnable";
    public final long A00;
    public final C12601xr A01;
    public final Runnable A02;

    public final void run() {
        C12601xr r5 = this.A01;
        if (!r5.A03) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            long convert = timeUnit.convert(System.currentTimeMillis(), timeUnit);
            long j = this.A00;
            if (j > convert) {
                try {
                    Thread.sleep(j - convert);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    AnonymousClass1y3.A01(e);
                    return;
                }
            }
            if (!r5.A03) {
                this.A02.run();
            }
        }
    }

    public RunnableC12611xs(Runnable runnable, C12601xr r2, long j) {
        this.A02 = runnable;
        this.A01 = r2;
        this.A00 = j;
    }
}
