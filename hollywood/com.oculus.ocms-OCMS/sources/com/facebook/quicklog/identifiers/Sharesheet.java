package com.facebook.quicklog.identifiers;

public class Sharesheet {
    public static final int CREATE_I18N_MODULE_CONSTANTS = 13369350;
    public static final short MODULE_ID = 204;
    public static final int SHARESHEET_DIRECT_SEND_TIME = 13369347;
    public static final int SHARESHEET_LOAD_TTI = 13369345;
    public static final int SHARESHEET_LOAD_TTRC = 13369352;
    public static final int SHARESHEET_NEWFEED_POST_TIME = 13369346;
    public static final int SHARESHEET_STORY_POST_TIME = 13369348;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 6 ? i != 8 ? "UNDEFINED_QPL_EVENT" : "SHARESHEET_SHARESHEET_LOAD_TTRC" : "SHARESHEET_CREATE_I18N_MODULE_CONSTANTS" : "SHARESHEET_SHARESHEET_STORY_POST_TIME" : "SHARESHEET_SHARESHEET_DIRECT_SEND_TIME" : "SHARESHEET_SHARESHEET_NEWFEED_POST_TIME" : "SHARESHEET_SHARESHEET_LOAD_TTI";
    }
}
