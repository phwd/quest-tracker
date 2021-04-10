package com.facebook.quicklog.identifiers;

public class ArCloudServices {
    public static final int EFFECT_FETCH = 38600708;
    public static final int FETCH = 38600714;
    public static final int FRAME_LOAD = 38600711;
    public static final int FRAME_RENDER = 38600710;
    public static final int MODEL_FETCH = 38600709;
    public static final short MODULE_ID = 589;

    public static String getMarkerName(int i) {
        return i != 4 ? i != 5 ? i != 6 ? i != 7 ? i != 10 ? "UNDEFINED_QPL_EVENT" : "AR_CLOUD_SERVICES_FETCH" : "AR_CLOUD_SERVICES_FRAME_LOAD" : "AR_CLOUD_SERVICES_FRAME_RENDER" : "AR_CLOUD_SERVICES_MODEL_FETCH" : "AR_CLOUD_SERVICES_EFFECT_FETCH";
    }
}
