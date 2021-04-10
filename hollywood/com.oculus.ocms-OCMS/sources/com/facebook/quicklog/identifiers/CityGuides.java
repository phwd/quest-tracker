package com.facebook.quicklog.identifiers;

public class CityGuides {
    public static final int CITY_GUIDES_BOOKMARK_TTI = 12124177;
    public static final int CITY_GUIDES_CATEGORY_NAVIGATION_TTI = 12124170;
    public static final int CITY_GUIDES_CLASSIC_UNIT_TTI = 12124164;
    public static final int CITY_GUIDES_EVENTS_UNIT_TTI = 12124167;
    public static final int CITY_GUIDES_FIND_WIFI_UNIT_TTI = 12124174;
    public static final int CITY_GUIDES_FRIENDS_WHO_LIVE_HERE_UNIT_TTI = 12124176;
    public static final int CITY_GUIDES_FRIENDS_WHO_VISITED_TTI = 12124173;
    public static final int CITY_GUIDES_LAUNCH_TTI = 12124161;
    public static final int CITY_GUIDES_LOCAL_UNIT_TTI = 12124163;
    public static final int CITY_GUIDES_NEARBY_FRIENDS_UNIT_TTI = 12124175;
    public static final int CITY_GUIDES_PIVOT_UNIT_TTI = 12124168;
    public static final int CITY_GUIDES_SAVED_TAB_TTI = 12124165;
    public static final int CITY_GUIDES_SOCIAL_UNIT_TTI = 12124162;
    public static final int CITY_GUIDES_TAB_SWITCH_TTI = 12124166;
    public static final short MODULE_ID = 185;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "CITY_GUIDES_CITY_GUIDES_LAUNCH_TTI";
            case 2:
                return "CITY_GUIDES_CITY_GUIDES_SOCIAL_UNIT_TTI";
            case 3:
                return "CITY_GUIDES_CITY_GUIDES_LOCAL_UNIT_TTI";
            case 4:
                return "CITY_GUIDES_CITY_GUIDES_CLASSIC_UNIT_TTI";
            case 5:
                return "CITY_GUIDES_CITY_GUIDES_SAVED_TAB_TTI";
            case 6:
                return "CITY_GUIDES_CITY_GUIDES_TAB_SWITCH_TTI";
            case 7:
                return "CITY_GUIDES_CITY_GUIDES_EVENTS_UNIT_TTI";
            case 8:
                return "CITY_GUIDES_CITY_GUIDES_PIVOT_UNIT_TTI";
            case 9:
            case 11:
            case 12:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 10:
                return "CITY_GUIDES_CITY_GUIDES_CATEGORY_NAVIGATION_TTI";
            case 13:
                return "CITY_GUIDES_CITY_GUIDES_FRIENDS_WHO_VISITED_TTI";
            case 14:
                return "CITY_GUIDES_CITY_GUIDES_FIND_WIFI_UNIT_TTI";
            case 15:
                return "CITY_GUIDES_CITY_GUIDES_NEARBY_FRIENDS_UNIT_TTI";
            case 16:
                return "CITY_GUIDES_CITY_GUIDES_FRIENDS_WHO_LIVE_HERE_UNIT_TTI";
            case 17:
                return "CITY_GUIDES_CITY_GUIDES_BOOKMARK_TTI";
        }
    }
}
