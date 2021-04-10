package com.facebook.quicklog.identifiers;

public class AlohaColdStarts {
    public static final int ALOHA_APP_COLD_START = 20054017;
    public static final short MODULE_ID = 306;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ALOHA_COLD_STARTS_ALOHA_APP_COLD_START";
    }
}
