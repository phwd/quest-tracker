package com.facebook.quicklog.identifiers;

public class MessengerEfabDerisk {
    public static final int EFAB_CLICK = 49217537;
    public static final short MODULE_ID = 751;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_EFAB_DERISK_EFAB_CLICK";
    }
}
