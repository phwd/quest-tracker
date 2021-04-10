package com.facebook.quicklog.identifiers;

public class Fbanalytics {
    public static final int ANRREPORT = 8192002;
    public static final short MODULE_ID = 125;

    public static String getMarkerName(int i) {
        return i != 2 ? "UNDEFINED_QPL_EVENT" : "FBANALYTICS_ANRREPORT";
    }
}
