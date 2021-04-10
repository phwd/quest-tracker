package com.facebook.quicklog.identifiers;

public class GemstoneSecretCrush {
    public static final int GEMSTONE_SC_TTRC_ANDROID = 50921476;
    public static final int GEMSTONE_SC_TTRC_IOS = 50921477;
    public static final short MODULE_ID = 777;

    public static String getMarkerName(int i) {
        return i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "GEMSTONE_SECRET_CRUSH_GEMSTONE_SC_TTRC_IOS" : "GEMSTONE_SECRET_CRUSH_GEMSTONE_SC_TTRC_ANDROID";
    }
}
