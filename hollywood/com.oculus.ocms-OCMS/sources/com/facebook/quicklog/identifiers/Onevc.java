package com.facebook.quicklog.identifiers;

public class Onevc {
    public static final int ANDROID_PRECALL_JOINABLELINK = 51642369;
    public static final short MODULE_ID = 788;
    public static final int ONEVC_RAISE_HAND = 51642371;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "ONEVC_ONEVC_RAISE_HAND" : "ONEVC_ANDROID_PRECALL_JOINABLELINK";
    }
}
