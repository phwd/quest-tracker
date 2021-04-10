package com.facebook.quicklog.identifiers;

public class FbAppMessaging {
    public static final int ANDROID_ACTIVE_NOW_TTRC = 35913734;
    public static final int FB4A_INBOX_THREAD_LIST = 35913733;
    public static final int LIGHTWEIGHT_MESSAGING = 35913729;
    public static final int MIB_SEND_MESSAGE = 35913732;
    public static final int MIB_SHARESHEET_TTRC = 35913736;
    public static final short MODULE_ID = 548;
    public static final int NEWSFEED_BROADCAST_SHEET = 35913735;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "FB_APP_MESSAGING_LIGHTWEIGHT_MESSAGING";
        }
        switch (i) {
            case 4:
                return "FB_APP_MESSAGING_MIB_SEND_MESSAGE";
            case 5:
                return "FB_APP_MESSAGING_FB4A_INBOX_THREAD_LIST";
            case 6:
                return "FB_APP_MESSAGING_ANDROID_ACTIVE_NOW_TTRC";
            case 7:
                return "FB_APP_MESSAGING_NEWSFEED_BROADCAST_SHEET";
            case 8:
                return "FB_APP_MESSAGING_MIB_SHARESHEET_TTRC";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
