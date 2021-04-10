package com.facebook.quicklog.identifiers;

public class MessengerEndToEndPre {
    public static final int MESSAGE_SEND_SUCCESS = 922161213;
    public static final short MODULE_ID = 14071;

    public static String getMarkerName(int i) {
        return i != 4157 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_END_TO_END_PRE_MESSAGE_SEND_SUCCESS";
    }
}
