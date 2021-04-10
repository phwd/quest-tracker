package com.facebook.quicklog.identifiers;

public class CsrPlayground {
    public static final short MODULE_ID = 15490;
    public static final int PLAYGROUND_CSR_TAIL_LOAD = 1015152642;
    public static final int PLAYGROUND_TTRC = 1015152641;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "CSR_PLAYGROUND_PLAYGROUND_CSR_TAIL_LOAD" : "CSR_PLAYGROUND_PLAYGROUND_TTRC";
    }
}
