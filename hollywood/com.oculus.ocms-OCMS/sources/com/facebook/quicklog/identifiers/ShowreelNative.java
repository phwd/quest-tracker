package com.facebook.quicklog.identifiers;

public class ShowreelNative {
    public static final int FB_FEED_SN_COMPONENT_USER_FLOW = 51517377;
    public static final int FB_SN_ANIM_PLAYER_PLAYBACK = 51516808;
    public static final int INSTANCE_ERRORS = 51527556;
    public static final int INSTANCE_PLAYBACK = 51511299;
    public static final int INSTANCE_TTR = 51511298;
    public static final short MODULE_ID = 786;
    public static final int TEST_EVENT_1 = 51511297;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 5512 ? i != 6081 ? i != 16260 ? "UNDEFINED_QPL_EVENT" : "SHOWREEL_NATIVE_INSTANCE_ERRORS" : "SHOWREEL_NATIVE_FB_FEED_SN_COMPONENT_USER_FLOW" : "SHOWREEL_NATIVE_FB_SN_ANIM_PLAYER_PLAYBACK" : "SHOWREEL_NATIVE_INSTANCE_PLAYBACK" : "SHOWREEL_NATIVE_INSTANCE_TTR" : "SHOWREEL_NATIVE_TEST_EVENT_1";
    }
}
