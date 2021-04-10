package com.facebook.quicklog.identifiers;

public class Drawablehierarchy {
    public static final int DRAWABLE_HIERARCHY_CONTROLLER = 983041;
    public static final int IMAGE_DISPLAY = 983042;
    public static final short MODULE_ID = 15;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "DRAWABLEHIERARCHY_IMAGE_DISPLAY" : "DRAWABLEHIERARCHY_DRAWABLE_HIERARCHY_CONTROLLER";
    }
}
