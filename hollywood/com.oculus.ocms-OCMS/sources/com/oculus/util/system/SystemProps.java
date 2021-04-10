package com.oculus.util.system;

import android.util.Log;

public class SystemProps {
    private static Class<?> CLASS = null;
    private static final String TAG = "SystemProps";

    private SystemProps() {
    }

    static {
        try {
            CLASS = Class.forName("android.os.SystemProperties");
        } catch (ClassNotFoundException unused) {
        }
    }

    public static String getString(String str, String str2) {
        try {
            return (String) CLASS.getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            Log.d(TAG, "getString() returning default " + str2);
            return str2;
        }
    }

    public static boolean getBool(String str, boolean z) {
        try {
            return ((Boolean) CLASS.getMethod("getBoolean", String.class, Boolean.TYPE).invoke(null, str, Boolean.valueOf(z))).booleanValue();
        } catch (Exception unused) {
            Log.d(TAG, "getBool() returning default " + z);
            return z;
        }
    }

    public static int getInt(String str, int i) {
        try {
            return ((Integer) CLASS.getMethod("getInt", String.class, Integer.TYPE).invoke(null, str, Integer.valueOf(i))).intValue();
        } catch (Exception unused) {
            Log.d(TAG, "getInt() returning default " + i);
            return i;
        }
    }
}
