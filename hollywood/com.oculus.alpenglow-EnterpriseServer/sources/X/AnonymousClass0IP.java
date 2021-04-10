package X;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.Partially$GwtIncompatible;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Beta
@GwtCompatible(emulated = true)
/* renamed from: X.0IP  reason: invalid class name */
public abstract class AnonymousClass0IP<V> extends AbstractC02220Xf<V> {
    public static <V> AnonymousClass0IP<V> from(ListenableFuture<V> listenableFuture) {
        if (listenableFuture instanceof AnonymousClass0IP) {
            return (AnonymousClass0IP) listenableFuture;
        }
        return new AnonymousClass0BW(listenableFuture);
    }

    public final void addCallback(AnonymousClass0Cg<? super V> r2, Executor executor) {
        if (r2 != null) {
            addListener(new AnonymousClass0CU(this, r2), executor);
            return;
        }
        throw null;
    }

    @Partially$GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public final <X extends Throwable> AnonymousClass0IP<V> catching(Class<X> cls, Function<? super X, ? extends V> function, Executor executor) {
        AnonymousClass003 r1 = new AnonymousClass003(this, cls, function);
        if (executor != null) {
            if (executor != AnonymousClass08h.INSTANCE) {
                executor = new ExecutorC007909g(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    @Partially$GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public final <X extends Throwable> AnonymousClass0IP<V> catchingAsync(Class<X> cls, AnonymousClass0Fa<? super X, ? extends V> r4, Executor executor) {
        AnonymousClass004 r1 = new AnonymousClass004(this, cls, r4);
        if (executor != null) {
            if (executor != AnonymousClass08h.INSTANCE) {
                executor = new ExecutorC007909g(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    public final <T> AnonymousClass0IP<T> transform(Function<? super V, T> function, Executor executor) {
        if (function != null) {
            AnonymousClass000 r1 = new AnonymousClass000(this, function);
            if (executor != null) {
                if (executor != AnonymousClass08h.INSTANCE) {
                    executor = new ExecutorC007909g(executor, r1);
                }
                addListener(r1, executor);
                return r1;
            }
            throw null;
        }
        throw null;
    }

    public final <T> AnonymousClass0IP<T> transformAsync(AnonymousClass0Fa<? super V, T> r3, Executor executor) {
        if (executor != null) {
            AnonymousClass002 r1 = new AnonymousClass002(this, r3);
            if (executor != AnonymousClass08h.INSTANCE) {
                executor = new ExecutorC007909g(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    @GwtIncompatible
    public final AnonymousClass0IP<V> withTimeout(long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        AnonymousClass03A r2 = new AnonymousClass03A(this);
        AnonymousClass00j r1 = new AnonymousClass00j(r2);
        r2.A01 = scheduledExecutorService.schedule(r1, j, timeUnit);
        addListener(r1, AnonymousClass08h.INSTANCE);
        return r2;
    }
}
