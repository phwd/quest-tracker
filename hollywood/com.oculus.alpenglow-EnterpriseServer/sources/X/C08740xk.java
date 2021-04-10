package X;

import android.os.Handler;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: X.0xk  reason: invalid class name and case insensitive filesystem */
public final class C08740xk extends AbstractExecutorService implements AbstractExecutorServiceC07550vT {
    public final Handler A00;

    public final boolean isShutdown() {
        return false;
    }

    public final boolean isTerminated() {
        return false;
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    public final void execute(Runnable runnable) {
        this.A00.post(runnable);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public final void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ExecutorService
    public final List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    public C08740xk(Handler handler) {
        this.A00 = handler;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final Future submit(Callable callable) {
        if (callable != null) {
            RunnableC08770xn r0 = new RunnableC08770xn(this.A00, callable);
            execute(r0);
            return r0;
        }
        throw null;
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new RunnableFutureC08870xy(this.A00, runnable, t);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public final <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new RunnableFutureC08870xy(this.A00, callable);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final /* bridge */ /* synthetic */ Future submit(Runnable runnable, Object obj) {
        if (runnable != null) {
            RunnableC08770xn r0 = new RunnableC08770xn(this.A00, runnable, obj);
            execute(r0);
            return r0;
        }
        throw null;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        Handler handler = this.A00;
        RunnableC08770xn r2 = new RunnableC08770xn(handler, runnable, null);
        handler.postDelayed(r2, timeUnit.toMillis(j));
        return r2;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        Handler handler = this.A00;
        RunnableC08770xn r2 = new RunnableC08770xn(handler, callable);
        handler.postDelayed(r2, timeUnit.toMillis(j));
        return r2;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final Future submit(Runnable runnable) {
        if (runnable != null) {
            RunnableC08770xn r0 = new RunnableC08770xn(this.A00, runnable, null);
            execute(r0);
            return r0;
        }
        throw null;
    }
}
