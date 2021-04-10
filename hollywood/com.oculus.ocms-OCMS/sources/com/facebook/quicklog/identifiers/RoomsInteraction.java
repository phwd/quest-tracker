package com.facebook.quicklog.identifiers;

public class RoomsInteraction {
    public static final short MODULE_ID = 2159;
    public static final int ROOMS_JOIN_E2E_WITH_INTERFACES_FUNNEL = 141502565;

    public static String getMarkerName(int i) {
        return i != 10341 ? "UNDEFINED_QPL_EVENT" : "ROOMS_INTERACTION_ROOMS_JOIN_E2E_WITH_INTERFACES_FUNNEL";
    }
}
