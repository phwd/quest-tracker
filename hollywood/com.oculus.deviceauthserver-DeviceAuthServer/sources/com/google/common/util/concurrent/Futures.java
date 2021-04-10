package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

@Beta
public final class Futures {
    private static final AsyncFunction<ListenableFuture<Object>, Object> DEREFERENCER = new AsyncFunction<ListenableFuture<Object>, Object>() {
        /* class com.google.common.util.concurrent.Futures.AnonymousClass4 */

        public ListenableFuture<Object> apply(ListenableFuture<Object> input) {
            return input;
        }
    };
    private static final Ordering<Constructor<?>> WITH_STRING_PARAM_FIRST = Ordering.natural().onResultOf(new Function<Constructor<?>, Boolean>() {
        /* class com.google.common.util.concurrent.Futures.AnonymousClass7 */

        public Boolean apply(Constructor<?> input) {
            return Boolean.valueOf(Arrays.asList(input.getParameterTypes()).contains(String.class));
        }
    }).reverse();

    /* access modifiers changed from: private */
    public interface FutureCombiner<V, C> {
        C combine(List<Optional<V>> list);
    }

    private Futures() {
    }

    public static <V, X extends Exception> CheckedFuture<V, X> makeChecked(ListenableFuture<V> future, Function<? super Exception, X> mapper) {
        return new MappingCheckedFuture((ListenableFuture) Preconditions.checkNotNull(future), mapper);
    }

    private static abstract class ImmediateFuture<V> implements ListenableFuture<V> {
        private static final Logger log = Logger.getLogger(ImmediateFuture.class.getName());

        @Override // java.util.concurrent.Future
        public abstract V get() throws ExecutionException;

        private ImmediateFuture() {
        }

