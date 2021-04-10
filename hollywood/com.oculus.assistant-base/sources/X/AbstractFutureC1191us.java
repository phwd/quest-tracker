package X;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: X.us  reason: case insensitive filesystem */
public abstract class AbstractFutureC1191us extends Tu implements Future {
    public final Future A00() {
        return ((SE) ((Bb) this)).A00;
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
    public final Object get() {
        return A00().get();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) {
        return A00().get(j, timeUnit);
    }
}
