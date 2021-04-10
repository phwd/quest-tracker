package com.facebook.quicklog.identifiers;

public class IgClientPerf {
    public static final int APP_START = 15335435;
    public static final int CRONET_USAGE = 15335447;
    public static final int DOWNLOAD_MODULES = 15335438;
    public static final int IG_BACKGROUND_TASKS = 15349278;
    public static final int IG_IPC_CALLS = 15343180;
    public static final int IMAGE_DOWNLOAD = 15335433;
    public static final int LOAD_MODULE = 15335444;
    public static final short MODULE_ID = 234;
    public static final int NOTIFICATION_PREFETCH = 15335434;

    public static String getMarkerName(int i) {
        if (i == 14) {
            return "IG_CLIENT_PERF_DOWNLOAD_MODULES";
        }
        if (i == 20) {
            return "IG_CLIENT_PERF_LOAD_MODULE";
        }
        if (i == 23) {
            return "IG_CLIENT_PERF_CRONET_USAGE";
        }
        if (i == 7756) {
            return "IG_CLIENT_PERF_IG_IPC_CALLS";
        }
        if (i == 13854) {
            return "IG_CLIENT_PERF_IG_BACKGROUND_TASKS";
        }
        switch (i) {
            case 9:
                return "IG_CLIENT_PERF_IMAGE_DOWNLOAD";
            case 10:
                return "IG_CLIENT_PERF_NOTIFICATION_PREFETCH";
            case 11:
                return "IG_CLIENT_PERF_APP_START";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
