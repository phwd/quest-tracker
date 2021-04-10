package com.facebook.quicklog.identifiers;

public class SubsampledExtensions {
    public static final int HOST_GET_EXTENSIONS = 39256068;
    public static final int IS_NEEDED = 39256065;
    public static final short MODULE_ID = 599;
    public static final int ON_GET_EXTENSION = 39256067;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "SUBSAMPLED_EXTENSIONS_HOST_GET_EXTENSIONS" : "SUBSAMPLED_EXTENSIONS_ON_GET_EXTENSION" : "SUBSAMPLED_EXTENSIONS_IS_NEEDED";
    }
}
