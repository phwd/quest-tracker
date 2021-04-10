package com.facebook.quicklog.identifiers;

public class FriendsNearby {
    public static final int DASHBOARD_FETCH_DATA = 3145731;
    public static final int DASHBOARD_FETCH_DATA1 = 3145732;
    public static final int DASHBOARD_FETCH_DATA2 = 3145733;
    public static final int DASHBOARD_INIT = 3145735;
    public static final int DASHBOARD_REFRESH_LOCATION = 3145730;
    public static final int DASHBOARD_TTI = 3145736;
    public static final int DASHBOARD_VIEW_RENDER = 3145734;
    public static final short MODULE_ID = 48;
    public static final int NEARBY_FRIENDS_DASHBOARD_TTRC = 3145743;
    public static final int PING_DELETE = 3145737;
    public static final int PING_FETCH_EXIST = 3145739;
    public static final int PING_WRITE = 3145738;

    public static String getMarkerName(int i) {
        if (i == 15) {
            return "FRIENDS_NEARBY_NEARBY_FRIENDS_DASHBOARD_TTRC";
        }
        switch (i) {
            case 2:
                return "FRIENDS_NEARBY_DASHBOARD_REFRESH_LOCATION";
            case 3:
                return "FRIENDS_NEARBY_DASHBOARD_FETCH_DATA";
            case 4:
                return "FRIENDS_NEARBY_DASHBOARD_FETCH_DATA1";
            case 5:
                return "FRIENDS_NEARBY_DASHBOARD_FETCH_DATA2";
            case 6:
                return "FRIENDS_NEARBY_DASHBOARD_VIEW_RENDER";
            case 7:
                return "FRIENDS_NEARBY_DASHBOARD_INIT";
            case 8:
                return "FRIENDS_NEARBY_DASHBOARD_TTI";
            case 9:
                return "FRIENDS_NEARBY_PING_DELETE";
            case 10:
                return "FRIENDS_NEARBY_PING_WRITE";
            case 11:
                return "FRIENDS_NEARBY_PING_FETCH_EXIST";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
