package com.facebook.quicklog.identifiers;

public class Snacks {
    public static final int DIRECT_INBOX_LOAD_TTI = 12320774;
    public static final int DIRECT_REPLY_THREAD_LOAD_TTI = 12320773;
    public static final short MODULE_ID = 188;
    public static final int SHARESHEET_LOADING_TIME = 12320769;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 5 ? i != 6 ? "UNDEFINED_QPL_EVENT" : "SNACKS_DIRECT_INBOX_LOAD_TTI" : "SNACKS_DIRECT_REPLY_THREAD_LOAD_TTI" : "SNACKS_SHARESHEET_LOADING_TIME";
    }
}
