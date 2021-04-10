package com.facebook.quicklog.identifiers;

public class Loyalty {
    public static final short MODULE_ID = 622;
    public static final int REWARD_HOME_TTRC = 40763393;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "LOYALTY_REWARD_HOME_TTRC";
    }
}
