package com.facebook.quicklog.identifiers;

public class UnifiedInbox {
    public static final int COMMS_CONFIG_FETCH = 36241425;
    public static final int FACEBOOK_COMMENTS_TAB_TTRC = 36241431;
    public static final int INBOX_TAB_TTRC = 36241428;
    public static final int INSTAGRAM_COMMENTS_TAB_TTRC = 36241432;
    public static final int INSTAGRAM_DIRECT_MESSAGE_LIST_FULL_FETCH = 36241410;
    public static final int INSTAGRAM_DIRECT_TAB_TTRC = 36241433;
    public static final int INSTAGRAM_DIRECT_THREAD_LIST_FULL_FETCH = 36241409;
    public static final int MESSENGER_MESSAGE_LIST_FULL_FETCH = 36241412;
    public static final int MESSENGER_TAB_TTRC = 36241430;
    public static final int MESSENGER_THREAD_LIST_FULL_FETCH = 36241411;
    public static final short MODULE_ID = 553;
    public static final int UNIFIED_COMMENTS_TAB_TTRC = 36241434;
    public static final int UNIFIED_THREADS_TAB_TTRC = 36241429;
    public static final int UNIFIED_THREAD_LIST = 36247808;
    public static final int UNIFIED_THREAD_LIST_FULL_FETCH = 36241426;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "UNIFIED_INBOX_INSTAGRAM_DIRECT_THREAD_LIST_FULL_FETCH";
        }
        if (i == 2) {
            return "UNIFIED_INBOX_INSTAGRAM_DIRECT_MESSAGE_LIST_FULL_FETCH";
        }
        if (i == 3) {
            return "UNIFIED_INBOX_MESSENGER_THREAD_LIST_FULL_FETCH";
        }
        if (i == 4) {
            return "UNIFIED_INBOX_MESSENGER_MESSAGE_LIST_FULL_FETCH";
        }
        if (i == 17) {
            return "UNIFIED_INBOX_COMMS_CONFIG_FETCH";
        }
        if (i == 18) {
            return "UNIFIED_INBOX_UNIFIED_THREAD_LIST_FULL_FETCH";
        }
        if (i == 6400) {
            return "UNIFIED_INBOX_UNIFIED_THREAD_LIST";
        }
        switch (i) {
            case 20:
                return "UNIFIED_INBOX_INBOX_TAB_TTRC";
            case 21:
                return "UNIFIED_INBOX_UNIFIED_THREADS_TAB_TTRC";
            case 22:
                return "UNIFIED_INBOX_MESSENGER_TAB_TTRC";
            case 23:
                return "UNIFIED_INBOX_FACEBOOK_COMMENTS_TAB_TTRC";
            case 24:
                return "UNIFIED_INBOX_INSTAGRAM_COMMENTS_TAB_TTRC";
            case 25:
                return "UNIFIED_INBOX_INSTAGRAM_DIRECT_TAB_TTRC";
            case 26:
                return "UNIFIED_INBOX_UNIFIED_COMMENTS_TAB_TTRC";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
