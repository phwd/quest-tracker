package com.facebook.quicklog.identifiers;

public class RpSignalingReliability {
    public static final short MODULE_ID = 5382;
    public static final int RECEIVE_MESSAGE = 352722515;
    public static final int SEND_MESSAGE = 352726604;

    public static String getMarkerName(int i) {
        return i != 7763 ? i != 11852 ? "UNDEFINED_QPL_EVENT" : "RP_SIGNALING_RELIABILITY_SEND_MESSAGE" : "RP_SIGNALING_RELIABILITY_RECEIVE_MESSAGE";
    }
}
