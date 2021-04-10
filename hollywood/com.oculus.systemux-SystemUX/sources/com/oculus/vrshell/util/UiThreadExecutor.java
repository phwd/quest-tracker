package com.oculus.vrshell.util;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.concurrent.Executor;

public class UiThreadExecutor implements Executor {
    private static final String TAG = LoggingUtil.tag(UiThreadExecutor.class);
    private static UiThreadExecutor sInstance;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private UiThreadExecutor() {
    }

    public static synchronized UiThreadExecutor getInstance() {
        UiThreadExecutor uiThreadExecutor;
        synchronized (UiThreadExecutor.class) {
            if (sInstance == null) {
                sInstance = new UiThreadExecutor();
            }
            uiThreadExecutor = sInstance;
        }
        return uiThreadExecutor;
    }

    public void execute(Runnable runnable) {
        if (this.mHandler.getLooper().getThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            this.mHandler.post(new Runnable(runnable) {
                /* class com.oculus.vrshell.util.$$Lambda$UiThreadExecutor$Xpuol0ub5dXLJIAyxDLRjKlXYHk */
                private final /* synthetic */ Runnable f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    this.f$0.run();
                }
            });
        }
    }

    @VisibleForTesting
    public static void setInstance(UiThreadExecutor uiThreadExecutor) {
        sInstance = uiThreadExecutor;
    }
}
