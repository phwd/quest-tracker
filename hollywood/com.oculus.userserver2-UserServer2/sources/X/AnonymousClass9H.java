package X;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.Partially$GwtIncompatible;
import com.oculus.common.build.BuildConfig;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Beta
@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
/* renamed from: X.9H  reason: invalid class name */
public abstract class AnonymousClass9H<V> extends MG<V> {
    public static <V> AnonymousClass9H<V> from(ListenableFuture<V> listenableFuture) {
        if (listenableFuture instanceof AnonymousClass9H) {
            return (AnonymousClass9H) listenableFuture;
        }
        return new AnonymousClass6e(listenableFuture);
    }

    public final void addCallback(XV<? super V> xv, Executor executor) {
        if (xv != null) {
            addListener(new RunnableC0188Xn(this, xv), executor);
            return;
        }
        throw null;
    }

    @Partially$GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public final <X extends Throwable> AnonymousClass9H<V> catching(Class<X> cls, Function<? super X, ? extends V> function, Executor executor) {
        AnonymousClass03 r1 = new AnonymousClass03(this, cls, function);
        if (executor != null) {
            if (executor != g1.INSTANCE) {
                executor = new fd(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    @Partially$GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public final <X extends Throwable> AnonymousClass9H<V> catchingAsync(Class<X> cls, XC<? super X, ? extends V> xc, Executor executor) {
        AnonymousClass04 r1 = new AnonymousClass04(this, cls, xc);
        if (executor != null) {
            if (executor != g1.INSTANCE) {
                executor = new fd(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    public final <T> AnonymousClass9H<T> transform(Function<? super V, T> function, Executor executor) {
        if (function != null) {
            AnonymousClass00 r1 = new AnonymousClass00(this, function);
            if (executor != null) {
                if (executor != g1.INSTANCE) {
                    executor = new fd(executor, r1);
                }
                addListener(r1, executor);
                return r1;
            }
            throw null;
        }
        throw null;
    }

    public final <T> AnonymousClass9H<T> transformAsync(XC<? super V, T> xc, Executor executor) {
        if (executor != null) {
            AnonymousClass02 r1 = new AnonymousClass02(this, xc);
            if (executor != g1.INSTANCE) {
                executor = new fd(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    @GwtIncompatible
    public final AnonymousClass9H<V> withTimeout(long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        AnonymousClass0C r2 = new AnonymousClass0C(this);
        gL gLVar = new gL(r2);
        r2.A01 = scheduledExecutorService.schedule(gLVar, j, timeUnit);
        addListener(gLVar, g1.INSTANCE);
        return r2;
    }
}
