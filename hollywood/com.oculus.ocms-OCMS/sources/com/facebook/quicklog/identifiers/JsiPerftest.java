package com.facebook.quicklog.identifiers;

public class JsiPerftest {
    public static final int MEMORY = 19398657;
    public static final int MEMORY_DESTROYED = 19398659;
    public static final int MEMORY_UNLOADED = 19398658;
    public static final short MODULE_ID = 296;
    public static final int RECEIVER_INITIALIASED = 19398660;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "JSI_PERFTEST_RECEIVER_INITIALIASED" : "JSI_PERFTEST_MEMORY_DESTROYED" : "JSI_PERFTEST_MEMORY_UNLOADED" : "JSI_PERFTEST_MEMORY";
    }
}
