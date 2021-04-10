package X;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: X.0XA  reason: invalid class name */
public final class AnonymousClass0XA<V> implements Future<V> {
    public static final AnonymousClass0XA<Void> A01 = new AnonymousClass0XA<>(null);
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

    public AnonymousClass0XA(V v) {
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
