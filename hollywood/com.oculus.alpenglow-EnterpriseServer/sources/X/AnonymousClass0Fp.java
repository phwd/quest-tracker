package X;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import androidx.annotation.NonNull;

/* renamed from: X.0Fp  reason: invalid class name */
public enum AnonymousClass0Fp {
    AUTOMATIC,
    TRUNCATE,
    WRITE_AHEAD_LOGGING;

    @SuppressLint({"NewApi"})
    public AnonymousClass0Fp resolve(Context context) {
        if (this != AUTOMATIC) {
            return this;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || isLowRamDevice(activityManager)) {
            return TRUNCATE;
        }
        return WRITE_AHEAD_LOGGING;
    }

    public static boolean isLowRamDevice(@NonNull ActivityManager activityManager) {
        return activityManager.isLowRamDevice();
    }
}
