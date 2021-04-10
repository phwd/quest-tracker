package com.facebook.quicklog.identifiers;

public class Mobilelab {
    public static final int MICROBENCHMARK_RESULT = 22020104;
    public static final short MODULE_ID = 336;
    public static final int TRIMARK_FRAME = 22020100;
    public static final int TRIMARK_LOAD = 22020098;
    public static final int TRIMARK_RUN = 22020099;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? i != 4 ? i != 8 ? "UNDEFINED_QPL_EVENT" : "MOBILELAB_MICROBENCHMARK_RESULT" : "MOBILELAB_TRIMARK_FRAME" : "MOBILELAB_TRIMARK_RUN" : "MOBILELAB_TRIMARK_LOAD";
    }
}
