package com.facebook.quicklog.identifiers;

public class Helium {
    public static final int LOAD = 47655768;
    public static final short MODULE_ID = 727;
    public static final int PATCH = 47644673;
    public static final int SETUP = 47650251;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 5579 ? i != 11096 ? "UNDEFINED_QPL_EVENT" : "HELIUM_LOAD" : "HELIUM_SETUP" : "HELIUM_PATCH";
    }
}
