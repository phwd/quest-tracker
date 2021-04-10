package com.facebook.quicklog.identifiers;

public class IgAndroidIngestion {
    public static final int CONFIGURE = 51052547;
    public static final int COVER_UPLOAD = 51052546;
    public static final short MODULE_ID = 779;
    public static final int PUBLISH = 51052545;
    public static final int RENDER = 51052548;
    public static final int UPLOAD = 51052549;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "IG_ANDROID_INGESTION_UPLOAD" : "IG_ANDROID_INGESTION_RENDER" : "IG_ANDROID_INGESTION_CONFIGURE" : "IG_ANDROID_INGESTION_COVER_UPLOAD" : "IG_ANDROID_INGESTION_PUBLISH";
    }
}
