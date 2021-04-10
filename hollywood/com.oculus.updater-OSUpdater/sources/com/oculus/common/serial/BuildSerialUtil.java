package com.oculus.common.serial;

import android.os.Build;
import com.facebook.debug.log.BLog;

public class BuildSerialUtil {
    private static final String TAG = "BuildSerialUtil";

    public static String getSerial() {
        if (Build.VERSION.SDK_INT < 26) {
            return Build.SERIAL;
        }
        try {
            return Build.getSerial();
        } catch (SecurityException e) {
            BLog.d(TAG, "Issue getting device serial number", (Throwable) e);
            return "PERMISSION_DENIED";
        }
    }
}
