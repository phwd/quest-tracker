package com.facebook.quicklog.identifiers;

public class SmartCapture {
    public static final short MODULE_ID = 517;
    public static final int SCP_ID_DETECTOR = 33882113;
    public static final int SCP_UPLOAD = 33882114;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "SMART_CAPTURE_SCP_UPLOAD" : "SMART_CAPTURE_SCP_ID_DETECTOR";
    }
}
