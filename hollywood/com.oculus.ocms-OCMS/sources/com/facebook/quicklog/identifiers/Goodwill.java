package com.facebook.quicklog.identifiers;

public class Goodwill {
    public static final int GPS_PLAYER_TTRC = 4915213;
    public static final int GPS_SCROLL_PERF = 4915214;
    public static final int MEMORIES_HOME_GOT_MH_DATA_WHEN_OFF = 4915210;
    public static final int MEMORIES_HOME_TTRC = 4915211;
    public static final int MEMORIES_TAIL_LOAD_TTI = 4915215;
    public static final short MODULE_ID = 75;
    public static final int RECAP_BACKGROUND_RUN = 4915208;
    public static final int RECAP_READ_PHOTOS_IOS = 4915205;
    public static final int RECAP_RETRIEVE_SCREENSHOTS_IOS = 4915209;
    public static final int RECAP_SELECT_PHOTOS_IOS = 4915206;
    public static final int RECAP_STORE_PHOTOS_IOS = 4915204;
    public static final int RECAP_XRAY_MODEL_LOAD_IOS = 4915203;
    public static final int RECAP_XRAY_SINGLE_PHOTO = 4915207;
    public static final int THROWBACK_FEED_TTI = 4915201;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "GOODWILL_THROWBACK_FEED_TTI";
            case 2:
            case 12:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 3:
                return "GOODWILL_RECAP_XRAY_MODEL_LOAD_IOS";
            case 4:
                return "GOODWILL_RECAP_STORE_PHOTOS_IOS";
            case 5:
                return "GOODWILL_RECAP_READ_PHOTOS_IOS";
            case 6:
                return "GOODWILL_RECAP_SELECT_PHOTOS_IOS";
            case 7:
                return "GOODWILL_RECAP_XRAY_SINGLE_PHOTO";
            case 8:
                return "GOODWILL_RECAP_BACKGROUND_RUN";
            case 9:
                return "GOODWILL_RECAP_RETRIEVE_SCREENSHOTS_IOS";
            case 10:
                return "GOODWILL_MEMORIES_HOME_GOT_MH_DATA_WHEN_OFF";
            case 11:
                return "GOODWILL_MEMORIES_HOME_TTRC";
            case 13:
                return "GOODWILL_GPS_PLAYER_TTRC";
            case 14:
                return "GOODWILL_GPS_SCROLL_PERF";
            case 15:
                return "GOODWILL_MEMORIES_TAIL_LOAD_TTI";
        }
    }
}
