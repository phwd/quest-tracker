package com.facebook.quicklog.identifiers;

public class MessengerBroadcastFlow {
    public static final short MODULE_ID = 327;
    public static final int SHARE_TO_FACEBOOK_LOAD = 21430274;
    public static final int STARTUP_LOAD = 21430273;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_BROADCAST_FLOW_SHARE_TO_FACEBOOK_LOAD" : "MESSENGER_BROADCAST_FLOW_STARTUP_LOAD";
    }
}
