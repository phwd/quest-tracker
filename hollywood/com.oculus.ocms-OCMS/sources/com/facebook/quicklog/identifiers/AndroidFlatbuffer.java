package com.facebook.quicklog.identifiers;

public class AndroidFlatbuffer {
    public static final int FEED_BUFFER_INITIALIZE_ERROR = 11403265;
    public static final int FEED_CHECK_FOR_BAD_SERIALIZE = 11403266;
    public static final short MODULE_ID = 174;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "ANDROID_FLATBUFFER_FEED_CHECK_FOR_BAD_SERIALIZE" : "ANDROID_FLATBUFFER_FEED_BUFFER_INITIALIZE_ERROR";
    }
}
