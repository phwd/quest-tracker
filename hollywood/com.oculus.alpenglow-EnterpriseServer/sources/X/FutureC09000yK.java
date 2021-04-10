package X;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: X.0yK  reason: invalid class name and case insensitive filesystem */
public final class FutureC09000yK<V> implements Future<V> {
    public static final FutureC09000yK<Void> A01 = new FutureC09000yK<>(null);
    public final V A00;

    public final boolean cancel(boolean z) {
        return false;
    }

    public final boolean isCancelled() {
        return false;
    }

    public final boolean isDone() {
        return true;
    }

    public FutureC09000yK(V v) {
        this.A00 = v;
    }

    @Override // java.util.concurrent.Future
    public final V get() throws ExecutionException {
        return this.A00;
    }

    @Override // java.util.concurrent.Future
    public final V get(long j, TimeUnit timeUnit) throws ExecutionException {
        return get();
    }
}
