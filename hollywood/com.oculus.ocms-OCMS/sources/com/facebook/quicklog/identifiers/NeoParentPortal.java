package com.facebook.quicklog.identifiers;

public class NeoParentPortal {
    public static final short MODULE_ID = 555;
    public static final int NEO_PROFILE_TTI = 36372481;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "NEO_PARENT_PORTAL_NEO_PROFILE_TTI";
    }
}
