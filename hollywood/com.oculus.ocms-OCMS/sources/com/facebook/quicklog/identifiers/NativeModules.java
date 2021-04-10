package com.facebook.quicklog.identifiers;

public class NativeModules {
    public static final int DUMMY_EVENT_FOR_TESTING_004 = 21626885;
    public static final short MODULE_ID = 330;
    public static final int SO_LOADED = 21626881;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "NATIVE_MODULES_DUMMY_EVENT_FOR_TESTING_004" : "NATIVE_MODULES_SO_LOADED";
    }
}
