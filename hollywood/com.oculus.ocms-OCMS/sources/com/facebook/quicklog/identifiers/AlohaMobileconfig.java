package com.facebook.quicklog.identifiers;

public class AlohaMobileconfig {
    public static final int ADD_REMOVE_LISTENER = 60950170;
    public static final int GET_API = 60963987;
    public static final int GET_MCS_VALUE = 60948481;
    public static final int INIT_MC_CLIENT = 60956249;
    public static final short MODULE_ID = 930;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 1690 ? i != 7769 ? i != 15507 ? "UNDEFINED_QPL_EVENT" : "ALOHA_MOBILECONFIG_GET_API" : "ALOHA_MOBILECONFIG_INIT_MC_CLIENT" : "ALOHA_MOBILECONFIG_ADD_REMOVE_LISTENER" : "ALOHA_MOBILECONFIG_GET_MCS_VALUE";
    }
}
