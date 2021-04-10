package com.facebook.common.util;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.view.ViewCompat;
import javax.annotation.Nullable;
import javax.inject.Singleton;

@Singleton
public class SecureWindowUtil {
    public static boolean adjustSecureWindowFlag(boolean z, @Nullable Activity activity) {
        return adjustSecureWindowFlagInternal(z, activity);
    }

    public static boolean adjustSecureWindowFlag(boolean z, Activity activity, boolean z2) {
        boolean adjustSecureWindowFlagInternal = adjustSecureWindowFlagInternal(z, activity);
        if (!z2 || !adjustSecureWindowFlagInternal) {
            return adjustSecureWindowFlagInternal;
        }
        Window window = activity.getWindow();
        WindowManager windowManager = activity.getWindowManager();
        if (ViewCompat.isAttachedToWindow(window.getDecorView())) {
            windowManager.removeViewImmediate(window.getDecorView());
            windowManager.addView(window.getDecorView(), window.getAttributes());
        }
        return true;
    }

    public static boolean adjustSecureWindowFlagAndRecreateActivity(boolean z, Activity activity) {
        boolean adjustSecureWindowFlagInternal = adjustSecureWindowFlagInternal(z, activity);
        if (adjustSecureWindowFlagInternal) {
            activity.recreate();
        }
        return adjustSecureWindowFlagInternal;
    }

    private static boolean adjustSecureWindowFlagInternal(boolean z, @Nullable Activity activity) {
        if (activity == null || activity.getWindow() == null || activity.getWindowManager() == null) {
            return false;
        }
        Window window = activity.getWindow();
        if (z == ((window.getAttributes().flags & 8192) != 0)) {
            return false;
        }
        if (z) {
            window.addFlags(8192);
        } else {
            window.clearFlags(8192);
        }
        return true;
    }
}
