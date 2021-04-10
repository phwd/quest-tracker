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
/* renamed from: X.Ay  reason: case insensitive filesystem */
public abstract class AbstractC0060Ay<V> extends UF<V> {
    public static <V> AbstractC0060Ay<V> from(ListenableFuture<V> listenableFuture) {
        if (listenableFuture instanceof AbstractC0060Ay) {
            return (AbstractC0060Ay) listenableFuture;
        }
        return new C00116i(listenableFuture);
    }

    public final void addCallback(B6<? super V> b6, Executor executor) {
        if (b6 != null) {
            addListener(new BL(this, b6), executor);
            return;
        }
        throw null;
    }

    @Partially$GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public final <X extends Throwable> AbstractC0060Ay<V> catching(Class<X> cls, Function<? super X, ? extends V> function, Executor executor) {
        AnonymousClass03 r1 = new AnonymousClass03(this, cls, function);
        if (executor != null) {
            if (executor != EnumC0063Bj.INSTANCE) {
                executor = new Bi(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    @Partially$GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public final <X extends Throwable> AbstractC0060Ay<V> catchingAsync(Class<X> cls, B4<? super X, ? extends V> b4, Executor executor) {
        AnonymousClass04 r1 = new AnonymousClass04(this, cls, b4);
        if (executor != null) {
            if (executor != EnumC0063Bj.INSTANCE) {
                executor = new Bi(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    public final <T> AbstractC0060Ay<T> transform(Function<? super V, T> function, Executor executor) {
        if (function != null) {
            AnonymousClass00 r1 = new AnonymousClass00(this, function);
            if (executor != null) {
                if (executor != EnumC0063Bj.INSTANCE) {
                    executor = new Bi(executor, r1);
                }
                addListener(r1, executor);
                return r1;
            }
            throw null;
        }
        throw null;
    }

    public final <T> AbstractC0060Ay<T> transformAsync(B4<? super V, T> b4, Executor executor) {
        if (executor != null) {
            AnonymousClass02 r1 = new AnonymousClass02(this, b4);
            if (executor != EnumC0063Bj.INSTANCE) {
                executor = new Bi(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    @GwtIncompatible
    public final AbstractC0060Ay<V> withTimeout(long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        AnonymousClass0A r2 = new AnonymousClass0A(this);
        CT ct = new CT(r2);
        r2.A01 = scheduledExecutorService.schedule(ct, j, timeUnit);
        addListener(ct, EnumC0063Bj.INSTANCE);
        return r2;
    }
}
