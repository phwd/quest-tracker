package com.facebook.quicklog.identifiers;

public class InstagramReporting {
    public static final short MODULE_ID = 4638;
    public static final int OPEN_REPORT_FLOW = 303970949;
    public static final int PAGE_LOADED = 303965077;
    public static final int REPORT_FUNNEL = 303960177;

    public static String getMarkerName(int i) {
        return i != 4209 ? i != 9109 ? i != 14981 ? "UNDEFINED_QPL_EVENT" : "INSTAGRAM_REPORTING_OPEN_REPORT_FLOW" : "INSTAGRAM_REPORTING_PAGE_LOADED" : "INSTAGRAM_REPORTING_REPORT_FUNNEL";
    }
}
