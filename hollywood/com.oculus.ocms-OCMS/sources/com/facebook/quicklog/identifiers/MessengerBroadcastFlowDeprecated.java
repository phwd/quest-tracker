package com.facebook.quicklog.identifiers;

public class MessengerBroadcastFlowDeprecated {
    public static final short MODULE_ID = 331;
    public static final int STARTUP_LOAD = 21692417;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_BROADCAST_FLOW_DEPRECATED_STARTUP_LOAD";
    }
}
