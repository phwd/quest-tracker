package com.facebook.quicklog.identifiers;

public class ReactNativeExpressRoute {
    public static final int INIT_EXPRESS_ROUTE = 49545217;
    public static final short MODULE_ID = 756;
    public static final int READ_EXPRESS_ROUTE_ENTRY = 49545218;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "REACT_NATIVE_EXPRESS_ROUTE_READ_EXPRESS_ROUTE_ENTRY" : "REACT_NATIVE_EXPRESS_ROUTE_INIT_EXPRESS_ROUTE";
    }
}
