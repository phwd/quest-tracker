package com.oculus.vrshell.util;

import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.util.ThreadExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadExecutor {
    private static final ThreadExecutor INSTANCE = new ThreadExecutor();
    private static final int MAXIMUM_POOL_SIZE = 4;
    private static final String TAG = LoggingUtil.tag(ThreadExecutor.class);
    private final ScheduledExecutorService mExecutorService = Executors.newScheduledThreadPool(4);

    public interface Listener<T> {
        void onFailure(Throwable th);

        void onSuccess(T t);
    }

    public static ThreadExecutor getInstance() {
        return INSTANCE;
    }

    public <T> ConcurrentListenableScheduledFuture<T> execute(Callable<T> callable) {
        return execute(callable, 0, null);
    }

    public <T> ConcurrentListenableScheduledFuture<T> execute(Callable<T> callable, int i) {
        return execute(callable, (long) i, null);
    }

    public <T> ConcurrentListenableScheduledFuture<T> execute(Callable<T> callable, Listener<T> listener) {
        return execute(callable, 0, listener);
    }

    public <T> ConcurrentListenableScheduledFuture<T> execute(Callable<T> callable, long j, Listener<T> listener) {
        ConcurrentListenableScheduledFuture<T> concurrentListenableScheduledFuture = new ConcurrentListenableScheduledFuture<>(this.mExecutorService, callable, j);
        if (listener != null) {
            concurrentListenableScheduledFuture.listen(listener);
        }
        return concurrentListenableScheduledFuture;
    }

    public class ConcurrentListenableScheduledFuture<T> implements ScheduledFuture<T> {
        private final ScheduledFuture<T> mBaseFuture;
        private final ScheduledExecutorService mExecutorService;
        private Throwable mFailure;
        private final List<Listener<T>> mListeners = new ArrayList();

        public ConcurrentListenableScheduledFuture(ScheduledExecutorService scheduledExecutorService, Callable<T> callable, long j) {
            this.mExecutorService = scheduledExecutorService;
            this.mBaseFuture = scheduledExecutorService.schedule(new Callable(callable) {
                /* class com.oculus.vrshell.util.$$Lambda$ThreadExecutor$ConcurrentListenableScheduledFuture$v6M5EWWpIU5rT7aWgJ7UqGk69mw */
                private final /* synthetic */ Callable f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return ThreadExecutor.ConcurrentListenableScheduledFuture.this.lambda$new$0$ThreadExecutor$ConcurrentListenableScheduledFuture(this.f$1);
                }
            }, j, TimeUnit.MILLISECONDS);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.oculus.vrshell.util.ThreadExecutor$ConcurrentListenableScheduledFuture<T> */
        /* JADX WARN: Multi-variable type inference failed */
        public /* synthetic */ Object lambda$new$0$ThreadExecutor$ConcurrentListenableScheduledFuture(Callable callable) throws Exception {
            try {
                Object call = callable.call();
                onSuccess(call);
                return call;
            } catch (Throwable th) {
                this.mFailure = th;
                onFailure(th);
                return null;
            }
        }

        public synchronized void listen(Listener<T> listener) {
            if (listener == null) {
                throw new NullPointerException();
            } else if (isDone()) {
                try {
                    T t = get();
                    if (this.mFailure != null) {
                        runListenerFailure(listener, this.mFailure);
                    } else {
                        runListenerSuccess(listener, t);
                    }
                } catch (Throwable th) {
                    runListenerFailure(listener, th);
                }
            } else {
                this.mListeners.add(listener);
            }
        }

        private synchronized void onSuccess(T t) {
            for (Listener<T> listener : this.mListeners) {
                runListenerSuccess(listener, t);
            }
        }

        private synchronized void onFailure(Throwable th) {
            for (Listener<T> listener : this.mListeners) {
                runListenerFailure(listener, th);
            }
        }

        private void runListenerSuccess(Listener<T> listener, T t) {
            this.mExecutorService.execute(new Runnable(t) {
                /* class com.oculus.vrshell.util.$$Lambda$ThreadExecutor$ConcurrentListenableScheduledFuture$yRrWbyCfj2iZiLiSSiOKNT3MPgA */
                private final /* synthetic */ Object f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ThreadExecutor.Listener.this.onSuccess(this.f$1);
                }
            });
        }

        private void runListenerFailure(Listener<T> listener, Throwable th) {
            this.mExecutorService.execute(new Runnable(th) {
                /* class com.oculus.vrshell.util.$$Lambda$ThreadExecutor$ConcurrentListenableScheduledFuture$Bi8CTQAxuvmttrHdX8gso8tbOU */
                private final /* synthetic */ Throwable f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ThreadExecutor.Listener.this.onFailure(this.f$1);
                }
            });
        }

        public long getDelay(TimeUnit timeUnit) {
            return this.mBaseFuture.getDelay(timeUnit);
        }

        public int compareTo(Delayed delayed) {
            return this.mBaseFuture.compareTo(delayed);
        }

        public boolean cancel(boolean z) {
            return this.mBaseFuture.cancel(z);
        }

        public boolean isCancelled() {
            return this.mBaseFuture.isCancelled();
        }

        public boolean isDone() {
            return this.mBaseFuture.isDone();
        }

        @Override // java.util.concurrent.Future
        public T get() throws InterruptedException, ExecutionException {
            return this.mBaseFuture.get();
        }

        @Override // java.util.concurrent.Future
        public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return this.mBaseFuture.get(j, timeUnit);
        }
    }
}
