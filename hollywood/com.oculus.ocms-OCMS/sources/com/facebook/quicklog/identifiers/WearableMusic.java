package com.facebook.quicklog.identifiers;

public class WearableMusic {
    public static final int APP_START = 656543095;
    public static final int BROWSE_SCROLL_PERF = 656545865;
    public static final int MEDIA_CONTACT_SHARE = 656545436;
    public static final int MEDIA_STORY_SHARE = 656543482;
    public static final short MODULE_ID = 10018;
    public static final int START_MEDIA_PLAYBACK = 656546481;

    public static String getMarkerName(int i) {
        return i != 3447 ? i != 3834 ? i != 5788 ? i != 6217 ? i != 6833 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_MUSIC_START_MEDIA_PLAYBACK" : "WEARABLE_MUSIC_BROWSE_SCROLL_PERF" : "WEARABLE_MUSIC_MEDIA_CONTACT_SHARE" : "WEARABLE_MUSIC_MEDIA_STORY_SHARE" : "WEARABLE_MUSIC_APP_START";
    }
}
