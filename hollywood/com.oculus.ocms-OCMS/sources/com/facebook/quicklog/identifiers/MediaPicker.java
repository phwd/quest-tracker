package com.facebook.quicklog.identifiers;

public class MediaPicker {
    public static final int MEDIAPICKER_LAUNCH = 3407873;
    public static final short MODULE_ID = 52;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "MEDIA_PICKER_MEDIAPICKER_LAUNCH";
    }
}
