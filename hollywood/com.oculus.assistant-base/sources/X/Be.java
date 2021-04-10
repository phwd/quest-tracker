package X;

import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class Be extends AbstractC1193uu {
    public static Be from(ListenableFuture listenableFuture) {
        if (listenableFuture instanceof Be) {
            return (Be) listenableFuture;
        }
        return new SF(listenableFuture);
    }

    public final Be catching(Class cls, Function function, Executor executor) {
        C00130y r1 = new C00130y(this, cls, function);
        if (executor != null) {
            if (executor != V3.INSTANCE) {
                executor = new V2(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    public final Be catchingAsync(Class cls, AbstractC0382Ut ut, Executor executor) {
        AnonymousClass12 r1 = new AnonymousClass12(this, cls, ut);
        if (executor != null) {
            if (executor != V3.INSTANCE) {
                executor = new V2(executor, r1);
            }
            addListener(r1, executor);
            return r1;
        }
        throw null;
    }

    public final Be withTimeout(long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        AnonymousClass1L r2 = new AnonymousClass1L(this);
        V6 v6 = new V6(r2);
        r2.A01 = scheduledExecutorService.schedule(v6, j, timeUnit);
        addListener(v6, V3.INSTANCE);
        return r2;
    }

    public final Be transform(Function function, Executor executor) {
        return (Be) AnonymousClass1O.A00(this, function, executor);
    }

    public final Be transformAsync(AbstractC0382Ut ut, Executor executor) {
        return (Be) AnonymousClass1O.A01(this, ut, executor);
    }

    public final void addCallback(AbstractC0383Uv uv, Executor executor) {
        C1192ut.A00(this, uv, executor);
    }
}
