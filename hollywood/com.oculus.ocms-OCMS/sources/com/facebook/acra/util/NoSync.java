package com.facebook.acra.util;

import android.os.Build;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class NoSync {
    private static boolean sSyncDisabled = false;

    private static native boolean disableFSSync(int i, boolean z);

    private NoSync() {
    }

    public static void disableFSSync(boolean z) {
        if (!sSyncDisabled) {
            sSyncDisabled = disableFSSync(Build.VERSION.SDK_INT, z);
        }
    }
}
