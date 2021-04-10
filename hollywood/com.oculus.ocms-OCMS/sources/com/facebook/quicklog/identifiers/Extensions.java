package com.facebook.quicklog.identifiers;

public class Extensions {
    public static final int COMPONENT_LAYOUT = 31457285;
    public static final int HOST_GET_EXTENSIONS = 31457283;
    public static final int HOST_IS_NEEDED = 31457284;
    public static final int IS_NEEDED = 31457281;
    public static final short MODULE_ID = 480;
    public static final int ON_GET_EXTENSION = 31457282;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "EXTENSIONS_COMPONENT_LAYOUT" : "EXTENSIONS_HOST_IS_NEEDED" : "EXTENSIONS_HOST_GET_EXTENSIONS" : "EXTENSIONS_ON_GET_EXTENSION" : "EXTENSIONS_IS_NEEDED";
    }
}
