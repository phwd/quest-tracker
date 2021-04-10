package com.facebook.quicklog.identifiers;

public class MessengerWellbeing {
    public static final short MODULE_ID = 2906;
    public static final int USER_BLOCK_TRACING = 190452763;
    public static final int USER_UNBLOCK_TRACING = 190458640;

    public static String getMarkerName(int i) {
        return i != 5147 ? i != 11024 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_WELLBEING_USER_UNBLOCK_TRACING" : "MESSENGER_WELLBEING_USER_BLOCK_TRACING";
    }
}
