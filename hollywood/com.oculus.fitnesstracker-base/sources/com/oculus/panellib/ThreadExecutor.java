package com.oculus.panellib;

import android.os.Handler;
import android.os.Looper;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadExecutor {
    private static ThreadExecutor sInstance;
    private final ListeningExecutorService mExecutorService;
    private final UiThreadExecutor mUiThreadExecutor;

    public ThreadExecutor() {
        ListeningScheduledExecutorService listeningScheduledExecutorService;
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(4);
        if (newScheduledThreadPool instanceof ListeningScheduledExecutorService) {
            listeningScheduledExecutorService = (ListeningScheduledExecutorService) newScheduledThreadPool;
        } else {
            listeningScheduledExecutorService = new MoreExecutors.ScheduledListeningDecorator(newScheduledThreadPool);
        }
        this.mExecutorService = listeningScheduledExecutorService;
        this.mUiThreadExecutor = new UiThreadExecutor();
    }

    /* access modifiers changed from: package-private */
    public static class UiThreadExecutor implements Executor {
        private final Handler mHandler;

        private UiThreadExecutor() {
            this.mHandler = new Handler(Looper.getMainLooper());
        }

        public void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    public static ThreadExecutor getInstance() {
        if (sInstance == null) {
            sInstance = new ThreadExecutor();
        }
        return sInstance;
    }

    public <T> void execute(Callable<T> callable) {
        execute(callable, null, "ThreadExecutor::execute");
    }

    public <T> void execute(Callable<T> callable, String str) {
        execute(callable, null, str);
    }

    public <T> void execute(Callable<T> callable, FutureCallback<T> futureCallback) {
        execute(callable, futureCallback, "ThreadExecutor::execute");
    }

    public <T> void execute(final Callable<T> callable, FutureCallback<T> futureCallback, final String str) {
        ListenableFuture<T> listenableFuture;
        if (!Systrace.isEnabled()) {
            listenableFuture = this.mExecutorService.submit((Callable) callable);
        } else {
            listenableFuture = this.mExecutorService.submit((Callable) new Callable<T>() {
                /* class com.oculus.panellib.ThreadExecutor.AnonymousClass1 */

                @Override // java.util.concurrent.Callable
                public T call() throws Exception {
                    SystraceBlock systraceBlock = new SystraceBlock(str);
                    try {
                        T t = (T) callable.call();
                        systraceBlock.close();
                        return t;
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            });
        }
        if (futureCallback != null) {
            Futures.addCallback(listenableFuture, futureCallback, this.mUiThreadExecutor);
        } else {
            Futures.addCallback(listenableFuture, new FutureCallback<T>() {
                /* class com.oculus.panellib.ThreadExecutor.AnonymousClass2 */

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onSuccess(T t) {
                }

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onFailure(Throwable th) {
                    throw new RuntimeException(th);
                }
            }, this.mUiThreadExecutor);
        }
    }
}
