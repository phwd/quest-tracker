package com.facebook.quicklog.identifiers;

public class IgThreadsAvvmuxerMonitor {
    public static final short MODULE_ID = 913;
    public static final int MUXING = 59834369;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "IG_THREADS_AVVMUXER_MONITOR_MUXING";
    }
}
