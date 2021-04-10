package com.facebook.quicklog.identifiers;

public class IgAndroidAvInterleave {
    public static final int INTERLEAVE = 60424193;
    public static final short MODULE_ID = 922;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IG_ANDROID_AV_INTERLEAVE_INTERLEAVE";
    }
}
