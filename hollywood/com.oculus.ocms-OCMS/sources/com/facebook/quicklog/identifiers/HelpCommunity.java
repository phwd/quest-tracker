package com.facebook.quicklog.identifiers;

public class HelpCommunity {
    public static final int HOME_SCREEN_TTI = 52101121;
    public static final short MODULE_ID = 795;
    public static final int QUESTION_TTI = 52101122;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "HELP_COMMUNITY_QUESTION_TTI" : "HELP_COMMUNITY_HOME_SCREEN_TTI";
    }
}
