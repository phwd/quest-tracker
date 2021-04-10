package com.facebook.quicklog.identifiers;

public class LsPlugin {
    public static final int IMPLEMENTATION_METHOD = 44367876;
    public static final int IMPLEMENTATION_METHOD_UNSAMPLED = 44376563;
    public static final int INTERFACE_METHOD = 44367873;
    public static final int INTERFACE_METHOD_UNSAMPLED = 44380521;
    public static final int IS_IMPLEMENTATION_NEEDED = 44367874;
    public static final int IS_IMPLEMENTATION_NEEDED_UNSAMPLED = 44374549;
    public static final int KILL_SWITCH = 44367875;
    public static final int KILL_SWITCH_UNSAMPLED = 44372399;
    public static final short MODULE_ID = 677;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 4527 ? i != 6677 ? i != 8691 ? i != 12649 ? "UNDEFINED_QPL_EVENT" : "LS_PLUGIN_INTERFACE_METHOD_UNSAMPLED" : "LS_PLUGIN_IMPLEMENTATION_METHOD_UNSAMPLED" : "LS_PLUGIN_IS_IMPLEMENTATION_NEEDED_UNSAMPLED" : "LS_PLUGIN_KILL_SWITCH_UNSAMPLED" : "LS_PLUGIN_IMPLEMENTATION_METHOD" : "LS_PLUGIN_KILL_SWITCH" : "LS_PLUGIN_IS_IMPLEMENTATION_NEEDED" : "LS_PLUGIN_INTERFACE_METHOD";
    }
}
