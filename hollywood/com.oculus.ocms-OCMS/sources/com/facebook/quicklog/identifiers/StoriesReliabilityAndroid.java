package com.facebook.quicklog.identifiers;

public class StoriesReliabilityAndroid {
    public static final int DATA_LAYER = 47513602;
    public static final int MEDIA_EVENT = 47515225;
    public static final short MODULE_ID = 725;
    public static final int NETWORK = 47513601;
    public static final int TRAY = 47513604;
    public static final int UI_LAYER = 47513605;
    public static final int UNSAMPLED_DEBUG_EVENT = 47529776;
    public static final int USER_ACTION = 47513603;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 1625 ? i != 16176 ? "UNDEFINED_QPL_EVENT" : "STORIES_RELIABILITY_ANDROID_UNSAMPLED_DEBUG_EVENT" : "STORIES_RELIABILITY_ANDROID_MEDIA_EVENT" : "STORIES_RELIABILITY_ANDROID_UI_LAYER" : "STORIES_RELIABILITY_ANDROID_TRAY" : "STORIES_RELIABILITY_ANDROID_USER_ACTION" : "STORIES_RELIABILITY_ANDROID_DATA_LAYER" : "STORIES_RELIABILITY_ANDROID_NETWORK";
    }
}
