package com.facebook.quicklog.identifiers;

public class Menu {
    public static final short MODULE_ID = 16026;
    public static final int ORDER_SURFACE_TTI = 1050296012;
    public static final int STRUCTURED_ORDER_MOBILE = 1050290325;

    public static String getMarkerName(int i) {
        return i != 10389 ? i != 16076 ? "UNDEFINED_QPL_EVENT" : "MENU_ORDER_SURFACE_TTI" : "MENU_STRUCTURED_ORDER_MOBILE";
    }
}
