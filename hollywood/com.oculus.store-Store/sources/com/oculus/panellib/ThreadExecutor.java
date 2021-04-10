package com.oculus.panellib;

import android.os.Handler;
import android.os.Looper;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadExecutor {
    private static final int MAXIMUM_POOL_SIZE = 4;
    private static ThreadExecutor sInstance;
    private final ListeningExecutorService mExecutorService = MoreExecutors.listeningDecorator(Executors.newScheduledThreadPool(4));
    private final UiThreadExecutor mUiThreadExecutor = new UiThreadExecutor();

    /* access modifiers changed from: private */
    public static class UiThreadExecutor implements Executor {
        private final Handler mHandler;

        private UiThreadExecutor() {
            this.mHandler = new Handler(Looper.getMainLooper());
        }

        public void execute(Runnable command) {
            this.mHandler.post(command);
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

    public <T> void execute(Callable<T> callable, String callerName) {
        execute(callable, null, callerName);
    }

    public <T> void execute(Callable<T> callable, FutureCallback<T> callback) {
        execute(callable, callback, "ThreadExecutor::execute");
    }

    public <T> void execute(final Callable<T> callable, FutureCallback<T> callback, final String callerName) {
        ListenableFuture<T> future;
        if (!Systrace.isEnabled()) {
            future = this.mExecutorService.submit((Callable) callable);
        } else {
            future = this.mExecutorService.submit((Callable) new Callable<T>() {
                /* class com.oculus.panellib.ThreadExecutor.AnonymousClass1 */

                /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
                    r3 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
                    r3 = r2;
                    r2 = r3;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:24:0x0036, code lost:
                    r2 = th;
                 */
                @Override // java.util.concurrent.Callable
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public T call() throws java.lang.Exception {
                    /*
                        r6 = this;
                        com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
                        java.lang.String r2 = r6
                        r0.<init>(r2)
                        r3 = 0
                        java.util.concurrent.Callable r2 = r4     // Catch:{ Throwable -> 0x001f, all -> 0x0036 }
                        java.lang.Object r1 = r2.call()     // Catch:{ Throwable -> 0x001f, all -> 0x0036 }
                        if (r0 == 0) goto L_0x0015
                        if (r3 == 0) goto L_0x001b
                        r0.close()     // Catch:{ Throwable -> 0x0016 }
                    L_0x0015:
                        return r1
                    L_0x0016:
                        r2 = move-exception
                        r3.addSuppressed(r2)
                        goto L_0x0015
                    L_0x001b:
                        r0.close()
                        goto L_0x0015
                    L_0x001f:
                        r2 = move-exception
                        throw r2     // Catch:{ all -> 0x0021 }
                    L_0x0021:
                        r3 = move-exception
                        r5 = r3
                        r3 = r2
                        r2 = r5
                    L_0x0025:
                        if (r0 == 0) goto L_0x002c
                        if (r3 == 0) goto L_0x0032
                        r0.close()     // Catch:{ Throwable -> 0x002d }
                    L_0x002c:
                        throw r2
                    L_0x002d:
                        r4 = move-exception
                        r3.addSuppressed(r4)
                        goto L_0x002c
                    L_0x0032:
                        r0.close()
                        goto L_0x002c
                    L_0x0036:
                        r2 = move-exception
                        goto L_0x0025
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.ThreadExecutor.AnonymousClass1.call():java.lang.Object");
                }
            });
        }
        if (callback != null) {
            Futures.addCallback(future, callback, this.mUiThreadExecutor);
        } else {
            Futures.addCallback(future, new FutureCallback<T>() {
                /* class com.oculus.panellib.ThreadExecutor.AnonymousClass2 */

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onSuccess(T t) {
                }

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onFailure(Throwable t) {
                    throw new RuntimeException(t);
                }
            }, this.mUiThreadExecutor);
        }
    }
}
