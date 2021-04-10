package com.oculus.vrapi;

import android.util.Log;
import java.lang.reflect.Method;

public class SystemProps {
    private static final String TAG = "SystemProps";
    private static Method getBoolMethod;
    private static Method getIntMethod;
    private static Method getStringMethod;

    private SystemProps() {
    }

    static {
        try {
            Class<?> CLASS = Class.forName("android.os.SystemProperties");
            getStringMethod = CLASS.getMethod("get", String.class, String.class);
            getBoolMethod = CLASS.getMethod("getBoolean", String.class, Boolean.TYPE);
            getIntMethod = CLASS.getMethod("getInt", String.class, Integer.TYPE);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
        }
    }

    public static String getString(String key, String defaultValue) {
        try {
            return (String) getStringMethod.invoke(null, key, defaultValue);
        } catch (Exception e) {
            Log.d(TAG, "getString() returning default " + defaultValue);
            return defaultValue;
        }
    }

    public static boolean getBool(String key, boolean defaultValue) {
        try {
            return ((Boolean) getBoolMethod.invoke(null, key, Boolean.valueOf(defaultValue))).booleanValue();
        } catch (Exception e) {
            Log.d(TAG, "getBool() returning default " + defaultValue);
            return defaultValue;
        }
    }

    public static int getInt(String key, int defaultValue) {
        try {
            return ((Integer) getIntMethod.invoke(null, key, Integer.valueOf(defaultValue))).intValue();
        } catch (Exception e) {
            Log.d(TAG, "getInt() returning default " + defaultValue);
            return defaultValue;
        }
    }
}
