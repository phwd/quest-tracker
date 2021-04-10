package com.facebook.quicklog.identifiers;

public class BackgroundTaskRunner {
    public static final short MODULE_ID = 39;
    public static final int RUN = 2555905;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "BACKGROUND_TASK_RUNNER_RUN";
    }
}
