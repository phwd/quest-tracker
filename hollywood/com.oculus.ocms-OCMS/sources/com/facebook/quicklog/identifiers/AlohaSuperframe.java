package com.facebook.quicklog.identifiers;

public class AlohaSuperframe {
    public static final int AMBIENT_PHOTOS_FETCH_FRAMES = 43451597;
    public static final int AMBIENT_PHOTOS_PRELOAD_MEDIA = 43458207;
    public static final int IDLE_SCREEN_ACTIVITY_LOADING = 43450371;
    public static final short MODULE_ID = 663;
    public static final int SUPERFRAME_LOADING = 43450370;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? i != 1229 ? i != 7839 ? "UNDEFINED_QPL_EVENT" : "ALOHA_SUPERFRAME_AMBIENT_PHOTOS_PRELOAD_MEDIA" : "ALOHA_SUPERFRAME_AMBIENT_PHOTOS_FETCH_FRAMES" : "ALOHA_SUPERFRAME_IDLE_SCREEN_ACTIVITY_LOADING" : "ALOHA_SUPERFRAME_SUPERFRAME_LOADING";
    }
}
