package X;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1xS  reason: invalid class name and case insensitive filesystem */
public final class C12421xS extends AbstractC12361xL {
    public static final ThreadFactoryC06661ap A02;
    public static final ThreadFactoryC06661ap A03;
    public static final long A04 = Long.getLong("rx2.io-keep-alive-time", 60).longValue();
    public static final AnonymousClass1xR A05;
    public static final AnonymousClass1xY A06;
    public static final TimeUnit A07 = TimeUnit.SECONDS;
    public final ThreadFactory A00 = A03;
    public final AtomicReference<AnonymousClass1xR> A01;

    static {
        AnonymousClass1xY r0 = new AnonymousClass1xY(new ThreadFactoryC06661ap("RxCachedThreadSchedulerShutdown", 5, false));
        A06 = r0;
        r0.dispose();
        int max = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        A03 = new ThreadFactoryC06661ap("RxCachedThreadScheduler", max, false);
        A02 = new ThreadFactoryC06661ap("RxCachedWorkerPoolEvictor", max, false);
        AnonymousClass1xR r2 = new AnonymousClass1xR(0, null, A03);
        A05 = r2;
        r2.A01.dispose();
        Future<?> future = r2.A03;
        if (future != null) {
            future.cancel(true);
        }
        ScheduledExecutorService scheduledExecutorService = r2.A04;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
    }

    public C12421xS() {
        AnonymousClass1xR r5 = A05;
        this.A01 = new AtomicReference<>(r5);
        AnonymousClass1xR r2 = new AnonymousClass1xR(A04, A07, this.A00);
        if (!this.A01.compareAndSet(r5, r2)) {
            r2.A01.dispose();
            Future<?> future = r2.A03;
            if (future != null) {
                future.cancel(true);
            }
            ScheduledExecutorService scheduledExecutorService = r2.A04;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
            }
        }
    }
}
