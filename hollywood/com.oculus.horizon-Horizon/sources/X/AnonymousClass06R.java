package X;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
/* renamed from: X.06R  reason: invalid class name */
public final class AnonymousClass06R extends AnonymousClass0Bf implements AbstractScheduledExecutorServiceC03390co {
    public final ScheduledExecutorService A00;

    @Override // X.AbstractScheduledExecutorServiceC03390co
    /* renamed from: A8S */
    public final ListenableScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        AnonymousClass009 r2 = new AnonymousClass009(Executors.callable(runnable, null));
        return new ScheduledFutureC000200n(r2, this.A00.schedule(r2, j, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        AnonymousClass00b r2 = new AnonymousClass00b(runnable);
        return new ScheduledFutureC000200n(r2, this.A00.scheduleAtFixedRate(r2, j, j2, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        AnonymousClass00b r2 = new AnonymousClass00b(runnable);
        return new ScheduledFutureC000200n(r2, this.A00.scheduleWithFixedDelay(r2, j, j2, timeUnit));
    }

    public AnonymousClass06R(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        if (scheduledExecutorService != null) {
            this.A00 = scheduledExecutorService;
            return;
        }
        throw null;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        AnonymousClass009 r2 = new AnonymousClass009(callable);
        return new ScheduledFutureC000200n(r2, this.A00.schedule(r2, j, timeUnit));
    }
}
