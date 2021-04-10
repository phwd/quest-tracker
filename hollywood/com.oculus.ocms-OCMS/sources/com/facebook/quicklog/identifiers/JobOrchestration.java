package com.facebook.quicklog.identifiers;

public class JobOrchestration {
    public static final int FBINTENTSERVICE_RUN = 43712515;
    public static final int FBJOBINTENTSERVICE_RUN = 43712513;
    public static final int FBSERVICE_RUN = 43712514;
    public static final short MODULE_ID = 667;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "JOB_ORCHESTRATION_FBINTENTSERVICE_RUN" : "JOB_ORCHESTRATION_FBSERVICE_RUN" : "JOB_ORCHESTRATION_FBJOBINTENTSERVICE_RUN";
    }
}
