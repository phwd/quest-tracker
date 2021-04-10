package com.oculus.executors;

import X.AbstractC057411o;
import X.AbstractScheduledExecutorServiceC01470ef;
import X.AnonymousClass0BN;
import X.AnonymousClass11s;
import android.os.Handler;
import android.os.Looper;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OculusThreadExecutor implements Executor {
    public static final int MAXIMUM_POOL_SIZE = 4;
    public static OculusThreadExecutor sInstance;
    public final AbstractScheduledExecutorServiceC01470ef mExecutorService;
    public final UiThreadExecutor mUiThreadExecutor;

    public static class UiThreadExecutor implements Executor {
        public final Handler mHandler = new Handler(Looper.getMainLooper());

        public void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    public static OculusThreadExecutor getInstance() {
        OculusThreadExecutor oculusThreadExecutor = sInstance;
        if (oculusThreadExecutor != null) {
            return oculusThreadExecutor;
        }
        OculusThreadExecutor oculusThreadExecutor2 = new OculusThreadExecutor();
        sInstance = oculusThreadExecutor2;
        return oculusThreadExecutor2;
    }

    public void execute(Runnable runnable) {
        this.mExecutorService.execute(runnable);
    }

    public void executeAfterDelay(Runnable runnable, int i) {
        this.mExecutorService.A9V(runnable, (long) i, TimeUnit.MILLISECONDS);
    }

    public void executeWithCallback(Callable callable, AbstractC057411o r5) {
        ListenableFuture AAX = this.mExecutorService.AAX(callable);
        UiThreadExecutor uiThreadExecutor = this.mUiThreadExecutor;
        if (r5 != null) {
            AAX.addListener(new AnonymousClass11s(AAX, r5), uiThreadExecutor);
            return;
        }
        throw null;
    }

    public void runOnUiThread(Runnable runnable) {
        this.mUiThreadExecutor.execute(runnable);
    }

    public OculusThreadExecutor() {
        AbstractScheduledExecutorServiceC01470ef r1;
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(4);
        if (newScheduledThreadPool instanceof AbstractScheduledExecutorServiceC01470ef) {
            r1 = (AbstractScheduledExecutorServiceC01470ef) newScheduledThreadPool;
        } else {
            r1 = new AnonymousClass0BN(newScheduledThreadPool);
        }
        this.mExecutorService = r1;
        this.mUiThreadExecutor = new UiThreadExecutor();
    }

    public AbstractScheduledExecutorServiceC01470ef getExecutorService() {
        return this.mExecutorService;
    }
}
