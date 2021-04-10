package com.oculus.executors;

import android.os.Handler;
import android.os.Looper;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class OculusThreadExecutor implements Executor {
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

    public void execute(Runnable runnable) {
        this.mExecutorService.execute(runnable);
    }
}
