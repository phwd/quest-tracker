package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@GwtCompatible
@CanIgnoreReturnValue
public abstract class MJ<V> extends QZ implements Future<V> {
    public final Future<? extends V> A00() {
        return ((AnonymousClass6d) ((AnonymousClass9G) this)).A00;
    }

    public boolean cancel(boolean z) {
        return A00().cancel(z);
    }

    public final boolean isCancelled() {
        return A00().isCancelled();
    }

    public final boolean isDone() {
        return A00().isDone();
    }

    @Override // java.util.concurrent.Future
    public final V get() throws InterruptedException, ExecutionException {
        return (V) A00().get();
    }

    @Override // java.util.concurrent.Future
    public final V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return (V) A00().get(j, timeUnit);
    }
}
