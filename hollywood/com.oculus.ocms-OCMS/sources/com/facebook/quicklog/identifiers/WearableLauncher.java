package com.facebook.quicklog.identifiers;

public class WearableLauncher {
    public static final int APP_START = 78580514;
    public static final int APP_TRAY_SCROLL_PERF = 78580320;
    public static final int COMPLICATION_DATABASE_LOAD_TIME = 78587245;
    public static final int COMPLICATION_UPDATE_REQUEST_LATENCY = 78586729;
    public static final short MODULE_ID = 1199;

    public static String getMarkerName(int i) {
        return i != 2656 ? i != 2850 ? i != 9065 ? i != 9581 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_LAUNCHER_COMPLICATION_DATABASE_LOAD_TIME" : "WEARABLE_LAUNCHER_COMPLICATION_UPDATE_REQUEST_LATENCY" : "WEARABLE_LAUNCHER_APP_START" : "WEARABLE_LAUNCHER_APP_TRAY_SCROLL_PERF";
    }
}
