package com.oculus.panellib;

import X.AbstractC057411o;
import X.AbstractScheduledExecutorServiceC01470ef;
import X.AnonymousClass0BN;
import X.AnonymousClass11s;
import X.AnonymousClass129;
import android.os.Handler;
import android.os.Looper;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadExecutor {
    public static final int MAXIMUM_POOL_SIZE = 4;
    public static ThreadExecutor sInstance;
    public final AnonymousClass129 mExecutorService;
    public final UiThreadExecutor mUiThreadExecutor;

    public static class UiThreadExecutor implements Executor {
        public final Handler mHandler;

        public void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }

        public UiThreadExecutor() {
            this.mHandler = new Handler(Looper.getMainLooper());
        }
    }

    public static ThreadExecutor getInstance() {
        ThreadExecutor threadExecutor = sInstance;
        if (threadExecutor != null) {
            return threadExecutor;
        }
        ThreadExecutor threadExecutor2 = new ThreadExecutor();
        sInstance = threadExecutor2;
        return threadExecutor2;
    }

    public ThreadExecutor() {
        AnonymousClass129 r1;
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(4);
        if (newScheduledThreadPool instanceof AbstractScheduledExecutorServiceC01470ef) {
            r1 = (AnonymousClass129) newScheduledThreadPool;
        } else {
            r1 = new AnonymousClass0BN(newScheduledThreadPool);
        }
        this.mExecutorService = r1;
        this.mUiThreadExecutor = new UiThreadExecutor();
    }

    public <T> void execute(Callable<T> callable) {
        execute(callable, null, "ThreadExecutor::execute");
    }

    public <T> void execute(Callable<T> callable, AbstractC057411o<T> r3) {
        execute(callable, r3, "ThreadExecutor::execute");
    }

    public <T> void execute(final Callable<T> callable, AbstractC057411o<T> r5, final String str) {
        ListenableFuture<T> AAX;
        if (!Systrace.isEnabled) {
            AAX = this.mExecutorService.AAX(callable);
        } else {
            AAX = this.mExecutorService.AAX(new Callable<T>() {
                /* class com.oculus.panellib.ThreadExecutor.AnonymousClass1 */

                /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
                    r1.close();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
                    throw r0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
                    r0 = move-exception;
                 */
                @Override // java.util.concurrent.Callable
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public T call() throws java.lang.Exception {
                    /*
                        r2 = this;
                        java.lang.String r0 = r6
                        com.oculus.panellib.SystraceBlock r1 = new com.oculus.panellib.SystraceBlock
                        r1.<init>(r0)
                        java.util.concurrent.Callable r0 = r4     // Catch:{ all -> 0x0011 }
                        java.lang.Object r0 = r0.call()     // Catch:{ all -> 0x0011 }
                        r1.close()
                        return r0
                    L_0x0011:
                        r0 = move-exception
                        throw r0     // Catch:{ all -> 0x0013 }
                    L_0x0013:
                        r0 = move-exception
                        r1.close()     // Catch:{ all -> 0x0017 }
                    L_0x0017:
                        throw r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.ThreadExecutor.AnonymousClass1.call():java.lang.Object");
                }
            });
        }
        if (r5 == null) {
            r5 = new AbstractC057411o<T>() {
                /* class com.oculus.panellib.ThreadExecutor.AnonymousClass2 */

                @Override // X.AbstractC057411o
                public void onSuccess(T t) {
                }

                @Override // X.AbstractC057411o
                public void onFailure(Throwable th) {
                    throw new RuntimeException(th);
                }
            };
        }
        AAX.addListener(new AnonymousClass11s(AAX, r5), this.mUiThreadExecutor);
    }

    public <T> void execute(Callable<T> callable, String str) {
        execute(callable, null, str);
    }
}
