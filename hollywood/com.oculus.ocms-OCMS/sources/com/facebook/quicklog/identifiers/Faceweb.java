package com.facebook.quicklog.identifiers;

public class Faceweb {
    public static final int CHROME_LOAD = 2359297;
    public static final int FACEWEB_PAGE_SESSION = 2359302;
    public static final int FW_FRAGMENT_CREATE = 2359304;
    public static final short MODULE_ID = 36;
    public static final int PAGE_NETWORK_LOAD = 2359299;
    public static final int PAGE_RPC_LOAD_COMPLETED = 2359300;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 6 ? i != 8 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "FACEWEB_PAGE_RPC_LOAD_COMPLETED" : "FACEWEB_PAGE_NETWORK_LOAD" : "FACEWEB_FW_FRAGMENT_CREATE" : "FACEWEB_FACEWEB_PAGE_SESSION" : "FACEWEB_CHROME_LOAD";
    }
}
