package com.facebook.quicklog.identifiers;

public class MliteApplicationModules {
    public static final int API_EXECUTE = 37421057;
    public static final short MODULE_ID = 571;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "MLITE_APPLICATION_MODULES_API_EXECUTE";
    }
}
