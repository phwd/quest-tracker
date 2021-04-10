package com.facebook.quicklog.identifiers;

public class MessengerCowatch {
    public static final int AVD_TABS_TTI_ANDROID = 38207491;
    public static final int AVD_TAB_CONTENT_ANDROID = 38207492;
    public static final int AVD_TAB_CONTENT_IG_ANDROID = 38211373;
    public static final int AVD_TAB_CONTENT_LOAD_TO_RENDER_ANDROID = 38220280;
    public static final short MODULE_ID = 583;
    public static final int PLAYER_PLAY_TTI_ANDROID = 38215573;

    public static String getMarkerName(int i) {
        return i != 3 ? i != 4 ? i != 3885 ? i != 8085 ? i != 12792 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_COWATCH_AVD_TAB_CONTENT_LOAD_TO_RENDER_ANDROID" : "MESSENGER_COWATCH_PLAYER_PLAY_TTI_ANDROID" : "MESSENGER_COWATCH_AVD_TAB_CONTENT_IG_ANDROID" : "MESSENGER_COWATCH_AVD_TAB_CONTENT_ANDROID" : "MESSENGER_COWATCH_AVD_TABS_TTI_ANDROID";
    }
}
