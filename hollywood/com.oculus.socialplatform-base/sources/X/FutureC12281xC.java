package X;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: X.1xC  reason: invalid class name and case insensitive filesystem */
public final class FutureC12281xC implements Future<Object> {
    public final AbstractC12271xB A00;

    @Override // java.util.concurrent.Future
    public final Object get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    public final boolean isCancelled() {
        return false;
    }

    public final boolean isDone() {
        return false;
    }

    public final boolean cancel(boolean z) {
        this.A00.dispose();
        return false;
    }

    public FutureC12281xC(AbstractC12271xB r1) {
        this.A00 = r1;
    }
}
