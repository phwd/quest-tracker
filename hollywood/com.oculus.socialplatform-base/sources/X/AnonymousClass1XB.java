package X;

import android.os.Handler;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1XB  reason: invalid class name */
public final class AnonymousClass1XB extends AbstractExecutorService implements AnonymousClass1XF {
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

    public AnonymousClass1XB(Handler handler) {
        this.A00 = handler;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final Future submit(Callable callable) {
        if (callable != null) {
            AnonymousClass1X9 r0 = new AnonymousClass1X9(this.A00, callable);
            execute(r0);
            return r0;
        }
        throw null;
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new AnonymousClass1XA(this.A00, runnable, t);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public final <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new AnonymousClass1XA(this.A00, callable);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final /* bridge */ /* synthetic */ Future submit(Runnable runnable, Object obj) {
        if (runnable != null) {
            AnonymousClass1X9 r0 = new AnonymousClass1X9(this.A00, runnable, obj);
            execute(r0);
            return r0;
        }
        throw null;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        Handler handler = this.A00;
        AnonymousClass1X9 r2 = new AnonymousClass1X9(handler, runnable, null);
        handler.postDelayed(r2, timeUnit.toMillis(j));
        return r2;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        Handler handler = this.A00;
        AnonymousClass1X9 r2 = new AnonymousClass1X9(handler, callable);
        handler.postDelayed(r2, timeUnit.toMillis(j));
        return r2;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final Future submit(Runnable runnable) {
        if (runnable != null) {
            AnonymousClass1X9 r0 = new AnonymousClass1X9(this.A00, runnable, null);
            execute(r0);
            return r0;
        }
        throw null;
    }
}
