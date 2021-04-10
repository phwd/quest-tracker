package com.facebook.quicklog.identifiers;

public class StorySurface {
    public static final short MODULE_ID = 531;
    public static final int STORY_SURFACE_TTI = 34799617;
    public static final int STORY_SURFACE_V1_TTRC = 34799621;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "STORY_SURFACE_STORY_SURFACE_V1_TTRC" : "STORY_SURFACE_STORY_SURFACE_TTI";
    }
}
