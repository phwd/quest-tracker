package com.facebook.quicklog.identifiers;

public class AndroidPigeon {
    public static final int CREATE = 29163521;
    public static final short MODULE_ID = 445;
    public static final int SEND = 29163522;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "ANDROID_PIGEON_SEND" : "ANDROID_PIGEON_CREATE";
    }
}
