package com.facebook.quicklog.identifiers;

public class PowerData {
    public static final int CURRENT = 4521985;
    public static final int HOURS_OF_USE = 4521986;
    public static final short MODULE_ID = 69;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "POWER_DATA_HOURS_OF_USE" : "POWER_DATA_CURRENT";
    }
}
