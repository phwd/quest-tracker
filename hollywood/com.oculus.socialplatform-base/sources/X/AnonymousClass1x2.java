package X;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1x2  reason: invalid class name */
public class AnonymousClass1x2 extends AbstractC12411xQ implements AbstractC12271xB {
    public final ScheduledExecutorService A00;
    public volatile boolean A01;

    @NonNull
    public final RunnableC12241x8 A03(Runnable runnable, long j, @NonNull TimeUnit timeUnit, @Nullable AbstractC12231x7 r9) {
        Future<?> future;
        AnonymousClass219.A01(runnable, "run is null");
        RunnableC12241x8 r3 = new RunnableC12241x8(runnable, r9);
        if (r9 != null && !r9.A1D(r3)) {
            return r3;
        }
        if (j <= 0) {
            try {
                future = this.A00.submit((Callable) r3);
            } catch (RejectedExecutionException e) {
                if (r9 != null) {
                    r9.A95(r3);
                }
                AnonymousClass1y3.A01(e);
                return r3;
            }
        } else {
            future = this.A00.schedule((Callable) r3, j, timeUnit);
        }
        r3.A00(future);
        return r3;
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (!this.A01) {
            this.A01 = true;
            this.A00.shutdownNow();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: java.util.Map<java.util.concurrent.ScheduledThreadPoolExecutor, java.lang.Object> */
    /* JADX WARN: Multi-variable type inference failed */
    public AnonymousClass1x2(ThreadFactory threadFactory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (C06641an.A03 && (newScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            C06641an.A01.put(newScheduledThreadPool, newScheduledThreadPool);
        }
        this.A00 = newScheduledThreadPool;
    }
}
