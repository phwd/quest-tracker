package com.facebook.quicklog.identifiers;

public class ColdStart {
    public static final int APPLICATION_CREATE = 7340053;
    public static final int BASE_CONTEXT_ATTACHED = 7340041;
    public static final int CREATE_DELEGATE = 7340039;
    public static final int ENSURE_DEXS_LOADED = 7340042;
    public static final int FBAPPIMPL_ON_CREATE = 7340038;
    public static final short MODULE_ID = 112;

    public static String getMarkerName(int i) {
        return i != 6 ? i != 7 ? i != 9 ? i != 10 ? i != 21 ? "UNDEFINED_QPL_EVENT" : "COLD_START_APPLICATION_CREATE" : "COLD_START_ENSURE_DEXS_LOADED" : "COLD_START_BASE_CONTEXT_ATTACHED" : "COLD_START_CREATE_DELEGATE" : "COLD_START_FBAPPIMPL_ON_CREATE";
    }
}
