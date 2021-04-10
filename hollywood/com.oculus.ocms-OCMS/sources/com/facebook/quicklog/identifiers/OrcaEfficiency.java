package com.facebook.quicklog.identifiers;

public class OrcaEfficiency {
    public static final int MESSENGER_PERF = 823857931;
    public static final short MODULE_ID = 12571;

    public static String getMarkerName(int i) {
        return i != 4875 ? "UNDEFINED_QPL_EVENT" : "ORCA_EFFICIENCY_MESSENGER_PERF";
    }
}
