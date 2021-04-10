package com.oculus.userserver.api;

import android.annotation.TargetApi;
import android.os.Looper;

public class Utils {
    @TargetApi(23)
    public static void A00() {
        if (!(!Looper.getMainLooper().isCurrentThread())) {
            throw new IllegalStateException("Must be on non ui thread!");
        }
    }
}
