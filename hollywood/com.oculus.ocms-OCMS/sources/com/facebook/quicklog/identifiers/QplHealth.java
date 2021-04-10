package com.facebook.quicklog.identifiers;

public class QplHealth {
    public static final int API_CALL = 27787270;
    public static final int CRASH_RESILIENCE = 27787276;
    public static final int LOCKLESS_IS_MARKER_ON_ACCURACY = 27787269;
    public static final int LOCKLESS_MARKER_START = 27787266;
    public static final int LOCKLESS_QUEUE_SIZE = 27787265;
    public static final int LOCKLESS_STARTED_MARKERS_MAP_COLLISIONS = 27787273;
    public static final int LOSS_TRACKING_MARKER_RECEIVED = 27787272;
    public static final int LOSS_TRACKING_MARKER_STARTED = 27787271;
    public static final short MODULE_ID = 424;
    public static final int QPL_CONFIG_LOAD = 27799150;
    public static final int QPL_CONFIG_SAVE = 27791744;
    public static final int QPL_HEARTBEAT_HARDCODED_CONFIG = 27792138;
    public static final int QPL_HEARTBEAT_SEVER_CONFIG = 27803336;
    public static final int USAGE = 27791726;
    public static final int ZERO_SAMPLE_RATE_DUE_MISSING_CONFIG = 27787268;
    public static final int ZERO_SAMPLE_RATE_DUE_MISSING_EVENT_IN_CONFIG = 27787267;

    public static String getMarkerName(int i) {
        if (i == 12) {
            return "QPL_HEALTH_CRASH_RESILIENCE";
        }
        if (i == 4462) {
            return "QPL_HEALTH_USAGE";
        }
        if (i == 4480) {
            return "QPL_HEALTH_QPL_CONFIG_SAVE";
        }
        if (i == 4874) {
            return "QPL_HEALTH_QPL_HEARTBEAT_HARDCODED_CONFIG";
        }
        if (i == 11886) {
            return "QPL_HEALTH_QPL_CONFIG_LOAD";
        }
        if (i == 16072) {
            return "QPL_HEALTH_QPL_HEARTBEAT_SEVER_CONFIG";
        }
        switch (i) {
            case 1:
                return "QPL_HEALTH_LOCKLESS_QUEUE_SIZE";
            case 2:
                return "QPL_HEALTH_LOCKLESS_MARKER_START";
            case 3:
                return "QPL_HEALTH_ZERO_SAMPLE_RATE_DUE_MISSING_EVENT_IN_CONFIG";
            case 4:
                return "QPL_HEALTH_ZERO_SAMPLE_RATE_DUE_MISSING_CONFIG";
            case 5:
                return "QPL_HEALTH_LOCKLESS_IS_MARKER_ON_ACCURACY";
            case 6:
                return "Api call wall time";
            case 7:
                return "QPL_HEALTH_LOSS_TRACKING_MARKER_STARTED";
            case 8:
                return "QPL_HEALTH_LOSS_TRACKING_MARKER_RECEIVED";
            case 9:
                return "QPL_HEALTH_LOCKLESS_STARTED_MARKERS_MAP_COLLISIONS";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
