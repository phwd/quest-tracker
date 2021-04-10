package com.facebook.quicklog.identifiers;

public class ReactNativeExamples {
    public static final short MODULE_ID = 780;
    public static final int RELAY_INTEGRATION_TTRC = 51118081;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "REACT_NATIVE_EXAMPLES_RELAY_INTEGRATION_TTRC";
    }
}
