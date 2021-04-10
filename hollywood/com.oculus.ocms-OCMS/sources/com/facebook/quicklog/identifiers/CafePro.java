package com.facebook.quicklog.identifiers;

public class CafePro {
    public static final short MODULE_ID = 11065;
    public static final int USER_INTERACTION = 725160706;

    public static String getMarkerName(int i) {
        return i != 4866 ? "UNDEFINED_QPL_EVENT" : "CAFE_PRO_USER_INTERACTION";
    }
}
