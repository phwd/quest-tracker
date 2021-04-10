package com.facebook.quicklog.identifiers;

public class AndroidTempMediaDiskFootage {
    public static final short MODULE_ID = 883;
    public static final int SNAPSHOT = 57868289;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ANDROID_TEMP_MEDIA_DISK_FOOTAGE_SNAPSHOT";
    }
}
