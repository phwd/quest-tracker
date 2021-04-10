package com.facebook.quicklog.identifiers;

public class ContextualProfileInitialLoad {
    public static final int CONTEXTUAL_PROFILE_INITIAL_LOAD_TTRC_ANDROID = 40566786;
    public static final int IM_CONTEXTUAL_PROFILE_INITIAL_LOAD = 40566788;
    public static final int IM_CONTEXTUAL_PROFILE_INITIAL_LOAD_ANDROID = 40566789;
    public static final short MODULE_ID = 619;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "CONTEXTUAL_PROFILE_INITIAL_LOAD_IM_CONTEXTUAL_PROFILE_INITIAL_LOAD_ANDROID" : "CONTEXTUAL_PROFILE_INITIAL_LOAD_IM_CONTEXTUAL_PROFILE_INITIAL_LOAD" : "CONTEXTUAL_PROFILE_INITIAL_LOAD_CONTEXTUAL_PROFILE_INITIAL_LOAD_TTRC_ANDROID";
    }
}
