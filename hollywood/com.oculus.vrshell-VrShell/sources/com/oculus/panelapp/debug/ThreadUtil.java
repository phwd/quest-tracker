package com.oculus.panelapp.debug;

import android.content.Context;
import android.os.Handler;

public class ThreadUtil {
    public static void runOnUiThread(Context context, Runnable runnable) {
        new Handler(context.getMainLooper()).post(runnable);
    }
}
