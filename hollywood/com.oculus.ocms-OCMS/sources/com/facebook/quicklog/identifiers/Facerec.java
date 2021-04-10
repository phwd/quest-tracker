package com.facebook.quicklog.identifiers;

public class Facerec {
    public static final int BITMAP_DETECTION_PERF_MARKER_NAME = 3866627;
    public static final int FILE_DETECTION_PERF_MARKER_NAME = 3866626;
    public static final int FRAME_DETECTION_PERF_MARKER_NAME = 3866628;
    public static final short MODULE_ID = 59;
    public static final int SERVER_COMMUNICATION_MARKER = 3866625;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "FACEREC_FRAME_DETECTION_PERF_MARKER_NAME" : "FACEREC_BITMAP_DETECTION_PERF_MARKER_NAME" : "FACEREC_FILE_DETECTION_PERF_MARKER_NAME" : "FACEREC_SERVER_COMMUNICATION_MARKER";
    }
}
