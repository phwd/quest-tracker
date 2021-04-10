package com.facebook.quicklog.identifiers;

public class OculusTwilight {
    public static final int HOME_TTI = 32112642;
    public static final short MODULE_ID = 490;
    public static final int OCULUS_TWILIGHT_3DS2_FUNNEL = 32122339;
    public static final int OCULUS_TWILIGHT_3DS2_FUNNEL_TEST = 32117126;
    public static final int OCULUS_TWILIGHT_ACCOUNT_CREATION_NUX_FUNNEL = 32125923;
    public static final int OCULUS_TWILIGHT_ACCOUNT_CREATION_NUX_FUNNEL_TEST = 32115163;
    public static final int OCULUS_TWILIGHT_CASTING_CHROMECAST_FUNNEL = 32124508;
    public static final int OCULUS_TWILIGHT_CASTING_CHROMECAST_FUNNEL_TEST = 32122828;
    public static final int OCULUS_TWILIGHT_CASTING_FUNNEL = 32124971;
    public static final int OCULUS_TWILIGHT_CASTING_FUNNEL_TEST = 32127793;
    public static final int OCULUS_TWILIGHT_DEVICE_SETUP_FUNNEL = 32124860;
    public static final int OCULUS_TWILIGHT_DEVICE_SETUP_FUNNEL_TEST = 32114285;
    public static final int OCULUS_TWILIGHT_ENTITLEMENT_FUNNEL = 32114510;
    public static final int OCULUS_TWILIGHT_ENTITLEMENT_FUNNEL_TEST = 32117663;
    public static final int OCULUS_TWILIGHT_GIFTING_FUNNEL = 32118506;
    public static final int OCULUS_TWILIGHT_GIFTING_FUNNEL_TEST = 32114569;
    public static final int OCULUS_TWILIGHT_LOGIN_FUNNEL = 32117114;
    public static final int OCULUS_TWILIGHT_LOGIN_FUNNEL_TEST = 32117209;
    public static final int PDP_TTI = 32112647;
    public static final int SEARCH_NULL_STATE_TTI = 32124965;
    public static final int SEARCH_RESULT_TTI = 32124101;
    public static final int STORE_TTI = 32112643;

    public static String getMarkerName(int i) {
        if (i == 2) {
            return "OCULUS_TWILIGHT_HOME_TTI";
        }
        if (i == 3) {
            return "OCULUS_TWILIGHT_STORE_TTI";
        }
        switch (i) {
            case 7:
                return "OCULUS_TWILIGHT_PDP_TTI";
            case 1645:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_DEVICE_SETUP_FUNNEL_TEST";
            case 1870:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_ENTITLEMENT_FUNNEL";
            case 1929:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_GIFTING_FUNNEL_TEST";
            case 2523:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_ACCOUNT_CREATION_NUX_FUNNEL_TEST";
            case 4474:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_LOGIN_FUNNEL";
            case 4486:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_3DS2_FUNNEL_TEST";
            case 4569:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_LOGIN_FUNNEL_TEST";
            case 5023:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_ENTITLEMENT_FUNNEL_TEST";
            case 5866:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_GIFTING_FUNNEL";
            case 9699:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_3DS2_FUNNEL";
            case 10188:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_CASTING_CHROMECAST_FUNNEL_TEST";
            case 11461:
                return "OCULUS_TWILIGHT_SEARCH_RESULT_TTI";
            case 11868:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_CASTING_CHROMECAST_FUNNEL";
            case 12220:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_DEVICE_SETUP_FUNNEL";
            case 12325:
                return "OCULUS_TWILIGHT_SEARCH_NULL_STATE_TTI";
            case 12331:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_CASTING_FUNNEL";
            case 13283:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_ACCOUNT_CREATION_NUX_FUNNEL";
            case 15153:
                return "OCULUS_TWILIGHT_OCULUS_TWILIGHT_CASTING_FUNNEL_TEST";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
