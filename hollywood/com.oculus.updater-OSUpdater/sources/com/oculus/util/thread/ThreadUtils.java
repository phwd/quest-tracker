package com.oculus.util.thread;

import android.os.Handler;
import android.os.Looper;
import com.facebook.ultralight.Dependencies;

@Dependencies
public class ThreadUtils {
    @Deprecated
    public static void runOnUiThread(Runnable runnable) {
        if (isUiThread()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    public static boolean isUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
