package com.oculus.util.system;

public class SystemProps {
    public static Class<?> CLASS = null;
    public static final String TAG = "SystemProps";

    static {
        try {
            CLASS = Class.forName("android.os.SystemProperties");
        } catch (ClassNotFoundException unused) {
        }
    }

    public static boolean getBool(String str, boolean z) {
        try {
            return ((Boolean) CLASS.getMethod("getBoolean", String.class, Boolean.TYPE).invoke(null, str, Boolean.valueOf(z))).booleanValue();
        } catch (Exception unused) {
            return z;
        }
    }

    public static int getInt(String str, int i) {
        try {
            return ((Integer) CLASS.getMethod("getInt", String.class, Integer.TYPE).invoke(null, str, Integer.valueOf(i))).intValue();
        } catch (Exception unused) {
            return i;
        }
    }

    public static String getString(String str, String str2) {
        try {
            return (String) CLASS.getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }
}
