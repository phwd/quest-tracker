package com.facebook.quicklog.identifiers;

public class Permanet {
    public static final short MODULE_ID = 606;
    public static final int ONBOARDING_COMPLETE = 39720490;
    public static final int SPD_ONBOARDING_COMPLETE = 39722231;

    public static String getMarkerName(int i) {
        return i != 5674 ? i != 7415 ? "UNDEFINED_QPL_EVENT" : "PERMANET_SPD_ONBOARDING_COMPLETE" : "PERMANET_ONBOARDING_COMPLETE";
    }
}
