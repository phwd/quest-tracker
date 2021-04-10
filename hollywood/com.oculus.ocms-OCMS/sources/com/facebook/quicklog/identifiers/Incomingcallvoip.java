package com.facebook.quicklog.identifiers;

public class Incomingcallvoip {
    public static final int INCOMINGCALL_MAKER = 4587521;
    public static final short MODULE_ID = 70;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "INCOMINGCALLVOIP_INCOMINGCALL_MAKER";
    }
}
