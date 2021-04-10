package com.facebook.quicklog.identifiers;

public class AdsManager {
    public static final int ADS_MANAGER_EDITING_TEST = 7745394;
    public static final int AMA_SHELL_TTI = 7733272;
    public static final int BRIDGE_START = 7733273;
    public static final int COLD_START_LOGIN = 7733252;
    public static final int COLD_START_MAIN = 7733251;
    public static final int CREATION_FLOW_ENTERING_TTI = 7733259;
    public static final int ENTER_CREATION_CONVERSIONS_TTI = 7733269;
    public static final int ENTER_CREATION_EVENT_RESPONSES_TTI = 7733266;
    public static final int ENTER_CREATION_LOCAL_AWARENESS_TTI = 7733262;
    public static final int ENTER_CREATION_MESSAGES_TTI = 7733267;
    public static final int ENTER_CREATION_PAGE_LIKES_TTI = 7733264;
    public static final int ENTER_CREATION_POST_ENGAGEMENT_TTI = 7733261;
    public static final int ENTER_CREATION_REACH_TTI = 7733268;
    public static final int ENTER_CREATION_VIDEO_VIEWS_TTI = 7733265;
    public static final int ENTER_CREATION_WEBSITE_CLICKS_TTI = 7733263;
    public static final short MODULE_ID = 118;
    public static final int PRODUCT_START = 7733274;

    public static String getMarkerName(int i) {
        if (i == 3) {
            return "ADS_MANAGER_COLD_START_MAIN";
        }
        if (i == 4) {
            return "ADS_MANAGER_COLD_START_LOGIN";
        }
        if (i == 11) {
            return "ADS_MANAGER_CREATION_FLOW_ENTERING_TTI";
        }
        if (i == 12146) {
            return "ADS_MANAGER_ADS_MANAGER_EDITING_TEST";
        }
        switch (i) {
            case 13:
                return "ADS_MANAGER_ENTER_CREATION_POST_ENGAGEMENT_TTI";
            case 14:
                return "ADS_MANAGER_ENTER_CREATION_LOCAL_AWARENESS_TTI";
            case 15:
                return "ADS_MANAGER_ENTER_CREATION_WEBSITE_CLICKS_TTI";
            case 16:
                return "ADS_MANAGER_ENTER_CREATION_PAGE_LIKES_TTI";
            case 17:
                return "ADS_MANAGER_ENTER_CREATION_VIDEO_VIEWS_TTI";
            case 18:
                return "ADS_MANAGER_ENTER_CREATION_EVENT_RESPONSES_TTI";
            case 19:
                return "ADS_MANAGER_ENTER_CREATION_MESSAGES_TTI";
            case 20:
                return "ADS_MANAGER_ENTER_CREATION_REACH_TTI";
            case 21:
                return "ADS_MANAGER_ENTER_CREATION_CONVERSIONS_TTI";
            default:
                switch (i) {
                    case 24:
                        return "ADS_MANAGER_AMA_SHELL_TTI";
                    case 25:
                        return "ADS_MANAGER_BRIDGE_START";
                    case 26:
                        return "ADS_MANAGER_PRODUCT_START";
                    default:
                        return "UNDEFINED_QPL_EVENT";
                }
        }
    }
}
