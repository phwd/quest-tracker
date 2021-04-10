package com.facebook.quicklog.identifiers;

public class OculusHome {
    public static final int INIT = 50528257;
    public static final short MODULE_ID = 771;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "OCULUS_HOME_INIT";
    }
}
