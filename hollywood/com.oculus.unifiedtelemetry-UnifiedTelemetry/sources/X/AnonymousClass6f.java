package X;

import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
/* renamed from: X.6f  reason: invalid class name */
public final class AnonymousClass6f extends C0058Aw implements UE {
    public final ScheduledExecutorService A00;

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        AnonymousClass0b r2 = new AnonymousClass0b(runnable);
        return new ScheduledFutureC00020n(r2, this.A00.scheduleAtFixedRate(r2, j, j2, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        AnonymousClass0b r2 = new AnonymousClass0b(runnable);
        return new ScheduledFutureC00020n(r2, this.A00.scheduleWithFixedDelay(r2, j, j2, timeUnit));
    }

    public AnonymousClass6f(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        if (scheduledExecutorService != null) {
            this.A00 = scheduledExecutorService;
            return;
        }
        throw null;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        AnonymousClass09 r2 = new AnonymousClass09(Executors.callable(runnable, null));
        return new ScheduledFutureC00020n(r2, this.A00.schedule(r2, j, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        AnonymousClass09 r2 = new AnonymousClass09(callable);
        return new ScheduledFutureC00020n(r2, this.A00.schedule(r2, j, timeUnit));
    }
}
