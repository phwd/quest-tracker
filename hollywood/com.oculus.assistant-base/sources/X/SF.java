package X;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public final class SF extends Be {
    public final ListenableFuture A00;

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

    public SF(ListenableFuture listenableFuture) {
        if (listenableFuture != null) {
            this.A00 = listenableFuture;
            return;
        }
        throw null;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.A00.get();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) {
        return this.A00.get(j, timeUnit);
    }
}
