package X;

import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
/* renamed from: X.6b  reason: invalid class name */
public final class AnonymousClass6b extends AnonymousClass9F implements MF {
    public final ScheduledExecutorService A00;

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        AnonymousClass0V r2 = new AnonymousClass0V(runnable);
        return new AnonymousClass0W(r2, this.A00.scheduleAtFixedRate(r2, j, j2, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        AnonymousClass0V r2 = new AnonymousClass0V(runnable);
        return new AnonymousClass0W(r2, this.A00.scheduleWithFixedDelay(r2, j, j2, timeUnit));
    }

    public AnonymousClass6b(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        if (scheduledExecutorService != null) {
            this.A00 = scheduledExecutorService;
            return;
        }
        throw null;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        AnonymousClass0B r2 = new AnonymousClass0B(Executors.callable(runnable, null));
        return new AnonymousClass0W(r2, this.A00.schedule(r2, j, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        AnonymousClass0B r2 = new AnonymousClass0B(callable);
        return new AnonymousClass0W(r2, this.A00.schedule(r2, j, timeUnit));
    }
}
