package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@GwtCompatible
/* renamed from: X.6e  reason: invalid class name */
public final class AnonymousClass6e<V> extends AnonymousClass9H<V> {
    public final ListenableFuture<V> A00;

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        this.A00.addListener(runnable, executor);
    }

    public final boolean cancel(boolean z) {
        return this.A00.cancel(z);
    }

    public final boolean isCancelled() {
        return this.A00.isCancelled();
    }

    public final boolean isDone() {
        return this.A00.isDone();
    }

    public AnonymousClass6e(ListenableFuture<V> listenableFuture) {
        if (listenableFuture != null) {
            this.A00 = listenableFuture;
            return;
        }
        throw null;
    }

    @Override // java.util.concurrent.Future
    public final V get() throws InterruptedException, ExecutionException {
        return this.A00.get();
    }

    @Override // java.util.concurrent.Future
    public final V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.A00.get(j, timeUnit);
    }
}
