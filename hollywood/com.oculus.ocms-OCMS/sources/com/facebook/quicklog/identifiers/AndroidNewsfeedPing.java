package com.facebook.quicklog.identifiers;

public class AndroidNewsfeedPing {
    public static final short MODULE_ID = 429;
    public static final int PING_ANDROID = 28114945;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ANDROID_NEWSFEED_PING_PING_ANDROID";
    }
}
