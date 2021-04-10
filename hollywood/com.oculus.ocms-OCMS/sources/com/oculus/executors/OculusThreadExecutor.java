package com.oculus.executors;

import android.os.Handler;
import android.os.Looper;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OculusThreadExecutor implements Executor {
    private static final int MAXIMUM_POOL_SIZE = 4;
    private static OculusThreadExecutor sInstance;
    private final ListeningScheduledExecutorService mExecutorService = MoreExecutors.listeningDecorator(Executors.newScheduledThreadPool(4));
    private final UiThreadExecutor mUiThreadExecutor = new UiThreadExecutor();

    static class UiThreadExecutor implements Executor {
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        UiThreadExecutor() {
        }

        public void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    public static OculusThreadExecutor getInstance() {
        if (sInstance == null) {
            sInstance = new OculusThreadExecutor();
        }
        return sInstance;
    }

    public ListeningScheduledExecutorService getExecutorService() {
        return this.mExecutorService;
    }

    public void execute(Runnable runnable) {
        this.mExecutorService.execute(runnable);
    }

    public void executeWithCallback(Callable callable, FutureCallback futureCallback) {
        Futures.addCallback(this.mExecutorService.submit(callable), futureCallback, this.mUiThreadExecutor);
    }

    public void executeAfterDelay(Runnable runnable, int i) {
        this.mExecutorService.schedule(runnable, (long) i, TimeUnit.MILLISECONDS);
    }

    public void runOnUiThread(Runnable runnable) {
        this.mUiThreadExecutor.execute(runnable);
    }
}
