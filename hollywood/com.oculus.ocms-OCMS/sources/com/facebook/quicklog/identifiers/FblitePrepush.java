package com.facebook.quicklog.identifiers;

public class FblitePrepush {
    public static final short MODULE_ID = 11716;
    public static final int NAVIGATION_OPPORTUNITIES = 767833611;
    public static final int PREPUSH_WORKFLOW = 767835980;

    public static String getMarkerName(int i) {
        return i != 13835 ? i != 16204 ? "UNDEFINED_QPL_EVENT" : "FBLITE_PREPUSH_PREPUSH_WORKFLOW" : "FBLITE_PREPUSH_NAVIGATION_OPPORTUNITIES";
    }
}
