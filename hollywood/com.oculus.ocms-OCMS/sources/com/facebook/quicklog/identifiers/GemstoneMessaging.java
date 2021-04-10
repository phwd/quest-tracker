package com.facebook.quicklog.identifiers;

public class GemstoneMessaging {
    public static final int GEMSTONE_MESSAGING_INBOX_TTRC_ANDROID = 32178179;
    public static final int GEMSTONE_MESSAGING_THREAD_TTRC_ANDROID = 32178180;
    public static final short MODULE_ID = 491;

    public static String getMarkerName(int i) {
        return i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "GEMSTONE_MESSAGING_GEMSTONE_MESSAGING_THREAD_TTRC_ANDROID" : "GEMSTONE_MESSAGING_GEMSTONE_MESSAGING_INBOX_TTRC_ANDROID";
    }
}
