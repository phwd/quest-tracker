package com.facebook.quicklog.identifiers;

public class Applinks {
    public static final int API_REQUEST_FAILED = 6881281;
    public static final int FAILED_TO_START_INTENT = 6881285;
    public static final int KATANA_AND_WAKIZASHI_NOT_INSTALLED = 6881282;
    public static final short MODULE_ID = 105;
    public static final int POSSIBLE_APPLINKS_URL = 6881286;
    public static final int SKIPPED_BECAUSE_DATA_NOT_READY = 6881284;
    public static final int STARTED_INTENT_SUCCESSFULLY = 6881287;
    public static final int URL_CLICKED = 6881283;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "APPLINKS_API_REQUEST_FAILED";
            case 2:
                return "APPLINKS_KATANA_AND_WAKIZASHI_NOT_INSTALLED";
            case 3:
                return "APPLINKS_URL_CLICKED";
            case 4:
                return "APPLINKS_SKIPPED_BECAUSE_DATA_NOT_READY";
            case 5:
                return "APPLINKS_FAILED_TO_START_INTENT";
            case 6:
                return "APPLINKS_POSSIBLE_APPLINKS_URL";
            case 7:
                return "APPLINKS_STARTED_INTENT_SUCCESSFULLY";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
