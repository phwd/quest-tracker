package X;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
/* renamed from: X.0BT  reason: invalid class name */
public final class AnonymousClass0BT extends AnonymousClass0IC implements AbstractScheduledExecutorServiceC02210Xe {
    public final ScheduledExecutorService A00;

    @Override // X.AbstractScheduledExecutorServiceC02210Xe
    /* renamed from: A7Y */
    public final ListenableScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        AnonymousClass039 r2 = new AnonymousClass039(Executors.callable(runnable, null));
        return new AnonymousClass060(r2, this.A00.schedule(r2, j, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        RunnableC006305z r2 = new RunnableC006305z(runnable);
        return new AnonymousClass060(r2, this.A00.scheduleAtFixedRate(r2, j, j2, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        RunnableC006305z r2 = new RunnableC006305z(runnable);
        return new AnonymousClass060(r2, this.A00.scheduleWithFixedDelay(r2, j, j2, timeUnit));
    }

    public AnonymousClass0BT(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        if (scheduledExecutorService != null) {
            this.A00 = scheduledExecutorService;
            return;
        }
        throw null;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        AnonymousClass039 r2 = new AnonymousClass039(callable);
        return new AnonymousClass060(r2, this.A00.schedule(r2, j, timeUnit));
    }
}
