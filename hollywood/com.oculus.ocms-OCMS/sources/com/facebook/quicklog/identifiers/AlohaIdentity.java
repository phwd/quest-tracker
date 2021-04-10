package com.facebook.quicklog.identifiers;

public class AlohaIdentity {
    public static final int LOADED_OWNERS_TEST = 527180820;
    public static final short MODULE_ID = 8044;

    public static String getMarkerName(int i) {
        return i != 9236 ? "UNDEFINED_QPL_EVENT" : "ALOHA_IDENTITY_LOADED_OWNERS_TEST";
    }
}
