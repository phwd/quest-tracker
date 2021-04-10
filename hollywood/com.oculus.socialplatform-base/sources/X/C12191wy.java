package X;

import io.reactivex.annotations.NonNull;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1wy  reason: invalid class name and case insensitive filesystem */
public final class C12191wy extends AbstractC12361xL {
    public static final ThreadFactoryC06661ap A02 = new ThreadFactoryC06661ap("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())), true);
    public static final ScheduledExecutorService A03;
    public final ThreadFactory A00;
    public final AtomicReference<ScheduledExecutorService> A01;

    static {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(0);
        A03 = newScheduledThreadPool;
        newScheduledThreadPool.shutdown();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: java.util.Map<java.util.concurrent.ScheduledThreadPoolExecutor, java.lang.Object> */
    /* JADX WARN: Multi-variable type inference failed */
    public C12191wy() {
        ThreadFactoryC06661ap r1 = A02;
        AtomicReference<ScheduledExecutorService> atomicReference = new AtomicReference<>();
        this.A01 = atomicReference;
        this.A00 = r1;
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, r1);
        if (C06641an.A03 && (newScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            C06641an.A01.put(newScheduledThreadPool, newScheduledThreadPool);
        }
        atomicReference.lazySet(newScheduledThreadPool);
    }

    @Override // X.AbstractC12361xL
    @NonNull
    public final AbstractC12271xB A02(@NonNull Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Future<?> future;
        AnonymousClass219.A01(runnable, "run is null");
        if (j2 <= 0) {
            ScheduledExecutorService scheduledExecutorService = this.A01.get();
            AnonymousClass1wt r1 = new AnonymousClass1wt(runnable, scheduledExecutorService);
            if (j <= 0) {
                future = scheduledExecutorService.submit(r1);
            } else {
                try {
                    future = scheduledExecutorService.schedule(r1, j, timeUnit);
                } catch (RejectedExecutionException e) {
                    AnonymousClass1y3.A01(e);
                    return AnonymousClass1z1.INSTANCE;
                }
            }
            r1.A00(future);
            return r1;
        }
        RunnableC12171ww r4 = new RunnableC12171ww(runnable);
        r4.A00(this.A01.get().scheduleAtFixedRate(r4, j, j2, timeUnit));
        return r4;
    }
}
