package com.facebook.quicklog.identifiers;

public class MessengerShops {
    public static final short MODULE_ID = 14043;
    public static final int PDP_TTI = 920332323;

    public static String getMarkerName(int i) {
        return i != 10275 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_SHOPS_PDP_TTI";
    }
}
