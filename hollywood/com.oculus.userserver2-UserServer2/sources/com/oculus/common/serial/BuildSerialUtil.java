package com.oculus.common.serial;

import android.os.Build;

public class BuildSerialUtil {
    public static final String PERMISSION_DENIED = "PERMISSION_DENIED";
    public static final String TAG = "BuildSerialUtil";

    public static String A00() {
        if (Build.VERSION.SDK_INT < 26) {
            return Build.SERIAL;
        }
        try {
            return Build.getSerial();
        } catch (SecurityException unused) {
            return PERMISSION_DENIED;
        }
    }
}
