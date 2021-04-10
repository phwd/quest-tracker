package X;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1XK  reason: invalid class name */
public final class AnonymousClass1XK<V> implements Future<V> {
    public static final AnonymousClass1XK<Void> A01 = new AnonymousClass1XK<>(null);
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

    public AnonymousClass1XK(V v) {
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
