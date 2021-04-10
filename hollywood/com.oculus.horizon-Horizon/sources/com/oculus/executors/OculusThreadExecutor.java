package com.oculus.executors;

import X.AbstractScheduledExecutorServiceC03390co;
import X.AnonymousClass06R;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class OculusThreadExecutor implements Executor {
    public static final int MAXIMUM_POOL_SIZE = 4;
    public static OculusThreadExecutor sInstance;
    public final AbstractScheduledExecutorServiceC03390co mExecutorService;
    public final UiThreadExecutor mUiThreadExecutor;

    public static class UiThreadExecutor implements Executor {
        public final Handler mHandler = new Handler(Looper.getMainLooper());

        public final void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    public static OculusThreadExecutor A00() {
        OculusThreadExecutor oculusThreadExecutor = sInstance;
        if (oculusThreadExecutor != null) {
            return oculusThreadExecutor;
        }
        OculusThreadExecutor oculusThreadExecutor2 = new OculusThreadExecutor();
        sInstance = oculusThreadExecutor2;
        return oculusThreadExecutor2;
    }

    public final void execute(Runnable runnable) {
        this.mExecutorService.execute(runnable);
    }

    public OculusThreadExecutor() {
        AbstractScheduledExecutorServiceC03390co r1;
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(4);
        if (newScheduledThreadPool instanceof AbstractScheduledExecutorServiceC03390co) {
            r1 = (AbstractScheduledExecutorServiceC03390co) newScheduledThreadPool;
        } else {
            r1 = new AnonymousClass06R(newScheduledThreadPool);
        }
        this.mExecutorService = r1;
        this.mUiThreadExecutor = new UiThreadExecutor();
    }
}
