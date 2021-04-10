package com.facebook.quicklog.identifiers;

public class Fox {
    public static final int ACCOUNT_CREATE = 19005446;
    public static final int AUDIENCE_REQUEST_CREATE = 19005451;
    public static final int CS_PEOPLE_TAB_TTI = 19005456;
    public static final int CS_PROFILE_TAB_TTI = 19005457;
    public static final int FEED = 19005441;
    public static final int FEED_GRAPHQL_CACHE = 19005445;
    public static final int FEED_GRAPHQL_LOAD = 19005443;
    public static final int FEED_GRAPHQL_LOAD_PROCESS = 19005454;
    public static final int FEED_GRAPHQL_LOAD_UI = 19005455;
    public static final int FEED_GRAPHQL_NETWORK = 19005444;
    public static final int FEED_GRAPHQL_STORY = 19005442;
    public static final int FETCH_SELECTED_AUDIENCE = 19005452;
    public static final short MODULE_ID = 290;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "FOX_FEED";
            case 2:
                return "FOX_FEED_GRAPHQL_STORY";
            case 3:
                return "FOX_FEED_GRAPHQL_LOAD";
            case 4:
                return "FOX_FEED_GRAPHQL_NETWORK";
            case 5:
                return "FOX_FEED_GRAPHQL_CACHE";
            case 6:
                return "FOX_ACCOUNT_CREATE";
            case 7:
            case 8:
            case 9:
            case 10:
            case 13:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 11:
                return "FOX_AUDIENCE_REQUEST_CREATE";
            case 12:
                return "FOX_FETCH_SELECTED_AUDIENCE";
            case 14:
                return "FOX_FEED_GRAPHQL_LOAD_PROCESS";
            case 15:
                return "FOX_FEED_GRAPHQL_LOAD_UI";
            case 16:
                return "FOX_CS_PEOPLE_TAB_TTI";
            case 17:
                return "FOX_CS_PROFILE_TAB_TTI";
        }
    }
}
