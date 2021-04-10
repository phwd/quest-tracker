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
/* renamed from: X.0M1  reason: invalid class name */
public abstract class AnonymousClass0M1<V> extends AbstractC01480eg<V> {
    public static <V> AnonymousClass0M1<V> from(ListenableFuture<V> listenableFuture) {
        if (listenableFuture instanceof AnonymousClass0M1) {
            return (AnonymousClass0M1) listenableFuture;
        }
        return new AnonymousClass0BQ(listenableFuture);
    }

    public final void addCallback(AbstractC057411o<? super V> r2, Executor executor) {
        if (r2 != null) {
            addListener(new AnonymousClass11s(this, r2), executor);
            return;
        }
        throw null;
    }

    @Partially$GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public final <X extends Throwable> AnonymousClass0M1<V> catching(Class<X> cls, Function<? super X, ? extends V> function, Executor executor) {
        AnonymousClass003 r1 = new AnonymousClass003(this, cls, function);
        if (executor != null) {
            if (executor != AnonymousClass12I.INSTANCE) {
                executor = new AnonymousClass12F(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    @Partially$GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public final <X extends Throwable> AnonymousClass0M1<V> catchingAsync(Class<X> cls, AnonymousClass11O<? super X, ? extends V> r4, Executor executor) {
        AnonymousClass004 r1 = new AnonymousClass004(this, cls, r4);
        if (executor != null) {
            if (executor != AnonymousClass12I.INSTANCE) {
                executor = new AnonymousClass12F(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    public final <T> AnonymousClass0M1<T> transform(Function<? super V, T> function, Executor executor) {
        if (function != null) {
            AnonymousClass000 r1 = new AnonymousClass000(this, function);
            if (executor != null) {
                if (executor != AnonymousClass12I.INSTANCE) {
                    executor = new AnonymousClass12F(executor, r1);
                }
                addListener(r1, executor);
                return r1;
            }
            throw null;
        }
        throw null;
    }

    public final <T> AnonymousClass0M1<T> transformAsync(AnonymousClass11O<? super V, T> r3, Executor executor) {
        if (executor != null) {
            AnonymousClass002 r1 = new AnonymousClass002(this, r3);
            if (executor != AnonymousClass12I.INSTANCE) {
                executor = new AnonymousClass12F(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    @GwtIncompatible
    public final AnonymousClass0M1<V> withTimeout(long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        AnonymousClass00D r2 = new AnonymousClass00D(this);
        AnonymousClass12m r1 = new AnonymousClass12m(r2);
        r2.A01 = scheduledExecutorService.schedule(r1, j, timeUnit);
        addListener(r1, AnonymousClass12I.INSTANCE);
        return r2;
    }
}
