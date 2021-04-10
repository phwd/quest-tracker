package com.facebook.quicklog.identifiers;

public class ProfileTailLoad {
    public static final short MODULE_ID = 319;
    public static final int PROFILE_TAIL_LOAD = 20905985;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "PROFILE_TAIL_LOAD_PROFILE_TAIL_LOAD";
    }
}
