package com.facebook.quicklog.identifiers;

public class Browser {
    public static final int BROWSER_CONTEXT_NETWORK_FETCH = 1835010;
    public static final int BROWSER_PIVOTS_HIDE = 1835011;
    public static final int BROWSER_PIVOTS_SHOW = 1835012;
    public static final short MODULE_ID = 28;
    public static final int PERF_MARK_BROWSERFRAGMENT_INITIALIZE = 1835013;
    public static final int PERF_MARK_FIRST_WEBVIEW_INVALIDATE = 1835014;
    public static final int PERF_MARK_PAGE_FINISHED = 1835015;
    public static final int WEB_VIEW_LOAD = 1835009;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "BROWSER_WEB_VIEW_LOAD";
            case 2:
                return "BROWSER_BROWSER_CONTEXT_NETWORK_FETCH";
            case 3:
                return "BROWSER_BROWSER_PIVOTS_HIDE";
            case 4:
                return "BROWSER_BROWSER_PIVOTS_SHOW";
            case 5:
                return "BROWSER_PERF_MARK_BROWSERFRAGMENT_INITIALIZE";
            case 6:
                return "BROWSER_PERF_MARK_FIRST_WEBVIEW_INVALIDATE";
            case 7:
                return "BROWSER_PERF_MARK_PAGE_FINISHED";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
