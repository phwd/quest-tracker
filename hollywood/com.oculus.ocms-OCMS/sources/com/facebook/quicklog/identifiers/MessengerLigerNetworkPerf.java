package com.facebook.quicklog.identifiers;

public class MessengerLigerNetworkPerf {
    public static final int MESSENGER_MSG_SENT_EVENT = 17170434;
    public static final int MESSENGER_PHOTO_UPLOAD_EVENT = 17170433;
    public static final short MODULE_ID = 262;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_LIGER_NETWORK_PERF_MESSENGER_MSG_SENT_EVENT" : "MESSENGER_LIGER_NETWORK_PERF_MESSENGER_PHOTO_UPLOAD_EVENT";
    }
}
