package com.facebook.quicklog.identifiers;

public class FbliteTestingInternalOnly {
    public static final int FBLITE_QPL_101 = 48955393;
    public static final short MODULE_ID = 747;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FBLITE_TESTING_INTERNAL_ONLY_FBLITE_QPL_101";
    }
}
