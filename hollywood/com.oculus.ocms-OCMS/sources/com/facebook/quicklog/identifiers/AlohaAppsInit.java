package com.facebook.quicklog.identifiers;

public class AlohaAppsInit {
    public static final int COLD_START = 36175873;
    public static final int HOT_START = 36175875;
    public static final short MODULE_ID = 552;
    public static final int TEST_MARKER = 36175876;
    public static final int WARM_START = 36175874;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "ALOHA_APPS_INIT_TEST_MARKER" : "ALOHA_APPS_INIT_HOT_START" : "ALOHA_APPS_INIT_WARM_START" : "ALOHA_APPS_INIT_COLD_START";
    }
}
