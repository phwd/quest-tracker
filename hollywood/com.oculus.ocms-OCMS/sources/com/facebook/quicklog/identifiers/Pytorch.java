package com.facebook.quicklog.identifiers;

public class Pytorch {
    public static final int MOBILE_MODULE_LOAD_STATS = 46016532;
    public static final int MOBILE_MODULE_STATS = 46006274;
    public static final int MOBILE_OPERATOR_STATS = 46006273;
    public static final short MODULE_ID = 702;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 10260 ? "UNDEFINED_QPL_EVENT" : "PYTORCH_MOBILE_MODULE_LOAD_STATS" : "PYTORCH_MOBILE_MODULE_STATS" : "PYTORCH_MOBILE_OPERATOR_STATS";
    }
}
