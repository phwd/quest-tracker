package X;

import io.reactivex.annotations.NonNull;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1wz  reason: invalid class name and case insensitive filesystem */
public final class C12201wz extends AbstractC12361xL {
    public static final AnonymousClass1x1 A02;
    public static final ThreadFactoryC06661ap A03;
    public static final int A04;
    public static final AnonymousClass1x0 A05;
    public final ThreadFactory A00 = A03;
    public final AtomicReference<AnonymousClass1x0> A01;

    public C12201wz() {
        AnonymousClass1x0 r3 = A05;
        this.A01 = new AtomicReference<>(r3);
        AnonymousClass1x0 r1 = new AnonymousClass1x0(A04, this.A00);
        if (!this.A01.compareAndSet(r3, r1)) {
            AnonymousClass1x1[] r32 = r1.A02;
            for (AnonymousClass1x1 r0 : r32) {
                r0.dispose();
            }
        }
    }

    @Override // X.AbstractC12361xL
    @NonNull
    public final AbstractC12271xB A02(@NonNull Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Future<?> future;
        AnonymousClass1x1 A002 = this.A01.get().A00();
        AnonymousClass219.A01(runnable, "run is null");
        if (j2 <= 0) {
            ScheduledExecutorService scheduledExecutorService = A002.A00;
            AnonymousClass1wt r4 = new AnonymousClass1wt(runnable, scheduledExecutorService);
            if (j <= 0) {
                future = scheduledExecutorService.submit(r4);
            } else {
                try {
                    future = scheduledExecutorService.schedule(r4, j, timeUnit);
                } catch (RejectedExecutionException e) {
                    AnonymousClass1y3.A01(e);
                    return AnonymousClass1z1.INSTANCE;
                }
            }
            r4.A00(future);
            return r4;
        }
        RunnableC12171ww r42 = new RunnableC12171ww(runnable);
        r42.A00(A002.A00.scheduleAtFixedRate(r42, j, j2, timeUnit));
        return r42;
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int intValue = Integer.getInteger("rx2.computation-threads", 0).intValue();
        if (intValue > 0 && intValue <= availableProcessors) {
            availableProcessors = intValue;
        }
        A04 = availableProcessors;
        AnonymousClass1x1 r0 = new AnonymousClass1x1(new ThreadFactoryC06661ap("RxComputationShutdown", 5, false));
        A02 = r0;
        r0.dispose();
        ThreadFactoryC06661ap r1 = new ThreadFactoryC06661ap("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx2.computation-priority", 5).intValue())), true);
        A03 = r1;
        AnonymousClass1x0 r02 = new AnonymousClass1x0(0, r1);
        A05 = r02;
        for (AnonymousClass1x1 r03 : r02.A02) {
            r03.dispose();
        }
    }
}
