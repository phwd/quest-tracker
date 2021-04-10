package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0IF  reason: invalid class name */
public final class AnonymousClass0IF extends AbstractExecutorService {
    public static final AnonymousClass0IF A00 = new AnonymousClass0IF();

    @Override // java.util.concurrent.ExecutorService
    public final boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return true;
    }

    public final boolean isShutdown() {
        return false;
    }

    public final boolean isTerminated() {
        return false;
    }

    public final void shutdown() {
    }

    @Override // java.util.concurrent.ExecutorService
    public final List<Runnable> shutdownNow() {
        return Collections.emptyList();
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
