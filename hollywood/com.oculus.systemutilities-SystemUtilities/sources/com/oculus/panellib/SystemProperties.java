package com.oculus.panellib;

import android.util.Log;

public class SystemProperties {
    private static Class<?> SYSTEM_PROPS_CLASS;
    private static final String TAG = SystemProperties.class.getSimpleName();

    static {
        try {
            SYSTEM_PROPS_CLASS = Class.forName("android.os.SystemProperties");
        } catch (ClassNotFoundException e) {
        }
    }

    private SystemProperties() {
    }

    public static String getString(String key, String defaultValue) {
        try {
            return (String) SYSTEM_PROPS_CLASS.getMethod("get", String.class, String.class).invoke(null, key, defaultValue);
        } catch (Exception e) {
            Log.d(TAG, "getString() returning default " + defaultValue);
            return defaultValue;
        }
    }
}
