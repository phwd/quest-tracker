package com.facebook.quicklog.identifiers;

public class FbliteAsyncActions {
    public static final int ASYNC_SUBMIT = 37748737;
    public static final int IG_CARBON_ASYNC_ACTION = 37748738;
    public static final short MODULE_ID = 576;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "FBLITE_ASYNC_ACTIONS_IG_CARBON_ASYNC_ACTION" : "FBLITE_ASYNC_ACTIONS_ASYNC_SUBMIT";
    }
}
