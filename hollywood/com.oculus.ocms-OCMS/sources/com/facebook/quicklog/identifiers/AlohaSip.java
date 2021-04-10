package com.facebook.quicklog.identifiers;

public class AlohaSip {
    public static final int CALL_CONNECT = 659103310;
    public static final int DEVICE_PROVISION = 659107431;
    public static final short MODULE_ID = 10057;

    public static String getMarkerName(int i) {
        return i != 7758 ? i != 11879 ? "UNDEFINED_QPL_EVENT" : "ALOHA_SIP_DEVICE_PROVISION" : "ALOHA_SIP_CALL_CONNECT";
    }
}
