package com.google.common.util.concurrent;

import com.facebook.common.build.config.BuildConfig;
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

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
abstract class ImmediateFuture<V> extends FluentFuture<V> {
    private static final Logger log = Logger.getLogger(ImmediateFuture.class.getName());

    @Override // java.util.concurrent.Future
    public abstract V get() throws ExecutionException;

    ImmediateFuture() {
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable listener, Executor executor) {
        Preconditions.checkNotNull(listener, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        try {
            executor.execute(listener);
        } catch (RuntimeException e) {
            log.log(Level.SEVERE, "RuntimeException while executing runnable " + listener + " with executor " + executor, (Throwable) e);
        }
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public V get(long timeout, TimeUnit unit) throws ExecutionException {
        Preconditions.checkNotNull(unit);
        return get();
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public static class ImmediateSuccessfulFuture<V> extends ImmediateFuture<V> {
        static final ImmediateSuccessfulFuture<Object> NULL = new ImmediateSuccessfulFuture<>(null);
        @NullableDecl
        private final V value;

        ImmediateSuccessfulFuture(@NullableDecl V value2) {
            this.value = value2;
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

        ImmediateSuccessfulCheckedFuture(@NullableDecl V value2) {
            this.value = value2;
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
        public V checkedGet(long timeout, TimeUnit unit) {
            Preconditions.checkNotNull(unit);
            return this.value;
        }

        public String toString() {
            return super.toString() + "[status=SUCCESS, result=[" + ((Object) this.value) + "]]";
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ImmediateFailedFuture<V> extends AbstractFuture.TrustedFuture<V> {
        ImmediateFailedFuture(Throwable thrown) {
            setException(thrown);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ImmediateCancelledFuture<V> extends AbstractFuture.TrustedFuture<V> {
        ImmediateCancelledFuture() {
            cancel(false);
        }
    }

    @GwtIncompatible
    static class ImmediateFailedCheckedFuture<V, X extends Exception> extends ImmediateFuture<V> implements CheckedFuture<V, X> {
        private final X thrown;

        ImmediateFailedCheckedFuture(X thrown2) {
            this.thrown = thrown2;
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
        public V checkedGet(long timeout, TimeUnit unit) throws Exception {
            Preconditions.checkNotNull(unit);
            throw this.thrown;
        }

        public String toString() {
            return super.toString() + "[status=FAILURE, cause=[" + this.thrown + "]]";
        }
    }
}
