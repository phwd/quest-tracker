package com.oculus.userserver.api;

import android.annotation.TargetApi;
import android.os.Looper;
import com.oculus.os.Version;

public class Utils {
    public static void assertState(boolean state, String message) {
        if (!state) {
            throw new IllegalStateException(message);
        }
    }

    @TargetApi(Version.VERSION_23)
    public static void assertNonUiThread() {
        assertState(!Looper.getMainLooper().isCurrentThread(), "Must be on non ui thread!");
    }
}
