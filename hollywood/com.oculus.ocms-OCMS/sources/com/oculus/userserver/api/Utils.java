package com.oculus.userserver.api;

import android.annotation.TargetApi;
import android.os.Looper;

public class Utils {
    public static void assertState(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    @TargetApi(23)
    public static void assertNonUiThread() {
        assertState(!Looper.getMainLooper().isCurrentThread(), "Must be on non ui thread!");
    }
}
