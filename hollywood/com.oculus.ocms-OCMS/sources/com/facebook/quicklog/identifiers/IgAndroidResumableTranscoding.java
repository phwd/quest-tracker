package com.facebook.quicklog.identifiers;

public class IgAndroidResumableTranscoding {
    public static final short MODULE_ID = 899;
    public static final int RESUME_TRANSCODING = 58916865;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IG_ANDROID_RESUMABLE_TRANSCODING_RESUME_TRANSCODING";
    }
}
