package com.oculus.panellib;

public class SystemProperties {
    public static Class<?> SYSTEM_PROPS_CLASS = null;
    public static final String TAG = "SystemProperties";

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
            return str2;
        }
    }

    public static double getDouble(String str, double d) {
        return Double.parseDouble(getString(str, Double.toString(d)));
    }

    public static int getInteger(String str, int i) {
        return Integer.parseInt(getString(str, Integer.toString(i)));
    }
}
