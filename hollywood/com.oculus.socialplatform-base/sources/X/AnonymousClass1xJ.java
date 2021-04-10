package X;

import io.reactivex.annotations.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1xJ  reason: invalid class name */
public final class AnonymousClass1xJ extends AbstractC12361xL {
    public static final AbstractC12361xL A01 = C12581xp.A03;
    @NonNull
    public final Executor A00;

    @Override // X.AbstractC12361xL
    @NonNull
    public final AbstractC12271xB A02(@NonNull Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Executor executor = this.A00;
        if (!(executor instanceof ScheduledExecutorService)) {
            return super.A02(runnable, j, j2, timeUnit);
        }
        AnonymousClass219.A01(runnable, "run is null");
        try {
            RunnableC12171ww r2 = new RunnableC12171ww(runnable);
            r2.A00(((ScheduledExecutorService) executor).scheduleAtFixedRate(r2, j, j2, timeUnit));
            return r2;
        } catch (RejectedExecutionException e) {
            AnonymousClass1y3.A01(e);
            return AnonymousClass1z1.INSTANCE;
        }
    }

    public AnonymousClass1xJ(@NonNull Executor executor) {
        this.A00 = executor;
    }
}
