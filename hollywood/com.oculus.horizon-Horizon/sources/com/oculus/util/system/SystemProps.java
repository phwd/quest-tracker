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
}
