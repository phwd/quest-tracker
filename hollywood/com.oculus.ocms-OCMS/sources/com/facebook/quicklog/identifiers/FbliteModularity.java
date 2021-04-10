package com.facebook.quicklog.identifiers;

public class FbliteModularity {
    public static final int FBLITE_API_EXECUTE_BATCH = 51380225;
    public static final short MODULE_ID = 784;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FBLITE_MODULARITY_FBLITE_API_EXECUTE_BATCH";
    }
}
