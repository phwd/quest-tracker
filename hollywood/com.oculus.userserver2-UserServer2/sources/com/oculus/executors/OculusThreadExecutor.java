package com.oculus.executors;

import X.AnonymousClass6b;
import X.MF;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class OculusThreadExecutor implements Executor {
    public static final int MAXIMUM_POOL_SIZE = 4;
    public static OculusThreadExecutor sInstance;
    public final MF mExecutorService;
    public final UiThreadExecutor mUiThreadExecutor;

    public static class UiThreadExecutor implements Executor {
        public final Handler mHandler = new Handler(Looper.getMainLooper());

        public final void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    public final void execute(Runnable runnable) {
        this.mExecutorService.execute(runnable);
    }

    public OculusThreadExecutor() {
        MF r1;
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(4);
        if (newScheduledThreadPool instanceof MF) {
            r1 = (MF) newScheduledThreadPool;
        } else {
            r1 = new AnonymousClass6b(newScheduledThreadPool);
        }
        this.mExecutorService = r1;
        this.mUiThreadExecutor = new UiThreadExecutor();
    }
}