        @Override // com.google.common.util.concurrent.ListenableFuture
        public void addListener(Runnable listener, Executor executor) {
            Preconditions.checkNotNull(listener, "Runnable was null.");
            Preconditions.checkNotNull(executor, "Executor was null.");
            try {
                executor.execute(listener);
            } catch (RuntimeException e) {
                Logger logger = log;
                Level level = Level.SEVERE;
                logger.log(level, "RuntimeException while executing runnable " + listener + " with executor " + executor, (Throwable) e);
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
    }

    /* access modifiers changed from: private */
    public static class ImmediateSuccessfulFuture<V> extends ImmediateFuture<V> {
        @Nullable
        private final V value;

        ImmediateSuccessfulFuture(@Nullable V value2) {
            super();
            this.value = value2;
        }

        @Override // java.util.concurrent.Future, com.google.common.util.concurrent.Futures.ImmediateFuture
        public V get() {
            return this.value;
        }
    }

    private static class ImmediateSuccessfulCheckedFuture<V, X extends Exception> extends ImmediateFuture<V> implements CheckedFuture<V, X> {
        @Nullable
        private final V value;

        ImmediateSuccessfulCheckedFuture(@Nullable V value2) {
            super();
            this.value = value2;
        }

        @Override // java.util.concurrent.Future, com.google.common.util.concurrent.Futures.ImmediateFuture
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
    }

    /* access modifiers changed from: private */
    public static class ImmediateFailedFuture<V> extends ImmediateFuture<V> {
        private final Throwable thrown;

        ImmediateFailedFuture(Throwable thrown2) {
            super();
            this.thrown = thrown2;
        }

        @Override // java.util.concurrent.Future, com.google.common.util.concurrent.Futures.ImmediateFuture
        public V get() throws ExecutionException {
            throw new ExecutionException(this.thrown);
        }
    }

    private static class ImmediateCancelledFuture<V> extends ImmediateFuture<V> {
        private final CancellationException thrown = new CancellationException("Immediate cancelled future.");

        ImmediateCancelledFuture() {
            super();
        }

        @Override // com.google.common.util.concurrent.Futures.ImmediateFuture
        public boolean isCancelled() {
            return true;
        }

        @Override // java.util.concurrent.Future, com.google.common.util.concurrent.Futures.ImmediateFuture
        public V get() {
            throw AbstractFuture.cancellationExceptionWithCause("Task was cancelled.", this.thrown);
        }
    }

    private static class ImmediateFailedCheckedFuture<V, X extends Exception> extends ImmediateFuture<V> implements CheckedFuture<V, X> {
        private final X thrown;

        ImmediateFailedCheckedFuture(X thrown2) {
            super();
            this.thrown = thrown2;
        }

        @Override // java.util.concurrent.Future, com.google.common.util.concurrent.Futures.ImmediateFuture
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
    }

    public static <V> ListenableFuture<V> immediateFuture(@Nullable V value) {
        return new ImmediateSuccessfulFuture(value);
    }

    public static <V, X extends Exception> CheckedFuture<V, X> immediateCheckedFuture(@Nullable V value) {
        return new ImmediateSuccessfulCheckedFuture(value);
    }

    public static <V> ListenableFuture<V> immediateFailedFuture(Throwable throwable) {
        Preconditions.checkNotNull(throwable);
        return new ImmediateFailedFuture(throwable);
    }

    public static <V> ListenableFuture<V> immediateCancelledFuture() {
        return new ImmediateCancelledFuture();
    }

    public static <V, X extends Exception> CheckedFuture<V, X> immediateFailedCheckedFuture(X exception) {
        Preconditions.checkNotNull(exception);
        return new ImmediateFailedCheckedFuture(exception);
    }

    public static <V> ListenableFuture<V> withFallback(ListenableFuture<? extends V> input, FutureFallback<? extends V> fallback) {
        return withFallback(input, fallback, MoreExecutors.directExecutor());
    }

    public static <V> ListenableFuture<V> withFallback(ListenableFuture<? extends V> input, FutureFallback<? extends V> fallback, Executor executor) {
        Preconditions.checkNotNull(fallback);
        return new FallbackFuture(input, fallback, executor);
    }

    /* access modifiers changed from: private */
    public static class FallbackFuture<V> extends AbstractFuture<V> {
        private volatile ListenableFuture<? extends V> running;

        FallbackFuture(ListenableFuture<? extends V> input, final FutureFallback<? extends V> fallback, Executor executor) {
            this.running = input;
            Futures.addCallback(this.running, new FutureCallback<V>() {
                /* class com.google.common.util.concurrent.Futures.FallbackFuture.AnonymousClass1 */

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onSuccess(V value) {
                    FallbackFuture.this.set(value);
                }

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onFailure(Throwable t) {
                    if (!FallbackFuture.this.isCancelled()) {
                        try {
                            FallbackFuture.this.running = fallback.create(t);
                            if (FallbackFuture.this.isCancelled()) {
                                FallbackFuture.this.running.cancel(FallbackFuture.this.wasInterrupted());
                            } else {
                                Futures.addCallback(FallbackFuture.this.running, new FutureCallback<V>() {
                                    /* class com.google.common.util.concurrent.Futures.FallbackFuture.AnonymousClass1.AnonymousClass1 */

                                    @Override // com.google.common.util.concurrent.FutureCallback
                                    public void onSuccess(V value) {
                                        FallbackFuture.this.set(value);
                                    }

                                    @Override // com.google.common.util.concurrent.FutureCallback
                                    public void onFailure(Throwable t) {
                                        if (FallbackFuture.this.running.isCancelled()) {
                                            FallbackFuture.this.cancel(false);
                                        } else {
                                            FallbackFuture.this.setException(t);
                                        }
                                    }
                                }, MoreExecutors.directExecutor());
                            }
                        } catch (Throwable e) {
                            FallbackFuture.this.setException(e);
                        }
                    }
                }
            }, executor);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public boolean cancel(boolean mayInterruptIfRunning) {
            if (!super.cancel(mayInterruptIfRunning)) {
                return false;
            }
            this.running.cancel(mayInterruptIfRunning);
            return true;
        }
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> input, AsyncFunction<? super I, ? extends O> function) {
        ChainingListenableFuture<I, O> output = new ChainingListenableFuture<>(function, input);
        input.addListener(output, MoreExecutors.directExecutor());
        return output;
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> input, AsyncFunction<? super I, ? extends O> function, Executor executor) {
        Preconditions.checkNotNull(executor);
        ChainingListenableFuture<I, O> output = new ChainingListenableFuture<>(function, input);
        input.addListener(rejectionPropagatingRunnable(output, output, executor), MoreExecutors.directExecutor());
        return output;
    }

    private static Runnable rejectionPropagatingRunnable(final AbstractFuture<?> outputFuture, final Runnable delegateTask, final Executor delegateExecutor) {
        return new Runnable() {
            /* class com.google.common.util.concurrent.Futures.AnonymousClass1 */

            public void run() {
                final AtomicBoolean thrownFromDelegate = new AtomicBoolean(true);
                try {
                    delegateExecutor.execute(new Runnable() {
                        /* class com.google.common.util.concurrent.Futures.AnonymousClass1.AnonymousClass1 */

                        public void run() {
                            thrownFromDelegate.set(false);
                            delegateTask.run();
                        }
                    });
                } catch (RejectedExecutionException e) {
                    if (thrownFromDelegate.get()) {
                        outputFuture.setException(e);
                    }
                }
            }
        };
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> input, Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(function);
        ChainingListenableFuture<I, O> output = new ChainingListenableFuture<>(asAsyncFunction(function), input);
        input.addListener(output, MoreExecutors.directExecutor());
        return output;
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> input, Function<? super I, ? extends O> function, Executor executor) {
        Preconditions.checkNotNull(function);
        return transform(input, asAsyncFunction(function), executor);
    }

    private static <I, O> AsyncFunction<I, O> asAsyncFunction(final Function<? super I, ? extends O> function) {
        return new AsyncFunction<I, O>() {
            /* class com.google.common.util.concurrent.Futures.AnonymousClass2 */

            @Override // com.google.common.util.concurrent.AsyncFunction
            public ListenableFuture<O> apply(I input) {
                return Futures.immediateFuture(Function.this.apply(input));
            }
        };
    }

    public static <I, O> Future<O> lazyTransform(final Future<I> input, final Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(input);
        Preconditions.checkNotNull(function);
        return new Future<O>() {
            /* class com.google.common.util.concurrent.Futures.AnonymousClass3 */

            public boolean cancel(boolean mayInterruptIfRunning) {
                return input.cancel(mayInterruptIfRunning);
            }

            public boolean isCancelled() {
                return input.isCancelled();
            }

            public boolean isDone() {
                return input.isDone();
            }

            @Override // java.util.concurrent.Future
            public O get() throws InterruptedException, ExecutionException {
                return (O) applyTransformation(input.get());
            }

            @Override // java.util.concurrent.Future
            public O get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return (O) applyTransformation(input.get(timeout, unit));
            }

            private O applyTransformation(I input) throws ExecutionException {
                try {
                    return (O) function.apply(input);
                } catch (Throwable t) {
                    throw new ExecutionException(t);
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public static class ChainingListenableFuture<I, O> extends AbstractFuture<O> implements Runnable {
        private AsyncFunction<? super I, ? extends O> function;
        private ListenableFuture<? extends I> inputFuture;
        private volatile ListenableFuture<? extends O> outputFuture;

        private ChainingListenableFuture(AsyncFunction<? super I, ? extends O> function2, ListenableFuture<? extends I> inputFuture2) {
            this.function = (AsyncFunction) Preconditions.checkNotNull(function2);
            this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(inputFuture2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public boolean cancel(boolean mayInterruptIfRunning) {
            if (!super.cancel(mayInterruptIfRunning)) {
                return false;
            }
            cancel(this.inputFuture, mayInterruptIfRunning);
            cancel(this.outputFuture, mayInterruptIfRunning);
            return true;
        }

        private void cancel(@Nullable Future<?> future, boolean mayInterruptIfRunning) {
            if (future != null) {
                future.cancel(mayInterruptIfRunning);
            }
        }

        public void run() {
            try {
                try {
                    final ListenableFuture<? extends O> outputFuture2 = (ListenableFuture) Preconditions.checkNotNull(this.function.apply(Uninterruptibles.getUninterruptibly(this.inputFuture)), "AsyncFunction may not return null.");
                    this.outputFuture = outputFuture2;
                    if (isCancelled()) {
                        outputFuture2.cancel(wasInterrupted());
                        this.outputFuture = null;
                        this.function = null;
                        this.inputFuture = null;
                        return;
                    }
                    outputFuture2.addListener(new Runnable() {
                        /* class com.google.common.util.concurrent.Futures.ChainingListenableFuture.AnonymousClass1 */

                        public void run() {
                            try {
                                ChainingListenableFuture.this.set(Uninterruptibles.getUninterruptibly(outputFuture2));
                            } catch (CancellationException e) {
                                ChainingListenableFuture.this.cancel(false);
                                ChainingListenableFuture.this.outputFuture = null;
                                return;
                            } catch (ExecutionException e2) {
                                ChainingListenableFuture.this.setException(e2.getCause());
                            } catch (Throwable th) {
                                ChainingListenableFuture.this.outputFuture = null;
                                throw th;
                            }
                            ChainingListenableFuture.this.outputFuture = null;
                        }
                    }, MoreExecutors.directExecutor());
                    this.function = null;
                    this.inputFuture = null;
                } catch (UndeclaredThrowableException e) {
                    setException(e.getCause());
                } catch (Throwable th) {
                    this.function = null;
                    this.inputFuture = null;
                    throw th;
                }
            } catch (CancellationException e2) {
                cancel(false);
                this.function = null;
                this.inputFuture = null;
            } catch (ExecutionException e3) {
                setException(e3.getCause());
                this.function = null;
                this.inputFuture = null;
            }
        }
    }

    public static <V> ListenableFuture<V> dereference(ListenableFuture<? extends ListenableFuture<? extends V>> nested) {
        return transform(nested, DEREFERENCER);
    }

    @Beta
    public static <V> ListenableFuture<List<V>> allAsList(ListenableFuture<? extends V>... futures) {
        return listFuture(ImmutableList.copyOf(futures), true, MoreExecutors.directExecutor());
    }

    @Beta
    public static <V> ListenableFuture<List<V>> allAsList(Iterable<? extends ListenableFuture<? extends V>> futures) {
        return listFuture(ImmutableList.copyOf(futures), true, MoreExecutors.directExecutor());
    }

    private static final class WrappedCombiner<T> implements Callable<T> {
        final Callable<T> delegate;
        CombinerFuture<T> outputFuture;

        WrappedCombiner(Callable<T> delegate2) {
            this.delegate = (Callable) Preconditions.checkNotNull(delegate2);
        }

        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            try {
                return this.delegate.call();
            } catch (ExecutionException e) {
                this.outputFuture.setException(e.getCause());
                return null;
            } catch (CancellationException e2) {
                this.outputFuture.cancel(false);
                return null;
            }
        }
    }

    private static final class CombinerFuture<V> extends ListenableFutureTask<V> {
        ImmutableList<ListenableFuture<?>> futures;

        CombinerFuture(Callable<V> callable, ImmutableList<ListenableFuture<?>> futures2) {
            super(callable);
            this.futures = futures2;
        }

        public boolean cancel(boolean mayInterruptIfRunning) {
            ImmutableList<ListenableFuture<?>> futures2 = this.futures;
            if (!super.cancel(mayInterruptIfRunning)) {
                return false;
            }
            UnmodifiableIterator<ListenableFuture<?>> it = futures2.iterator();
            while (it.hasNext()) {
                it.next().cancel(mayInterruptIfRunning);
            }
            return true;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.ListenableFutureTask
        public void done() {
            super.done();
            this.futures = null;
        }

        /* access modifiers changed from: protected */
        public void setException(Throwable t) {
            super.setException(t);
        }
    }

    public static <V> ListenableFuture<V> nonCancellationPropagating(ListenableFuture<V> future) {
        return new NonCancellationPropagatingFuture(future);
    }

    private static class NonCancellationPropagatingFuture<V> extends AbstractFuture<V> {
        NonCancellationPropagatingFuture(final ListenableFuture<V> delegate) {
            Preconditions.checkNotNull(delegate);
            Futures.addCallback(delegate, new FutureCallback<V>() {
                /* class com.google.common.util.concurrent.Futures.NonCancellationPropagatingFuture.AnonymousClass1 */

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onSuccess(V result) {
                    NonCancellationPropagatingFuture.this.set(result);
                }

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onFailure(Throwable t) {
                    if (delegate.isCancelled()) {
                        NonCancellationPropagatingFuture.this.cancel(false);
                    } else {
                        NonCancellationPropagatingFuture.this.setException(t);
                    }
                }
            }, MoreExecutors.directExecutor());
        }
    }

    @Beta
    public static <V> ListenableFuture<List<V>> successfulAsList(ListenableFuture<? extends V>... futures) {
        return listFuture(ImmutableList.copyOf(futures), false, MoreExecutors.directExecutor());
    }

    @Beta
    public static <V> ListenableFuture<List<V>> successfulAsList(Iterable<? extends ListenableFuture<? extends V>> futures) {
        return listFuture(ImmutableList.copyOf(futures), false, MoreExecutors.directExecutor());
    }

    @Beta
    public static <T> ImmutableList<ListenableFuture<T>> inCompletionOrder(Iterable<? extends ListenableFuture<? extends T>> futures) {
        final ConcurrentLinkedQueue<AsyncSettableFuture<T>> delegates = Queues.newConcurrentLinkedQueue();
        ImmutableList.Builder<ListenableFuture<T>> listBuilder = ImmutableList.builder();
        SerializingExecutor executor = new SerializingExecutor(MoreExecutors.directExecutor());
        for (final ListenableFuture<? extends T> future : futures) {
            AsyncSettableFuture<T> delegate = AsyncSettableFuture.create();
            delegates.add(delegate);
            future.addListener(new Runnable() {
                /* class com.google.common.util.concurrent.Futures.AnonymousClass5 */

                public void run() {
                    ((AsyncSettableFuture) delegates.remove()).setFuture(future);
                }
            }, executor);
            listBuilder.add((ListenableFuture<T>) delegate);
        }
        return listBuilder.build();
    }

    public static <V> void addCallback(ListenableFuture<V> future, FutureCallback<? super V> callback) {
        addCallback(future, callback, MoreExecutors.directExecutor());
    }

    public static <V> void addCallback(final ListenableFuture<V> future, final FutureCallback<? super V> callback, Executor executor) {
        Preconditions.checkNotNull(callback);
        future.addListener(new Runnable() {
            /* class com.google.common.util.concurrent.Futures.AnonymousClass6 */

            public void run() {
                try {
                    callback.onSuccess(Uninterruptibles.getUninterruptibly(ListenableFuture.this));
                } catch (ExecutionException e) {
                    callback.onFailure(e.getCause());
                } catch (RuntimeException e2) {
                    callback.onFailure(e2);
                } catch (Error e3) {
                    callback.onFailure(e3);
                }
            }
        }, executor);
    }

    public static <V, X extends Exception> V get(Future<V> future, Class<X> exceptionClass) throws Exception {
        Preconditions.checkNotNull(future);
        Preconditions.checkArgument(!RuntimeException.class.isAssignableFrom(exceptionClass), "Futures.get exception type (%s) must not be a RuntimeException", exceptionClass);
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw newWithCause(exceptionClass, e);
        } catch (ExecutionException e2) {
            wrapAndThrowExceptionOrError(e2.getCause(), exceptionClass);
            throw new AssertionError();
        }
    }

    public static <V, X extends Exception> V get(Future<V> future, long timeout, TimeUnit unit, Class<X> exceptionClass) throws Exception {
        Preconditions.checkNotNull(future);
        Preconditions.checkNotNull(unit);
        Preconditions.checkArgument(!RuntimeException.class.isAssignableFrom(exceptionClass), "Futures.get exception type (%s) must not be a RuntimeException", exceptionClass);
        try {
            return future.get(timeout, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw newWithCause(exceptionClass, e);
        } catch (TimeoutException e2) {
            throw newWithCause(exceptionClass, e2);
        } catch (ExecutionException e3) {
            wrapAndThrowExceptionOrError(e3.getCause(), exceptionClass);
            throw new AssertionError();
        }
    }

    private static <X extends Exception> void wrapAndThrowExceptionOrError(Throwable cause, Class<X> exceptionClass) throws Exception {
        if (cause instanceof Error) {
            throw new ExecutionError((Error) cause);
        } else if (cause instanceof RuntimeException) {
            throw new UncheckedExecutionException(cause);
        } else {
            throw newWithCause(exceptionClass, cause);
        }
    }

    public static <V> V getUnchecked(Future<V> future) {
        Preconditions.checkNotNull(future);
        try {
            return (V) Uninterruptibles.getUninterruptibly(future);
        } catch (ExecutionException e) {
            wrapAndThrowUnchecked(e.getCause());
            throw new AssertionError();
        }
    }

    private static void wrapAndThrowUnchecked(Throwable cause) {
        if (cause instanceof Error) {
            throw new ExecutionError((Error) cause);
        }
        throw new UncheckedExecutionException(cause);
    }

    private static <X extends Exception> X newWithCause(Class<X> exceptionClass, Throwable cause) {
        for (Constructor<X> constructor : preferringStrings(Arrays.asList(exceptionClass.getConstructors()))) {
            X instance = (X) ((Exception) newFromConstructor(constructor, cause));
            if (instance != null) {
                if (instance.getCause() == null) {
                    instance.initCause(cause);
                }
                return instance;
            }
        }
        throw new IllegalArgumentException("No appropriate constructor for exception of type " + exceptionClass + " in response to chained exception", cause);
    }

    private static <X extends Exception> List<Constructor<X>> preferringStrings(List<Constructor<X>> constructors) {
        return WITH_STRING_PARAM_FIRST.sortedCopy(constructors);
    }

    @Nullable
    private static <X> X newFromConstructor(Constructor<X> constructor, Throwable cause) {
        Class<?>[] paramTypes = constructor.getParameterTypes();
        Object[] params = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> paramType = paramTypes[i];
            if (paramType.equals(String.class)) {
                params[i] = cause.toString();
            } else if (!paramType.equals(Throwable.class)) {
                return null;
            } else {
                params[i] = cause;
            }
        }
        try {
            return constructor.newInstance(params);
        } catch (IllegalArgumentException e) {
            return null;
        } catch (InstantiationException e2) {
            return null;
        } catch (IllegalAccessException e3) {
            return null;
        } catch (InvocationTargetException e4) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static class CombinedFuture<V, C> extends AbstractFuture<C> {
        private static final Logger logger = Logger.getLogger(CombinedFuture.class.getName());
        final boolean allMustSucceed;
        FutureCombiner<V, C> combiner;
        ImmutableCollection<? extends ListenableFuture<? extends V>> futures;
        final AtomicInteger remaining;
        Set<Throwable> seenExceptions;
        final Object seenExceptionsLock = new Object();
        List<Optional<V>> values;

        CombinedFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> futures2, boolean allMustSucceed2, Executor listenerExecutor, FutureCombiner<V, C> combiner2) {
            this.futures = futures2;
            this.allMustSucceed = allMustSucceed2;
            this.remaining = new AtomicInteger(futures2.size());
            this.combiner = combiner2;
            this.values = Lists.newArrayListWithCapacity(futures2.size());
            init(listenerExecutor);
        }

        /* JADX INFO: Multiple debug info for r0v6 'i'  int: [D('index' int), D('i' int)] */
        /* access modifiers changed from: protected */
        public void init(Executor listenerExecutor) {
            addListener(new Runnable() {
                /* class com.google.common.util.concurrent.Futures.CombinedFuture.AnonymousClass1 */

                public void run() {
                    if (CombinedFuture.this.isCancelled()) {
                        UnmodifiableIterator<? extends ListenableFuture<? extends V>> it = CombinedFuture.this.futures.iterator();
                        while (it.hasNext()) {
                            ((ListenableFuture) it.next()).cancel(CombinedFuture.this.wasInterrupted());
                        }
                    }
                    CombinedFuture combinedFuture = CombinedFuture.this;
                    combinedFuture.futures = null;
                    combinedFuture.values = null;
                    combinedFuture.combiner = null;
                }
            }, MoreExecutors.directExecutor());
            if (this.futures.isEmpty()) {
                set(this.combiner.combine(ImmutableList.of()));
                return;
            }
            for (int i = 0; i < this.futures.size(); i++) {
                this.values.add(null);
            }
            final int index = 0;
            UnmodifiableIterator<? extends ListenableFuture<? extends V>> it = this.futures.iterator();
            while (it.hasNext()) {
                final ListenableFuture<? extends V> listenable = (ListenableFuture) it.next();
                listenable.addListener(new Runnable() {
                    /* class com.google.common.util.concurrent.Futures.CombinedFuture.AnonymousClass2 */

                    public void run() {
                        CombinedFuture.this.setOneValue(index, listenable);
                    }
                }, listenerExecutor);
                index++;
            }
        }

        private void setExceptionAndMaybeLog(Throwable throwable) {
            boolean visibleFromOutputFuture = false;
            boolean firstTimeSeeingThisException = true;
            if (this.allMustSucceed) {
                boolean visibleFromOutputFuture2 = super.setException(throwable);
                synchronized (this.seenExceptionsLock) {
                    if (this.seenExceptions == null) {
                        this.seenExceptions = Sets.newHashSet();
                    }
                    firstTimeSeeingThisException = this.seenExceptions.add(throwable);
                }
                visibleFromOutputFuture = visibleFromOutputFuture2;
            }
            if ((throwable instanceof Error) || (this.allMustSucceed && !visibleFromOutputFuture && firstTimeSeeingThisException)) {
                logger.log(Level.SEVERE, "input future failed.", throwable);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x004a, code lost:
            if (r1 != null) goto L_0x0066;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0064, code lost:
            if (r1 != null) goto L_0x0066;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0066, code lost:
            set(r0.combine(r1));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x0091, code lost:
            if (r1 != null) goto L_0x0066;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x00af, code lost:
            if (r1 != null) goto L_0x0066;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void setOneValue(int r7, java.util.concurrent.Future<? extends V> r8) {
            /*
            // Method dump skipped, instructions count: 217
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Futures.CombinedFuture.setOneValue(int, java.util.concurrent.Future):void");
        }
    }

    private static <V> ListenableFuture<List<V>> listFuture(ImmutableList<ListenableFuture<? extends V>> futures, boolean allMustSucceed, Executor listenerExecutor) {
        return new CombinedFuture(futures, allMustSucceed, listenerExecutor, new FutureCombiner<V, List<V>>() {
            /* class com.google.common.util.concurrent.Futures.AnonymousClass8 */

            @Override // com.google.common.util.concurrent.Futures.FutureCombiner
            public List<V> combine(List<Optional<V>> values) {
                List<V> result = Lists.newArrayList();
                Iterator<Optional<V>> it = values.iterator();
                while (it.hasNext()) {
                    Optional<V> element = it.next();
                    result.add(element != null ? element.orNull() : null);
                }
                return Collections.unmodifiableList(result);
            }
        });
    }

    private static class MappingCheckedFuture<V, X extends Exception> extends AbstractCheckedFuture<V, X> {
        final Function<? super Exception, X> mapper;

        MappingCheckedFuture(ListenableFuture<V> delegate, Function<? super Exception, X> mapper2) {
            super(delegate);
            this.mapper = (Function) Preconditions.checkNotNull(mapper2);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractCheckedFuture
        public X mapException(Exception e) {
            return this.mapper.apply(e);
        }
    }
}
