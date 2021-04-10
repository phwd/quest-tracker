package com.facebook.quicklog.identifiers;

public class IgMediaUpload {
    public static final int IGTV = 62652417;
    public static final short MODULE_ID = 956;
    public static final int POST_LIVE_IGTV = 62652418;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "IG_MEDIA_UPLOAD_POST_LIVE_IGTV" : "IG_MEDIA_UPLOAD_IGTV";
    }
}
