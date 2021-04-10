package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
abstract class ImmediateFuture<V> extends FluentFuture<V> {
    private static final Logger log = Logger.getLogger(ImmediateFuture.class.getName());

    public boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public abstract V get() throws ExecutionException;

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return true;
    }

    ImmediateFuture() {
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = log;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
        }
    }

    @Override // java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) throws ExecutionException {
        Preconditions.checkNotNull(timeUnit);
        return get();
    }

    /* access modifiers changed from: package-private */
    public static class ImmediateSuccessfulFuture<V> extends ImmediateFuture<V> {
        static final ImmediateSuccessfulFuture<Object> NULL = new ImmediateSuccessfulFuture<>(null);
        @NullableDecl
        private final V value;

        ImmediateSuccessfulFuture(@NullableDecl V v) {
            this.value = v;
        }

        @Override // java.util.concurrent.Future, com.google.common.util.concurrent.ImmediateFuture
        public V get() {
            return this.value;
        }

        public String toString() {
            return super.toString() + "[status=SUCCESS, result=[" + ((Object) this.value) + "]]";
        }
    }

    @GwtIncompatible
    static class ImmediateSuccessfulCheckedFuture<V, X extends Exception> extends ImmediateFuture<V> implements CheckedFuture<V, X> {
        @NullableDecl
        private final V value;

        ImmediateSuccessfulCheckedFuture(@NullableDecl V v) {
            this.value = v;
        }

        @Override // java.util.concurrent.Future, com.google.common.util.concurrent.ImmediateFuture
        public V get() {
            return this.value;
        }

        @Override // com.google.common.util.concurrent.CheckedFuture
        public V checkedGet() {
            return this.value;
        }

        @Override // com.google.common.util.concurrent.CheckedFuture
        public V checkedGet(long j, TimeUnit timeUnit) {
            Preconditions.checkNotNull(timeUnit);
            return this.value;
        }

        public String toString() {
            return super.toString() + "[status=SUCCESS, result=[" + ((Object) this.value) + "]]";
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ImmediateFailedFuture<V> extends AbstractFuture.TrustedFuture<V> {
        ImmediateFailedFuture(Throwable th) {
            setException(th);
        }
    }

    static final class ImmediateCancelledFuture<V> extends AbstractFuture.TrustedFuture<V> {
        ImmediateCancelledFuture() {
            cancel(false);
        }
    }

    @GwtIncompatible
    static class ImmediateFailedCheckedFuture<V, X extends Exception> extends ImmediateFuture<V> implements CheckedFuture<V, X> {
        private final X thrown;

        ImmediateFailedCheckedFuture(X x) {
            this.thrown = x;
        }

        @Override // java.util.concurrent.Future, com.google.common.util.concurrent.ImmediateFuture
        public V get() throws ExecutionException {
            throw new ExecutionException(this.thrown);
        }

        @Override // com.google.common.util.concurrent.CheckedFuture
        public V checkedGet() throws Exception {
            throw this.thrown;
        }

        @Override // com.google.common.util.concurrent.CheckedFuture
        public V checkedGet(long j, TimeUnit timeUnit) throws Exception {
            Preconditions.checkNotNull(timeUnit);
            throw this.thrown;
        }

        public String toString() {
            return super.toString() + "[status=FAILURE, cause=[" + this.thrown + "]]";
        }
    }
}
