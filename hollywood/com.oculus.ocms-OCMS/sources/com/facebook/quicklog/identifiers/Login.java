package com.facebook.quicklog.identifiers;

public class Login {
    public static final int ACCOUNT_SWITCH = 2293791;
    public static final int APP_CREATE_TO_LOGIN_ACTIVITY_CREATE = 2293782;
    public static final int ASSISTIVE_ID = 2308284;
    public static final int AUTHENTICATION = 2293780;
    public static final int COLD_START_NUX_TTI = 2293774;
    public static final int END_TO_END = 2293761;
    public static final int FETCH_LOGIN_COMPONENTS = 2293770;
    public static final int FETCH_LOGIN_DATA_TTI = 2293764;
    public static final int FETCH_PERSISTENT_COMPONENTS = 2293777;
    public static final int HEADER_FALLBACK = 2306419;
    public static final int INTERSTITIAL_PREPARATION = 2293781;
    public static final int LIAS_ANDROID = 2293788;
    public static final int LOAD_ACTIVITY_AFTER_FETCH_IG_LINKED_FBID = 2293784;
    public static final int LOAD_PARALLEL_COMPONENTS = 2293787;
    public static final int LOAD_PERSISTENT_COMPONENTS = 2293776;
    public static final int LOGIN_LOCALES_TTL = 2293790;
    public static final int LOGIN_TO_BEFORE_FEED_FETCH = 2293763;
    public static final int LOGIN_TO_FEED_STORY = 2293779;
    public static final int LOGIN_TTI_ANDROID = 2293785;
    public static final int LOGOUT = 2293778;
    public static final short MODULE_ID = 35;
    public static final int SILENT_LOGIN = 2293773;
    public static final int SMARTLOCK_SAVE = 2296873;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "NewLogin";
        }
        if (i == 10) {
            return "LOGIN_FETCH_LOGIN_COMPONENTS";
        }
        if (i == 3113) {
            return "LOGIN_SMARTLOCK_SAVE";
        }
        if (i == 12659) {
            return "LOGIN_HEADER_FALLBACK";
        }
        if (i == 14524) {
            return "LOGIN_ASSISTIVE_ID";
        }
        if (i == 3) {
            return "LoginToBeforeFeedFetch";
        }
        if (i == 4) {
            return "LOGIN_FETCH_LOGIN_DATA_TTI";
        }
        if (i == 13) {
            return "LOGIN_SILENT_LOGIN";
        }
        if (i == 14) {
            return "ColdStartNuxTTI";
        }
        if (i == 24) {
            return "LOGIN_LOAD_ACTIVITY_AFTER_FETCH_IG_LINKED_FBID";
        }
        if (i == 25) {
            return "Fb4aLoginTTI";
        }
        if (i == 27) {
            return "LOGIN_LOAD_PARALLEL_COMPONENTS";
        }
        if (i == 28) {
            return "LOGIN_LIAS_ANDROID";
        }
        if (i == 30) {
            return "Fb4aLocalesTTL";
        }
        if (i == 31) {
            return "LOGIN_ACCOUNT_SWITCH";
        }
        switch (i) {
            case 16:
                return "LOGIN_LOAD_PERSISTENT_COMPONENTS";
            case 17:
                return "LOGIN_FETCH_PERSISTENT_COMPONENTS";
            case 18:
                return "LOGIN_LOGOUT";
            case 19:
                return "LoginToFeedStory";
            case 20:
                return "Authentication";
            case 21:
                return "InterstitialPreparation";
            case 22:
                return "AppCreateToLoginActivityCreate";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
