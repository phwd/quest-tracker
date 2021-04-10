package com.oculus.executors;

import X.AnonymousClass6f;
import X.UE;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class OculusThreadExecutor implements Executor {
    public static final int MAXIMUM_POOL_SIZE = 4;
    public static OculusThreadExecutor sInstance;
    public final UE mExecutorService;
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
        UE r1;
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(4);
        if (newScheduledThreadPool instanceof UE) {
            r1 = (UE) newScheduledThreadPool;
        } else {
            r1 = new AnonymousClass6f(newScheduledThreadPool);
        }
        this.mExecutorService = r1;
        this.mUiThreadExecutor = new UiThreadExecutor();
    }
}
