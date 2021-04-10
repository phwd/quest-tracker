package com.facebook.quicklog.identifiers;

public class Bigfoot {
    public static final int INIT_PROVIDERS = 44236801;
    public static final int MEASURE = 44236804;
    public static final short MODULE_ID = 675;
    public static final int REPORT_DATA = 44236802;
    public static final int REQUEST_MEASUREMENT = 44236803;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "BIGFOOT_MEASURE" : "BIGFOOT_REQUEST_MEASUREMENT" : "BIGFOOT_REPORT_DATA" : "BIGFOOT_INIT_PROVIDERS";
    }
}
