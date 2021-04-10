package com.facebook.quicklog.identifiers;

public class Papaya {
    public static final int EXECUTION = 188224997;
    public static final short MODULE_ID = 2872;
    public static final int SUBMISSION = 188226264;

    public static String getMarkerName(int i) {
        return i != 5605 ? i != 6872 ? "UNDEFINED_QPL_EVENT" : "PAPAYA_SUBMISSION" : "PAPAYA_EXECUTION";
    }
}
