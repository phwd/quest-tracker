package com.facebook.quicklog.identifiers;

public class Privacy {
    public static final int ACTIVITY_LOG_LOADING_TIME = 1441799;
    public static final int AUDIENCE_SELECTOR_LAUNCH = 1441793;
    public static final int CHECKUP_APPS_STEP_TTI = 1441796;
    public static final int CHECKUP_COMPOSER_STEP_TTI = 1441794;
    public static final int CHECKUP_INTRO_TTI = 1441797;
    public static final int CHECKUP_PROFILE_STEP_TTI = 1441795;
    public static final int EDIT_STORY_PRIVACY_ROUND_TRIP = 1441798;
    public static final short MODULE_ID = 22;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "PRIVACY_AUDIENCE_SELECTOR_LAUNCH";
            case 2:
                return "PRIVACY_CHECKUP_COMPOSER_STEP_TTI";
            case 3:
                return "PRIVACY_CHECKUP_PROFILE_STEP_TTI";
            case 4:
                return "PRIVACY_CHECKUP_APPS_STEP_TTI";
            case 5:
                return "PRIVACY_CHECKUP_INTRO_TTI";
            case 6:
                return "PRIVACY_EDIT_STORY_PRIVACY_ROUND_TRIP";
            case 7:
                return "PRIVACY_ACTIVITY_LOG_LOADING_TIME";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
