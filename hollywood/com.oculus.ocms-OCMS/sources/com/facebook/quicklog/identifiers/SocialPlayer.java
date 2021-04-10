package com.facebook.quicklog.identifiers;

public class SocialPlayer {
    public static final int CONTROLLER_INIT_ANDROID = 21561345;
    public static final int CONTROLLER_INIT_WITH_PARENT_LOADED_ANDROID = 21561348;
    public static final int CONTROLLER_RELOAD_ANDROID = 21561346;
    public static final int CONTROLLER_UNLOAD_ANDROID = 21561347;
    public static final short MODULE_ID = 329;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "SOCIAL_PLAYER_CONTROLLER_INIT_WITH_PARENT_LOADED_ANDROID" : "SOCIAL_PLAYER_CONTROLLER_UNLOAD_ANDROID" : "SOCIAL_PLAYER_CONTROLLER_RELOAD_ANDROID" : "SOCIAL_PLAYER_CONTROLLER_INIT_ANDROID";
    }
}
