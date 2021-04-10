package com.facebook.quicklog.identifiers;

public class IgGrowth {
    public static final int ACTIVITY_FEED_LOAD = 16908289;
    public static final int CONTACT_IMPORT_PAGE_LOAD = 16908291;
    public static final int GCM_HIGH_PRIORITY_PROCESS = 16908290;
    public static final short MODULE_ID = 258;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "IG_GROWTH_CONTACT_IMPORT_PAGE_LOAD" : "IG_GROWTH_GCM_HIGH_PRIORITY_PROCESS" : "IG_GROWTH_ACTIVITY_FEED_LOAD";
    }
}
