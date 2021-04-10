package com.facebook.quicklog.identifiers;

public class AndroidJavaToNative {
    public static final short MODULE_ID = 440;
    public static final int NAVIGATION_MODULE_ANDROID = 28835841;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ANDROID_JAVA_TO_NATIVE_NAVIGATION_MODULE_ANDROID";
    }
}
