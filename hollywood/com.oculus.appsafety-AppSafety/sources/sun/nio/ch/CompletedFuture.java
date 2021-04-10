package sun.nio.ch;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

final class CompletedFuture<V> implements Future<V> {
    private final Throwable exc;
    private final V result;

    private CompletedFuture(V result2, Throwable exc2) {
        this.result = result2;
        this.exc = exc2;
    }

    static <V> CompletedFuture<V> withResult(V result2) {
        return new CompletedFuture<>(result2, null);
    }

    static <V> CompletedFuture<V> withFailure(Throwable exc2) {
        if (!(exc2 instanceof IOException) && !(exc2 instanceof SecurityException)) {
            exc2 = new IOException(exc2);
        }
        return new CompletedFuture<>(null, exc2);
    }

    static <V> CompletedFuture<V> withResult(V result2, Throwable exc2) {
        if (exc2 == null) {
            return withResult(result2);
        }
        return withFailure(exc2);
    }

    @Override // java.util.concurrent.Future
    public V get() throws ExecutionException {
        Throwable th = this.exc;
        if (th == null) {
            return this.result;
        }
        throw new ExecutionException(th);
    }

    @Override // java.util.concurrent.Future
    public V get(long timeout, TimeUnit unit) throws ExecutionException {
        if (unit != null) {
            Throwable th = this.exc;
            if (th == null) {
                return this.result;
            }
            throw new ExecutionException(th);
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return true;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }
}
