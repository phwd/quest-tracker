package com.facebook.quicklog.identifiers;

public class AlohaWorkVc {
    public static final int HOME_SCREEN_LOAD = 440144064;
    public static final short MODULE_ID = 6716;

    public static String getMarkerName(int i) {
        return i != 4288 ? "UNDEFINED_QPL_EVENT" : "ALOHA_WORK_VC_HOME_SCREEN_LOAD";
    }
}
