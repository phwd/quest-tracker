package com.facebook.quicklog.identifiers;

public class ProfileInitialLoad {
    public static final short MODULE_ID = 318;
    public static final int PROFILE_INITIAL_LOAD = 20840449;
    public static final int PROFILE_INITIAL_LOAD_TTRC_ANDROID = 20840451;
    public static final int PROFILE_INITIAL_LOAD_VNEXT_TTRC_ANDROID = 20852906;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 3 ? i != 12458 ? "UNDEFINED_QPL_EVENT" : "PROFILE_INITIAL_LOAD_PROFILE_INITIAL_LOAD_VNEXT_TTRC_ANDROID" : "PROFILE_INITIAL_LOAD_PROFILE_INITIAL_LOAD_TTRC_ANDROID" : "PROFILE_INITIAL_LOAD_PROFILE_INITIAL_LOAD";
    }
}
