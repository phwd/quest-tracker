package X;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: X.Rr  reason: case insensitive filesystem */
public final class ScheduledExecutorServiceC0337Rr extends BY implements ScheduledExecutorService, V0 {
    public final ScheduledExecutorService A00;

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        AnonymousClass1n r2 = new AnonymousClass1n(runnable);
        return new AnonymousClass1p(r2, this.A00.scheduleAtFixedRate(r2, j, j2, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        AnonymousClass1n r2 = new AnonymousClass1n(runnable);
        return new AnonymousClass1p(r2, this.A00.scheduleWithFixedDelay(r2, j, j2, timeUnit));
    }

    public ScheduledExecutorServiceC0337Rr(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        if (scheduledExecutorService != null) {
            this.A00 = scheduledExecutorService;
            return;
        }
        throw null;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        AnonymousClass1K r2 = new AnonymousClass1K(Executors.callable(runnable, null));
        return new AnonymousClass1p(r2, this.A00.schedule(r2, j, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        AnonymousClass1K r2 = new AnonymousClass1K(callable);
        return new AnonymousClass1p(r2, this.A00.schedule(r2, j, timeUnit));
    }
}
