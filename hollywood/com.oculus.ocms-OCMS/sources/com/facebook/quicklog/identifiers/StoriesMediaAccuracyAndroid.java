package com.facebook.quicklog.identifiers;

public class StoriesMediaAccuracyAndroid {
    public static final short MODULE_ID = 11525;
    public static final int STORIES_OPTIMISTIC_DATA_TRACE = 755303525;

    public static String getMarkerName(int i) {
        return i != 1125 ? "UNDEFINED_QPL_EVENT" : "STORIES_MEDIA_ACCURACY_ANDROID_STORIES_OPTIMISTIC_DATA_TRACE";
    }
}
