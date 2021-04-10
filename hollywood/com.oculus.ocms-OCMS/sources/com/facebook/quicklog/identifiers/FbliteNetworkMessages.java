package com.facebook.quicklog.identifiers;

public class FbliteNetworkMessages {
    public static final int FBLITE_NETWORK_PROFILING = 19595267;
    public static final int FBLITE_RTT = 19595268;
    public static final short MODULE_ID = 299;
    public static final int RECEIVE_SERVER_MESSAGE = 19595266;
    public static final int SEND_CLIENT_MESSAGE = 19595265;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "FBLITE_NETWORK_MESSAGES_FBLITE_RTT" : "FBLITE_NETWORK_MESSAGES_FBLITE_NETWORK_PROFILING" : "FBLITE_NETWORK_MESSAGES_RECEIVE_SERVER_MESSAGE" : "FBLITE_NETWORK_MESSAGES_SEND_CLIENT_MESSAGE";
    }
}
