package com.facebook.quicklog.identifiers;

public class Casting {
    public static final int CAST_FIRST_DEVICE_CONNECTION = 62128130;
    public static final int CAST_NEXT_VIDEO_REQUEST = 62128132;
    public static final int CAST_PLAYBACK_STARTED = 62128134;
    public static final int CAST_RECEIVER_APP_LOAD = 62128131;
    public static final int CAST_STARTUP = 62128135;
    public static final int CAST_VIDEO_METADATA_REQUEST = 62128133;
    public static final int FETCHER_VIDEO_METADATA_REQUEST = 62134670;
    public static final short MODULE_ID = 948;
    public static final int TV_APPS_CASTING_FUNNEL_TEST = 62138396;

    public static String getMarkerName(int i) {
        if (i == 6542) {
            return "CASTING_FETCHER_VIDEO_METADATA_REQUEST";
        }
        if (i == 10268) {
            return "CASTING_TV_APPS_CASTING_FUNNEL_TEST";
        }
        switch (i) {
            case 2:
                return "CASTING_CAST_FIRST_DEVICE_CONNECTION";
            case 3:
                return "CASTING_CAST_RECEIVER_APP_LOAD";
            case 4:
                return "CASTING_CAST_NEXT_VIDEO_REQUEST";
            case 5:
                return "CASTING_CAST_VIDEO_METADATA_REQUEST";
            case 6:
                return "CASTING_CAST_PLAYBACK_STARTED";
            case 7:
                return "CASTING_CAST_STARTUP";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
