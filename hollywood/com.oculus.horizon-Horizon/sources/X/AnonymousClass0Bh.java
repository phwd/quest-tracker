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
/* renamed from: X.0Bh  reason: invalid class name */
public abstract class AnonymousClass0Bh<V> extends AbstractC03400cp<V> {
    public static <V> AnonymousClass0Bh<V> from(ListenableFuture<V> listenableFuture) {
        if (listenableFuture instanceof AnonymousClass0Bh) {
            return (AnonymousClass0Bh) listenableFuture;
        }
        return new AnonymousClass06Y(listenableFuture);
    }

    public final void addCallback(AbstractC08630wz<? super V> r2, Executor executor) {
        if (r2 != null) {
            addListener(new RunnableC08640x3(this, r2), executor);
            return;
        }
        throw null;
    }

    @Partially$GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public final <X extends Throwable> AnonymousClass0Bh<V> catching(Class<X> cls, Function<? super X, ? extends V> function, Executor executor) {
        AnonymousClass003 r1 = new AnonymousClass003(this, cls, function);
        if (executor != null) {
            if (executor != EnumC08710xT.INSTANCE) {
                executor = new ExecutorC08700xQ(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    @Partially$GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public final <X extends Throwable> AnonymousClass0Bh<V> catchingAsync(Class<X> cls, AbstractC08460wc<? super X, ? extends V> r4, Executor executor) {
        AnonymousClass004 r1 = new AnonymousClass004(this, cls, r4);
        if (executor != null) {
            if (executor != EnumC08710xT.INSTANCE) {
                executor = new ExecutorC08700xQ(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    public final <T> AnonymousClass0Bh<T> transform(Function<? super V, T> function, Executor executor) {
        if (function != null) {
            AnonymousClass000 r1 = new AnonymousClass000(this, function);
            if (executor != null) {
                if (executor != EnumC08710xT.INSTANCE) {
                    executor = new ExecutorC08700xQ(executor, r1);
                }
                addListener(r1, executor);
                return r1;
            }
            throw null;
        }
        throw null;
    }

    public final <T> AnonymousClass0Bh<T> transformAsync(AbstractC08460wc<? super V, T> r3, Executor executor) {
        if (executor != null) {
            AnonymousClass002 r1 = new AnonymousClass002(this, r3);
            if (executor != EnumC08710xT.INSTANCE) {
                executor = new ExecutorC08700xQ(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    @GwtIncompatible
    public final AnonymousClass0Bh<V> withTimeout(long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        AnonymousClass00A r2 = new AnonymousClass00A(this);
        RunnableC08730xy r1 = new RunnableC08730xy(r2);
        r2.A01 = scheduledExecutorService.schedule(r1, j, timeUnit);
        addListener(r1, EnumC08710xT.INSTANCE);
        return r2;
    }
}
