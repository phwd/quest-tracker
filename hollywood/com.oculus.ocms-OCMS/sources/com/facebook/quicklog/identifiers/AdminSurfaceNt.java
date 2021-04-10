package com.facebook.quicklog.identifiers;

public class AdminSurfaceNt {
    public static final int ADMIN_SURFACE_LOAD_TIME_NT = 25427969;
    public static final int ADMIN_SURFACE_PTR_LOAD_TIME_NT = 25427970;
    public static final short MODULE_ID = 388;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "ADMIN_SURFACE_NT_ADMIN_SURFACE_PTR_LOAD_TIME_NT" : "ADMIN_SURFACE_NT_ADMIN_SURFACE_LOAD_TIME_NT";
    }
}
