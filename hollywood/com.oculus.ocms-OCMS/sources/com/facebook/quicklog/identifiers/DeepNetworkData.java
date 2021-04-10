package com.facebook.quicklog.identifiers;

public class DeepNetworkData {
    public static final int GENERAL = 6094849;
    public static final short MODULE_ID = 93;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "DEEP_NETWORK_DATA_GENERAL";
    }
}
