package com.facebook.quicklog.identifiers;

public class AndroidImageLoadReliability {
    public static final int IMAGE_LOAD_RELIABILITY = 61014017;
    public static final short MODULE_ID = 931;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ANDROID_IMAGE_LOAD_RELIABILITY_IMAGE_LOAD_RELIABILITY";
    }
}
