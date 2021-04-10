package com.facebook.quicklog.identifiers;

public class FblitePagingProvider {
    public static final int FBLITE_SEARCH_RESULT_PAGE_PAGING_PROVIDER_FETCH_WAIT = 34275332;
    public static final int FBLITE_STORIES_TOP_TRAY_PAGING_PROVIDER_FETCH_WAIT = 34275331;
    public static final int FBLITE_TIMELINE_PAGING_PROVIDER_FETCH_WAIT = 34275329;
    public static final int FBLITE_WATCHLIST_PAGING_PROVIDER_FETCH_WAIT = 34275333;
    public static final int FBLITE_WATCH_PAGING_PROVIDER_FETCH_WAIT = 34275330;
    public static final short MODULE_ID = 523;
    public static final int PAGE_LOAD = 34275334;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "FBLITE_PAGING_PROVIDER_FBLITE_TIMELINE_PAGING_PROVIDER_FETCH_WAIT";
            case 2:
                return "FBLITE_PAGING_PROVIDER_FBLITE_WATCH_PAGING_PROVIDER_FETCH_WAIT";
            case 3:
                return "FBLITE_PAGING_PROVIDER_FBLITE_STORIES_TOP_TRAY_PAGING_PROVIDER_FETCH_WAIT";
            case 4:
                return "FBLITE_PAGING_PROVIDER_FBLITE_SEARCH_RESULT_PAGE_PAGING_PROVIDER_FETCH_WAIT";
            case 5:
                return "FBLITE_PAGING_PROVIDER_FBLITE_WATCHLIST_PAGING_PROVIDER_FETCH_WAIT";
            case 6:
                return "FBLITE_PAGING_PROVIDER_PAGE_LOAD";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
