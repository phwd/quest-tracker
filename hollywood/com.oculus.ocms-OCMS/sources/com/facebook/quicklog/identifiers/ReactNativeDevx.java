package com.facebook.quicklog.identifiers;

public class ReactNativeDevx {
    public static final int METRO_STARTUP = 159785008;
    public static final short MODULE_ID = 2438;

    public static String getMarkerName(int i) {
        return i != 8240 ? "UNDEFINED_QPL_EVENT" : "REACT_NATIVE_DEVX_METRO_STARTUP";
    }
}
