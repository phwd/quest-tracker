package com.facebook.quicklog.identifiers;

public class ProfileAboutRn {
    public static final short MODULE_ID = 238;
    public static final int PROFILE_ABOUT_APPS_TTI = 15597572;
    public static final int PROFILE_ABOUT_FIELDS_TTI = 15597571;
    public static final int PROFILE_ABOUT_INITIAL_LOAD_TTRC = 15597575;
    public static final int PROFILE_ABOUT_INITIAL_LOAD_TTRC_ANDROID = 15597576;
    public static final int PROFILE_ABOUT_OVERVIEW_TTI = 15597569;
    public static final int PROFILE_ABOUT_PROTILES_TTI = 15597570;
    public static final int PROFILE_ABOUT_TTI = 15597573;
    public static final int PROFILE_SELF_ABOUT_TTI = 15597574;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "PROFILE_ABOUT_RN_PROFILE_ABOUT_OVERVIEW_TTI";
            case 2:
                return "PROFILE_ABOUT_RN_PROFILE_ABOUT_PROTILES_TTI";
            case 3:
                return "PROFILE_ABOUT_RN_PROFILE_ABOUT_FIELDS_TTI";
            case 4:
                return "PROFILE_ABOUT_RN_PROFILE_ABOUT_APPS_TTI";
            case 5:
                return "PROFILE_ABOUT_RN_PROFILE_ABOUT_TTI";
            case 6:
                return "PROFILE_ABOUT_RN_PROFILE_SELF_ABOUT_TTI";
            case 7:
                return "PROFILE_ABOUT_RN_PROFILE_ABOUT_INITIAL_LOAD_TTRC";
            case 8:
                return "PROFILE_ABOUT_RN_PROFILE_ABOUT_INITIAL_LOAD_TTRC_ANDROID";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
