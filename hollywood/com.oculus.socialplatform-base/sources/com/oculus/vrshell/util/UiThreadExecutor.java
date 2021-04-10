package com.oculus.vrshell.util;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.concurrent.Executor;

public class UiThreadExecutor implements Executor {
    public static final String TAG = LoggingUtil.tag(UiThreadExecutor.class);
    public static UiThreadExecutor sInstance;
    public final Handler mHandler = new Handler(Looper.getMainLooper());

    public static synchronized UiThreadExecutor getInstance() {
        UiThreadExecutor uiThreadExecutor;
        synchronized (UiThreadExecutor.class) {
            uiThreadExecutor = sInstance;
            if (uiThreadExecutor == null) {
                uiThreadExecutor = new UiThreadExecutor();
                sInstance = uiThreadExecutor;
            }
        }
        return uiThreadExecutor;
    }

    public void execute(Runnable runnable) {
        if (this.mHandler.getLooper().getThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            this.mHandler.post(new Runnable(runnable) {
                /* class com.oculus.vrshell.util.$$Lambda$UiThreadExecutor$FMfjO6bm12Ml5nQw3muVuMRUKdA2 */
                public final /* synthetic */ Runnable f$0;

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
