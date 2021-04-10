package com.facebook.quicklog.identifiers;

public class Direct {
    public static final int DIRECT_FIRST_THREAD_LOAD_TTI = 13303810;
    public static final int DIRECT_INBOX_LOAD_TTI = 13303809;
    public static final int DIRECT_INBOX_REFRESH_TTI = 13303812;
    public static final int DIRECT_NEXT_THREAD_TTI = 13303811;
    public static final short MODULE_ID = 203;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "DIRECT_DIRECT_INBOX_REFRESH_TTI" : "DIRECT_DIRECT_NEXT_THREAD_TTI" : "DIRECT_DIRECT_FIRST_THREAD_LOAD_TTI" : "DIRECT_DIRECT_INBOX_LOAD_TTI";
    }
}
