package X;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
/* renamed from: X.0BN  reason: invalid class name */
public final class AnonymousClass0BN extends AnonymousClass0Lw implements AbstractScheduledExecutorServiceC01470ef {
    public final ScheduledExecutorService A00;

    @Override // X.AbstractScheduledExecutorServiceC01470ef
    /* renamed from: A9V */
    public final ListenableScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        AnonymousClass00C r2 = new AnonymousClass00C(Executors.callable(runnable, null));
        return new AnonymousClass02g(r2, this.A00.schedule(r2, j, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        AnonymousClass02f r2 = new AnonymousClass02f(runnable);
        return new AnonymousClass02g(r2, this.A00.scheduleAtFixedRate(r2, j, j2, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        AnonymousClass02f r2 = new AnonymousClass02f(runnable);
        return new AnonymousClass02g(r2, this.A00.scheduleWithFixedDelay(r2, j, j2, timeUnit));
    }

    public AnonymousClass0BN(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        if (scheduledExecutorService != null) {
            this.A00 = scheduledExecutorService;
            return;
        }
        throw null;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        AnonymousClass00C r2 = new AnonymousClass00C(callable);
        return new AnonymousClass02g(r2, this.A00.schedule(r2, j, timeUnit));
    }
}
