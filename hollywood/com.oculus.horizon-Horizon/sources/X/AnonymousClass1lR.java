package X;

import android.os.Handler;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;

/* renamed from: X.1lR  reason: invalid class name */
public final class AnonymousClass1lR<V> implements RunnableFuture<V>, ScheduledFuture<V> {
    public final Handler A00;
    public final FutureTask<V> A01;

    public final boolean cancel(boolean z) {
        return this.A01.cancel(z);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final long getDelay(TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public final boolean isCancelled() {
        return this.A01.isCancelled();
    }

    public final boolean isDone() {
        return this.A01.isDone();
    }

    public final void run() {
        this.A01.run();
    }

    public AnonymousClass1lR(Handler handler, Runnable runnable, @Nullable V v) {
        this.A00 = handler;
        this.A01 = new FutureTask<>(runnable, v);
    }

    public AnonymousClass1lR(Handler handler, Callable<V> callable) {
        this.A00 = handler;
        this.A01 = new FutureTask<>(callable);
    }

    @Override // java.util.concurrent.Future
    public final V get() throws InterruptedException, ExecutionException {
        return this.A01.get();
    }

    @Override // java.util.concurrent.Future
    public final V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.A01.get(j, timeUnit);
    }
}
