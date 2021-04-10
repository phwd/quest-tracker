package com.facebook.quicklog.identifiers;

public class SurveyEngPlatformRemix {
    public static final short MODULE_ID = 317;
    public static final int SURVEY_LOAD_TIME_ANDROID = 20774913;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "SURVEY_ENG_PLATFORM_REMIX_SURVEY_LOAD_TIME_ANDROID";
    }
}
