package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.errorprone.annotations.ForOverride;
import java.lang.Throwable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible
public abstract class AbstractCatchingFuture<V, X extends Throwable, F, T> extends AbstractFuture.TrustedFuture<V> implements Runnable {
    @NullableDecl
    Class<X> exceptionType;
    @NullableDecl
    F fallback;
    @NullableDecl
    ListenableFuture<? extends V> inputFuture;

    /* access modifiers changed from: package-private */
    @NullableDecl
    @ForOverride
    public abstract T doFallback(F f, X x) throws Exception;

    /* access modifiers changed from: package-private */
    @ForOverride
    public abstract void setResult(@NullableDecl T t);

    static <V, X extends Throwable> ListenableFuture<V> create(ListenableFuture<? extends V> input, Class<X> exceptionType2, Function<? super X, ? extends V> fallback2, Executor executor) {
        CatchingFuture<V, X> future = new CatchingFuture<>(input, exceptionType2, fallback2);
        input.addListener(future, MoreExecutors.rejectionPropagatingExecutor(executor, future));
        return future;
    }

    static <X extends Throwable, V> ListenableFuture<V> create(ListenableFuture<? extends V> input, Class<X> exceptionType2, AsyncFunction<? super X, ? extends V> fallback2, Executor executor) {
        AsyncCatchingFuture<V, X> future = new AsyncCatchingFuture<>(input, exceptionType2, fallback2);
        input.addListener(future, MoreExecutors.rejectionPropagatingExecutor(executor, future));
        return future;
    }

    AbstractCatchingFuture(ListenableFuture<? extends V> inputFuture2, Class<X> exceptionType2, F fallback2) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(inputFuture2);
        this.exceptionType = (Class) Preconditions.checkNotNull(exceptionType2);
        this.fallback = (F) Preconditions.checkNotNull(fallback2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r14v0, resolved type: com.google.common.util.concurrent.AbstractCatchingFuture<V, X extends java.lang.Throwable, F, T> */
    /* JADX WARN: Multi-variable type inference failed */
    public final void run() {
        boolean z = true;
        ListenableFuture<? extends V> localInputFuture = this.inputFuture;
        Class<X> localExceptionType = this.exceptionType;
        F localFallback = this.fallback;
        boolean z2 = (localExceptionType == null) | (localInputFuture == null);
        if (localFallback != null) {
            z = false;
        }
        if (!(z | z2) && !isCancelled()) {
            this.inputFuture = null;
            Object obj = null;
            Throwable throwable = null;
            try {
                obj = Futures.getDone(localInputFuture);
            } catch (ExecutionException e) {
                throwable = (Throwable) Preconditions.checkNotNull(e.getCause());
            } catch (Throwable e2) {
                throwable = e2;
            }
            if (throwable == null) {
                set(obj);
            } else if (!Platform.isInstanceOfThrowableClass(throwable, localExceptionType)) {
                setException(throwable);
            } else {
                try {
                    T fallbackResult = doFallback(localFallback, throwable);
                    this.exceptionType = null;
                    this.fallback = null;
                    setResult(fallbackResult);
                } catch (Throwable th) {
                    this.exceptionType = null;
                    this.fallback = null;
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        ListenableFuture<? extends V> localInputFuture = this.inputFuture;
        Class<X> localExceptionType = this.exceptionType;
        F localFallback = this.fallback;
        String superString = super.pendingToString();
        String resultString = "";
        if (localInputFuture != null) {
            resultString = "inputFuture=[" + localInputFuture + "], ";
        }
        if (localExceptionType != null && localFallback != null) {
            return resultString + "exceptionType=[" + localExceptionType + "], fallback=[" + ((Object) localFallback) + "]";
        }
        if (superString != null) {
            return resultString + superString;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
    }

    /* access modifiers changed from: private */
    public static final class AsyncCatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, AsyncFunction<? super X, ? extends V>, ListenableFuture<? extends V>> {
        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Throwable */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public /* bridge */ /* synthetic */ Object doFallback(Object obj, Throwable th) throws Exception {
            return doFallback((AsyncFunction) ((AsyncFunction) obj), th);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public /* bridge */ /* synthetic */ void setResult(Object obj) {
            setResult((ListenableFuture) ((ListenableFuture) obj));
        }

        AsyncCatchingFuture(ListenableFuture<? extends V> input, Class<X> exceptionType, AsyncFunction<? super X, ? extends V> fallback) {
            super(input, exceptionType, fallback);
        }

        /* access modifiers changed from: package-private */
        public ListenableFuture<? extends V> doFallback(AsyncFunction<? super X, ? extends V> fallback, X cause) throws Exception {
            ListenableFuture<? extends V> replacement = fallback.apply(cause);
            Preconditions.checkNotNull(replacement, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)?");
            return replacement;
        }

        /* access modifiers changed from: package-private */
        public void setResult(ListenableFuture<? extends V> result) {
            setFuture(result);
        }
    }

    /* access modifiers changed from: private */
    public static final class CatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, Function<? super X, ? extends V>, V> {
        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Throwable */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        @NullableDecl
        public /* bridge */ /* synthetic */ Object doFallback(Object obj, Throwable th) throws Exception {
            return doFallback((Function) ((Function) obj), th);
        }

        CatchingFuture(ListenableFuture<? extends V> input, Class<X> exceptionType, Function<? super X, ? extends V> fallback) {
            super(input, exceptionType, fallback);
        }

        /* access modifiers changed from: package-private */
        @NullableDecl
        public V doFallback(Function<? super X, ? extends V> fallback, X cause) throws Exception {
            return (V) fallback.apply(cause);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public void setResult(@NullableDecl V result) {
            set(result);
        }
    }
}
