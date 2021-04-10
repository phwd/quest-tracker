package com.oculus.panellib;

import android.util.Log;

public class SystemProperties {
    private static Class<?> SYSTEM_PROPS_CLASS = null;
    private static final String TAG = "SystemProperties";

    private SystemProperties() {
    }

    static {
        try {
            SYSTEM_PROPS_CLASS = Class.forName("android.os.SystemProperties");
        } catch (ClassNotFoundException unused) {
        }
    }

    public static String getString(String str, String str2) {
        try {
            return (String) SYSTEM_PROPS_CLASS.getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            String str3 = TAG;
            Log.d(str3, "getString() returning default " + str2);
            return str2;
        }
    }
}
