package com.facebook.quicklog.identifiers;

public class GroupsSimpleFeed {
    public static final int FETCH_TAIL = 18153478;
    public static final int LOAD_START = 18153473;
    public static final int LOAD_SUCCESS = 18153474;
    public static final short MODULE_ID = 277;
    public static final int ON_CACHE_HIT = 18153476;
    public static final int ON_TTI = 18153475;
    public static final int ON_USER_CANCEL = 18153477;
    public static final int PULL_TO_REFRESH = 18153479;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "GROUPS_SIMPLE_FEED_LOAD_START";
            case 2:
                return "GROUPS_SIMPLE_FEED_LOAD_SUCCESS";
            case 3:
                return "GROUPS_SIMPLE_FEED_ON_TTI";
            case 4:
                return "GROUPS_SIMPLE_FEED_ON_CACHE_HIT";
            case 5:
                return "GROUPS_SIMPLE_FEED_ON_USER_CANCEL";
            case 6:
                return "GROUPS_SIMPLE_FEED_FETCH_TAIL";
            case 7:
                return "GROUPS_SIMPLE_FEED_PULL_TO_REFRESH";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
