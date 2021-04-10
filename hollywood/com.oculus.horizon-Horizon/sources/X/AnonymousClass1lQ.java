package X;

import android.os.Handler;
import com.facebook.infer.annotation.Nullsafe;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1lQ  reason: invalid class name */
public class AnonymousClass1lQ extends AbstractExecutorService implements ScheduledExecutorService {
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

    public void execute(Runnable runnable) {
        this.A00.post(runnable);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public final void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ExecutorService
    public final List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    public AnonymousClass1lQ(Handler handler) {
        this.A00 = handler;
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public final /* bridge */ /* synthetic */ RunnableFuture newTaskFor(Runnable runnable, @Nullable Object obj) {
        return new AnonymousClass1lR(this.A00, runnable, obj);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    public final /* bridge */ /* synthetic */ RunnableFuture newTaskFor(Callable callable) {
        return new AnonymousClass1lR(this.A00, callable);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        Handler handler = this.A00;
        AnonymousClass1lR r2 = new AnonymousClass1lR(handler, runnable, null);
        handler.postDelayed(r2, timeUnit.toMillis(j));
        return r2;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        Handler handler = this.A00;
        AnonymousClass1lR r2 = new AnonymousClass1lR(handler, callable);
        handler.postDelayed(r2, timeUnit.toMillis(j));
        return r2;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final Future submit(Runnable runnable) {
        if (runnable != null) {
            AnonymousClass1lR r0 = new AnonymousClass1lR(this.A00, runnable, null);
            execute(r0);
            return r0;
        }
        throw null;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final /* bridge */ /* synthetic */ Future submit(Runnable runnable, @Nullable Object obj) {
        if (runnable != null) {
            AnonymousClass1lR r0 = new AnonymousClass1lR(this.A00, runnable, obj);
            execute(r0);
            return r0;
        }
        throw null;
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final Future submit(Callable callable) {
        if (callable != null) {
            AnonymousClass1lR r0 = new AnonymousClass1lR(this.A00, callable);
            execute(r0);
            return r0;
        }
        throw null;
    }
}
