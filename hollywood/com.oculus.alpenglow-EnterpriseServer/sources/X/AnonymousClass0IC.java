package X;

import com.google.common.annotations.GwtIncompatible;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
/* renamed from: X.0IC  reason: invalid class name */
public class AnonymousClass0IC extends AbstractC02240Xi {
    public final ExecutorService A00;

    @Override // java.util.concurrent.ExecutorService
    public final boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.A00.awaitTermination(j, timeUnit);
    }

    public final void execute(Runnable runnable) {
        this.A00.execute(runnable);
    }

    public final boolean isShutdown() {
        return this.A00.isShutdown();
    }

    public final boolean isTerminated() {
        return this.A00.isTerminated();
    }

    public final void shutdown() {
        this.A00.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public final List<Runnable> shutdownNow() {
        return this.A00.shutdownNow();
    }

    public AnonymousClass0IC(ExecutorService executorService) {
        if (executorService != null) {
            this.A00 = executorService;
            return;
        }
        throw null;
    }
}
