package com.facebook.quicklog.identifiers;

public class HelpCenter {
    public static final int CONTENT_REDESIGN = 821831905;
    public static final int HOME_REDESIGN = 821836711;
    public static final short MODULE_ID = 12540;
    public static final int SEARCH_REDESIGN = 821836802;

    public static String getMarkerName(int i) {
        return i != 10465 ? i != 15271 ? i != 15362 ? "UNDEFINED_QPL_EVENT" : "HELP_CENTER_SEARCH_REDESIGN" : "HELP_CENTER_HOME_REDESIGN" : "HELP_CENTER_CONTENT_REDESIGN";
    }
}
