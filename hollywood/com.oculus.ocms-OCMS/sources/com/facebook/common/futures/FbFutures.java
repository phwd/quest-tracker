package com.facebook.common.futures;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;

public class FbFutures {
    public static <I, O> ListenableFuture<O> lazyTransform(final ListenableFuture<I> listenableFuture, final Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(listenableFuture);
        Preconditions.checkNotNull(function);
        return new ListenableFuture<O>() {
            /* class com.facebook.common.futures.FbFutures.AnonymousClass1 */

            @Override // com.google.common.util.concurrent.ListenableFuture
            public void addListener(Runnable runnable, Executor executor) {
                ListenableFuture.this.addListener(runnable, executor);
            }

            public boolean cancel(boolean z) {
                return ListenableFuture.this.cancel(z);
            }

            public boolean isCancelled() {
                return ListenableFuture.this.isCancelled();
            }

            public boolean isDone() {
                return ListenableFuture.this.isDone();
            }

            @Override // java.util.concurrent.Future
            public O get() throws InterruptedException, ExecutionException {
                return (O) applyTransformation(ListenableFuture.this.get());
            }

            @Override // java.util.concurrent.Future
            public O get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
                return (O) applyTransformation(ListenableFuture.this.get(j, timeUnit));
            }

            private O applyTransformation(I i) throws ExecutionException {
                try {
                    return (O) function.apply(i);
                } catch (Throwable th) {
                    throw new ExecutionException(th);
                }
            }
        };
    }

    public static <T> ListenableFuture<T> makeUnfailable(ListenableFuture<T> listenableFuture) {
        final SettableFuture create = SettableFuture.create();
        Futures.addCallback(listenableFuture, new FutureCallback<T>() {
            /* class com.facebook.common.futures.FbFutures.AnonymousClass2 */

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onSuccess(@Nullable T t) {
                SettableFuture.this.set(t);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable th) {
                SettableFuture.this.set(null);
            }
        }, MoreExecutors.directExecutor());
        return create;
    }

    public static ListenableFuture<List<Object>> successfulOrThrowableAsList(ListenableFuture<?>... listenableFutureArr) {
        return successfulOrThrowableAsList(Lists.newArrayList(listenableFutureArr));
    }

    public static ListenableFuture<List<Object>> successfulOrThrowableAsList(Iterable<? extends ListenableFuture<?>> iterable) {
        return Futures.allAsList(Iterables.transform(iterable, new Function<ListenableFuture<?>, ListenableFuture<Object>>() {
            /* class com.facebook.common.futures.FbFutures.AnonymousClass3 */

            @Nullable
            public ListenableFuture<Object> apply(@Nullable ListenableFuture<?> listenableFuture) {
                if (listenableFuture == null) {
                    return null;
                }
                return AlwaysSuccessfulReturnsThrowableFuture.create(listenableFuture);
            }
        }));
    }

    public static <T> T getNowOrDefault(Future<T> future, @Nullable T t) {
        return future.isDone() ? (T) Futures.getUnchecked(future) : t;
    }

    @Nullable
    public static <T> T getNowOrNull(Future<T> future) {
        return (T) getNowOrDefault(future, null);
    }

    public static <T> T getNowOrThrow(Future<T> future) {
        Preconditions.checkState(future.isDone());
        return (T) Futures.getUnchecked(future);
    }
}
